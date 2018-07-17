package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteEntity;
import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteRuntimeException;
import cn.rongcapital.mc2.me.commons.infrastructure.reactor.ReactorNettyClient;
import cn.rongcapital.mc2.me.commons.infrastructure.reactor.ReactorNettyResult;
import cn.rongcapital.mc2.me.commons.util.GsonUtils;
import cn.rongcapital.mc2.me.cpm.domain.FieldName;

@Document(collection = "campaign")
public class Campaign extends IgniteEntity {

	@Field(FieldName.FIELD_TENANT_ID)
	private long tenantId;

	@Field(FieldName.FIELD_NAME)
	private String name;

	@Field(FieldName.FIELD_DESCRIPTION)
	private String description;

	@Field(FieldName.FIELD_BIZ_TIME_TYPE)
	private int bizTimeFlag;

	@Field(FieldName.FIELD_BIZ_DATE_RANGE)
	private CampaignBizDateRange bizDateRange;

	@Field(FieldName.FIELD_DIAGRAM)
	private CampaignDiagram diagram;

	@Field(FieldName.FIELD_STARTUP_POLICY)
	private CampaignStartupPolicy startupPolicy;

	@Field(FieldName.FIELD_SHUTDOWN_POLICY)
	private CampaignShutdownPolicy shutdownPolicy;

	@Field(FieldName.FIELD_IS_DELETED)
	private boolean isDeleted;

	@Field(FieldName.FIELD_CREATE_OPERATION)
	private CampaignOperation createOperation;

	@Field(FieldName.FIELD_MODIFY_OPERATION)
	private CampaignOperation modifyOperation;

	@Field(FieldName.FIELD_UPDATE_OPERATION)
	private CampaignOperation updateOperation;

	@Field(FieldName.FIELD_DELETE_OPERATION)
	private CampaignOperation deleteOperation;

	@Field(FieldName.FIELD_PUBLISH_OPERATION)
	private CampaignOperation publishOperation;

	@Field(FieldName.FIELD_TERMINATE_OPERATION)
	private CampaignOperation terminateOperation;

	public Campaign() {}

	public Campaign(long tenantId, long userId, String userName, String name, String description, int bizTimeFlag, Date bizStartTime, Date bizEndTime) {
		this.tenantId = tenantId;
		this.name = name;
		this.description = description;
		this.bizTimeFlag = bizTimeFlag;
		this.bizDateRange = new CampaignBizDateRange(bizStartTime, bizEndTime);
		this.createBy = userId;
		this.createAt = new Date();
		this.createOperation = new CampaignOperation(this.createAt, userId, userName);
	}

	public Campaign(long tenantId, long userId, String userName, String name, String description, int bizTimeFlag, Date bizStartTime, Date bizEndTime, Map<String, Object> node) {
		this(tenantId, userId, userName, name, description, bizTimeFlag, bizStartTime, bizEndTime);
		this.diagram = new CampaignDiagram(node);
	}

	/**
	 * 租户用户删除某活动
	 * @param tenantId
	 * @param userId
	 * @param userName
	 */
	public void deleteBy(long tenantId, long userId, String userName) {
		if (this.isDeleted) {
			throw new IgniteRuntimeException(5000, "已经被删除了, 不可用重复删除!");
		}
		if (this.tenantId != tenantId) {
			throw new IgniteRuntimeException(5001, "不能删除其他租户的数据!");
		}
		if (CampaignStatus.DRAFT != this.status) {
			throw new IgniteRuntimeException(5002, "只能删除草稿状态的活动!");
		}
		this.isDeleted = true;
		this.tenantId = tenantId;
		this.updateBy = userId;
		this.updateAt = new Date();
		this.deleteOperation = new CampaignOperation(this.updateAt, userId, userName);
	}

	/**
	 * 修改草稿信息
	 * @param userId
	 * @param userName
	 * @param name
	 * @param description
	 * @param bizTimeFlag
	 * @param bizStartTime
	 * @param bizEndTime
	 */
	public void modifyDraftBy(long userId, String userName, String name, String description, int bizTimeFlag, Date bizStartTime, Date bizEndTime) {
		this.updateBy = userId;
		this.updateAt = new Date();
		this.name = name;
		this.description = description;
		this.bizTimeFlag = bizTimeFlag;
		this.bizDateRange.resetStartTime(bizStartTime);
		this.bizDateRange.resetEndTime(bizEndTime);
		this.modifyOperation = new CampaignOperation(this.updateAt, userId, userName);
		this.updateOperation = new CampaignOperation(this.updateAt, userId, userName);
	}

	/**
	 * 更新画布信息
	 * @param userId
	 * @param userName
	 * @param diagramJson
	 */
	public void updateDiagramBy(long userId, String userName, String diagramJson) {
		this.updateBy = userId;
		this.updateAt = new Date();
		this.diagram = GsonUtils.create().fromJson(diagramJson, CampaignDiagram.class);
		this.updateOperation = new CampaignOperation(this.updateAt, userId, userName);
	}

	/**
	 * 发布流程
	 * @param userId
	 * @param userName
	 * @param token
	 */
	public CampaignFlow publish(long tenantId, long userId, String userName, String token) {
		// 检查活动
		this.checkValidity();
		// 解析流程图
		List<CampaignNode> nodes = diagram.parseNodes(tenantId, token);
		// 预发布
		long count = nodes.stream().map(node -> {
			return node.prePublish();
		}).filter(ok -> ok).count();
		// 判断预发布结果
		if (nodes.size() == count) {
			// 固化标签
			if (curingTags(nodes)) {
				this.status = CampaignStatus.RUNNING;
				this.updateAt = new Date();
				this.updateBy = userId;
				this.publishOperation = new CampaignOperation(this.updateAt, userId, userName);
				return new CampaignFlow(this.id, this.tenantId, this.startupPolicy, this.shutdownPolicy, nodes);
			}
		}
		return null;
	}

	/**
	 * 终止流程
	 * @param userId
	 * @param userName
	 * @param token
	 */
	public void terminate(long tenantId, long userId, String userName, String token) {
		List<CampaignNode> nodes = diagram.parseNodes(tenantId, token);
		// 取消固化标签
		if (releaseCuringTags(nodes)) {
			this.status = CampaignStatus.DONE;
			this.updateAt = new Date();
			this.updateBy = userId;
			this.terminateOperation = new CampaignOperation(this.updateAt, userId, userName);
		}
	}

	/**
	 * 检查活动有效性
	 */
	public void checkValidity() {
		if (CampaignStatus.DRAFT != this.status) {
			throw new IgniteRuntimeException(5000, "只能发布草稿状态的活动!");
		}
		if (this.diagram.isNodeEmpty()) {
			throw new IgniteRuntimeException(5000, "活动编排图中一个节点都没有!");
		}
		if (this.diagram.isLineEmpty()) {
			throw new IgniteRuntimeException(5000, "活动编排图中一条连线都没有!");
		}
	}

	/**
	 * 固化标签
	 * @return
	 */
	public boolean curingTags(List<CampaignNode> nodes) {
		// 提取固化标签ID集
		Set<Long> tagIds = nodes.stream().flatMap(node -> {
			return node.extractTagIds().stream();
		}).filter(stream -> null != stream).collect(Collectors.toSet());
		// 调用远程固化接口
		ReactorNettyResult result = ReactorNettyClient.post("", tagIds, Map.class).block();
		if (result.isOk()) {
			// TODO
		}
		return false;
	}

	/**
	 * 释放固化的标签
	 */
	public boolean releaseCuringTags(List<CampaignNode> nodes) {
		// 提取固化标签ID集
		Set<Long> tagIds = nodes.stream().flatMap(node -> {
			return node.extractTagIds().stream();
		}).filter(stream -> null != stream).collect(Collectors.toSet());
		// 调用远程取消固化接口
		ReactorNettyResult result = ReactorNettyClient.post("", tagIds, Map.class).block();
		if (result.isOk()) {
			// TODO
		}
		return false;
	}

	public <T> T toFindOut(Class<T> outType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id);
		map.put("name", this.name);
		map.put("description", this.description);
		map.put("status", this.status);
		if (null != this.createAt) {
			map.put("createAt", this.createAt);
		}
		if (null != this.createBy) {
			map.put("createBy", this.createBy);
		}
		if (null != this.updateAt) {
			map.put("updateAt", this.updateAt);
		}
		if (null != this.updateBy) {
			map.put("updateBy", this.updateBy);
		}
		if (null != this.modifyOperation) {
			this.modifyOperation.putOperationTime(map, "modifyTime");
			this.modifyOperation.putOperatorName(map, "modifier");
		}
		if (null != this.updateOperation) {
			this.updateOperation.putOperationTime(map, "updateTime");
			this.updateOperation.putOperatorName(map, "updater");
		}
		if (null != this.deleteOperation) {
			this.deleteOperation.putOperationTime(map, "deleteTime");
			this.deleteOperation.putOperatorName(map, "deleter");
		}
		if (null != this.publishOperation) {
			this.publishOperation.putOperationTime(map, "publishTime");
			this.publishOperation.putOperatorName(map, "publisher");
		}
		if (null != this.terminateOperation) {
			this.terminateOperation.putOperationTime(map, "terminateTime");
			this.terminateOperation.putOperatorName(map, "terminator");
		}
		map.put("bizTimeFlag", this.bizTimeFlag);
		this.bizDateRange.putStartTime(map, "bizStartTime");
		this.bizDateRange.putEndTime(map, "bizEndTime");
		this.startupPolicy.putTime(map, "startupTime");
		this.shutdownPolicy.putTime(map, "shutdownTime");
		String json = GsonUtils.create().toJson(map);
		return GsonUtils.create().fromJson(json, outType);
	}

	public <T> T toFindOneOut(Class<T> outType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id);
		map.put("name", this.name);
		map.put("description", this.description);
		map.put("status", this.status);
		if (null != this.createAt) {
			map.put("createAt", this.createAt);
		}
		if (null != this.createBy) {
			map.put("createBy", this.createBy);
		}
		if (null != this.updateAt) {
			map.put("updateAt", this.updateAt);
		}
		if (null != this.updateBy) {
			map.put("updateBy", this.updateBy);
		}
		if (null != this.diagram) {
			map.put("diagramJson", GsonUtils.create().toJson(this.diagram));
		}
		if (null != this.modifyOperation) {
			this.modifyOperation.putOperationTime(map, "modifyTime");
			this.modifyOperation.putOperatorName(map, "modifier");
		}
		if (null != this.updateOperation) {
			this.updateOperation.putOperationTime(map, "updateTime");
			this.updateOperation.putOperatorName(map, "updater");
		}
		if (null != this.deleteOperation) {
			this.deleteOperation.putOperationTime(map, "deleteTime");
			this.deleteOperation.putOperatorName(map, "deleter");
		}
		if (null != this.publishOperation) {
			this.publishOperation.putOperationTime(map, "publishTime");
			this.publishOperation.putOperatorName(map, "publisher");
		}
		if (null != this.terminateOperation) {
			this.terminateOperation.putOperationTime(map, "terminateTime");
			this.terminateOperation.putOperatorName(map, "terminator");
		}
		map.put("bizTimeFlag", this.bizTimeFlag);
		this.bizDateRange.putStartTime(map, "bizStartTime");
		this.bizDateRange.putEndTime(map, "bizEndTime");
		this.startupPolicy.putTime(map, "startupTime");
		this.startupPolicy.putMode(map, "startupMode");
		this.shutdownPolicy.putTime(map, "shutdownTime");
		this.shutdownPolicy.putMode(map, "shutdownMode");
		this.shutdownPolicy.putOption(map, "shutdownOption");
		String json = GsonUtils.create().toJson(map);
		return GsonUtils.create().fromJson(json, outType);
	}

}

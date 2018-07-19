package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteEntity;
import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteRuntimeException;
import cn.rongcapital.mc2.me.commons.infrastructure.reactor.ReactorNettyClient;
import cn.rongcapital.mc2.me.commons.infrastructure.reactor.ReactorNettyResult;
import cn.rongcapital.mc2.me.commons.util.GsonUtils;
import cn.rongcapital.mc2.me.cpm.domain.FieldName;

@SuppressWarnings("serial")
@Document(collection = "campaign")
public class Campaign extends IgniteEntity {

	@QuerySqlField
	@Field(FieldName.FIELD_TENANT_ID)
	private long tenantId;

	@QuerySqlField
	@Field(FieldName.FIELD_NAME)
	private String name;

	@QuerySqlField
	@Field(FieldName.FIELD_DESCRIPTION)
	private String description;

	@QuerySqlField
	@Field(FieldName.FIELD_BIZ_DATE_FLAG)
	private int bizDateFlag;

	@QuerySqlField
	@Field(FieldName.FIELD_BIZ_DATE_RANGE)
	private CampaignBizDateRange bizDateRange;

	@QuerySqlField
	@Field(FieldName.FIELD_DIAGRAM)
	private CampaignDiagram diagram;

	@QuerySqlField
	@Field(FieldName.FIELD_STARTUP_POLICY)
	private CampaignStartupPolicy startupPolicy;

	@QuerySqlField
	@Field(FieldName.FIELD_SHUTDOWN_POLICY)
	private CampaignShutdownPolicy shutdownPolicy;

	@QuerySqlField
	@Field(FieldName.FIELD_IS_DELETED)
	private boolean isDeleted;

	@QuerySqlField
	@Field(FieldName.FIELD_CREATE_OPERATION)
	private CampaignOperation createOperation;

	@QuerySqlField
	@Field(FieldName.FIELD_MODIFY_OPERATION)
	private CampaignOperation modifyOperation;

	@QuerySqlField
	@Field(FieldName.FIELD_UPDATE_OPERATION)
	private CampaignOperation updateOperation;

	@QuerySqlField
	@Field(FieldName.FIELD_DELETE_OPERATION)
	private CampaignOperation deleteOperation;

	@QuerySqlField
	@Field(FieldName.FIELD_PUBLISH_OPERATION)
	private CampaignOperation publishOperation;

	@QuerySqlField
	@Field(FieldName.FIELD_TERMINATE_OPERATION)
	private CampaignOperation terminateOperation;

	public Campaign() {}

	public Campaign(long tenantId, long userId, String userName, String name, String description, int bizDateFlag, Long bizStartDate, Long bizEndDate) {
		this.status = CampaignStatus.DRAFT;
		this.tenantId = tenantId;
		this.name = name;
		this.description = description;
		this.bizDateFlag = bizDateFlag;
		this.bizDateRange = new CampaignBizDateRange(bizStartDate, bizEndDate);
		this.createBy = userId;
		this.createAt = new Date();
		this.createOperation = new CampaignOperation(this.createAt, userId, userName);
	}

	public Campaign(long tenantId, long userId, String userName, String name, String description, int bizDateFlag, Long bizStartDate, Long bizEndDate, Map<String, Object> node) {
		this(tenantId, userId, userName, name, description, bizDateFlag, bizStartDate, bizEndDate);
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
	public void modifyDraftBy(long userId, String userName, String name, String description, int bizDateFlag, Long bizStartDate, Long bizEndDate) {
		this.updateBy = userId;
		this.updateAt = new Date();
		this.name = name;
		this.description = description;
		this.bizDateFlag = bizDateFlag;
		this.bizDateRange.resetStart(bizStartDate);
		this.bizDateRange.resetEnd(bizEndDate);
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
	 * @param tenantId 
	 * @param userId
	 * @param userName
	 * @param userToken
	 * @param shutdownOption 
	 * @param shutdownTime 
	 * @param shutdownMode 
	 * @param startupTime 
	 * @param startupMode 
	 */
	public CampaignFlow publish(long tenantId, long userId, String userName, String userToken, Integer startupMode, Long startupTime, Integer shutdownMode, Long shutdownTime, Integer shutdownOption) {
		if (tenantId != this.tenantId) {
			// TODO
		}
		// 检查活动
		this.checkValidity();
		// 解析流程图
		List<CampaignNode> nodes = diagram.parseNodes(this.tenantId, userToken);
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
				this.startupPolicy = new CampaignStartupPolicy(startupMode, startupTime);
				this.shutdownPolicy = new CampaignShutdownPolicy(shutdownMode, shutdownTime, shutdownOption);
				return new CampaignFlow(this.id, this.tenantId, this.startupPolicy, this.shutdownPolicy, nodes);
			}
		}
		return null;
	}

	/**
	 * 终止流程
	 * @param tenantId
	 * @param userId 
	 * @param userName
	 * @param shutdownOption 
	 */
	public void terminate(long tenantId, long userId, String userName, int shutdownOption) {
		if (tenantId != this.tenantId) {
			// TODO
		}
		List<CampaignNode> nodes = diagram.parseNodes(this.tenantId);
		// 取消固化标签
		if (releaseCuringTags(nodes)) {
			this.status = CampaignStatus.DONE;
			this.updateAt = new Date();
			this.updateBy = userId;
			this.terminateOperation = new CampaignOperation(this.updateAt, userId, userName);
			this.shutdownPolicy.updateBy(shutdownOption);
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
		if (null != this.createOperation) {
			this.createOperation.putOperationTime(map, "createTime");
			this.createOperation.putOperatorName(map, "creator");
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
		map.put("bizDateFlag", this.bizDateFlag);
		if (null != this.bizDateRange) {
			this.bizDateRange.putStart(map, "bizStartDate");
			this.bizDateRange.putEnd(map, "bizEndDate");
		}
		if (null != this.startupPolicy) {
			this.startupPolicy.putTime(map, "startupTime");
		}
		if (null != this.shutdownPolicy) {
			this.shutdownPolicy.putTime(map, "shutdownTime");
		}
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
		if (null != this.createOperation) {
			this.createOperation.putOperationTime(map, "createTime");
			this.createOperation.putOperatorName(map, "creator");
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
		map.put("bizDateFlag", this.bizDateFlag);
		if (null != this.bizDateRange) {
			this.bizDateRange.putStart(map, "bizStartDate");
			this.bizDateRange.putEnd(map, "bizEndDate");
		}
		if (null != this.startupPolicy) {
			this.startupPolicy.putTime(map, "startupTime");
			this.startupPolicy.putMode(map, "startupMode");
		}
		if (null != this.shutdownPolicy) {
			this.shutdownPolicy.putTime(map, "shutdownTime");
			this.shutdownPolicy.putMode(map, "shutdownMode");
			this.shutdownPolicy.putOption(map, "shutdownOption");
		}
		String json = GsonUtils.create().toJson(map);
		return GsonUtils.create().fromJson(json, outType);
	}

}

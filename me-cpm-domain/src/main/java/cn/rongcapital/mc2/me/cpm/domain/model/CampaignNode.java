package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteRuntimeException;
import cn.rongcapital.mc2.me.commons.infrastructure.reactor.ReactorNettyResult;

public class CampaignNode {

	@Expose
	private String nodeId;

	private String category;

	@Expose
	private String type;

	@Expose
	private CampaignNodeSetting setting;

	@Expose
	private CampaignNodeComponent component;

	@Expose
	private List<CampaignNodeLineIncoming> incomings;

	@Expose
	private List<CampaignNodeLineOutcoming> outcomings;

	private CampaignCrowd crowd;

	public CampaignNode() {}

	@SuppressWarnings("unchecked")
	public CampaignNode(long tenantId, String token, Map<String, Object> node, List<Map<String, Object>> incomings, List<Map<String, Object>> outcomings) {
		this.nodeId = (String) node.get("id");
		Map<String, Object> option = (Map<String, Object>) node.get("option");
		this.type = (String) option.get("type");
		this.category = (String) option.get("category");
		String faasName = (String) option.get("faasName");
		String faasVersion = (String) option.get("faasVersion");
		Map<String, Object> data = (Map<String, Object>) option.get("data");
		Integer errorHandle = (Integer) data.get("errorHandle");
		Integer delayMode = (Integer) data.get("timeType");
		Long delayInterval = (Long) data.get("expiredInterval");
		Long expireInterval = (Long) data.get("expiredTime");
		data.remove("errorHandle");
		data.remove("timeType");
		data.remove("expiredInterval");
		data.remove("expiredTime");
		this.setting = new CampaignNodeSetting(errorHandle, delayMode, delayInterval, expireInterval);
		this.component = new CampaignNodeComponent(faasName, faasVersion, token, data);

		CampaignNodeType typeEnum = CampaignNodeType.valueOf(type);
		if (CampaignNodeType.START.equals(typeEnum)) {
			Integer crowdType = (Integer) data.get("crowdType");
			Map<String, Object> setting = (Map<String, Object>) data.get("setting");
			Integer refreshMode = (Integer) setting.get("refreshMode");
			String refreshCron = (String) setting.get("refreshCron");
			Integer limitMaxCount = (Integer) setting.get("limitMaxCount");
			Integer limitPeriod = (Integer) setting.get("limitPeriod");
			Integer limitPeriodCount = (Integer) setting.get("limitPeriodCount");
			if (CampaignCrowdType.SEGMENT == crowdType) {
				String originalExpression = (String) data.get("computationRule");
				List<Map<String, Object>> tags = (List<Map<String, Object>>) data.get("tags");
				this.crowd = new CampaignCrowdSegment(tenantId, originalExpression, tags, refreshMode, refreshCron, limitMaxCount, limitPeriod, limitPeriodCount);
			} else {
				// TODO
			}
		}
	}

	/**
	 * 预发布
	 * @return
	 */
	public boolean prePublish() {
		ReactorNettyResult result = crowd.checkAvailable().block();
		if (result.isOk()) {
			// TODO
			checkData();
			checkIncomings();
			checkOutcomings();
			return true;
		}
		return false;
	}

	/**
	 * 验证数据
	 * @return
	 */
	public void checkData() {
		CampaignNodeType typeEnum = CampaignNodeType.valueOf(type);
		if (!CampaignNodeType.END.equals(typeEnum)) {
			if (component.checkData()) {
				throw new IgniteRuntimeException(5000, "节点数据验证失败!");
			}
		}
	}

	/**
	 * 验证进入连线
	 * @return
	 */
	public void checkIncomings() {
		CampaignNodeType typeEnum = CampaignNodeType.valueOf(type);
		if (!CampaignNodeType.START.equals(typeEnum)) {
			if (CollectionUtils.isEmpty(incomings)) {
				throw new IgniteRuntimeException(5000, "活动编排图中可能存在某个节点缺失传入连线");
			}
		}
		// 同个节点的输入和输出不可连线
		if (CollectionUtils.isNotEmpty(incomings)) {
			long count = incomings.stream().filter(line -> line.isRing()).count();
			if (0 != count) {
				throw new IgniteRuntimeException(5000, "同个节点的输入和输出不可连线");
			}
		}
	}

	/**
	 * 验证输出连线
	 * @return
	 */
	public void checkOutcomings() {
		CampaignNodeType typeEnum = CampaignNodeType.valueOf(type);
		if (!CampaignNodeType.END.equals(typeEnum)) {
			if (CollectionUtils.isEmpty(outcomings)) {
				throw new IgniteRuntimeException(5000, "活动编排图中可能存在某个节点缺失传出连线");
			}
		}
		// 同个节点的输入和输出不可连线
		if (CollectionUtils.isNotEmpty(outcomings)) {
			long count = outcomings.stream().filter(line -> line.isRing()).count();
			if (0 != count) {
				throw new IgniteRuntimeException(5000, "同个节点的输入和输出不可连线");
			}
		}
	}

	/**
	 * 提取标签ID集
	 * @return
	 */
	public Set<Long> extractTagIds() {
		CampaignNodeCategory categoryEnum = CampaignNodeCategory.valueOf(category);
		if (CampaignNodeCategory.AUDIENCE.equals(categoryEnum)) {
			return this.crowd.extractTagIds();
		} else {
			return this.component.extractTagIds();
		}
	}

}

package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.mongodb.core.mapping.Field;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.commons.infrastructure.reactor.ReactorNettyClient;
import cn.rongcapital.mc2.me.commons.infrastructure.reactor.ReactorNettyResult;
import cn.rongcapital.mc2.me.cpm.domain.FieldName;
import reactor.core.publisher.Mono;

public class CampaignCrowdSegment extends CampaignCrowd {

	@Expose
	@Field(FieldName.FIELD_ORIGINAL_EXPRESSION)
	private String originalExpression;

	@Expose
	private String targetExpression;

	@Field(FieldName.FIELD_TAGS)
	private Set<CampaignTag> tags;

	public CampaignCrowdSegment() {}

	public CampaignCrowdSegment(long tenantId, String originalExpression, List<Map<String, Object>> tags, int refreshMode, String refreshCron, int limitMaxCount, int limitPeriod, int limitPeriodCount) {
		super(refreshMode, refreshCron, limitMaxCount, limitPeriod, limitPeriodCount);
		this.originalExpression = originalExpression;
		if (CollectionUtils.isNotEmpty(tags)) {
			this.tags = tags.stream().map(tag -> {
				int id = (Integer) tag.get("id");
				String values = (String) tag.get("tag_values");
				return new CampaignTag(id, values);
			}).collect(Collectors.toSet());
			this.targetExpression = this.generateTargetExpression(tenantId);
		}
	}

	/**
	 * 生产目标表达式(代码版本号)
	 * @param tenantId
	 * @return
	 */
	public String generateTargetExpression(long tenantId) {
		StringBuilder tempExpressionBuilder = new StringBuilder(this.originalExpression);
		this.tags.stream().forEach(tag -> {
			long id = tag.getId();
			String values = tag.getValues();
			int version = tag.extractVersion(tenantId);
			String tempExpression = tempExpressionBuilder.toString();
			tempExpression = tempExpression.replaceAll(String.format("%d\\s*:\\s*%s", id, values) , String.format("%d:%s:%d", id, values, version));
			tempExpressionBuilder.setLength(0);
			tempExpressionBuilder.append(tempExpression);
		});
		return tempExpressionBuilder.toString();
	}

	@Override
	public Mono<ReactorNettyResult> checkAvailable() {
		return ReactorNettyClient.post("", "", Map.class);
	}

	@Override
	public Set<Long> extractTagIds() {
		return tags.stream().map(tag -> tag.getId()).collect(Collectors.toSet());
	}

}

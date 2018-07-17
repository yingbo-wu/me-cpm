package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Field;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.commons.infrastructure.reactor.ReactorNettyResult;
import cn.rongcapital.mc2.me.cpm.domain.FieldName;
import reactor.core.publisher.Mono;

public abstract class CampaignCrowd {

	@Expose
	@Field(FieldName.FIELD_REFRESH)
	protected CampaignCrowdRefresh refresh;

	@Expose
	@Field(FieldName.FIELD_LIMIT)
	protected CampaignCrowdLimit limit;

	public CampaignCrowd() {}

	public CampaignCrowd(int refreshMode, String refreshCron, int limitMaxCount, int limitPeriod, int limitPeriodCount) {
		this.refresh = new CampaignCrowdRefresh(refreshMode, refreshCron);
		this.limit = new CampaignCrowdLimit(limitMaxCount, limitPeriod, limitPeriodCount);
	}

	/**
	 * 检查可用性
	 * @return
	 */
	public abstract Mono<ReactorNettyResult> checkAvailable();

	/**
	 * 提取标签ID集
	 * @return
	 */
	public abstract Set<Long> extractTagIds();

}

package cn.rongcapital.mc2.me.cpm.domain.model;

import org.springframework.data.mongodb.core.mapping.Field;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.cpm.domain.FieldName;

public class CampaignCrowdLimit {

	@Expose
	@Field(FieldName.FIELD_MAX_COUNT)
	private int maxCount;

	@Expose
	@Field(FieldName.FIELD_PERIOD)
	private int period;

	@Expose
	@Field(FieldName.FIELD_PERIOD_COUNT)
	private int periodCount;

	public CampaignCrowdLimit(int maxCount, int period, int periodCount) {
		this.maxCount = maxCount;
		this.period = period;
		this.periodCount = periodCount;
	}

}

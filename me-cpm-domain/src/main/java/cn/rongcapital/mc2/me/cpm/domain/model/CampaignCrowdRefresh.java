package cn.rongcapital.mc2.me.cpm.domain.model;

import org.springframework.data.mongodb.core.mapping.Field;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.cpm.domain.FieldName;

public class CampaignCrowdRefresh {

	@Expose
	@Field(FieldName.FIELD_MODE)
	private int mode;

	@Expose
	@Field(FieldName.FIELD_CRON)
	private String cron;

	public CampaignCrowdRefresh() {}

	public CampaignCrowdRefresh(int mode, String cron) {
		this.mode = mode;
		this.cron = cron;
	}

}

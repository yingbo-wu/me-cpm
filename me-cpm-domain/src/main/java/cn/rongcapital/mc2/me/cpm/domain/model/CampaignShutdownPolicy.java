package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Date;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Field;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.cpm.domain.FieldName;

public class CampaignShutdownPolicy {

	@Expose
	@Field(FieldName.FIELD_MODE)
	private String mode;

	@Expose
	@Field(FieldName.FIELD_TIME)
	private Date time;

	@Expose
	private String expression;

	@Expose
	@Field(FieldName.FIELD_ADVANCED)
	private CampaignShutdownAdvanced advanced;

	public Date lookupTime() {
		return time;
	}

	public void putTime(Map<String, Object> map, String key) {
		if (null != this.time) {
			map.put(key, this.time);
		}
	}

	public void putMode(Map<String, Object> map, String key) {
		if (null != this.mode) {
			map.put(key, this.mode);
		}
	}

	public void putOption(Map<String, Object> map, String key) {
		if (null != this.mode) {
			advanced.putOption(map, key);
		}
	}

}

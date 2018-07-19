package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.springframework.data.mongodb.core.mapping.Field;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.cpm.domain.FieldName;

public class CampaignShutdownPolicy {

	@Expose
	@QuerySqlField
	@Field(FieldName.FIELD_MODE)
	private int mode;

	@Expose
	@QuerySqlField
	@Field(FieldName.FIELD_TIME)
	private Date time;

	@Expose
	@QuerySqlField
	private String expression;

	@Expose
	@QuerySqlField
	@Field(FieldName.FIELD_ADVANCED)
	private CampaignShutdownAdvanced advanced;

	public CampaignShutdownPolicy() {}

	public CampaignShutdownPolicy(int mode, Long time, Integer option) {
		this.mode = mode;
		if (null != time && 0 < time) {
			this.time = new Date(time);
			this.expression = DateFormatUtils.format(this.time, "ss mm HH dd MM ? yyyy");
		}
		if (null != option) {
			this.advanced = new CampaignShutdownAdvanced(option);
		}
	}

	public Date lookupTime() {
		return time;
	}

	public void putTime(Map<String, Object> map, String key) {
		if (null != this.time) {
			map.put(key, this.time);
		}
	}

	public void putMode(Map<String, Object> map, String key) {
		map.put(key, this.mode);
	}

	public void putOption(Map<String, Object> map, String key) {
		if (1 == this.mode) {
			advanced.putOption(map, key);
		}
	}

	public void updateBy(int option) {
		if (null == this.advanced) {
			this.advanced = new CampaignShutdownAdvanced(option);
		} else {
			this.advanced.updateBy(option);
		}
	}

}

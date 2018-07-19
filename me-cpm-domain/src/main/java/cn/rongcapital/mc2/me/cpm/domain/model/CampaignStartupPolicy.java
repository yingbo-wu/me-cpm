package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.springframework.data.mongodb.core.mapping.Field;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.cpm.domain.FieldName;

public class CampaignStartupPolicy {

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

	public CampaignStartupPolicy() {
		if (null != this.time) {
			this.expression = DateFormatUtils.format(this.time, "ss mm HH dd MM ? yyyy");
		}
	}

	public CampaignStartupPolicy(int mode, Long time) {
		this.mode = mode;
		if (null != time && 0 < time) {
			this.time = new Date(time);
			this.expression = DateFormatUtils.format(this.time, "ss mm HH dd MM ? yyyy");
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

}

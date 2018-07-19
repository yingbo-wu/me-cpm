package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Date;
import java.util.Map;

import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.springframework.data.mongodb.core.mapping.Field;

import cn.rongcapital.mc2.me.cpm.domain.FieldName;

public class CampaignBizDateRange {

	@QuerySqlField
	@Field(FieldName.FIELD_SATRT)
	private Date start;

	@QuerySqlField
	@Field(FieldName.FIELD_END)
	private Date end;

	public CampaignBizDateRange() {}

	public CampaignBizDateRange(Long start, Long end) {
		if (null != start) {
			this.start = new Date(start);
		}
		if (null != end) {
			this.end = new Date(end);
		}
	}

	public Date lookupStart() {
		return this.start;
	}

	public Date lookupEnd() {
		return this.end;
	}

	public void putStart(Map<String, Object> map, String key) {
		if (null != this.start) {
			map.put(key, this.start);
		}
	}

	public void putEnd(Map<String, Object> map, String key) {
		if (null != this.end) {
			map.put(key, this.end);
		}
	}

	public void resetStart(Long start) {
		this.start = new Date(start);
	}

	public void resetEnd(Long end) {
		this.end = new Date(end);
	}

}

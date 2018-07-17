package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Date;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Field;

import cn.rongcapital.mc2.me.cpm.domain.FieldName;

public class CampaignBizDateRange {

	@Field(FieldName.FIELD_SATRT_TIME)
	private Date startTime;

	@Field(FieldName.FIELD_END_TIME)
	private Date endTime;

	public CampaignBizDateRange() {}

	public CampaignBizDateRange(Date startTime, Date endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Date lookupStartTime() {
		return startTime;
	}

	public Date lookupEndTime() {
		return endTime;
	}

	public void putStartTime(Map<String, Object> map, String key) {
		if (null != this.startTime) {
			map.put(key, this.startTime);
		}
	}

	public void putEndTime(Map<String, Object> map, String key) {
		if (null != this.endTime) {
			map.put(key, this.endTime);
		}
	}

	public void resetStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void resetEndTime(Date endTime) {
		this.endTime = endTime;
	}

}

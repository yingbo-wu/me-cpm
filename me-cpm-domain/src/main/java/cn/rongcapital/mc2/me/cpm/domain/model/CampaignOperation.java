package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Date;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Field;

import cn.rongcapital.mc2.me.cpm.domain.FieldName;

public class CampaignOperation {

	@Field(FieldName.FIELD_TYPE)
	private String type;

	@Field(FieldName.FIELD_USER_ID)
	private long userId;

	@Field(FieldName.FIELD_USER_NAME)
	private String userName;

	@Field(FieldName.FIELD_TIME)
	private Date time;

	public CampaignOperation(Date time, long userId, String userName) {
		this.time = time;
		this.userId = userId;
		this.userName = userName;
	}

	public void putOperationTime(Map<String, Object> map, String key) {
		if (null != this.time) {
			map.put(key, this.time);
		}
	}

	public void putOperatorName(Map<String, Object> map, String key) {
		if (null != this.userName) {
			map.put(key, this.userName);
		}
	}

}

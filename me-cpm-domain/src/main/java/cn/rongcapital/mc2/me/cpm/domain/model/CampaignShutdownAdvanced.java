package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Field;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.cpm.domain.FieldName;

public class CampaignShutdownAdvanced {

	@Expose
	@Field(FieldName.FIELD_OPTION)
	private int option;

	public CampaignShutdownAdvanced(int option) {
		this.option = option;
	}

	public void putOption(Map<String, Object> map, String key) {
		map.put(key, this.option);
	}

}

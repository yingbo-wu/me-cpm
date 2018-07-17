package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Map;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.commons.util.GsonUtils;

public class CampaignWebNode {

	@Expose
	private String id;

	@Expose
	private Map<String, Object> offset;

	@Expose
	private Map<String, Object> option;

	@Expose
	private Map<String, Object> data;

	@Expose
	private String showtitle;

	@SuppressWarnings("unchecked")
	public CampaignWebNode(String componentId, String dataJson) {
		this.id = String.format("node_%s_%d", componentId, System.currentTimeMillis());
		this.data = GsonUtils.create().fromJson(dataJson, Map.class);
		this.offset.put("left", "517");
		this.offset.put("top", "133");
		this.showtitle = (String) this.data.get("name");
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> toMap() {
		String json = GsonUtils.create().toJson(this);
		return GsonUtils.create().fromJson(json, Map.class);
	}

}

package cn.rongcapital.mc2.me.cpm.domain.model;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.commons.infrastructure.spring.PropertyContext;

public class CampaignNodePrincipal {

	@Expose
	private String name;

	@Expose
	private String version;

	@Expose
	private String token;

	@Expose
	private String uid;

	public CampaignNodePrincipal() {}

	public CampaignNodePrincipal(String name, String version, String token) {
		this.name = name;
		this.version = version;
		this.token = token;
		this.uid = PropertyContext.build().getProperty("TODO");
	}

}

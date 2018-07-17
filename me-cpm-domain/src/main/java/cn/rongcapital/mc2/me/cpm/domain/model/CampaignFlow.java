package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.List;

import com.google.gson.annotations.Expose;

public class CampaignFlow {

	@Expose
	private String campaignId;

	@Expose
	private Long tenantId;

	@Expose
	private CampaignFlowDiagram diagram;

	@Expose
	private CampaignStartupPolicy startupPolicy;

	@Expose
	private CampaignShutdownPolicy shutdownPolicy;

	public CampaignFlow() {}

	public CampaignFlow(String campaignId, Long tenantId, CampaignStartupPolicy startupPolicy, CampaignShutdownPolicy shutdownPolicy, List<CampaignNode> nodes) {
		this.campaignId = campaignId;
		this.tenantId = tenantId;
		this.diagram = new CampaignFlowDiagram(nodes);
		this.startupPolicy = startupPolicy;
		this.shutdownPolicy = shutdownPolicy;
	}

}

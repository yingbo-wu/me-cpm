package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.List;

import com.google.gson.annotations.Expose;

public class CampaignFlowDiagram {

	@Expose
	private List<CampaignNode> nodes;

	public CampaignFlowDiagram() {}

	public CampaignFlowDiagram(List<CampaignNode> nodes) {
		this.nodes = nodes;
	}

}

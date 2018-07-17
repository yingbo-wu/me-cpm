package cn.rongcapital.mc2.me.cpm.api.dto;

import java.util.List;

import cn.rongcapital.mc2.me.commons.api.ApiOut;

public class CampaignPagingOut implements ApiOut {

	private long total;

	private List<CampaignFindOut> list;

	public CampaignPagingOut(long total, List<CampaignFindOut> list) {
		this.total = total;
		this.list = list;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<CampaignFindOut> getList() {
		return list;
	}

	public void setList(List<CampaignFindOut> list) {
		this.list = list;
	}

}

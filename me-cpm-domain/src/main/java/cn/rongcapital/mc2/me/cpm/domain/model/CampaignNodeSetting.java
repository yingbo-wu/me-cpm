package cn.rongcapital.mc2.me.cpm.domain.model;

import com.google.gson.annotations.Expose;

public class CampaignNodeSetting {

	@Expose
	private int errorHandle;

	@Expose
	private CampaignNodeDelayHandle delayHandle;

	public CampaignNodeSetting(Integer errorHandle, Integer delayMode, Long delayInterval, Long expireInterval) {
		this.errorHandle = errorHandle;
		if (null != delayMode) {
			this.delayHandle = new CampaignNodeDelayHandle(delayMode, delayInterval, expireInterval);
		}
	}

}

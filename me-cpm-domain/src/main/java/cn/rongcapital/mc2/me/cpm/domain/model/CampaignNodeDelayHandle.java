package cn.rongcapital.mc2.me.cpm.domain.model;

import com.google.gson.annotations.Expose;

public class CampaignNodeDelayHandle {

	@Expose
	private int mode;

	@Expose
	private long interval;

	public CampaignNodeDelayHandle() {}

	public CampaignNodeDelayHandle(int mode, long delayInterval, long expireInterval) {
		this.mode = mode;
		if (0 == mode) {
			this.interval = delayInterval;
		} else {
			this.interval = expireInterval;
		}
	}

}

package cn.rongcapital.mc2.me.cpm.domain.model;

import com.google.gson.annotations.Expose;

public class CampaignNodeTransition {

	@Expose
	private boolean isDefault;

	@Expose
	private Object threshold;

	@Override
	public int hashCode() {
		if (null != this.threshold) {
			return this.threshold.hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return null != this.threshold && this.threshold.equals(obj);
	}

}

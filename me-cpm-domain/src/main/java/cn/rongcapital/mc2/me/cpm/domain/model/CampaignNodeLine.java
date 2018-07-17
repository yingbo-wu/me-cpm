package cn.rongcapital.mc2.me.cpm.domain.model;

import com.google.gson.annotations.Expose;

import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteRuntimeException;

public abstract class CampaignNodeLine {

	@Expose
	protected String fromId;

	@Expose
	protected String toId;

	@Expose
	protected CampaignNodeTransition transiation;

	public boolean isRing() {
		if (null != this.fromId && null != this.toId) {
			return this.fromId.equals(this.toId);
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (null == this.fromId && null == this.toId) {
			throw new IgniteRuntimeException(5000, "fromId、toId都不能为空");
		}
		return (this.fromId + this.toId).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (null == this.fromId && null == this.toId) {
			throw new IgniteRuntimeException(5000, "fromId、toId都不能为空");
		}
		if (obj instanceof CampaignNodeLine) {
			CampaignNodeLine line = (CampaignNodeLine) obj;
			return this.fromId.equals(line.fromId) && this.toId.equals(line.toId);
		} else {
			return false;
		}
	}

}

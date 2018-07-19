package cn.rongcapital.mc2.me.cpm.api.dto;

import javax.validation.constraints.NotNull;

import cn.rongcapital.mc2.me.commons.api.ApiIn;

public class ComponentFindIn implements ApiIn {

	@NotNull(message = "4401")
	private Long tenantId;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

}

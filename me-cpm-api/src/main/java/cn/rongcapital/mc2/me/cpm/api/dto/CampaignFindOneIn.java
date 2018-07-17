package cn.rongcapital.mc2.me.cpm.api.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import cn.rongcapital.mc2.me.commons.api.ApiIn;

public class CampaignFindOneIn implements ApiIn {

	@NotBlank(message = "4000")
	private String id;

	@NotNull(message = "4001")
	private Long tenantId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

}

package cn.rongcapital.mc2.me.cpm.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import cn.rongcapital.mc2.me.commons.api.ApiIn;

public class CampaignNameCheckIn implements ApiIn {

	private String id;

	@NotNull(message = "4000")
	private Long tenantId;

	@NotBlank(message = "4001")
	@Pattern(regexp = "[\u4e00-\u9fa5A-Za-z0-9]*", message = "4002")
	@Length(min = 0, max = 30, message = "4003")
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

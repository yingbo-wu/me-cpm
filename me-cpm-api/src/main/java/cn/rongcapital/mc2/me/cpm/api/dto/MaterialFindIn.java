package cn.rongcapital.mc2.me.cpm.api.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import cn.rongcapital.mc2.me.commons.api.ApiIn;

public class MaterialFindIn implements ApiIn {

	@NotNull(message = "4001")
	private Long tenantId;

	@NotBlank(message = "4002")
	private String type;

	private String keyword;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}

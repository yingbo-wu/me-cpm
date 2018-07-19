package cn.rongcapital.mc2.me.cpm.api.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import cn.rongcapital.mc2.me.commons.api.ApiIn;

public class CampaignDeleteIn implements ApiIn {

	@NotBlank(message = "4001")
	private String id;

	@NotNull(message = "4401")
	private Long tenantId;

	@NotNull(message = "4402")
	private Long userId;

	@NotNull(message = "4403")
	private String userName;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}

package cn.rongcapital.mc2.me.cpm.api.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.rongcapital.mc2.me.commons.api.ApiIn;

public class CampaignUpdateIn implements ApiIn {

	@NotNull(message = "4001")
	private String id;

	@NotEmpty(message = "4002")
	@Length(min = 1, max = 15, message = "4003")
	private String name;

	@Length(min = 0, max = 100, message = "4004")
	private String description;

	@NotNull(message = "4005")
	private Integer bizDateFlag;
	
	private Long bizStartDate;

	private Long bizEndDate;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBizDateFlag() {
		return bizDateFlag;
	}

	public void setBizDateFlag(Integer bizDateFlag) {
		this.bizDateFlag = bizDateFlag;
	}

	public Long getBizStartDate() {
		return bizStartDate;
	}

	public void setBizStartDate(Long bizStartDate) {
		this.bizStartDate = bizStartDate;
	}

	public Long getBizEndDate() {
		return bizEndDate;
	}

	public void setBizEndDate(Long bizEndDate) {
		this.bizEndDate = bizEndDate;
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

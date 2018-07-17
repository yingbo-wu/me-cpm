package cn.rongcapital.mc2.me.cpm.api.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.rongcapital.mc2.me.commons.api.ApiIn;

public class CampaignUpdateIn implements ApiIn {

	@NotNull(message = "4001")
	private String id;

	@NotNull(message = "4009")
	private Long userId;

	private String userName;

	@NotEmpty(message = "4002")
	@Length(min = 1, max = 15, message = "4003")
	private String name;

	@Length(min = 0, max = 100, message = "4004")
	private String description;

	@NotNull(message = "4005")
	private Long tenantId;

	@NotNull(message = "4006")
	private Integer bizTimeFlag;

	private Long bizStartTime;

	private Long bizEndTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getBizTimeFlag() {
		return bizTimeFlag;
	}

	public void setBizTimeFlag(Integer bizTimeFlag) {
		this.bizTimeFlag = bizTimeFlag;
	}

	public Long getBizStartTime() {
		return bizStartTime;
	}

	public void setBizStartTime(Long bizStartTime) {
		this.bizStartTime = bizStartTime;
	}

	public Long getBizEndTime() {
		return bizEndTime;
	}

	public void setBizEndTime(Long bizEndTime) {
		this.bizEndTime = bizEndTime;
	}

}

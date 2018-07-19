package cn.rongcapital.mc2.me.cpm.api.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import cn.rongcapital.mc2.me.commons.api.ApiIn;

public class CampaignFlowPublishIn implements ApiIn {

	@NotNull(message = "4001")
	private String id;

	@NotNull(message = "4002")
	private Integer startupMode;

	private Long startupTime;

	@NotNull(message = "4003")
	private Integer shutdownMode;

	private Long shutdownTime;

	private Integer shutdownOption;

	@NotNull(message = "4401")
	private Long tenantId;

	@NotNull(message = "4402")
	private Long userId;

	@NotEmpty(message = "4403")
	private String userName;

	@NotEmpty(message = "4404")
	private String userToken;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStartupMode() {
		return startupMode;
	}

	public void setStartupMode(Integer startupMode) {
		this.startupMode = startupMode;
	}

	public Long getStartupTime() {
		return startupTime;
	}

	public void setStartupTime(Long startupTime) {
		this.startupTime = startupTime;
	}

	public Integer getShutdownMode() {
		return shutdownMode;
	}

	public void setShutdownMode(Integer shutdownMode) {
		this.shutdownMode = shutdownMode;
	}

	public Long getShutdownTime() {
		return shutdownTime;
	}

	public void setShutdownTime(Long shutdownTime) {
		this.shutdownTime = shutdownTime;
	}

	public Integer getShutdownOption() {
		return shutdownOption;
	}

	public void setShutdownOption(Integer shutdownOption) {
		this.shutdownOption = shutdownOption;
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

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

}

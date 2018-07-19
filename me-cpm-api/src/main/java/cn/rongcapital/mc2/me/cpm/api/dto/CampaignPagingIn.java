package cn.rongcapital.mc2.me.cpm.api.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import cn.rongcapital.mc2.me.commons.api.ApiIn;

public class CampaignPagingIn implements ApiIn {

	private String name;

	private List<Integer> status;

	private boolean isOwn;

	private Date fromDate;

	private Date toDate;

	private Integer pageIndex;

	private Integer pageSize;

	@NotNull(message = "4401")
	private Long tenantId;

	@NotNull(message = "4402")
	private Long userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getStatus() {
		return status;
	}

	public void setStatus(List<Integer> status) {
		this.status = status;
	}

	public boolean isOwn() {
		return isOwn;
	}

	public void setOwn(boolean isOwn) {
		this.isOwn = isOwn;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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

}

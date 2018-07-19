package cn.rongcapital.mc2.me.cpm.api.dto;

import java.util.Date;

import cn.rongcapital.mc2.me.commons.api.ApiOut;

public class CampaignFindOneOut implements ApiOut {

	private String id;

	private String name;

	private String description;

	private Integer bizDateFlag;

	private Date bizStartDate;

	private Date bizEndDate;

	private Date startupTime;

	private Date shutdownTime;

	private Integer startupMode;

	private Integer shutdownMode;

	private Integer shutdownOption;

	private String diagramJson;

	private Integer status;

	private Date createAt;

	private Long createBy;

	private Date updateAt;

	private Long updateBy;

	private Date createTime;

	private Date modifyTime;

	private Date updateTime;

	private Date deleteTime;

	private Date publishTime;

	private Date terminateTime;

	private String creator;

	private String modifier;

	private String updater;

	private String deleter;

	private String publisher;

	private String terminator;

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

	public Date getBizStartDate() {
		return bizStartDate;
	}

	public void setBizStartDate(Date bizStartDate) {
		this.bizStartDate = bizStartDate;
	}

	public Date getBizEndDate() {
		return bizEndDate;
	}

	public void setBizEndDate(Date bizEndDate) {
		this.bizEndDate = bizEndDate;
	}

	public Date getStartupTime() {
		return startupTime;
	}

	public void setStartupTime(Date startupTime) {
		this.startupTime = startupTime;
	}

	public Date getShutdownTime() {
		return shutdownTime;
	}

	public void setShutdownTime(Date shutdownTime) {
		this.shutdownTime = shutdownTime;
	}

	public Integer getStartupMode() {
		return startupMode;
	}

	public void setStartupMode(Integer startupMode) {
		this.startupMode = startupMode;
	}

	public Integer getShutdownMode() {
		return shutdownMode;
	}

	public void setShutdownMode(Integer shutdownMode) {
		this.shutdownMode = shutdownMode;
	}

	public Integer getShutdownOption() {
		return shutdownOption;
	}

	public void setShutdownOption(Integer shutdownOption) {
		this.shutdownOption = shutdownOption;
	}

	public String getDiagramJson() {
		return diagramJson;
	}

	public void setDiagramJson(String diagramJson) {
		this.diagramJson = diagramJson;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getTerminateTime() {
		return terminateTime;
	}

	public void setTerminateTime(Date terminateTime) {
		this.terminateTime = terminateTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getDeleter() {
		return deleter;
	}

	public void setDeleter(String deleter) {
		this.deleter = deleter;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTerminator() {
		return terminator;
	}

	public void setTerminator(String terminator) {
		this.terminator = terminator;
	}

}

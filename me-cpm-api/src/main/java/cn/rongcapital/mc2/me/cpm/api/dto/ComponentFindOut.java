package cn.rongcapital.mc2.me.cpm.api.dto;

import java.util.Date;

import cn.rongcapital.mc2.me.commons.api.ApiOut;

public class ComponentFindOut implements ApiOut {

	private String id;

	private String name;

	private String description;

	private String category;

	private String type;

	private String materialDsl;

	private String displayLabel;

	private Integer ownerType;

	private String logo;

	private String template;

	private String templateReadOnly;

	private String promptContent;

	private Integer usageCount;

	private String providerName;

	private String faasType;

	private String faasName;

	private String faasVersion;

	private String resourceCode;

	private Date updateAt;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMaterialDsl() {
		return materialDsl;
	}

	public void setMaterialDsl(String materialDsl) {
		this.materialDsl = materialDsl;
	}

	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public Integer getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTemplateReadOnly() {
		return templateReadOnly;
	}

	public void setTemplateReadOnly(String templateReadOnly) {
		this.templateReadOnly = templateReadOnly;
	}

	public String getPromptContent() {
		return promptContent;
	}

	public void setPromptContent(String promptContent) {
		this.promptContent = promptContent;
	}

	public Integer getUsageCount() {
		return usageCount;
	}

	public void setUsageCount(Integer usageCount) {
		this.usageCount = usageCount;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getFaasType() {
		return faasType;
	}

	public void setFaasType(String faasType) {
		this.faasType = faasType;
	}

	public String getFaasName() {
		return faasName;
	}

	public void setFaasName(String faasName) {
		this.faasName = faasName;
	}

	public String getFaasVersion() {
		return faasVersion;
	}

	public void setFaasVersion(String faasVersion) {
		this.faasVersion = faasVersion;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

}

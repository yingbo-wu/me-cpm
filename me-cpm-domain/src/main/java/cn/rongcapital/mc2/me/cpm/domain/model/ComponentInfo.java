package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteEntity;
import cn.rongcapital.mc2.me.commons.util.GsonUtils;
import cn.rongcapital.mc2.me.cpm.domain.FieldName;

@SuppressWarnings("serial")
@Document(collection = "component")
public class ComponentInfo extends IgniteEntity {

	@QuerySqlField
	@Field(FieldName.FIELD_PROVIDER_ID)
	private String providerId;

	@QuerySqlField
	@Field(FieldName.FIELD_PROVIDER_NAME)
	private String providerName;

	@QuerySqlField
	@Field(FieldName.FIELD_NAME)
	private String name;

	@QuerySqlField
	@Field(FieldName.FIELD_CATEGORY)
	private String category;

	@QuerySqlField
	@Field(FieldName.FIELD_TYPE)
	private String type;

	@QuerySqlField
	@Field(FieldName.FIELD_LOGO)
	private String logo;

	@QuerySqlField
	@Field(FieldName.FIELD_DESCRIPTION)
	private String description;

	@QuerySqlField
	@Field(FieldName.FIELD_PROMPT_CONTENT)
	private String promptContent;

	@QuerySqlField
	@Field(FieldName.FIELD_USAGE_COUNT)
	private Integer usageCount;

	@QuerySqlField
	@Field(FieldName.FIELD_OWNER_TYPE)
	private Integer ownerType;

	@QuerySqlField
	@Field(FieldName.FIELD_MATERIAL_DSL)
	private String materialDsl;

	@QuerySqlField
	@Field(FieldName.FIELD_DISPLAY_LABEL)
	private String displayLabel;

	@QuerySqlField
	@Field(FieldName.FIELD_FAAS_TYPE)
	private String faasType;

	@QuerySqlField
	@Field(FieldName.FIELD_FAAS_NAME)
	private String faasName;

	@QuerySqlField
	@Field(FieldName.FIELD_FAAS_VERSION)
	private String faasVersion;

	@QuerySqlField
	@Field(FieldName.FIELD_TEMPLATE)
	private String template;

	@QuerySqlField
	@Field(FieldName.FIELD_TEMPLATE_READ_ONLY)
	private String templateReadOnly;

	@QuerySqlField
	@Field(FieldName.FIELD_RESOURCE_CODE)
	private String resourceCode;

	@QuerySqlField
	@Field(FieldName.FIELD_PATH_OPTIONS)
	private List<Map<String, Object>> pathOptions;

	public <T> T toFindOut(Class<T> outType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id);
		map.put("name", this.name);
		map.put("description", this.description);
		map.put("category", this.category);
		map.put("type", this.type);
		map.put("materialDsl", this.materialDsl);
		map.put("displayLabel", this.displayLabel);
		map.put("ownerType", this.ownerType);
		map.put("logo", this.logo);
		map.put("template", this.template);
		map.put("templateReadOnly", this.templateReadOnly);
		map.put("promptContent", this.promptContent);
		map.put("usageCount", this.usageCount);
		map.put("providerName", this.providerName);
		map.put("faasType", this.faasType);
		map.put("faasName", this.faasName);
		map.put("faasVersion", this.faasVersion);
		map.put("resourceCode", this.resourceCode);
		map.put("updateAt", this.updateAt);
		String json = GsonUtils.create().toJson(map);
		return GsonUtils.create().fromJson(json, outType);
	}

}

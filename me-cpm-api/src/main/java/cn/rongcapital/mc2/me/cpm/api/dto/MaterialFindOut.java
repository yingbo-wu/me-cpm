package cn.rongcapital.mc2.me.cpm.api.dto;

import cn.rongcapital.mc2.me.commons.api.ApiOut;

public class MaterialFindOut implements ApiOut {

	private String key;

	private String value;

	private String template;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}

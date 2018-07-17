package cn.rongcapital.mc2.me.cpm.domain.model;

import cn.rongcapital.mc2.me.commons.feign.tag.TagResult;
import cn.rongcapital.mc2.me.commons.feign.tag.api.TagInfoApi;
import cn.rongcapital.mc2.me.commons.feign.tag.dto.TagInfo;
import cn.rongcapital.mc2.me.commons.util.FeignUtils;

public class CampaignTag {

	private long id;

	private String values;

	public CampaignTag() {}

	public CampaignTag(int id, String values) {
		this.id = id;
		this.values = values;
	}

	public int extractVersion(long tenantId) {
		TagInfoApi api = FeignUtils.proxyRestful(TagInfoApi.class, "");
		TagResult<TagInfo> result = api.get(id, tenantId);
		if (result.isOk()) {
			TagInfo tagInfo = result.getData();
			return tagInfo.getTagVersion();
		}
		return 0;
	}

	public long getId() {
		return id;
	}

	public String getValues() {
		return values;
	}

}

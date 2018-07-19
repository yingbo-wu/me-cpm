package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;

import com.google.gson.annotations.Expose;

public class CampaignNodeComponent {

	@Expose
	private Map<String, Object> data;

	@Expose
	private CampaignNodePrincipal principal;

	public CampaignNodeComponent() {}

	public CampaignNodeComponent(String faasName, String faasVersion, String token, Map<String, Object> data) {
		this.data = data;
		if (null == token) {
			this.principal = new CampaignNodePrincipal(faasName, faasVersion);
		} else {
			this.principal = new CampaignNodePrincipal(faasName, faasVersion, token);
		}
	}

	public CampaignNodeComponent(String faasName, String faasVersion, Map<String, Object> data) {
		this(faasName, faasVersion, null, data);
	}

	/**
	 * 验证数据
	 * @return
	 */
	public boolean checkData() {
		return MapUtils.isNotEmpty(data);
	}

	/**
	 * 鉴权
	 * @return
	 */
	public boolean authorize() {
		// TODO
		return false;
	}

	/**
	 * 提取标签ID集
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<Long> extractTagIds() {
		if (data.containsKey("tags")) {
			List<Map<String, Object>> tags = (List<Map<String, Object>>) data.get("tags");
			return tags.stream().map(tag -> (Long) tag.get("id")).collect(Collectors.toSet());
		}
		return null;
	}

}

package cn.rongcapital.mc2.me.cpm.app.service;

import org.apache.ignite.resources.SpringResource;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.commons.api.ApiException;
import cn.rongcapital.mc2.me.commons.api.ApiResult;
import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteService;
import cn.rongcapital.mc2.me.cpm.api.CampaignDiagramApi;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignDiagramSaveIn;
import cn.rongcapital.mc2.me.cpm.domain.service.CampaignDiagramSaveService;

@Service
public class CampaignDiagramService extends IgniteService implements CampaignDiagramApi {

	private static final long serialVersionUID = -2966152760015817877L;

	@SpringResource(resourceName = "campaignDiagramSaveService")
	private transient CampaignDiagramSaveService campaignDiagramSaveService;

	/**
	 * 保存活动编排图
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ApiResult<Void> save(CampaignDiagramSaveIn in) {
		// 活动id
		String id = in.getId();
		// 租户id
		long tenantId = in.getTenantId();
		// 用户id
		long userId = in.getUserId();
		// 用户name
		String userName = in.getUserName();
		// 编排图JSON串
		String diagramJson = in.getDiagramJson();
		try {
			campaignDiagramSaveService.save(id, tenantId, userId, userName, diagramJson);
			return ApiResult.success();
		} catch (ApiException e) {
			return e.result();
		}
	}

}

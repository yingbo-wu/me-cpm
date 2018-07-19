package cn.rongcapital.mc2.me.cpm.app.service;

import org.apache.ignite.resources.SpringResource;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.commons.api.ApiResult;
import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteService;
import cn.rongcapital.mc2.me.cpm.api.CampaignFlowApi;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignFlowPublishIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignFlowShutdownIn;
import cn.rongcapital.mc2.me.cpm.domain.service.CampaignFlowPublishService;
import cn.rongcapital.mc2.me.cpm.domain.service.CampaignFlowShutdownService;

@Service
public class CampaignFlowService extends IgniteService implements CampaignFlowApi {

	private static final long serialVersionUID = -2966152760015817877L;

	@SpringResource(resourceName = "campaignFlowPublishService")
	private transient CampaignFlowPublishService campaignFlowPublishService;

	@SpringResource(resourceName = "campaignFlowShutdownService")
	private transient CampaignFlowShutdownService campaignFlowShutdownService;

	@Override
	public ApiResult<Void> publish(CampaignFlowPublishIn in) {
		String id = in.getId();
		Long tenantId = in.getTenantId();
		Long userId = in.getUserId();
		String userName = in.getUserName();
		String userToken = in.getUserToken();
		Integer startupMode = in.getStartupMode();
		Long startupTime = in.getStartupTime();
		Integer shutdownMode = in.getShutdownMode();
		Long shutdownTime = in.getShutdownTime();
		Integer shutdownOption = in.getShutdownOption();
		try {
			campaignFlowPublishService.publish(id, tenantId, userId, userName, userToken, startupMode, startupTime, shutdownMode, shutdownTime, shutdownOption);
			return ApiResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(5000, e.getMessage());
		}
	}

	@Override
	public ApiResult<Void> shutdown(CampaignFlowShutdownIn in) {
		String id = in.getId();
		Long tenantId = in.getTenantId();
		Long userId = in.getUserId();
		String userName = in.getUserName();
		Integer shutdownOption = in.getShutdownOption();
		try {
			campaignFlowShutdownService.shutdown(id, tenantId, userId, userName, shutdownOption);
			return ApiResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(5000, e.getMessage());
		}
	}

}

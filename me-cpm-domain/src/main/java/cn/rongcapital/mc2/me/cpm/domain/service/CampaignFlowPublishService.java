package cn.rongcapital.mc2.me.cpm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.commons.communication.event.CampaignPublishedEvent;
import cn.rongcapital.mc2.me.commons.infrastructure.redisson.RedissonEventPublisher;
import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;
import cn.rongcapital.mc2.me.cpm.domain.model.CampaignFlow;
import cn.rongcapital.mc2.me.cpm.domain.repository.CampaignRepository;

@Service
public class CampaignFlowPublishService {

	@Autowired
	private CampaignRepository campaignRepository;

	public void publish(String id, Long tenantId, Long userId, String userName, String userToken, Integer startupMode, Long startupTime, Integer shutdownMode, Long shutdownTime, Integer shutdownOption) {
		Campaign campaign = campaignRepository.findOne(id);
		CampaignFlow campaignFlow = campaign.publish(tenantId, userId, userName, userToken, startupMode, startupTime, shutdownMode, shutdownTime, shutdownOption);
		CampaignPublishedEvent event = new CampaignPublishedEvent(campaignFlow);
		RedissonEventPublisher.publish(CampaignPublishedEvent.EVENT_NAME, event);
	}

}

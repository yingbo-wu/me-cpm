package cn.rongcapital.mc2.me.cpm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;
import cn.rongcapital.mc2.me.cpm.domain.repository.CampaignRepository;

@Service
public class CampaignFlowShutdownService {

	@Autowired
	private CampaignRepository campaignRepository;

	public void shutdown(String id, long tenantId, long userId, String userName, Integer shutdownOption) {
		Campaign campaign = campaignRepository.findOne(id);
		campaign.terminate(tenantId, userId, userName, shutdownOption);
	}

}

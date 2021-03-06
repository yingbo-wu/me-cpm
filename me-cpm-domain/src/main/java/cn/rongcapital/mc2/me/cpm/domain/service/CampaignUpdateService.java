package cn.rongcapital.mc2.me.cpm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;
import cn.rongcapital.mc2.me.cpm.domain.repository.CampaignRepository;

@Service
public class CampaignUpdateService {

	@Autowired
	private CampaignRepository campaignRepository;

	public void update(String id, long userId, String userName, String name, String description, int bizDateFlag, Long bizStartDate, Long bizEndDate) {
		Campaign campaign = campaignRepository.findOne(id);
		campaign.modifyDraftBy(userId, userName, name, description, bizDateFlag, bizStartDate, bizEndDate);
	}

}

package cn.rongcapital.mc2.me.cpm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;
import cn.rongcapital.mc2.me.cpm.domain.repository.CampaignRepository;

@Service
public class CampaignDeleteService {

	@Autowired
	private CampaignRepository campaignRepository;

	public void delete(String id, long tenantId, long userId, String userName) {
		Campaign campaign = campaignRepository.findOne(id);
		campaign.deleteBy(tenantId, userId, userName);
		campaignRepository.save(campaign);
	}

}

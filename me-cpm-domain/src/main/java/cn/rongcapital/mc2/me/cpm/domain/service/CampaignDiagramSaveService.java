package cn.rongcapital.mc2.me.cpm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteRuntimeException;
import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;
import cn.rongcapital.mc2.me.cpm.domain.repository.CampaignRepository;

@Service
public class CampaignDiagramSaveService {

	@Autowired
	private CampaignRepository campaignRepository;

	public void save(String id, long tenantId, long userId, String userName, String diagramJson) {
		Campaign campaign = campaignRepository.findOneByIdAndTenantId(id, tenantId);
		if (null == campaign) {
			throw new IgniteRuntimeException(500, "活动不存在!");
		}
		campaign.updateDiagramBy(userId, userName, diagramJson);
		campaignRepository.save(campaign);
	}

}

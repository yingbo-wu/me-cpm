package cn.rongcapital.mc2.me.cpm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;
import cn.rongcapital.mc2.me.cpm.domain.repository.CampaignRepository;

@Service
public class CampaignCheckService {

	@Autowired
	private CampaignRepository campaignRepository;

	public boolean checkName(String id, long tenantId, String name) {
		Campaign campaign = null;
		if (null == id) {
			campaign = campaignRepository.findOneByTenantIdAndNameAndIsDeletedFalse(tenantId, name);
		} else {
			campaign = campaignRepository.findOneByIdNotAndTenantIdAndNameAndIsDeletedFalse(id, tenantId, name);
		}
		return null == campaign;
	}

}

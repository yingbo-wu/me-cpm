package cn.rongcapital.mc2.me.cpm.domain.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;
import cn.rongcapital.mc2.me.cpm.domain.model.CampaignNodeCategory;
import cn.rongcapital.mc2.me.cpm.domain.model.CampaignWebNode;
import cn.rongcapital.mc2.me.cpm.domain.model.ComponentInfo;
import cn.rongcapital.mc2.me.cpm.domain.repository.CampaignRepository;
import cn.rongcapital.mc2.me.cpm.domain.repository.ComponentInfoRepository;

@Service
public class CampaignCreateService {

	@Autowired
	private CampaignRepository campaignRepository;

	@Autowired
	private ComponentInfoRepository componentInfoRepository;

	/**
	 * 标准创建
	 * @param tenantId
	 * @param userId
	 * @param userName
	 * @param name
	 * @param description
	 * @param bizDateFlag
	 * @param bizStartDate
	 * @param bizEndDate
	 * @return
	 */
	public String create(long tenantId, long userId, String userName, String name, String description, int bizDateFlag, Long bizStartDate, Long bizEndDate) {
		Campaign campaign = new Campaign(tenantId, userId, userName, name, description, bizDateFlag, bizStartDate, bizEndDate);
		campaignRepository.save(campaign);
		return campaign.getId();
	}

	/**
	 * 从CDP创建
	 * @param tenantId
	 * @param userId
	 * @param userName
	 * @param name
	 * @param description
	 * @param bizDateFlag
	 * @param bizStartDate
	 * @param bizEndDate
	 * @param dataJson
	 * @return
	 */
	public String create(long tenantId, long userId, String userName, String name, String description, int bizDateFlag, Long bizStartDate, Long bizEndDate, String dataJson) {
		ComponentInfo componentInfo = componentInfoRepository.findOneByCategory(CampaignNodeCategory.AUDIENCE.name());
		String componentId = componentInfo.getId();
		CampaignWebNode node = new CampaignWebNode(componentId, dataJson);
		Map<String, Object> map = node.toMap();
		Campaign campaign = new Campaign(tenantId, userId, userName, name, description, bizDateFlag, bizStartDate, bizEndDate, map);
		campaignRepository.save(campaign);
		return campaign.getId();
	}

}

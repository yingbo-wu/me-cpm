package cn.rongcapital.mc2.me.cpm.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;
import cn.rongcapital.mc2.me.cpm.domain.model.CampaignQueryBuilder;
import cn.rongcapital.mc2.me.cpm.domain.repository.CampaignRepository;

@Service
public class CampaignFindService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private CampaignRepository campaignRepository;

	public List<Campaign> find(long tenantId, long userId, String name, List<Integer> status, boolean isOwn, Date fromDate, Date toDate, int pageIndex, int pageSize) {
		CampaignQueryBuilder queryBuilder = new CampaignQueryBuilder(tenantId, userId, name, status, isOwn, fromDate, toDate, pageIndex, pageSize);
		Query query = queryBuilder.buildPagingQuery();
		List<Campaign> list = mongoTemplate.find(query, Campaign.class);
		return list;
	}

	public Campaign findOne(String id, long tenantId) {
		return campaignRepository.findOneByIdAndTenantId(id, tenantId);
	}

}

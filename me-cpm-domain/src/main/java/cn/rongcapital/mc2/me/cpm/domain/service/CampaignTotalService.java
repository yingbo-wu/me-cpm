package cn.rongcapital.mc2.me.cpm.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;
import cn.rongcapital.mc2.me.cpm.domain.model.CampaignQueryBuilder;

@Service
public class CampaignTotalService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public long total(long tenantId, long userId, String name, List<Integer> status, boolean isOwn, Date fromDate, Date toDate) {
		CampaignQueryBuilder queryBuilder = new CampaignQueryBuilder(tenantId, userId, name, status, isOwn, fromDate, toDate);
		Query query = queryBuilder.buildQuery();
		return mongoTemplate.count(query, Campaign.class);
	}

}

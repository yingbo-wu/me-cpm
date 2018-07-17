package cn.rongcapital.mc2.me.cpm.data.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;

@Repository
public interface CampaignDao extends MongoRepository<Campaign, String> {

}

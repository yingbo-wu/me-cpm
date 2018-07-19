package cn.rongcapital.mc2.me.cpm.data.cao;

import org.apache.ignite.springdata.repository.config.RepositoryConfig;

import cn.rongcapital.mc2.me.cpm.data.CacheName;
import cn.rongcapital.mc2.me.cpm.domain.repository.CampaignRepository;

@RepositoryConfig(cacheName = CacheName.CAMPAIGN_CACHE_NAME)
public interface CampaignCao extends CampaignRepository {

}

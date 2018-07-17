package cn.rongcapital.mc2.me.cpm.domain.repository;

import org.apache.ignite.springdata.repository.IgniteRepository;

import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;

public interface CampaignRepository extends IgniteRepository<Campaign, String> {

	Campaign findOneByIdAndTenantId(String id, long tenantId);

	Campaign findOneByTenantIdAndNameAndIsDeletedFalse(long tenantId, String name);

	Campaign findOneByIdNotAndTenantIdAndNameAndIsDeletedFalse(String id, long tenantId, String name);

}

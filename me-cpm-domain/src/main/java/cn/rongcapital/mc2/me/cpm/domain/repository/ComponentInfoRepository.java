package cn.rongcapital.mc2.me.cpm.domain.repository;

import java.util.List;

import org.apache.ignite.springdata.repository.IgniteRepository;

import cn.rongcapital.mc2.me.cpm.domain.model.ComponentInfo;

public interface ComponentInfoRepository extends IgniteRepository<ComponentInfo, String> {

	ComponentInfo findOneByCategory(String category);

	List<ComponentInfo> findByProviderId(long tenantId);

}

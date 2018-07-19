package cn.rongcapital.mc2.me.cpm.data.cao;

import org.apache.ignite.springdata.repository.config.RepositoryConfig;

import cn.rongcapital.mc2.me.cpm.data.CacheName;
import cn.rongcapital.mc2.me.cpm.domain.repository.ComponentInfoRepository;

@RepositoryConfig(cacheName = CacheName.COMPONENT_INFO_CACHE_NAME)
public interface ComponentInfoCao extends ComponentInfoRepository {

}

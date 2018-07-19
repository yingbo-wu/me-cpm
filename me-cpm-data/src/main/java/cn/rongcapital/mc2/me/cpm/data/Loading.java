package cn.rongcapital.mc2.me.cpm.data;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteCacheLoader;

@Component
public class Loading implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		IgniteCacheLoader.load(CacheName.CAMPAIGN_CACHE_NAME);
		IgniteCacheLoader.load(CacheName.COMPONENT_INFO_CACHE_NAME);
	}

}

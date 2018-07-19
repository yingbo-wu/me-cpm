package cn.rongcapital.mc2.me.cpm.app;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteServiceDeployment;
import cn.rongcapital.mc2.me.cpm.api.CampaignApi;
import cn.rongcapital.mc2.me.cpm.api.CampaignDiagramApi;
import cn.rongcapital.mc2.me.cpm.api.CampaignFlowApi;
import cn.rongcapital.mc2.me.cpm.api.ComponentApi;

@Component
public class Launch implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		IgniteServiceDeployment.deploy(CampaignApi.class);
		IgniteServiceDeployment.deploy(CampaignDiagramApi.class);
		IgniteServiceDeployment.deploy(CampaignFlowApi.class);
		IgniteServiceDeployment.deploy(ComponentApi.class);
	}

}

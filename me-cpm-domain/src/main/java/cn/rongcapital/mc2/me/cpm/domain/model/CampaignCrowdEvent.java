package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Map;
import java.util.Set;

import cn.rongcapital.mc2.me.commons.infrastructure.reactor.ReactorNettyClient;
import cn.rongcapital.mc2.me.commons.infrastructure.reactor.ReactorNettyResult;
import reactor.core.publisher.Mono;

public class CampaignCrowdEvent extends CampaignCrowd {

	@Override
	public Mono<ReactorNettyResult> checkAvailable() {
		return ReactorNettyClient.post("", "", Map.class);
	}

	@Override
	public Set<Long> extractTagIds() {
		// TODO Auto-generated method stub
		return null;
	}

}

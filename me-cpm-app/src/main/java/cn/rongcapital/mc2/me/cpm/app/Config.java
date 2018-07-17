package cn.rongcapital.mc2.me.cpm.app;

import java.util.Arrays;
import java.util.Collections;

import org.apache.ignite.IgniteSpringBean;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteNodeType;

@Configuration
public class Config {

	@Value("${me.cpm.service.ignite.name}")
	private String name;

	@Value("${me.cpm.service.ignite.addresses}")
	private String[] addresses;

	@Bean
	public IgniteSpringBean igniteSpringBean() {
		TcpDiscoverySpi spi = new TcpDiscoverySpi();
		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
		ipFinder.setAddresses(Arrays.asList(addresses));
		spi.setIpFinder(ipFinder);
		IgniteConfiguration configuration = new IgniteConfiguration();
		configuration.setDiscoverySpi(spi);
		configuration.setIgniteInstanceName(name);
		configuration.setPeerClassLoadingEnabled(true);
		configuration.setUserAttributes(Collections.singletonMap(IgniteNodeType.SERVICE_NODE.name(), true));
		IgniteSpringBean bean = new IgniteSpringBean();
		bean.setConfiguration(configuration);
		return bean;
	}

}

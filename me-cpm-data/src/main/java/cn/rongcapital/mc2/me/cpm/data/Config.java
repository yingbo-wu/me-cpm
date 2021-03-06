package cn.rongcapital.mc2.me.cpm.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.cache.configuration.FactoryBuilder;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.IgniteSpring;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteCacheLoader;
import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteNodeFilter;
import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteNodeType;
import cn.rongcapital.mc2.me.commons.infrastructure.spring.BeanContext;
import cn.rongcapital.mc2.me.cpm.data.store.CampaignStore;
import cn.rongcapital.mc2.me.cpm.data.store.ComponentInfoStore;
import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;
import cn.rongcapital.mc2.me.cpm.domain.model.ComponentInfo;

@Configuration
@ComponentScan
@PropertySource({ "classpath:data-config.properties" })
@EntityScan({ "cn.rongcapital.mc2.me.cpm.domain.model" })
@EnableIgniteRepositories({ "cn.rongcapital.mc2.me.cpm.data.cao" })
@EnableMongoRepositories({ "cn.rongcapital.mc2.me.cpm.data.dao" })
public class Config {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${me.cpm.data.ignite.name}")
	private String name;

	@Value("${me.cpm.data.ignite.addresses}")
	private String[] addresses;

	@Value("${me.cpm.mongo.servers}")
	private String servers;

	@Value("${me.cpm.mongo.database}")
	private String database;

	@Bean
	public IgniteCacheLoader igniteCacheLoader() {
		return new IgniteCacheLoader();
	}

	@Bean
	public CacheConfiguration<String, Campaign> campaignCacheConfig() {
		CacheConfiguration<String, Campaign> cacheConfig = new CacheConfiguration<String, Campaign>(CacheName.CAMPAIGN_CACHE_NAME);
		// Setting SQL schema for the cache.
		cacheConfig.setIndexedTypes(String.class, Campaign.class);
		cacheConfig.setNodeFilter(new IgniteNodeFilter(IgniteNodeType.DATA_NODE));
		cacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(CampaignStore.class));
		cacheConfig.setWriteBehindEnabled(true);
		cacheConfig.setReadThrough(true);
		cacheConfig.setWriteThrough(true);
		return cacheConfig;
	}

	@Bean
	public CacheConfiguration<String, ComponentInfo> componentInfoCacheConfig() {
		CacheConfiguration<String, ComponentInfo> cacheConfig = new CacheConfiguration<String, ComponentInfo>(CacheName.COMPONENT_INFO_CACHE_NAME);
		// Setting SQL schema for the cache.
		cacheConfig.setIndexedTypes(String.class, ComponentInfo.class);
		cacheConfig.setNodeFilter(new IgniteNodeFilter(IgniteNodeType.DATA_NODE));
		cacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(ComponentInfoStore.class));
		cacheConfig.setWriteBehindEnabled(true);
		cacheConfig.setReadThrough(true);
		cacheConfig.setWriteThrough(true);
		return cacheConfig;
	}

	@Bean
	public Ignite igniteInstance() {
		IgniteConfiguration configuration = new IgniteConfiguration();
		configuration.setUserAttributes(Collections.singletonMap(IgniteNodeType.DATA_NODE.name(), true));
		// Setting some custom name for the node.
		configuration.setIgniteInstanceName(name);
		// Enabling peer-class loading feature.
		configuration.setPeerClassLoadingEnabled(true);
		// configuration.setDataStorageConfiguration(storageConfiguration);
		configuration.setCacheConfiguration(new CacheConfiguration[] { campaignCacheConfig(), componentInfoCacheConfig() });
		Ignite ignite = null;
		try {
			ignite = IgniteSpring.start(configuration, BeanContext.build());
		} catch (IgniteCheckedException e) {
			e.printStackTrace();
		}
		return ignite;
	}

	@Bean
	public MongoClient mongoClient() {
		MongoClientOptions options = MongoClientOptions.builder().connectTimeout(60000).build();
		List<ServerAddress> addressList = new ArrayList<ServerAddress>();
		Stream.of(servers.split(",")).forEach(server -> {
			try {
				String[] strs = server.split(":");
				ServerAddress address = new ServerAddress(strs[0], Integer.parseInt(strs[1]));
				addressList.add(address);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		});
		return new MongoClient(addressList, options);
	}

	@Bean
	public MongoDbFactory mongoDbFactory() {
		return new SimpleMongoDbFactory(mongoClient(), database);
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		MongoDbFactory mongoDbFactory = mongoDbFactory();
		MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
		return mongoTemplate;
	}

}

package cn.rongcapital.mc2.me.cpm.data.store;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

import org.apache.ignite.cache.store.CacheStoreAdapter;

import cn.rongcapital.mc2.me.commons.infrastructure.spring.BeanContext;
import cn.rongcapital.mc2.me.cpm.data.dao.CampaignDao;
import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;

public class CampaignStore extends CacheStoreAdapter<Object, Campaign> {

	@Override
	public Campaign load(Object key) throws CacheLoaderException {
		CampaignDao dao = BeanContext.build().getBean(CampaignDao.class);
		return dao.findOne((String) key);
	}

	@Override
	public void write(Entry<? extends Object, ? extends Campaign> entry) throws CacheWriterException {
		CampaignDao dao = BeanContext.build().getBean(CampaignDao.class);
		Campaign entity = entry.getValue();
		entity.setId((String) entry.getKey());
		dao.save(entry.getValue());
	}

	@Override
	public void delete(Object key) throws CacheWriterException {
		CampaignDao dao = BeanContext.build().getBean(CampaignDao.class);
		dao.delete(load(key));
	}

}
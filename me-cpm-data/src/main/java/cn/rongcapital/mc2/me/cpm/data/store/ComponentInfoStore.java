package cn.rongcapital.mc2.me.cpm.data.store;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

import org.apache.ignite.cache.store.CacheStoreAdapter;

import cn.rongcapital.mc2.me.commons.infrastructure.spring.BeanContext;
import cn.rongcapital.mc2.me.cpm.data.dao.ComponentInfoDao;
import cn.rongcapital.mc2.me.cpm.domain.model.ComponentInfo;

public class ComponentInfoStore extends CacheStoreAdapter<Object, ComponentInfo> {

	@Override
	public ComponentInfo load(Object key) throws CacheLoaderException {
		ComponentInfoDao dao = BeanContext.build().getBean(ComponentInfoDao.class);
		return dao.findOne((String) key);
	}

	@Override
	public void write(Entry<? extends Object, ? extends ComponentInfo> entry) throws CacheWriterException {
		ComponentInfoDao dao = BeanContext.build().getBean(ComponentInfoDao.class);
		ComponentInfo entity = entry.getValue();
		entity.setId((String) entry.getKey());
		dao.save(entry.getValue());
	}

	@Override
	public void delete(Object key) throws CacheWriterException {
		ComponentInfoDao dao = BeanContext.build().getBean(ComponentInfoDao.class);
		dao.delete(load(key));
	}

}
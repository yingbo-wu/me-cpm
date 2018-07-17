package cn.rongcapital.mc2.me.cpm.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.cpm.domain.model.ComponentInfo;
import cn.rongcapital.mc2.me.cpm.domain.repository.ComponentInfoRepository;

@Service
public class ComponentFindService {

	@Autowired
	private ComponentInfoRepository componentInfoRepository;

	public List<ComponentInfo> find(long tenantId) {
		return componentInfoRepository.findByProviderId(tenantId);
	}

}

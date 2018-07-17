package cn.rongcapital.mc2.me.cpm.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ignite.resources.SpringResource;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.commons.api.ApiResult;
import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteService;
import cn.rongcapital.mc2.me.cpm.api.ComponentApi;
import cn.rongcapital.mc2.me.cpm.api.dto.ComponentFindIn;
import cn.rongcapital.mc2.me.cpm.api.dto.ComponentFindOut;
import cn.rongcapital.mc2.me.cpm.domain.model.ComponentInfo;
import cn.rongcapital.mc2.me.cpm.domain.service.ComponentFindService;

@Service
public class ComponentService extends IgniteService implements ComponentApi {

	private static final long serialVersionUID = -1676562137146454597L;

	@SpringResource(resourceName = "componentFindService")
	private transient ComponentFindService componentFindService;

	@Override
	public ApiResult<List<ComponentFindOut>> find(ComponentFindIn in) {
		long tenantId = in.getTenantId();
		try {
			List<ComponentInfo> list = componentFindService.find(tenantId);
			List<ComponentFindOut> outList = list.stream().map(component -> component.toFindOut(ComponentFindOut.class)).collect(Collectors.toList());
			return ApiResult.success(outList);
		} catch (Exception e) {
			return ApiResult.error(5000);
		}
	}

}

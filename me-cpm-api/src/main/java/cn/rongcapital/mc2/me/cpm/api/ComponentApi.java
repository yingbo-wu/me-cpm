package cn.rongcapital.mc2.me.cpm.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;

import org.apache.ignite.services.Service;

import cn.rongcapital.mc2.me.commons.api.ApiResult;
import cn.rongcapital.mc2.me.cpm.api.dto.ComponentFindIn;
import cn.rongcapital.mc2.me.cpm.api.dto.ComponentFindOut;

@ValidateOnExecution
public interface ComponentApi extends Service {

	/**
	 * 查询组件定义
	 */
	ApiResult<List<ComponentFindOut>> find(@NotNull(message = "4000") @Valid ComponentFindIn in);

}

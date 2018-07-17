package cn.rongcapital.mc2.me.cpm.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;

import org.apache.ignite.services.Service;

import cn.rongcapital.mc2.me.commons.api.ApiResult;
import cn.rongcapital.mc2.me.cpm.api.dto.MaterialFindIn;
import cn.rongcapital.mc2.me.cpm.api.dto.MaterialFindOut;

@ValidateOnExecution
public interface MaterialApi extends Service {

	/**
	 * 查询consul素材
	 */
	ApiResult<List<MaterialFindOut>> find(@NotNull(message = "4000") @Valid MaterialFindIn in);

}

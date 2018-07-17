package cn.rongcapital.mc2.me.cpm.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;

import org.apache.ignite.services.Service;

import cn.rongcapital.mc2.me.commons.api.ApiResult;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignDiagramSaveIn;

@ValidateOnExecution
public interface CampaignDiagramApi extends Service {

	ApiResult<Void> save(@NotNull(message = "4000") @Valid CampaignDiagramSaveIn in);

}

package cn.rongcapital.mc2.me.cpm.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;

import org.apache.ignite.services.Service;

import cn.rongcapital.mc2.me.commons.api.ApiResult;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignCreateIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignDeleteIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignFindIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignFindOneIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignFindOneOut;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignFindOut;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignNameCheckIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignPagingIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignPagingOut;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignCreateForCdpIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignTotalIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignUpdateIn;

@ValidateOnExecution
public interface CampaignApi extends Service {

	ApiResult<String> create(@NotNull(message = "4000") @Valid CampaignCreateIn in);

	ApiResult<String> create(@NotNull(message = "4000") @Valid CampaignCreateForCdpIn in);

	ApiResult<Void> delete(@NotNull(message = "4000") @Valid CampaignDeleteIn in);

	ApiResult<String> update(@NotNull(message = "4000") @Valid CampaignUpdateIn in);

	ApiResult<Long> total(@NotNull(message = "4000") @Valid CampaignTotalIn in);

	ApiResult<List<CampaignFindOut>> find(@NotNull(message = "4000") @Valid CampaignFindIn in);

	ApiResult<CampaignPagingOut> paging(@NotNull(message = "4000") @Valid CampaignPagingIn in);

	ApiResult<CampaignFindOneOut> findOne(@NotNull(message = "4000") @Valid CampaignFindOneIn in);

	ApiResult<Boolean> checkName(@NotNull(message = "4000") @Valid CampaignNameCheckIn in);

}

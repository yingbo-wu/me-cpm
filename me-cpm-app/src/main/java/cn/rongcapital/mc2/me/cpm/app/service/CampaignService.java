package cn.rongcapital.mc2.me.cpm.app.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ignite.resources.SpringResource;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.me.commons.api.ApiException;
import cn.rongcapital.mc2.me.commons.api.ApiResult;
import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteService;
import cn.rongcapital.mc2.me.cpm.api.CampaignApi;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignCreateForCdpIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignCreateIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignDeleteIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignFindIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignFindOneIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignFindOneOut;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignFindOut;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignNameCheckIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignPagingIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignPagingOut;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignTotalIn;
import cn.rongcapital.mc2.me.cpm.api.dto.CampaignUpdateIn;
import cn.rongcapital.mc2.me.cpm.domain.model.Campaign;
import cn.rongcapital.mc2.me.cpm.domain.service.CampaignCheckService;
import cn.rongcapital.mc2.me.cpm.domain.service.CampaignCreateService;
import cn.rongcapital.mc2.me.cpm.domain.service.CampaignDeleteService;
import cn.rongcapital.mc2.me.cpm.domain.service.CampaignFindService;
import cn.rongcapital.mc2.me.cpm.domain.service.CampaignTotalService;
import cn.rongcapital.mc2.me.cpm.domain.service.CampaignUpdateService;

@Service
public class CampaignService extends IgniteService implements CampaignApi {

	private static final long serialVersionUID = 7027909700746904983L;

	@SpringResource(resourceName = "campaignCreateService")
	private transient CampaignCreateService campaignCreateService;

	@SpringResource(resourceName = "campaignUpdateService")
	private transient CampaignUpdateService campaignUpdateService;

	@SpringResource(resourceName = "campaignDeleteService")
	private transient CampaignDeleteService campaignDeleteService;

	@SpringResource(resourceName = "campaignTotalService")
	private transient CampaignTotalService campaignTotalService;

	@SpringResource(resourceName = "campaignFindService")
	private transient CampaignFindService campaignFindService;

	@SpringResource(resourceName = "campaignCheckService")
	private transient CampaignCheckService campaignCheckService;

	/**
	 * 标准创建
	 */
	@Override
	public ApiResult<String> create(CampaignCreateIn in) {
		long tenantId = in.getTenantId();
		long userId = in.getUserId();
		String userName = in.getUserName();
		String name = in.getName();
		String description = in.getDescription();
		int bizDateFlag = in.getBizDateFlag();
		Long bizStartDate = in.getBizStartDate();
		Long bizEndDate = in.getBizEndDate();
		try {
			String id = campaignCreateService.create(tenantId, userId, userName, name, description, bizDateFlag, bizStartDate, bizEndDate);
			return ApiResult.success(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(5000, e.getMessage());
		}
	}

	/**
	 * 从CDP创建
	 */
	@Override
	public ApiResult<String> create(CampaignCreateForCdpIn in) {
		long tenantId = in.getTenantId();
		long userId = in.getUserId();
		String userName = in.getUserName();
		String name = in.getName();
		String description = in.getDescription();
		int bizDateFlag = in.getBizDateFlag();
		Long bizStartDate = in.getBizStartDate();
		Long bizEndDate = in.getBizEndDate();
		String dataJson = in.getDataJson();
		try {
			String id = campaignCreateService.create(tenantId, userId, userName, name, description, bizDateFlag, bizStartDate, bizEndDate, dataJson);
			return ApiResult.success(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(5000, e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApiResult<Void> delete(CampaignDeleteIn in) {
		String id = in.getId();
		long tenantId = in.getTenantId();
		long userId = in.getUserId();
		String userName = in.getUserName();
		try {
			campaignDeleteService.delete(id, tenantId, userId, userName);
			return ApiResult.success();
		} catch (ApiException e) {
			return e.result();
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(5000, e.getMessage());
		}
	}

	@Override
	public ApiResult<String> update(CampaignUpdateIn in) {
		String id = in.getId();
		long userId = in.getUserId();
		String userName = in.getUserName();
		String name = in.getName();
		String description = in.getDescription();
		int bizDateFlag = in.getBizDateFlag();
		Long bizStartDate = in.getBizStartDate();
		Long bizEndDate = in.getBizEndDate();
		try {
			campaignUpdateService.update(id, userId, userName, name, description, bizDateFlag, bizStartDate, bizEndDate);
			return ApiResult.success(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(5000, e.getMessage());
		}
	}

	@Override
	public ApiResult<Long> total(CampaignTotalIn in) {
		long tenantId = in.getTenantId();
		long userId = in.getUserId();
		String name = in.getName();
		List<Integer> status = in.getStatus();
		Date fromDate = in.getFromDate();
		Date toDate = in.getToDate();
		boolean isOwn = in.isOwn();
		try {
			long total = campaignTotalService.total(tenantId, userId, name, status, isOwn, fromDate, toDate);
			return ApiResult.success(total);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(5000, e.getMessage());
		}
	}

	@Override
	public ApiResult<List<CampaignFindOut>> find(CampaignFindIn in) {
		long tenantId = in.getTenantId();
		long userId = in.getUserId();
		String name = in.getName();
		List<Integer> status = in.getStatus();
		Date fromDate = in.getFromDate();
		Date toDate = in.getToDate();
		boolean isOwn = in.isOwn();
		int pageIndex = in.getPageIndex();
		int pageSize = in.getPageSize();
		try {
			List<Campaign> list = campaignFindService.find(tenantId, userId, name, status, isOwn, fromDate, toDate, pageIndex, pageSize);
			List<CampaignFindOut> outList = list.stream().map(campaign -> campaign.toFindOut(CampaignFindOut.class)).collect(Collectors.toList());
			return ApiResult.success(outList);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(5000, e.getMessage());
		}
	}

	@Override
	public ApiResult<CampaignFindOneOut> findOne(CampaignFindOneIn in) {
		String id = in.getId();
		long tenantId = in.getTenantId();
		try {
			Campaign campaign = campaignFindService.findOne(id, tenantId);
			CampaignFindOneOut out = campaign.toFindOneOut(CampaignFindOneOut.class);
			return ApiResult.success(out);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(5000, e.getMessage());
		}
	}

	@Override
	public ApiResult<CampaignPagingOut> paging(CampaignPagingIn in) {
		long tenantId = in.getTenantId();
		long userId = in.getUserId();
		String name = in.getName();
		List<Integer> status = in.getStatus();
		Date fromDate = in.getFromDate();
		Date toDate = in.getToDate();
		boolean isOwn = in.isOwn();
		int pageIndex = in.getPageIndex();
		int pageSize = in.getPageSize();
		try {
			List<Campaign> list = campaignFindService.find(tenantId, userId, name, status, isOwn, fromDate, toDate, pageIndex, pageSize);
			long total = campaignTotalService.total(tenantId, userId, name, status, isOwn, fromDate, toDate);
			List<CampaignFindOut> findList = list.stream().map(campaign -> campaign.toFindOut(CampaignFindOut.class)).collect(Collectors.toList());
			CampaignPagingOut out = new CampaignPagingOut(total, findList);
			return ApiResult.success(out);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(5000, e.getMessage());
		}
	}

	@Override
	public ApiResult<Boolean> checkName(CampaignNameCheckIn in) {
		String id = in.getId();
		long tenantId = in.getTenantId();
		String name = in.getName();
		try {
			boolean available = campaignCheckService.checkName(id, tenantId, name);
			return ApiResult.success(available);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(5000, e.getMessage());
		}
	}

}

package cn.rongcapital.mc2.me.cpm.domain.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import cn.rongcapital.mc2.me.commons.infrastructure.ignite.IgniteEntity;
import cn.rongcapital.mc2.me.cpm.domain.FieldName;

public class CampaignQueryBuilder {

	private Long tenantId;

	private Long userId;

	private String name;

	private List<Integer> status;

	private boolean isOwn;

	private Date fromDate;

	private Date toDate;

	private Integer pageIndex;

	private Integer pageSize;

	public CampaignQueryBuilder(long tenantId, long userId, String name, List<Integer> status, boolean isOwn, Date fromDate, Date toDate) {
		this.tenantId = tenantId;
		this.userId = userId;
		this.name = name;
		this.status = status;
		this.isOwn = isOwn;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public CampaignQueryBuilder(long tenantId, long userId, String name, List<Integer> status, boolean isOwn, Date fromDate, Date toDate, int pageIndex, int pageSize) {
		this.tenantId = tenantId;
		this.userId = userId;
		this.name = name;
		this.status = status;
		this.isOwn = isOwn;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public Query buildQuery() {
		Criteria criteria = Criteria.where(FieldName.FIELD_TENANT_ID).is(this.tenantId);
		if (StringUtils.isNotEmpty(this.name)) {
			criteria.and(FieldName.FIELD_NAME).regex(this.name.trim(), "i");
		}
		if (this.isOwn) {
			criteria.and(IgniteEntity.FIELD_CREATE_AT).is(this.userId);
		}
		if (null != this.fromDate && null != this.toDate) {
			criteria.and(IgniteEntity.FIELD_UPDATE_AT).gte(this.fromDate).and(IgniteEntity.FIELD_UPDATE_AT).lte(this.toDate);
		}
		if (CollectionUtils.isNotEmpty(this.status)) {
			criteria.and(IgniteEntity.FIELD_STATUS).in(this.status);
		}
		criteria.and(FieldName.FIELD_IS_DELETED).is(false);
		return new Query(criteria);
	}

	public Query buildPagingQuery() {
		Query query = buildQuery();
		query.limit(this.pageSize).skip((this.pageIndex - 1) * this.pageSize);
		return query;
	}

}

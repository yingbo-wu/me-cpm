package cn.rongcapital.mc2.me.cpm.domain.model;

public interface CampaignCrowdRefreshTime {

	/**
	 * 没有任何, 对应CampaignCrowdRefreshMode.UN_REFRESH
	 */
	int NOTHING = 0;

	/**
	 * 间隔时间
	 */
	int INTERVAL = 1;

	/**
	 * 特定时间
	 */
	int SPECIFIC = 2;

}

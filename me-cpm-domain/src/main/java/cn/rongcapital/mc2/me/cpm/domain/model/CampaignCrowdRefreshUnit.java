package cn.rongcapital.mc2.me.cpm.domain.model;

public interface CampaignCrowdRefreshUnit {

	/**
	 * 没有任何, 对应CampaignCrowdRefreshMode.UN_REFRESH
	 */
	int NOTHING = 0;

	/**
	 * 秒
	 */
	int SECOND = 1;

	/**
	 * 分
	 */
	int MINUTE = 2;

	/**
	 * 小时
	 */
	int HOUR = 3;

	/**
	 * 天
	 */
	int DAY = 4;

	/**
	 * 周
	 */
	int WEEK = 5;

	/**
	 * 月
	 */
	int MONTH = 6;

}

package cn.rongcapital.mc2.me.cpm.domain.model;

public interface CampaignShutdownOption {

	/**
	 *  立即终止
	 */
	int IMMEDIATELY = 0;

	/**
	 *  等待流程实例运行结束
	 */
	int AWAIT_FOR = 1;

}

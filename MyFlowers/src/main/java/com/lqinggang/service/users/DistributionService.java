package com.lqinggang.service.users;

import java.util.List;

import com.lqinggang.entity.users.Distribution;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 下午12:49:27
 */
public interface DistributionService {
	
	/**
	 * 增加一个配送人员
	 * @param distribution 配送人员
	 * @return void
	 */
	public void addDistribution(Distribution distribution);
	
	/**
	 * 删除一个配送人员
	 * @param distribution_id 配送人员ID
	 * @return void
	 */
	public void deleteDistribution(int distribution_id);
	
	/**
	 * 通过配送人员ID查询配送人员
	 * @param distribution_id
	 * @return Distribution
	 */
	public List<Distribution> queryDistribution(int distribution_id);
	
	/**
	 * 通过配送人员ID查询配送人员状态
	 * @param distribution_id
	 * @return String
	 */
	public String queryDistributionStatus(int distribution_id);
	
	/**
	 * 更新配送人员信息
	 * @param distribution 配送人员
	 * @return void
	 */
	public void updateDistribution(Distribution distribution);
}

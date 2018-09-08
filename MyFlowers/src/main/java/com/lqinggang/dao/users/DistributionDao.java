package com.lqinggang.dao.users;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.users.Distribution;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 上午11:26:11
 */
public interface DistributionDao extends BaseDao<Distribution>{
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
	public List<Distribution> queryDistributionById(int distribution_id);
	
	/**
	 * 通过配送人员ID查询配送人员状态
	 * @param distribution_id
	 * @return String
	 */
	public String queryDistributionStatusById(int distribution_id);
	
	/**
	 * 更新配送人员信息
	 * @param distribution 配送人员
	 * @return void
	 */
	public void updateDistribution(Distribution distribution);
}

package com.lqinggang.dao.impl.users;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.users.DistributionDao;
import com.lqinggang.entity.users.Distribution;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 下午12:17:40
 */
@Repository(value = "distributionDao")
public class DistributionDaoImpl extends DaoSupport<Distribution> implements DistributionDao {

	@Override
	public void addDistribution(Distribution distribution) {
		save(distribution);
	}

	@Override
	public void deleteDistribution(int distribution_id) {
		delete(distribution_id);
	}

	@Override
	public List<Distribution> queryDistributionById(int distribution_id) {
		return queryByHql(" from Distribution where person_id='" + distribution_id + "'");
	}

	@Override
	public String queryDistributionStatusById(int distribution_id) {
		List<Distribution> distribution = queryDistributionById(distribution_id);
		if (distribution != null && distribution.size() > 0) {
			for (int i = 0; i < distribution.size();) {
				return distribution.get(i).getStatus();
			}
		}
		return null;
	}

	@Override
	public void updateDistribution(Distribution distribution) {
		saveOrUpdate(distribution);

	}

}

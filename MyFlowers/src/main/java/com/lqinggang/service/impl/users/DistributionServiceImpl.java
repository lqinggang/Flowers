package com.lqinggang.service.impl.users;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lqinggang.dao.users.DistributionDao;
import com.lqinggang.entity.users.Distribution;
import com.lqinggang.service.users.DistributionService;

/**
 * @author LQingGang
 * @time 2018年3月20日 - 下午02:20:52
 */
@Transactional
@Service(value="distributionService")
public class DistributionServiceImpl implements DistributionService {

	@Autowired
	private DistributionDao distributionDao;
	// @Autowired
	// private PersonDao personDao;

	@Override
	public void addDistribution(Distribution distribution) {
		distributionDao.addDistribution(distribution);
	}

	@Override
	public void deleteDistribution(int distribution_id) {
		distributionDao.deleteDistribution(distribution_id);
	}

	@Override
	public List<Distribution> queryDistribution(int distribution_id) {
		return distributionDao.queryDistributionById(distribution_id);
	}

	@Override
	public String queryDistributionStatus(int distribution_id) {
		return distributionDao.queryDistributionStatusById(distribution_id);
	}

	@Override
	public void updateDistribution(Distribution distribution) {
//		personDao.deletePersonById(personId);
		distributionDao.updateDistribution(distribution);
	}

}

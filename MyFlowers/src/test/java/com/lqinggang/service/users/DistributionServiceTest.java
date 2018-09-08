package com.lqinggang.service.users;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lqinggang.dao.users.PersonDao;
import com.lqinggang.entity.users.Distribution;
import com.lqinggang.entity.users.Person;

/**
 * @author LQingGang
 * @time 2018年3月23日 - 下午3:16:38
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@Rollback(false)
public class DistributionServiceTest {
	@Autowired
	private DistributionService distributionService;

	@Autowired
	private UsersService usersService;
	/**
	 * 增加一个配送人员
	 * 
	 * @param distribution
	 *            配送人员
	 * @return void
	 */
	// @Test
	// public void addDistribution() {
	// Person person = new Person();
	// person.setName("Distribution");
	// person.setPassword("Distribution");
	// usersService.addPersonInfo(person);
	// Distribution distribution = new Distribution();
	// distribution.setStatus("忙");
	// distribution.setPerson_id(person);
	// distributionService.addDistribution(distribution);
	// }

	/**
	 * 通过配送人员ID查询配送人员
	 * 
	 * @param distribution_id
	 * @return Distribution
	 */
	@Test
	public void queryDistribution() {
		System.out.println(distributionService.queryDistribution(2).get(0).getStatus());
	}

	/**
	 * 通过配送人员ID查询配送人员状态
	 * 
	 * @param distribution_id
	 * @return String
	 */
	@Test
	public void queryDistributionStatus() {

		System.out.println(distributionService.queryDistributionStatus(2));

	}

	/**
	 * 更新配送人员信息
	 * 
	 * @param distribution
	 *            配送人员
	 * @return void
	 */
	@Test
	public void updateDistribution() {
		Distribution distribution = distributionService.queryDistribution(2).get(0);
		distribution.setStatus("闲");
		distributionService.updateDistribution(distribution);
	}

	/**
	 * 删除一个配送人员
	 * 
	 * @param distribution
	 *            配送人员
	 * @return void
	 */
	@Test
	public void deleteDistribution() {
		distributionService.deleteDistribution(2);

	}

}

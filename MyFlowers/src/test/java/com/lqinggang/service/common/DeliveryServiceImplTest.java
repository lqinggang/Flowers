package com.lqinggang.service.common;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lqinggang.dao.users.DistributionDao;
import com.lqinggang.entity.common.Delivery;
import com.lqinggang.entity.users.Distribution;
import com.lqinggang.entity.users.Person;
import com.lqinggang.service.orders.OrdersService;
import com.lqinggang.service.users.DistributionService;
import com.lqinggang.service.users.UsersService;

/**
 * @author LQingGang
 * @time 2018年3月24日 - 上午11:23:41
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@Rollback(false)
public class DeliveryServiceImplTest {

	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private DistributionService distributionService;

	/**
	 * @throws java.lang.Exception
	 * @return void
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 * @return void
	 */
	@After
	public void tearDown() throws Exception {
	}

	// @Test
	// public void addDelivery() {
	// Person person = usersService.queryPersonsById(2).get(0);
	// if (person == null) {
	// System.out.println("person is null");
	// }
	// Distribution distribution = new Distribution();
	// distribution.setStatus("忙");
	// distribution.setPerson_id(person);
	// distributionService.addDistribution(distribution);
	// Delivery delivery = new Delivery();
	//
	// delivery.setOrder_id(ordersService.queryOrdersByOrdersId(5).get(0));
	// delivery.setSend_add("send_add");
	// delivery.setSend_desc("send_desc");
	// delivery.setPerson_id(distribution);
	//
	// deliveryService.addDelivery(delivery);
	// }

//	@Test
//	public void queryDelivery() {
//		List<Delivery> deliveries = deliveryService.queryDelivery(5);
//		if (deliveries != null && deliveries.size() > 0) {
//			for (int i = 0; i < deliveries.size(); i++) {
//				System.out.println(deliveries.get(i).getSend_add());
//			}
//		}
//
//	}

//	@Test
//	public void updateDelivery() {
//		List<Delivery> deliveries = deliveryService.queryDelivery(5);
//		if (deliveries != null && deliveries.size() > 0) {
//			for (int i = 0; i < deliveries.size(); i++) {
//				deliveries.get(i).setSend_add("new send_add");
//				deliveryService.updateDelivery(deliveries.get(i));
//			}
//		}
//	}

	@Test
	public void deleteDelivery() {
		deliveryService.deleteDelivery("5");
	}

}

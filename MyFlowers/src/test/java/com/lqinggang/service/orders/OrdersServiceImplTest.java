package com.lqinggang.service.orders;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.flowers.FlowersCategory;
import com.lqinggang.entity.orders.Orders;
import com.lqinggang.entity.orders.OrdersCancel;
import com.lqinggang.entity.orders.Purchase;
import com.lqinggang.entity.users.Person;
import com.lqinggang.service.flowers.FlowersService;
import com.lqinggang.service.users.UsersService;

/**
 * @author LQingGang
 * @time 2018年3月24日 - 上午9:23:43
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@Rollback(false)
public class OrdersServiceImplTest {

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private FlowersService flowersService;

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

	/**
	 * 新增订单
	 * 
	 * @param orders
	 * @return void
	 */
//	@Test
//	public void addOrders() {
//		Orders order = new Orders();
//		order.setAmount(new Integer(100));
//		Date day = new Date();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		order.setDate(Timestamp.valueOf(df.format(day)));
//		order.setOrder_id("5");
//
//		Person person = new Person();
//		// person.setName("addOrders");
//		// person.setPassword("1");
//		// usersService.addPersonInfo(person);
//
//		List<Person> persons = usersService.queryPersonsById(2);
//		if (persons != null && persons.size() > 0) {
//			person = persons.get(0);
//		} else {
//			System.out.println("person is null");
//		}
//
//		order.setPerson_id(person);
//		FlowersCategory category = new FlowersCategory();
//		category.setName("category2");
//
//		Flowers flower = new Flowers();
//		flower.setContent("Content2");
//		flower.setDescription("Descriptioin2");
//		flower.setImage("images2");
//		flower.setName("name2");
//		flower.setPrice(new Float(100));
//		flower.setQuantity(new Integer(999));
//		flower.setCategory_id(category);
//		flowersService.addFlowersCategory(category);
//		flowersService.addFlowers(flower);
//		order.setFlower_id(flower);
//
//		ordersService.addOrders(order);
//	}

	/**
	 * 设置订单状态
	 * 
	 * @param ordersStatus
	 * @return void
	 */
//	 @Test
//	 public void setOrdersStatus() {
//	 OrdersStatus ordersStatus = new OrdersStatus();
//	 Date day = new Date();
//	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	 ordersStatus.setCancel_date(Timestamp.valueOf(df.format(day)));
//	 ordersStatus.setCancel_renson("renson");
//	 ordersStatus.setCancel_status(1);
//	 ordersStatus.setOrder_status_id(ordersService.queryOrdersByOrdersId(4).get(0));
//	 ordersService.setOrdersStatus(ordersStatus);
//	 }

	/**
	 * 通过订单编号查询订单
	 * 
	 * @param orders_id
	 */
	// @Test
	// public void queryOrders() {
	// List<Orders> orders = ordersService.queryOrdersByOrdersId(1);
	// if (orders != null && orders.size() > 0) {
	// System.out.println(orders.get(0).getDate());
	// }
	// }

	/**
	 * 通过订单编号查询订单状态
	 * 
	 * @param orders_id
	 * @return OrdersStatus
	 */
	// @Test
	// public void queryOrdersStatusByOrdersId() {
	// List<OrdersStatus> ordersStatus =
	// ordersService.queryOrdersStatusByOrdersId(1);
	// if (ordersStatus != null && ordersStatus.size() > 0) {
	// System.out.println(ordersStatus.get(0).getCancel_renson());
	// }
	// }

	/**
	 * 添加订单评价
	 * 
	 * @param purchaseType
	 * @return void
	 */
//	@Test
//	public void addPurchaseType() {
//		PurchaseType purchaseType = new PurchaseType();
//		purchaseType.setCommodity(5);
//		purchaseType.setService(5);
//		purchaseType.setLogistics(5);
//		purchaseType.setPurchase_content("不错");
//		List<Orders> orders = ordersService.queryOrdersByOrdersId(4);
//		purchaseType.setOrder_id(orders.get(0));
//		ordersService.addPurchaseType(purchaseType);
//	}

	/**
	 * 通过订单编号查询订单评价
	 * 
	 * @param orders_id
	 * @return PurchaseType
	 */
	// @Test
	// public void queryPurchaseTypeByOrdersId() {
	// List<PurchaseType> purchaseTypes =
	// ordersService.queryPurchaseTypeByOrdersId(1);
	// if (purchaseTypes != null && purchaseTypes.size() > 0) {
	// System.out.println(purchaseTypes.get(0).getPurchase_content());
	// }
	// }

	/**
	 * 列出用户的所有订单
	 * 
	 * @param users_id
	 */
	// @Test
	// public void listUsersOrders() {
	// List<Orders> orders = ordersService.listUsersOrders(3);
	// if (orders != null && orders.size() > 0) {
	// for (int i = 0; i < orders.size(); i++) {
	// System.out.println(orders.get(i).getPerson_id().getPerson_id() + ":" +
	// orders.get(i).getDate());
	// }
	// }
	// }

	/**
	 * 更新订单
	 * 
	 * @param orders
	 * @return void
	 */
	// @Test
	// public void updateOrdes() {
	// List<Orders> orders = ordersService.queryOrdersByOrdersId(1);
	// if (orders != null && orders.size() > 0) {
	// for (int i = 0; i < orders.size(); i++) {
	// orders.get(i).setAmount(1000);
	// ordersService.updateOrdes(orders.get(i));
	// }
	// }else {
	// System.out.println("orders is null ");
	// }
	// }

	/**
	 * 更新订单评价
	 * 
	 * @param purchaseType
	 * @return void
	 */
	// @Test
	// public void updatePurchaseType() {
	// List<PurchaseType> purchaseTypes =
	// ordersService.queryPurchaseTypeByOrdersId(1);
	// if (purchaseTypes!=null && purchaseTypes.size()>0) {
	// for (int i = 0; i < purchaseTypes.size(); i++) {
	// purchaseTypes.get(i).setPurchase_content("New Content");
	// purchaseTypes.get(i).setService(4);
	// ordersService.updatePurchaseType(purchaseTypes.get(i));
	// }
	// }
	// }

	/**
	 * 通过订单编号删除订单
	 * 
	 * @param orders_id
	 * @return void
	 */
	@Test
	public void deleteOrders() {
		ordersService.deleteOrders("4");
	}

}

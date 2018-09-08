package com.lqinggang.test.data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lqinggang.common.util.GenerateOrderIDUtil;
import com.lqinggang.common.util.MD5Util;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.orders.Orders;
import com.lqinggang.entity.orders.OrdersCancel;
import com.lqinggang.entity.orders.Purchase;
import com.lqinggang.entity.users.Admin;
import com.lqinggang.entity.users.Cart;
import com.lqinggang.entity.users.Contact;
import com.lqinggang.entity.users.Member;
import com.lqinggang.entity.users.Person;
import com.lqinggang.entity.users.Users;
import com.lqinggang.service.flowers.FlowersService;
import com.lqinggang.service.orders.OrdersService;
import com.lqinggang.service.users.AdminService;
import com.lqinggang.service.users.UsersService;

/**
 * @author LQingGang
 * @time 2018年4月19日 - 上午9:29:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@Rollback(false)
public class TestData {

	@Autowired
	private UsersService usersService;
	@Autowired
	private FlowersService flowersService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private OrdersService ordersService;

	/**
	 * 生成用户信息
	 * 
	 * @return void
	 */
	@Test
	public void AddUsers() {

		for (int i = 0; i < 1000; i++) {
			Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Person person = new Person();
			person.setName("test" + i);
			person.setPassword(MD5Util.toMD5("password"));

			Users users = new Users();
			users.setAge(Integer.valueOf((int) (1 + Math.random() * 99)));
			users.setBirtyday(new java.sql.Date(day.getTime()));
			users.setGender(((int) (Math.random() * 2)) % 2 == 0 ? "男" : "女");
			users.setUser_id(person);

			// 30天内注册
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, (int) (-Math.random() * 30));
			Date monday = calendar.getTime();
			users.setRegister_date(Timestamp.valueOf(df.format(monday)));

			users.setDescription("这是用户test" + i + "的描述信息");

			Contact contact = new Contact();
			contact.setAddress("110000,110100,110101,这是用户test" + i + "的地址");
			contact.setContact_id(person);
			contact.setEmail("test" + i + "@email.com");
			contact.setTelephone("1111111" + String.format("%04d", i));

			Member member = new Member();
			member.setDiscount(Float.valueOf(1));
			member.setExperience(0);
			member.setRank(1);
			member.setPerson_id(person);

			usersService.addPersonInfo(person);
			usersService.createUsers(users);
			usersService.addContactInfo(contact);
			usersService.addMember(member);
			if (((int) (Math.random() * 10)) % 5 == 0) {
				int num = (int) (Math.random() * 6);
				for (int j = 0; j < num; j++) {
					Cart cart = new Cart();
					cart.setAmount((int) (1 + Math.random() * 5));
					cart.setPerson_id(person);
					cart.setDate(new java.sql.Date(day.getTime()));
					cart.setFlower_id(flowersService.queryFlowers((int) (1 + Math.random() * 52)).get(0));
					usersService.addCart(cart);
				}
			}

		}

	}

	/**
	 * 生成管理员信息
	 * 
	 * @return void
	 */
	@Test
	public void addAdmin() {
		for (int j = 0; j < 4; j++) {
			Person person = new Person();
			person.setName("admin" + j);
			person.setPassword(MD5Util.toMD5("password"));

			Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Admin admin = new Admin();
			admin.setAdmin_id(person);
			admin.setLast_login(Timestamp.valueOf(df.format(day)));

			usersService.addPersonInfo(person);
			adminService.addAdmin(admin);
		}

	}

	/**
	 * 生成鲜花信息
	 * 
	 * @return void
	 */
	@Test
	public void addFlower() {
		for (int i = 0; i < 500; i++) {
			int flowerId = (int) (1 + Math.random() * 52);
			List<Flowers> flowersList = flowersService.queryFlowers(flowerId);

			Flowers flowers = flowersList.get(0);
			Flowers flowers2 = new Flowers();

			flowers2.setAmount(flowers.getAmount());
			flowers2.setCategory_id(flowers.getCategory_id());
			flowers2.setColor(flowers.getColor());
			flowers2.setContent(flowers.getContent());
			flowers2.setDescription(flowers.getDescription());
			flowers2.setImage(flowers.getImage());
			flowers2.setKeyword(flowers.getKeyword());
			flowers2.setName(flowers.getName());
			flowers2.setOrigin(flowers.getOrigin());
			flowers2.setPrice(flowers.getPrice());
			flowers2.setQuantity(flowers.getQuantity());
			flowers2.setContent_info(flowers.getContent_info());

			flowersService.addFlowers(flowers2);

		}

	}

	/**
	 * 生成订单信息
	 * 
	 * @return void
	 */
	@Test
	public void addOrders() {
		for (int i = 0; i < 1000; i++) {

			Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			int flowerid = (int) (1 + Math.random() * 52);
			int count = (int) usersService.getUsersCount();
			List<Integer> personList = new ArrayList<Integer>();
			personList.add(1);
			personList.add(2);
			personList.add(3);
			personList.add(4);
			for (int j = 0; j < count - 4; j++) {
				personList.add(11 + i);
			}
			int orderstatusid = (int) (Math.random() * 5);
			if (orderstatusid == 0) {
				orderstatusid = (int) (Math.random() * 5);
			}
			if (orderstatusid == 1) {
				orderstatusid = (int) (Math.random() * 5);
			}
			Orders order = new Orders();
			order.setAmount((int) (1 + Math.random() * 6));
			order.setContact("11" + String.format("%09d", (int) (Math.random() * 1000000000)));

			// 30天内订单
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, (int) (-Math.random() * 30));
			java.util.Date monday = calendar.getTime();

			order.setDate(Timestamp.valueOf(df.format(monday)));
			order.setFlower_id(flowersService.queryFlowers(flowerid).get(0));
			String orderid = GenerateOrderIDUtil.getOrderId();
			List<Orders> orders = new ArrayList<Orders>();

			while (orders != null && orders.size() > 0) {
				orderid = GenerateOrderIDUtil.getOrderId();
				orders = ordersService.queryOrdersByOrdersId(orderid);
			}
			order.setOrder_id(orderid);
			order.setAddress("这是订单" + orderid + "收件地址");
			order.setNote("这是订单" + orderid + "的备注信息");

			int personid = personList.get((int) (Math.random() * count));
			order.setPerson_id(usersService.queryUsersById(personid).get(0).getUser_id());
			order.setPrice(order.getAmount() * order.getFlower_id().getPrice());
			order.setRecipient("订单" + orderid + "的收件人");
			order.setStatus_type_id(ordersService.queryOrderStatusTypes(orderstatusid).get(0));
			ordersService.addOrders(order);

			if (order.getStatus_type_id().getStatus_type_id() == 0) { // 已取消
				OrdersCancel ordersCancel = new OrdersCancel();
				ordersCancel.setOrder_status_id(order);
				// ordersCancel.setStatus(true);
				ordersCancel.setDate(Timestamp.valueOf(df.format(day)));
				ordersCancel.setContent("订单" + order.getStatus_type_id().getStatus_type_id() + "取消原因");
				ordersService.setOrdersStatus(ordersCancel);
			}
			if (order.getStatus_type_id().getStatus_type_id() == 4) {// 已评价
				Purchase purchase = new Purchase();
				purchase.setCommodity(Integer.valueOf((int) (1 + Math.random() * 4)));
				purchase.setLogistics(Integer.valueOf((int) (1 + Math.random() * 4)));
				purchase.setService(Integer.valueOf((int) (1 + Math.random() * 4)));

				calendar.add(Calendar.DATE, -4);
				if (monday.before(calendar.getTime())) {
					calendar.add(Calendar.DATE, (int) (-Math.random() * 3));
					purchase.setDate(Timestamp.valueOf(df.format(calendar.getTime())));
				} else {
					purchase.setDate(order.getDate());
				}
				int purch = (purchase.getCommodity() + purchase.getLogistics() + purchase.getService()) / 3;

				String purchImg = "<img alt=\"评价图片\" src=\"" + order.getFlower_id().getImage()
						+ "\" style=\"width:60px;height:60px;\">";
				String purchases_content = purch < 2 ? "不好" : "很好看！";

				if (((int) (Math.random() * 4)) % 4 == 0) {
					purchases_content = "<span style=\"margin-right:10px;\">" + purchases_content + "</span>"
							+ purchImg;
				}

				purchase.setPurchase_content(purchases_content);
				purchase.setOrder_id(order);
				ordersService.addPurchase(purchase);
			}
		}
	}

}

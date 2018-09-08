package com.lqinggang.service.flowers;

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

import com.lqinggang.common.util.MD5Util;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.flowers.FlowersCategory;
import com.lqinggang.entity.flowers.Sales;
import com.lqinggang.entity.users.Person;
import com.lqinggang.service.users.UsersService;

/**
 * @author L()gGang
 * @time 2018年3月23日 - 下午4:09:52
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@Rollback(false)
public class FlowersServiceImplTest {

	@Autowired
	private FlowersService flowersService;
	@Autowired
	private UsersService usersService;

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
	// public void addFlowersCategory() {
	// FlowersCategory category = new FlowersCategory();
	// category.setCategory_id(10);
	// category.setName("Test Category");
	// flowersService.addFlowersCategory(category);
	// }
	//
	// @Test
	// public void countFlowersCategory(){
	// System.out.println(flowersService.countFlowersCategory());
	// }
	// @Test
	// public void listFlowersCategory() {
	// List<FlowersCategory> categories =
	// flowersService.listAllFlowersCategories();
	// if (categories != null && categories.size() > 0) {
	// for (int i = 0; i < categories.size(); i++) {
	// System.out.println(
	// "ID:" + categories.get(i).getCategory_id() + "Category:" +
	// categories.get(i).getName());
	// }
	// }
	// }

	// @Test
	// public void addFlowers() {
	// FlowersCategory category = new FlowersCategory();
	// List<FlowersCategory> categories =
	// flowersService.listAllFlowersCategories();
	// if (categories != null && categories.size() > 0) {
	// category = categories.get(0);
	// }
	// Flowers flowers = new Flowers();
	// flowers.setName("月季2");
	// flowers.setImage("static/pages/images/flowers/Rose2.jpg");
	// flowers.setDescription("月季2");
	// flowers.setContent("这是月季2<br/>这是月季2<br/>这是月季2<br/>这是月季2<br/>这是月季2<br/>");
	// flowers.setPrice(new Float(99));
	// flowers.setQuantity(199);
	// flowers.setCategory_id(category);
	// flowersService.addFlowers(flowers);
	// }

	// @Test
	// public void queryFlowersCategoryByFlowersId() {
	// List<FlowersCategory> flowersCategory =
	// flowersService.queryFlowersCategoryByFlowersId(1);
	// System.out.println(flowersCategory.get(0).getName());
	// }

	// @Test
	// public void updateFlowersCategory() {
	// FlowersCategory flowersCategory =
	// flowersService.listAllFlowersCategories().get(0);
	// flowersCategory.setName("new Name");
	// flowersService.updateFlowersCategory(flowersCategory);
	// }

	// @Test
	// public void queryFlowers() {
	// List<Flowers> flowers = flowersService.queryFlowers(1);
	// if (flowers != null && flowers.size() > 0) {
	// for (int i = 0; i < flowers.size(); i++) {
	// System.out.println(flowers.get(i).getName());
	// // Flowers
	// }
	// }
	// }

	// @Test
	// public void addSales() {
	// Sales sales = new Sales();
	// Person person = new Person();
	// person.setName("addSales");
	// person.setPassword(MD5.toMD5("password"));
	// usersService.addPersonInfo(person);
	// List<Flowers> flowers = flowersService.queryFlowers(1);
	// if (flowers != null && flowers.size() > 0) {
	// sales.setSales_id(flowers.get(0));
	// }
	// sales.setAmount(10);
	// Date day = new Date();
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// sales.setDate(Timestamp.valueOf(df.format(day)));
	// sales.setPerson_id(person);
	// flowersService.addSales(sales);
	// }

	// @Test
	// public void querySalesByFlowersId() {
	// System.out.println(flowersService.querySalesByFlowersId(1).get(0).getAmount());
	// }

	// @Test
	// public void querySalesByUsersId() {
	// List<Sales> sales = flowersService.querySalesByUsersId(5);
	// if (sales != null && sales.size() > 0) {
	// System.out.println(sales.get(0).getAmount());
	// }
	// }

	// @Test
	// public void updateFlowers() {
	// List<Flowers> flowers = flowersService.queryFlowers(1);
	// if (flowers != null && flowers.size() > 0) {
	// flowers.get(0).setContent("This is a new Content");
	// flowersService.updateFlowers(flowers.get(0));
	// }
	// }

	// @Test
	// public void deleteFlowers() {
	// flowersService.deleteFlowersByFlowerId(1);
	// }

	// @Test
	// public void deleteFlowersCategory() {
	// flowersService.deleteFlowersCategoryByCatagoryId(10);
	// }

	@Test
	public void findFlowersByFlowersCategoryName() {
		List<Flowers> flowers = flowersService.findFlowersByFlowersCategoryName("其他鲜花");
		if (flowers != null && flowers.size() > 0) {
			System.out.println(flowers.get(0).getImage());
		} else {
			System.out.println("null");
		}
	}

}

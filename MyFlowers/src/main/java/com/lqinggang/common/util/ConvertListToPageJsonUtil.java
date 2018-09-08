package com.lqinggang.common.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lqinggang.controller.admin.AdminOrdersController;
import com.lqinggang.entity.common.AdminLogs;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.orders.Orders;
import com.lqinggang.entity.orders.Purchase;
import com.lqinggang.entity.users.Contact;
import com.lqinggang.entity.users.Member;
import com.lqinggang.entity.users.Person;
import com.lqinggang.entity.users.Users;

/**
 * 信息转JSONArray工具
 * 
 * @author LQingGang
 * @time 2018年4月16日 - 下午4:50:38
 */
public class ConvertListToPageJsonUtil {

	private static Logger logger = LoggerFactory.getLogger(AdminOrdersController.class);

	/**
	 * 将用户信息转化成JSONArray
	 * 
	 * @param usersList
	 * @return String
	 */
	public static JSONArray usersInfoToJsonArray(List<Users> usersList, List<Contact> usersContactsList,
			List<Member> membersList) {
		JSONArray jsonArray = new JSONArray();
		if (usersList != null && usersList.size() > 0 && usersContactsList != null && usersContactsList.size() > 0
				&& membersList != null && membersList.size() > 0) {
			for (Users user : usersList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", user.getUser_id().getPerson_id());
				jsonObject.put("name", user.getUser_id().getName());
				jsonObject.put("sex", user.getGender());
				jsonObject.put("age", user.getAge());
				jsonObject.put("birtyday", user.getBirtyday().toString());
				jsonObject.put("register_date", user.getRegister_date().toString());
				for (Contact contact : usersContactsList) {
					if (contact.getContact_id().getPerson_id() == user.getUser_id().getPerson_id()) { // 查找用户对应的联系方式
						jsonObject.put("email", contact.getEmail());
						jsonObject.put("telephone", contact.getTelephone());
						jsonObject.put("address", contact.getAddress());
					}
				}
				for (Member member : membersList) {
					if (member.getPerson_id().getPerson_id() == user.getUser_id().getPerson_id()) { // 查找用户对应的会员信息
						jsonObject.put("discount",
								(member.getDiscount() * 10 == 10 ? "不打折" : member.getDiscount() * 10));
						jsonObject.put("experience", member.getExperience());
						jsonObject.put("rank", member.getRank());
					}
				}

				jsonArray.add(jsonObject);
			}

		}
		logger.info("用户信转换成JSONArray");
		return jsonArray;
	}

	/**
	 * 将用户密码信息转化成JSONArray
	 * 
	 * @param persons
	 * @return JSONArray
	 */
	public static JSONArray usersPasswordToJsonArray(List<Person> persons) {
		JSONArray jsonArray = new JSONArray();
		if (persons != null && persons.size() > 0) {

			for (int i = 0; i < 50; i++) {
				for (Person person : persons) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("name", person.getName());
					jsonObject.put("password", person.getPassword());
					jsonArray.add(jsonObject);
				}

			}

		}
		logger.info("用户密码信息换成JSONArray");
		return jsonArray;
	}

	/**
	 * 鲜花信息转换成JSONArray
	 * 
	 * @param flowersList
	 * @return JSONArray
	 */
	public static JSONArray flowersInfoToJsonArray(List<Flowers> flowersList) {
		JSONArray jsonArray = new JSONArray();
		if (flowersList != null && flowersList.size() > 0) {

			for (Flowers flowers : flowersList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("flowerid", flowers.getFlower_id());
				jsonObject.put("name", flowers.getName());
				jsonObject.put("amount", flowers.getAmount());
				jsonObject.put("category", flowers.getCategory_id().getName());
				jsonObject.put("color", flowers.getColor());
				jsonObject.put("content", flowers.getContent());
				jsonObject.put("description", flowers.getDescription());
				jsonObject.put("image", flowers.getImage());
				jsonObject.put("keyword", flowers.getKeyword());
				jsonObject.put("origin", flowers.getOrigin());
				jsonObject.put("price", flowers.getPrice());
				jsonObject.put("quantity", flowers.getQuantity());

				String content_info = new String();
				if (flowers.getContent_info() != null) { // table以代码形式展示
					content_info = flowers.getContent_info().replace("<", "&lt;");
					content_info = content_info.replace(">", "&gt;");
				}

				jsonObject.put("content_info", content_info);
				jsonArray.add(jsonObject);
			}
		}
		logger.info("鲜花信息换成JSONArray");
		return jsonArray;
	}

	/**
	 * 将订单信息转化成JSONArray
	 * 
	 * @param ordersList
	 * @return JSONArray
	 */
	public static JSONArray ordersInfoToJsonArray(List<Orders> ordersList) {

		JSONArray jsonArray = new JSONArray();
		if (ordersList != null && ordersList.size() > 0) {
			for (Orders order : ordersList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("orderid", order.getOrder_id());
				jsonObject.put("recipient", order.getRecipient());
				jsonObject.put("contact", order.getContact());
				jsonObject.put("address", order.getAddress());
				jsonObject.put("flowerid", order.getFlower_id().getFlower_id());
				jsonObject.put("flowername", order.getFlower_id().getName());
				jsonObject.put("amount", order.getAmount());
				jsonObject.put("date", order.getDate() == null ? "" : order.getDate().toString());
				jsonObject.put("note", order.getNote());
				if (order.getPerson_id() != null) {
					jsonObject.put("personname", order.getPerson_id().getName());
				} else {
					jsonObject.put("personname", "");
				}

				jsonObject.put("price", order.getPrice());
				jsonObject.put("status", order.getStatus_type_id().getStatus_name());
				jsonArray.add(jsonObject);
			}

		}
		logger.info("订单信息换成JSONArray");
		return jsonArray;
	}

	/**
	 * 将鲜花评价转化成JSONArray
	 * 
	 * @param ordersList
	 * @return JSONArray
	 */
	public static JSONArray purchaseInfoToJsonArray(List<Purchase> purchaseList) {

		JSONArray jsonArray = new JSONArray();
		if (purchaseList != null && purchaseList.size() > 0) {
			for (Purchase purchase : purchaseList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("orderid", purchase.getOrder_id().getOrder_id());
				jsonObject.put("flowerid", purchase.getOrder_id().getFlower_id().getFlower_id());
				jsonObject.put("flowername", purchase.getOrder_id().getFlower_id().getName());
				jsonObject.put("personid", purchase.getOrder_id().getPerson_id().getPerson_id());
				jsonObject.put("personname", purchase.getOrder_id().getPerson_id().getName());
				jsonObject.put("commodity", purchase.getCommodity());
				jsonObject.put("logistics", purchase.getLogistics());
				jsonObject.put("service", purchase.getService());
				jsonObject.put("content", purchase.getPurchase_content());
				jsonArray.add(jsonObject);
			}

		}
		logger.info("鲜花评价信息换成JSONArray");
		return jsonArray;
	}

	/**
	 * 将管理员日志转化成JSONArray
	 * 
	 * @param ordersList
	 * @return JSONArray
	 */
	public static JSONArray adminLogsInfoToJsonArray(List<AdminLogs> adminLogsList) {

		JSONArray jsonArray = new JSONArray();
		if (adminLogsList != null && adminLogsList.size() > 0) {
			for (AdminLogs adminLogs : adminLogsList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", adminLogs.getLogs_id());
				jsonObject.put("name", adminLogs.getPerson_id().getName());
				jsonObject.put("date", adminLogs.getDate().toString());
				jsonObject.put("ip", adminLogs.getIp());
				jsonObject.put("content", adminLogs.getContent());
				jsonArray.add(jsonObject);
			}

		}
		logger.info("管理员日志信息换成JSONArray");
		return jsonArray;
	}

}

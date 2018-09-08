package com.lqinggang.controller.users;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lqinggang.common.controller.BaseController;
import com.lqinggang.common.util.MD5Util;
import com.lqinggang.entity.users.Contact;
import com.lqinggang.entity.users.Person;
import com.lqinggang.entity.users.Users;
import com.lqinggang.service.users.UsersService;

/**
 * 用户资料设置
 * 
 * @author LQingGang
 * @time 2018年4月4日 - 上午10:54:43
 */
@Controller
@RequestMapping(value = "/users")
public class UsersDataSettingController extends BaseController {
	// private static String indexPage = getViewPath("/pages/jsp/index");
	private static String dataSettingPage = getViewPath("/pages/jsp/users/datasetting");
	private static String settingPage = getViewPath("/pages/jsp/users/setting");
	private static String loginPage = getViewPath("/pages/jsp/users/login");
	private static String leftPage = "common/left.jsp";

	private static Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private UsersService usersService;

	/**
	 * 获取资料设置页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/datasettingpage")
	public String getdateSettingPage(HttpServletRequest request) {
		request.setAttribute("left", leftPage);
		logger.info(request.getSession().getAttribute("username") + "- datasetting");
		return dataSettingPage;
	}

	/**
	 * 获取资料设置表单页
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/settingpage")
	public String settingPage(HttpServletRequest request, HttpSession session) {
		request.setAttribute("left", leftPage);
		logger.info(request.getSession().getAttribute("username") + "- datasetting");

		if (session.getAttribute("username") != null) {
			String userName = session.getAttribute("username").toString();
			List<Person> persons = usersService.queryPersonsByName(userName);
			List<Users> users = usersService.queryUsersByName(userName);
			List<Contact> contacts = usersService.queryUsersContactByName(userName);
			if ((persons != null && persons.size() > 0) && (users != null && users.size() > 0)
					&& (contacts != null && contacts.size() > 0)) {
				request.setAttribute("username", persons.get(0).getName());
				request.setAttribute("age", users.get(0).getAge());
				request.setAttribute("birtyday", users.get(0).getBirtyday());
				request.setAttribute("email", contacts.get(0).getEmail());
				request.setAttribute("telephone", contacts.get(0).getTelephone());
				String address[] = contacts.get(0).getAddress().split(",", 4);
				request.setAttribute("prov", address[0]);
				request.setAttribute("city", address[1]);
				request.setAttribute("area", address[2]);
				request.setAttribute("address", address[3]);
			}
			return settingPage;
		} else {
			// 用户未登录
			return loginPage;
		}
	}

	/**
	 * 基本资料设置
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/basicsetting")
	public String basicsetting(HttpServletRequest request, HttpSession session) {
		request.setAttribute("left", leftPage);
		// 用户名
		if (session.getAttribute("username") == null) {
			return loginPage;
		}
		String name = session.getAttribute("username").toString();
		// 性别
		String gender = request.getParameter("gender");
		// 年龄
		String usersage = request.getParameter("age");
		int age = 0;
		try {
			age = Integer.parseInt(usersage);
		} catch (Exception e) {
			request.setAttribute("msg", "年龄错误");
			return dataSettingPage;
		}
		// 生日
		String birtyday = request.getParameter("birtyday");
		// Email
		String email = request.getParameter("email");
		// 备注
		String description = request.getParameter("description");

		List<Users> users = usersService.queryUsersByName(name);
		List<Contact> contact = usersService.queryUsersContactByName(name);

		if ((users != null && users.size() > 0) && (contact != null && contact.size() > 0)) {
			users.get(0).setAge(age);
			if (birtyday != null) {
				Date birDate = null;
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					birDate = format.parse(birtyday);
					users.get(0).setBirtyday(new java.sql.Date(birDate.getTime()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			users.get(0).setDescription(description);
			users.get(0).setGender(gender);
			contact.get(0).setEmail(email);

			usersService.updateUsers(users.get(0));
			usersService.updateUsersContact(contact.get(0));
			request.setAttribute("msg", "修改成功！");

		} else {
			return loginPage;
		}
		return dataSettingPage;
	}

	/**
	 * 密码修改
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/passwordsetting")
	public String passwordsetting(HttpServletRequest request, HttpSession session) {
		// 用户名
		if (session.getAttribute("username") == null) {
			return loginPage;
		}
		Object password = request.getParameter("password");
		Object password2 = request.getParameter("password2");
		Object password3 = request.getParameter("password3");
		if (password == null || password2 == null || password3 == null) {
			request.setAttribute("msg", "密码不能为空！");
			return dataSettingPage;
		}

		String name = session.getAttribute("username").toString();
		boolean exists = usersService.findPersonIsExists(name, password.toString());
		if (!exists) {
			request.setAttribute("msg", "密码不正确");
			return dataSettingPage;
		}
		if (!password2.equals(password3)) {
			request.setAttribute("msg", "密码不一致");
			return dataSettingPage;
		}

		List<Person> persons = usersService.queryPersonsByName(name);

		if (persons != null && persons.size() > 0) {
			if (persons.get(0).getPassword().equals(MD5Util.toMD5(password.toString()))) {
				usersService.updateUsersPassword(persons.get(0));
				request.setAttribute("msg", "密码修改成功！");
			} else {
				request.setAttribute("msg", "原密码不正确");
			}

		} else {
			return loginPage;
		}
		return dataSettingPage;
	}

	/**
	 * 联系方式修改
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/contactsetting")
	public String contactsetting(HttpServletRequest request, HttpSession session) {
		request.setAttribute("left", leftPage);
		// 用户名
		if (session.getAttribute("username") == null) {
			return loginPage;
		}
		String name = session.getAttribute("username").toString();

		String telephone = request.getParameter("telephone");
		// 地址
		String province = request.getParameter("provid"); // 省
		String city = request.getParameter("cityid");// 市
		String area = request.getParameter("areaid");// 县
		String address2 = request.getParameter("address2"); // 详细地址
		if (address2 == null || "".equals(address2.trim())) {
			request.setAttribute("msg", "详细地址不能为空！");
			return dataSettingPage;
		}
		request.setAttribute("address2", address2);

		// 数据库中地址字段为：省,市,县,详细地址（省，市，县为代号）
		String address = province + "," + city + "," + area + "," + address2;

		List<Contact> contacts = usersService.queryUsersContactByName(name);

		if (contacts != null && contacts.size() > 0) {
			contacts.get(0).setTelephone(telephone);
			contacts.get(0).setAddress(address);
			usersService.updateUsersContact(contacts.get(0));
			request.setAttribute("msg", "修改成功！");
		}

		return dataSettingPage;
	}

}

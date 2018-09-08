package com.lqinggang.controller.users;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lqinggang.common.controller.BaseController;
import com.lqinggang.common.util.DataVerificationUtil;
import com.lqinggang.common.util.MD5Util;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.users.Contact;
import com.lqinggang.entity.users.Member;
import com.lqinggang.entity.users.Person;
import com.lqinggang.entity.users.Users;
import com.lqinggang.service.flowers.FlowersService;
import com.lqinggang.service.users.UsersService;

/**
 * 用户相关
 * 
 * @author LQingGang
 * @time 2018年3月28日 - 上午9:52:05
 */
@Controller
@RequestMapping(value = "/users")
public class UsersController extends BaseController {

	private static String loginPage = getViewPath("/pages/jsp/users/login");
	private static String basicRegisteredPageString = getViewPath("/pages/jsp/users/basicregistered");
	private static String indexPage = getViewPath("/pages/jsp/index");
	private static String registeredPage = getViewPath("/pages/jsp/users/registered");
	private static String completionForm = getViewPath("/pages/jsp/users/registeredform");
	private static String leftPage = "common/left.jsp";

	private static Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Value("${ImageCode.sessionKey}")
	private String sessionKey;

	@Autowired
	private UsersService usersService;
	@Autowired
	private FlowersService flowersService;

	@InitBinder({ "person" })
	public void initPersonBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("person.");
	}

	@InitBinder({ "users" })
	public void initUsersBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("users.");
	}

	@InitBinder({ "contact" })
	public void initContactBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("contact.");
	}

	/**
	 * 获取登录页面
	 * 
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "/loginpage")
	public String getLoginPage(HttpServletRequest request) {
		request.setAttribute("left", leftPage);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("name")) {
					request.setAttribute("name", cookie.getValue());
				}
				if (cookie.getName().equals("password")) {
					request.setAttribute("password", cookie.getValue());
				}
			}
		}
		return loginPage;
	}

	/**
	 * 用户登录
	 * 
	 * @param session
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/login")
	public String Login(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("left", leftPage);
		HttpSession session = request.getSession();

		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 获取数据
		String name = request.getParameter("loginname");
		String password = request.getParameter("password");
		String remember[] = request.getParameterValues("savepassword");
		String code = request.getParameter("code");
		// 记住密码
		if (remember != null) {
			Cookie nameCookie = new Cookie("name", name);
			nameCookie.setMaxAge(7 * 24 * 60 * 60);
			nameCookie.setPath(request.getContextPath() + "/");
			Cookie passwordCookie = new Cookie("password", password);
			passwordCookie.setMaxAge(7 * 24 * 60 * 60);
			passwordCookie.setPath(request.getContextPath() + "/");

			response.addCookie(nameCookie);
			response.addCookie(passwordCookie);
		}
		// 验证-------------------------
		if (name == null || "".equals(name.trim())) {
			request.setAttribute("msg", "名字不能为空！");
			return loginPage;
		}
		request.setAttribute("name", name);

		if (password == null || "".equals(password.trim())) {
			request.setAttribute("msg", "密码不能为空！");
			return loginPage;
		}
		request.setAttribute("password", password);

		if (code == null || "".equals(code.trim())) {
			request.setAttribute("msg", "验证码不能为空！");
			return loginPage;
		} else if (!code.trim().toLowerCase().equals(session.getAttribute("SESSIONCODE").toString().toLowerCase())) {
			request.setAttribute("msg", "验证码错误！");
			return loginPage;
		} else {
			if (!usersService.findPersonIsExists(name, password)) {
				request.setAttribute("msg", "用户不存在或密码错误！");
				return loginPage;
			}
		}
		// 验证结束--------------------------------------------------
		logger.info(name + "login pages at" + df.format(day));
		request.setAttribute("page", "indexbody");
		session.setAttribute("username", name);

		// 在线人数
		int count = 0;
		ServletContext application = request.getSession().getServletContext();
		if (application.getAttribute("count") != null) {
			try {
				count = Integer.valueOf(application.getAttribute("count").toString());
				application.setAttribute("count", ++count);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		return indexPage;
	}

	/**
	 * 获取注册页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/users/registeredpage")
	public String getBasicRegisteredPage(HttpServletRequest request) {
		getRegisteredFlowers(request);
		return basicRegisteredPageString;
	}

	/**
	 * 获取注册页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping("/completionpage")
	public String getRegisteredpage(HttpServletRequest request) {
		return registeredPage;
	}

	/**
	 * 获取注册表单
	 * 
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "/registeredform")
	public String getRegisteredForm(HttpServletRequest request, HttpSession session) {
		request.setAttribute("left", leftPage);
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
		}
		return completionForm;
	}

	/**
	 * 用户注册 x
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/users/registered")
	public String userRegistered(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		request.setAttribute("left", leftPage);
		// 用户名
		String name = request.getParameter("name");
		if (name == null || "".equals(name.trim())) {
			request.setAttribute("msg", "用户名不能为空！");
			getRegisteredFlowers(request);
			return basicRegisteredPageString;
		}
		if (name.length() < 4 || name.length() > 24) {
			request.setAttribute("msg", "用户名长度应大于4且小24！");
			getRegisteredFlowers(request);
			return basicRegisteredPageString;
		}
		request.setAttribute("name", name);

		// 密码
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		if (password == null || password2 == null || "".equals(password.trim()) || "".equals(password2.trim())) {
			request.setAttribute("msg", "密码不能为空！");
			getRegisteredFlowers(request);
			return basicRegisteredPageString;
		} else {
			if (!password.equals(password2)) {
				request.setAttribute("msg", "密码不一致！");
				getRegisteredFlowers(request);
				return basicRegisteredPageString;
			}
		}

		request.setAttribute("password", password);

		String code = request.getParameter("code");
		if (code == null || "".equals(code.trim())) {
			request.setAttribute("msg", "验证码不能为空！");
			getRegisteredFlowers(request);
			return basicRegisteredPageString;
		} else if (!code.trim().toLowerCase().equals(session.getAttribute("SESSIONCODE").toString().toLowerCase())) {
			request.setAttribute("msg", "验证码错误！");
			getRegisteredFlowers(request);
			return basicRegisteredPageString;
		}

		// 通过用户名查询是否存在该用户
		List<Person> users = usersService.queryPersonsByName(name);

		if (users == null || users.size() <= 0) { // 该用户不存在

			Users user = new Users();
			Person person = new Person();
			Contact contact = new Contact();
			Member member = new Member();

			person.setName(name.trim());
			person.setPassword(MD5Util.toMD5(password));

			Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			user.setRegister_date(Timestamp.valueOf(df.format(day)));
			user.setUser_id(person);

			member.setDiscount(new Float(1));
			member.setExperience(new Integer(0));
			member.setPerson_id(person);
			member.setRank(new Integer(1));
			// 调用service层
			usersService.addPersonInfo(person);
			usersService.addContactInfo(contact);
			usersService.createUsers(user);
			usersService.addMember(member);

			logger.info("用户" + name + "注册,时间: " + df.format(day));
			session.setAttribute("username", name);
			request.setAttribute("title", "注册成功");
			request.setAttribute("msg", "恭喜您：" + name + ",注册成功！");
			request.setAttribute("page", "indexbody");

		} else {
			request.setAttribute("msg", "该用户名以存在！");
			getRegisteredFlowers(request);
			return basicRegisteredPageString;
		}
		return indexPage;
	}

	/**
	 * 用户资料补全
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping("/registered")
	public String usersRegistered(HttpServletRequest request, HttpSession session) {
		request.setAttribute("left", leftPage);
		// 用户名
		Object name = session.getAttribute("username");
		if (name == null || "".equals(name.toString().trim())) {
			return loginPage;
		}
		// 性别
		String gender = request.getParameter("gender");
		// 年龄
		String usersage = request.getParameter("age");
		if (!DataVerificationUtil.isAge(usersage)) {
			request.setAttribute("msg", "年龄错误");
			return registeredPage;
		}

		// 生日
		String birtyday = request.getParameter("birtyday");
		// 联系方式
		String telephone = request.getParameter("telephone");

		System.out.println(telephone);

		if (!DataVerificationUtil.isTelephone(telephone)) {
			request.setAttribute("msg", "电话号码格式不正确！");
			return registeredPage;
		}
		request.setAttribute("telephone", telephone);
		// Email
		String email = request.getParameter("email");
		if (!DataVerificationUtil.isEmail(email)) {
			request.setAttribute("msg", "Email格式不正确");
			return registeredPage;
		}
		// 地址
		String province = request.getParameter("provid"); // 省
		String city = request.getParameter("cityid");// 市
		String area = request.getParameter("areaid");// 县
		String address2 = request.getParameter("address2"); // 详细地址
		request.setAttribute("address2", address2);

		// 数据库中地址字段为：省,市,县,详细地址（省，市，县为代号）
		String address = province + "," + city + "," + area + "," + address2;

		// 个人介绍
		String description = request.getParameter("description");

		List<Users> users = usersService.queryUsersByName(name.toString());
		List<Contact> contacts = usersService.queryUsersContactByName(name.toString());

		if (users != null && users.size() > 0) { // 该用户还不存在

			Users user = users.get(0);
			user.setAge(usersage == null ? 0 : Integer.valueOf(usersage));
			if (birtyday != null && !"".equals(birtyday.trim())) {
				Date birDate = null;
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					birDate = format.parse(birtyday);
					user.setBirtyday(new java.sql.Date(birDate.getTime()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			user.setDescription(description);

			if (DataVerificationUtil.notNull(gender)) {
				user.setGender(gender);
			}

			usersService.updateUsers(user);
			Contact contact = contacts.get(0);
			contact.setAddress(address);
			contact.setEmail(email);
			contact.setTelephone(telephone);
			usersService.updateUsersContact(contact);

		}

		request.setAttribute("msg", "修改成功！");
		return registeredPage;
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request) {
		request.setAttribute("left", leftPage);
		HttpSession session = request.getSession();
		request.setAttribute("page", "indexbody");
		if (session == null) {
			return indexPage;
		}
		session.removeAttribute("username");
		// 在线人数
		int count = 0;
		ServletContext application = request.getSession().getServletContext();
		if (application.getAttribute("count") != null) {
			try {
				count = Integer.valueOf(application.getAttribute("count").toString());
				if (count > 0) {
					application.setAttribute("count", --count);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		return indexPage;

	}

	/**
	 * 注册页面的鲜花展示
	 * 
	 * @param request
	 * @return void
	 */
	private void getRegisteredFlowers(HttpServletRequest request) {
		long count = flowersService.getFlowersCount();
		// long num = (long) (Math.random() * count + 1);
		// List<Flowers> flowers = flowersService.listRangeFlowers(num, 1);

		long num = (long) (Math.random() * (count - 4));
		List<Flowers> flowers = flowersService.listRangeFlowers(num, 4);

		if (flowers != null && flowers.size() > 0) {
			// request.setAttribute("flower", flowers.get(0));
			request.setAttribute("flowers", flowers);
		}

	}

}

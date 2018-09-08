package com.lqinggang.controller.admin;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lqinggang.common.constants.CommonConstants;
import com.lqinggang.common.controller.BaseController;
import com.lqinggang.common.entity.PageEntity;
import com.lqinggang.common.util.ConvertListToPageJsonUtil;
import com.lqinggang.common.util.DataVerificationUtil;
import com.lqinggang.common.util.ExportToExcelUtil;
import com.lqinggang.common.util.IpAddressUtil;
import com.lqinggang.common.util.MD5Util;
import com.lqinggang.entity.common.AdminLogs;
import com.lqinggang.entity.users.Contact;
import com.lqinggang.entity.users.Member;
import com.lqinggang.entity.users.Person;
import com.lqinggang.entity.users.Users;
import com.lqinggang.service.users.AdminService;
import com.lqinggang.service.users.UsersService;

/**
 * 后台用户相关管理
 * 
 * @author LQingGang
 * @time 2018年4月16日 - 下午1:53:26
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminUsersController extends BaseController {

	private static String usersInfoPage = getViewPath("/admin/users/usersinfo");
	private static String userspasswordPage = getViewPath("/admin/users/userspassword");
	private static String adduserPage = getViewPath("/admin/users/adduser");
	private static String usersSearchPage = getViewPath("/admin/users/usersearch");

	private static Logger logger = LoggerFactory.getLogger(AdminUsersController.class);

	@Autowired
	private UsersService usersService;
	@Autowired
	private AdminService adminService;

	/**
	 * 用户信息页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/usersinfoPage")
	public String getUsersInfoPage(HttpServletRequest request) {
		return usersInfoPage;
	}

	/**
	 * 获取用户信息数据
	 * 
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/usersinfo")
	public @ResponseBody Map<String, Object> getUsersInfo(HttpServletRequest request, HttpSession session) {

		// 分页信息
		PageEntity<Users> pageEntity = new PageEntity<Users>();
		String currentPage = request.getParameter("curr");
		if (currentPage != null && !"".equals(currentPage.trim())) {
			pageEntity.setCurrentPage(Integer.valueOf(currentPage));

		} else {
			pageEntity.setCurrentPage(1);
		}

		pageEntity.setPageSize(50);// 每页显示数据条数

		long count = usersService.getUsersCount(); // 总记录数
		long start = 0;
		if (pageEntity.getCurrentPage() != 1) {
			start = (pageEntity.getCurrentPage() - 1) * pageEntity.getPageSize();// 当前页从1开始
		}

		long size = pageEntity.getPageSize();

		List<Users> users = usersService.listRangeUsers(start, size);
		List<Contact> contacts = new ArrayList<Contact>();
		for (int i = 0; i < users.size(); i++) {
			List<Contact> contact = new ArrayList<Contact>();
			contact = usersService.queryUsersContactByName(users.get(i).getUser_id().getName());
			if (contact != null && contact.size() > 0) {
				contacts.add(contact.get(0));
			}
		}

		List<Member> members = usersService.listRangeMember(start, size);

		JSONArray userdata = ConvertListToPageJsonUtil.usersInfoToJsonArray(users, contacts, members);

		if (userdata != null) {

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", 0);
			jsonObject.put("msg", "");
			jsonObject.put("count", count);
			jsonObject.put("data", userdata);
			return jsonObject;
		}
		addLogs(request, session, session.getAttribute("adminName").toString() + "获取用户信息");

		return null;
	}

	/**
	 * 用户密码页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/userspasswordPage")
	public String getUsersPasswordPage(HttpServletRequest request) {
		return userspasswordPage;
	}

	/**
	 * 获取用户密码
	 * 
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/userspassword")
	public @ResponseBody Map<String, Object> getUsesPassword(HttpServletRequest request) {
		return null;
	}

	/**
	 * 新增用户页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/adduserPage")
	public String addUserPage(HttpServletRequest request, HttpSession session) {
		addLogs(request, session, session.getAttribute("adminName").toString() + "添加信息用户");

		return adduserPage;
	}

	/**
	 * 新增用户
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/users/add")
	public String addUser(HttpServletRequest request, HttpSession session) {
		addLogs(request, session, session.getAttribute("adminName").toString() + "添加信息用户");
		// 用户名
		String name = request.getParameter("name");
		if (name == null || "".equals(name.trim())) {
			request.setAttribute("msg", "用户名不能为空！");
			return adduserPage;
		}
		if (!DataVerificationUtil.isUsersName(name)) {
			request.setAttribute("msg", "用户名由英文,数字以及下划线组成");
			return adduserPage;
		}
		request.setAttribute("name", name);

		// 密码
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		if (password == null || password2 == null || "".equals(password.trim()) || "".equals(password2.trim())) {
			request.setAttribute("msg", "密码不能为空！");
			return adduserPage;
		} else {
			if (!password.equals(password2)) {
				request.setAttribute("msg", "密码不一致！");
				return adduserPage;
			}
		}

		request.setAttribute("password", password);
		// 性别
		String gender = request.getParameter("gender");
		// 年龄
		String usersage = request.getParameter("age");
		int age = 0;
		try {
			age = Integer.parseInt(usersage);
		} catch (Exception e) {
			request.setAttribute("msg", "年龄错误");
			return adduserPage;
		}
		// 生日
		String birtyday = request.getParameter("birtyday");
		// 联系方式
		String telephone = request.getParameter("telephone");
		if (telephone == null || "".equals(telephone.trim())) {
			request.setAttribute("msg", "电话号码不能为空！");
			return adduserPage;
		}
		request.setAttribute("telephone", telephone);
		// Email
		String email = request.getParameter("email");
		// 地址
		String province = request.getParameter("provid"); // 省
		String city = request.getParameter("cityid");// 市
		String area = request.getParameter("areaid");// 县
		String address2 = request.getParameter("address2"); // 详细地址
		if (address2 == null || "".equals(address2.trim())) {
			request.setAttribute("msg", "详细地址不能为空！");
			return adduserPage;
		}
		request.setAttribute("address2", address2);

		// 数据库中地址字段为：省,市,县,详细地址（省，市，县为代号）
		String address = province + "," + city + "," + area + "," + address2;

		// 个人介绍
		String description = request.getParameter("description");

		// 通过用户名查询是否存在该用户
		List<Person> users = usersService.queryPersonsByName(name);

		if (users == null || users.size() <= 0) { // 该用户还不存在
			Users user = new Users();
			Person person = new Person();
			Contact contact = new Contact();
			Member member = new Member();

			person.setName(name);
			person.setPassword(MD5Util.toMD5(password));

			user.setAge(age);

			// 获取当前日期作为用户注册时间
			Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 生日
			if (birtyday != null) {
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
			user.setGender(gender);
			user.setRegister_date(Timestamp.valueOf(df.format(day)));
			user.setUser_id(person);

			contact.setAddress(address);
			contact.setContact_id(person);
			contact.setEmail(email);
			contact.setTelephone(telephone);

			member.setDiscount(new Float(1));
			member.setExperience(new Integer(0));
			member.setPerson_id(person);
			member.setRank(new Integer(1));
			// 调用service层
			usersService.addPersonInfo(person);
			usersService.addContactInfo(contact);
			usersService.createUsers(user);
			usersService.addMember(member);
			request.setAttribute("msg", "添加用户成功！");

		} else {
			request.setAttribute("msg", "该用户名以存在！");
			return adduserPage;
		}

		return usersInfoPage;
	}

	/**
	 * 搜索用户结果页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/users/searchpage")
	public String getUsersSearchResultPage(HttpServletRequest request) {
		String search = request.getParameter("search");
		if (search != null && !"".equals(search.trim())) {
			request.setAttribute("search", search.toString());
			return usersSearchPage;
		}
		return usersInfoPage;
	}

	/**
	 * 用户搜索
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/users/search")
	public @ResponseBody Map<String, Object> usersSearch(HttpServletRequest request) {

		String search = request.getParameter("search");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "");
		jsonObject.put("code", 0);
		if (search != null && !"".equals(search.trim())) {
			List<Users> users = new ArrayList<Users>();
			List<Contact> contacts = new ArrayList<Contact>();
			List<Member> members = new ArrayList<Member>();
			JSONArray userinfo = new JSONArray();
			try {
				int id = Integer.valueOf(search);

				users = usersService.queryUsersById(id);
			} catch (NumberFormatException e) {

				users = usersService.queryUsersByName(search.trim());
			}

			if (users != null && users.size() > 0) {

				contacts = usersService.queryUsersContactByName(users.get(0).getUser_id().getName());
				members = usersService.queryUsersMemberByName(users.get(0).getUser_id().getName());
			}

			userinfo = ConvertListToPageJsonUtil.usersInfoToJsonArray(users, contacts, members);

			jsonObject.put("count", userinfo.size());
			jsonObject.put("data", userinfo);
		} else {
			request.setAttribute("msg", "不存在该用户");
			jsonObject.put("count", 0);
			jsonObject.put("data", "");
		}
		return jsonObject;
	}

	/**
	 * 导出用户信息
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/users/export")
	public String exportUsersInfo(HttpServletRequest request, HttpSession session) {

		PageEntity<Users> pageEntity = new PageEntity<Users>();
		String currentPage = request.getParameter("curr");
		if (currentPage != null && !"".equals(currentPage.trim())) {
			pageEntity.setCurrentPage(Integer.valueOf(currentPage));
		} else {
			pageEntity.setCurrentPage(1);
		}

		pageEntity.setPageSize(50);// 每页显示数据条数

		long start = 0;
		if (pageEntity.getCurrentPage() != 1) {
			start = (pageEntity.getCurrentPage() - 1) * pageEntity.getPageSize();// 当前页从1开始
		}

		long size = pageEntity.getPageSize();

		List<Users> users = usersService.listRangeUsers(start, size);
		List<Contact> contacts = new ArrayList<Contact>();
		for (int i = 0; i < users.size(); i++) {
			List<Contact> contact = new ArrayList<Contact>();
			contact = usersService.queryUsersContactByName(users.get(i).getUser_id().getName());
			if (contact != null && contact.size() > 0) {
				contacts.add(contact.get(0));
			}
		}

		List<Member> members = usersService.listRangeMember(start, size);

		Random r = new Random();
		Date d = new Date();
		String filename = String.valueOf(r.nextInt(99999999) + d.getTime());

		try {
			String path = request.getServletContext().getRealPath(CommonConstants.EXPORT_LOCATION + "/users/");
			ExportToExcelUtil.exportUsersInfoToExcel(path, filename, session.getAttribute("adminName").toString(),
					users, contacts, members);
			request.setAttribute("export_msg", "导出成功");
		} catch (Exception e) {
			request.setAttribute("export_msg", "导出失败");
			e.printStackTrace();
		}

		return usersInfoPage;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/users/delete")
	public String usersDelete(HttpServletRequest request, HttpSession session) {
		String ds = request.getParameter("ds");

		// System.out.println("users:" + ds == null ? "NULL" :
		// JSONArray.parseArray(ds).size());

		JSONArray jsonArray = JSONArray.parseArray(ds);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject users = jsonArray.getJSONObject(i);
			String idString = users.getString("id");
			if (idString != null && !"".equals(idString.trim())) {
				try {
					int usersid = Integer.valueOf(idString);
					usersService.deleteUsersById(usersid);
					request.setAttribute("msg", "删除成功！");
					addLogs(request, session, session.getAttribute("adminName").toString() + "删除用户" + usersid + "信息");
					logger.info("删除用户" + usersid + "信息成功");
				} catch (NumberFormatException e) {
					logger.warn("删除用户信息出错");
					request.setAttribute("msg", "删除出错！");
					break;
				}

			}
		}
		logger.info(session.getAttribute("adminName").toString() + "删除用户信息");
		return usersInfoPage;

	}

	/**
	 * 添加日志信息
	 * 
	 * @param request
	 * @param session
	 * @param content
	 * @return void
	 */
	public void addLogs(HttpServletRequest request, HttpSession session, String content) {
		AdminLogs adminLogs = new AdminLogs();
		String name = session.getAttribute("adminName").toString();

		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		adminLogs.setPerson_id(adminService.queryAdminsByName(name).get(0).getAdmin_id());
		adminLogs.setDate(Timestamp.valueOf(df.format(day)));
		adminLogs.setIp(IpAddressUtil.getIpAddr(request));
		adminLogs.setContent(content);
		adminService.addLogs(adminLogs);

	}
}

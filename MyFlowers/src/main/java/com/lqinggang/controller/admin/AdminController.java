package com.lqinggang.controller.admin;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lqinggang.common.controller.BaseController;
import com.lqinggang.common.entity.PageEntity;
import com.lqinggang.common.entity.Report;
import com.lqinggang.common.util.ConvertListToPageJsonUtil;
import com.lqinggang.common.util.IpAddressUtil;
import com.lqinggang.entity.common.AdminLogs;
import com.lqinggang.entity.users.Admin;
import com.lqinggang.entity.users.Contact;
import com.lqinggang.entity.users.Person;
import com.lqinggang.service.common.ReportViewService;
import com.lqinggang.service.orders.OrdersService;
import com.lqinggang.service.users.AdminService;
import com.lqinggang.service.users.UsersService;

/**
 * 后台管理页面
 * 
 * @author LQingGang
 * @time 2018年4月7日 - 下午5:16:47
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

	// private static String indexPage = getViewPath("/admin/common/index");
	private static String homePage = getViewPath("/admin/common/homepage");
	private static String loginPage = getViewPath("/admin/common/login");
	private static String infoPage = getViewPath("/admin/admin/info");
	private static String passwordPage = getViewPath("/admin/admin/password");

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private ReportViewService reportViewService;
	@Autowired
	private OrdersService ordersService;

	/**
	 * 管理退出登录
	 * 
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			logger.info("管理员" + session.getAttribute("adminName").toString() + "退出登录,时间：" + new Date().toString());
			addLogs(request, session, session.getAttribute("adminName").toString() + "退出登录");
			session.removeAttribute("adminName"); // 移除session
		}
		return loginPage;

	}

	/**
	 * 后台主页
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/homepage")
	public String gethomePage(HttpServletRequest request, HttpSession session) {

		request.setAttribute("pendingCount", ordersService.getPendingOrdersCount());// 设置待处理订单数
		request.setAttribute("purchasesCount", ordersService.getPuechaseCount(7)); // 获取7天内的评价数

		setFlowersSalesReport(request, session, 7); // 鲜花销售量前7
		setDailyFlowersSalesReport(request, 30); // 一月内每天鲜花销售量
		setUsersRegisteredReport(request, 15); // 15天内用户注册
		setUsersSalesReport(request, 10); // 购买量排名前10的用户
		logger.info("管理员" + session.getAttribute("adminName").toString() + "进入主页,时间：" + new Date().toString());
		addLogs(request, session, session.getAttribute("adminName").toString() + "登陆");
		return homePage;
	}

	/**
	 * 获取管理员信息页面
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/info")
	public String getInfoPage(HttpServletRequest request, HttpSession session) {

		String name = session.getAttribute("adminName").toString(); // 获取管理员名称
		List<Admin> admins = adminService.queryAdminsByName(name); // 查询对应的管理员
		List<Contact> contacts = usersService.queryUsersContactByName(name); // 查询管理员联系方式
		List<Person> persons = usersService.queryPersonsByName(name); // 查询管理员信息
		if (admins != null && contacts != null && persons != null) { // 设置管理员信息
			request.setAttribute("name", persons.get(0).getName());
			request.setAttribute("email", contacts.get(0).getEmail());
			request.setAttribute("telephone", contacts.get(0).getTelephone());
			String address[] = contacts.get(0).getAddress().split(",", 4);// 以,划分出4个字符串
			request.setAttribute("prov", address[0]); // 省
			request.setAttribute("city", address[1]);// 市
			request.setAttribute("area", address[2]); // 县
			request.setAttribute("address", address[3]); // 详细地址

		}
		addLogs(request, session, "获取管理员" + session.getAttribute("adminName").toString() + "信息");

		logger.info(session.getAttribute("adminName").toString() + "获取管理员信息,时间：" + new Date().toString());

		return infoPage;
	}

	/**
	 * 基本信息修改
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/infoupdate")
	public String infoUpdate(HttpServletRequest request, HttpSession session) {

		String name = session.getAttribute("adminName").toString();
		List<Contact> contacts = usersService.queryUsersContactByName(name);

		// 获取管理员修改之后的信息
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String prov = request.getParameter("provid");
		String city = request.getParameter("cityid");
		String area = request.getParameter("areaid");
		String address2 = request.getParameter("address");

		if (telephone == null) {
			request.setAttribute("title", "ERROR");
			request.setAttribute("msg", "电话号码不能为空！");
			return infoPage;
		}
		if (email == null) {
			request.setAttribute("title", "ERROR");
			request.setAttribute("msg", "电子邮箱不能为空！");
			return infoPage;
		}
		if (address2 == null) {
			request.setAttribute("title", "ERROR");
			request.setAttribute("msg", "详细地址不能为空！");
			return infoPage;
		}
		String address = prov + "," + city + "," + area + "," + address2;
		if (contacts != null) {
			contacts.get(0).setEmail(email);
			contacts.get(0).setTelephone(telephone);
			contacts.get(0).setAddress(address);
			usersService.updateUsersContact(contacts.get(0)); // 更新修改
			// 回转到jsp页面
			request.setAttribute("title", "SUCCESS");
			request.setAttribute("msg", "修改成功！");
			request.setAttribute("name", name);
			request.setAttribute("email", contacts.get(0).getEmail());
			request.setAttribute("telephone", contacts.get(0).getTelephone());
			request.setAttribute("prov", prov);
			request.setAttribute("city", city);
			request.setAttribute("area", area);
			request.setAttribute("address", address2);

			addLogs(request, session, "更新管理员" + session.getAttribute("adminName").toString() + "信息");
			logger.info(session.getAttribute("adminName").toString() + "更新管理员信息,时间：" + new Date().toString());
			return infoPage;
		}
		request.setAttribute("title", "ERROR");
		request.setAttribute("msg", "修改失败！");
		logger.warn(session.getAttribute("adminName").toString() + "更新管理员信息失败,时间：" + new Date().toString());
		return infoPage;
	}

	/**
	 * 管理员密码修改页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/password")
	public String getPasswordPage(HttpServletRequest request, HttpSession session) {
		logger.info(session.getAttribute("adminName").toString() + "进入密码修改页面,时间：" + new Date().toString());
		return passwordPage;
	}

	/**
	 * 密码修改
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/passwordupdate")
	public String psswordUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		String name = session.getAttribute("adminName").toString();

		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String password3 = request.getParameter("password3");

		if (password == null || password2 == null || password3 == null
				|| (password != null && password.trim().length() == 0)
				|| (password2 != null && password2.trim().length() == 0)
				|| (password3 != null && password3.trim().length() == 0)) { // 密码不能为空
			request.setAttribute("title", "ERROR");
			request.setAttribute("msg", "密码不能为空");
			return passwordPage;
		}

		if (!password2.equals(password3)) {
			request.setAttribute("title", "ERROR");
			request.setAttribute("msg", "密码不一致");
			return passwordPage;
		}

		if (usersService.findPersonIsExists(name, password)) { // 查询对应的管理员是否存在,间接验证原密码是否正确
			List<Person> persons = usersService.queryPersonsByName(name);
			persons.get(0).setPassword(password2.trim());
			usersService.updateUsersPassword(persons.get(0));
			request.setAttribute("title", "SUCCESS");
			request.setAttribute("msg", "修改成功！");
		} else {
			request.setAttribute("msg", "原密码错误！");
			request.setAttribute("title", "ERROR");
			logger.info(session.getAttribute("adminName").toString() + "修改密码失败,时间：" + new Date().toString());
		}

		addLogs(request, session, "更新管理员" + session.getAttribute("adminName").toString() + "密码");
		logger.info("管理员" + session.getAttribute("adminName").toString() + "修改密码,时间：" + new Date().toString());

		return passwordPage;
	}

	/**
	 * 获取日志信息
	 * 
	 * @param request
	 * @param session
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/logs")
	public @ResponseBody Map<String, Object> getLogs(HttpServletRequest request, HttpSession session) {
		// 分页信息
		PageEntity<AdminLogs> pageEntity = new PageEntity<AdminLogs>();
		String currentPage = request.getParameter("curr");
		if (currentPage == null) {
			pageEntity.setCurrentPage(1);
		} else {
			pageEntity.setCurrentPage(Integer.valueOf(currentPage));
		}

		pageEntity.setPageSize(30);// 每页显示数据条数

		long count = adminService.getLogsCount(); // 总记录数

		long start = 0;
		if (pageEntity.getCurrentPage() != 1) {
			start = (pageEntity.getCurrentPage() - 1) * pageEntity.getPageSize();// 当前页从1开始
		}

		long size = pageEntity.getPageSize();

		List<AdminLogs> adminLogs = adminService.listRangeLogs(start, size);

		JSONArray logs = ConvertListToPageJsonUtil.adminLogsInfoToJsonArray(adminLogs);// 将鲜花信息转化成JSONArray
		pageEntity.setTotalRecords(count);// 总记录数
		pageEntity.setTotalPages();// 总页数
		pageEntity.setList(adminLogs);// 数据

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", count);
		jsonObject.put("data", logs);
		logger.info("管理员" + session.getAttribute("adminName").toString() + "获取日志信息,时间：" + new Date().toString());

		return jsonObject;
	}

	/**
	 * 添加日志信息
	 * 
	 * @param request
	 * @param session
	 * @param content
	 * @return void
	 */
	private void addLogs(HttpServletRequest request, HttpSession session, String content) {
		AdminLogs adminLogs = new AdminLogs();
		String name = session.getAttribute("adminName").toString();

		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		adminLogs.setPerson_id(adminService.queryAdminsByName(name).get(0).getAdmin_id());
		adminLogs.setDate(Timestamp.valueOf(df.format(day))); // 设置日志时间为当前日期
		adminLogs.setIp(IpAddressUtil.getIpAddr(request)); // 设置IP地址
		adminLogs.setContent(content); // 设置内容
		adminService.addLogs(adminLogs);
	}

	/**
	 * 设置鲜花的销售报表
	 * 
	 * @param request
	 * @param session
	 * @return void
	 */
	private void setFlowersSalesReport(HttpServletRequest request, HttpSession session, int size) {
		List<Report> flowersReports = reportViewService.listFlowersSalesReport();
		long totalSales = reportViewService.getFlowersTotalSales(); // 获取鲜花总销售量
		request.setAttribute("totalSales", totalSales);

		List<Report> reports = new ArrayList<Report>();

		// 报表信息
		if (flowersReports != null && flowersReports.size() > size) {
			long sum = 0;
			for (int i = 0; i < size; i++) { // 销售量前size的鲜花
				sum += Long.valueOf(flowersReports.get(i).getNumber().toString());
				reports.add(flowersReports.get(i));
			}
			// 销售量size之后的鲜花
			reports.add(new Report("其他", totalSales - sum));
		} else {
			reports = flowersReports;
		}

		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < reports.size(); i++) { // 传化成JSONArray
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", reports.get(i).getName());
			jsonObject.put("value", reports.get(i).getNumber());
			jsonArray.add(jsonObject);
		}

		List<String> nameList = new ArrayList<String>();
		// 报表百分比
		for (Report report : reports) {

			nameList.add("'" + report.getName() + "'");

			long number = Long.valueOf(String.valueOf(report.getNumber())).longValue();
			float percentage = ((float) number / (float) totalSales);
			BigDecimal b = new BigDecimal(percentage);
			report.setNumber(b.setScale(4, BigDecimal.ROUND_HALF_UP).floatValue() * 100);
		}
		request.setAttribute("flowersReport", reports);
		request.setAttribute("salesList", jsonArray);
		request.setAttribute("salesNameList", nameList);
		logger.info("鲜花销售报表,时间：" + new Date().toString());
	}

	/**
	 * 每日鲜花销售量报表
	 * 
	 * @param request
	 * @param date
	 *            前date天鲜花销售
	 * @return void
	 */
	private void setDailyFlowersSalesReport(HttpServletRequest request, int date) {
		List<Report> reports = reportViewService.listRangeFlowersSales(date);

		List<Object> name = new ArrayList<Object>();
		List<Object> number = new ArrayList<Object>();

		if (reports != null && reports.size() > 0) {
			for (int i = reports.size() - 1; i >= 0; i--) {
				name.add("'" + reports.get(i).getName() + "'");
				number.add(reports.get(i).getNumber());
			}
		}
		request.setAttribute("nameList", name);
		request.setAttribute("numberList", number);
	}

	/**
	 * 用户统计报表
	 * 
	 * @param request
	 * @param date
	 *            前date天的用户注册
	 * @return void
	 */
	private void setUsersRegisteredReport(HttpServletRequest request, int date) {
		List<Report> reports = reportViewService.listUsesRegisteredReport(date); // 获取前date天内用户注册报表
		List<Object> womenList = reportViewService.listRangeUsersRegisteredGenderReport(date, "女"); // 获取前date天内女性用户注册数
		List<Object> manList = reportViewService.listRangeUsersRegisteredGenderReport(date, "男"); // 获取前date天内男性用户注册数

		if (reports != null && reports.size() > 0) {
			// request.setAttribute("usersRegisteredReport", reports);
			List<Object> name = new ArrayList<Object>();
			List<Object> number = new ArrayList<Object>();

			if (reports != null && reports.size() > 0) {
				// 反转
				for (int i = reports.size() - 1; i >= 0; i--) {
					name.add("'" + reports.get(i).getName() + "'");
					number.add(reports.get(i).getNumber());
				}
			}
			// 反转
			Collections.reverse(womenList);
			Collections.reverse(manList);
			request.setAttribute("usersNameList", name);
			request.setAttribute("usersNumberList", number);
			request.setAttribute("womenList", womenList);
			request.setAttribute("manList", manList);
			logger.info("每日鲜花销售报表,时间：" + new Date().toString());
		}

	}

	/**
	 * 购买量前size的用户
	 * 
	 * @param request
	 * @param size
	 * @return void
	 */
	private void setUsersSalesReport(HttpServletRequest request, int size) {

		List<Report> reports = reportViewService.listUsersSalesReport(size); // 列出购买量前size的用户
		if (reports != null) {

			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < reports.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", reports.get(i).getName());
				jsonObject.put("value", reports.get(i).getNumber());
				jsonArray.add(jsonObject);
			}
			request.setAttribute("usersSalesReport", jsonArray);

			List<String> nameList = new ArrayList<String>();
			for (int i = 0; i < reports.size(); i++) {
				nameList.add("'" + reports.get(i).getName() + "'");
			}
			request.setAttribute("usersNameSalesList", nameList);
			logger.info("购买量排行,时间：" + new Date().toString());
		}
	}

}

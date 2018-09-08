package com.lqinggang.controller.common;

import java.sql.Timestamp;
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
import com.lqinggang.common.util.IpAddressUtil;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.users.Admin;
import com.lqinggang.service.flowers.FlowersService;
import com.lqinggang.service.users.AdminService;

/**
 * 不方便分类的控制器
 * 
 * @author LQingGang
 * @time 2018年3月24日 - 下午1:15:40
 */
@Controller
@RequestMapping(value = "/mainPage")
public class IndexController extends BaseController {

	@Autowired
	private FlowersService flowersService;
	@Autowired
	private AdminService adminService;

	// private static String leftPage = "common/left.jsp";
	private static String indexPage = getViewPath("/pages/jsp/index");
	private static String aboutPage = getViewPath("/pages/jsp/common/aboutPage");
	private static String aboutInfoPage = getViewPath("/pages/jsp/common/about");
	private static String adminindexPage = getViewPath("/admin/common/index");
	private static String adminloginPage = getViewPath("/admin/common/login");
	private static String flowersSearchPage = getViewPath("/pages/jsp/common/flowerssearch");

	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

	/**
	 * 网站首页
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/index")
	public String getIndexPage(HttpServletRequest request, HttpSession session) {

		List<Flowers> recommendList = (List<Flowers>) flowersService.listSalesTopFlowers(5);

		session.setAttribute("recommendList", recommendList);
		// 按类别查找鲜花
		List<Flowers> festivaList = flowersService.findFlowersByFlowersCategoryName("节日鲜花");
		List<Flowers> weddingList = flowersService.findFlowersByFlowersCategoryName("婚礼鲜花");
		List<Flowers> loveList = flowersService.findFlowersByFlowersCategoryName("爱情鲜花");
		List<Flowers> condolenceList = flowersService.findFlowersByFlowersCategoryName("探望慰问");
		List<Flowers> elderList = flowersService.findFlowersByFlowersCategoryName("问候长辈");
		List<Flowers> otherList = flowersService.findFlowersByFlowersCategoryName("其他鲜花");

		session.setAttribute("festivaList", festivaList);
		session.setAttribute("weddingList", weddingList);
		session.setAttribute("loveList", loveList);
		session.setAttribute("condolenceList", condolenceList);
		session.setAttribute("elderList", elderList);
		session.setAttribute("otherList", otherList);

		/* request.setAttribute("left", leftPage); */
		if (request.getParameter("page") != null && !"".equals(request.getParameter("page").trim())) {
			request.setAttribute("page", request.getAttribute("page").toString());
		} else {
			request.setAttribute("page", "indexbody");
		}

		logger.info("进入主页");
		return indexPage;

	}

	/**
	 * 关于我们
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/aboutpage")
	public String getAboutPage(HttpServletRequest request) {
		logger.info("进入关于我们页面");
		return aboutPage;
	}

	/**
	 * 获取关于我们页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/about")
	public String getAboutInfo(HttpServletRequest request) {
		logger.info("获取关于我们页面");
		return aboutInfoPage;
	}

	/**
	 * 管理员登录页面
	 * 
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "/adminlogin")
	public String getAdminLoginPage(HttpServletRequest request) {
		logger.info(IpAddressUtil.getIpAddr(request) + "-获取管理员登录页面");
		return adminloginPage;
	}

	/**
	 * 管理员登录
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/login")
	public String adminLogin(HttpServletRequest request, HttpSession session) {
		String name = request.getParameter("name");
		if (name == null || name.trim().equals("")) {
			request.setAttribute("msg", "请输入管理员名称！");
			return adminloginPage;
		}
		String password = request.getParameter("password");
		if (password == null || password.trim().equals("")) {
			request.setAttribute("msg", "请输入密码！");
			return adminloginPage;
		}
		String code = request.getParameter("code");
		if (code == null || "".equals(code.trim()) || session.getAttribute("SESSIONCODE") == null
				|| !code.trim().toLowerCase().equals(session.getAttribute("SESSIONCODE").toString().toLowerCase())) {

			request.setAttribute("msg", "验证码错误");
			return adminloginPage;
		}

		List<Admin> admins = adminService.findAdmins(name, password);
		if (admins == null || admins.size() <= 0) {
			request.setAttribute("msg", "管理员名称或密码错误！");
			return adminloginPage;
		}
		session.setAttribute("adminName", name);
		session.setAttribute("lastlogindate", admins.get(0).getLast_login().toString());

		// 更新上次登录时间
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		admins.get(0).setLast_login(Timestamp.valueOf(df.format(day)));
		adminService.updateAdmin(admins.get(0));
		logger.info("管理员" + name + "登录");
		return adminindexPage;
	}

	/**
	 * 鲜花搜索
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flowers/search")
	public String getFloersSearchPage(HttpServletRequest request) {
		String type = "category";
		String name = "节日鲜花";
		String id = "1";
		if (request.getParameter("id") != null && !"".equals(request.getParameter("id").trim())) {
			id = request.getParameter("id");
			request.setAttribute("id", id);
		}
		if (request.getParameter("type") != null && !"".equals(request.getParameter("type").trim())) {
			type = request.getParameter("type");
		}

		if (request.getParameter("name") != null && !"".equals(request.getParameter("name").trim())) {
			name = request.getParameter("name");
		}
		request.setAttribute("name", name);
		request.setAttribute("type", type);
		logger.info("获取鲜花搜索页面");
		return flowersSearchPage;
	}

}

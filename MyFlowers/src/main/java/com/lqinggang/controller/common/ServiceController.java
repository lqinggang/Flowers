package com.lqinggang.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lqinggang.common.controller.BaseController;
import com.lqinggang.entity.orders.Orders;
import com.lqinggang.service.orders.OrdersService;

/**
 * 服务中心
 * 
 * @author LQingGang
 * @time 2018年3月29日 - 上午11:33:20
 */
@Controller
@RequestMapping(value = "/service")
public class ServiceController extends BaseController {

	private static String leftPage = "service/serviceleft.jsp";
	private static String servicePage = getViewPath("/pages/jsp/service/service");
	private static String complaintsPage = getViewPath("/pages/jsp/service/complaints");
	private static String problemPage = getViewPath("/pages/jsp/service/problem");
	private static String serviceDescriptionPage = getViewPath("/pages/jsp/service/serviceDescription");
	private static String shoppingProcessPage = getViewPath("/pages/jsp/service/shoppingprocess");
	private static String privacyPage = getViewPath("/pages/jsp/service/privacy");
	private static String safetyPage = getViewPath("/pages/jsp/service/safety");
	private static String paymentPage = getViewPath("/pages/jsp/service/payment");
	private static String contactPage = getViewPath("/pages/jsp/service/contact");
	private static String afterSalesPage = getViewPath("/pages/jsp/service/aftersales");
	private static String myordersPage = getViewPath("/pages/jsp/sales/orders");
	private static String loginPage = getViewPath("/pages/jsp/users/login");
	private static String orderinstructionPage = getViewPath("/pages/jsp/service/orderInstructions");

	private static Logger logger = LoggerFactory.getLogger(ServiceController.class);

	// @Autowired
	// private UsersService usersService;
	@Autowired
	private OrdersService ordersService;

	/**
	 * 服务中心页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/servicepage")
	public String getServicePage(HttpServletRequest request, HttpSession session) {
		request.setAttribute("left", leftPage);
		String page = request.getParameter("page");
		if (page != null && page.trim().equals("myorders")) {
			String type = request.getParameter("type");
			request.setAttribute("type", type);
		}
		if (page == null || "".equals(page)) { // 设置服务中心的默认显示页面
			page = "problem";
		}

		request.setAttribute("page", page);
		return servicePage;
	}

	/**
	 * 常见问题页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/problem")
	public String getProblemPage(HttpServletRequest request) {
		logger.info("getProblemPage");
		request.setAttribute("left", leftPage);
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "service/problem");
		map.put("position", "常见问题");

		request.setAttribute("positionMap", map);
		return problemPage;
	}

	/**
	 * 服务声明页面
	 * 
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "/servicedescription")
	public String getServiceStatementPage(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "service/serviceDescription");
		map.put("position", "服务声明");
		request.setAttribute("positionMap", map);
		request.setAttribute("left", leftPage);
		return serviceDescriptionPage;
	}

	/**
	 * 投诉说明页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/complaints")
	public String getComplaintsPage(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "service/complaints");
		map.put("position", "投诉说明");
		request.setAttribute("positionMap", map);
		request.setAttribute("left", leftPage);
		return complaintsPage;
	}

	/**
	 * 购物流程页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/shoppingprocess")
	public String getShoppingProcessPage(HttpServletRequest request) {
		request.setAttribute("left", leftPage);
		return shoppingProcessPage;
	}

	/**
	 * 支付说明
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/paymentInstructions")
	public String getPaymentInstructionsPage(HttpServletRequest request) {
		request.setAttribute("left", leftPage);
		return paymentPage;

	}

	/**
	 * 隐私条款页面
	 * 
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "/privacy")
	public String getprivacyPolicyPage(HttpServletRequest request) {
		request.setAttribute("left", leftPage);
		return privacyPage;

	}

	/**
	 * 安全条款页面
	 * 
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "/safety")
	public String getSafetyPage(HttpServletRequest request) {
		request.setAttribute("left", leftPage);
		return safetyPage;

	}

	/**
	 * 联系我们页面
	 * 
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "/contact")
	public String getContactPage(HttpServletRequest request) {
		request.setAttribute("left", leftPage);
		return contactPage;
	}

	/**
	 * 售后服务页面
	 * 
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "/aftersales")
	public String getAfterSalesPage(HttpServletRequest request) {
		request.setAttribute("left", leftPage);
		return afterSalesPage;
	}

	/**
	 * 订单说明页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/orderinstruction")
	public String orderinstruction(HttpServletRequest request) {
		request.setAttribute("left", leftPage);
		return orderinstructionPage;
	}

	/**
	 * 订单信息页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/myorders")
	public String getMyOrdersPage(HttpServletRequest request, HttpSession session) {
		request.setAttribute("left", leftPage);

		String type = request.getParameter("type");

		if (session.getAttribute("username") == null) {
			return loginPage;
		}

		long start = 0; // 默认开始位置
		long pagesize = 6; // 每页显示记录数
		int currpage = 0; // 默认当前页
		long count = 0; // 记录总数
		String curr = request.getParameter("curr");

		if (curr != null && !"".equals(curr.trim())) {
			currpage = Integer.valueOf(curr) - 1;
			start = currpage * pagesize;
		}

		request.setAttribute("curr", currpage + 1); // 设置当前页
		// 获取待签收订单总数
		request.setAttribute("receiptCount", ordersService.getOrdersCount(getUsersName(session), 2));
		// 获取待评价订单总数
		request.setAttribute("evaluatedCount", ordersService.getOrdersCount(getUsersName(session), 3));

		List<Orders> ordersList = new ArrayList<Orders>();
		if (type != null && type.trim().equals("2")) { // 待签收订单
			count = ordersService.getOrdersCount(getUsersName(session), 2);
			ordersList = ordersService.listRangeUsersOrders(getUsersName(session), 2, start, pagesize);
		} else if (type != null && type.trim().equals("3")) { // 待评价订单
			count = ordersService.getOrdersCount(getUsersName(session), 3);
			ordersList = ordersService.listRangeUsersOrders(getUsersName(session), 3, start, pagesize);
		} else { // 默认全部订单
			count = ordersService.getUsersOrdersCount(getUsersName(session));
			ordersList = ordersService.listRangeUsersOrders(getUsersName(session), start, pagesize);
		}
		request.setAttribute("count", count);
		request.setAttribute("orderList", ordersList);
		return myordersPage;
	}

	/**
	 * 订单搜索
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/orderssearch")
	public String orderssearch(HttpServletRequest request, HttpSession session) {
		request.setAttribute("left", leftPage);
		if (session.getAttribute("username") != null) {

			String name = session.getAttribute("username").toString().trim();

			long start = 0;
			int currpage = 0;
			long pagesize = 6;
			long count = 0;
			String curr = request.getParameter("curr");
			if (curr != null && !"".equals(curr.trim())) {
				currpage = Integer.valueOf(curr) - 1;
				start = currpage * pagesize;
			}

			request.setAttribute("curr", currpage + 1);

			count = ordersService.getUsersOrdersCount(name);
			List<Orders> ordersList = ordersService.listRangeUsersOrders(name, start, pagesize);

			request.setAttribute("count", count);
			request.setAttribute("orderList", ordersList);
		}

		return myordersPage;
	}

	/**
	 * 获取登录用户名
	 * 
	 * @param session
	 * @return String
	 */
	private String getUsersName(HttpSession session) {
		Object username = session.getAttribute("username");
		if (username != null && !"".equals(username.toString())) {
			return username.toString();
		}
		return null;
	}

}

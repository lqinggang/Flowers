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
import com.lqinggang.common.util.GenerateOrderIDUtil;
import com.lqinggang.common.util.IpAddressUtil;
import com.lqinggang.entity.common.AdminLogs;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.orders.Orders;
import com.lqinggang.entity.orders.OrdersCancel;
import com.lqinggang.entity.orders.Purchase;
import com.lqinggang.service.flowers.FlowersService;
import com.lqinggang.service.orders.OrdersService;
import com.lqinggang.service.users.AdminService;

/**
 * 后台订单相关管理
 * 
 * @author LQingGang
 * @time 2018年4月16日 - 下午1:51:53
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminOrdersController extends BaseController {

	private static String usersOrdersPage = getViewPath("/admin/orders/orders");
	private static String addOrderPage = getViewPath("/admin/orders/addorder");
	private static String pendingOrdersPage = getViewPath("/admin/orders/pendingorders");
	private static String evaluationPage = getViewPath("/admin/orders/evaluation");
	private static String orderSearchResultPage = getViewPath("/admin/orders/ordersearch");
	private static String evaluationSearchPage = getViewPath("/admin/orders/evaluationsearchpage");

	private static Logger logger = LoggerFactory.getLogger(AdminOrdersController.class);

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private FlowersService flowersService;
	// @Autowired
	// private UsersService usersService;

	/**
	 * 订单信息页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/ordersinfoPage")
	public String ordersInfoPage(HttpServletRequest request, HttpSession session) {
		logger.info("订单信息页面");
		return usersOrdersPage;
	}

	/**
	 * 获取所有用户订单信息
	 * 
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/ordersinfo")
	public @ResponseBody Map<String, Object> getOrdersInfo(HttpServletRequest request, HttpSession session) {
		// 分页信息
		PageEntity<Orders> pageEntity = new PageEntity<Orders>();

		String currentPage = request.getParameter("curr");
		if (currentPage != null && !"".equals(currentPage.trim())) {
			pageEntity.setCurrentPage(Integer.valueOf(currentPage));

		} else {
			pageEntity.setCurrentPage(1);
		}

		long count = 0;
		long start = 0;
		// long pagesize = 50;
		pageEntity.setPageSize(50);// 每页显示数据条数
		if (pageEntity.getCurrentPage() != 1) {
			start = (pageEntity.getCurrentPage() - 1) * pageEntity.getPageSize();// 当前页从1开始
		}

		long size = pageEntity.getPageSize();

		List<Orders> ordersList = null;
		if (request.getParameter("pending") == null) {
			ordersList = ordersService.listRangeOrders(start, size);
			count = ordersService.getAllOrdersCount(); // 总记录数;
		} else {
			ordersList = ordersService.listPendingOrders(start, size);
			count = ordersService.getPendingOrdersCount();

		}

		JSONArray ordersInfo = ConvertListToPageJsonUtil.ordersInfoToJsonArray(ordersList);// 将订单信息转化成JSONArray

		pageEntity.setTotalRecords(count);// 总记录数
		pageEntity.setTotalPages();// 总页数

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", count);
		jsonObject.put("data", ordersInfo);
		request.setAttribute("count", count);
		addLogs(request, session, session.getAttribute("adminName").toString() + "获取鲜花信息");
		logger.info("订单信息");
		return jsonObject;
	}

	/**
	 * 新增订单页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/addorderPage")
	public String addOrderPage(HttpServletRequest request) {
		List<Flowers> flowersList = flowersService.listAllFlowers();
		request.setAttribute("flowersList", flowersList);
		logger.info("新增订单页面");
		return addOrderPage;
	}

	/**
	 * 新增订单
	 * 
	 * @param request
	 * @return void
	 */
	@RequestMapping(value = "/addorder")
	public String addOrder(HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("id");
		String amount = request.getParameter("amount");
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String note = request.getParameter("note");

		Orders orders = new Orders();

		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (id == null || (id != null && "".equals(id.trim()))) {
			request.setAttribute("tip", "id");
			request.setAttribute("msg", "鲜花编号不能为空！");
			return addOrderPage;
		} else {
			int flowers_id;
			try {
				flowers_id = Integer.valueOf(id);
				List<Flowers> flowers = flowersService.queryFlowers(flowers_id);
				orders.setFlower_id(flowers.get(0));
			} catch (Exception e) {
				request.setAttribute("tip", "id");
				request.setAttribute("msg", "不存在该鲜花！");
				return addOrderPage;
			}
		}

		if (amount == null || (amount != null && "".equals(amount.trim()))) {
			request.setAttribute("tip", "amount");
			request.setAttribute("msg", "鲜花数量不能为空！");
			return addOrderPage;
		} else {
			int flower_amount;
			try {
				flower_amount = Integer.valueOf(amount);
				orders.setAmount(flower_amount);

			} catch (Exception e) {
				request.setAttribute("tip", "amount");
				request.setAttribute("msg", "鲜花数量格式不正确！");
				return addOrderPage;
			}
		}
		if (name == null || (name != null && "".equals(name.trim()))) {
			request.setAttribute("tip", "name");
			request.setAttribute("msg", "收件人不能为空！");
			return addOrderPage;
		}
		orders.setRecipient(name);
		if (contact == null || (contact != null && "".equals(contact.trim()))) {
			request.setAttribute("tip", "contact");
			request.setAttribute("msg", "收件人联系方式不能为空！");
			return addOrderPage;
		}
		orders.setContact(contact);
		if (address == null || (address != null && "".equals(address.trim()))) {
			request.setAttribute("tip", "address");
			request.setAttribute("msg", "收件地址不能为空！");
			return addOrderPage;
		}
		orders.setAddress(address);
		orders.setNote(note);
		orders.setStatus_type_id(ordersService.queryOrderStatusTypes(1).get(0));
		orders.setDate(Timestamp.valueOf(df.format(day)));
		orders.setOrder_id(GenerateOrderIDUtil.getOrderId());
		orders.setPrice(orders.getFlower_id().getPrice() * orders.getAmount());
		// orders.setPerson_id(usersService.queryPersonsByName(session.getAttribute("adminName").toString()).get(0));

		ordersService.addOrders(orders);

		addLogs(request, session, session.getAttribute("adminName").toString() + "新增订单" + orders.getOrder_id());
		logger.info(session.getAttribute("adminName").toString() + "新增订单" + orders.getOrder_id());
		return usersOrdersPage;
	}

	/**
	 * 待处理订单页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/pendingordersPage")
	public String pendingOrdersPage(HttpServletRequest request) {
		return pendingOrdersPage;
	}

	/**
	 * 待处理订单数据
	 * 
	 * @param request
	 * @param session
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/pendingorders")
	public @ResponseBody Map<String, Object> pendingOrders(HttpServletRequest request, HttpSession session) {
		// 分页信息
		PageEntity<Orders> pageEntity = new PageEntity<Orders>();
		String currentPage = request.getParameter("curr");
		if (currentPage != null && !"".equals(currentPage.trim())) {
			pageEntity.setCurrentPage(Integer.valueOf(currentPage));

		} else {
			pageEntity.setCurrentPage(1);
		}

		pageEntity.setPageSize(50);// 每页显示数据条数

		long count = ordersService.getAllOrdersCount(); // 总记录数
		long start = 0;
		if (pageEntity.getCurrentPage() != 1) {
			start = (pageEntity.getCurrentPage() - 1) * pageEntity.getPageSize();// 当前页从1开始
		}

		long size = pageEntity.getPageSize();

		List<Orders> ordersList = ordersService.listPendingOrders(start, size);

		JSONArray ordersInfo = ConvertListToPageJsonUtil.ordersInfoToJsonArray(ordersList);// 将订单信息转化成JSONArray

		pageEntity.setTotalRecords(count);// 总记录数
		pageEntity.setTotalPages();// 总页数

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", count);
		jsonObject.put("data", ordersInfo);
		request.setAttribute("count", count);

		// addLogs(request, session,
		// session.getAttribute("adminName").toString() + "获取待处理订单信息");
		return jsonObject;

	}

	/**
	 * 评价页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/evaluationPage")
	public String getEvaluationPgae() {
		return evaluationPage;
	}

	/**
	 * 获取鲜花评价信息数据
	 * 
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/evaluation")
	public @ResponseBody Map<String, Object> getFlowersEvaluation(HttpServletRequest request, HttpSession session) {

		// 分页信息
		PageEntity<Purchase> pageEntity = new PageEntity<Purchase>();
		String currentPage = request.getParameter("curr");
		if (currentPage != null && !"".equals(currentPage.trim())) {
			pageEntity.setCurrentPage(Integer.valueOf(currentPage));

		} else {
			pageEntity.setCurrentPage(1);
		}
		pageEntity.setPageSize(50);// 每页显示数据条数

		long count = ordersService.getPurchasesCount();// 总记录数
		long start = 0;
		if (pageEntity.getCurrentPage() != 1) {
			start = (pageEntity.getCurrentPage() - 1) * pageEntity.getPageSize();// 当前页从1开始
		}

		long size = pageEntity.getPageSize();

		List<Purchase> purchasesList = new ArrayList<Purchase>();
		if (request.getParameter("orderid") != null && !"".equals(request.getParameter("orderid").trim())) {
			purchasesList = ordersService.queryPurchaseByOrdersId(request.getParameter("orderid"));
			count = (purchasesList == null ? 0 : purchasesList.size());
		} else {
			purchasesList = ordersService.listRangePurchases(start, size); // 获取所有鲜花信息
		}

		JSONArray purchases = ConvertListToPageJsonUtil.purchaseInfoToJsonArray(purchasesList);// 将鲜花信息转化成JSONArray

		pageEntity.setTotalRecords(count);// 总记录数
		pageEntity.setTotalPages();// 总页数
		pageEntity.setList(purchasesList);// 数据

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", count);
		jsonObject.put("data", purchases);
		request.setAttribute("count", count);

		// addLogs(request, session,
		// session.getAttribute("adminName").toString() + "获取评价信息");

		return jsonObject;

	}

	/**
	 * 确认发货
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/order/ship")
	public String confirmShipment(HttpServletRequest request, HttpSession session) {

		String orderid = request.getParameter("orderid");
		if (orderid != null) {
			List<Orders> orders = ordersService.queryOrdersByOrdersId(orderid); // 根据订单编号查询对应的订单
			if (orders != null && orders.size() > 0) {
				if (orders.get(0).getFlower_id().getQuantity() < orders.get(0).getAmount()) {
					request.setAttribute("msg", "库存量不足");
					addLogs(request, session,
							session.getAttribute("adminName").toString() + " 订单" + orderid + "发货失败：库存量不足！");
				} else {
					orders.get(0).setStatus_type_id(ordersService.queryOrderStatusTypes(2).get(0));
					int quantity = orders.get(0).getFlower_id().getQuantity() - orders.get(0).getAmount(); // 当前鲜花库存量
					orders.get(0).getFlower_id().setQuantity(quantity); // 鲜花库存量减少
					flowersService.updateFlowers(orders.get(0).getFlower_id()); // 更新鲜花库存量
					ordersService.updateOrdes(orders.get(0));// 更新鲜花状态
					addLogs(request, session, session.getAttribute("adminName").toString() + " 订单" + orderid + "发货");
				}

			}
		}

		return pendingOrdersPage;
	}

	/**
	 * 取消订单
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/order/cancel")
	public String cancelOrder(HttpServletRequest request, HttpSession session) {

		String orderid = request.getParameter("orderid");
		if (orderid != null) {
			List<Orders> orders = ordersService.queryOrdersByOrdersId(orderid);
			if (orders != null && orders.size() > 0) {
				orders.get(0).setStatus_type_id(ordersService.queryOrderStatusTypes(0).get(0));
				ordersService.updateOrdes(orders.get(0));
				Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				OrdersCancel ordersCancel = new OrdersCancel();
				ordersCancel.setContent("管理员取消");
				ordersCancel.setOrder_status_id(orders.get(0));
				ordersCancel.setDate(Timestamp.valueOf(df.format(day)));
				ordersService.setOrdersStatus(ordersCancel);

				Flowers flowers = orders.get(0).getFlower_id();
				flowers.setQuantity(flowers.getQuantity() + orders.get(0).getAmount());// 更新鲜花库存量
				flowersService.updateFlowers(flowers);
			}
		}
		addLogs(request, session, session.getAttribute("adminName").toString() + " 取消订单" + orderid);

		return pendingOrdersPage;

	}

	/**
	 * 订单搜索页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/order/searchpage")
	public String searchResultPage(HttpServletRequest request) {
		String orderid = request.getParameter("orderid");
		if (orderid != null && !"".equals(orderid.trim()) && DataVerificationUtil.isNumber(orderid)) {
			request.setAttribute("orderid", orderid.toString());
			return orderSearchResultPage;
		}
		return usersOrdersPage;
	}

	/**
	 * 订单搜索结果
	 * 
	 * @param request
	 * @param session
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/order/search")
	public @ResponseBody Map<String, Object> searchOrder(HttpServletRequest request, HttpSession session) {

		String orderid = request.getParameter("orderid");
		JSONObject jsonObject = new JSONObject();
		if (orderid != null && !"".equals(orderid.trim())) {
			List<Orders> orders = ordersService.queryOrdersByOrdersId(orderid);
			JSONArray order = ConvertListToPageJsonUtil.ordersInfoToJsonArray(orders);
			jsonObject.put("msg", "");
			jsonObject.put("count", order.size());
			jsonObject.put("code", 0);
			jsonObject.put("data", order);

		} else {
			request.setAttribute("msg", "订单编号不能为空");
		}
		return jsonObject;
	}

	/**
	 * 删除订单
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/orders/delete")
	public String deleteOrder(HttpServletRequest request, HttpSession session) {

		String ds = request.getParameter("ds");

		// System.out.println(ds == null ? "ds is NULL" : ds);

		JSONArray jsonArray = null;
		try {
			jsonArray = JSONArray.parseArray(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(jsonArray == null ? "NULL" : jsonArray.size());

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject orders = jsonArray.getJSONObject(i);
			String idString = orders.getString("orderid");

			// System.out.println(idString);

			if (idString != null && !"".equals(idString.trim())) {
				// ordersService.deletePurchaseByOrderId(idString);
				ordersService.deleteOrders(idString);
				addLogs(request, session, session.getAttribute("adminName").toString() + " 删除订单" + idString);
				request.setAttribute("msg", "删除成功！");
				logger.info("删除成功");
			}
		}
		// addLogs(request, session,
		// session.getAttribute("adminName").toString() + "删除鲜花信息");
		//
		// String id = request.getParameter("orderid");
		//
		// if (id != null && !"".equals(id.trim())) {
		// ordersService.deleteOrders(id);
		// addLogs(request, session,
		// session.getAttribute("adminName").toString() + " 删除订单" +
		// id.toString());
		// request.setAttribute("msg", id.toString() + "订单删除成功");
		// }
		return usersOrdersPage;
	}

	/**
	 * 导出订单信息
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/orders/export")
	public String exportOrders(HttpServletRequest request, HttpSession session) {

		// 分页信息
		PageEntity<Orders> pageEntity = new PageEntity<Orders>();
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

		List<Orders> ordersList = null;
		if (request.getParameter("type") == null) {
			ordersList = ordersService.listRangeOrders(start, size);
		} else {
			ordersList = ordersService.listPendingOrders(start, size);
		}

		Random r = new Random();
		Date d = new Date();

		String filename = String.valueOf(r.nextInt(99999999) + d.getTime());

		try {
			String path = request.getServletContext().getRealPath(CommonConstants.EXPORT_LOCATION + "/orders/");
			ExportToExcelUtil.exportOrdersInfoToExcel(path, filename, session.getAttribute("adminName").toString(),
					ordersList);
			request.setAttribute("export_msg", "导出成功");
			addLogs(request, session, session.getAttribute("adminName").toString() + " 导出订单信息");
		} catch (Exception e) {
			request.setAttribute("export_msg", "导出失败");
			e.printStackTrace();
		}

		return usersOrdersPage;
	}

	/**
	 * 订单（鲜花）评价信息页面(订单评价搜索)
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/order/evaluationsearchpage")
	public String getEvaluationSearchPage(HttpServletRequest request) {
		String orderid = request.getParameter("orderid");
		if (orderid != null && !"".equals(orderid.trim()) && DataVerificationUtil.isNumber(orderid)) {
			request.setAttribute("orderid", orderid);
		}
		return evaluationSearchPage;
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

	// public static PageEntity<Orders> page() {
	//
	// }
	//

}

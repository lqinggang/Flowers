package com.lqinggang.controller.order;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lqinggang.common.constants.CommonConstants;
import com.lqinggang.common.controller.BaseController;
import com.lqinggang.common.util.FileUtil;
import com.lqinggang.controller.admin.AdminFlowersController;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.orders.OrderStatusType;
import com.lqinggang.entity.orders.Orders;
import com.lqinggang.entity.orders.OrdersCancel;
import com.lqinggang.entity.orders.Purchase;
import com.lqinggang.entity.users.Member;
import com.lqinggang.entity.users.Person;
import com.lqinggang.service.flowers.FlowersService;
import com.lqinggang.service.orders.OrdersService;
import com.lqinggang.service.users.UsersService;

/**
 * 订单相关
 * 
 * @author LQingGang
 * @time 2018年4月13日 - 下午5:36:41
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {

	private static String loginPage = getViewPath("/pages/jsp/users/login");
	private static String orderInfoPage = getViewPath("/pages/jsp/sales/orderinfo");
	private static String cartPage = getViewPath("/pages/jsp/sales/cart");
	private static String myordersPage = getViewPath("/pages/jsp/sales/orders");
	private static String evaluationPage = getViewPath("/pages/jsp/sales/evaluation");

	private static Logger logger = LoggerFactory.getLogger(AdminFlowersController.class);

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private FlowersService flowersService;

	/**
	 * 订单信息页面
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/orderinfo")
	public String getOrderInfoPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		if (session.getAttribute("username") == null) { // 用户未登录
			return loginPage;
		}
		String username = session.getAttribute("username").toString();
		if (request.getParameter("orderid") != null && request.getParameter("orderid").length() > 0) { // 订单编号不能为空
			List<Person> persons = usersService.queryPersonsByName(username); // 根据用户名查询用户
			if (persons != null && persons.size() > 0) {
				String orderId = request.getParameter("orderid"); // 获取订单编号
				if (orderId != null && !"".equals(orderId.trim())) { // 订单ID不为空
					List<Orders> orders = ordersService.queryOrdersByOrdersId(orderId.trim());
					if (orders != null && orders.size() > 0) { // 订单存在
						request.setAttribute("orderinfo", orders.get(0));
						if (orders.get(0).getStatus_type_id().getStatus_type_id() == 1) {// 待发货订单
							request.setAttribute("page", "cancel");
							request.setAttribute("info", "取消订单");
						} else if (orders.get(0).getStatus_type_id().getStatus_type_id() == 2) { // 带签收订单
							request.setAttribute("page", "sign");
							request.setAttribute("info", "确认签收");
						} else if (orders.get(0).getStatus_type_id().getStatus_type_id() == 3) { // 待评价订单
							request.setAttribute("page", "purchase");
							request.setAttribute("info", "立即评价");
							request.setAttribute("target", "_top");
						} else {
							// ----
						}
					} else {
						request.setAttribute("msg", "订单不存在");
						request.setAttribute("page", "myorders");
						return myordersPage;
					}

				}
			}
		} else {
			request.setAttribute("msg", "订单编号不能为空！");
			request.setAttribute("page", "myorders");
			return myordersPage;

		}
		return orderInfoPage;
	}

	/**
	 * 取消订单
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/cancel")
	public String cancelOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		if (session.getAttribute("username") == null) {// 用户未登录
			return loginPage;
		}
		if (request.getParameter("orderid") == null) {// 订单编号不能为空
			return orderInfoPage;
		}

		String cancel_reson = request.getParameter("content"); // 获取订单取消原因
		String orderid = request.getParameter("orderid").toString();
		List<Orders> orders = ordersService.queryOrdersByOrdersId(orderid);
		List<OrdersCancel> orderStatus = ordersService.queryOrdersStatusByOrdersId(orderid);
		List<OrderStatusType> orderStatusTypes = ordersService.queryOrderStatusTypes(0);

		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		orders.get(0).setStatus_type_id(orderStatusTypes.get(0));

		if (orderStatus == null || orderStatus.size() == 0) { // 订单状态不存在
			OrdersCancel ordersCancel = new OrdersCancel();
			ordersCancel.setContent(cancel_reson);
			ordersCancel.setOrder_status_id(orders.get(0));
			ordersCancel.setDate(Timestamp.valueOf(df.format(day)));
			// ordersCancel.setStatus(true);
			ordersService.setOrdersStatus(ordersCancel);
		} else {
			// orderStatus.get(0).setStatus(true);
			orderStatus.get(0).setDate(Timestamp.valueOf(df.format(day)));
			orderStatus.get(0).setContent(cancel_reson);
			ordersService.updateOrderStauts(orderStatus.get(0));
		}

		ordersService.updateOrdes(orders.get(0)); // 更新订单
		Flowers flowers = orders.get(0).getFlower_id();
		flowers.setQuantity(flowers.getQuantity() + orders.get(0).getAmount());// 更新鲜花库存量
		flowersService.updateFlowers(flowers);

		request.setAttribute("msg", "订单取消成功");
		try {
			request.setAttribute("page", "myorders");
			response.sendRedirect(request.getContextPath() + "/service/servicepage?page=myorders");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cartPage;
	}

	/**
	 * 订单签收
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/sign")
	public String signOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		String orderid = request.getParameter("orderid");
		if (orderid != null) {
			List<Orders> orders = ordersService.queryOrdersByOrdersId(orderid);
			List<OrderStatusType> orderStatusTypes = ordersService.queryOrderStatusTypes(3);
			orders.get(0).setStatus_type_id(orderStatusTypes.get(0));
			ordersService.updateOrdes(orders.get(0));

			List<Member> members = usersService.queryUsersMemberByName(session.getAttribute("username").toString());
			Member member = members.get(0);
			int experience = (int) (member.getExperience() + orders.get(0).getPrice());
			if (experience <= 1000) {
				member.setRank(1);
				member.setDiscount((float) 1);
			} else if (experience > 1000 && experience <= 2000) {
				member.setRank(2);
				member.setDiscount((float) 0.9);
			} else {
				member.setRank(3);
				member.setDiscount((float) 0.8);
			}
			member.setExperience((int) (member.getExperience() + orders.get(0).getPrice()));
			usersService.updateUsersMember(member);

		} else {
			request.setAttribute("msg", "订单编号不能为空");
		}
		try {
			request.setAttribute("page", "myorders");
			response.sendRedirect(request.getContextPath() + "/service/servicepage?page=myorders");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cartPage;
	}

	/**
	 * 订单评价页面
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/purchase")
	public String purchaseOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return loginPage;
		}
		String orderid = request.getParameter("orderid");
		request.setAttribute("orderid", orderid);
		return evaluationPage;
	}

	/**
	 * 订单评价
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/evaluate")
	public String evaluateOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		String orderid = request.getParameter("orderid");
		if (orderid == null) {
			request.setAttribute("msg", "订单编号不能为空");
			try {
				request.setAttribute("page", "myorders");
				response.sendRedirect(request.getContextPath() + "/service/servicepage?page=myorders");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<Orders> orders = ordersService.queryOrdersByOrdersId(orderid);
		if (orders == null || orders.size() == 0) {
			request.setAttribute("msg", "订单不存在");
			try {
				request.setAttribute("page", "myorders");
				response.sendRedirect(request.getContextPath() + "/service/servicepage?page=myorders");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String service = request.getParameter("service");
		String logistics = request.getParameter("logistics");
		String commodity = request.getParameter("commodity");
		String content = request.getParameter("evaluation_content");
		String evaluationImage = request.getParameter("evaluationimg");

		Purchase purchase = new Purchase();
		if (service == null) {
			service = "5";
		}
		if (logistics == null) {
			logistics = "5";
		}
		if (commodity == null) {
			commodity = "5";
		}
		if (content == null) {
			content = "<span style=\"margin-right:10px;\">此用户没有评价！</span>";
		} else {
			content = "<span style=\"margin-right:10px;\"" + content + "</span>";
		}
		if (evaluationImage != null) {
			content += evaluationImage;
		}

		List<OrderStatusType> orderStatusTypes = ordersService.queryOrderStatusTypes(4);
		orders.get(0).setStatus_type_id(orderStatusTypes.get(0));

		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		purchase.setService(Integer.valueOf(service));
		purchase.setLogistics(Integer.valueOf(logistics));
		purchase.setCommodity(Integer.valueOf(commodity));
		purchase.setPurchase_content(content);
		purchase.setDate(Timestamp.valueOf(df.format(day)));
		purchase.setOrder_id(orders.get(0));

		ordersService.updateOrdes(orders.get(0)); // 更新订单状态
		ordersService.addPurchase(purchase); // 新增订单评价

		try {
			request.setAttribute("page", "myorders");
			response.sendRedirect(request.getContextPath() + "/service/servicepage?page=myorders");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return myordersPage;
	}

	/**
	 * 评价图片上传
	 * 
	 * @param request
	 * @param session
	 * @param file
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/evaluate/upload")
	public @ResponseBody Map<String, Object> eveluationUpload(HttpServletRequest request, HttpSession session,
			MultipartFile file) {
		// 原始文件名称
		String oldName = file.getOriginalFilename();

		// 上传文件路径
		String path = request.getServletContext()
				.getRealPath(CommonConstants.FLOWRES_IMG_URL + "/uploadPic/evaluation");
		// 改变文件名
		String fileName = FileUtil.fileRename(oldName);
		// 图片文件名
		String imageName = fileName;
		// 创建年月文件夹
		Calendar date = Calendar.getInstance();
		File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));

		// 图片路径
		String relativePath = CommonConstants.FLOWRES_IMG_URL + "/uploadPic/evaluation" + File.separator + dateDirs
				+ File.separator;
		relativePath = relativePath.replace("\\", "/");

		// 改成绝对路径
		fileName = path + File.separator + dateDirs + File.separator + fileName;
		fileName = fileName.replace("\\", "/");
		File imagefile = new File(fileName);
		// 文件夹不存在时创建文件夹
		if (!imagefile.getParentFile().exists()) {
			imagefile.getParentFile().mkdirs();
		}

		try {
			// 将内存中的数据写入磁盘
			file.transferTo(imagefile);
		} catch (IllegalStateException e) {
			logger.warn("上传异常：IllegalStateException");
			e.printStackTrace();
		} catch (IOException e) {
			logger.warn("上传异常：IOException");
			e.printStackTrace();
		}

		JSONObject jsonObject = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("src", relativePath);
		map.put("name", imageName);
		jsonObject.put("code", 0);
		jsonObject.put("data", map);
		jsonObject.put("msg", "");
		logger.info("图片上传成功");
		return jsonObject;

	}
}

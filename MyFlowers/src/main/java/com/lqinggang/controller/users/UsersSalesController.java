package com.lqinggang.controller.users;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lqinggang.common.controller.BaseController;
import com.lqinggang.common.util.GenerateOrderIDUtil;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.orders.OrderStatusType;
import com.lqinggang.entity.orders.Orders;
import com.lqinggang.entity.orders.OrdersCancel;
import com.lqinggang.entity.users.Cart;
import com.lqinggang.entity.users.Contact;
import com.lqinggang.entity.users.Member;
import com.lqinggang.entity.users.Person;
import com.lqinggang.service.flowers.FlowersService;
import com.lqinggang.service.orders.OrdersService;
import com.lqinggang.service.users.UsersService;

/**
 * 用户购买相关
 * 
 * @author LQingGang
 * @time 2018年4月6日 - 上午11:10:57
 */
@Controller
@RequestMapping(value = "/users")
public class UsersSalesController extends BaseController {
	private static String loginPage = getViewPath("/pages/jsp/users/login");
	private static String cartPage = getViewPath("/pages/jsp/sales/cart");
	private static String myCartPage = getViewPath("/pages/jsp/sales/mycart");
	private static String buyPage = getViewPath("/pages/jsp/sales/buypage");
	private static String servicePage = getViewPath("/pages/jsp/service/service");

	@Autowired
	private UsersService usersService;
	@Autowired
	private FlowersService flowersService;
	@Autowired
	private OrdersService ordersService;

	/**
	 * 获取购物车主页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/cart")
	public String getCartPage(HttpServletRequest request, HttpSession session) {
		String page = "mycart";
		if (session.getAttribute("username") == null) {
			return loginPage;
		}
		if (request.getAttribute("page") != null) {
			page = request.getAttribute("page").toString();
		}
		request.setAttribute("page", page);

		return cartPage;
	}

	/**
	 * 将鲜花加入到用户的购物车中
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/addtocart")
	public String addToCart(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		if (request.getParameter("flower_id") != null) {
			int flower_id = Integer.parseInt(request.getParameter("flower_id").toString());
			// 查询对应的鲜花
			List<Flowers> flowers = flowersService.queryFlowers(flower_id);

			List<Person> persons = usersService.queryPersonsByName(session.getAttribute("username").toString());
			// 用户存在并且对应的鲜花存在
			if (persons != null && persons.size() > 0 && flowers != null && flowers.size() > 0) {
				// 查找用户对应的购物车记录
				List<Cart> carts = usersService.queryCarts(persons.get(0).getName(), flowers.get(0).getFlower_id());

				for (int i = 0; i < flowers.size(); i++) {
					Date day = new Date();

					Cart usercart = new Cart();
					if (carts != null && carts.size() > 0) {
						// 购物车中已经存在该记录时，数量加1
						usercart = carts.get(0);
						usercart.setAmount(carts.get(0).getAmount() + 1);
						usersService.updateUsersCart(usercart);
					} else {

						// 购物车中不存在同样的记录
						usercart.setFlower_id(flowers.get(i));
						usercart.setPerson_id(persons.get(0));
						usercart.setDate(new java.sql.Date(day.getTime()));
						usercart.setAmount(1);
						usersService.addCart(usercart);
					}
				}
			}
		}
		request.setAttribute("page", "mycart");
		return cartPage;
	}

	/**
	 * 获取用户购物车内容
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/mycart")
	public String getUsersCart(HttpServletRequest request, HttpSession session) {

		String username = null;
		Object name = session.getAttribute("username");

		if (name != null && !"".equals(name.toString().trim())) {
			username = name.toString();
		} else {
			return loginPage;
		}

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

		count = usersService.getUsersCartCount(username);
		request.setAttribute("count", count);
		List<Cart> carts = usersService.listRangeUsersCart(username, start, pagesize);
		request.setAttribute("mycartList", carts);

		if ((carts != null && carts.size() == 0) || carts == null) {
			List<Flowers> recommendList = flowersService.listTopxFlowers(15);
			request.setAttribute("recommendList", recommendList);
		}
		if (carts != null && carts.size() < 10 && carts.size() > 0) {
			List<Flowers> recommendList = flowersService.listTopxFlowers(5);
			request.setAttribute("recommendList", recommendList);
		}

		return myCartPage;

	}

	/**
	 * 确认从购物车删除
	 * 
	 * @return
	 * @return String
	 */
	public String deleteCart() {
		return null;

	}

	/**
	 * 确认从购物车删除
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */

	@RequestMapping(value = "/deleteofcart")
	public String deleteCart(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return loginPage;
		}
		String flower_id = request.getParameter("delete_flower_id");
		int id = 0;
		if (flower_id != null) {
			try {
				id = Integer.parseInt(flower_id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		List<Cart> carts = usersService.queryCarts(session.getAttribute("username").toString(), id);
		if (carts != null && carts.size() > 0) {
			for (int i = 0; i < carts.size(); i++) {
				usersService.deleteCart(carts.get(i).getCart_id());
			}
		}

		request.setAttribute("page", "mycart");
		return cartPage;

	}

	/**
	 * 更新用户购物车
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/updatecart")
	public String updateCart(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return loginPage;
		}
		List<Cart> carts = usersService.listUsersCart(session.getAttribute("username").toString());
		if (carts != null && carts.size() > 0) {
			for (int i = 0; i < carts.size(); i++) {
				String amount = request.getParameter(String.valueOf(carts.get(i).getFlower_id().getFlower_id()));
				if (amount != null && !"".equals(amount)) {
					if (amount.equals("0") || amount == "0") {
						usersService.deleteCart(carts.get(i).getCart_id());
					} else {
						carts.get(i).setAmount(Integer.parseInt(amount));
						usersService.updateUsersCart(carts.get(i));
					}
				}
			}
		}
		List<Cart> carts2 = usersService.listRangeUsersCart(getUserName(session), 0, 6);
		request.setAttribute("curr", 1);
		long count = usersService.getUsersCartCount(getUserName(session));
		request.setAttribute("count", count);
		request.setAttribute("mycartList", carts2);
		if ((carts2 != null && carts2.size() == 0) || carts2 == null) {
			List<Flowers> recommendList = flowersService.listTopxFlowers(15);
			request.setAttribute("recommendList", recommendList);
		}
		if (carts2 != null && carts2.size() < 10 && carts2.size() > 0) {
			List<Flowers> recommendList = flowersService.listTopxFlowers(5);
			request.setAttribute("recommendList", recommendList);
		}
		return myCartPage;

	}

	/**
	 * 跳转到鲜花购买页面
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/buypage")
	public String buyPage(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return loginPage;
		}

		// 查询用户的地址
		List<Contact> contacts = usersService.queryUsersContactByName(session.getAttribute("username").toString());
		if (contacts != null && contacts.size() > 0) {
			request.setAttribute("contact", contacts.get(0).getTelephone());
			String address[] = contacts.get(0).getAddress().split(",", 4);
			// request.setAttribute("prov", address[0]);
			// request.setAttribute("city", address[1]);
			// request.setAttribute("area", address[2]);
			request.setAttribute("address", address[3]);
		}

		// 列出用户的购物车
		List<Cart> carts = usersService.listUsersCart(session.getAttribute("username").toString());

		List<Cart> list = new ArrayList<Cart>();
		float totalPrice = 0;
		if (carts != null && carts.size() > 0) {
			for (int i = 0; i < carts.size(); i++) {
				String[] check = request
						.getParameterValues("check" + String.valueOf(carts.get(i).getFlower_id().getFlower_id())); // 检查是否勾选
				if (check != null) {
					list.add(carts.get(i));
					totalPrice += carts.get(i).getAmount() * carts.get(i).getFlower_id().getPrice(); // 计算总价

				}
			}

		}
		List<Member> members = usersService.queryUsersMemberByName(session.getAttribute("username").toString());

		session.setAttribute("totalPrice", String.format("%.2f", totalPrice * members.get(0).getDiscount()));
		session.setAttribute("orderFlowersList", list);
		return buyPage;
	}

	/**
	 * 鲜花购买
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/buy")
	public String settleAccounts(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return loginPage;

		}
		// request.setAttribute("page", "mycart");
		String name = request.getParameter("name");
		if (name == null || "".equals(name.trim())) {
			request.setAttribute("msg", "收件人名称不能为空！");
			request.setAttribute("page", "buypage");
			return cartPage;
		}

		String contact = request.getParameter("contact");
		if (contact == null || "".equals(contact.trim())) {
			request.setAttribute("msg", "收件人联系方式不能为空！");
			request.setAttribute("page", "buypage");
			return cartPage;
		}
		String address = request.getParameter("address");
		if (address == null || "".equals(address.trim())) {
			request.setAttribute("msg", "收件人地址不能为空！");
			request.setAttribute("page", "buypage");
			return cartPage;
		}
		String note = request.getParameter("note");
		Object ordersList = session.getAttribute("orderFlowersList");

		if (ordersList != null) {
			@SuppressWarnings("unchecked")
			List<Cart> orders = (List<Cart>) ordersList;
			float price = 0;
			int amount = 0;
			// 查询用户会员信息
			List<Member> members = usersService.queryUsersMemberByName(session.getAttribute("username").toString());
			Member member = members.get(0);
			for (int i = 0; i < orders.size(); i++) {
				price = orders.get(i).getFlower_id().getPrice(); // 获取订单价格
				amount = orders.get(i).getAmount();// 获取购买量

				Flowers flowers = new Flowers();
				List<Flowers> flowersList = flowersService.queryFlowers(orders.get(i).getFlower_id().getFlower_id());// 获取订单对应的鲜花

				if (flowersList != null && flowersList.size() > 0) {
					flowers = flowersList.get(0);
				} else {
					request.setAttribute("msg", "很抱歉，购买失败");
					request.setAttribute("page", "mycart");
					return cartPage;
				}
				Orders orders1 = new Orders();
				Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String priceString = String.format("%.2f", price * amount * member.getDiscount()); // 打折后

				String orderId = GenerateOrderIDUtil.getOrderId(); // 获取订单编号
				// 设置订单信息
				orders1.setAddress(address);
				orders1.setContact(contact);
				orders1.setRecipient(name);
				orders1.setPerson_id(
						usersService.queryPersonsByName(session.getAttribute("username").toString()).get(0));
				orders1.setDate(Timestamp.valueOf(df.format(day)));
				orders1.setFlower_id(flowers);
				orders1.setAmount(amount);
				orders1.setOrder_id(orderId);
				orders1.setNote(note);
				orders1.setPrice(Float.valueOf(priceString));

				List<OrderStatusType> orderStatusTypes = ordersService.queryOrderStatusTypes(1); // 待发货订单

				if (orderStatusTypes != null && orderStatusTypes.size() > 0) {
//					OrdersCancel ordersCancel = new OrdersCancel();
//					ordersCancel.setOrder_status_id(orders1);
					orders1.setStatus_type_id(orderStatusTypes.get(0));
					ordersService.addOrders(orders1); // 新增订单
//					ordersService.setOrdersStatus(ordersCancel);// 设置订单状态
				}

				flowers.setQuantity(flowers.getQuantity() - orders1.getAmount());// 更新鲜花库存量
				flowersService.updateFlowers(flowers);
			}
			request.setAttribute("msg", "购买成功！感谢您的购买");
		}

		// request.setAttribute("page", "mycart");
		request.setAttribute("page", "mycart");
		return cartPage;
	}

	/**
	 * 订单查询
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/orders")
	public String orderSearch(HttpServletRequest request, HttpSession session) {
		request.setAttribute("page", "myorders");
		return servicePage;
	}

	/**
	 * 获取用户名
	 * 
	 * @param request
	 * @return String
	 */
	private String getUserName(HttpSession session) {
		Object username = session.getAttribute("username");
		if (username != null && !"".equals(username.toString().trim())) {
			return username.toString().trim();
		}
		return null;
	}

}

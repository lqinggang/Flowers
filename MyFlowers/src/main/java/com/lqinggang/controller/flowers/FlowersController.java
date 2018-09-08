package com.lqinggang.controller.flowers;

import java.util.ArrayList;
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
import com.lqinggang.common.constants.CommonConstants;
import com.lqinggang.common.controller.BaseController;
import com.lqinggang.common.entity.PageEntity;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.orders.Purchase;
import com.lqinggang.service.flowers.FlowersService;
import com.lqinggang.service.orders.OrdersService;

/**
 * 鲜花相关
 * 
 * @author LQingGang
 * @time 2018年3月27日 - 下午2:14:31
 */
@Controller
@RequestMapping(value = "/flowers")
public class FlowersController extends BaseController {

	private static String indexbodyPage = getViewPath("/pages/jsp/common/indexbody");
	private static String searchflowersPage = getViewPath("/pages/jsp/flowers/searchflowers");
	private static String flowerInfoPage = getViewPath("/pages/jsp/flowers/flower");
	// private static String leftPage = "common/left.jsp";
	private static String flowerPage = getViewPath("/pages/jsp/flowers/flowerspage");

	private static Logger logger = LoggerFactory.getLogger(FlowersController.class);

	@Autowired
	private FlowersService flowersService;
	@Autowired
	private OrdersService ordersService;

	// @RequestMapping()
	// public ModelAndView flowersPage() {
	// return new ModelAndView(indexbodyPage);
	// }

	/**
	 * 鲜花页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flowerpage")
	public String getFlowerPage(HttpServletRequest request) {
		String page = indexbodyPage;
		if (request.getParameter("page") != null) {
			page = request.getParameter("page").toString();
		}
		request.setAttribute("page", page);
		logger.info("进入鲜花页面");
		return flowerPage;
	}

	/**
	 * 鲜花展示页面
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping(value = "/searchflowers")
	public String searchFlowers(HttpServletRequest request, HttpServletResponse response) {

		// request.setAttribute("left", leftPage);

		String flowerid = request.getParameter("id");

		if (flowerid != null) {
			List<Flowers> flowers = flowersService.queryFlowers(Integer.parseInt(flowerid));
			if (flowers != null) {
				for (int i = 0; i < flowers.size();) {
					request.setAttribute("searchresultList", flowers);
					request.setAttribute("flowerid", flowers.get(i).getFlower_id());
					String[] content = flowers.get(i).getContent().split(",", 2);
					List<String> contentList = new ArrayList<String>();
					for (int j = 0; j < content.length; j++) {
						contentList.add(content[i]);
					}
					request.setAttribute("flowerscontentList", contentList);
					int start = 0;
					int pagesize = 3; // 每页显示记录数
					int currpage = 0;
					String curr = request.getParameter("curr");
					if (curr != null && !"".equals(curr.trim())) {
						currpage = Integer.valueOf(curr) - 1;
						start = currpage * pagesize;
					}

					request.setAttribute("curr", currpage + 1); // 设置当前页
					// 获取鲜花评价记录总数
					request.setAttribute("count",
							ordersService.getPurchasesCountByFlowersId(flowers.get(i).getFlower_id()));
					// 获取鲜花评价记录
					List<Purchase> purchases = ordersService.listPurchasesByFlowersId(flowers.get(i).getFlower_id(),
							start, pagesize);
					if (purchases != null) {
						for (int j = 0; j < purchases.size(); j++) { // 将用户名传化成l****g形式

							char[] name = purchases.get(j).getOrder_id().getPerson_id().getName().toCharArray();

							String newName = String.valueOf(name[0]) + String.valueOf(name[1]) + "*****"
									+ String.valueOf(name[name.length - 2]) + String.valueOf(name[name.length - 1]);
							purchases.get(j).getOrder_id().getPerson_id().setName(newName);
						}
					}
					request.setAttribute("purchasesList", purchases); // 鲜花评价
					return flowerInfoPage;
				}

			} else {
				// 没有找到
				return indexbodyPage;
			}
		}
		return indexbodyPage;

	}

	/**
	 * 鲜花搜索结果页面
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping(value = "/search")
	public String searchFlowersByName(HttpServletRequest request, HttpServletResponse response) {
		// request.setAttribute("left", leftPage);

		request.setAttribute("search", "search");
		String flowersname = request.getParameter("name");// 关键字或类别名
		String type = request.getParameter("type");// 搜索类型：关键字或类别
		request.setAttribute("name", flowersname);
		request.setAttribute("type", type);

		long count = 0; // 总记录数
		long pagesize = 30;// 每页显示记录数
		long currpage = 0;// 当前页
		long start = 0; // 搜索记录开始位置
		String curr = request.getParameter("curr");// 获取当前页，当前页从1开始
		if (curr != null && !"".equals(curr.trim())) {
			currpage = Integer.valueOf(curr) - 1;
			start = currpage * pagesize; // 开始位置
		}

//		System.out.println(curr);

		if (flowersname != null && !"".equals(flowersname.trim())) {
			List<Flowers> flowers3 = new ArrayList<Flowers>();
			if (type != null && type.equals("category")) {
				int categoryid = 0;
				request.setAttribute("category", type);

				categoryid = CommonConstants.categotyMap.get(flowersname);
				flowers3 = flowersService.listRangeFlowersByCategoryId(categoryid, start, pagesize);
				count = flowersService.getCategoryFlowersCount(categoryid);
			} else {

				flowers3 = flowersService.listFlowersByFlowersName(flowersname, start, pagesize);
				count = flowersService.getFlowersCount(flowersname);
			}

			request.setAttribute("count", count);// 将记录总数回传到页面
			request.setAttribute("curr", currpage + 1);// 将当前页回传到页面
			request.setAttribute("searchList", flowers3);

			// 没有找到相对应的结果或相对应的结果不足
			if (flowers3 == null || (flowers3 != null && flowers3.size() <= 25)) {
				List<Flowers> youlike = new ArrayList<Flowers>();
				if (flowers3.size() <= 0) {
					youlike = flowersService.listTopxFlowers(25);
				} else {
					// 每一页只能显示30条记录，为了不是页面出现滚动条只能显示25条
					youlike = flowersService.listTopxFlowers((30 - flowers3.size()) / 5 * 5 - 5);
				}
				request.setAttribute("youlikeList", youlike);
			}

		} else {
			request.setAttribute("count", flowersService.getFlowersCount());
			request.setAttribute("curr", currpage + 1);
			request.setAttribute("searchList", flowersService.listRangeFlowers(start, pagesize));
			request.setAttribute("msg", "没有找到相对应的鲜花");

		}
		return searchflowersPage;

	}

	/**
	 * 获取鲜花展示主页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/indexbody")
	public String indexBody(HttpServletRequest request) {
		// request.setAttribute("left", leftPage);
		return indexbodyPage;

	}

	/**
	 * 鲜花按类别搜索
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping(value = "/category")
	public String searchFlowersByCatrgory(HttpServletRequest request, HttpServletResponse response) {
		// request.setAttribute("left", leftPage);
		request.setAttribute("search", "category");
		// 获取传递的参数
		String category = request.getParameter("name");
		request.setAttribute("name", category); // 回转给下一页
		// String cateString = request.getParameter("id");

		int categoryid = 0;
		if (category != null || "".equals(category)) {
			categoryid = CommonConstants.categotyMap.get(category);
			// 获取鲜花类型名对应的类型ID
		}

		List<Flowers> flowers3 = flowersService.listFlowersByCategoryId(categoryid);

		// 测试用------------------
		List<Flowers> flowers = new ArrayList<Flowers>();
		// for (int i = 0; i < 10; i++) {
		flowers.addAll(flowers3);// 使数据“显得”相对较多写
		// }

		// 分页------------------
		long page = 1;
		String str_page = request.getParameter("page");

		// ---------------------------
		PageEntity<Flowers> pageEntity = new PageEntity<Flowers>();// 当前页默认是1
		pageEntity.setPageSize(30); // 设置每页显示数据条数
		pageEntity.setTotalRecords(flowers.size());// 设置总需要显示的记录数
		pageEntity.setTotalPages();// 设置总页数

		if (str_page != null) {
			int pag = Integer.parseInt(str_page);
			if (pag > 1) {
				if (pag >= pageEntity.getTotalPages()) {
					page = pageEntity.getTotalPages();
				} else {
					page = pag;
				}
			}
		}
		pageEntity.setCurrentPage(page);

		List<Flowers> flowers2 = new ArrayList<Flowers>();

		for (long i = (pageEntity.getCurrentPage() - 1) * (pageEntity.getPageSize()); i < (pageEntity.getCurrentPage())
				* pageEntity.getPageSize() && i < flowers.size(); i++) {
			flowers2.add(flowers.get((int) i));
		}

		request.setAttribute("pageEntity", pageEntity);
		// 分页-------------------
		if (flowers != null && flowers.size() > 0) {
			request.setAttribute("searchList", flowers2);
		} else {
			request.setAttribute("msg", "没有找到该类别的鲜花");
		}
		// 没有找到相对应的结果或相对应的结果不足
		if (flowers2 == null || (flowers2 != null && flowers2.size() < 25)) {
			List<Flowers> youlike = new ArrayList<Flowers>();
			if (flowers2.size() <= 0) {
				youlike = flowersService.listTopxFlowers(25);
			} else {
				// 每一页只能显示30条记录，为了不是页面出现滚动条只能显示25条
				youlike = flowersService.listTopxFlowers((30 - flowers2.size()) / 5 * 5 - 5);
			}
			request.setAttribute("youlikeList", youlike);
		}

		return searchflowersPage;

	}

	/**
	 * 鲜花多条件分类搜索
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/classify")
	public String searchByClassify(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// request.setAttribute("left", leftPage);
		request.setAttribute("search", "classify");
		// 获取分类筛选的条件
		String color = request.getParameter("searchcolor");
		String category = request.getParameter("searchcategory");
		String amount = request.getParameter("searchamount");

		request.setAttribute("searchcolor", color);
		request.setAttribute("searchcategory", category);
		request.setAttribute("searchamount", amount);

		// map中的key必须是....字段
		Map<Object, Object> map = new HashMap<Object, Object>();
		if (color != null && !"".equals(color.trim())) {
			map.put("color", request.getParameter("searchcolor"));
		}
		if (category != null && !"".equals(category.trim())) {
			map.put("category", request.getParameter("searchcategory"));
		}
		if (amount != null && !"".equals(amount.trim())) {
			map.put("amount", Integer.parseInt(request.getParameter("searchamount")));
		}

		long count = 0; // 总记录数
		long pagesize = 30;// 每页显示记录数
		long currpage = 0;// 当前页
		long start = 0; // 搜索记录开始位置
		String curr = request.getParameter("curr");// 获取当前页
		if (curr != null && !"".equals(curr.trim())) {
			currpage = Integer.valueOf(curr) - 1;
			start = currpage * pagesize;
		}

		// 按照分类条件查找鲜花
		// List<Flowers> flowers3 = flowersService.classify(map);
		List<Flowers> flowers3 = flowersService.listRangeClassify(map, start, pagesize);
		count = flowersService.getClassifyCount(map);
		request.setAttribute("count", count);// 将记录总数回传到页面
		request.setAttribute("curr", currpage + 1);// 将当前页回传到页面

		// 返回查找结果
		request.setAttribute("searchList", flowers3);

		// 没有找到相对应的结果或相对应的结果不足
		if (flowers3 == null || (flowers3 != null && flowers3.size() < 25)) {
			List<Flowers> youlike = new ArrayList<Flowers>();
			if (flowers3 == null) {
				youlike = flowersService.listTopxFlowers(25);
			} else {
				// 每一页只能显示30条记录，为了不是页面出现滚动条只能显示25条
				youlike = flowersService.listTopxFlowers((30 - flowers3.size()) / 5 * 5 - 5);
			}
			request.setAttribute("youlikeList", youlike);
		}
		return searchflowersPage;
	}

}

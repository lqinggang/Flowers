package com.lqinggang.controller.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lqinggang.common.constants.CommonConstants;
import com.lqinggang.common.controller.BaseController;
import com.lqinggang.common.entity.PageEntity;
import com.lqinggang.common.util.ConvertListToPageJsonUtil;
import com.lqinggang.common.util.DataVerificationUtil;
import com.lqinggang.common.util.ExportToExcelUtil;
import com.lqinggang.common.util.FileUtil;
import com.lqinggang.common.util.IpAddressUtil;
import com.lqinggang.entity.common.AdminLogs;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.flowers.FlowersCategory;
import com.lqinggang.entity.orders.Purchase;
import com.lqinggang.service.flowers.FlowersService;
import com.lqinggang.service.orders.OrdersService;
import com.lqinggang.service.users.AdminService;

/**
 * 后台鲜花相关管理
 * 
 * @author LQingGang
 * @time 2018年4月16日 - 下午1:52:48
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminFlowersController extends BaseController {

	private static String flowersPage = getViewPath("/admin/flowers/flowersinfo");
	private static String flowerSearchResultPage = getViewPath("/admin/flowers/flowersearch");
	private static String addFlowersPage = getViewPath("/admin/flowers/addflower");
	private static String evaluationPage = getViewPath("/admin/orders/evaluation");
	private static String contentInfoPage = getViewPath("/admin/flowers/contentinfo");

	private static Logger logger = LoggerFactory.getLogger(AdminFlowersController.class);

	@Autowired
	private FlowersService flowersService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private OrdersService ordersService;

	/**
	 * 鲜花信息页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flowersinfoPage")
	public String flowersInfoPage(HttpServletRequest request) {
		return flowersPage;
	}

	/**
	 * 获取鲜花信息数据
	 * 
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/flowersinfo")
	public @ResponseBody Map<String, Object> getFlowersInfo(HttpServletRequest request, HttpSession session) {

		// 分页信息
		PageEntity<Flowers> pageEntity = new PageEntity<Flowers>();
		String currentPage = request.getParameter("curr");

		if (currentPage != null && !"".equals(currentPage.trim())) { // 当前页不为空
			pageEntity.setCurrentPage(Integer.valueOf(currentPage));
		} else {
			pageEntity.setCurrentPage(1);
		}

		pageEntity.setPageSize(50);// 每页显示数据条数

		long count = flowersService.getFlowersCount(); // 总记录数
		long start = 0;
		if (pageEntity.getCurrentPage() != 1) {
			start = (pageEntity.getCurrentPage() - 1) * pageEntity.getPageSize();// 当前页从1开始
		}

		long size = pageEntity.getPageSize();

		List<Flowers> flowersList = flowersService.listRangeFlowers(start, size); // 获取所有鲜花信息

		JSONArray flowersInfo = ConvertListToPageJsonUtil.flowersInfoToJsonArray(flowersList);// 将鲜花信息转化成JSONArray

		pageEntity.setTotalRecords(count);// 总记录数
		pageEntity.setTotalPages();// 总页数
		pageEntity.setList(flowersList);// 数据

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", count);
		jsonObject.put("data", flowersInfo);
		request.setAttribute("count", count);

		logger.info(session.getAttribute("adminName").toString() + "获取鲜花信息,时间：" + new Date().getTime());

		return jsonObject;

	}

	/**
	 * 导出鲜花信息
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flowers/export")
	public String exportFlowersInfo(HttpServletRequest request, HttpSession session) {
		PageEntity<Flowers> pageEntity = new PageEntity<Flowers>();
		List<Flowers> flowersList = new ArrayList<Flowers>();

		String currentPage = request.getParameter("curr");

		if (currentPage != null && !"".equals(currentPage.trim())) {
			try {
				pageEntity.setCurrentPage(Integer.valueOf(currentPage));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			pageEntity.setCurrentPage(1);
		}
		pageEntity.setPageSize(50);// 每页显示数据条数

		long start = 0;
		if (pageEntity.getCurrentPage() != 1) {
			start = (pageEntity.getCurrentPage() - 1) * pageEntity.getPageSize();// 当前页从1开始
		}

		long size = pageEntity.getPageSize();

		flowersList = flowersService.listRangeFlowers(start, size); // 获取所有鲜花信息

		Random r = new Random();
		Date d = new Date();
		String filename = String.valueOf(r.nextInt(99999999) + d.getTime());

		// try {
		String path = request.getServletContext().getRealPath(CommonConstants.EXPORT_LOCATION + "/flowers/");
		if (ExportToExcelUtil.exportFlowersDataToExcel(path, filename, session.getAttribute("adminName").toString(),
				flowersList)) {
			request.setAttribute("export_msg", "导出成功");
			logger.info(session.getAttribute("adminName").toString() + "导出鲜花信息,时间：" + new Date().getTime());

		} else {

			request.setAttribute("export_msg", "导出失败");
			logger.info(session.getAttribute("adminName").toString() + "导出鲜花信息失败,时间：" + new Date().getTime());
		}
		return flowersPage;
	}

	/**
	 * 新增鲜花页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/addflowerPage")
	public String addflowerPage(HttpServletRequest request, HttpSession session) {
		List<FlowersCategory> flowersCategories = flowersService.listAllFlowersCategories();
		request.setAttribute("categoryList", flowersCategories);
		logger.info(session.getAttribute("adminName").toString() + "进入添加鲜花信息页面,时间：" + new Date().getTime());
		return addFlowersPage;
	}

	/**
	 * 鲜花文字描述信息页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flower/contentinfo")
	public String getFlowerContentInfoPage(HttpServletRequest request, HttpSession session) {
		logger.info(session.getAttribute("adminName").toString() + "进入鲜花文字描述信息页面,时间：" + new Date().getTime());
		return contentInfoPage;
	}

	/**
	 * 新增鲜花
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/addflower")
	public String addflower(HttpServletRequest request, HttpSession session) {
		String name = request.getParameter("name");
		if (name == null || (name != null && "".equals(name.trim()))) {
			request.setAttribute("msg", "鲜花名称不能为空");
			return addFlowersPage;
		}
		String category = request.getParameter("category");
		if (category == null || (category != null && "".equals(category.trim()))) {
			request.setAttribute("msg", "鲜花类别不能为空");
			return addFlowersPage;
		}
		String keyword = request.getParameter("keyword");
		String color = request.getParameter("color");
		String amount = request.getParameter("amount");
		String origin = request.getParameter("origin");
		String flowerimage = request.getParameter("flowerimage");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String content1 = request.getParameter("content1");
		String content2 = request.getParameter("content2");
		String content_info = request.getParameter("content_info");

		Flowers flower = new Flowers();
		List<FlowersCategory> flowersCategories = flowersService
				.queryFlowersCategoryByCategoryId(Integer.valueOf(category));
		if (flowersCategories != null && flowersCategories.size() > 0) {
			flower.setCategory_id(flowersCategories.get(0));
		} else {
			flower.setCategory_id(flowersService.queryFlowersCategoryByCategoryId(6).get(0));
		}
		flower.setName(name);
		flower.setKeyword(keyword);
		flower.setColor(color);

		try {
			flower.setAmount(Integer.valueOf(amount));
			flower.setQuantity(Integer.valueOf(quantity));
			flower.setPrice(Float.valueOf(price));
		} catch (NumberFormatException e) {
			request.setAttribute("msg", "数值不正确，请输入正确数字");
			logger.info("数字格式化不正确,时间：" + new Date().getTime());
			return addFlowersPage;
		}

		flower.setOrigin(origin);
		flower.setImage(flowerimage);
		flower.setDescription(description);
		String content = (content1 == null ? content2 : content1 + (content2 == null ? null : "," + content2));
		flower.setContent(content);
		flower.setContent_info(content_info);
		flowersService.addFlowers(flower);
		addLogs(request, session, session.getAttribute("adminName").toString() + "添加鲜花信息");
		request.setAttribute("msg", "添加成功");
		logger.info(session.getAttribute("adminName").toString() + "新增鲜花" + name + ",时间：" + new Date().getTime());
		return flowersPage;
	}

	/**
	 * 更新鲜花名称
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flower/update/name")
	public String updateFlowerName(HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if (id != null && name != null) {
			int flowerid = Integer.valueOf(id);
			List<Flowers> flower = flowersService.queryFlowers(flowerid);
			if (flower != null) {
				flower.get(0).setName(name);
				flowersService.updateFlowers(flower.get(0));
				addLogs(request, session, session.getAttribute("adminName").toString() + "更新鲜花信息");

			}
		} else {
			request.setAttribute("msg", "修改失败");
		}
		addLogs(request, session, session.getAttribute("adminName").toString() + "更新鲜花" + id + "名称");
		logger.info(session.getAttribute("adminName").toString() + "更新鲜花" + id + "名称,时间：" + new Date().getTime());
		return flowersPage;
	}

	/**
	 * 更新鲜花枝数
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flower/update/amount")
	public String updateFlowerAmount(HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("id");
		String amount = request.getParameter("amount");
		if (id != null && amount != null) {
			int flowerid = Integer.valueOf(id);
			List<Flowers> flower = flowersService.queryFlowers(flowerid);
			if (flower != null) {
				try {
					Integer.valueOf(amount);
				} catch (Exception e) {
					request.setAttribute("msg", "修改失败");
					return flowersPage;
				}
				flower.get(0).setAmount(Integer.valueOf(amount));
				flowersService.updateFlowers(flower.get(0));
			}
		} else {
			request.setAttribute("msg", "修改失败");
		}
		addLogs(request, session, session.getAttribute("adminName").toString() + "更新鲜花" + id + "枝数");
		logger.info(session.getAttribute("adminName").toString() + "更新鲜花" + id + "枝数,时间：" + new Date().getTime());

		return flowersPage;
	}

	/**
	 * 更新鲜花颜色
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flower/update/color")
	public String updateFlowerColor(HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("id");
		String color = request.getParameter("color");
		if (id != null && color != null) {
			int flowerid = Integer.valueOf(id);
			List<Flowers> flower = flowersService.queryFlowers(flowerid);
			if (flower != null) {
				flower.get(0).setColor(color);
				flowersService.updateFlowers(flower.get(0));
			}
		} else {
			request.setAttribute("msg", "修改失败");
		}
		addLogs(request, session, session.getAttribute("adminName").toString() + "更新鲜花" + id + "颜色");
		logger.info(session.getAttribute("adminName").toString() + "更新鲜花" + id + "颜色,时间：" + new Date().getTime());

		return flowersPage;
	}

	/**
	 * 更新鲜花产地
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flower/update/origin")
	public String updateFlowerOrigin(HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("id");
		String origin = request.getParameter("origin");
		if (id != null && origin != null) {
			int flowerid = Integer.valueOf(id);
			List<Flowers> flower = flowersService.queryFlowers(flowerid);
			if (flower != null) {
				flower.get(0).setOrigin(origin);
				flowersService.updateFlowers(flower.get(0));
			}
		} else {
			request.setAttribute("msg", "修改失败");
		}
		addLogs(request, session, session.getAttribute("adminName").toString() + "更新鲜花" + id + "产地");
		logger.info(session.getAttribute("adminName").toString() + "更新鲜花" + id + "产地,时间：" + new Date().getTime());
		return flowersPage;
	}

	/**
	 * 更新鲜花搜索关键字
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flower/update/keyword")
	public String updateFlowerKeyword(HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("id");
		String keyword = request.getParameter("keyword");
		if (id != null && keyword != null) {
			int flowerid = Integer.valueOf(id);
			List<Flowers> flower = flowersService.queryFlowers(flowerid);
			if (flower != null) {
				flower.get(0).setKeyword(keyword);
				flowersService.updateFlowers(flower.get(0));
			}
		} else {
			request.setAttribute("msg", "修改失败");
		}
		addLogs(request, session, session.getAttribute("adminName").toString() + "更新鲜花" + id + "搜索关键字");
		logger.info(session.getAttribute("adminName").toString() + "更新鲜花" + id + "搜索关键字,时间：" + new Date().getTime());
		return flowersPage;
	}

	/**
	 * 更新鲜花库存量
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flower/update/quantity")
	public String updateFlowerQuantity(HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("id");
		String quantity = request.getParameter("quantity");
		if (id != null && quantity != null) {
			try {
				Integer.valueOf(quantity);
			} catch (NumberFormatException e) {
				request.setAttribute("msg", "修改失败");
				return flowersPage;
			}
			int flowerid = Integer.valueOf(id);
			List<Flowers> flower = flowersService.queryFlowers(flowerid);
			if (flower != null) {
				flower.get(0).setQuantity(Integer.valueOf(quantity));
				flowersService.updateFlowers(flower.get(0));
			}
		} else {
			request.setAttribute("msg", "修改失败");
		}
		addLogs(request, session, session.getAttribute("adminName").toString() + "更新鲜花" + id + "库存量");
		logger.info("修改鲜花" + id + "库存量");
		return flowersPage;
	}

	/**
	 * 更新鲜花花语
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flower/update/description")
	public String updateFlowerDescription(HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("id");
		String description = request.getParameter("description");
		if (id != null && description != null) {
			if (!DataVerificationUtil.isInteger(id)) {
				request.setAttribute("msg", "修改失败");
				return flowersPage;
			}
			int flowerid = Integer.valueOf(id);
			List<Flowers> flower = flowersService.queryFlowers(flowerid);
			if (flower != null) {
				flower.get(0).setDescription(description);
				flowersService.updateFlowers(flower.get(0));
			}
		} else {
			request.setAttribute("msg", "修改失败");
		}
		addLogs(request, session, session.getAttribute("adminName").toString() + "更新鲜花" + id + "花语");
		logger.info("修改鲜花" + id + "花语");
		return flowersPage;
	}

	/**
	 * 更新鲜花价格
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flower/update/price")
	public String updateFlowerPrice(HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("id");
		String price = request.getParameter("price");
		if (id != null && price != null) {
			if (!DataVerificationUtil.isInteger(id)) {
				request.setAttribute("msg", "修改失败");
				logger.info("鲜花ID不正确！");
				return flowersPage;
			}
			int flowerid = Integer.valueOf(id);
			List<Flowers> flower = flowersService.queryFlowers(flowerid);
			if (flower != null) {

				try {
					Float.valueOf(price);
				} catch (NumberFormatException e) {
					request.setAttribute("msg", "修改失败");
					logger.info("鲜花价格格式不正确！");
					return flowersPage;
				}

				flower.get(0).setPrice(Float.valueOf(price));
				flowersService.updateFlowers(flower.get(0));
			}
		} else {
			request.setAttribute("msg", "修改失败");
		}
		addLogs(request, session, session.getAttribute("adminName").toString() + "更新鲜花" + id + "价格");
		logger.info("修改鲜花" + id + "价格");
		return flowersPage;
	}

	/**
	 * 删除鲜花信息
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flowers/delete")
	public String deleteFlowers(HttpServletRequest request, HttpSession session) {
		String ds = request.getParameter("ds");

		// System.out.println(ds);
		// System.out.println("flowers:" + ds == null ? "NULL" :
		// JSONArray.parseArray(ds).size());

		JSONArray jsonArray = JSONArray.parseArray(ds);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject flower = jsonArray.getJSONObject(i);
			String idString = flower.getString("flowerid");
			if (idString != null && !"".equals(idString.trim())) {
				try {
					int flowerid = Integer.valueOf(idString);
					flowersService.deleteFlowersByFlowerId(flowerid);
					request.setAttribute("msg", "删除成功！");
					logger.info("删除鲜花成功");
				} catch (NumberFormatException e) {
					logger.warn("删除鲜花出错");
					request.setAttribute("msg", "删除出错！");
				}

			}
		}
		addLogs(request, session, session.getAttribute("adminName").toString() + "删除鲜花信息");

		logger.info(session.getAttribute("adminName").toString() + "删除鲜花信息");
		return flowersPage;
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/upload")
	public @ResponseBody Map<String, Object> uploadImage(HttpServletRequest request, HttpSession session,
			MultipartFile file) {

		String type = "flowers";
		if (request.getParameter("type") != null) {
			type = "content";
		}

		// 原始文件名称
		String oldName = file.getOriginalFilename();

		// 上传文件路径
		String path = request.getServletContext().getRealPath(CommonConstants.FLOWRES_IMG_URL + "/uploadPic/" + type);
		// 改变文件名
		String fileName = FileUtil.fileRename(oldName);
		// 图片文件名
		String imageName = fileName;
		// 创建年月文件夹
		Calendar date = Calendar.getInstance();
		File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));

		// 图片路径
		String relativePath = CommonConstants.FLOWRES_IMG_URL + "/uploadPic/" + type + File.separator + dateDirs
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
		addLogs(request, session, session.getAttribute("adminName").toString() + "上传图片" + imageName);
		logger.info("图片上传成功");
		return jsonObject;
	}

	/**
	 * 鲜花搜索页面
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/flowers/searchpage")
	public String flowerSearchPage(HttpServletRequest request) {
		String flowerid = request.getParameter("flowerid");
		if (flowerid != null && !"".equals(flowerid.trim())) {
			try {
				Integer.valueOf(flowerid);
			} catch (NumberFormatException e) {
				request.setAttribute("msg", "鲜花编号不正确");
				logger.warn("鲜花编号不正确");
				return flowersPage;
			}
			request.setAttribute("flowerid", flowerid.toString());
			return flowerSearchResultPage;
		}
		logger.info("鲜花搜索");
		return flowersPage;
	}

	/**
	 * 获取鲜花信息数据
	 * 
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/flowers/search")
	public @ResponseBody Map<String, Object> searchFlower(HttpServletRequest request, HttpSession session) {

		String flowerid = request.getParameter("flowerid");
		JSONArray flowersInfo = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		if (flowerid != null && !"".equals(flowerid.trim())) {
			int id = Integer.valueOf(flowerid);
			List<Flowers> flowersList = flowersService.queryFlowers(id);
			flowersInfo = ConvertListToPageJsonUtil.flowersInfoToJsonArray(flowersList);// 将鲜花信息转化成JSONArray
		}

		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", flowersInfo.size());
		jsonObject.put("data", flowersInfo);
		logger.info("获取鲜花信息");
		return jsonObject;

	}

	/**
	 * 删除鲜花评价信息
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/flowers/evaluation/delete")
	public String flowersEvaluationDelete(HttpServletRequest request, HttpSession session) {

		String ds = request.getParameter("ds");

		// System.out.println(ds);
		// System.out.println("flowers:" + ds == null ? "NULL" :
		// JSONArray.parseArray(ds).size());

		JSONArray jsonArray = JSONArray.parseArray(ds);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject flower = jsonArray.getJSONObject(i);
			String idString = flower.getString("orderid");
			if (idString != null && !"".equals(idString.trim())) {
				try {
					ordersService.deletePurchaseByOrderId(idString);
					request.setAttribute("msg", "删除成功！");
					logger.info("删除鲜花成功");
					addLogs(request, session, session.getAttribute("adminName").toString() + "删除鲜花评价信息");
				} catch (NumberFormatException e) {
					logger.warn("删除鲜花出错");
					request.setAttribute("msg", "删除出错！");
				}

			}
		}

		logger.info(session.getAttribute("adminName").toString() + "删除鲜花评价信息");
		return flowersPage;
	}

	/**
	 * 鲜花评价信息导出
	 * 
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "flowers/evaluation/export")
	public String flowersEvaluationExport(HttpServletRequest request, HttpSession session) {

		String currentPage = request.getParameter("curr");

		long curr = 0;
		long start = 0;
		long pagesize = 50;

		if (currentPage != null && !"".equals(currentPage.trim())) {
			curr = Integer.valueOf(currentPage) - 1;
			start = curr * pagesize;

		}

		List<Purchase> purchasesList = ordersService.listRangePurchases(start, pagesize);

		Random r = new Random();
		Date d = new Date();
		String filename = String.valueOf(r.nextInt(99999999) + d.getTime());

		try {
			String path = request.getServletContext().getRealPath(CommonConstants.EXPORT_LOCATION + "/evaluation/");
			ExportToExcelUtil.exportEvaluationToExcel(path, filename, session.getAttribute("adminName").toString(),
					purchasesList);
			request.setAttribute("export_msg", "导出成功");
			logger.info(session.getAttribute("adminName").toString() + "导出鲜花评价信息,时间：" + new Date().getTime());
		} catch (Exception e) {
			request.setAttribute("export_msg", "导出失败");
			logger.info(session.getAttribute("adminName").toString() + "导出鲜花评价信息失败,时间：" + new Date().getTime());
			e.printStackTrace();
		}

		return evaluationPage;

	}

	/**
	 * 添加日志信息
	 * 
	 * @param request
	 * @param session
	 * @param content
	 *            日志内容
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

package com.lqinggang.service.impl.flowers;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lqinggang.dao.flowers.FlowersCategoryDao;
import com.lqinggang.dao.flowers.FlowersDao;
import com.lqinggang.dao.flowers.SalesDao;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.flowers.FlowersCategory;
import com.lqinggang.entity.flowers.Sales;
import com.lqinggang.entity.orders.Purchase;
import com.lqinggang.service.flowers.FlowersService;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 上午9:51:09
 */
@Transactional
@Service(value = "flowersService")
public class FlowersServiceImpl implements FlowersService {

	@Autowired
	private FlowersDao flowersDao;
	@Autowired
	private SalesDao salesDao;
	@Autowired
	private FlowersCategoryDao flowersCategoryDao;

	@Override
	public void addFlowers(Flowers flowers) {
		flowersDao.addFlower(flowers);

	}

	@Override
	public void addSales(Sales sales) {
		salesDao.addSales(sales);

	}

	@Override
	public void addFlowersCategory(FlowersCategory flowersCategory) {
		flowersCategoryDao.addFlowersCategory(flowersCategory);
	}

	@Override
	public void deleteFlowersByFlowerId(int flowers_id) {
		flowersDao.delete(flowers_id);

	}

	@Override
	public void deleteFlowersCategoryByCatagoryId(int category_id) {
		flowersCategoryDao.deleteFlowersCategory(category_id);
	}

	@Override
	public List<Flowers> queryFlowers(int flowers_id) {
		return flowersDao.queryFlowersByFlowersId(flowers_id);
	}

	@Override
	public List<FlowersCategory> queryFlowersCategoryByFlowersId(int flowers_id) {
		try {
			List<Flowers> flowers = flowersDao.queryByHql(" from Flowers as p where p.flower_id='" + flowers_id + "'");
			if (flowers != null && flowers.size() > 0) {
				for (int i = 0; i < flowers.size();) {
					return flowersCategoryDao.queryFlowersCategory(flowers.get(i).getCategory_id().getCategory_id());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sales> querySalesByFlowersId(int flowers_id) {
		return salesDao.querySalesByFlowersId(flowers_id);
	}

	@Override
	public List<Sales> querySalesByUsersId(int users_id) {
		return salesDao.querySalesByUsersId(users_id);
	}

	@Override
	public void updateFlowers(Flowers flowers) {
		flowersDao.updateFlowers(flowers);
	}

	@Override
	public void updateFlowersCategory(FlowersCategory flowersCategory) {
		flowersCategoryDao.updateFlowersCategory(flowersCategory);
	}

	@Override
	public int countFlowersCategory() {
		return (int) flowersCategoryDao.getCount();
	}

	@Override
	public List<FlowersCategory> listAllFlowersCategories() {
		String hql = " from FlowersCategory";
		return flowersCategoryDao.queryByHql(hql);
	}

	@Override
	public List<Flowers> listTopxFlowers(int x) {
		String hql = " from Flowers ";
		return flowersDao.queryTopXByHql(hql, x);
	}

	@Override
	public List<FlowersCategory> queryFlowersCategoryByCategoryName(String categoryname) {
		return flowersCategoryDao.queryFlowersCategory(categoryname);
	}

	@Override
	public List<Flowers> findFlowersByFlowersCategoryName(String categoryname) {
		if (categoryname != null || "".equals(categoryname)) {
			List<FlowersCategory> flowersCategory = flowersCategoryDao.queryFlowersCategory(categoryname);
			if (flowersCategory != null && flowersCategory.size() > 0) {
				for (int i = 0; i < flowersCategory.size();) {
					List<Flowers> flowers = flowersDao
							.findFlowersByFlowersCategoryId(flowersCategory.get(0).getCategory_id());
					return flowers;
				}
			}
		}
		return null;
	}

	@Override
	public List<Purchase> queryPurchaseByFlowersId(int flowers_id) {
		// List<Sales> sales = salesDao.querySalesByFlowersId(flowers_id);
		// if (sales != null && sales.size() > 0) {
		// // List<Purchase> purchases =
		// }
		return null;
	}

	@Override
	public List<Flowers> listFlowersByCategoryId(int category_id) {
		return flowersDao.findFlowersByFlowersCategoryId(category_id);
	}

	@Override
	public List<Flowers> listRangeFlowersByCategoryId(int category_id, long start, long size) {
		return flowersDao.listRangeFlowers(category_id, start, size);
	}

	@Override
	public List<Flowers> listFlowersByName(String flowers_name) {
		return flowersDao.queryFlowersByFlowersName(flowers_name);
	}

	@Override
	public List<Flowers> classify(Map<Object, Object> map) {
		return flowersDao.classify(map);
	}

	@Override
	public List<Flowers> listRangeClassify(Map<Object, Object> map, long start, long size) {
		return flowersDao.listRangeClassify(map, start, size);

	}

	@Override
	public long getClassifyCount(Map<Object, Object> map) {
		return flowersDao.getClassifyCount(map);
	}

	@Override
	public List<Flowers> listAllFlowers() {
		return flowersDao.listAllFlowers();
	}

	@Override
	public List<Flowers> listRangeFlowers(long start, long size) {
		return flowersDao.listRange(Flowers.class, start, size);
	}

	@Override
	public long getFlowersCount() {
		return flowersDao.getCount();
	}

	@Override
	public List<FlowersCategory> queryFlowersCategoryByCategoryId(int category_id) {
		return flowersCategoryDao.queryFlowersCategory(category_id);
	}

	@Override
	public List<Flowers> listFlowersByFlowersName(String flowers_name, long start, long size) {
		return flowersDao.listFlowersByFlowersName(flowers_name, start, size);
	}

	@Override
	public long getCategoryFlowersCount(int category_id) {
		return flowersDao.getCategoryFlowersCount(category_id);
	}

	@Override
	public long getFlowersCount(String name) {
		return flowersDao.getFlowersCount(name);
	}

	@Override
	public int getTotalSales() {
		return 0;
	}

	@Override
	public List<Flowers> listSalesTopFlowers(int top) {
		return flowersDao.listSalesTopFlowers(top);
	}

}

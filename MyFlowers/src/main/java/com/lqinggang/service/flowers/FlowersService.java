package com.lqinggang.service.flowers;

import java.util.List;
import java.util.Map;

import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.flowers.FlowersCategory;
import com.lqinggang.entity.flowers.Sales;
import com.lqinggang.entity.orders.Purchase;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 上午9:30:37
 */
public interface FlowersService {

	/**
	 * 新增一种鲜花
	 * 
	 * @param flowers
	 *            鲜花实体
	 * @return void
	 */
	public void addFlowers(Flowers flowers);

	/**
	 * 增加一条新的鲜花销售量
	 * 
	 * @param sales
	 * @return void
	 */
	public void addSales(Sales sales);

	/**
	 * 新增一条鲜花类型
	 * 
	 * @param flowersCategory
	 *            鲜花类型
	 * @return void
	 */
	public void addFlowersCategory(FlowersCategory flowersCategory);

	/**
	 * 鲜花类别总数
	 * 
	 * @return
	 * @return int
	 */
	public int countFlowersCategory();

	/**
	 * 列出所有的鲜花类别
	 * 
	 * @return
	 * @return List<FlowersCategory>
	 */
	public List<FlowersCategory> listAllFlowersCategories();

	/**
	 * 通过鲜花名模糊查找鲜花
	 * 
	 * @param flowers_name
	 * @return List<Flowers>
	 */
	public List<Flowers> listFlowersByName(String flowers_name);

	/**
	 * 通过鲜花类别列出所有类别鲜花
	 * 
	 * @param category_id
	 * @return List<Flowers>
	 */
	public List<Flowers> listFlowersByCategoryId(int category_id);

	/**
	 * 删除一种鲜花
	 * 
	 * @param flowers_id
	 *            鲜花编号
	 * @return void
	 */
	public void deleteFlowersByFlowerId(int flowers_id);

	/**
	 * 删除一种鲜花类型
	 * 
	 * @param category_id
	 * @return void
	 */
	public void deleteFlowersCategoryByCatagoryId(int category_id);

	/**
	 * 查找鲜花
	 * 
	 * @param flowers_ids
	 *            鲜花实体Id
	 * @return List<Flowers> 鲜花实体
	 */
	public List<Flowers> queryFlowers(int flowers_id);

	/**
	 * 通过鲜花编号查询对应的鲜花类别
	 * 
	 * @param flowers_id
	 * @return FlowersCategory
	 */
	public List<FlowersCategory> queryFlowersCategoryByFlowersId(int flowers_id);

	/**
	 * 通过类别名查询鲜花类别
	 * 
	 * @param categoryname
	 * @return FlowersCategory
	 */
	public List<FlowersCategory> queryFlowersCategoryByCategoryName(String categoryname);

	/**
	 * 通过类别ID查询鲜花类别
	 * 
	 * @param category_id
	 * @return List<FlowersCategory>
	 */
	public List<FlowersCategory> queryFlowersCategoryByCategoryId(int category_id);

	/**
	 * 通过鲜花编号查询销售记录
	 * 
	 * @param flowers_id
	 * @return List<Sales> 销售记录列表
	 */
	public List<Sales> querySalesByFlowersId(int flowers_id);

	/**
	 * 通过用户ID查询销售记录
	 * 
	 * @param users_id
	 * @return List<Sales>
	 */
	public List<Sales> querySalesByUsersId(int users_id);

	/**
	 * 通过鲜花类别查找鲜花
	 * 
	 * @param categoryname
	 * @return List<Flowers>
	 */
	public List<Flowers> findFlowersByFlowersCategoryName(String categoryname);

	/**
	 * @param flowers_id
	 * @return List<PurchaseType>
	 */
	public List<Purchase> queryPurchaseByFlowersId(int flowers_id);

	/**
	 * 列出前x的鲜花
	 * 
	 * @return
	 * @return List<Flowers>
	 */
	public List<Flowers> listTopxFlowers(int x);

	/**
	 * 列出销售前top的鲜花
	 * 
	 * @param top
	 * @return List<Flowers>
	 */
	public List<Flowers> listSalesTopFlowers(int top);

	/**
	 * 列出所有的鲜花
	 * 
	 * @return
	 * @return List<Flowers>
	 */
	public List<Flowers> listAllFlowers();

	/**
	 * 列出start到end范围内的鲜花
	 * 
	 * @param start
	 * @param end
	 * @return List<Flowers>
	 */
	public List<Flowers> listRangeFlowers(long start, long size);

	// /**
	// * 列出类别为category_id的start到end范围内的鲜花
	// *
	// * @param category_id
	// * @param start
	// * @param size
	// * @return List<Flowers>
	// */
	// public List<Flowers> listRangeFlowers(int category_id, long start, long
	// size);

	/**
	 * @param category_id
	 * @param start
	 * @param size
	 * @return
	 * @return List<Flowers>
	 */
	public List<Flowers> listRangeFlowersByCategoryId(int category_id, long start, long size);

	/**
	 * 列出鲜花名称类似于flowers_name的从start开始的size条记录
	 * 
	 * @param flowers_name
	 * @param start
	 * @param size
	 * @return List<Flowers>
	 */
	public List<Flowers> listFlowersByFlowersName(String flowers_name, long start, long size);

	/**
	 * 获取某一类鲜花的总记录数
	 * 
	 * @param category_id
	 * @return long
	 */
	public long getCategoryFlowersCount(int category_id);

	/**
	 * 获取鲜花类似名字于name的鲜花总记录数
	 * 
	 * @param name
	 * @return long
	 */
	public long getFlowersCount(String name);

	/**
	 * 获取记录数
	 * 
	 * @return
	 * @return int
	 */
	public long getFlowersCount();

	/**
	 * 更新鲜花信息
	 * 
	 * @param flowers
	 *            鲜花实体
	 * @return void
	 */
	public void updateFlowers(Flowers flowers);

	/**
	 * 更新鲜花类别信息
	 * 
	 * @param flowersCategory
	 *            鲜花类别
	 * @return void
	 */
	public void updateFlowersCategory(FlowersCategory flowersCategory);

	/**
	 * 分类搜索
	 * 
	 * @param map
	 * @return List<Flowers>
	 */
	public List<Flowers> classify(Map<Object, Object> map);

	/**
	 * 分页分类搜索
	 * 
	 * @param map
	 * @param start
	 * @param size
	 * @return List<Flowers>
	 */
	public List<Flowers> listRangeClassify(Map<Object, Object> map, long start, long size);

	/**
	 * 获取分类搜索总记录数
	 * 
	 * @param map
	 * @return long
	 */
	public long getClassifyCount(Map<Object, Object> map);

	/**
	 * 获取鲜花销售总量
	 * 
	 * @return int
	 */
	public int getTotalSales();

}

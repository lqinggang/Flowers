package com.lqinggang.dao.flowers;

import java.util.List;
import java.util.Map;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.orders.Purchase;

/**
 * 鲜花
 * 
 * @author LQingGang
 * @time 2018年3月19日 - 下午9:25:35
 */
public interface FlowersDao extends BaseDao<Flowers> {
	/**
	 * 新增鲜花
	 * 
	 * @param flowers
	 *            鲜花实体
	 * @return void
	 */
	public void addFlower(Flowers flower);

	/**
	 * 删除鲜花
	 * 
	 * @param flowers_ids
	 *            鲜花编号
	 * @return void
	 */
	public void deleteFlowerByFlowersIds(int[] flowers_ids);

	/**
	 * 通过鲜花编号查询单个鲜花信息
	 * 
	 * @param flowers_id
	 *            鲜花编号
	 * @return Flowers 鲜花实体
	 */
	public List<Flowers> queryFlowersByFlowersId(int flowers_id);

	/**
	 * 通过鲜花名称模糊查找鲜花
	 * 
	 * @param flowers_name
	 *            鲜花名称
	 * @return List<Flowers>
	 */
	public List<Flowers> queryFlowersByFlowersName(String flowers_name);

	/**
	 * 通过鲜花类别查找鲜花
	 * 
	 * @param categoryname
	 *            类别名称
	 * @return List<Flowers>
	 */
	public List<Flowers> findFlowersByFlowersCategoryId(int categoryid);

	/**
	 * 
	 * @param flowers_id
	 *            鲜花编号
	 * @return List<PurchaseType>
	 */
	public List<Purchase> queryPurchaseByFlowersId(int flowers_id);

	/**
	 * @param queryMap
	 *            查询条件
	 * @return List<Flowers>
	 */
	public List<Flowers> classify(Map<Object, Object> queryMap);

	/**
	 * 列出所有的鲜花
	 * 
	 * @return List<Flowers>
	 */
	public List<Flowers> listAllFlowers();

	/**
	 * 列出start开始的end条记录的鲜花
	 * 
	 * @param start
	 * @param size
	 * @return List<Flowers>
	 */
	public List<Flowers> listRangeFlowers(int start, int size);

	/**
	 * 列出类别为category_id的start到size范围内的鲜花
	 * 
	 * @param category_id
	 *            鲜花类别
	 * @param start
	 *            开始位置
	 * @param size
	 *            搜索记录总数
	 * @return List<Flowers>
	 */
	public List<Flowers> listRangeFlowers(int category_id, long start, long size);

	/**
	 * 列出鲜花名称类似于flowers_name的从start开始的size条记录
	 * 
	 * @param flowers_name
	 *            搜索关键字
	 * @param start
	 *            开始位置
	 * @param size
	 *            搜索的记录总数
	 * @return List<Flowers>
	 */
	public List<Flowers> listFlowersByFlowersName(String flowers_name, long start, long size);

	/**
	 * 列出销售量前top的鲜花
	 * 
	 * @param top
	 * @return List<Flowers>
	 */
	public List<Flowers> listSalesTopFlowers(int top);

	/**
	 * 获取某一类鲜花的总记录数
	 * 
	 * @param category_id
	 *            鲜花类别ID
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
	 * 更新鲜花信息
	 * 
	 * @param flowers
	 *            鲜花对象
	 * @return void
	 */
	public void updateFlowers(Flowers flowers);

	/**
	 * 多条件分类搜索，列出start开始的size条记录
	 * 
	 * @param map
	 *            搜索条件
	 * @param start
	 *            开始位置
	 * @param size
	 *            搜索记录总数
	 * @return List<Flowers>
	 */
	public List<Flowers> listRangeClassify(Map<Object, Object> map, long start, long size);

	/**
	 * 获取给定的条件的总记录数
	 * 
	 * @param map
	 * @return long
	 */
	public long getClassifyCount(Map<Object, Object> map);
}

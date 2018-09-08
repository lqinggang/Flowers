package com.lqinggang.dao.orders;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.common.entity.Report;
import com.lqinggang.entity.orders.Orders;

/**
 * 订单
 * 
 * @author LQingGang
 * @time 2018年3月22日 - 下午1:52:41
 */
@SuppressWarnings("unused")
public interface OrdersDao extends BaseDao<Orders> {

	/**
	 * 新增订单
	 * 
	 * @param orders
	 * @return void
	 */
	public void addOrders(Orders orders);

	/**
	 * 通过订单编号删除订单
	 * 
	 * @param orders_id
	 * @return void
	 */
	public void deleteOrders(String orders_id);

	/**
	 * 通过订单编号查询订单
	 * 
	 * @param orders_id
	 * @return Orders
	 */
	public List<Orders> queryOrders(String orders_id);

	/**
	 * 列出用户的所有订单
	 * 
	 * @param users_id
	 * @return List<Orders>
	 */
	public List<Orders> listUsersOrders(int users_id);

	/**
	 * 获取订单总数
	 * 
	 * @return long
	 */
	public long getOrdersCount();

	/**
	 * 获取待处理订单总数
	 * 
	 * @return long
	 */
	public long getPendingOrdersCount();

	/**
	 * 列出用户从start开始的size条订单记录
	 * 
	 * @param name
	 * @param start
	 * @param size
	 * @return List<Orders>
	 */
	public List<Orders> listRangeUsersOrders(String name, long start, long size);

	/**
	 * 获取用户订单总数
	 * 
	 * @param name
	 * @return long
	 */
	public long getUsersOrdersCount(String name);

	/**
	 * 列出start开始的size条用户订单记录
	 * 
	 * @param start
	 * @param size
	 * @return List<Orders>
	 */
	public List<Orders> listRangeOrders(long start, long size);

	/**
	 * 列出start开始的size条待处理订单
	 * 
	 * @param start
	 * @param size
	 * @return List<Orders>
	 */
	public List<Orders> listRangePendingOrders(long start, long size);

	/**
	 * 列出最近一周之内的订单
	 * 
	 * @return List<Orders>
	 */
	public List<Orders> listWithinAWeekOrders();

	/**
	 * 获取一周内订单记录总数
	 * 
	 * @return long
	 */
	public long getWithinAWeekOrdersCount();

	/**
	 * 鲜花销售报表
	 * 
	 * @return List<Orders>
	 */
	public List<Orders> listFlowersReports();

	/**
	 * 更新订单
	 * 
	 * @param orders
	 * @return void
	 */
	public void updateOrdes(Orders orders);

	/**
	 * 列出用户的订单记录信息
	 * 
	 * @param name
	 *            用户名
	 * @param type
	 *            订单类别
	 * @param start
	 *            开始位置
	 * @param size
	 *            搜索记录总数
	 * @return List<Orders>
	 */
	public List<Orders> listRangeUsersOrders(String name, int type, long start, long size);

	/**
	 * 获取用户类型为type的订单的总数
	 * 
	 * @param name
	 *            用户名
	 * @param type
	 *            类别
	 * @return long
	 */
	public long getOrdersCount(String name, int type);

}

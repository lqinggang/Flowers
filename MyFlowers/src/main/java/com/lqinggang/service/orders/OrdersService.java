package com.lqinggang.service.orders;

import java.util.List;

import com.lqinggang.entity.orders.OrderStatusType;
//import com.lqinggang.entity.orders.OrderStatusType;
import com.lqinggang.entity.orders.Orders;
import com.lqinggang.entity.orders.OrdersCancel;
import com.lqinggang.entity.orders.Purchase;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 下午2:20:30
 */
public interface OrdersService {

	/**
	 * 新增订单
	 * 
	 * @param orders
	 * @return void
	 */
	public void addOrders(Orders orders);

	/**
	 * 添加订单评价
	 * 
	 * @param purchase
	 * @return void
	 */
	public void addPurchase(Purchase purchase);

	/**
	 * 新增订单状态类型
	 * 
	 * @param orderStatusType
	 * @return void
	 */
	public void addOrderStatusType(OrderStatusType orderStatusType);

	/**
	 * 设置订单状态
	 * 
	 * @param ordersCancel
	 * @return void
	 */
	public void setOrdersStatus(OrdersCancel ordersCancel);

	/**
	 * 通过订单编号删除订单
	 * 
	 * @param orders_id
	 * @return void
	 */
	public void deleteOrders(String orders_id);

	/**
	 * 删除订单状态类型
	 * 
	 * @param status_type_id
	 * @return void
	 */
	public void deleteOrderStatusType(int status_type_id);

	/**
	 * 通过订单ID删除订单（鲜花）评价信息
	 * 
	 * @param orderId
	 * @return void
	 */
	public void deletePurchaseByOrderId(String orderId);

	/**
	 * 通过订单编号查询订单
	 * 
	 * @param orders_id
	 * @return Orders
	 */
	public List<Orders> queryOrdersByOrdersId(String orders_id);

	/**
	 * 通过订单编号查询订单状态
	 * 
	 * @param orderid
	 * @return OrdersStatus
	 */
	public List<OrdersCancel> queryOrdersStatusByOrdersId(String orderid);

	/**
	 * 通过订单编号查询订单评价
	 * 
	 * @param orders_id
	 * @return Purchase
	 */
	public List<Purchase> queryPurchaseByOrdersId(String orders_id);

	/**
	 * 查找某一种鲜花的评价
	 * 
	 * @param flowers_id
	 * @return List<Purchase>
	 */
	public List<Purchase> queryPurchaseByFlowersId(int flowers_id);

	/**
	 * 列出用户的所有订单
	 * 
	 * @param users_id
	 * @return List<Orders>
	 */
	public List<Orders> listUsersOrders(int users_id);

	/**
	 * 列出待处理订单
	 * 
	 * @return List<Orders>
	 */
	public List<Orders> listPendingOrders(long start, long size);

	/**
	 * 列出从start开始的size条订单记录
	 * 
	 * @param start
	 * @param size
	 * @return List<Orders>
	 */
	public List<Orders> listRangeOrders(long start, long size);

	/**
	 * 列出所有的鲜花评价
	 * 
	 * @return
	 * @return List<Purchase>
	 */
	public List<Purchase> listAllPurchases();

	/**
	 * 列出从start开始的size条评价记录
	 * 
	 * @param start
	 * @param size
	 * @return List<Purchase>
	 */
	public List<Purchase> listRangePurchases(long start, long size);

	/**
	 * 鲜花的统计报表
	 * 
	 * @return List<Orders>
	 */
	public List<Orders> listFlowersReports();

	/**
	 * 获取鲜花评价总记录数
	 * 
	 * @return
	 * @return long
	 */
	public long getPurchasesCount();

	/**
	 * 获取所有订单的记录条数
	 * 
	 * @return long
	 */
	public long getAllOrdersCount();

	/**
	 * 获取待处理订单数
	 * 
	 * @return long
	 */
	public long getPendingOrdersCount();

	/**
	 * 查询订单状态类型
	 * 
	 * @param status_type_id
	 * @return List<OrderStatusType>
	 */
	public List<OrderStatusType> queryOrderStatusTypes(int status_type_id);

	/**
	 * 更新订单
	 * 
	 * @param orders
	 * @return void
	 */
	public void updateOrdes(Orders orders);

	/**
	 * 更新订单评价
	 * 
	 * @param purchase
	 * @return void
	 */
	public void updatePurchase(Purchase purchase);

	/**
	 * 更新订单状态类型
	 * 
	 * @param orderStatusType
	 * @return void
	 */
	public void updateOrderStatusType(OrderStatusType orderStatusType);

	/**
	 * 更新订单状态
	 * 
	 * @param ordersCancel
	 * @return void
	 */
	public void updateOrderStauts(OrdersCancel ordersCancel);

	/**
	 * 通过鲜花id查询鲜花评价总数
	 * 
	 * @param flower_id
	 * @return long
	 */
	public long getPurchasesCountByFlowersId(int flower_id);

	/**
	 * 查询鲜花ID start开始的size条记录评价
	 * 
	 * @param flower_id
	 * @param start
	 * @param size
	 * @return List<Purchase>
	 */
	public List<Purchase> listPurchasesByFlowersId(int flower_id, long start, long size);

	/**
	 * 列出用户name 从start开始的size条订单记录
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
	 * 列出一周内的记录
	 * 
	 * @return List<Orders>
	 */
	public List<Orders> listWithinAWeekOrders();

	/**
	 * 获取一周内订单记录数
	 * 
	 * @return long
	 */
	public long getWithinAWeekOrdersCount();

	/**
	 * 获取date内的评价数
	 * 
	 * @param date
	 * @return long
	 */
	public long getPuechaseCount(int date);

	/**
	 * 列出用户类型为type的订单
	 * 
	 * @param name
	 * @param type
	 * @param start
	 * @param size
	 * @return List<Orders>
	 */
	public List<Orders> listRangeUsersOrders(String name, int type, long start, long size);

	/**
	 * 获取用户类型为type的订单的总数
	 * 
	 * @param name
	 * @param type
	 * @return long
	 */
	public long getOrdersCount(String name, int type);

}

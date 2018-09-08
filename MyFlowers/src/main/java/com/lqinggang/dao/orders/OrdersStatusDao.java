package com.lqinggang.dao.orders;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.orders.OrdersCancel;

/**
 * 订单状态
 * 
 * @author LQingGang
 * @time 2018年3月22日 - 下午2:04:34
 */
public interface OrdersStatusDao extends BaseDao<OrdersCancel> {

	/**
	 * 新增订单状态
	 * 
	 * @param ordersCancel
	 * @return void
	 */
	public void addOrdersStatus(OrdersCancel ordersCancel);

	/**
	 * 删除订单状态
	 * 
	 * @param orders_is
	 * @return void
	 */
	public void deleteOrdersStauts(int orders_id);

	/**
	 * 查询订单状态
	 * 
	 * @param orders_id
	 * @return OrdersStatus
	 */
	public List<OrdersCancel> queryOrdersStatus(String orders_id);

	/**
	 * 更新订单状态
	 * 
	 * @param ordersCancel
	 * @return void
	 */
	public void updateOrdersStatus(OrdersCancel ordersCancel);

}

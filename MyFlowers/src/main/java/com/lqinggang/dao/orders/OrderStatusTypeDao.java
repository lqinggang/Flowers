package com.lqinggang.dao.orders;

import java.util.List;

import com.lqinggang.entity.orders.OrderStatusType;

/**
 * 订单状态类型
 * 
 * @author LQingGang
 * @time 2018年4月10日 - 下午8:21:12
 */
public interface OrderStatusTypeDao {

	/**
	 * 添加订单状态类型
	 * 
	 * @param statusType
	 * @return void
	 */
	public void addOrderStatusType(OrderStatusType statusType);

	/**
	 * 删除订单状态
	 * 
	 * @param status_id
	 * @return void
	 */
	public void deleteOrderStatusType(int status_id);

	/**
	 * 查询订单状态
	 * 
	 * @param status_id
	 * @return List<OrderStatusType>
	 */
	public List<OrderStatusType> queryOrderStatusTypes(int status_id);

	/**
	 * 更新订单状态
	 * 
	 * @param orderStatusType
	 * @return void
	 */
	public void updateOrderStatusTypes(OrderStatusType orderStatusType);
}

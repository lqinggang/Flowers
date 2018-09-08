package com.lqinggang.dao.impl.orders;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.orders.OrdersStatusDao;
import com.lqinggang.entity.orders.OrdersCancel;

@Repository(value = "ordersStatusDao")
public class OrdersStatusDaoImpl extends DaoSupport<OrdersCancel> implements OrdersStatusDao {

	@Override
	public void addOrdersStatus(OrdersCancel ordersCancel) {
		save(ordersCancel);
	}

	@Override
	public void deleteOrdersStauts(int orders_id) {
		delete(orders_id);
	}

	@Override
	public List<OrdersCancel> queryOrdersStatus(String order_id) {
		String hql = " from OrdersStatus as o where " + "o.order_status_id.order_id='" + order_id + "'";
		List<OrdersCancel> ordersCancel = queryByHql(hql);
		return ordersCancel;
	}

	@Override
	public void updateOrdersStatus(OrdersCancel ordersCancel) {
		update(ordersCancel);
	}

}

package com.lqinggang.dao.impl.orders;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.orders.OrderStatusTypeDao;
import com.lqinggang.entity.orders.OrderStatusType;

/**
 * @author LQingGang
 * @time 2018年4月10日 - 下午5:55:43
 */
@Repository(value = "orderStatusTypeDao")
public class OrderStatusTypeDaoImpl extends DaoSupport<OrderStatusType> implements OrderStatusTypeDao {

	@Override
	public void addOrderStatusType(OrderStatusType statusType) {
		save(statusType);
	}

	@Override
	public void deleteOrderStatusType(int status_id) {
		delete(status_id);

	}

	@Override
	public List<OrderStatusType> queryOrderStatusTypes(int status_id) {
		String hql = " from OrderStatusType as o where o.status_type_id='" + status_id + "'";
		List<OrderStatusType> orderStatusTypes = queryByHql(hql);
		return orderStatusTypes;
	}

	@Override
	public void updateOrderStatusTypes(OrderStatusType orderStatusType) {
		saveOrUpdate(orderStatusType);
	}

}

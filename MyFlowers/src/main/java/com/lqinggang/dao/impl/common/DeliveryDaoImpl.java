package com.lqinggang.dao.impl.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.common.DeliveryDao;
import com.lqinggang.entity.common.Delivery;

@Repository(value = "deliveryDao")
public class DeliveryDaoImpl extends DaoSupport<Delivery> implements DeliveryDao {

	@Override
	public void addDelivery(Delivery delivery) {
		save(delivery);
	}

	@Override
	public void deleteDelivery(String order_id) {
		delete(order_id);
	}

	@Override
	public List<Delivery> queryDelivery(int order_id) {
		String hql = " from Delivery as d where d.order_id.order_id ='" + order_id + "'";
		List<Delivery> delivery = queryByHql(hql);
		return delivery;
	}

	@Override
	public void updateDelivery(Delivery delivery) {
		saveOrUpdate(delivery);
	}

}

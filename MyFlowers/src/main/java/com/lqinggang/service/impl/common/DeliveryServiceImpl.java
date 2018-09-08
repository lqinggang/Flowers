package com.lqinggang.service.impl.common;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lqinggang.dao.common.DeliveryDao;
import com.lqinggang.entity.common.Delivery;
import com.lqinggang.service.common.DeliveryService;

@Transactional
@Service(value = "deliveryService")
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryDao deliveryDao;

	@Override
	public void addDelivery(Delivery delivery) {
		deliveryDao.addDelivery(delivery);
	}

	@Override
	public void deleteDelivery(String order_id) {
		deliveryDao.deleteDelivery(order_id);

	}

	@Override
	public List<Delivery> queryDelivery(int order_id) {
		return deliveryDao.queryDelivery(order_id);
	}

	@Override
	public void updateDelivery(Delivery delivery) {
		deliveryDao.updateDelivery(delivery);
	}

}

package com.lqinggang.service.impl.orders;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lqinggang.dao.orders.OrderStatusTypeDao;
//import com.lqinggang.dao.orders.OrderStatusTypeDao;
import com.lqinggang.dao.orders.OrdersDao;
import com.lqinggang.dao.orders.OrdersStatusDao;
import com.lqinggang.dao.orders.PurchaseDao;
import com.lqinggang.entity.orders.OrderStatusType;
//import com.lqinggang.entity.orders.OrderStatusType;
import com.lqinggang.entity.orders.Orders;
import com.lqinggang.entity.orders.OrdersCancel;
import com.lqinggang.entity.orders.Purchase;
import com.lqinggang.service.orders.OrdersService;

@Transactional
@Repository(value = "ordersService")
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private PurchaseDao purchaseDao;
	@Autowired
	private OrdersStatusDao ordersStatusDao;
	@Autowired
	private OrderStatusTypeDao orderStatusTypeDao;

	@Override
	public void addOrders(Orders orders) {
		ordersDao.addOrders(orders);
	}

	@Override
	public void addPurchase(Purchase purchase) {
		purchaseDao.addPurchase(purchase);
	}

	@Override
	public void setOrdersStatus(OrdersCancel ordersCancel) {
		ordersStatusDao.addOrdersStatus(ordersCancel);

	}

	@Override
	public void deleteOrders(String orders_id) {
		ordersDao.deleteOrders(orders_id);

	}

	@Override
	public List<Orders> queryOrdersByOrdersId(String orders_id) {
		return ordersDao.queryOrders(orders_id);
	}

	@Override
	public List<OrdersCancel> queryOrdersStatusByOrdersId(String orders_id) {
		return ordersStatusDao.queryOrdersStatus(orders_id);
	}

	@Override
	public List<Purchase> queryPurchaseByOrdersId(String orders_id) {
		return purchaseDao.queryPurchaseByOrdersId(orders_id);
	}

	@Override
	public List<Orders> listUsersOrders(int users_id) {
		List<Orders> list = ordersDao.listUsersOrders(users_id);
		return list;
	}

	@Override
	public List<Orders> listRangeUsersOrders(String name, long start, long size) {
		return ordersDao.listRangeUsersOrders(name, start, size);
	}

	@Override
	public long getUsersOrdersCount(String name) {
		return ordersDao.getUsersOrdersCount(name);
	}

	@Override
	public void updateOrdes(Orders orders) {
		ordersDao.updateOrdes(orders);

	}

	@Override
	public void updatePurchase(Purchase purchase) {
		purchaseDao.updatePurchase(purchase);

	}

	@Override
	public List<Purchase> queryPurchaseByFlowersId(int flowers_id) {
		List<Purchase> purchases = purchaseDao.queryPurchasesByFlowersId(flowers_id);
		return purchases;
	}

	@Override
	public void addOrderStatusType(OrderStatusType orderStatusType) {
		orderStatusTypeDao.addOrderStatusType(orderStatusType);
	}

	@Override
	public void deleteOrderStatusType(int status_type_id) {
		orderStatusTypeDao.deleteOrderStatusType(status_type_id);
	}

	@Override
	public List<OrderStatusType> queryOrderStatusTypes(int status_type_id) {
		List<OrderStatusType> orderStatusTypes = orderStatusTypeDao.queryOrderStatusTypes(status_type_id);
		return orderStatusTypes;
	}

	@Override
	public void updateOrderStatusType(OrderStatusType orderStatusType) {
		orderStatusTypeDao.updateOrderStatusTypes(orderStatusType);

	}

	@Override
	public void updateOrderStauts(OrdersCancel ordersCancel) {
		ordersStatusDao.saveOrUpdate(ordersCancel);
	}

	@Override
	public List<Orders> listRangeOrders(long start, long size) {
		return ordersDao.listRangeOrders(start, size);
	}

	@Override
	public long getAllOrdersCount() {
		return ordersDao.getCount();
	}

	@Override
	public List<Orders> listPendingOrders(long start, long size) {
		return ordersDao.listRangePendingOrders(start, size);
	}

	@Override
	public long getPendingOrdersCount() {
		return ordersDao.getPendingOrdersCount();
	}

	@Override
	public List<Purchase> listAllPurchases() {
		return purchaseDao.listAll(Purchase.class);
	}

	@Override
	public List<Purchase> listRangePurchases(long start, long size) {
		return purchaseDao.listRangePurchases(start, size);
	}

	@Override
	public long getPurchasesCount() {
		return purchaseDao.getPurchasesCount();
	}

	@Override
	public long getPurchasesCountByFlowersId(int flower_id) {
		return purchaseDao.getPurchasesCountByFlowersId(flower_id);
	}

	@Override
	public List<Purchase> listPurchasesByFlowersId(int flower_id, long start, long size) {
		return purchaseDao.listPurchasesByFlowersId(flower_id, start, size);
	}

	@Override
	public List<Orders> listFlowersReports() {
		return ordersDao.listFlowersReports();
	}

	@Override
	public List<Orders> listWithinAWeekOrders() {
		return ordersDao.listWithinAWeekOrders();
	}

	@Override
	public long getWithinAWeekOrdersCount() {
		return ordersDao.getWithinAWeekOrdersCount();
	}

	@Override
	public void deletePurchaseByOrderId(String orderId) {
		purchaseDao.deletePurchaseByOrdersId(orderId);
	}

	@Override
	public long getPuechaseCount(int date) {
		return purchaseDao.getPurchasesCount(date);
	}

	@Override
	public List<Orders> listRangeUsersOrders(String name, int type, long start, long size) {
		return ordersDao.listRangeUsersOrders(name, type, start, size);
	}

	@Override
	public long getOrdersCount(String name, int type) {
		return ordersDao.getOrdersCount(name, type);
	}

}

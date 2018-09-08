package com.lqinggang.dao.impl.orders;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.common.util.DateUtil;
import com.lqinggang.dao.orders.PurchaseDao;
import com.lqinggang.entity.orders.Purchase;

@Repository(value = "purchaseTypeDao")
public class PurchaseDaoImpl extends DaoSupport<Purchase> implements PurchaseDao {

	@Override
	public void addPurchase(Purchase purchase) {
		save(purchase);
	}

	@Override
	public void deletePurchaseByOrdersId(String orders_id) {
		delete(orders_id);
	}

	@Override
	public List<Purchase> queryPurchaseByOrdersId(String orders_id) {
		String hql = " from Purchase as p where p.order_id.order_id='" + orders_id + "'";
		List<Purchase> purchase = queryByHql(hql);
		return purchase;
	}

	@Override
	public void updatePurchase(Purchase purchase) {
		saveOrUpdate(purchase);

	}

	@Override
	public List<Purchase> queryPurchasesByFlowersId(int flowers_id) {
		String hql = " from Purchase as p where p.order_id.flower_id.flower_id='" + flowers_id + "' ";
		List<Purchase> purchases = queryByHql(hql);
		return purchases;
	}

	@Override
	public List<Purchase> listRangePurchases(long start, long size) {
		return listRange(Purchase.class, start, size);
	}

	@Override
	public long getPurchasesCount() {
		return getCount();
	}

	@Override
	public List<Purchase> listPurchasesByFlowersId(int flowers_id, long start, long size) {
		String hql = " from Purchase as p where p.order_id.flower_id.flower_id='" + flowers_id + "' ";
		List<Purchase> purchases = listRange(hql, start, size);
		return purchases;
	}

	@Override
	public long getPurchasesCountByFlowersId(int flowers_id) {
		String hql = " from Purchase as p where p.order_id.flower_id.flower_id='" + flowers_id + "' ";
		return getCount(hql);
	}

	@Override
	public long getPurchasesCount(int date) {
		String hql = " from Purchase as p where p.date>='" + DateUtil.getDailyTimeStart(-date) + "' and p.date<='"
				+ DateUtil.getDailyTimeEnd(0) + "'";
		return getCount(hql);
	}

}

package com.lqinggang.dao.orders;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.orders.Purchase;

/**
 * 订单评价
 * 
 * @author LQingGang
 * @time 2018年3月22日 - 下午2:05:34
 */
public interface PurchaseDao extends BaseDao<Purchase> {

	/**
	 * 新增评价
	 * 
	 * @param purchase
	 * @return void
	 */
	public void addPurchase(Purchase purchase);

	/**
	 * 通过订单编号删除评价
	 * 
	 * @param orders_id
	 * @return void
	 */
	public void deletePurchaseByOrdersId(String orders_id);

	/**
	 * 通过订单编号查询订单评价
	 * 
	 * @param orders_id
	 * @return Purchase
	 */
	public List<Purchase> queryPurchaseByOrdersId(String orders_id);

	/**
	 * 通过鲜花ID查询全部评价
	 * 
	 * @param flowers_id
	 * @return List<Purchase>
	 */
	public List<Purchase> queryPurchasesByFlowersId(int flowers_id);

	/**
	 * 查询鲜花ID start开始的size条记录评价
	 * 
	 * @param flowers_id
	 * @return List<Purchase>
	 */
	public List<Purchase> listPurchasesByFlowersId(int flowers_id, long start, long size);

	/**
	 * 通过鲜花ID获取评价总数
	 * 
	 * @param flowers_id
	 * @return long
	 */
	public long getPurchasesCountByFlowersId(int flowers_id);

	/**
	 * 获取date天内的评价数
	 * 
	 * @param date
	 * @return long
	 */
	public long getPurchasesCount(int date);

	/**
	 * 列出从start开始的size条评价记录
	 * 
	 * @param start
	 * @param size
	 * @return List<Purchase>
	 */
	public List<Purchase> listRangePurchases(long start, long size);

	/**
	 * 获取评价总记录数
	 * 
	 * @return long
	 */
	public long getPurchasesCount();

	/**
	 * 更新评价
	 * 
	 * @param purchase
	 * @return void
	 */
	public void updatePurchase(Purchase purchase);

}

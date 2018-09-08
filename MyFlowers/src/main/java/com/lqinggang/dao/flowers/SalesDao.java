package com.lqinggang.dao.flowers;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.flowers.Sales;

/**
 * @author LQingGang
 * @time 2018年3月19日 - 下午9:39:01
 */
public interface SalesDao extends BaseDao<Sales> {

	/**
	 * 新增销售
	 * 
	 * @param sales
	 *            销售数据
	 * @return void
	 */
	public void addSales(Sales sales);

	/**
	 * 删除销售数据
	 * 
	 * @param sales_id
	 *            鲜花编号
	 * @return void
	 */
	public void deleteSales(int sales_id);

	/**
	 * 查询销售数据
	 * 
	 * @param sales_id
	 *            鲜花编号
	 * @return Sales 销售数据
	 */
	public List<Sales> querySalesByFlowersId(int flowers_id);
	
	/**
	 * 通过用户ID查询销售记录
	 * @param userid
	 * @return List<Sales>
	 */
	public List<Sales> querySalesByUsersId(int userid);

	/**
	 * 更新销售数据
	 * 
	 * @param sales
	 *            鲜花
	 * @return void
	 */
	public void updateSales(Sales sales);
}

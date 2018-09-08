package com.lqinggang.dao.impl.flowers;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.flowers.SalesDao;
import com.lqinggang.entity.flowers.Sales;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 上午9:10:36
 */
@Repository(value = "salesDao")
public class SalesDaoImpl extends DaoSupport<Sales> implements SalesDao {

	@Override
	public void addSales(Sales sales) {
		save(sales);
	}

	@Override
	public void deleteSales(int sales_id) {
		delete(sales_id);

	}

	@Override
	public List<Sales> querySalesByFlowersId(int flower_id) {
		List<Sales> sales = queryByHql(" from Sales as s where s.sales_id.flower_id='" + flower_id + "' ");
		return sales;
	}

	@Override
	public void updateSales(Sales sales) {
		update(sales);
	}

	@Override
	public List<Sales> querySalesByUsersId(int userid) {
		String hql = " from Sales as s where s.person_id.person_id='" + userid + "' ";
		List<Sales> sales = queryByHql(hql);
		return sales;
	}

}

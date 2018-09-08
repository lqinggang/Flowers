package com.lqinggang.dao.impl.orders;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.common.util.DateUtil;
import com.lqinggang.dao.orders.OrdersDao;
import com.lqinggang.entity.orders.Orders;

@Repository(value = "ordersDao")
public class OrdersDaoImpl extends DaoSupport<Orders> implements OrdersDao {

	@Override
	public void addOrders(Orders orders) {
		save(orders);
	}

	@Override
	public void deleteOrders(String orders_id) {
		delete(orders_id);
	}

	@Override
	public List<Orders> queryOrders(String orders_id) {
		String hql = " from Orders as o where o.order_id='" + orders_id + "' ";
		List<Orders> orders = queryByHql(hql);
		return orders;
	}

	@Override
	public List<Orders> listUsersOrders(int users_id) {
		String hql = " from Orders as o where o.person_id.person_id='" + users_id + "' ";
		List<Orders> list = queryByHql(hql);
		return list;
	}

	@Override
	public void updateOrdes(Orders orders) {
		saveOrUpdate(orders);
	}

	@Override
	public List<Orders> listRangeOrders(long start, long size) {
		return listRange(Orders.class, start, size);
	}

	@Override
	public List<Orders> listRangePendingOrders(long start, long size) {
		String hql = " from Orders as o where o.status_type_id.status_type_id='1'";
		return listRange(hql, start, size);
	}

	/**
	 * 获取用户订单记录数
	 */
	@Override
	public long getOrdersCount() {
		return getCount();
	}

	/**
	 * 根据hql获取记录数
	 */
	@Override
	public long getPendingOrdersCount() {
		String hql = " from Orders as o where o.status_type_id.status_type_id='1'";
		return getCount(hql);
	}

	/**
	 * 鲜花销售报表
	 */
	@Override
	public List<Orders> listFlowersReports() {
		// List<Report> reports = new ArrayList<Report>();

		// SELECT orders.flower_id,sum(orders.amount) FROM flowers_db.orders
		// where orders.status_type_id in (2,3,4) GROUP BY orders.flower_id
		// ORDER BY sum(orders.amount) DESC ;

		String hql = " from Orders as o " + "where o.status_type_id.status_type_id in (2,3,4)"
				+ " group by o.flower_id.flower_id order by sum(o.amount)";
		List<Orders> orders = queryByHql(hql);
		return orders;
	}

	@Override
	public List<Orders> listRangeUsersOrders(String name, long start, long size) {
		String hql = " from Orders as o where o.person_id.name='" + name + "' ";
		return listRange(hql, start, size);
	}

	@Override
	public List<Orders> listRangeUsersOrders(String name, int type, long start, long size) {
		String hql = " from Orders as o where o.person_id.name='" + name + "' and o.status_type_id.status_type_id="
				+ type + " ";
		return listRange(hql, start, size);
	}

	@Override
	public long getUsersOrdersCount(String name) {
		String hql = "   from Orders as o where o.person_id.name='" + name + "' ";
		return getCount(hql);
	}

	@Override
	public List<Orders> listWithinAWeekOrders() {
		String hql = "  from Orders as o where o.date >= '" + DateUtil.getStateTime(-7) + "' and o.date <= '"
				+ DateUtil.getStateTime(0) + "' ";
		return queryByHql(hql);
	}

	@Override
	public long getWithinAWeekOrdersCount() {
		String hql = "  from Orders as o where o.date >= '" + DateUtil.getStateTime(-7) + "' and o.date <= '"
				+ DateUtil.getStateTime(0) + "' ";
		return getCount(hql);
	}

	@Override
	public long getOrdersCount(String name, int type) {
		String hql = "  from Orders as o where o.person_id.name='" + name + "' and o.status_type_id.status_type_id="
				+ type + " ";
		return getCount(hql);
	}

}

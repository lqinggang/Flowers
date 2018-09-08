package com.lqinggang.dao.impl.common;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.common.entity.Report;
import com.lqinggang.common.util.DateUtil;
import com.lqinggang.dao.common.ReportViewDao;

/**
 * @author LQingGang
 * @time 2018年4月27日 - 下午7:52:02
 */
@Repository(value = "reportViewDao")
public class ReportViewDaoImpl extends DaoSupport<Object[]> implements ReportViewDao {

	/**
	 * 鲜花销售报表
	 */
	@Override
	public List<Report> listFlowersSalesReport() {

		String hql = " select o.flower_id.flower_id,o.flower_id.name,sum(o.amount) from"
				+ " Orders as o where o.status_type_id.status_type_id in (2,3,4) group by"
				+ " o.flower_id.flower_id order by sum(o.amount) desc ";
		List<Report> flowersReport = new ArrayList<Report>();
		List<Object[]> list = (List<Object[]>) queryByHql(hql);
		if (list != null) {
			for (Object[] objects : list) {
				Report report = new Report(objects[0].toString() + "-" + objects[1].toString(), objects[2]);
				flowersReport.add(report);
			}
		}
		return flowersReport;
	}

	@Override
	public long getTotalSales() {
		String hql = " select sum(o.amount) from Orders as o where o.status_type_id.status_type_id in (2,3,4) ";
		List<Object[]> sumList = queryByHql(hql);
		if (sumList != null) {
			long total = Long.valueOf(String.valueOf(sumList.get(0))).longValue();
			return total;

		}
		return 0;
	}

	@Override
	public List<Report> listRangeFlowersSales(int date) {
		List<Report> flowersReport = new ArrayList<Report>();
		if (date > 0) {
			for (int i = 0; i < date; i++) {
				String hql = " select o.amount from  Orders as o where  o.status_type_id.status_type_id in (2,3,4) "
						+ "and o.date >= '" + DateUtil.getDailyTimeStart(-i) + " ' and o.date <= '"
						+ DateUtil.getDailyTimeEnd(-i) + "' ";
				List<Object[]> list = (List<Object[]>) queryByHql(hql);

				Report report = new Report();
				report.setName(DateUtil.getStateTime(-i));

				if (list != null && list.size() > 0) {
					int sum = 0;
					for (int j = 0; j < list.size(); j++) {
						sum += Integer.valueOf(String.valueOf(list.get(j))).intValue();
					}
					report.setNumber(sum);
				} else {
					report.setNumber("0");
				}

				flowersReport.add(report);

			}
		}
		return flowersReport;
	}

	@Override
	public List<Report> listUsesRegisteredReport(int date) {

		List<Report> reports = new ArrayList<Report>();
		if (date > 0) {
			for (int i = 0; i < date; i++) {
				String hql = " from Users as u where u.register_date>='" + DateUtil.getDailyTimeStart(-i)
						+ "' and u.register_date<='" + DateUtil.getDailyTimeEnd(-i) + "' ";
				long count = getCount(hql);
				Report report = new Report();
				report.setName(DateUtil.getStateTime(-i));
				report.setNumber(count);
				reports.add(report);
			}
		}
		return reports;
	}

	@Override
	public List<Report> listUsersSalesReport(int size) {

		List<Report> reports = new ArrayList<Report>();
		if (size > 0) {
			String hql = " select o.person_id.name,sum(o.price) from Orders as o"
					+ "  where o.status_type_id.status_type_id in (2,3,4)"
					+ " group by o.person_id.person_id order by sum(o.price) desc ";
			List<Object[]> objects = listRange(hql, 0, size);

			if (objects != null && objects.size() > 0) {
				for (int j = 0; j < objects.size(); j++) {
					double number = Double.parseDouble(objects.get(j)[1].toString());
					DecimalFormat df = new DecimalFormat("0.00");// 格式化
					reports.add(new Report(objects.get(j)[0], df.format(number)));
				}
			}
		}
		return reports;
	}

	@Override
	public List<Object> listRangeUsersRegisteredGenderReport(int date, String gender) {
		List<Object> reports = new ArrayList<Object>();
		if (date > 0) {
			for (int i = 0; i < date; i++) {
				String hql = " from Users as u where u.gender='" + gender + "' and u.register_date>='"
						+ DateUtil.getDailyTimeStart(-i) + "' and u.register_date<='" + DateUtil.getDailyTimeEnd(-i)
						+ "' ";
				long count = getCount(hql);
				reports.add(count);
			}
		}
		return reports;
	}

}

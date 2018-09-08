package com.lqinggang.service.impl.common;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.common.entity.Report;
import com.lqinggang.dao.common.ReportViewDao;
import com.lqinggang.service.common.ReportViewService;

/**
 * @author LQingGang
 * @time 2018年4月27日 - 下午8:00:04
 */
@Transactional
@Service(value = "reportViewService")
public class ReportViewServiceImpl extends DaoSupport<Report> implements ReportViewService {

	@Autowired
	private ReportViewDao reportViewDao;

	@Override
	public List<Report> listFlowersSalesReport() {
		return reportViewDao.listFlowersSalesReport();
	}

	@Override
	public long getFlowersTotalSales() {
		return reportViewDao.getTotalSales();
	}

	@Override
	public List<Report> listRangeFlowersSales(int date) {
		return reportViewDao.listRangeFlowersSales(date);
	}

	@Override
	public List<Report> listUsesRegisteredReport(int date) {
		return reportViewDao.listUsesRegisteredReport(date);
	}

	@Override
	public List<Report> listUsersSalesReport(int size) {
		return reportViewDao.listUsersSalesReport(size);
	}

	@Override
	public List<Object> listRangeUsersRegisteredGenderReport(int date, String gender) {
		return reportViewDao.listRangeUsersRegisteredGenderReport(date, gender);
	}

}

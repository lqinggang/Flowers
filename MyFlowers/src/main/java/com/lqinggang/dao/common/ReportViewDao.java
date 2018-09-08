package com.lqinggang.dao.common;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.common.entity.Report;

/**
 * 报表
 * 
 * @author LQingGang
 * @time 2018年4月27日 - 下午7:50:14
 */
public interface ReportViewDao extends BaseDao<Object[]> {
	/**
	 * 鲜花统计报表
	 * 
	 * @return List<Report>
	 */
	public List<Report> listFlowersSalesReport();

	/**
	 * 获取鲜花销售总量
	 * 
	 * @return long
	 */
	public long getTotalSales();

	/**
	 * 列出从date天前开始的n天的每天鲜花销售量
	 * 
	 * @param date
	 * @return List<Report>
	 */
	public List<Report> listRangeFlowersSales(int date);

	/**
	 * date天内用户注册报表
	 * 
	 * @param date
	 * @return List<Report>
	 */
	public List<Report> listUsesRegisteredReport(int date);

	/**
	 * 列出鲜花购买最多的前size用户
	 * 
	 * @param size
	 * @return List<Report>
	 */
	public List<Report> listUsersSalesReport(int size);

	/**
	 * date天内性别为gender的每天注册量
	 * 
	 * @param date
	 * @param gender
	 * @return List<Report>
	 */
	public List<Object> listRangeUsersRegisteredGenderReport(int date, String gender);

}

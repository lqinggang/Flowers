package com.lqinggang.service.common;

import java.util.List;

import com.lqinggang.common.entity.Report;

/**
 * 统计报表
 * 
 * @author LQingGang
 * @time 2018年4月27日 - 下午7:58:04
 */
public interface ReportViewService {

	/**
	 * 鲜花销售报表
	 * 
	 * @return List<Report> name 对应鲜花编号 number 对应鲜花销售量
	 */
	public List<Report> listFlowersSalesReport();

	/**
	 * 获取鲜花销售总量
	 * 
	 * @return long
	 */
	public long getFlowersTotalSales();

	/**
	 * 列出从date天前开始的共n天的每天鲜花销售量
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
	 * 列出date天内性别为gender的用户的注册量
	 * 
	 * @param date
	 * @param gender
	 * @return List<Object>
	 */
	public List<Object> listRangeUsersRegisteredGenderReport(int date, String gender);

}

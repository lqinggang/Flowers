package com.lqinggang.dao.common;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.common.AdminLogs;

/**
 * 管理员日志
 * 
 * @author LQingGang
 * @time 2018年4月21日 - 下午9:48:21
 */
public interface AdminLogsDao extends BaseDao<AdminLogs> {

	/**
	 * 新增管理员日志信息
	 * 
	 * @param logs
	 *            日志信息
	 * @return void
	 */
	public void addLogs(AdminLogs logs);

	/**
	 * 根据日志ID删除日志信息
	 * 
	 * @param logs_id
	 *            日志ID
	 * @return void
	 */
	public void deleteLogs(int logs_id);

	/**
	 * 根据日志ID查找日志信息
	 * 
	 * @param logs_id
	 *            日志ID
	 * @return AdminLogs 对应的日志信息或者NULL
	 */
	public AdminLogs queryLogs(int logs_id);

	/**
	 * 列出名为admin_name的管理员日志信息
	 * 
	 * @param admin_name
	 *            管理员名
	 * @return List<AdminLogs>
	 */
	public List<AdminLogs> listAdminLogs(int admin_name);

	/**
	 * 列出所有的日志信息
	 * 
	 * @return List<AdminLogs>
	 */
	public List<AdminLogs> listAllLogs();

	/**
	 * 列出从start开始的size条记录
	 * 
	 * @param start
	 *            开始位置
	 * @param size
	 *            搜索记录数
	 * @return List<AdminLogs>
	 */
	public List<AdminLogs> listRangeLos(long start, long size);

	/**
	 * 获取日志记录数
	 * 
	 * @return long
	 */
	public long getLogsCount();

	/**
	 * 更新日志信息
	 * 
	 * @param adminLogs
	 * @return void
	 */
	public void updateLogs(AdminLogs adminLogs);

}

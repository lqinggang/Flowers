package com.lqinggang.service.users;

import java.sql.Timestamp;
import java.util.List;

import com.lqinggang.entity.common.AdminLogs;
import com.lqinggang.entity.users.Admin;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 下午12:44:37
 */
public interface AdminService {

	/**
	 * 新增一个管理员
	 * 
	 * @param admin
	 *            管理员
	 * @return void
	 */
	public void addAdmin(Admin admin);

	/**
	 * 添加管理员日志
	 * 
	 * @param adminLogs
	 * @return void
	 */
	public void addLogs(AdminLogs adminLogs);

	/**
	 * 通过admin_id删除一个管理员
	 * 
	 * @param admin_id
	 *            管理员ID
	 * @return void
	 */
	public void deleteAdminByAdminId(int admin_id);

	/**
	 * 根据日志ID删除日志信息
	 * 
	 * @param logs_id
	 * @return void
	 */
	public void deleteAdminByLogsId(int logs_id);

	/**
	 * 查询所有的管理员
	 * 
	 * @return List<Admin>管理员列表
	 */
	public List<Admin> listAllAdmins();

	/**
	 * 列出所有的日志信息
	 * 
	 * @return List<AdminLogs>
	 */
	public List<AdminLogs> listAllLogs();

	/**
	 * 列出start开始的size条日志记录
	 * 
	 * @param start
	 * @param size
	 * @return List<AdminLogs>
	 */
	public List<AdminLogs> listRangeLogs(long start, long size);

	/**
	 * 通过管理员ID查询管理员
	 * 
	 * @param admin_id
	 *            管理员ID
	 * @return List<Admin> 管理员
	 */
	public List<Admin> queryAdminsByAdminId(int admin_id);

	/**
	 * 通过管理员名称查询管理员
	 * 
	 * @param name
	 * @return List<Admin>
	 */
	public List<Admin> queryAdminsByName(String name);

	/**
	 * 根据日志ID查询日志信息
	 * 
	 * @param logs_id
	 * @return List<AdminLogs>
	 */
	public AdminLogs queryLogsByLogsId(int logs_id);

	/**
	 * 通过管理员名称和密码查询管理员
	 * 
	 * @param name
	 * @param password
	 * @return List<Admin>
	 */
	public List<Admin> findAdmins(String name, String password);

	/**
	 * 通过管理员ID查询管理员上次登录时间
	 * 
	 * @param admin_id
	 *            管理员ID
	 * @return Timestamp 上次登录时间
	 */
	public Timestamp getLoginDateByAdminId(int admin_id);

	/**
	 * 通过管理员名称查询管理员上次登录时间
	 * 
	 * @param admin_id
	 *            管理员ID
	 * @return Timestamp 上次登录时间
	 */
	public Timestamp getLoginDateByAdminName(String admin_name);

	/**
	 * 获取日志信息总数
	 * 
	 * @return long
	 */
	public long getLogsCount();

	/**
	 * 更新管理员信息
	 * 
	 * @param admin
	 *            管理员对象
	 * @return void
	 */
	public void updateAdmin(Admin admin);

	/**
	 * 更新日志信息
	 * 
	 * @param logs
	 * @return void
	 */
	public void updateLogs(AdminLogs logs);

}

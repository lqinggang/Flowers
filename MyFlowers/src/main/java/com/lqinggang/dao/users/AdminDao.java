package com.lqinggang.dao.users;

import java.sql.Timestamp;
import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.users.Admin;

/**
 * 管理员
 * 
 * @author LQingGang
 * @time 2018年3月22日 - 上午11:24:35
 */
public interface AdminDao extends BaseDao<Admin> {

	/**
	 * 新增一个管理员
	 * 
	 * @param admin
	 *            管理员
	 * @return void
	 */
	public void addAdmin(Admin admin);

	/**
	 * 通过admin_id删除一个管理员
	 * 
	 * @param admin_id
	 *            管理员ID
	 * @return void
	 */
	public void deleteAdminByAdminId(int admin_id);

	/**
	 * 通过管理员名称和密码查询管理员
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @return List<Admin>
	 */
	public List<Admin> findAdmins(String name, String password);

	/**
	 * 查询所有的管理员
	 * 
	 * @return List<Admin>管理员列表
	 */
	public List<Admin> listAllAdmins();

	/**
	 * 通过管理员ID查询管理员
	 * 
	 * @param admin_id
	 *            管理员ID
	 * @return Admin 管理员
	 */
	public List<Admin> queryAdminsByAdminId(int admin_id);

	/**
	 * 通过管理员ID查询管理员上次登录时间
	 * 
	 * @param admin_id
	 *            管理员ID
	 * @return Timestamp 上次登录时间
	 */
	public Timestamp getLoginDateByAdminId(int admin_id);

	/**
	 * 更新管理员信息
	 * 
	 * @param admin
	 *            管理员对象
	 * @return void
	 */
	public void updateAdmin(Admin admin);
}

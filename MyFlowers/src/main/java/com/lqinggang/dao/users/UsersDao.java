package com.lqinggang.dao.users;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.users.Users;

/**
 * 用户
 * 
 * @author LQingGang
 * @time 2018年3月20日 - 上午9:26:01
 */
public interface UsersDao extends BaseDao<Users> {

	/**
	 * 新增一个用户
	 * 
	 * @param user
	 *            新增用户
	 * @return void
	 */
	public void addUsers(Users user);

	/**
	 * 通过用户ID删除用户
	 * 
	 * @param userId
	 *            用户Id
	 * @return void
	 */
	public void deleteUsersById(int userId);

	/**
	 * 通过用户名删除用户
	 * 
	 * @param userName
	 *            用户名
	 * @return void
	 */
	public void deleteUsersByName(String userName);

	/**
	 * 通过用户ID查找用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return Users 用户
	 */
	public List<Users> queryUsersById(int userId);

	/**
	 * 通过用户ID查找用户
	 * 
	 * @param userid
	 * @return Users
	 */
	// public Users queryByUsersId(int userid);

	/**
	 * 列出所有的用户
	 * 
	 * @return
	 * @return List<Users>
	 */
	public List<Users> listAllUsers();

	/**
	 * 修改用户信息
	 * 
	 * @param users
	 *            用户
	 * @return void
	 */
	public void updateUsers(Users users);
}

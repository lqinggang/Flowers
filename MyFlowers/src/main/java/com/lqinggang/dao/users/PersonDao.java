package com.lqinggang.dao.users;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.users.Person;

/**
 * @author LQingGang
 * @time 2018年3月20日 - 上午9:25:47
 */
public interface PersonDao extends BaseDao<Person> {
	/**
	 * 新增一个用户
	 * 
	 * @param person
	 *            新增的用户
	 * @return void
	 */
	public void addPerson(Person person);

	/**
	 * 通过用户ID删除用户
	 * 
	 * @param personId
	 *            用户ID
	 * @return void
	 */
	public void deletePersonById(int personId);

	/**
	 * 通过用户名删除用户
	 * 
	 * @param personName
	 *            用户名
	 * @return void
	 */
	public void deletePersonByName(String personName);

	/**
	 * 通过用户ID查找用户
	 * 
	 * @param personId
	 *            用户ID
	 * @return Person 用户
	 */
	public List<Person> queryPersonById(int personId);

	/**
	 * 通过用户名查找用户
	 * 
	 * @param personName
	 *            用户名
	 * @return Person 用户
	 */
	public List<Person> queryPersonByName(String personName);

	/**
	 * 修改用户信息
	 * 
	 * @param person
	 *            用户
	 * @return void
	 */
	public void updatePerson(Person person);

}

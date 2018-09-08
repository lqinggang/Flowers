package com.lqinggang.service.users;

import java.util.List;

import com.lqinggang.entity.users.Cart;
import com.lqinggang.entity.users.Contact;
import com.lqinggang.entity.users.Member;
import com.lqinggang.entity.users.Person;
import com.lqinggang.entity.users.Users;

/**
 * @author LQingGang
 * @time 2018年3月20日 - 上午9:42:08
 */
public interface UsersService {

	/**
	 * 新增一个用户
	 * 
	 * @param user
	 *            新增用户
	 * @return void
	 */
	public void createUsers(Users user);

	/**
	 * 添加用户对应的信息
	 * 
	 * @param person
	 * @return void
	 */
	public void addPersonInfo(Person person);

	/**
	 * 添加用户联系方式
	 * 
	 * @param contact
	 * @return void
	 */
	public void addContactInfo(Contact contact);

	/**
	 * 添加会员信息
	 * 
	 * @param member
	 * @return void
	 */
	public void addMember(Member member);

	/**
	 * 加入购物车
	 * 
	 * @param cart
	 * @return vod
	 */
	public void addCart(Cart cart);

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
	 * 通过用户名和鲜花编号删除购物车相关信息
	 * 
	 * @param name
	 * @param flower_id
	 * @return void
	 */
	public void deleteCart(String name, int flower_id);

	/**
	 * 通过购物车ID删除对应的一条数据
	 * 
	 * @param cart_id
	 * @return void
	 */
	public void deleteCart(int cart_id);

	/**
	 * 通过用户ID查询Person
	 * 
	 * @param person_id
	 * @return List<Person>
	 */
	public List<Person> queryPersonsById(int person_id);

	/**
	 * 通过用户ID查询Person
	 * 
	 * @param person_id
	 * @return List<Person>
	 */
	public List<Person> queryPersonsByName(String person_name);

	/**
	 * 查找用户是否存在
	 * 
	 * @param name
	 * @param password
	 * @return boolean
	 */
	public boolean findPersonIsExists(String name, String password);

	/**
	 * 通过用户ID查找用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return Users 用户
	 */
	public List<Users> queryUsersById(int userId);

	/**
	 * 通过用户名查找用户
	 * 
	 * @param userName
	 *            用户名
	 * @return List<Users> 用户列表
	 */
	public List<Users> queryUsersByName(String userName);

	// public Users queryUsersByName(String username);

	/**
	 * 通过用户名查询用户联系方式
	 * 
	 * @param userName
	 * @return Contact
	 */
	public List<Contact> queryUsersContactByName(String userName);

	/**
	 * 通过用户名称查询用户会员信息
	 * 
	 * @param userName
	 * @return Member
	 */
	public List<Member> queryUsersMemberByName(String userName);

	/**
	 * 列出用户购物车中的商品
	 * 
	 * @param username
	 * @return List<Cart>
	 */
	public List<Cart> listUsersCart(String username);

	/**
	 * 列出用户购物车start开始的size条记录
	 * 
	 * @param username
	 * @param start
	 * @param size
	 * @return List<Cart>
	 */
	public List<Cart> listRangeUsersCart(String username, long start, long size);

	/**
	 * 列出所用的用户
	 * 
	 * @return
	 * @return List<Users>
	 */
	public List<Users> listUsers();

	/**
	 * 查询从start开始的size条用户记录
	 * 
	 * @param start
	 * @param size
	 * @return List<Users>
	 */
	public List<Users> listRangeUsers(long start, long size);

	/**
	 * 列出所有的联系方式
	 * 
	 * @return
	 * @return List<Contact>
	 */
	public List<Contact> listContacts();

	/**
	 * 列出从start开始的size条联系方式记录
	 * 
	 * @param start
	 * @param size
	 * @return List<Contact>
	 */
	public List<Contact> listRangeUsersContacts(long start, long size);

	/**
	 * 列出从start开始的size条会员信息
	 * 
	 * @param start
	 * @param size
	 * @return List<Member>
	 */
	public List<Member> listRangeMember(long start, long size);

	/**
	 * 查询购物车中是否存在某一条记录
	 * 
	 * @param name
	 * @param flower_id
	 * @return List<Cart>
	 */
	public List<Cart> queryCarts(String name, int flower_id);

	/**
	 * 获取用户数
	 * 
	 * @return
	 * @return long
	 */
	public long getUsersCount();

	/**
	 * 更新用户信息
	 * 
	 * @param users
	 * @return void
	 */
	public void updateUsers(Users users);

	/**
	 * 更新用户联系方式
	 * 
	 * @param contact
	 * @return void
	 */
	public void updateUsersContact(Contact contact);

	/**
	 * 更新用户会员信息
	 * 
	 * @param member
	 * @return void
	 */
	public void updateUsersMember(Member member);

	/**
	 * 更新用户密码
	 * 
	 * @param person
	 * @return void
	 */
	public void updateUsersPassword(Person person);

	/**
	 * 更新用户购物车
	 * 
	 * @param cart
	 * @return void
	 */
	public void updateUsersCart(Cart cart);

	/**
	 * 获取用户购物车记录总数
	 * 
	 * @param name
	 * @return long
	 */
	long getUsersCartCount(String name);

}

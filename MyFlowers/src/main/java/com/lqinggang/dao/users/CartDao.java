package com.lqinggang.dao.users;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.users.Cart;

/**
 * 购物车
 * 
 * @author LQingGang
 * @time 2018年3月21日 - 下午3:38:09
 */
public interface CartDao extends BaseDao<Cart> {

	/**
	 * 增加购物车记录
	 * 
	 * @param cart
	 * @return void
	 */
	public void addCart(Cart cart);

	/**
	 * 删除购物车记录
	 * 
	 * @param cart
	 * @return void
	 */
	public void deleteCart(int cart_id);

	/**
	 * 通过用户名和鲜花编号删除购物车中单条记录
	 * 
	 * @param name
	 * @return void
	 */
	public void deleteCart(String name, int flower_id);

	/**
	 * 清空购物车
	 * 
	 * @param name
	 * @return void
	 */
	public void deleterCart(String name);

	/**
	 * 通过用户ID查询购物车记录
	 * 
	 * @param user_id
	 * @return List<Cart>
	 */
	/* public List<Cart> listCartsById(int user_id); */

	/**
	 * 通过用户名查询用户购物车记录
	 * 
	 * @param name
	 * @return List<Cart>
	 */
	public List<Cart> listCartsByPerson(String name);

	/**
	 * 通过用户名称和鲜花ID查询对应的购物车记录
	 * 
	 * @param name
	 * @param flower_id
	 * @return List<Cart>
	 */
	public List<Cart> queryCart(String name, int flower_id);

	/**
	 * 更新购物车记录
	 * 
	 * @param cart
	 * @return void
	 */
	public void updateCartById(Cart cart);

	/**
	 * 列出用户name从start开始的size条记录
	 * 
	 * @param name
	 * @param start
	 * @param size
	 * @return List<Cart>
	 */
	public List<Cart> listCartsByPerson(String name, long start, long size);

	/**
	 * 获取用户购物车记录总数
	 * 
	 * @param name
	 * @return long
	 */
	long getUsersCartCount(String name);

}

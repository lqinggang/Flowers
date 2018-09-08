package com.lqinggang.dao.impl.users;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.users.CartDao;
import com.lqinggang.entity.users.Cart;

/**
 * @author LQingGang
 * @time 2018年3月21日 - 下午3:46:34
 */
@Repository(value = "cartDao")
public class CartDaoImpl extends DaoSupport<Cart> implements CartDao {

	@Override
	public void addCart(Cart cart) {
		save(cart);
	}

	@Override
	public void deleteCart(int cart_id) {
		delete(cart_id);

	}

	/*
	 * @Override public List<Cart> listCartsById(int user_id) { String hql =
	 * " from Cart as c where c.person_id.person_id='" + user_id + "'";
	 * List<Cart> list = queryByHql(hql); return list; }
	 */

	@Override
	public void updateCartById(Cart cart) {
		saveOrUpdate(cart);

	}

	@Override
	public List<Cart> listCartsByPerson(String name) {
		String hql = " from  Cart as c where c.person_id.name='" + name + "'";
		List<Cart> list = queryByHql(hql);
		return list;
	}

	@Override
	public List<Cart> listCartsByPerson(String name, long start, long size) {
		String hql = " from  Cart as c where c.person_id.name='" + name + "'";
		List<Cart> list = listRange(hql, start, size);
		return list;
	}

	@Override
	public void deleteCart(String name, int flower_id) {
		List<Cart> carts = queryCart(name, flower_id);
		if (carts != null && carts.size() > 0) {
			for (int i = 0; i < carts.size(); i++) {
				delete(carts.get(i));
			}
		}

	}

	@Override
	public void deleterCart(String name) {
		String hql = " from Cart as c where c.person_id.name='" + name + "'";
		List<Cart> carts = queryByHql(hql);
		if (carts != null && carts.size() > 0) {
			for (int i = 0; i < carts.size(); i++) {
				delete(carts.get(i));
			}
		}

	}

	@Override
	public List<Cart> queryCart(String name, int flower_id) {
		String hql = " from Cart as c where c.person_id.name='" + name + "' and c.flower_id.flower_id=" + flower_id
				+ " ";
		List<Cart> carts = queryByHql(hql);
		return carts;
	}

	@Override
	public long getUsersCartCount(String name) {
		String hql = " from Cart as c where c.person_id.name='" + name + "'";
		return getCount(hql);
	}

}

package com.lqinggang.dao.impl.users;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.users.UsersDao;
import com.lqinggang.entity.users.Users;

/**
 * @author LQingGang
 * @time 2018年3月20日 - 上午9:41:09
 */
@Repository(value = "userDao")
public class UsersDaoImpl extends DaoSupport<Users> implements UsersDao {

	@Override
	public void addUsers(Users user) {
		save(user);
	}

	@Override
	public void deleteUsersById(int userId) {
		delete(userId);

	}

	@Override
	public void deleteUsersByName(String userName) {
		List<Users> list = queryByHql(userName);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				delete(list.get(i).getUser_id().getPerson_id());
			}
		}
	}

	@Override
	public List<Users> queryUsersById(int userId) {
		String hql = "from Users as u where u.user_id.person_id = " + userId + "";
		return queryByHql(hql);
	}

//	@Override
//	public Users queryByUsersId(int userid) {
//		String hql = "from Users as u where u.user_id.person_id = " + userid + "";
//		List<Users> users = queryByHql(hql);
//		if (users != null && users.size() > 0) {
//			return users.get(0);
//		}
//		return null;
//	}

	@Override
	public void updateUsers(Users users) {
		saveOrUpdate(users);
	}

	@Override
	public List<Users> listAllUsers() {
		return listAll(Users.class);
	}

}

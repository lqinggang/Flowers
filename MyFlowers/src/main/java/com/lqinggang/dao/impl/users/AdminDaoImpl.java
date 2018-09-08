package com.lqinggang.dao.impl.users;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.users.AdminDao;
import com.lqinggang.entity.users.Admin;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 上午11:43:40
 */
@Repository(value = "adminDao")
public class AdminDaoImpl extends DaoSupport<Admin> implements AdminDao {

	@Override
	public void addAdmin(Admin admin) {
		save(admin);
	}

	@Override
	public void deleteAdminByAdminId(int admin_id) {
		delete(admin_id);
	}

	@Override
	public List<Admin> listAllAdmins() {
		List<Admin> list = queryByHql(" from Admin ");
		return list;
	}

	@Override
	public List<Admin> queryAdminsByAdminId(int admin_id) {
		List<Admin> admins = queryByHql(" from Admin as a where a.admin_id.person_id='" + admin_id + "'");
		if (admins != null) {
			return admins;
		}
		return null;
	}

	@SuppressWarnings("unused")
	@Override
	public Timestamp getLoginDateByAdminId(int admin_id) {
		List<Admin> admin = queryAdminsByAdminId(admin_id);
		if (admin != null && admin.size() > 0) {
			for (int i = 0; i < admin.size(); i++) {
				return admin.get(i).getLast_login();
			}
		}
		return null;
	}

	@Override
	public void updateAdmin(Admin admin) {
		saveOrUpdate(admin);
	}

	@Override
	public List<Admin> findAdmins(String name, String password) {
		String hql = " from Admin as a where a.admin_id.name='" + name + "' and a.admin_id.password='" + password + "'";
		return queryByHql(hql);
	}

}

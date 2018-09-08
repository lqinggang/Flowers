package com.lqinggang.dao.impl.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.common.AdminLogsDao;
import com.lqinggang.entity.common.AdminLogs;

/**
 * @author LQingGang
 * @time 2018年4月21日 - 下午10:02:53
 */
@Repository(value = "adminLogsDao")
public class AdminLogsDaoImpl extends DaoSupport<AdminLogs> implements AdminLogsDao {

	@Override
	public void addLogs(AdminLogs logs) {
		save(logs);
	}

	@Override
	public void deleteLogs(int logs_id) {
		delete(logs_id);
	}

	@Override
	public AdminLogs queryLogs(int logs_id) {
		String hql = " from AdminLogs as a where a.logs_id='" + logs_id + "' ";

		List<AdminLogs> adminLogs = queryByHql(hql);

		if (adminLogs != null && adminLogs.size() > 0) {
			return adminLogs.get(0);
		}
		return null;
	}

	@Override
	public List<AdminLogs> listAdminLogs(int admin_name) {
		String hql = " from AdminLogs as a where a.person_id.name='" + admin_name + "' ";
		return queryByHql(hql);
	}

	@Override
	public List<AdminLogs> listAllLogs() {
		return listAll(AdminLogs.class);
	}

	@Override
	public List<AdminLogs> listRangeLos(long start, long size) {
		return listRange(AdminLogs.class, start, size);
	}

	@Override
	public void updateLogs(AdminLogs adminLogs) {
		saveOrUpdate(adminLogs);

	}

	@Override
	public long getLogsCount() {
		return getCount();
	}

}

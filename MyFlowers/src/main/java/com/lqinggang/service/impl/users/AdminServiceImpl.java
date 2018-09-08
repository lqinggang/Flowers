package com.lqinggang.service.impl.users;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lqinggang.common.util.MD5Util;
import com.lqinggang.dao.common.AdminLogsDao;
import com.lqinggang.dao.users.AdminDao;
import com.lqinggang.dao.users.PersonDao;
import com.lqinggang.entity.common.AdminLogs;
import com.lqinggang.entity.users.Admin;
import com.lqinggang.entity.users.Person;
import com.lqinggang.service.users.AdminService;

/**
 * @author LQingGang
 * @time 2018年3月20日 - 下午02:28:43
 */
@Transactional
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private PersonDao personDao;
	@Autowired
	private AdminLogsDao adminLogsDao;

	@Override
	public void addAdmin(Admin admin) {
		try {
			adminDao.addAdmin(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAdminByAdminId(int admin_id) {
		adminDao.deleteAdminByAdminId(admin_id);
	}

	@Override
	public List<Admin> listAllAdmins() {
		return adminDao.listAllAdmins();
	}

	@Override
	public List<Admin> queryAdminsByAdminId(int admin_id) {
		return adminDao.queryAdminsByAdminId(admin_id);
	}

	@Override
	public Timestamp getLoginDateByAdminId(int admin_id) {
		return adminDao.getLoginDateByAdminId(admin_id);
	}

	@Override
	public Timestamp getLoginDateByAdminName(String admin_name) {
		List<Person> persons = personDao.queryPersonByName(admin_name);
		if (persons != null && persons.size() > 0) {
			for (int i = 0; i < persons.size();) {
				return adminDao.getLoginDateByAdminId(persons.get(i).getPerson_id());
			}
		}
		return null;
	}

	@Override
	public void updateAdmin(Admin admin) {
		adminDao.updateAdmin(admin);
	}

	@SuppressWarnings("unused")
	@Override
	public List<Admin> queryAdminsByName(String name) {
		List<Person> persons = personDao.queryPersonByName(name);
		if (persons != null && persons.size() > 0) {
			for (int i = 0; i < persons.size(); i++) {
				List<Admin> admins = adminDao.queryAdminsByAdminId(persons.get(0).getPerson_id());
				return admins;
			}
		}
		return null;
	}

	@Override
	public List<Admin> findAdmins(String name, String password) {
		return adminDao.findAdmins(name, MD5Util.toMD5(password));
	}

	@Override
	public void addLogs(AdminLogs adminLogs) {
		adminLogsDao.addLogs(adminLogs);
	}

	@Override
	public void deleteAdminByLogsId(int logs_id) {
		adminLogsDao.deleteLogs(logs_id);
	}

	@Override
	public List<AdminLogs> listAllLogs() {
		return adminLogsDao.listAllLogs();
	}

	@Override
	public List<AdminLogs> listRangeLogs(long start, long size) {
		return adminLogsDao.listRangeLos(start, size);
	}

	@Override
	public AdminLogs queryLogsByLogsId(int logs_id) {
		return adminLogsDao.queryLogs(logs_id);
	}

	@Override
	public void updateLogs(AdminLogs logs) {
		adminLogsDao.updateLogs(logs);
	}

	@Override
	public long getLogsCount() {
		return adminLogsDao.getLogsCount();
	}

}

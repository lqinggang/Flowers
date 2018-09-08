package com.lqinggang.service.users;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lqinggang.common.util.MD5Util;
import com.lqinggang.entity.users.Admin;
import com.lqinggang.entity.users.Person;

/**
 * @author LQingGang
 * @time 2018年3月23日 - 下午12:34:25
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@Rollback(false)
public class AdminServiceImplTest {

	@Autowired
	private AdminService adminService;
	@Autowired
	private UsersService usersService;

	/**
	 * @throws java.lang.Exception
	 * @return void
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 * @return void
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 新增一个管理员
	 * 
	 * @param admin
	 *            管理员
	 * @return void
	 */
	// @Test
	// public void addAdmin() {
	// Person person = new Person();
	// person.setName("addAdmin");
	// person.setPassword(MD5.toMD5("Password"));
	// usersService.addPersonInfo(person);
	// Admin admin = new Admin();
	// admin.setAdmin_id(person);
	// Date day = new Date();
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// admin.setLast_login(Timestamp.valueOf(df.format(day)));
	// adminService.addAdmin(admin);
	// }

	/**
	 * 查询所有的管理员
	 * 
	 * @return List<Admin>管理员列表
	 */
	@Test
	public void listAllAdmins() {
		List<Admin> admins = adminService.listAllAdmins();
		if (admins != null && admins.size() > 0) {
			for (int i = 0; i < admins.size(); i++) {
				System.out.println(admins.get(i).getAdmin_id().getName() + "  " + admins.get(i).getLast_login());
			}
		}
	}

	/**
	 * 通过管理员ID查询管理员
	 * 
	 * @param admin_id
	 *            管理员ID
	 * @return Admin 管理员
	 */
	@Test
	public void queryAdminsByAdminId() {
		adminService.queryAdminsByAdminId(1);
	}

	/**
	 * 通过管理员ID查询管理员上次登录时间
	 * 
	 * @param admin_id
	 *            管理员ID
	 * @return Timestamp 上次登录时间
	 */
	@Test
	public void getLoginDateByAdminId() {
		adminService.getLoginDateByAdminId(1);
	}

	/**
	 * 更新管理员信息
	 * 
	 * @param admin
	 *            管理员对象
	 * @return void //
	 */
	@Test
	public void updateAdmin() {
		List<Admin> admin = adminService.queryAdminsByAdminId(1);
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		admin.get(0).setLast_login(Timestamp.valueOf(df.format(day)));
		adminService.updateAdmin(admin.get(0));
	}

	/**
	 * 通过admin_id删除一个管理员
	 * 
	 * @param admin_id
	 *            管理员ID
	 * @return void
	 */
	public void deleteAdminByAdminId() {
		adminService.deleteAdminByAdminId(1);
	}

}

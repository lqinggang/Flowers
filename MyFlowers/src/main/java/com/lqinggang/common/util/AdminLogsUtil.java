package com.lqinggang.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.lqinggang.entity.common.AdminLogs;
import com.lqinggang.service.users.AdminService;

/**
 * 后台管理生成日志工具
 * 
 * @author LQingGang
 * @time 2018年4月21日 - 下午10:41:24
 */
public class AdminLogsUtil {

	@Autowired
	private static AdminService adminService;

	public static void addLogs(HttpServletRequest request, HttpSession session, String content) {
		AdminLogs adminLogs = new AdminLogs();
		String name = session.getAttribute("adminName").toString();

		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		adminLogs.setPerson_id(adminService.queryAdminsByName(name).get(0).getAdmin_id());
		adminLogs.setDate(Timestamp.valueOf(df.format(day)));
		adminLogs.setIp(IpAddressUtil.getIpAddr(request));
		adminLogs.setContent(content);
		adminService.addLogs(adminLogs);

	}
}

package com.lqinggang.common.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 在线用户监听
 * 
 * @author LQingGang
 * @time 2018年4月15日 - 下午10:37:50
 */
public class OnlineUsersListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
	// 声明一个ServletContext对象
	private ServletContext application = null;
	private static int count = 0;

	public void contextInitialized(ServletContextEvent sce) {
		// 容器初始化时，向application中存放一个空的容器
		this.application = sce.getServletContext();
		this.application.setAttribute("count", count);

	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// 将用户名称从列表中删除
		// @SuppressWarnings("unchecked")
		// List<String> list = (List<String>)
		// this.application.getAttribute("onlineUsersList");
		// String name = (String) se.getSession().getAttribute("username");
		if (se.getSession().getAttribute("username") == null) {
			if (count > 0) {
				application.setAttribute("count", --count);
			}
			// System.out.println("在线人数：" + count);
		} /*
			 * else { System.out.println("!=null"); }
			 */
		// this.application.setAttribute("onlineUsersList", list);
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
	}
}

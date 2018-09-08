package com.lqinggang.common.controller;

/**
 * @author LQingGang
 * @time 2018年3月17日 - 下午12:40:02
 */
public class BaseController {
	public BaseController() {
	}

	/**
	 * 获取页面的路径
	 * 
	 * @param path
	 * @return String
	 */
	public static String getViewPath(String path) {
		return path != null && !path.trim().equals("") ? path : "";
	}

}

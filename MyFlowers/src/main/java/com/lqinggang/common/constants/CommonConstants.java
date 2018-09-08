package com.lqinggang.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LQingGang
 * @time 2018年3月24日 - 下午6:20:00
 */
public class CommonConstants {

	// 鲜花图片地址
	public static String FLOWRES_IMG_URL = "static/pages/images/flowers";
	// 导出地址
	public static String EXPORT_LOCATION = "export/";
	// 手机号码正则表达式
	public static final String PHONE_NUMBER_REG = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
	// Email正则表达式
	public static final String EMAIL_REG = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	// 鲜花类别映射
	public static final Map<String, Integer> categotyMap = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 2853963654451394653L;

		{
			put("节日鲜花", 1);
			put("婚礼鲜花", 2);
			put("爱情鲜花", 3);
			put("探望慰问", 4);
			put("问候长辈", 5);
			put("其他鲜花", 6);
		}
	};
}

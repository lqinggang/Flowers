package com.lqinggang.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpSession;

/**
 * @author LQingGang
 * @time 2018年3月20日 - 上午11:47:51
 */
public class GenericsUtils {
	/**
	 * 获取泛型的类型
	 * 
	 * @param clazz
	 * @return Class
	 */
	@SuppressWarnings("unchecked")
	public static Class<Object> getGenericType(Class<?> clazz) {
		Type genType = clazz.getGenericSuperclass();// 得到泛型父类
		Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
		if (!(types[0] instanceof Class)) {
			return Object.class;
		}
		return (Class<Object>) types[0];
	}

	/**
	 * 获取对象的类名称
	 * 
	 * @param clazz
	 * @return 类名称
	 */
	public static String getGenericName(Class<?> clazz) {
		return clazz.getSimpleName();
	}

	public static String getUserName(HttpSession session) {
		if (session.getAttribute("username") != null) {
			return session.getAttribute("username").toString().trim();
		}
		return null;
	}
}

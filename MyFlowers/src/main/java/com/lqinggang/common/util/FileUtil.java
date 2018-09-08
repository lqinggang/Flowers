package com.lqinggang.common.util;

import java.util.Date;
import java.util.Random;

/**
 * @author LQingGang
 * @time 2018年5月10日 - 下午3:57:31
 */
public class FileUtil {

	/**
	 * 文件重命名
	 * 
	 * @param oldName
	 * @return String
	 */
	public static String fileRename(String oldName) {
		Random r = new Random();
		Date d = new Date();
		String newName = oldName;
		// 获取后缀名
		String suffix = oldName.substring(oldName.indexOf('.'));
		newName = r.nextInt(99999999) + d.getTime() + suffix;
		return newName;
	}
}

package com.lqinggang.common.util;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 * 
 * @author LQingGang
 * @time 2018年3月21日 - 下午7:08:49
 */
public class MD5Util {
	public MD5Util() {
	}

	public static final String toMD5(String key) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = key.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				// 获取高4位,str[k]==byte0的高4位
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				// 获取低4位,str[k+1]==byte0的低4位
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}

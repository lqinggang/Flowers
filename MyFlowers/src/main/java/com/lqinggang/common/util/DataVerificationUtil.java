package com.lqinggang.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lqinggang.common.constants.CommonConstants;

/**
 * @author LQingGang
 * @time 2018年4月4日 - 下午3:56:24
 */
public class DataVerificationUtil {
	public DataVerificationUtil() {
	}

	public static boolean isUsersName(String name) {
		String regex = "/^[A-Za-z]+$/g"; // 至少包含一个英文字符
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name.trim());
		return matcher.matches();
	}

	public static boolean isAge(String age) {
		if (age != null && !"".equals(age.trim())) {
			try {
				int temp = Integer.parseInt(age);
				if (temp < 0 || temp > 150) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;

	}

	public static boolean isValidUserName(String username) {
		String regex = "^[A-Za-z]+$ ";
		if (username.length() < 4 || username.length() > 40) {
			return false;
		}
		if (username.matches(regex)) {
			return true;
		}
		return false;
	}

	public static boolean isTelephone(String telephone) {
		if (telephone != null && !"".equals(telephone.trim())) {
			Pattern pattern = Pattern.compile("1[0-9]{10}");
			Matcher matcher = pattern.matcher(telephone.trim());
			return matcher.matches();
		}
		return true;

	}

	public static boolean isEmail(String email) {
		if (email != null && !"".equals(email.trim())) {
			Pattern pattern = Pattern.compile(CommonConstants.EMAIL_REG);
			Matcher matcher = pattern.matcher(email.trim());
			return matcher.matches();
		}
		return true;

	}

	public static boolean isInteger(String number) {
		if (number != null && !"".equals(number.trim())) {
			try {
				Integer.valueOf(number.trim());
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	public static boolean notNull(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(str.trim());
		return matcher.matches();
	}

}

package com.lqinggang.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 订单编号生成
 * 
 * @author LQingGang
 * @time 2018年4月9日 - 上午11:23:22
 */
public class GenerateOrderIDUtil {

	public GenerateOrderIDUtil() {
		// TODO Auto-generated constructor stub
	}

	public static final String getOrderId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 当前系统时间，精确到秒
		String newDate = sdf.format(new Date());
		Random random = new Random();
		// 获取5位随机数
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		return newDate + String.valueOf(rannum);
	}
}

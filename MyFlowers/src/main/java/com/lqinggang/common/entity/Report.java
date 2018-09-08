package com.lqinggang.common.entity;

import java.io.Serializable;

/**
 * 统计报表实体类
 * 
 * @author LQingGang
 * @time 2018年4月27日 - 下午5:07:05
 */
public class Report implements Serializable {
	private static final long serialVersionUID = 5376677341654872707L;

	private Object name;
	private Object number;

	public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public Object getNumber() {
		return number;
	}

	public void setNumber(Object number) {
		this.number = number;
	}

	public Report() {

	}

	/**
	 * @param objects
	 * @param number
	 */
	public Report(Object objects, Object number) {
		this.name = objects;
		this.number = number;
	}

}

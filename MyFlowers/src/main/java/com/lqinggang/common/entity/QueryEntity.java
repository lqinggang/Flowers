package com.lqinggang.common.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author LQingGang
 * @time 2018年3月23日 - 上午9:24:24
 */
@SuppressWarnings("rawtypes")
public class QueryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9079740044069084569L;
	private String queryKey;
	private int type = 0; // 文章类型0:无限制
	private int count;// 查询数据量 0不限制 大于0限制
	private int status = 3; // 0:禁用 1:启用
	private Map<String, String> orderBy = new HashMap<>(); // 排序条件

	private Map eqParams = new HashMap();
	private Map likeStrings = new HashMap();

	private String dateKeyword;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date beginCreateTime;// 查询 开始添加时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endCreateTime;// 查询 结束添加时间

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Map<String, String> getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Map<String, String> orderBy) {
		this.orderBy = orderBy;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDateKeyword() {
		return dateKeyword;
	}

	public void setDateKeyword(String dateKeyword) {
		this.dateKeyword = dateKeyword;
	}

	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map getEqParams() {
		return eqParams;
	}

	public void setEqParams(Map eqParams) {
		this.eqParams = eqParams;
	}

	public Map getLikeStrings() {
		return likeStrings;
	}

	public void setLikeStrings(Map likeStrings) {
		this.likeStrings = likeStrings;
	}
}

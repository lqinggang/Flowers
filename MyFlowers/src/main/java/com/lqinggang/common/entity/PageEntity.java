package com.lqinggang.common.entity;

import java.util.List;

/**
 * 分页实体
 * 
 * @author LQingGang
 * @time 2018年4月1日 - 下午6:17:50
 */
public class PageEntity<T> {
	private long totalPages; // 总页数
	private long totalRecords; // 总记录数
	private List<T> list; // 当前页结果集
	private long currentPage = 0; // 当前页(默认第1页)
	private int pageSize = 30; // 每页显示记录数
	@SuppressWarnings("unused")
	private int nextPage;
	@SuppressWarnings("unused")
	private int previousPage;
	@SuppressWarnings("unused")
	private int firstPage;
	@SuppressWarnings("unused")
	private int lastPage;

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages() {
		if (getTotalRecords() <= getPageSize()) {
			this.totalPages = 1;
		} else if (getTotalRecords() % getPageSize() != 0) {
			this.totalPages = getTotalRecords() / getPageSize() + 1;
		} else {
			this.totalPages = getTotalRecords() / getPageSize();
		}
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long count) {
		this.totalRecords = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long page) {
		this.currentPage = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// -------------------------------------
	public int getFirstPage() {
		return 1;
	}

	public long getLastPage() {
		return getTotalPages();
	}

	public long getNextPage() {
		if (getCurrentPage() >= totalPages) {
			return totalPages;
		}
		return getCurrentPage() + 1;
	}

	public long getPreviousPage() {
		if (getCurrentPage() <= 1) {
			return 1;
		}
		return getCurrentPage() - 1;
	}

}

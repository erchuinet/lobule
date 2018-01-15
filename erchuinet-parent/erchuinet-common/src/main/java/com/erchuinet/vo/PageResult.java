package com.erchuinet.vo;

import java.util.List;

public class PageResult<T> {
	/**
	 * 总记录数
	 */
	private Integer total;

	/**
	 * 结果集列表
	 */
	private List<T> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	
}

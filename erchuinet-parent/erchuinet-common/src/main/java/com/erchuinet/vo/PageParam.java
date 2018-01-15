package com.erchuinet.vo;

public class PageParam<T>
{
	/**
	 * 每页显示的最大记录数
	 */
	private int rows;
	
	/**
	 * 开始记录数
	 */
	private int page;
	
	/**
	 * 截止记录数
	 */
	private int end;
	
	/**
	 * 排序字段
	 */
	private String order;
	
	/**
	 * 排序方式
	 */
	private String sort;
	
	/**
	 * 查询条件对象
	 */
	private T t;

	public int getEnd()
	{
		end = page * rows;
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() 
	{
//		return (page - 1) * rows;
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
}

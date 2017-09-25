package com.yum.card.model;

public class Pager {
	private int pageSize;	
	private int rowCount;
	private int pageCount;
	private int pageNow;
	
	public Pager() {		
		super();
		this.pageSize = 11;
	}
	
	public Pager(int pageSize) {
		super();
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	
}

package com.gasinfo.util;

public class PageRoll {
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage(int pageNum) {
		return pageNum*6;
	}
	private int pageSize=6;
}

package com.lihaogn.vo;

import java.util.List;

public class PageBean {

	// 当前页
	private int currentPage;
	// 当前页显示的条数
	private int currentCount;
	// 总条数
	private int totalCount;
	// 总页数
	private int totalPage;
	// 每页显示的数据
	private List<PageBeanFood> foodList;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<PageBeanFood> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<PageBeanFood> foodList) {
		this.foodList = foodList;
	}
	
	
}

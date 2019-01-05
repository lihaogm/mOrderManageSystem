package com.lihaogn.domain;

import java.util.List;

public class OrderInfoWX {

	private String orderId;
	private String userName;
	private String orderTime;
	private List<Food> orderContentList;
	private short orderDeskId;
	private short orderPeopleNum;
	private int orderTotalPrice;
	private String orderRemarks;

	

	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getOrderTime() {
		return orderTime;
	}



	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}



	public List<Food> getOrderContentList() {
		return orderContentList;
	}



	public void setOrderContentList(List<Food> orderContentList) {
		this.orderContentList = orderContentList;
	}



	public short getOrderDeskId() {
		return orderDeskId;
	}



	public void setOrderDeskId(short orderDeskId) {
		this.orderDeskId = orderDeskId;
	}



	public short getOrderPeopleNum() {
		return orderPeopleNum;
	}



	public void setOrderPeopleNum(short orderPeopleNum) {
		this.orderPeopleNum = orderPeopleNum;
	}



	public int getOrderTotalPrice() {
		return orderTotalPrice;
	}



	public void setOrderTotalPrice(int orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}



	public String getOrderRemarks() {
		return orderRemarks;
	}



	public void setOrderRemarks(String orderRemarks) {
		this.orderRemarks = orderRemarks;
	}



	/**
	 * private String orderId; 
	 * private String userName; 
	 * private String orderTime;
	 * private List<Food> orderContentList; 
	 * private short orderTableNum; 
	 * private short orderPeopleNum; 
	 * private int orderTotalPrice; 
	 * private String orderRemarks;
	 * 
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "orderInfo[orderId: " + orderId + "\r\n" + "userName: " + userName + "\r\n" + "orderTime: " + orderTime
				+ "\r\n" + "orderTableNum: " + orderDeskId + "\r\n" + "orderPeopleNum:" + orderPeopleNum + "\r\n" + "orderTotalPrice: "
				+ orderTotalPrice + "\r\n" + "orderRemarks: " + orderRemarks + "]";
	}
}

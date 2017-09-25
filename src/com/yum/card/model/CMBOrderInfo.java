package com.yum.card.model;

public class CMBOrderInfo {
	private String orderid;
	private String brand;
	private long amount;
	private int count;
	private String time;
	private String status;
	private String cardbin;
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCardbin() {
		return cardbin;
	}
	public void setCardbin(String cardbin) {
		this.cardbin = cardbin;
	}
	
	
}

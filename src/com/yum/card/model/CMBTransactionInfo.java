package com.yum.card.model;

public class CMBTransactionInfo {
	
	private String cardbin;
	private String orderid;
	private String storeid;
	private String transtime;
	private long beforebalance;
	private long afterbalance;	
	private long transamount;
	//-----------------------------
	private String transactionType;
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	//-----------------------------
	public String getCardbin() {
		return cardbin;
	}
	public void setCardbin(String cardbin) {
		this.cardbin = cardbin;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public String getTranstime() {
		return transtime;
	}
	public void setTranstime(String transtime) {
		this.transtime = transtime;
	}
	public long getBeforebalance() {
		return beforebalance;
	}
	public void setBeforebalance(long beforebalance) {
		this.beforebalance = beforebalance;
	}
	public long getAfterbalance() {
		return afterbalance;
	}
	public void setAfterbalance(long afterbalance) {
		this.afterbalance = afterbalance;
	}
	public long getTransamount() {
		return transamount;
	}
	public void setTransamount(long transamount) {
		this.transamount = transamount;
	}
	
	
}

package com.yum.card.model;

public class OrderInfo {
	private String orderid;
	private String issuerbrandid;
	private long amount;
	private String purchasetime;
	private String orderstatus;
	private String cardbin;
	private int cardstatus;
	private String personaccountinfo;
	private String receiver;
	private boolean paymentcard;
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getIssuerbrandid() {
		return issuerbrandid;
	}
	public void setIssuerbrandid(String issuerbrandid) {
		this.issuerbrandid = issuerbrandid;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getPurchasetime() {
		return purchasetime;
	}
	public void setPurchasetime(String purchasetime) {
		this.purchasetime = purchasetime;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getCardbin() {
		return cardbin;
	}
	public void setCardbin(String cardbin) {
		this.cardbin = cardbin;
	}
	public int getCardstatus() {
		return cardstatus;
	}
	public void setCardstatus(int cardstatus) {
		this.cardstatus = cardstatus;
	}
	public String getPersonaccountinfo() {
		return personaccountinfo;
	}
	public void setPersonaccountinfo(String personaccountinfo) {
		this.personaccountinfo = personaccountinfo;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public boolean getPaymentcard() {
		return paymentcard;
	}
	public void setPaymentcard(boolean paymentcard) {
		this.paymentcard = paymentcard;
	}
	
}

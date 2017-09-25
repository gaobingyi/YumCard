package com.yum.card.model;

public class AccountTransactionInfo {
	private String orderId;
	private String paymentAccount;
	private String userCode;
	private String issuerBrandId;
	private String transactionId;//账户交易凭证号
	private long amount;
	private String storeId;
	private String orderStatus;
	private String originalTransId;
	private String acqDateTime;//交易时间
	private String cards;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPaymentAccount() {
		return paymentAccount;
	}
	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getIssuerBrandId() {
		return issuerBrandId;
	}
	public void setIssuerBrandId(String issuerBrandId) {
		this.issuerBrandId = issuerBrandId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getAcqDateTime() {
		return acqDateTime;
	}
	public void setAcqDateTime(String acqDateTime) {
		this.acqDateTime = acqDateTime;
	}
	public String getCards() {
		return cards;
	}
	public void setCards(String cards) {
		this.cards = cards;
	}
	public String getOriginalTransId() {
		return originalTransId;
	}
	public void setOriginalTransId(String originalTransId) {
		this.originalTransId = originalTransId;
	}


}

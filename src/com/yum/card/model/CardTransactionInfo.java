package com.yum.card.model;

public class CardTransactionInfo {
	private String cardBin;
	private String orderId;
	private String transactionId;
	private long beforeBalance;
	private long afterBalance;
	private long amount;
	private String lastUpdateTime;//交易时间
	
	public String getCardBin() {
		return cardBin;
	}
	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public long getBeforeBalance() {
		return beforeBalance;
	}
	public void setBeforeBalance(long beforeBalance) {
		this.beforeBalance = beforeBalance;
	}
	public long getAfterBalance() {
		return afterBalance;
	}
	public void setAfterBalance(long afterBalance) {
		this.afterBalance = afterBalance;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}	
}

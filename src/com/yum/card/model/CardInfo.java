package com.yum.card.model;
/*
 * users和payment中的卡信息表内容一样
 */
public class CardInfo {
	private String cardBin;
	private String purchaseTime;
	private long cardAmount;
	private long balance;
	private int currentStatus;
	private boolean isactivate;
	private String issuerBrandId;
	private String validThru;
	private boolean isRevoked;
	private boolean isBound;
	private boolean isFrozen;
	
	public String getCardBin() {
		return cardBin;
	}
	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}
	public String getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public long getCardAmount() {
		return cardAmount;
	}
	public void setCardAmount(long cardAmount) {
		this.cardAmount = cardAmount;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public int getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}
	public boolean getIsactivate() {
		return isactivate;
	}
	public void setIsactivate(boolean isactivate) {
		this.isactivate = isactivate;
	}
	public String getIssuerBrandId() {
		return issuerBrandId;
	}
	public void setIssuerBrandId(String issuerBrandId) {
		this.issuerBrandId = issuerBrandId;
	}
	public String getValidThru() {
		return validThru;
	}
	public void setValidThru(String validThru) {
		this.validThru = validThru;
	}
	public boolean getIsRevoked() {
		return isRevoked;
	}
	public void setIsRevoked(boolean b) {
		this.isRevoked = b;
	}
	public boolean getIsBound() {
		return isBound;
	}
	public void setIsBound(boolean isBound) {
		this.isBound = isBound;
	}
	public boolean getIsFrozen() {
		return isFrozen;
	}
	public void setIsFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}	
}

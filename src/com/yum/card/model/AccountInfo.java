package com.yum.card.model;

public class AccountInfo {
	private String userCode;
	private String issuerBrandId;
	private String mobile;
	private long accountBalance;
	private boolean isFrozen;
	
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public long getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}
	public boolean getIsFrozen() {
		return isFrozen;
	}
	public void setIsFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}
}

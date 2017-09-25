package com.yum.card.model;

public class CardConversionInfo {
	String userCode;
	String mobile;
	String issuerBrandId;
	String pcardbin;
	String ecardbin;
	long balance;
	String transtime;//??
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIssuerBrandId() {
		return issuerBrandId;
	}
	public void setIssuerBrandId(String issuerBrandId) {
		this.issuerBrandId = issuerBrandId;
	}
	public String getPcardbin() {
		return pcardbin;
	}
	public void setPcardbin(String pcardbin) {
		this.pcardbin = pcardbin;
	}
	public String getEcardbin() {
		return ecardbin;
	}
	public void setEcardbin(String ecardbin) {
		this.ecardbin = ecardbin;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getTranstime() {
		return this.transtime;
	}
	public void setTranstime(String transtime) {
		this.transtime = transtime;
	}
}

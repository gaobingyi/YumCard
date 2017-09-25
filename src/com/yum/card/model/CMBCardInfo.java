package com.yum.card.model;

public class CMBCardInfo {
	private String paymentCode;
	private String cardbin;
	private String carddesc;
	private String brand;
	private long cardamount;
	private long balance;
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getCardbin() {
		return cardbin;
	}
	public void setCardbin(String cardbin) {
		this.cardbin = cardbin;
	}
	public String getCarddesc() {
		return carddesc;
	}
	public void setCarddesc(String carddesc) {
		this.carddesc = carddesc;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public long getCardamount() {
		return cardamount;
	}
	public void setCardamount(long cardamount) {
		this.cardamount = cardamount;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	
}

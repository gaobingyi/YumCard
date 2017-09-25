package com.yum.card.model;

public class AdjustBalanceResult {
	private String account;
	private long account_balance_before;//调整前账户余额
	private long account_balance_after;//调整后账户余额
	private String brand;
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	private String card;	
	private long card_balance_before;//调整前卡余额
	private long card_balance_after;//调整后卡余额
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public long getAccount_balance_before() {
		return account_balance_before;
	}
	public void setAccount_balance_before(long account_balance_before) {
		this.account_balance_before = account_balance_before;
	}
	public long getAccount_balance_after() {
		return account_balance_after;
	}
	public void setAccount_balance_after(long account_balance_after) {
		this.account_balance_after = account_balance_after;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public long getCard_balance_before() {
		return card_balance_before;
	}
	public void setCard_balance_before(long card_balance_before) {
		this.card_balance_before = card_balance_before;
	}
	public long getCard_balance_after() {
		return card_balance_after;
	}
	public void setCard_balance_after(long card_balance_after) {
		this.card_balance_after = card_balance_after;
	}
}

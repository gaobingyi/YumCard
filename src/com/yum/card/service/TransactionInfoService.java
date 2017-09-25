package com.yum.card.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.yum.card.model.AccountTransactionInfo;
import com.yum.card.model.CardTransactionInfo;
import com.yum.card.model.Pager;
import com.yum.card.util.JDBCUtil;

public class TransactionInfoService {
	
	private Connection conn;
	private Statement sm;
	private ResultSet rs;
	public Pager getPage(String queryParameter, String last) {
		Pager pager= new Pager();
		try {
			System.out.println("before connect getpager");
			conn = JDBCUtil.getConn("payment");
			System.out.println("after connect getpager");
			sm = conn.createStatement();
			System.out.println("1");
			String sql = "select count(*) from t_account_transaction where "
					+ "paymentaccount = \'" + queryParameter + "\'";
			if (last != null) {
				
			}
			System.out.println(sql);//´òÓ¡SQLÓï¾ä
			System.out.println("2");
			rs = sm.executeQuery(sql);
			System.out.println("3");
			if (rs.next()) {
				if (last != null) {
					pager.setRowCount(1);
				} else {
					pager.setRowCount(rs.getInt(1));
					System.out.println("all records: "+rs.getInt(1));
					if (pager.getRowCount() % pager.getPageSize() == 0) {
						pager.setPageCount(pager.getRowCount() / pager.getPageSize());
					} else {
						pager.setPageCount(pager.getRowCount() / pager.getPageSize() + 1);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}	
		return pager;
	}
	public ArrayList<AccountTransactionInfo> getAccountTransactionInfo(String queryParameter, Pager pager, String last) {
		ArrayList<AccountTransactionInfo> accountTransactionInfoList = new ArrayList<AccountTransactionInfo>();
		try {
			System.out.println("before connect getaccounttransactioninfo");
			conn = JDBCUtil.getConn("payment");
			System.out.println("after connect getaccounttransactioninfo");
			sm = conn.createStatement();
			String sql = "select orderid, transactionid, storeid, issuerbrandid, orderstatus, "
					+ "originaltransid, acqdatetime, paymentaccount, amount from "
					+ "t_account_transaction where paymentaccount in (\'"
					+ queryParameter + "\') order by acqdatetime desc ";
			if (last != null) {
				sql = sql + "limit 1 offset 0";
			} else {
				sql = sql + "limit " + pager.getPageSize()
				+ " offset " + (pager.getPageNow()-1)*pager.getPageSize();
			}			
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			//½«½á¹û¼¯·â×°µ½ArrayListÖÐ
			while(rs.next()) {
				System.out.println("SQLÈ¡³öÖµÁËÑ½£¡");
				AccountTransactionInfo accountTransactionInfo = 
						new AccountTransactionInfo();
				accountTransactionInfo.setOrderId(rs.getString(1));
				accountTransactionInfo.setTransactionId(rs.getString(2));
				accountTransactionInfo.setStoreId(rs.getString(3));
				accountTransactionInfo.setIssuerBrandId(rs.getString(4));
				accountTransactionInfo.setOrderStatus(rs.getString(5));
				System.out.println("¶©µ¥×´Ì¬£º" + rs.getString(5));
				/*accountTransactionInfo.setOriginalTransId(rs.getString(6));
				System.out.println("originalid: " + rs.getString(6));
				if (accountTransactionInfo.getOriginalTransId().equals("")) {
					System.out.println("originalid equals \"\"");					
				}
				if (accountTransactionInfo.getOriginalTransId() == "") {
					System.out.println("originalid == \"\"");					
				}
				if (accountTransactionInfo.getOriginalTransId() == null) {
					System.out.println("originalid equals null");					
				}*/
 
				try {
					accountTransactionInfo.setOriginalTransId(rs.getString(6));
				} catch (Exception e) {
					//e.printStackTrace();
				}
												
				accountTransactionInfo.setAcqDateTime(rs.getString(7));
				accountTransactionInfo.setPaymentAccount(rs.getString(8));
				accountTransactionInfo.setAmount(rs.getLong(9));
				accountTransactionInfo.setCards(getCardsByOrderid(rs.getString(1)));
				accountTransactionInfoList.add(accountTransactionInfo);
				System.out.println(accountTransactionInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}	
		return accountTransactionInfoList;
	}
	
	//¸ù¾Ý¶©µ¥ºÅ»ñÈ¡ËùÓÐ¿¨ºÅ£¬²¢×ª³É"2366232000001059362(12.00) 2366232000001157166(23.50)"
	private String getCardsByOrderid(String orderid) {
		String cards = "";		
		Connection conn = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn("payment");
			sm = conn.createStatement();
			String sql = "select cardbin,amount from t_card_transaction "
					+ "where orderid = \'" + orderid + "\'";
			System.out.println(sql);//´òÓ¡SQLÓï¾ä
			rs = sm.executeQuery(sql);
			while (rs.next()) { 
				cards = cards + rs.getString(1) + " (" + rs.getInt(2)/100.0 + ") ";
				System.out.println(cards);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}		
		return cards;		
	}
	public ArrayList<CardTransactionInfo> getCardTransactionInfo(String queryParameter) {
		ArrayList<CardTransactionInfo> cardTransactionInfoList = 
				new ArrayList<CardTransactionInfo>();
		try {
			conn = JDBCUtil.getConn("payment");
			sm = conn.createStatement();
			String sql = "select cardbin, orderid, transactionid, beforebalance, "
					+ "afterbalance, amount, lastupdatetime from t_card_transaction "
					+ "where cardbin in (\'" + queryParameter + "\')";			
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			//½«½á¹û¼¯·â×°µ½ArrayListÖÐ
			while(rs.next()) {
				CardTransactionInfo cardTransactionInfo = 
						new CardTransactionInfo();
				cardTransactionInfo.setCardBin(rs.getString(1));
				cardTransactionInfo.setOrderId(rs.getString(2));
				cardTransactionInfo.setTransactionId(rs.getString(3));
				cardTransactionInfo.setBeforeBalance(rs.getLong(4));
				cardTransactionInfo.setAfterBalance(rs.getLong(5));
				cardTransactionInfo.setAmount(rs.getLong(6));
				cardTransactionInfo.setLastUpdateTime(rs.getString(7));
				cardTransactionInfoList.add(cardTransactionInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}		
		return cardTransactionInfoList;
	}
}

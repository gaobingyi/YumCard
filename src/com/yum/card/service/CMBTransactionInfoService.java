package com.yum.card.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yum.card.model.CMBCardInfo;
import com.yum.card.model.CMBOrderInfo;
import com.yum.card.model.CMBTransactionInfo;
import com.yum.card.util.JDBCUtil;

public class CMBTransactionInfoService {
	private Connection conn;
	private Statement sm;
	private ResultSet rs;
	
	private static String cardbin;
	
	public CMBCardInfo getCardInfo(String input, String parameter_type) {
		CMBCardInfo cmbCardInfo = null;
		try {
			String sql = null;
			conn = JDBCUtil.getConn("users");
			sm = conn.createStatement();
			if (parameter_type.equals("payment_code")) {
				sql = "select paymentcode,cardbin,carddesc,issuerbrandid,cardamount,"
						+ "balance from t_card_info where paymentcode = \'"
						+ input + "\'";
			} else {
				sql = "select paymentcode,cardbin,carddesc,issuerbrandid,cardamount,"
						+ "balance from t_card_info where cardbin = \'"
						+ input + "\'";
			}			
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				cmbCardInfo = new CMBCardInfo();
				cmbCardInfo.setPaymentCode(rs.getString(1));
				cmbCardInfo.setCardbin(rs.getString(2));
				cardbin = rs.getString(2);
				cmbCardInfo.setCarddesc(rs.getString(3));
				cmbCardInfo.setBrand(rs.getString(4));
				cmbCardInfo.setCardamount(rs.getLong(5));
				cmbCardInfo.setBalance(rs.getLong(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}			
		return cmbCardInfo;
	}	
	
	public List<CMBTransactionInfo> getTransactionInfoList(String payment_code) {
		List<CMBTransactionInfo> cmbTransactionInfoList = new ArrayList<CMBTransactionInfo>();
		try {
			String sql = null;
			conn = JDBCUtil.getConn("users");
			sm = conn.createStatement();
			sql = "select cardbin,orderid,storeid,transtime,beforebalance,"
					+ "afterbalance,actualamount,originaltransid from t_card_transaction_info"
					+ " where cardbin = \'" + cardbin + "\' order by transtime desc";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			while (rs.next()) {
				CMBTransactionInfo cmbTransactioninfo = new CMBTransactionInfo();
				cmbTransactioninfo.setCardbin(rs.getString(1));
				cmbTransactioninfo.setOrderid(rs.getString(2));
				cmbTransactioninfo.setStoreid(rs.getString(3));
				cmbTransactioninfo.setTranstime(rs.getString(4));
				cmbTransactioninfo.setBeforebalance(rs.getLong(5));
				cmbTransactioninfo.setAfterbalance(rs.getLong(6));
				cmbTransactioninfo.setTransamount(rs.getLong(7));
				String originaltransid = rs.getString(8);
				if (originaltransid == null || "".equals(originaltransid)) {
					cmbTransactioninfo.setTransactionType("0");//消费
				} else {
					cmbTransactioninfo.setTransactionType("1");//退款
				}	
				cmbTransactionInfoList.add(cmbTransactioninfo);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}		
		return cmbTransactionInfoList;
	}	
	
	private String getCardbin(String orderid) {
		String cardbin = "";
		try {
			String sql = null;
			conn = JDBCUtil.getConn("users");
			sm = conn.createStatement();
			sql = "select cardbin from t_order_receiver_relation "
					+ "where orderid = \'" + orderid + "\'";			
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			while (rs.next()) {
				cardbin += rs.getString(1) + "<br>";
			}
			int length = cardbin.length();
			cardbin = cardbin.substring(0, length-4);
			System.out.println(cardbin);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}
		return cardbin;		
	}

	public CMBOrderInfo getOrderInfo(String input) {
		CMBOrderInfo cmbOrderInfo = null;
		try {
			String sql = null;
			conn = JDBCUtil.getConn("users");
			sm = conn.createStatement();
			sql = "select orderseqid,orderstatus,issuerbrandid,amount,"
					+ "totalcount,purchasetime from t_order_info where "
					+ "orderseqid = \'" + input + "\'";			
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				cmbOrderInfo = new CMBOrderInfo();
				cmbOrderInfo.setOrderid(rs.getString(1));
				cmbOrderInfo.setStatus(rs.getString(2));
				cmbOrderInfo.setBrand(rs.getString(3));
				cmbOrderInfo.setAmount(rs.getLong(4));
				cmbOrderInfo.setCount(rs.getInt(5));
				cmbOrderInfo.setTime(rs.getString(6));					
				cmbOrderInfo.setCardbin(getCardbin(input));
				System.out.println(cmbOrderInfo.getCardbin());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}		
		return cmbOrderInfo;
	}
}

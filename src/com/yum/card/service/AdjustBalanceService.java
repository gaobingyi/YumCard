package com.yum.card.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yum.card.model.AdjustBalanceResult;
import com.yum.card.util.JDBCUtil;

public class AdjustBalanceService {
	private Connection conn;
	private Statement sm;
	private ResultSet rs;
	
	public boolean isAccountExist(String account, String brand) {
		boolean flag = false;//Ĭ�ϲ�����
		try {
			conn = JDBCUtil.getConn_write("payment");
			sm = conn.createStatement();
			String sql = "select mobile from t_account_info where "
					+ "mobile in (\'" + account + "\') and issuerbrandid in (\'" + brand + "\')";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}
		return flag;
	}
	public AdjustBalanceResult getAccountExcuteResult(String account, String brand, int balance_i,
			String adjust_orientation) {
		AdjustBalanceResult adjustBalanceResult = new AdjustBalanceResult();
		long balance_before = 0;
		long balance_after = 0;
		try {
			conn = JDBCUtil.getConn_write("payment");
			sm = conn.createStatement();
			//��ѯ����ǰ�˻����
			String sql = "select mobile, accountbalance, issuerbrandid from t_account_info where mobile in (\'" 
					+ account + "\') and issuerbrandid in (\'" + brand + "\')";;
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				adjustBalanceResult.setAccount(rs.getString(1));				
				adjustBalanceResult.setAccount_balance_before(rs.getLong(2));
				balance_before = rs.getLong(2);
				adjustBalanceResult.setBrand(rs.getString(3));
			}
			if (adjust_orientation.equals("up")) {
				balance_after = balance_before + balance_i;
			} else {
				balance_after = balance_before - balance_i;
			}
			//�����˻����
			sql = "update t_account_info set accountbalance = " + balance_after 
					+ " where mobile =\'" + account + "\' and issuerbrandid = \'" + brand + "\'";
			System.out.println(sql);
			sm.executeUpdate(sql);
			//��ѯ������˻����
			sql = "select accountbalance from t_account_info where mobile in (\'" 
					+ account + "\') and issuerbrandid in (\'" + brand + "\')";;
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {				
				adjustBalanceResult.setAccount_balance_after(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}		
		return adjustBalanceResult;		
	}
	public boolean isCardExist(String cardbin) {
		boolean flag = false;//Ĭ�ϲ�����
		try {
			conn = JDBCUtil.getConn_write("payment");
			sm = conn.createStatement();
			String sql = "select cardbin from t_card_info where "
					+ "cardbin in (\'" + cardbin + "\')";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}
		return flag;
	}
	public AdjustBalanceResult getCardExcuteResult(String cardbin, int balance_i, String adjust_orientation) {
		AdjustBalanceResult adjustBalanceResult = new AdjustBalanceResult();
		long balance_before = 0;
		long balance_after = 0;
		try {
			conn = JDBCUtil.getConn_write("payment");
			sm = conn.createStatement();
			//��ѯ����ǰ�˻����
			String sql = "select cardbin, balance from t_card_info where cardbin in (\'" + cardbin + "\')";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				adjustBalanceResult.setCard(rs.getString(1));				
				adjustBalanceResult.setCard_balance_before(rs.getLong(2));
				balance_before = rs.getLong(2);
			}
			if (adjust_orientation.equals("up")) {
				balance_after = balance_before + balance_i;
			} else {
				balance_after = balance_before - balance_i;
			}
			//�����˻����
			sql = "update t_card_info set balance = " + balance_after 
					+ " where cardbin =\'" + cardbin + "\'";
			System.out.println(sql);
			sm.executeUpdate(sql);
			//��ѯ������˻����
			sql = "select balance from t_card_info where cardbin in (\'" + cardbin + "\')";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {				
				adjustBalanceResult.setCard_balance_after(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}		
		return adjustBalanceResult;	
	}	
}

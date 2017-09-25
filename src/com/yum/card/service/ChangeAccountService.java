package com.yum.card.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yum.card.model.AccountInfo;
import com.yum.card.util.JDBCUtil;

public class ChangeAccountService {
	private Connection conn;
	private Statement sm;
	private ResultSet rs;
	
	public boolean isOldAccountExist(String account_old, String brand) {
		boolean flag = false;//Ĭ�ϲ�����
		try {
			conn = JDBCUtil.getConn_write("payment");
			sm = conn.createStatement();
			String sql = "select mobile from t_account_info where "
					+ "mobile in (\'" + account_old + "\') and issuerbrandid in (\'" + brand + "\')";
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

	public boolean isNewAccountHasBalance(String account_new, String brand) {
		boolean flag = false;//Ĭ��û���������˻�������
		try {
			conn = JDBCUtil.getConn_write("payment");
			sm = conn.createStatement();
			String sql = "select accountbalance from t_account_info where "
					+ "mobile in (\'" + account_new + "\') and issuerbrandid in (\'" + brand + "\')";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				if (rs.getLong(1) > 0) {
					flag = true;
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}
		return flag;
	}

	public AccountInfo getExcuteResult(String account_old, String account_new, String brand) {
		AccountInfo accountInfo = new AccountInfo();
		//1.ɾ��(�����)���˻���(4�ű�)
		//2.��ԭ�˻�(�����)�ֻ�Ÿ�Ϊ���ֻ��(4�ű�)
		//users:t_user_info
		try {
			conn = JDBCUtil.getConn_write("users");
			sm = conn.createStatement();
			String sql = "delete from t_user_info where mobile in (\'" 
			+ account_new + "\') and issuerbrandid in (\'" + brand + "\')";
			System.out.println(sql);
			sm.executeUpdate(sql);
			sql = "update t_user_info set mobile = \'" + account_new + 
					"\' where mobile = \'" + account_old +"\' and issuerbrandid in (\'" + brand + "\')"; 
			System.out.println(sql);
			sm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}
		//payment:t_account_info
		try {
			conn = JDBCUtil.getConn_write("payment");
			sm = conn.createStatement();
			String sql = "delete from t_account_info where mobile in (\'" 
			+ account_new + "\') and issuerbrandid in (\'" + brand + "\')";
			System.out.println(sql);
			sm.executeUpdate(sql);
			sql = "update t_account_info set mobile = \'" + account_new + 
					"\' where mobile = \'" + account_old +"\' and issuerbrandid in (\'" + brand + "\')"; 
			System.out.println(sql);
			sm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}
		//egcmc:user_login/c_sso
		try {
			conn = JDBCUtil.getConn_egcms();
			sm = conn.createStatement();
			//user_login
			String sql = "delete from user_login where c_username in (\'" 
			+ account_new + "\') and c_store_id in (\'210010" + brand + "\')";
			System.out.println(sql);
			sm.executeUpdate(sql);
			sql = "update user_login set c_username = \'" + account_new + 
					"\' where c_username = \'" + account_old + "\' "
					+ "and c_store_id in (\'210010" + brand + "\')"; 
			System.out.println(sql);
			sm.executeUpdate(sql);
			//c_sso
			sql = "delete from c_sso where sso in (\'" + account_new + "\') "
					+ "and brand = (\'210010" + brand + "\')";
			System.out.println(sql);
			sm.executeUpdate(sql);
			sql = "update c_sso set sso = \'" + account_new +
					"\' where sso = \'" + account_old + "\' "
					+ "and brand = (\'210010" + brand + "\')";
			System.out.println(sql);
			sm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}
		//3.�����˻���Ϣ(���)����
		try {
			conn = JDBCUtil.getConn_write("payment");
			sm = conn.createStatement();
			String sql = "select mobile, accountbalance, issuerbrandid from t_account_info"
					+ " where mobile in (\'" + account_new + "\') and "
							+ "issuerbrandid in (\'" + brand + "\')";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				accountInfo.setMobile(rs.getString(1));
				accountInfo.setAccountBalance(rs.getLong(2));
				accountInfo.setIssuerBrandId(rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}
		return accountInfo;
	}	
}

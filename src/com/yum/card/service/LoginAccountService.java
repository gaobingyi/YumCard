package com.yum.card.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yum.card.model.LoginAccount;
import com.yum.card.util.JDBCUtil;

public class LoginAccountService {
	private Connection conn;
	private Statement sm;
	private ResultSet rs;
	
	public LoginAccount modify(String account, String brand, 
			String login_password, String login_type) {
		LoginAccount loginAccount = new LoginAccount();
		try {
			conn = JDBCUtil.getConn_egcms();
			sm = conn.createStatement();
			String sql = null;			
			if (login_type.equals("A")) {
				System.out.println("hehe");
				//����ԭʼ��¼����
				sql = "select current_password, c_login_type from user_login where "
						+ "c_username = \'" + account + "\' and c_store_id "
								+ "= \'" + brand + "\'";
				System.out.println(sql);
				rs = sm.executeQuery(sql);
				if (rs.next()) {
					loginAccount.setOriginal_login_password(rs.getString(1));
					loginAccount.setOriginal_login_type(rs.getString(2));
					System.out.println(rs.getString(1) + " " + rs.getString(2));
				}
				//�滻��¼����
				sql = "update user_login set current_password = \'" + 
				login_password + "\', " + "c_login_type = \'F\' where "
				+ "c_username = \'" + account + "\' and c_store_id = \'" + brand + "\'";
				System.out.println(sql);
				sm.executeUpdate(sql);
			} else {
				//�滻��¼����
				sql = "update user_login set current_password = \'" + 
				login_password + "\', " + "c_login_type = \'" + login_type + "\' where "
				+ "c_username = \'" + account + "\' and c_store_id = \'" + brand + "\'";
				System.out.println(sql);
				sm.executeUpdate(sql);
			}
			
			//��ȡ��ǰ��¼����
			sql = "select current_password, c_login_type from user_login where "
					+ "c_username = \'" + account + "\' and c_store_id "
					+ "= \'" + brand + "\'";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				loginAccount.setCurrent_login_password(rs.getString(1));
				loginAccount.setCurrent_login_type(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}
		
		return loginAccount;
	}

	public boolean isAccountExist(String account, String brand) {
		boolean flag = false;//Ĭ�ϲ�����
		try {
			conn = JDBCUtil.getConn_egcms();
			sm = conn.createStatement();
			String sql = "select c_username from user_login "
					+ "where c_username = \'" + account + "\' and "
							+ "c_store_id = \'" + brand + "\'";
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
	
	
}

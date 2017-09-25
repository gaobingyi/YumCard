package com.yum.card.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.yum.card.model.AccountInfo;
import com.yum.card.util.JDBCUtil;

public class AccountInfoService {
	private Connection conn;
	private Statement sm;
	private ResultSet rs;
	
	public ArrayList<AccountInfo> getAccountInfo(String queryParameter, String parameterType, String brand_new) {
		ArrayList<AccountInfo> accountInfoList = new ArrayList<AccountInfo>();
		try {
			conn = JDBCUtil.getConn("payment");
			sm = conn.createStatement();
			String sql = "select usercode, issuerbrandid, mobile, accountbalance,"
					+ " isfrozen from t_account_info where ";
			if (parameterType.equals("mobile")) {
				sql = sql + "mobile in (\'" + queryParameter + "\')";
			} else if (parameterType.equals("usercode")) {
				sql = sql + "usercode in (\'" + queryParameter + "\')";
			} else {
				sql += "id in (select accountseqid from t_account_card_relation "
						+ "where cardseqid in (select id from t_card_info where "
						+ "cardbin in (\'" + queryParameter + "\')))";
			}
			sql += " and issuerbrandid in (" + brand_new + ")";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			//将结果集封装到ArrayList中
			while(rs.next()) {
				AccountInfo accountInfo = new AccountInfo();
				accountInfo.setUserCode(rs.getString(1));
				accountInfo.setIssuerBrandId(rs.getString(2));
				accountInfo.setMobile(rs.getString(3));	
				accountInfo.setAccountBalance(rs.getLong(4));	
				accountInfo.setIsFrozen(rs.getBoolean(5));					
				accountInfoList.add(accountInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}	
		return accountInfoList;
	}
	
}

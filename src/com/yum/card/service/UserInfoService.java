package com.yum.card.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.yum.card.model.UserInfo;
import com.yum.card.util.JDBCUtil;

public class UserInfoService {
	private Connection conn;
	private Statement sm;
	private ResultSet rs;
	
	//获取用户信息
	public ArrayList<UserInfo> getUserInfo(String queryParameter, 
			String parameterType, String brand) {
		ArrayList<UserInfo> userInfoList = new ArrayList<UserInfo>();
		try {
			conn = JDBCUtil.getConn("users");
			sm = conn.createStatement();
			String sql = "select usercode,issuerBrandId,mobile from t_user_info where ";
			if (parameterType.equals("mobile")) {
				sql += "mobile in (\'" + queryParameter + "\')";
			} else if (parameterType.equals("usercode")) {
				sql += "usercode in (\'" + queryParameter + "\')";
			} else {
				sql += "id in (select userseqid from t_user_own_card_relation "
						+ "where cardseqid in (select id from t_card_info where "
						+ "cardbin in (\'" + queryParameter + "\')))";
			}
			sql += " and issuerbrandid in (" + brand + ")";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			//将结果集封装到ArrayList中
			while(rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setUserCode(rs.getString(1));
				userInfo.setIssuerBrandId(rs.getString(2));
				userInfo.setMobile(rs.getString(3));			
				userInfoList.add(userInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}	
		return userInfoList;		
	}
}

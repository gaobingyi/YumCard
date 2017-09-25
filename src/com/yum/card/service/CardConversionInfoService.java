package com.yum.card.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yum.card.model.CardConversionInfo;
import com.yum.card.util.JDBCUtil;

public class CardConversionInfoService {
	private Connection conn;
	private Statement sm;
	private ResultSet rs;

	public CardConversionInfo getCardConversionInfo(String queryParameter, String parameterType) {
		CardConversionInfo cardConversionInfo = null;
		try {
			conn = JDBCUtil.getConn("users");
			sm = conn.createStatement();
			String sql = "select usercode,mobile,issuerbrandid,pcardbin,ecardbin,balance,transtime "
					+ "from t_card_conversion "
					+ "where "+ parameterType + " in (\'"
					+ queryParameter
					+ "\')";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				cardConversionInfo = new CardConversionInfo();
				cardConversionInfo.setUserCode(rs.getString(1));
				System.out.println(sql);
				cardConversionInfo.setMobile(rs.getString(2));
				cardConversionInfo.setIssuerBrandId(rs.getString(3));
				cardConversionInfo.setPcardbin(rs.getString(4));
				cardConversionInfo.setEcardbin(rs.getString(5));
				cardConversionInfo.setBalance(rs.getLong(6));
				cardConversionInfo.setTranstime(rs.getString(7));							
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}	
		return cardConversionInfo;	
	}	
}

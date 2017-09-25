package com.yum.card.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.yum.card.model.CardInfo;
import com.yum.card.model.Pager;
import com.yum.card.util.JDBCUtil;

public class CardInfoService {
	//数据库连接（可以将参数封装成对象）
	private Connection conn;
	private Statement sm;
	private ResultSet rs;

	//获取分页信息（rowCount，pageCount）
	public Pager getPager(String queryParameter_new, String parameterType) {
		Pager pager= new Pager();
		try {
			conn = JDBCUtil.getConn("users");
			sm = conn.createStatement();
			/*String sql = "select count(*) from t_card_info where "
					+ parameterType + " in ("
					+ queryParameter + ")";*/
			String sql = null;
			if (parameterType.equals("cardbin")) {
				sql = "select count(*) from t_card_info where cardbin in ("+ queryParameter_new +")";
			} else {
				sql = "select count(*) from t_card_info where id in ("
						+ "select cardseqid from t_user_own_card_relation"
						+ " where userseqid in (select id from t_user_info"
						+ " where mobile in ("+ queryParameter_new +")))";
			}
			System.out.println("SQL语句:" + sql);
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				pager.setRowCount(rs.getInt(1));
				if (pager.getRowCount() % pager.getPageSize() == 0) {
					pager.setPageCount(pager.getRowCount() / pager.getPageSize());
				} else {
					pager.setPageCount(pager.getRowCount() / pager.getPageSize() + 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}	
		return pager;
	}
	
	//获取查询结果
	public ArrayList<CardInfo> getCardInfo(String queryParameter_new, String parameterType, Pager pager) {
		ArrayList<CardInfo> cardInfoList = new ArrayList<CardInfo>();
		try {
			conn = JDBCUtil.getConn("users");
			sm = conn.createStatement();
			/*String sql = "select cardbin,purchasetime,cardamount,balance,currentstatus,isactivate, issuerbrandid,validthru,isrevoked,isbound,isfrozen "
					+ "from t_card_info "
					+ "where " + parameterType + " in ("
					+ queryParameter + ") "
					+ "limit " + pager.getPageSize()
					+ " offset " + (pager.getPageNow()-1)*pager.getPageSize();*/
			String sql = "select cardbin,purchasetime,cardamount,balance,currentstatus,"
					+ "isactivate,issuerbrandid,validthru,isrevoked,isbound,isfrozen "
					+ "from t_card_info where ";
			if (parameterType.equals("cardbin")) {
				sql += "cardbin in (" + queryParameter_new + ")";
			} else {
				sql +=  " id in (select cardseqid from t_user_own_card_relation where "
						+ "userseqid in (select id from t_user_info where mobile in ("
						+ queryParameter_new + ")))";
			}
			sql += "limit " + pager.getPageSize() + " offset " + 
					(pager.getPageNow()-1)*pager.getPageSize();
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			//将结果集封装到ArrayList中
			while(rs.next()) {
				CardInfo cardInfo = new CardInfo();
				cardInfo.setCardBin(rs.getString(1));
				cardInfo.setPurchaseTime(rs.getString(2));
				cardInfo.setCardAmount(rs.getLong(3));
				cardInfo.setBalance(rs.getLong(4));
				cardInfo.setCurrentStatus(rs.getInt(5));				
				cardInfo.setIsactivate(rs.getBoolean(6));				
				cardInfo.setIssuerBrandId(rs.getString(7));				
				cardInfo.setValidThru(rs.getString(8));				
				cardInfo.setIsRevoked(rs.getBoolean(9));				
				cardInfo.setIsBound(rs.getBoolean(10));				
				cardInfo.setIsFrozen(rs.getBoolean(11));				
				cardInfoList.add(cardInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}	
		return cardInfoList;
	}	
}

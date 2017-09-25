package com.yum.card.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.yum.card.model.OrderInfo;
import com.yum.card.model.Pager;
import com.yum.card.util.JDBCUtil;

public class OrderInfoService {
	//���ݿ����ӣ����Խ�������װ�ɶ���
	private Connection conn;
	private Statement sm;
	private ResultSet rs;
	
	//��ȡ��ҳ��Ϣ
	public Pager getPager(String queryParameter_new, String parameterType) {
		Pager pager= new Pager();
		try {
			conn = JDBCUtil.getConn("users");
			sm = conn.createStatement();
			String sql = null;
			System.out.println("�������ͣ�"+parameterType);
			if (parameterType.equals("orderseqid")) {
				sql = "select count(*) from t_order_info where orderseqid in ("+ queryParameter_new +")";
			} else if (parameterType.equals("cardbin")) {
				sql = "select count(*) from t_order_receiver_relation where cardbin in ("+ queryParameter_new +")";
			} else {
				sql = "select count(*) from t_user_info where mobile in ("+ queryParameter_new +")";
			}
			System.out.println(sql);//��ӡSQL���
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

	public ArrayList<OrderInfo> getOrderInfo(String queryParameter_new, String parameterType, Pager pager) {
		ArrayList<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		try {
			conn = JDBCUtil.getConn("users");
			sm = conn.createStatement();
			String sql = "select orderseqid, oi.issuerbrandid, amount, purchasetime, "
					+ "orderstatus, cardbin, oi.personaccountinfo, orr.receiver from "
					+ "t_order_info as oi left join t_order_receiver_relation as orr "
					+ "on oi.orderseqid = orr.orderid where ";
			if (parameterType.equals("orderseqid")) {
				sql = sql + "orderseqid in (" + queryParameter_new + ") ";
			} else if (parameterType.equals("cardbin")) {
				sql = sql + "cardbin in (" + queryParameter_new + ") ";
			} else {
				sql = sql + "oi.personaccountinfo in (select usercode "
						+ "from t_user_info where mobile in (" 
						+ queryParameter_new + ")) ";
			}
			sql = sql + "limit " + pager.getPageSize()
					+ " offset " + (pager.getPageNow()-1)*pager.getPageSize();
			System.out.println(sql);//��ӡSQL���
			rs = sm.executeQuery(sql);
			//���������װ��ArrayList��
			while(rs.next()) {
				System.out.println("SQLȡ��ֵ��ѽ��");
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setOrderid(rs.getString(1));
				orderInfo.setIssuerbrandid(rs.getString(2));
				orderInfo.setAmount(rs.getLong(3));
				orderInfo.setPurchasetime(rs.getString(4));
				orderInfo.setOrderstatus(rs.getString(5));				
				orderInfo.setCardbin(rs.getString(6));				
				orderInfo.setCardstatus(getCardStstus(rs.getString(6)));//��״̬				
				orderInfo.setPersonaccountinfo(getMoileByUsercode(rs.getString(7)));				
				orderInfo.setReceiver(getMoileByUsercode(rs.getString(8)));				
				orderInfo.setPaymentcard(isPaymentHave(rs.getString(6)));//payment����							
				orderInfoList.add(orderInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.releaseConn(rs, sm, conn);
		}	
		return orderInfoList;
	}
	
	//��ȡ��״̬
	private int getCardStstus(String cardbin) {
		int cardStstus = -1;
		if (cardbin != null) {
			Connection conn = null;
			Statement sm = null;
			ResultSet rs = null;
			try {
				conn = JDBCUtil.getConn("users");
				sm = conn.createStatement();
				String sql = "select currentstatus from t_card_info where "
						+ "cardbin in (\'"+ cardbin +"\')";
				System.out.println(sql);//��ӡSQL���
				rs = sm.executeQuery(sql);
				if (rs.next()) {
					cardStstus = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCUtil.releaseConn(rs, sm, conn);
			}	
			return cardStstus;
		}		
		return cardStstus;
	}
	
	//��ȡpayment���Ƿ��п�
	private boolean isPaymentHave(String cardbin) {
		boolean isHave = false;
		if (cardbin != null) {
			Connection conn = null;
			Statement sm = null;
			ResultSet rs = null;
			try {
				conn = JDBCUtil.getConn("payment");
				sm = conn.createStatement();
				String sql = "select id from t_card_info where "
						+ "cardbin in (\'"+ cardbin +"\')";
				System.out.println(sql);//��ӡSQL���
				rs = sm.executeQuery(sql);
				if (rs.next()) {
					isHave = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCUtil.releaseConn(rs, sm, conn);
			}	
			return isHave;
		}		
		return isHave;
	}
	
	//����usercode��ȡmobile
	private String getMoileByUsercode(String usercode) {
		String mobile = null;
		if (usercode != null) {
			Connection conn = null;
			Statement sm = null;
			ResultSet rs = null;
			try {
				conn = JDBCUtil.getConn("users");
				sm = conn.createStatement();
				String sql = "select mobile from t_user_info where "
						+ "usercode in (\'"+ usercode +"\')";
				System.out.println(sql);//��ӡSQL���
				rs = sm.executeQuery(sql);
				if (rs.next()) {
					mobile = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCUtil.releaseConn(rs, sm, conn);
			}	
			return mobile;
		}		
		return mobile;
	}
}

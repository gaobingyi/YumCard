package com.yum.card.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {

	private static Connection conn;	
	
	// Connect to 172.31.77.62 (read)
	public static Connection getConn(String database) {		
		new WebInitUtil();
		String db_driver = WebInitUtil.getDb_driver();
		String db_url_read = WebInitUtil.getDb_url_read() + database;
		String db_user_read = WebInitUtil.getDb_user_read();
		String db_passwd_read = WebInitUtil.getDb_passwd_read();
		
		try {
			Class.forName(db_driver);
			try {
				conn = DriverManager.getConnection(db_url_read, db_user_read, db_passwd_read);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// Connect to 172.31.77.61 (write)
	public static Connection getConn_write(String database) {			
		new WebInitUtil();
		String db_driver = WebInitUtil.getDb_driver();
		String db_url_write = WebInitUtil.getDb_url_write() + database;
		String db_user_write = WebInitUtil.getDb_user_write();
		String db_passwd_write = WebInitUtil.getDb_passwd_write();
		
		try {
			Class.forName(db_driver);
			try {
				conn = DriverManager.getConnection(db_url_write, db_user_write, db_passwd_write);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// Connect to 172.31.77.59 (egcms)
	public static Connection getConn_egcms() {
		new WebInitUtil();
		String db_driver = WebInitUtil.getDb_driver();
		String db_url_egcms = WebInitUtil.getDb_url_egcms() + "egcms";
		String db_user_egcms = WebInitUtil.getDb_user_egcms();
		String db_passwd_egcms = WebInitUtil.getDb_passwd_egcms();
		
		try {
			Class.forName(db_driver);
			try {
				conn = DriverManager.getConnection(db_url_egcms, db_user_egcms, db_passwd_egcms);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/*public static Connection getConn_uat(String database) {
		
		String db_driver = "org.postgresql.Driver";
		String db_url = "jdbc:postgresql://172.29.165.54:5432/" + database;
		String db_user = "postgres";
		String db_passwd = "EGC+CREDIT";
		
		try {
			Class.forName(db_driver);
			try {
				conn = DriverManager.getConnection(db_url, db_user, db_passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConn_uat_egcms() {
		try {
			Class.forName("org.postgresql.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:postgresql://172.29.165.54:5432/egcms", "postgres", "EGC+CREDIT");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}*/
	
	//�ͷ���ݿ�����
	public static void releaseConn(ResultSet rs, Statement sm, Connection conn) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(sm != null) {
				sm.close();
				sm = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

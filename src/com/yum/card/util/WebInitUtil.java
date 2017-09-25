package com.yum.card.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class WebInitUtil implements ServletContextListener { 
	private static String db_driver;
	private static String db_url_read;	
	private static String db_user_read;
	private static String db_passwd_read;
	private static String db_url_write;	
	private static String db_user_write;
	private static String db_passwd_write;
	private static String db_url_egcms;	
	private static String db_user_egcms;
	private static String db_passwd_egcms;
    
    public void contextDestroyed(ServletContextEvent arg0) {  
    	
    }  
      
    public void contextInitialized(ServletContextEvent arg0) {
    	PropertiesUtil prop = new PropertiesUtil();
        db_driver = prop.getProperties("db_driver");
        db_url_read = prop.getProperties("db_url_read");
        db_url_write = prop.getProperties("db_url_write");
        db_url_egcms = prop.getProperties("db_url_egcms");
        String user_read = prop.getProperties("db_user_read");
        String user_write = prop.getProperties("db_user_write");
        String user_egcms = prop.getProperties("db_user_egcms");
        String passwd_read = prop.getProperties("db_passwd_read");
        String passwd_write = prop.getProperties("db_passwd_write");
        String passwd_egcms = prop.getProperties("db_passwd_egcms");
        try {
			db_user_read = new DesUtils().decrypt(user_read);
			db_user_write = new DesUtils().decrypt(user_write);
			db_user_egcms = new DesUtils().decrypt(user_egcms);
			db_passwd_read = new DesUtils().decrypt(passwd_read);
			db_passwd_write = new DesUtils().decrypt(passwd_write);
			db_passwd_egcms = new DesUtils().decrypt(passwd_egcms);
		} catch (Exception e) {
			e.printStackTrace();
		}		
    }

	public static String getDb_driver() {
		return db_driver;
	}

	public static void setDb_driver(String db_driver) {
		WebInitUtil.db_driver = db_driver;
	}

	public static String getDb_url_read() {
		return db_url_read;
	}

	public static void setDb_url_read(String db_url_read) {
		WebInitUtil.db_url_read = db_url_read;
	}

	public static String getDb_user_read() {
		return db_user_read;
	}

	public static void setDb_user_read(String db_user_read) {
		WebInitUtil.db_user_read = db_user_read;
	}

	public static String getDb_passwd_read() {
		return db_passwd_read;
	}

	public static void setDb_passwd_read(String db_passwd_read) {
		WebInitUtil.db_passwd_read = db_passwd_read;
	}

	public static String getDb_url_write() {
		return db_url_write;
	}

	public static void setDb_url_write(String db_url_write) {
		WebInitUtil.db_url_write = db_url_write;
	}

	public static String getDb_user_write() {
		return db_user_write;
	}

	public static void setDb_user_write(String db_user_write) {
		WebInitUtil.db_user_write = db_user_write;
	}

	public static String getDb_passwd_write() {
		return db_passwd_write;
	}

	public static void setDb_passwd_write(String db_passwd_write) {
		WebInitUtil.db_passwd_write = db_passwd_write;
	}

	public static String getDb_url_egcms() {
		return db_url_egcms;
	}

	public static void setDb_url_egcms(String db_url_egcms) {
		WebInitUtil.db_url_egcms = db_url_egcms;
	}

	public static String getDb_user_egcms() {
		return db_user_egcms;
	}

	public static void setDb_user_egcms(String db_user_egcms) {
		WebInitUtil.db_user_egcms = db_user_egcms;
	}

	public static String getDb_passwd_egcms() {
		return db_passwd_egcms;
	}

	public static void setDb_passwd_egcms(String db_passwd_egcms) {
		WebInitUtil.db_passwd_egcms = db_passwd_egcms;
	}
   
}

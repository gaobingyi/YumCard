package com.yum.card.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	public String getProperties(String key) {
		String value = null;
		InputStream in = null;
		try {
        	Properties prop = new Properties();
        	in = this.getClass().getClassLoader().getResourceAsStream("/conf.properties");
            prop.load(in);
            value = prop.getProperty(key);            
        }
        catch(Exception e) {
            System.out.println(e);
        } finally {
    		try {
    			if (in != null)
    				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }		
		return value;		
	}
	
	public String getProperties2(String key) throws IOException {
		String value = null;
		String basepathString = this.getClass().getResource("/").getPath();
		String file = basepathString.replace("%20", " ") + "code.properties";
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		
        Properties prop = new Properties();  
        //FileInputStream fis = new FileInputStream("/conf.properties");
        // ´ÓÊäÈëÁ÷ÖÐ¶ÁÈ¡ÊôÐÔÁÐ±í£¨¼üºÍÔªËØ¶Ô£©
        prop.load(in);
        value = prop.getProperty(key);   
        in.close();
		return value;		
	}
	
	public void setProperties(String key,String value) throws IOException {	
		String basepathString = this.getClass().getResource("/").getPath();
		String file = basepathString.replace("%20", " ") + "code.properties";
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		
        Properties prop = new Properties();  
        //FileInputStream fis = new FileInputStream("/conf.properties");
        // ´ÓÊäÈëÁ÷ÖÐ¶ÁÈ¡ÊôÐÔÁÐ±í£¨¼üºÍÔªËØ¶Ô£©
        prop.load(in);
        // µ÷ÓÃ Hashtable µÄ·½·¨ put¡£Ê¹ÓÃ getProperty ·½·¨Ìá¹©²¢ÐÐÐÔ¡£
        // Ç¿ÖÆÒªÇóÎªÊôÐÔµÄ¼üºÍÖµÊ¹ÓÃ×Ö·û´®¡£·µ»ØÖµÊÇ Hashtable µ÷ÓÃ put µÄ½á¹û¡£
        OutputStream fos = new FileOutputStream(file);
        prop.setProperty(key, value);
        // ÒÔÊÊºÏÊ¹ÓÃ load ·½·¨¼ÓÔØµ½ Properties ±íÖÐµÄ¸ñÊ½£¬
        // ½«´Ë Properties ±íÖÐµÄÊôÐÔÁÐ±í£¨¼üºÍÔªËØ¶Ô£©Ð´ÈëÊä³öÁ÷
        prop.store(fos,null);
        //¹Ø±ÕÎÄ¼þ
        in.close();
        fos.close();
    }
}

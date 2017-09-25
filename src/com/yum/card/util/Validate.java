package com.yum.card.util;

import java.io.IOException;

public class Validate {
	public boolean valiCode(String operationCode) throws IOException {
		PropertiesUtil propertiesUtil= new PropertiesUtil();
		String operation_code = propertiesUtil.getProperties2("operation_code");
		System.out.println(operation_code);
		String[] code = operation_code.split("&");
		for (int i=0; i<code.length; i++) {
			System.out.println(code[i]);
		}		
		for (int i=0; i<code.length; i++) {
			if (code[i].equals(operationCode)) {
				return true;
			}
		}		
		return false;
	}
}

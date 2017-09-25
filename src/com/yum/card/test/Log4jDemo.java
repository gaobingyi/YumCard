package com.yum.card.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jDemo {
	private static Logger logger = LogManager
			.getLogger(Log4jDemo.class.getName());
	public static void main(String[] args) {
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");		
		logger.error("This is an error message");
		
		new Log4jDemo2().out();
	}
}

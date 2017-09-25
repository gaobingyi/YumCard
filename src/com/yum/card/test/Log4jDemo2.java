package com.yum.card.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jDemo2 {
	static final Logger logger = LogManager.
			getLogger(Log4jDemo2.class.getName());
	public void out() {
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");		
		logger.error("This is an error message");
	}
}

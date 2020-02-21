package com.chatter.web.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chatterconstants.ChatterConstants;


public class ApplicationProperties {
	
	private static Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);
	
	private static Properties properties = new Properties();
	
	public static void loadProperties(){
		try {
			properties.load(ApplicationProperties.class.getClassLoader().getResourceAsStream(ChatterConstants.CHATTER_APPCONFIG_PROPERTIES));
			properties.load(ApplicationProperties.class.getClassLoader().getResourceAsStream(ChatterConstants.CHATTER_SYSTEM_PROPERTIES));
			properties.load(ApplicationProperties.class.getClassLoader().getResourceAsStream(ChatterConstants.CHATTER_EMAIL_PROPERTIES));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}		 
	}
	
	public static String get(String key){
		
		return properties.getProperty(key);
	}
}

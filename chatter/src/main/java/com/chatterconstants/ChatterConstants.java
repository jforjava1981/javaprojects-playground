package com.chatterconstants;
/**
 * Constants used in Application.
 *  
 * @author shailesh
 *
 */
public class ChatterConstants {
	/**
	 *  format of dates used in application {@value}
	 *  MM -- used for 2 digit month , e.g. 02 or 12
	 *  dd -- used for 2 digit date, e.g. 02 or 31
	 *  YYYY -- used for 4 digit year , e.g. 1981
	 */
	public static final String CHATTER_DATE_FORMAT_MM_DD_YYYY = "MM/dd/YYYY";
	/**
	 *  format of dates used in application {@value}
	 *  MM -- used for 2 digit month , e.g. 02 or 12
	 *  dd -- used for 2 digit date, e.g. 02 or 31
	 *  YYYY -- used for 4 digit year , e.g. 1981
	 */
	public static final String CHATTER_DATE_FORMAT_YYYY_MM_dd = "YYYY-MM-dd hh:mm:ss";
	/**
	 *  location of  system level configuration properties file {@value}
	 *  This file lists all system level properties
	 * 
	 */
	public static final String CHATTER_SYSTEM_PROPERTIES = "/properties/config/system.properties";
	/**
	 *  location of all application level configuration properties file {@value}
	 *  This file lists all application level configuration properties
	 */
	public static final String CHATTER_APPCONFIG_PROPERTIES = "/properties/config/appconfig.properties";
	/**
	 *  location of all email configuration properties file {@value}
	 *  This file lists all email configuration properties
	 */
	public static final String CHATTER_EMAIL_PROPERTIES = "/properties/config/email.properties";
	/**
	 *  {@value} is used as a key in properties file {@value #CHATTER_APPCONFIG_PROPERTIES}<br> 
	 *  value of this key indicates physical path on  Web server where item images are stored<br>
	 *  this path is actual physical directory path 
	 *  
	 */
	/**
	 * boolean status to indicates value
	 * {@value} as {@link java.lang.String}  
	 */
	public static final String CHATTER_STATUS_TRUE = "true";
	/**
	 * boolean status to indicate value
	 * {@value} as {@link java.lang.String}  
	 */
	public static final String CHATTER_STATUS_FALSE = "false";
	/**
	 * boolean status to indicate value
	 * {@value} as boolean {@literal}  
	 */
	public static final Boolean CHATTER_STATUS_BOOLEAN_TRUE = true;
	/**
	 * boolean status to indicate value
	 * {@value} as boolean {@literal}  
	 */
	public static final Boolean CHATTER_STATUS_BOOLEAN_FALSE = false;
	/**
	 * boolean status to indicate value
	 * {@value} as {@link java.lang.String}  
	 */
	public static final String CHATTER_STATUS_Y = "Y";
	/**
	 * boolean status to indicate value
	 * {@value} as {@link java.lang.String}  
	 */
	public static final String CHATTER_STATUS_N = "N";
	/**
	 * Role name used for user of type admin/administrator {@value} 
	 */
	public static final String CHATTER_ROLE_USER = "USER";
	/**
	 * default password used whenever a user is created.
	 * {@value} 
	 */
	public static final String CHATTER_DEFAULT_PASSWORD = "PAssword123";
	
	/**
	 * Role name used for user of type admin/administrator {@value} 
	 */
	public static final String CHATTER_ROLE_ADMIN = "ADMIN";
	
	
	
}

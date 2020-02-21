package com.chatter.exception;

import java.util.UUID;

/**
 * This class has a single static method which generates a unique id based on
 * time.
 *
 * @author chatter dev team
 *  
 */
public class ExceptionIDGenerator {

	/**
	 * Constructor level description goes here.
	 *
	 * @throws Exception
	 *             Exceptions written in throws clause
	 */
	public ExceptionIDGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * generates a unique id based on time.
	 *
	 * @return String generates a unique id based on UUID.
	 * @see Related links.
	 * @since
	 */
	public static String getExceptionID() {
		String exceptionId = UUID.randomUUID().toString();
		return exceptionId;
	}
}

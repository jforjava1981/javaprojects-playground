package com.chatter.exception;

/**
 * BusinessException class is the CHECKED exception that carries list of error<br>
 * codes <br>
 * 1) Wraps all business error codes for validation errors <br>
 * 2) Wraps all checked/application exceptions from Data access layer 
 *
 * @author shailesh
 * 
 */
public class BusinessException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7132613319121469325L;

	/**
	 * Constructor level description goes here.
	 *
	 * @throws Exception
	 *             Exceptions written in throws clause
	 */
	public BusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor level description goes here.
	 * @param eString -- exception as string 
	 */
	public BusinessException(String eString) {
		super(eString);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor level description goes here.
	 * @param eString -- exception message 
	 * @param t -- throwable
	 */
	public BusinessException(String eString, Throwable t) {
		super(eString, t);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor level description goes here.
	 *
	 */
	public BusinessException(Throwable t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

}

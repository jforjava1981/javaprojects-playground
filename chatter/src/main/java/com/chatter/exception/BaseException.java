package com.chatter.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseException is a abstract/base class of all application Exceptions and can
 * be used as the base exception to throw from business logic tier whenever a
 * application exception occurs. It keeps track of the exception if it is logged
 * or not and also stores the unique id, so that it can be carried all along to
 * the client tier and displayed to the end user. The end user can call up the
 * customer support using this number.
 * 
 * @author chatter dev team
 * 
 */
public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8586899246261708258L;

	// -- unqiue incident id to reference the incident/error both in log file
	// and customer care
	protected String uniqueID = null;

	// -- List of error codes ie business rule failure codes list
	protected List<String> businessItems = null;

	// -- cause of exception
	protected Throwable immediateCause = null;

	/**
	 * returns List of error messages added througout the hierarchy of exception
	 * propagation
	 * 
	 * @return Returns the businessItems Collection.
	 * @throws Exception
	 *             Exceptions written in throws clause.
	 * @see Related links.
	 * @since
	 */
	public List<String> getBusinessItems() {
		return businessItems;
	}

	/**
	 * Method level description goes here.
	 * 
	 * @param businessItems
	 *            The businessItems to set.
	 * @exception Exception
	 *                Exceptions written in throws clause.
	 * @see Related links.
	 * @since
	 */
	public void setBusinessItems(List<String> businessItems) {
		this.businessItems = businessItems;
	}

	/**
	 * Getter Method level description goes here.
	 * 
	 * @return Returns the cause Throwable.
	 * @throws Exception
	 *             Exceptions written in throws clause.
	 * @see Related links.
	 * @since
	 */
	public Throwable getCause() {
		return immediateCause;
	}

	/**
	 * Method level description goes here.
	 * 
	 * @param cause
	 *            The cause to set.
	 * @exception Exception
	 *                Exceptions written in throws clause.
	 * @see Related links.
	 * @since
	 */
	public void setImmediateCause(Throwable cause) {
		this.immediateCause = cause;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	/**
	 * Constructor level description goes here.
	 * 
	 * @param param1
	 *            Description about param1
	 * @param param2
	 *            Description about param2
	 * @throws Exception
	 *             Exceptions written in throws clause
	 * @see Related links
	 * @since
	 */
	public BaseException() {
		super();
		uniqueID = ExceptionIDGenerator.getExceptionID();
		businessItems = new ArrayList<String>();
	}

	/**
	 * Constructor level description goes here.
	 * 
	 * @param eString -- exception message
	 * 
	 */
	public BaseException(String eString) {
		super(eString);
		uniqueID = ExceptionIDGenerator.getExceptionID();
		businessItems = new ArrayList<String>();
		businessItems.add(eString);
	}

	/**
	 * Constructor level description goes here.
	 * 
	 * @param eString -- exception message
	 *            
	 * @param exception
	 *            cause of the exception
	 * 
	 */
	public BaseException(String eString, Throwable exception) {
		super(eString, exception);

		// -- Check if the exception is of type BaseException
		// -- if yes, then DO NOT create another unqiueID and add all
		// -- the businessItems i.e. error messages to it plus
		// -- set the logged status accordingly
		if (exception instanceof BaseException) {
			BaseException lclException = (BaseException) exception;
			uniqueID = lclException.getUniqueID();
			businessItems = new ArrayList<String>();
			businessItems.addAll(lclException.getBusinessItems());
			immediateCause = exception;
		} else {
			uniqueID = ExceptionIDGenerator.getExceptionID();
			businessItems = new ArrayList<String>();
			businessItems.add(eString);
			immediateCause = exception;
		}
	}

	/**
	 * Constructor level description goes here.
	 * 
	 * @param param1
	 *            Description about param1
	 * @param param2
	 *            Description about param2
	 * @throws Exception
	 *             Exceptions written in throws clause
	 * @see Related links
	 * @since
	 */
	public BaseException(Throwable exception) {
		super(exception);
		// -- Check if the exception is of type BaseException
		// -- if yes, then DO NOT create another unqiueID and add all
		// -- the businessItems i.e. error messages to it plus
		// -- set the logged status accordingly
		if (exception instanceof BaseException) {
			BaseException lclException = (BaseException) exception;
			uniqueID = lclException.getUniqueID();
			businessItems = new ArrayList<String>();
			businessItems.addAll(lclException.getBusinessItems());
			immediateCause = exception;
		} else {
			uniqueID = ExceptionIDGenerator.getExceptionID();
			businessItems = new ArrayList<String>();
			immediateCause = exception;
		}

	}

}

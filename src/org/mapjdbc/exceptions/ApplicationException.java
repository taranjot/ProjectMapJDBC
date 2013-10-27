package org.mapjdbc.exceptions;

import org.mapjdbc.constants.ApplicationConstants;


/**
 * The Application Exception is raised when an application has to stop functioning due to some error.
 * Please check and fill the error code when catching any kind of exception.
 * 
 * @author Taran
 * 
 * */
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer errCode;
	private String errorMessage;
	
	/**
	 * Throw an default exception only in case of errors.
	 * */
	public ApplicationException() {
		this.errCode = 99;
		this.errorMessage = ApplicationConstants.ERROR_MESSAGE;
	}
	
	/**
	 * @param errCode
	 * @param errorMessage
	 */
	
	public ApplicationException(Integer errCode, String errorMessage) {
		super();
		this.errCode = errCode;
		this.errorMessage = errorMessage;
	}

	public Integer getErrCode() {
		return errCode;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
	
	
	

}

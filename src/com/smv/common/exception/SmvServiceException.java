package com.smv.common.exception;

import java.io.Serializable;

import javax.xml.ws.WebFault;



@WebFault(name = "smvServiceException")
public class SmvServiceException extends Exception implements Serializable {

	private static final long serialVersionUID = 19595587587587505L;
	
	private Integer errorCode;
	private String errorMessage;
	private String description;
	
	public SmvServiceException() {
		super();
	}
	
	public SmvServiceException(Integer code, String message) {
		super();
		this.errorCode = code;
		this.errorMessage = message;
	}


	public SmvServiceException(Integer code, String message, String description) {
		super();
		this.errorCode = code;
		this.errorMessage = message;
		this.description = description;
	}
	
	public void setErrorCode(Integer statusCode) {
		this.errorCode = statusCode;
	}


	public Integer getErrorCode() {
		return errorCode;
	}


	public void setErrorMessage(String statusMessage) {
		this.errorMessage = statusMessage;
	}


	public String getErrorMessage() {
		return errorMessage;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	
}

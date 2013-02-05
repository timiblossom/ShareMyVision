package com.smv.common.bean;

public class ResponseDTO {
	private int statusCode;
	private String message;
	private Object value;
	
	public ResponseDTO() {}
	
	public ResponseDTO(int statusCode, String msg, Object value) {
		this.statusCode = statusCode;
		this.message = msg;
		this.value = value;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}

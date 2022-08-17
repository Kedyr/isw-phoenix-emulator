package com.interswitchug.phoenix.simulator.utils;

import com.interswitchug.phoenix.simulator.dto.PhoenixResponseCodes;


public class SystemApiException extends Exception {
	private static final long serialVersionUID = -1988373452396426046L;

	private final  String responseMessage;
	private final String responseCode;
	private Exception exception;

	
	public SystemApiException(String code,String message){
		super();
		this.responseMessage = message;
		this.responseCode = code;
		this.exception = null;
	}
	
	public SystemApiException(String code,String message,Exception exception){
		super();
		this.responseMessage = message;
		this.responseCode = code;
		this.exception = exception;
	}
	
	public SystemApiException(Exception exc){
		super();
		this.responseMessage =  exc.getMessage();
		this.responseCode = PhoenixResponseCodes.INTERNAL_ERROR.CODE;
		this.exception = exc;
	}
	

	

	
	public Exception getException() {
		return this.exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getMessage(){
		return this.responseMessage;
	}
	
	public String getCode(){
		return this.responseCode;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}

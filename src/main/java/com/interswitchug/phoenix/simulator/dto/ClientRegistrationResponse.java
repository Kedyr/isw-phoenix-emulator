package com.interswitchug.phoenix.simulator.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ClientRegistrationResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2922295347736640220L;
	private String transactionReference;
	private String authToken;
	private String serverSessionPublicKey;
	
	
	
	
	

	public String getServerSessionPublicKey() {
		return serverSessionPublicKey;
	}
	public void setServerSessionPublicKey(String serverSessionPublicKey) {
		this.serverSessionPublicKey = serverSessionPublicKey;
	}
	public String getTransactionReference() {
		return transactionReference;
	}
	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	
}

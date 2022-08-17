package com.interswitchug.phoenix.simulator.dto;

import java.io.Serializable;


public class LoginRequest extends ClientTerminalRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2799722798508464659L;
	private String password;
	private String clientSessionPublicKey;
	
	
	
	public String getClientSessionPublicKey() {
		return clientSessionPublicKey;
	}
	public void setClientSessionPublicKey(String clientSessionPublicKey) {
		this.clientSessionPublicKey = clientSessionPublicKey;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

package com.interswitchug.phoenix.simulator.dto;


public class KeyExchangeResponse   {
	private String authToken;
	private String serverSessionPublicKey;
	private String expireTime;
	private boolean requiresOtp;
	private String terminalKey;
	
	
	
	
	public String getTerminalKey() {
		return terminalKey;
	}
	public void setTerminalKey(String terminalKey) {
		this.terminalKey = terminalKey;
	}
	public boolean isRequiresOtp() {
		return requiresOtp;
	}
	public void setRequiresOtp(boolean requiresOtp) {
		this.requiresOtp = requiresOtp;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getServerSessionPublicKey() {
		return serverSessionPublicKey;
	}
	public void setServerSessionPublicKey(String serverSessionPublicKey) {
		this.serverSessionPublicKey = serverSessionPublicKey;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	
	
}

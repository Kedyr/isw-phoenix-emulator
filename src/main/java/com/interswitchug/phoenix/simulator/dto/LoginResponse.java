package com.interswitchug.phoenix.simulator.dto;

import java.io.Serializable;

public class LoginResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6595929533116515317L;
	private String firstname;
	private String lastname;
	private String username;
	private String name;
	private String contact;
	private String authToken;
	private String merchantId;
	private String userId;
	private String terminalId;
	private boolean active;
	private String location;
	private String operatorName;
	private String clientSecret;
	private String serverSessionPublicKey;
	private boolean requiresOtp;
	private String tpk;
	private String tpkIV;
	private String currencySymbol;
	private String currencyCode;
	
	
	
	
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getTpkIV() {
		return tpkIV;
	}
	public void setTpkIV(String tpkIV) {
		this.tpkIV = tpkIV;
	}
	public String getTpk() {
		return tpk;
	}
	public void setTpk(String tpk) {
		this.tpk = tpk;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String token) {
		this.authToken = token;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	
	public boolean isRequiresOtp() {
		return requiresOtp;
	}
	public void setRequiresOtp(boolean requiresOtp) {
		this.requiresOtp = requiresOtp;
	}
	public String getServerSessionPublicKey() {
		return serverSessionPublicKey;
	}
	public void setServerSessionPublicKey(String serverSessionPublicKey) {
		this.serverSessionPublicKey = serverSessionPublicKey;
	}
	
	
	
	
}
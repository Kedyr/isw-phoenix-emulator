package com.interswitchug.phoenix.simulator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ClientRegistrationDetail extends ClientTerminalRequest{

	

	private String name;
	private String phoneNumber;
	private String nin;
	private String gender;
	private String emailAddress;
	private String ownerPhoneNumber;
	private String publicKey;
	private String clientSessionPublicKey;
	
	
	public String getClientSessionPublicKey() {
		return clientSessionPublicKey;
	}
	public void setClientSessionPublicKey(String clientSessionPublicKey) {
		this.clientSessionPublicKey = clientSessionPublicKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getNin() {
		return nin;
	}
	public void setNin(String nin) {
		this.nin = nin;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}
	public void setOwnerPhoneNumber(String ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	
	
   
}


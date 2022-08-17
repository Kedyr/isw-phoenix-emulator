package com.interswitchug.phoenix.simulator.dto;


public class PaymentRequest extends PaymentsTerminalRequest {
	private double amount;
	private String customerId;
	private String phoneNumber;
	private long paymentCode;
	private String customerName;
	private String sourceOfFunds;
	private String narration;
	private String depositorName;
	private String location;
	private String alternateCustomerId;
	private String transactionCode;
	private String customerToken;
	private String additionalData;
	private String collectionsAccountNumber;
	private String pin;
	private String otp;
	private String currencyCode;
	private CardData cardData;
	
	
		
	public CardData getCardData() {
		return cardData;
	}
	public void setCardData(CardData cardData) {
		this.cardData = cardData;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public long getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(long paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSourceOfFunds() {
		return sourceOfFunds;
	}
	public void setSourceOfFunds(String sourceOfFunds) {
		this.sourceOfFunds = sourceOfFunds;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getDepositorName() {
		return depositorName;
	}
	public void setDepositorName(String depositorName) {
		this.depositorName = depositorName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getAlternateCustomerId() {
		return alternateCustomerId;
	}
	public void setAlternateCustomerId(String alternateCustomerId) {
		this.alternateCustomerId = alternateCustomerId;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getCustomerToken() {
		return customerToken;
	}
	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}
	public String getAdditionalData() {
		return additionalData;
	}
	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}
	public String getCollectionsAccountNumber() {
		return collectionsAccountNumber;
	}
	public void setCollectionsAccountNumber(String collectionsAccountNumber) {
		this.collectionsAccountNumber = collectionsAccountNumber;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	

	

}

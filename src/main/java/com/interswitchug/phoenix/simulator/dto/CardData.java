package com.interswitchug.phoenix.simulator.dto;

import java.io.Serializable;

public class CardData  implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9204211501143065811L;
	private String pan;
	private String expiryDate;
	private String track2;
	private String issuer;
	private String cardAcceptor;
	private String posDataCode;
	private String posEntryMode;
	private String accountType;
	private String sequenceNumber;
	private String posConditionCode;
	private String serviceCode;
	private String pinBlock;
	private String posGeoCode;
	private String countryCode;
	private String amountAuthorized;
	private String amountOther;
	private String applicationInterchangeProfile;
	private String cryptogram;
	private String cryptogramInformationData;
	private String cvmResults;
	private String terminalCapabilities;
	private String terminalCountryCode;
	private String terminalType;
	private String terminalVerificationResult;
	private String transactionCurrencyCode;
	private String transactionDate;
	private String transactionType;
	private String unpredictableNumber;
	private String applicationTransactionCounter;
	private String issuerApplicationData;
	
	
	
	public String getApplicationTransactionCounter() {
		return applicationTransactionCounter;
	}
	public void setApplicationTransactionCounter(String applicationTransactionCounter) {
		this.applicationTransactionCounter = applicationTransactionCounter;
	}
	public String getIssuerApplicationData() {
		return issuerApplicationData;
	}
	public void setIssuerApplicationData(String issuerApplicationData) {
		this.issuerApplicationData = issuerApplicationData;
	}
	public String getAmountAuthorized() {
		return amountAuthorized;
	}
	public void setAmountAuthorized(String amountAuthorized) {
		this.amountAuthorized = amountAuthorized;
	}
	public String getAmountOther() {
		return amountOther;
	}
	public void setAmountOther(String amountOther) {
		this.amountOther = amountOther;
	}
	public String getApplicationInterchangeProfile() {
		return applicationInterchangeProfile;
	}
	public void setApplicationInterchangeProfile(String applicationInterchangeProfile) {
		this.applicationInterchangeProfile = applicationInterchangeProfile;
	}
	public String getCryptogram() {
		return cryptogram;
	}
	public void setCryptogram(String cryptogram) {
		this.cryptogram = cryptogram;
	}
	public String getCryptogramInformationData() {
		return cryptogramInformationData;
	}
	public void setCryptogramInformationData(String cryptogramInformationData) {
		this.cryptogramInformationData = cryptogramInformationData;
	}
	public String getCvmResults() {
		return cvmResults;
	}
	public void setCvmResults(String cvmResults) {
		this.cvmResults = cvmResults;
	}
	public String getTerminalCapabilities() {
		return terminalCapabilities;
	}
	public void setTerminalCapabilities(String terminalCapabilities) {
		this.terminalCapabilities = terminalCapabilities;
	}
	public String getTerminalCountryCode() {
		return terminalCountryCode;
	}
	public void setTerminalCountryCode(String terminalCountryCode) {
		this.terminalCountryCode = terminalCountryCode;
	}
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	public String getTerminalVerificationResult() {
		return terminalVerificationResult;
	}
	public void setTerminalVerificationResult(String terminalVerificationResult) {
		this.terminalVerificationResult = terminalVerificationResult;
	}
	public String getTransactionCurrencyCode() {
		return transactionCurrencyCode;
	}
	public void setTransactionCurrencyCode(String transactionCurrencyCode) {
		this.transactionCurrencyCode = transactionCurrencyCode;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getUnpredictableNumber() {
		return unpredictableNumber;
	}
	public void setUnpredictableNumber(String unpredictableNumber) {
		this.unpredictableNumber = unpredictableNumber;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getPosGeoCode() {
		return posGeoCode;
	}
	public void setPosGeoCode(String posGeoCode) {
		this.posGeoCode = posGeoCode;
	}
	public String getPinBlock() {
		return pinBlock;
	}
	public void setPinBlock(String pinBlock) {
		this.pinBlock = pinBlock;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getPosConditionCode() {
		return posConditionCode;
	}
	public void setPosConditionCode(String posConditionCode) {
		this.posConditionCode = posConditionCode;
	}
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getPosDataCode() {
		return posDataCode;
	}
	public void setPosDataCode(String posDataCode) {
		this.posDataCode = posDataCode;
	}
	public String getPosEntryMode() {
		return posEntryMode;
	}
	public void setPosEntryMode(String posEntryMode) {
		this.posEntryMode = posEntryMode;
	}
	public String getCardAcceptor() {
		return cardAcceptor;
	}
	public void setCardAcceptor(String cardAcceptor) {
		this.cardAcceptor = cardAcceptor;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getTrack2() {
		return track2;
	}
	public void setTrack2(String track2) {
		this.track2 = track2;
	}

}

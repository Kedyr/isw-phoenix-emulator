package com.interswitchug.phoenix.simulator.dto;


public class PaymentsTerminalRequest {
	public String terminalId;
	private String requestReference;
	private String IIN;
	private String gprsCoordinate;
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getRequestReference() {
		return requestReference;
	}
	public void setRequestReference(String requestReference) {
		this.requestReference = requestReference;
	}
	public String getIIN() {
		return IIN;
	}
	public void setIIN(String iIN) {
		IIN = iIN;
	}
	public String getGprsCoordinate() {
		return gprsCoordinate;
	}
	public void setGprsCoordinate(String gprsCoordinate) {
		this.gprsCoordinate = gprsCoordinate;
	}
	
	
}

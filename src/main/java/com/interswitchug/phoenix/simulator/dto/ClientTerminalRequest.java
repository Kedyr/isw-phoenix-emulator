package com.interswitchug.phoenix.simulator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ClientTerminalRequest {
	private String terminalId;
	private String appVersion;
	private String serialId;
	private String requestReference;
	private String gprsCoordinate;
	
	
	
	public String getGprsCoordinate() {
		return gprsCoordinate;
	}
	public void setGprsCoordinate(String gprsCoordinate) {
		this.gprsCoordinate = gprsCoordinate;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getSerialId() {
		return serialId;
	}
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}
	public String getRequestReference() {
		return requestReference;
	}
	public void setRequestReference(String requestReference) {
		this.requestReference = requestReference;
	}
	
    
}

package com.interswitchug.phoenix.simulator.dto;

import java.util.ArrayList;
import java.util.List;

public enum PhoenixResponseCodes {
		NONEXISTENT_TRANSACTION("90025", "NON EXISTENT TRANSACTION", "25"),
		INTERNAL_ERROR("90096","AN ERROR OCCURED", "96"),
		AUTHORIZATION_ERROR("90063","AUTHORIZATION ERROR","63"),
		ROUTING_ERROR("90092","ROUTING_ERROR", "92"),
		EXPIRED_TOKEN("90054","EXPIRED TIN/PIN/TOKEN  OR OTP", "54"),
		APPROVED("90000","TRANSACTION APPROVED", "00"),
		DEPRECATED_APPROVED("9000","TRANSACTION APPROVED", "00"),
		TRANSACTION_SENT("90001","TRANSACTION APPROVED", "09"),
		DUPLICATE_REFERENCE("90026","DUPLICATE REQUEST REFERENCE", "26"),
		DUPLICATE_RECORD("90026","DUPLICATE RECORD", "26"),
		ISSUER_INNOPERATIVE("90091","REMOTE SYSTEM TEMPORARILY UNAVAILABLE",  "91"),
		EXCEEDS_CONFIGURED_LIMIT("90098","EXCEEDS CONFIGURED LIMIT","98"),
		REQUEST_IN_PROGRESS("90009","REQUEST IN PROGRESS", "09"),
		DECLINED("90020","TRANSACTION DECLINED BY BILLER", "20"),
		SUSUPECTED_DUPLICATE("90094","REJECTED AS SUSPECT DUPLICATE","94"),
		SUSUPECTED_FRAUD("90059","REJECTED AS SUSPECT DUPLICATE/FRAUD","59"),
		ERROR_RESPONSE_FROM_HOST("90006","ERROR RESPONSE FROM HOST","06"),
		INVALID_AMOUNT("90013","INVALID_AMOUNT","13"),// "13"),
		UN_RECOGNIZABLE_CUSTOMER_NUMBER("90052","UN RECOGNIZABLE CUSTOMER NUMBER", "12"),
		MISSING_PHONE_NUMBER("900A9","MISSING_PHONE_NUMBER", "12"),
		UN_ACCEPTABLE_TRANSACTION_FEE("90023","UN ACCEPTABLE TRANSACTION FEE", "23"),
		INSUFFICIENT_FUNDS("90051","INSUFFICIENT FUNDS", "51"),
		WRONG_PIN_OR_OTP("90055","WRONG PIN OR OTP", "55"),
		FORMAT_ERROR("90030","FORMAT ERROR","30"),
		
		INVALID_PAYMENT_CODE("70017", "INVALID PAYMENT ITEM", "96"),
		INVALID_REQUEST_REFERENCE("70018","INVALID OR DUPLICATE REQUEST REFERENCE",  "12"),
		SECURITY_CONFIGURATION_REQUIRED("700A5","SECURITY CONFIGURATION REQUIRED ","A5"),
		PASSWORD_CHANGE_REQUIRED("700A6","PASSWORD CHANGE REQUIRED ","A6"),
		DATA_NOT_FOUND("70038","DATA NOT FOUND","38"),
		ACCOUNT_NOT_FOUND("90052","ACCOUNT NOT FOUND","52"),
		SAVINGS_ACCOUNT_NOT_FOUND("70053","ACCOUNT NOT FOUND","53"),
		INCORRECT_FEE_SETUP("70037","INCORRECT FEE SETUP","37"),
	    TERMINAL_OWNER_NOT_SET_UP("70030","TERMINAL OWNER NOT SETUP OR CONFIGURED FOR THIS ACTION","30"),
	    UNRECOGNISED_ISSUER("70031","UNRECOGNIZED ISSUER ","31"),
	    BILLER_NOT_FOUND("70010","BILLER NOT FOUND","10"),
	    BILLER_NOT_ENABLED_FOR_CHANNEL("70026","BILLER  NOT ENABLED FOR CHANNEL","26"),
	    ISSUER_NOT_ENABLED_FOR_BILLER("70027","ISSUER NOT ENABLED FOR BILLER","27"),
	    TERMINAL_OWNER_NOT_ENABLED_FOR_BILLER("70028","TERMINAL OWNER NOT ENABLED FOR BILLER","28"),
	    TERMINAL_OWNER_NOT_ENABLED_FOR_CHANNEL("70029","TERMINAL OWNER NOT ENABLED FOR CHANNEL","29"),
	    TRANSACTION_NOT_PERMITTED("70058","TRANSACTION NOT PERMITTED","58"),
		NO_CARD_RECORD("70056","NO CARD RECORD ","56"),
		EXPIRED_CARD("70054","EXPIRED CARD ","54"),
		TRANSACTION_NOT_PERMITED_TO_CARD("70057","TRANSACTION NOT PERMITTED TO CARDHOLDER","57"),
		PIN_TRIES_EXCEEDED("70038","PIN TRIES EXCEEDED ","38"),
		EXCEEDS_WITHDRAWAL_LIMIT("70061","EXCEEDS WITHDRAWAL LIMIT","61"),
		RESPONSE_RECEIVED_TOO_LATE("90009","RESPONSE RECEIVED TOO LATE", "68");

		
		public final String CODE;
		public final String MESSAGE;
		public final String SWITCH_ERROR_CODE;  
		
		PhoenixResponseCodes(String code, String message,String switcherrorCode){
			this.CODE = code;
			this.MESSAGE = message;
			this.SWITCH_ERROR_CODE = switcherrorCode;
		}
		
		/**
		 * these errors happen within isw systems
		 * @return
		 */
		public static List<String> getInternalResponseCodes(){
			List<String> internalCodes = new ArrayList<>();
			internalCodes.add(PhoenixResponseCodes.AUTHORIZATION_ERROR.CODE);
			internalCodes.add(PhoenixResponseCodes.INTERNAL_ERROR.CODE);
			internalCodes.add(PhoenixResponseCodes.ERROR_RESPONSE_FROM_HOST.CODE);
			internalCodes.add(PhoenixResponseCodes.ISSUER_INNOPERATIVE.CODE);
			internalCodes.add(PhoenixResponseCodes.ROUTING_ERROR.CODE);
			return internalCodes;
		}
		
		public static String retrieveMessage(String code){
		    for(int c=0;c< PhoenixResponseCodes.values().length;c++){
		        if(code.equalsIgnoreCase(PhoenixResponseCodes.values()[c].CODE))
		        	return PhoenixResponseCodes.values()[c].MESSAGE;
		    }
		    return "";
		}
		
		public static String retrieveSwitchCode(String code){
		    for(int c=0;c< PhoenixResponseCodes.values().length;c++){
		        if(code.equalsIgnoreCase(PhoenixResponseCodes.values()[c].CODE))
		        	return PhoenixResponseCodes.values()[c].SWITCH_ERROR_CODE;
		    }
		    return "";
		}
		
		public static String retrieveBasedOnSwitchCode(String code){
		    for(int c=0;c< PhoenixResponseCodes.values().length;c++){
		        if(code.equalsIgnoreCase(PhoenixResponseCodes.values()[c].SWITCH_ERROR_CODE))
		        	return PhoenixResponseCodes.values()[c].CODE;
		    }
		    return PhoenixResponseCodes.DECLINED.CODE;
		}
	}

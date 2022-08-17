package com.interswitchug.phoenix.simulator;

import java.security.KeyPair;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interswitchug.phoenix.simulator.dto.JSONDataTransform;
import com.interswitchug.phoenix.simulator.dto.LoginOtpValidationRequest;
import com.interswitchug.phoenix.simulator.dto.LoginRequest;
import com.interswitchug.phoenix.simulator.dto.LoginResponse;
import com.interswitchug.phoenix.simulator.dto.PhoenixResponseCodes;
import com.interswitchug.phoenix.simulator.dto.SystemResponse;
import com.interswitchug.phoenix.simulator.utils.AuthUtils;
import com.interswitchug.phoenix.simulator.utils.Constants;
import com.interswitchug.phoenix.simulator.utils.CryptoUtils;
import com.interswitchug.phoenix.simulator.utils.EllipticCurveUtils;
import com.interswitchug.phoenix.simulator.utils.HttpUtil;
import com.interswitchug.phoenix.simulator.utils.UtilMethods;

public class Login {
	
	 private static Logger LOG = LoggerFactory.getLogger(Login.class);
	
	public static String baseUrl = Constants.ROOT_LINK + "client/";
	public static String endpointUrl = baseUrl + "login";
	public static final String loginEndpointUrl = baseUrl + "validateLoginOtp";
	
	public static void main(String[] args) throws Exception {
		EllipticCurveUtils curveUtils = new EllipticCurveUtils("ECDH");
		KeyPair pair = curveUtils.generateKeypair();
		String privateKey = curveUtils.getPrivateKey(pair);
		String publicKey = curveUtils.getPublicKey(pair);
		 String terminalid = Constants.MY_TERMINAL_ID;
		SystemResponse<LoginResponse> response = Login.login(publicKey,terminalid);
		LOG.info("LoginResponse : {}", response.getResponseMessage());
		 if(response.getResponseCode().equals(PhoenixResponseCodes.APPROVED.CODE) && response.getResponse().isRequiresOtp()) {
			 LOG.info("Enter received OTP: "); 
			 Scanner in  = new Scanner(System.in);
			 String otp = in.next();
			 in.close();
				String clearServerSessionKey = CryptoUtils.decryptWithPrivate(response.getResponse().getServerSessionPublicKey());
			 String terminalkey = curveUtils.doECDH(privateKey, clearServerSessionKey);
			 String otpResponse = loginOtp(otp,terminalkey);
			 LOG.info("otpResponse {}", otpResponse);
		 }
	}
	
	public static SystemResponse<LoginResponse> login(String sessionPublicKey,String terminalid) throws Exception {
		LoginRequest request = new LoginRequest();
		request.setTerminalId(terminalid);
		request.setSerialId(Constants.MY_SERIAL_ID);
		request.setRequestReference(java.util.UUID.randomUUID().toString());
		request.setAppVersion(Constants.APP_VERSION);                 
		String passwordHash = UtilMethods.hash512(Constants.ACCOUNT_PWD) + request.getRequestReference() + Constants.MY_SERIAL_ID ;
		request.setPassword(CryptoUtils.signWithPrivateKey(passwordHash));
		request.setClientSessionPublicKey(sessionPublicKey);
		Map<String,String> headers = AuthUtils.generateInterswitchAuth(Constants.POST_REQUEST, endpointUrl, "","","");
		String json= JSONDataTransform.marshall(request);

		String response =  HttpUtil.postHTTPRequest( endpointUrl, headers, json);
		return UtilMethods.unMarshallSystemResponseObject(response, LoginResponse.class);
	}
	
	public static String loginOtp(String otp,String terminalkey) throws Exception {
		LoginOtpValidationRequest request = new LoginOtpValidationRequest();
		request.setTerminalId(Constants.MY_TERMINAL_ID);
		request.setSerialId(Constants.MY_SERIAL_ID);
		request.setRequestReference(java.util.UUID.randomUUID().toString());
		request.setAppVersion(Constants.APP_VERSION);
		request.setOtp(CryptoUtils.encrypt(otp, terminalkey));
		
		Map<String,String> headers = AuthUtils.generateInterswitchAuth(Constants.POST_REQUEST, loginEndpointUrl, "","","",Constants.PRIKEY);
		String json= JSONDataTransform.marshall(request);

		return  HttpUtil.postHTTPRequest( loginEndpointUrl, headers, json);
	}
}

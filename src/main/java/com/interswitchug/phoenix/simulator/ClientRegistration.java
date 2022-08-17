package com.interswitchug.phoenix.simulator;

import java.security.KeyPair;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interswitchug.phoenix.simulator.dto.ClientRegistrationDetail;
import com.interswitchug.phoenix.simulator.dto.ClientRegistrationResponse;
import com.interswitchug.phoenix.simulator.dto.CompleteClientRegistration;
import com.interswitchug.phoenix.simulator.dto.JSONDataTransform;
import com.interswitchug.phoenix.simulator.dto.LoginResponse;
import com.interswitchug.phoenix.simulator.dto.PhoenixResponseCodes;
import com.interswitchug.phoenix.simulator.dto.SystemResponse;
import com.interswitchug.phoenix.simulator.utils.AuthUtils;
import com.interswitchug.phoenix.simulator.utils.Constants;
import com.interswitchug.phoenix.simulator.utils.CryptoUtils;
import com.interswitchug.phoenix.simulator.utils.EllipticCurveUtils;
import com.interswitchug.phoenix.simulator.utils.HttpUtil;
import com.interswitchug.phoenix.simulator.utils.UtilMethods;

public class ClientRegistration {
	
	 private static Logger LOG = LoggerFactory.getLogger(ClientRegistration.class);
	 
	 public static String baseUrl =  Constants.ROOT_LINK + "client/";
	 public static final String registrationEndpointUrl = baseUrl + "clientRegistration";
	 public static final String registrationCompletionEndpointUrl = baseUrl  + "completeClientRegistration";

	  
	 public static void main(String[] args) throws Exception {
		 KeyPair pair = CryptoUtils.generateKeyPair();
		String privateKey = Base64.encodeBase64String(pair.getPrivate().getEncoded());
		String publicKey =  Base64.encodeBase64String(pair.getPublic().getEncoded());
		
		LOG.info(" private key {} ", privateKey);
		LOG.info(" public key  {} " , publicKey);
		
		EllipticCurveUtils curveUtils = new EllipticCurveUtils("ECDH");
		KeyPair keyPair = curveUtils.generateKeypair();
		String curvePrivateKey = curveUtils.getPrivateKey(keyPair);
		String curvePublicKey = curveUtils.getPublicKey(keyPair);
		
		 String resonse = clientRegistrationRequest(publicKey,curvePublicKey,privateKey);
		
		 SystemResponse<ClientRegistrationResponse> registrationResponse = UtilMethods.unMarshallSystemResponseObject(resonse,  ClientRegistrationResponse.class);
		 if(!registrationResponse.getResponseCode().equals(PhoenixResponseCodes.APPROVED.CODE)) {
			 LOG.info("Client Registration failed: {} ", registrationResponse.getResponseMessage());
		 }else {
			   String decryptedSessionKey = CryptoUtils.decryptWithPrivate(registrationResponse.getResponse().getServerSessionPublicKey(),privateKey);
			   String terminalKey = curveUtils.doECDH(curvePrivateKey,decryptedSessionKey);
			   LOG.info("==============terminalKey==============");
			   LOG.info("terminalKey: {} ",  terminalKey); 
			   String authToken =  CryptoUtils.decryptWithPrivate(registrationResponse.getResponse().getAuthToken(),privateKey);
			  
			   LOG.info(" authToken {} " , authToken);
			   String transactionReference = registrationResponse.getResponse().getTransactionReference();
			   LOG.info("Enter received OTP: "); 
			   Scanner in  = new Scanner(System.in);
			   String otp = in.next();
			   in.close();
			   String finalResponse =  completeRegistration(terminalKey,authToken,transactionReference, otp,privateKey);
			 
			   SystemResponse<LoginResponse> response = UtilMethods.unMarshallSystemResponseObject(finalResponse,  LoginResponse.class);
			   if(response.getResponseCode().equals(PhoenixResponseCodes.APPROVED.CODE)) {
				   if(response.getResponse().getClientSecret() != null  && response.getResponse().getClientSecret().length() > 5) {
					   String clientSecret = CryptoUtils.decryptWithPrivate(response.getResponse().getClientSecret() ,privateKey);
					   LOG.info("clientSecret: {}", clientSecret); 
				   }
			   }else {
				   LOG.info("finalResponse: {}", response.getResponseMessage()); 
			   }
		 }
	 }
	 
	 private static String clientRegistrationRequest(String publicKey,String clientSessionPublicKey,String privateKey) throws Exception{		 
		  ClientRegistrationDetail setup= new ClientRegistrationDetail();
		  setup.setSerialId(Constants.MY_SERIAL_ID);
		  setup.setName("API Client");
		  setup.setNin("123456");
		  setup.setOwnerPhoneNumber("00000");
		  setup.setPhoneNumber("00000000");
		  setup.setPublicKey(publicKey);
		  setup.setRequestReference(java.util.UUID.randomUUID().toString());
		  setup.setTerminalId(Constants.TERMINAL_ID);
		  setup.setGprsCoordinate("");
		  setup.setClientSessionPublicKey(clientSessionPublicKey);
		  
		  Map<String,String> headers = AuthUtils.generateInterswitchAuth(Constants.POST_REQUEST, registrationEndpointUrl,"","","",privateKey);
		  String json= JSONDataTransform.marshall(setup);

		 return HttpUtil.postHTTPRequest( registrationEndpointUrl, headers, json);
	  }
	 
	 private static String completeRegistration(String terminalKey,String authToken,String transactionReference,String otp,String privateKey) throws Exception{
			
		 CompleteClientRegistration completeReg= new CompleteClientRegistration();


		  String passwordHash = UtilMethods.hash512(Constants.ACCOUNT_PWD);
		  
		  completeReg.setTerminalId(Constants.TERMINAL_ID);
		  completeReg.setSerialId(Constants.MY_SERIAL_ID);
		  completeReg.setOtp(CryptoUtils.encrypt(otp,terminalKey));
		  completeReg.setRequestReference(java.util.UUID.randomUUID().toString());
		  completeReg.setPassword(CryptoUtils.encrypt(passwordHash,terminalKey));
		  completeReg.setTransactionReference(transactionReference);
		  completeReg.setAppVersion(Constants.APP_VERSION);
		  completeReg.setGprsCoordinate("");
		  
		 
		  Map<String,String> headers = AuthUtils.generateInterswitchAuth(Constants.POST_REQUEST, registrationCompletionEndpointUrl,
				  "",authToken,terminalKey,privateKey);
		  String json= JSONDataTransform.marshall(completeReg);
		  return HttpUtil.postHTTPRequest( registrationCompletionEndpointUrl, headers, json);
	 }
}

package com.interswitchug.phoenix.simulator.utils;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AuthUtils {
	 private static Logger LOG = LoggerFactory.getLogger(AuthUtils.class);

	
	public static Map<String, String> generateInterswitchAuth(String httpMethod, String resourceUrl,
			String additionalParameters,String authToken,String terminalKey) throws Exception {
		return generateInterswitchAuth( httpMethod,  resourceUrl,
				 additionalParameters, authToken, terminalKey,""); 
	}
	

		
	public static Map<String, String> generateInterswitchAuth(String httpMethod, String resourceUrl,
			String additionalParameters,String authToken,String terminalKey,String privateKey) throws Exception {
		HashMap<String, String> interswitchAuth = new HashMap<>();

		TimeZone ugTimeZone = TimeZone.getTimeZone("Africa/Kampala");

		Calendar calendar = Calendar.getInstance(ugTimeZone);
		long timestamp = calendar.getTimeInMillis() / 1000;

		UUID uuid = UUID.randomUUID();
		String nonce = uuid.toString().replace("-", "");

		String clientIdBase64 =  Base64.getEncoder().encodeToString(Constants.CLIENT_ID.getBytes(StandardCharsets.UTF_8));
		String authorization = Constants.AUTHORIZATION_REALM + " " + clientIdBase64;

		String encodedResourceUrl = URLEncoder.encode(resourceUrl, Constants.ISO_8859_1);
		String signatureCipher = httpMethod + "&" + encodedResourceUrl + "&" + timestamp + "&" + nonce + "&"
				+ Constants.CLIENT_ID + "&" + Constants.CLIENT_SECRET;

		if (additionalParameters != null && !"".equals(additionalParameters))
			signatureCipher = signatureCipher + "&" + additionalParameters;
		
		LOG.info("signature cipher {} ",signatureCipher);

		interswitchAuth.put(Constants.AUTHORIZATION, authorization.trim());
		interswitchAuth.put(Constants.TIMESTAMP, String.valueOf(timestamp));
		interswitchAuth.put(Constants.NONCE, nonce);
		if(privateKey.isEmpty()) 
			interswitchAuth.put(Constants.SIGNATURE,CryptoUtils.signWithPrivateKey(signatureCipher));
		else {
			interswitchAuth.put(Constants.SIGNATURE,CryptoUtils.signWithPrivateKey(signatureCipher,privateKey));
		}
				
		authToken = !terminalKey.isBlank()?CryptoUtils.encrypt(authToken,terminalKey):"";
		interswitchAuth.put(Constants.AUTH_TOKEN,authToken);
		return interswitchAuth;
	}
	


	 

}

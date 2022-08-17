package com.interswitchug.phoenix.simulator;

import java.util.Map;

import com.interswitchug.phoenix.simulator.dto.JSONDataTransform;
import com.interswitchug.phoenix.simulator.dto.KeyExchangeResponse;
import com.interswitchug.phoenix.simulator.dto.PaymentRequest;
import com.interswitchug.phoenix.simulator.dto.PhoenixResponseCodes;
import com.interswitchug.phoenix.simulator.dto.SystemResponse;
import com.interswitchug.phoenix.simulator.utils.AuthUtils;
import com.interswitchug.phoenix.simulator.utils.Constants;
import com.interswitchug.phoenix.simulator.utils.HttpUtil;

public class PaymentNotification {

	public static String endpointUrl = Constants.ROOT_LINK + "sente/xpayment";
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		PaymentRequest request = new PaymentRequest();
		request.setPaymentCode(53046936951L);
		request.setCustomerId("");
		request.setRequestReference(java.util.UUID.randomUUID().toString());
		request.setTerminalId(Constants.MY_TERMINAL_ID);
		request.setAmount(600);
		request.setCurrencyCode("4444");
			
		
		String additionalData = request.getAmount()+"&"
		+request.getTerminalId()+"&"
				+request.getRequestReference()+"&"
		+ request.getCustomerId()+"&"
				+request.getPaymentCode();


		SystemResponse<KeyExchangeResponse> exchangeKeys = KeyExchange.doKeyExchange();
		
		if(exchangeKeys.getResponseCode().equals(PhoenixResponseCodes.APPROVED.CODE)) {
			String authToken = exchangeKeys.getResponse().getAuthToken();
			String sessionKey = exchangeKeys.getResponse().getTerminalKey();
			
			Map<String,String> headers = AuthUtils.generateInterswitchAuth(Constants.POST_REQUEST, endpointUrl, additionalData,
					authToken,sessionKey);

			HttpUtil.postHTTPRequest(endpointUrl, headers, JSONDataTransform.marshall(request));
		}
		
	}
}

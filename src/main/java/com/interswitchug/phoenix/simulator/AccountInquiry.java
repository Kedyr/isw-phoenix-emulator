package com.interswitchug.phoenix.simulator;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.interswitchug.phoenix.simulator.dto.KeyExchangeResponse;
import com.interswitchug.phoenix.simulator.dto.PhoenixResponseCodes;
import com.interswitchug.phoenix.simulator.dto.SystemResponse;
import com.interswitchug.phoenix.simulator.utils.AuthUtils;
import com.interswitchug.phoenix.simulator.utils.Constants;
import com.interswitchug.phoenix.simulator.utils.HttpUtil;


public class AccountInquiry {

	 public static String endpointUrl =  Constants.ROOT_LINK + "sente/customerValidation";
	public static void main(String[] args) throws Exception {
		

		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		
		rootNode.put("paymentCode","53046936951");
		rootNode.put("customerId",Constants.MY_TERMINAL_ID);
		rootNode.put("requestReference", java.util.UUID.randomUUID().toString());
		rootNode.put("terminalId", Constants.MY_TERMINAL_ID);
		rootNode.put("amount", "600");
		rootNode.put("currencyCode", "800");

		SystemResponse<KeyExchangeResponse> exchangeKeys = KeyExchange.doKeyExchange();
		if(exchangeKeys.getResponseCode().equals(PhoenixResponseCodes.APPROVED.CODE)) {
			Map<String,String> headers = AuthUtils.generateInterswitchAuth(Constants.POST_REQUEST, endpointUrl, "",
						exchangeKeys.getResponse().getAuthToken(),exchangeKeys.getResponse().getTerminalKey());
			String jsonString = mapper.writeValueAsString(rootNode);
			HttpUtil.postHTTPRequest(endpointUrl, headers, jsonString);
		}
	}
	
}

package com.interswitchug.phoenix.simulator;

import java.util.Map;

import com.interswitchug.phoenix.simulator.dto.KeyExchangeResponse;
import com.interswitchug.phoenix.simulator.dto.PhoenixResponseCodes;
import com.interswitchug.phoenix.simulator.dto.SystemResponse;
import com.interswitchug.phoenix.simulator.utils.AuthUtils;
import com.interswitchug.phoenix.simulator.utils.Constants;
import com.interswitchug.phoenix.simulator.utils.HttpUtil;

public class BalanceInquiry {
	public static String endpointUrl =  Constants.ROOT_LINK +  "sente/accountBalance";
		public static void main(String[] args) throws Exception {
		String request = endpointUrl +"?terminalId="+ Constants.MY_TERMINAL_ID + "&requestReference=123436";

		SystemResponse<KeyExchangeResponse> exchangeKeys = KeyExchange.doKeyExchange();
		if(exchangeKeys.getResponseCode().equals(PhoenixResponseCodes.APPROVED.CODE)) {
		   Map<String,String> headers = AuthUtils.generateInterswitchAuth(Constants.GET_REQUEST, request, "",exchangeKeys.getResponse().getAuthToken(),exchangeKeys.getResponse().getTerminalKey());
		   HttpUtil.getHTTPRequest(request, headers);
		}
	}
}

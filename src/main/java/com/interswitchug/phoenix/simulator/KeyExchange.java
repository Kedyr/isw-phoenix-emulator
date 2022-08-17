package com.interswitchug.phoenix.simulator;

import java.security.KeyPair;
import java.util.Map;

import com.interswitchug.phoenix.simulator.dto.JSONDataTransform;
import com.interswitchug.phoenix.simulator.dto.KeyExchangeRequest;
import com.interswitchug.phoenix.simulator.dto.KeyExchangeResponse;
import com.interswitchug.phoenix.simulator.dto.PhoenixResponseCodes;
import com.interswitchug.phoenix.simulator.dto.SystemResponse;
import com.interswitchug.phoenix.simulator.utils.AuthUtils;
import com.interswitchug.phoenix.simulator.utils.Constants;
import com.interswitchug.phoenix.simulator.utils.CryptoUtils;
import com.interswitchug.phoenix.simulator.utils.EllipticCurveUtils;
import com.interswitchug.phoenix.simulator.utils.HttpUtil;
import com.interswitchug.phoenix.simulator.utils.SystemApiException;
import com.interswitchug.phoenix.simulator.utils.UtilMethods;

public class KeyExchange {
	public static String endpointUrl = Constants.ROOT_LINK + "client/doKeyExchange";

	public static void main(String[] args) throws Exception {
		doKeyExchange();
	}

	/**
	 * this method hsould be run at intervals and the generated authtoken saved for reuse till the set expiration period
	 * @return
	 * @throws Exception
	 */
	public static SystemResponse<KeyExchangeResponse> doKeyExchange() throws Exception {
		EllipticCurveUtils curveUtils = new EllipticCurveUtils("ECDH");
		KeyPair pair = curveUtils.generateKeypair();
		String privateKey = curveUtils.getPrivateKey(pair);
		String publicKey = curveUtils.getPublicKey(pair);

		KeyExchangeRequest request = new KeyExchangeRequest();
		request.setTerminalId(Constants.MY_TERMINAL_ID);
		request.setSerialId(Constants.MY_SERIAL_ID);
		request.setRequestReference(java.util.UUID.randomUUID().toString());
		request.setAppVersion(Constants.APP_VERSION);
		String passwordHash = UtilMethods.hash512(Constants.ACCOUNT_PWD) + request.getRequestReference()
				+ Constants.MY_SERIAL_ID;
		request.setPassword(CryptoUtils.signWithPrivateKey(passwordHash));
		request.setClientSessionPublicKey(publicKey);

		Map<String, String> headers = AuthUtils.generateInterswitchAuth(Constants.POST_REQUEST, endpointUrl, "", "",
				"");
		String json = JSONDataTransform.marshall(request);

		String response = HttpUtil.postHTTPRequest(endpointUrl, headers, json);
		SystemResponse<KeyExchangeResponse> keyxchangeResponse = UtilMethods.unMarshallSystemResponseObject(response,
				KeyExchangeResponse.class);
		if (keyxchangeResponse.getResponseCode().equals(PhoenixResponseCodes.APPROVED.CODE)) {
			String clearServerSessionKey = CryptoUtils
					.decryptWithPrivate(keyxchangeResponse.getResponse().getServerSessionPublicKey());
			String terminalkey = new EllipticCurveUtils("ECDH").doECDH(privateKey,clearServerSessionKey);
			keyxchangeResponse.getResponse().setTerminalKey(terminalkey);
			if (!keyxchangeResponse.getResponse().getAuthToken().isBlank())
				keyxchangeResponse.getResponse()
						.setAuthToken(CryptoUtils.decryptWithPrivate(keyxchangeResponse.getResponse().getAuthToken()));

			return keyxchangeResponse;
		} else
			throw new SystemApiException(keyxchangeResponse.getResponseCode(), keyxchangeResponse.getResponseMessage());
	}

}

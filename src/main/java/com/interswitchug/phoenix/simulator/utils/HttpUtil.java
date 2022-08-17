package com.interswitchug.phoenix.simulator.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this is purely used for testing purposes and not production
 * @author Denis Kagimu on 17 Aug 2022
 *
 */
public class HttpUtil {
	 private static Logger LOG = LoggerFactory.getLogger(HttpUtil.class);
	public static String postHTTPRequest( String resourceUrl, Map<String, String> headers,
			String data) throws Exception {
		 
		LOG.info("http outgoing request body {} ",data);
		LOG.info("http outgoing request url {} ",resourceUrl);
		
		StringBuilder resposeString = new StringBuilder();
		CloseableHttpClient client = HttpClients.custom()
				.setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		HttpPost httpPost = new HttpPost(resourceUrl);

		StringEntity entity = new StringEntity(data);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			httpPost.setHeader(key, value);
		}

		CloseableHttpResponse response = client.execute(httpPost);
		HttpEntity httpEntity = response.getEntity();
		int responseCode = response.getStatusLine().getStatusCode();
		if (httpEntity != null) {
			InputStream inputStream = httpEntity.getContent();
			int c;
			while ((c = inputStream.read()) != -1) {
				resposeString.append((char) c);
			}
		}
		client.close();
		LOG.info("http response code {} ",responseCode);
		LOG.info("http response body {} ",resposeString);
		return resposeString.toString();
	}
	
	public static String getHTTPRequest( String resourceUrl, Map<String, String> headers) throws Exception {


		LOG.info("http outgoing request url {} ",resourceUrl);
		
		 CloseableHttpClient client = HttpClients
				    .custom()
				    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
				    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
				    .build();
		HttpGet httpGet = new HttpGet(resourceUrl);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-type", "application/json");
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			httpGet.setHeader(key, value);
		}
		CloseableHttpResponse response = client.execute(httpGet);
	    HttpEntity httpEntity = response.getEntity();
	    StringBuilder resposeString = new StringBuilder();
	    int responseCode = response.getStatusLine().getStatusCode();
	    
		if(httpEntity != null){
			InputStream inputStream = httpEntity.getContent();
			int c;
			while ((c = inputStream.read()) != -1) {
				resposeString.append((char) c);
			}
		}
	    client.close();
		LOG.info("http response code {} ",responseCode);
		LOG.info("http response body {} ",resposeString);
		return resposeString.toString();
	}

}

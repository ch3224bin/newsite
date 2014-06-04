package com.john_na.common.api;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

public class DaumApi {
	
	private static final String REQUEST_TOKEN_URL = DaumApiBundle.getValue("REQUEST_TOKEN_URL");
	private static final String ACCESS_TOKEN_URL = DaumApiBundle.getValue("ACCESS_TOKEN_URL");
	private static final String AUTHORIZE_URL = DaumApiBundle.getValue("AUTHORIZE_URL");
	
	// Service Provider 객체 생성
	private CommonsHttpOAuthProvider provider;

	// Consumer 객체 생성
	private CommonsHttpOAuthConsumer consumer;
	
	public DaumApi(String consumerKey, String consumerSecretKey) {
		provider = new CommonsHttpOAuthProvider(REQUEST_TOKEN_URL, ACCESS_TOKEN_URL, AUTHORIZE_URL);
		consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecretKey);
	}
	
	public CommonsHttpOAuthConsumer getConsumer() {
		return consumer;
	}
	
	public CommonsHttpOAuthProvider getProvider() {
		return provider;
	}
	
	public String requestToken(String callbackUrl) throws Exception {
		return provider.retrieveRequestToken(consumer, callbackUrl);
	}
	
	public void accessToken(String verifier) throws Exception {
		provider.retrieveAccessToken(consumer, verifier);
	}
	
	public void setTokenWithSecret(String accessToken, String tokenSecret) {
		consumer.setTokenWithSecret(accessToken, tokenSecret);
	}
}

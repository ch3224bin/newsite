package com.john_na.common.api;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;

public class DaumApi {
//	static final String REQUEST_TOKEN_URL = "https://apis.daum.net/oauth/requestToken";
//	static final String AUTHORIZE_URL = "https://apis.daum.net/oauth/authorize";
//	static final String ACCESS_TOKEN_URL = "https://apis.daum.net/oauth/accessToken";
	
	// Service Provider 객체 생성
	private  OAuthProvider provider;

	// Consumer 객체 생성
	private  OAuthConsumer consumer;
	
//	public DaumApi(String consumerKey, String consumerSecretKey) {
//		provider = new CommonsHttpOAuthProvider(REQUEST_TOKEN_URL, ACCESS_TOKEN_URL, AUTHORIZE_URL);
//		consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecretKey);
//	}
	
	DaumApi() {}
	
	public DaumApi(OAuthProvider provider, OAuthConsumer consumer) {
		this.provider = provider;
		this.consumer = consumer;
	}
	
	public OAuthConsumer getConsumer() {
		return consumer;
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

package com.john_na.common.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

@Component("daumApiProvider")
@SuppressWarnings("serial")
public class DaumApiProvider extends CommonsHttpOAuthProvider {
	
	private static final String REQUEST_TOKEN_URL = "https://apis.daum.net/oauth/requestToken";
	private static final String AUTHORIZE_URL = "https://apis.daum.net/oauth/authorize";
	private static final String ACCESS_TOKEN_URL = "https://apis.daum.net/oauth/accessToken";
	
	public DaumApiProvider() {
		super(REQUEST_TOKEN_URL, AUTHORIZE_URL, ACCESS_TOKEN_URL);
	}
}

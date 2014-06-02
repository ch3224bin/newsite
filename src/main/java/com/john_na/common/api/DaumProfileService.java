package com.john_na.common.api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.john_na.common.entity.DaumAPIVo;
import com.john_na.common.entity.DaumApiReturnVo;
import com.john_na.common.entity.DaumProfileVo;

@Service
public class DaumProfileService extends DaumApi {
	private final Gson gson = new Gson();
	
	private final String CALLBACK_URL = "http://localhost:8080/authCal.do";
	private final String API_URL = "https://apis.daum.net/calendar";
	
//	@Autowired
//	public DaumProfileService(@Value("#{api['PROFILE_CONSUMER_KEY']}") String consumerKey, @Value("#{api['PROFILE_CONSUMER_SECRET_KEY']}") String consumerSecretKey) { 
//		super(consumerKey, consumerSecretKey);
//	}
	
	DaumProfileService() {}
	
	@Autowired
	public DaumProfileService(OAuthProvider provider, OAuthConsumer consumer) { 
		super(provider, consumer);
	}
	
	public String requestToken() throws Exception {
		return super.requestToken(CALLBACK_URL);
	}
	
	public DaumProfileVo getProfile() throws Exception {
		setTokenWithSecret(getConsumer().getToken(), getConsumer().getTokenSecret());
		
		URL url = new URL(API_URL);		
		HttpURLConnection request = (HttpURLConnection) url.openConnection();

		// request를 서명합니다.
		getConsumer().sign(request);		

		request.connect();
		
        DaumApiReturnVo vo = gson.fromJson(new InputStreamReader(request.getInputStream()), DaumApiReturnVo.class);
		
		return vo.getUser();
	}

	public DaumAPIVo createProfileApiVo() {
        DaumAPIVo daumProfileApiVo = new DaumAPIVo();
        daumProfileApiVo.setAccessToken(getConsumer().getToken());
        daumProfileApiVo.setTokenSecret(getConsumer().getTokenSecret());
		return daumProfileApiVo;
	}
}

package com.john_na.common.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.john_na.common.entity.DaumAPIVo;
import com.john_na.common.entity.DaumCalendarCategoryVo;
import com.john_na.common.entity.DaumCalendarEventVo;

@Service("daumCalendarService")
public class DaumCalendarService extends DaumApi {

	private final Gson gson = new Gson();
	private final AuthHttp authHttp = new AuthHttp();
	
	private final String CALLBACK_URL = "http://localhost:8080/authCal.do";
	private final String API_URL = "https://apis.daum.net/calendar";
	
//	@Autowired
//	public DaumCalendarService(@Value("#{api['CALENDAR_CONSUMER_KEY']}") String consumerKey, @Value("#{api['CALENDAR_CONSUMER_SECRET_KEY']}") String consumerSecretKey) { 
//		super(consumerKey, consumerSecretKey);
//	}
	
	DaumCalendarService() {}
	
	@Inject
	public DaumCalendarService(OAuthProvider provider, OAuthConsumer consumer) { 
		super(provider, consumer);
	}
	
	public String requestToken() throws Exception {
		return super.requestToken(CALLBACK_URL);
	}
	
	public DaumAPIVo createCalendarApiVo() {
		DaumAPIVo daumCalendarApiVo = new DaumAPIVo();
		daumCalendarApiVo.setAccessToken(getConsumer().getToken());
		daumCalendarApiVo.setTokenSecret(getConsumer().getTokenSecret());
		return daumCalendarApiVo;
	}
	
	public Collection<DaumCalendarCategoryVo> getCategoryList() throws Exception {
		
		setTokenWithSecret(getConsumer().getToken(), getConsumer().getTokenSecret());
		
		HttpGet request = new HttpGet(API_URL + "/category/index.json");

		// request를 서명합니다.
		getConsumer().sign(request);		

		HttpClient httpClient = HttpClientBuilder.create().build();
		return gson.fromJson(httpClient.execute(request, new BasicResponseHandler()), new TypeToken<Collection<DaumCalendarCategoryVo>>(){}.getType());
	}
	
	public DaumCalendarEventVo createEvent(DaumCalendarEventVo eventVo) throws Exception {
		setTokenWithSecret(getConsumer().getToken(), getConsumer().getTokenSecret());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("title", StringUtils.stripToEmpty(eventVo.getTitle())));
		params.add(new BasicNameValuePair("description", StringUtils.stripToEmpty(eventVo.getDescription())));
		params.add(new BasicNameValuePair("location", StringUtils.stripToEmpty(eventVo.getLocation())));
		params.add(new BasicNameValuePair("category_id", StringUtils.stripToEmpty(eventVo.getCategoryId())));
		params.add(new BasicNameValuePair("start_at", StringUtils.stripToEmpty(eventVo.getStartAt())));
		params.add(new BasicNameValuePair("end_at", StringUtils.stripToEmpty(eventVo.getEndAt())));
		params.add(new BasicNameValuePair("allday", String.valueOf(eventVo.getAllday())));
		
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
		String result = authHttp.getHttpPost(API_URL + "/event/create.json", formEntity, getConsumer());

		return gson.fromJson(result, DaumCalendarEventVo.class);
	}
	
	public Collection<DaumCalendarEventVo> getEventList(DaumCalendarEventVo eventVo) throws Exception {
		setTokenWithSecret(getConsumer().getToken(), getConsumer().getTokenSecret());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("start_at", StringUtils.stripToEmpty(eventVo.getStartAt())));
		params.add(new BasicNameValuePair("end_at", StringUtils.stripToEmpty(eventVo.getEndAt())));
		
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
		String result = authHttp.getHttpPost(API_URL + "/event/index.json", formEntity, getConsumer());
		System.out.println(result);
		
		return gson.fromJson(result, new TypeToken<Collection<DaumCalendarEventVo>>(){}.getType());
	}

}

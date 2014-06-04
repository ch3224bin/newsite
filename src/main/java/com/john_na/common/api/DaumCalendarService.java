package com.john_na.common.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.anyframe.util.ThreadLocalUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.john_na.common.entity.DaumAPIVo;
import com.john_na.common.entity.DaumCalendarCategoryVo;
import com.john_na.common.entity.DaumCalendarEventVo;

@Service
public class DaumCalendarService {

	private final Logger log = Logger.getLogger(DaumCalendarService.class);
	
	private final Gson gson = new Gson();
	private final AuthHttp authHttp = new AuthHttp();
	
	@Value("#{api['CALENDAR_CALLBACK_URL']}")
	private String callbackUrl;
	
	@Value("#{api['CALENDAR_API_URL']}")
	private String apiUrl;
	
	public String requestToken() throws Exception {
		DaumApi calendarApi = (DaumApi) ThreadLocalUtil.get("calendarApi");
		return calendarApi.requestToken(callbackUrl);
	}
	
	public DaumAPIVo createCalendarApiVo() {
		DaumApi calendarApi = (DaumApi) ThreadLocalUtil.get("calendarApi");
		DaumAPIVo daumCalendarApiVo = new DaumAPIVo();
		daumCalendarApiVo.setAccessToken(calendarApi.getConsumer().getToken());
		daumCalendarApiVo.setTokenSecret(calendarApi.getConsumer().getTokenSecret());
		return daumCalendarApiVo;
	}
	
	public Collection<DaumCalendarCategoryVo> getCategoryList() throws Exception {
		DaumApi calendarApi = (DaumApi) ThreadLocalUtil.get("calendarApi");

		String resultMsg = "";
		Collection<DaumCalendarCategoryVo> resultVo = null;
		try {
			resultMsg = authHttp.getHttpGet(apiUrl + "/category/index.json", calendarApi.getConsumer());
			resultVo = gson.fromJson(resultMsg, new TypeToken<Collection<DaumCalendarCategoryVo>>(){}.getType());
		} catch (Exception e) {
			log.debug(resultMsg);
			throw e;
		}
		
		return resultVo;
	}
	
	public DaumCalendarEventVo createEvent(DaumCalendarEventVo eventVo) throws Exception {
		DaumApi calendarApi = (DaumApi) ThreadLocalUtil.get("calendarApi");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("title", StringUtils.stripToEmpty(eventVo.getTitle())));
		params.add(new BasicNameValuePair("description", StringUtils.stripToEmpty(eventVo.getDescription())));
		params.add(new BasicNameValuePair("location", StringUtils.stripToEmpty(eventVo.getLocation())));
		params.add(new BasicNameValuePair("category_id", StringUtils.stripToEmpty(eventVo.getCategoryId())));
		params.add(new BasicNameValuePair("start_at", StringUtils.stripToEmpty(eventVo.getStartAt())));
		params.add(new BasicNameValuePair("end_at", StringUtils.stripToEmpty(eventVo.getEndAt())));
		params.add(new BasicNameValuePair("allday", String.valueOf(eventVo.getAllday())));
		
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
		
		String resultMsg = "";
		DaumCalendarEventVo resultVo = null;
		try {
			resultMsg = authHttp.getHttpPost(apiUrl + "/event/create.json", formEntity, calendarApi.getConsumer());
			resultVo = gson.fromJson(resultMsg, DaumCalendarEventVo.class);
		} catch (Exception e) {
			log.debug(resultMsg);
			throw e;
		}
		
		return resultVo;
	}
	
	public Collection<DaumCalendarEventVo> getEventList(DaumCalendarEventVo eventVo) throws Exception {
		DaumApi calendarApi = (DaumApi) ThreadLocalUtil.get("calendarApi");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("start_at", StringUtils.stripToEmpty(eventVo.getStartAt())));
		params.add(new BasicNameValuePair("end_at", StringUtils.stripToEmpty(eventVo.getEndAt())));
		
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
		
		String resultMsg = "";
		Collection<DaumCalendarEventVo> resultVo = null;
		try {
			resultMsg = authHttp.getHttpPost(apiUrl + "/event/index.json", formEntity, calendarApi.getConsumer());
			resultVo = gson.fromJson(resultMsg, new TypeToken<Collection<DaumCalendarEventVo>>(){}.getType());
		} catch (Exception e) {
			log.debug(resultMsg);
			throw e;
		}
		
		return resultVo;
	}

}

package com.john_na.common.api;

import oauth.signpost.OAuthConsumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class AuthHttp {

	// OAuth 인증으로 GET 요청
	public String getHttpGet(String http_url, OAuthConsumer consumerHttp)
			throws Exception {
		HttpGet http_get = new HttpGet(http_url);

		if (consumerHttp != null) {
			consumerHttp.sign(http_get);
		}

		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpResponse response = httpclient.execute(http_get);

		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity); // String return
	}

	// HTTP GET 요청
	public String getHttpGet(String http_url) throws Exception {
		HttpGet http_get = new HttpGet(http_url);
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpResponse response = httpclient.execute(http_get);

		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity);
	}

	// HTTP POST 요청
	public String getHttpPost(String http_url, UrlEncodedFormEntity formEntity,
			OAuthConsumer consumerHttp) throws Exception {
		HttpPost http_post = new HttpPost(http_url);
		http_post.setEntity(formEntity);

		if (consumerHttp != null) {
			consumerHttp.sign(http_post);
		}

		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpResponse response = httpclient.execute(http_post);

		HttpEntity entity = response.getEntity();

		return EntityUtils.toString(entity);
	}

}
package com.john_na.common.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

@Component("daumApiConsumer")
@SuppressWarnings("serial")
public class DaumApiConsumer extends CommonsHttpOAuthConsumer {

	@Autowired
	public DaumApiConsumer(@Value("#{api['PROFILE_CONSUMER_KEY']}") String consumerKey, @Value("#{api['PROFILE_CONSUMER_SECRET_KEY']}") String consumerSecret) {
		super(consumerKey, consumerSecret);
		System.out.println(consumerKey + " : " + consumerSecret);
	}

}

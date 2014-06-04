package com.john_na.common.api;

import java.util.ResourceBundle;

public class DaumApiBundle {
	private static final ResourceBundle bundle = ResourceBundle.getBundle("daum-api-key");
	
	public static String getValue(String key) {
		return bundle.getString(key);
	}
}

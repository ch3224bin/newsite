package com.john_na.common.api;

import org.anyframe.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.john_na.common.entity.DaumAPIVo;
import com.john_na.common.entity.DaumApiReturnVo;
import com.john_na.common.entity.DaumProfileVo;

@Service
public class DaumProfileService {
	private final Gson gson = new Gson();
	private final AuthHttp authHttp = new AuthHttp();
	
	@Value("#{api['PROFILE_CALLBACK_URL']}")
	private String callbackUrl;
	
	@Value("#{api['PROFILE_API_URL']}")
	private String apiUrl;
	
	public String requestToken() throws Exception {
		DaumApi profileApi = (DaumApi) ThreadLocalUtil.get("profileApi");
		return profileApi.requestToken(callbackUrl);
	}
	
	public DaumProfileVo getProfile() throws Exception {
		DaumApi profileApi = (DaumApi) ThreadLocalUtil.get("profileApi");
		
        String result = authHttp.getHttpGet(apiUrl, profileApi.getConsumer());
		DaumApiReturnVo vo = gson.fromJson(result, DaumApiReturnVo.class);
		
		return vo.getUser();
	}

	public DaumAPIVo createProfileApiVo() {
		DaumApi profileApi = (DaumApi) ThreadLocalUtil.get("profileApi");
		
        DaumAPIVo daumProfileApiVo = new DaumAPIVo();
        daumProfileApiVo.setAccessToken(profileApi.getConsumer().getToken());
        daumProfileApiVo.setTokenSecret(profileApi.getConsumer().getTokenSecret());
		return daumProfileApiVo;
	}
}

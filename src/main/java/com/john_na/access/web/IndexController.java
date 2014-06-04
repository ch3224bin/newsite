package com.john_na.access.web;

import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.anyframe.util.ThreadLocalUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.john_na.access.service.IAuthService;
import com.john_na.category.service.ICategoryService;
import com.john_na.common.api.DaumApi;
import com.john_na.common.api.DaumApiBuilder;
import com.john_na.common.api.DaumCalendarService;
import com.john_na.common.api.DaumProfileService;
import com.john_na.common.entity.DaumAPIVo;
import com.john_na.common.entity.DaumCalendarCategoryVo;
import com.john_na.common.entity.DaumProfileVo;

@Controller
public class IndexController {
	
	@Inject private IAuthService authService;
	@Inject private ICategoryService categoryService;
	@Inject private DaumCalendarService daumCalendarService;
	@Inject private DaumProfileService daumProfileService;
	
	@RequestMapping("/index.do")
	public String index(@RequestParam(value="oauth_token", required=false) String oauthToken,
						@RequestParam(value="oauth_verifier", required=false) String oauthVerifier,
						@RequestParam(value="access_token", required=false) String accessToken,
						@RequestParam(value="token_secret", required=false) String tokenSecret,
						HttpServletRequest request) throws Exception  {
		
		// 1. 프로필 인증
		HttpSession session = request.getSession();
		DaumAPIVo daumProfileApiVo = (DaumAPIVo) session.getAttribute("daumProfileApiVo");
		
		DaumApi profileApi = (DaumApi) session.getAttribute("profileApi");
		if (profileApi == null) {
			profileApi = DaumApiBuilder.getProfileApi();
			session.setAttribute("profileApi", profileApi);
		}
		ThreadLocalUtil.add("profileApi", profileApi);
		// 1-1. 이미 프로필 인증됨
		if (daumProfileApiVo != null && StringUtils.isNotEmpty(daumProfileApiVo.getAccessToken()) && StringUtils.isNotEmpty(daumProfileApiVo.getTokenSecret())) {
			return "redirect:/authCal.do";
		}
		// 1-2. 방금 authored마침.
		if (StringUtils.isNotEmpty(oauthToken) && StringUtils.isNotEmpty(oauthVerifier)) { // access
			try {
				profileApi.accessToken(oauthVerifier);
				
				daumProfileApiVo = daumProfileService.createProfileApiVo();
				authService.updateProfile(daumProfileApiVo);
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:http://john-na.com";
			}
			
			session.setAttribute("daumProfileApiVo", daumProfileApiVo);
			
			return "redirect:/authCal.do";
		}
		// 1-3. 새로접속.
		if (StringUtils.isNotEmpty(accessToken) && StringUtils.isNotEmpty(tokenSecret)) { // access
			try {
				profileApi.setTokenWithSecret(accessToken, tokenSecret);
				
				DaumProfileVo profile = daumProfileService.getProfile();
				if (profile == null) {
					String authUrl = daumProfileService.requestToken();
					return "redirect:" + authUrl;
				}
				daumProfileApiVo = daumProfileService.createProfileApiVo();
				daumProfileApiVo.setUserId(profile.getId());
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:http://john-na.com";
			}
			
			session.setAttribute("daumProfileApiVo", daumProfileApiVo);
			
			return "redirect:/authCal.do";
		}
		// 1-4.
		if (StringUtils.isEmpty(oauthToken) || StringUtils.isEmpty(oauthVerifier)) {
			String authUrl = daumProfileService.requestToken();
			return "redirect:" + authUrl;
		}
		
		return "common/error";
	}
	
	@RequestMapping("/authCal.do")
	public String authCal(@RequestParam(value="oauth_token", required=false) String oauthToken,
						@RequestParam(value="oauth_verifier", required=false) String oauthVerifier,
						Model model,
						HttpServletRequest request) throws Exception {
		// 1. 캘린더 인증
		HttpSession session = request.getSession();
		DaumAPIVo daumProfileVo = (DaumAPIVo) session.getAttribute("daumProfileApiVo");
		model.addAttribute("daumProfileVo", daumProfileVo);
		
		DaumAPIVo daumCalendarApiVo = (DaumAPIVo) session.getAttribute("daumCalendarApiVo");
		
		DaumApi calendarApi = (DaumApi) session.getAttribute("calendarApi");
		if (calendarApi == null) {
			calendarApi = DaumApiBuilder.getCalendarApi();
			session.setAttribute("calendarApi", calendarApi);
		}
		ThreadLocalUtil.add("calendarApi", calendarApi);
		// 1-1. 이미 캘린더 인증됨
		if (daumCalendarApiVo != null && StringUtils.isNotEmpty(daumCalendarApiVo.getAccessToken()) && StringUtils.isNotEmpty(daumCalendarApiVo.getTokenSecret())) {
			return "redirect:/main.do";
		}
		
		// 1-2. DB에서 key를 가져옮
		daumCalendarApiVo = authService.getCalendarAuthKey();
		if (daumCalendarApiVo != null && StringUtils.isNotEmpty(daumCalendarApiVo.getAccessToken()) && StringUtils.isNotEmpty(daumCalendarApiVo.getTokenSecret())) {
			try {
				calendarApi.setTokenWithSecret(daumCalendarApiVo.getAccessToken(), daumCalendarApiVo.getTokenSecret());
				
				Collection<DaumCalendarCategoryVo> categoryList = categoryService.getCategoryList();
				if (categoryList == null) {
					authService.removeCalendarAuthKey();
					return "redirect:/authCal.do";
				}
			}catch(Exception e){
				e.printStackTrace();
				authService.removeCalendarAuthKey();
				return "redirect:/authCal.do";
			}
			
			session.setAttribute("daumCalendarApiVo", daumCalendarApiVo);
			return "redirect:/main.do";
		}
		
		// 1-3. 방금 authored마침.
		if (StringUtils.isNotEmpty(oauthToken) && StringUtils.isNotEmpty(oauthVerifier)) { // access
			try {
				calendarApi.accessToken(oauthVerifier);
				
				daumCalendarApiVo = daumCalendarService.createCalendarApiVo();
				daumCalendarApiVo.setUserId(daumProfileVo.getUserId());
				authService.updateCalendar(daumCalendarApiVo);
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:http://john-na.com";
			}
			
			session.setAttribute("daumCalendarApiVo", daumCalendarApiVo);
			
			return "redirect:/main.do";
		}
		if (StringUtils.isEmpty(oauthToken) || StringUtils.isEmpty(oauthVerifier)) {
			String authUrl = daumCalendarService.requestToken();
			return "redirect:" + authUrl;
		}
		
		return "common/error";
	}
}

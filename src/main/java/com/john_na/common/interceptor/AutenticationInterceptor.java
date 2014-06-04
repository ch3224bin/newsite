package com.john_na.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.anyframe.util.ThreadLocalUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.john_na.common.api.DaumApi;
import com.john_na.common.entity.DaumAPIVo;

public class AutenticationInterceptor extends HandlerInterceptorAdapter  {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		if ( session != null ){
			DaumAPIVo daumCalendarVo = (DaumAPIVo) session.getAttribute("daumCalendarApiVo");
			if ( daumCalendarVo != null ){
				ThreadLocalUtil.add("daumCalendarApiVo", daumCalendarVo);
			}
			DaumAPIVo daumProfileVo = (DaumAPIVo) session.getAttribute("daumProfileApiVo");
			if ( daumProfileVo != null ){
				ThreadLocalUtil.add("daumProfileApiVo", daumProfileVo);
			}
			DaumApi profileApi = (DaumApi) session.getAttribute("profileApi");
			if ( profileApi != null ) {
				ThreadLocalUtil.add("profileApi", profileApi);
			}
			DaumApi calendarApi = (DaumApi) session.getAttribute("calendarApi");
			if ( calendarApi != null ) {
				ThreadLocalUtil.add("calendarApi", calendarApi);
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		ThreadLocalUtil.clearSharedInfo();
		super.afterCompletion(request, response, handler, ex);
	}
}

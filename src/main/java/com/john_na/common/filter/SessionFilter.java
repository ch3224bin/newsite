package com.john_na.common.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.john_na.common.entity.DaumAPIVo;


public class SessionFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest hr = (HttpServletRequest)request;
		String requestURI = hr.getRequestURI();
		String queryString = hr.getQueryString();
		
		HttpSession session = hr.getSession(false);
		
		boolean hasSession = false;
		boolean hasProfileInfo = false;
		boolean hasCalendarInfo = false;
		if(session != null) {
			DaumAPIVo daumProfileApiVo = (DaumAPIVo) session.getAttribute("daumProfileApiVo");
			DaumAPIVo daumCalendarApiVo = (DaumAPIVo) session.getAttribute("daumCalendarApiVo");
			
			hasProfileInfo = daumProfileApiVo != null
					&& StringUtils.isNotEmpty(daumProfileApiVo.getAccessToken())
					&& StringUtils.isNotEmpty(daumProfileApiVo.getTokenSecret());
			hasCalendarInfo = daumCalendarApiVo != null
					&& StringUtils.isNotEmpty(daumCalendarApiVo.getAccessToken())
					&& StringUtils.isNotEmpty(daumCalendarApiVo.getTokenSecret());
			hasSession = hasProfileInfo && hasCalendarInfo;
			
		}
		
		if (requestURI.matches(".+[.]{1}js$|.+[.]{1}css$|.+[.]{1}gif$|.+[.]{1}jpg$|.+[.]{1}png$|.+[.]{1}bmp$|.+[.]{1}ico$") || requestURI.indexOf("/cardMgt.do") > -1 ) 
		{
			filterChain.doFilter(request, response);
		}
		else if (("/".equals(requestURI)
					|| requestURI.indexOf("/index.jsp") != -1
					|| requestURI.indexOf("/index.do") != -1
					|| requestURI.indexOf("/authCal.do") != -1) 
				&& hasSession)
		{
			((HttpServletResponse)response).sendRedirect("/main.do");
		}
		else if (("/".equals(requestURI)
				|| requestURI.indexOf("/index.jsp") != -1
				|| requestURI.indexOf("/index.do") != -1
				|| requestURI.indexOf("/authCal.do") != -1) 
			&& !hasSession)
		{
			// /authCal.do로 캘린더인증 시 프로필 인증이 없다면 index로 되돌림
			if (requestURI.indexOf("/authCal.do") != -1 && !hasProfileInfo) {
				((HttpServletResponse)response).sendRedirect("/");
			} else {
				filterChain.doFilter(request, response);
			}
		}
		else if (hasSession)
		{
			filterChain.doFilter(request, response);
		}
		else
		{
			if(!"/".equals(requestURI) && requestURI.indexOf("/index.jsp") == -1) {
				request.setAttribute("targetUrl", requestURI + "?" + queryString + paramToString(request));
			}
			request.getRequestDispatcher("/").forward(request, response);
		}
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@SuppressWarnings("unchecked")
	private String paramToString(ServletRequest request) {
		StringBuilder strParam = new StringBuilder();
		
		Enumeration<String> parameterNames = request.getParameterNames();
		for( ; parameterNames.hasMoreElements() ; ) {
			String paramName = parameterNames.nextElement();
			if("targetUrl".equals(paramName) || "method".equals(paramName)) continue;
			strParam.append("&").append(paramName).append("=").append(request.getParameter(paramName));
		}
		
		return strParam.toString();
	}

}

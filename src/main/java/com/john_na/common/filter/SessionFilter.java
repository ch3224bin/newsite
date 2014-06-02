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


public class SessionFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest hr = (HttpServletRequest)request;
		String requestURI = hr.getRequestURI();
		String queryString = hr.getQueryString();
		
		HttpSession session = hr.getSession(false);
		
		boolean hasSession = true;
		/*
		if(session != null) {
			Users user = (Users) session.getAttribute("user");
			
			hasSession = (user != null && StringUtils.isNotEmpty(user.getUserId()))
			|| requestURI.indexOf("/access.do") != -1;
			
		}
		*/
		
		if (requestURI.matches(".+[.]{1}js$|.+[.]{1}css$|.+[.]{1}gif$|.+[.]{1}jpg$|.+[.]{1}png$|.+[.]{1}bmp$|.+[.]{1}ico$") || requestURI.indexOf("/cardMgt.do") > -1 ) {
			filterChain.doFilter(request, response);
		}/*else if(("/".equals(requestURI) || requestURI.indexOf("/index.jsp") != -1) && hasSession) {
			((HttpServletResponse)response).sendRedirect("/todo.do?method=todolist");
		}*/else if(hasSession) {
			filterChain.doFilter(request, response);
		}else{
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

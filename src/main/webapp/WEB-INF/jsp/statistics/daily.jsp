<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="base" uri="http://john-na.com/tlds/base" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<title>John-na.com</title>
<script type="text/javascript" src="/js/header.js"></script>
</head>
<body>
<input type="hidden" name="access_token" value="${daumProfileApiVo.accessToken}">
<input type="hidden" name="token_secret" value="${daumProfileApiVo.tokenSecret}">
<div data-role="page" id="daily">
	<jsp:include page="../header/header.jsp" />
	
	<div data-role="main" class="ui-content">
	<c:if test="${not empty eventList}">
		<ul id="dailyList" data-role="listview" data-inset="true">
		<c:forEach var="day" items="${eventList}">
		    <li data-role="list-divider">${day.startAt} <span class="ui-li-count">총 ${day.count}건, ${day.min}분</span></li>
		    <c:forEach var="event" items="${day.events}">
		    <li>
		    <h2>${event.categoryName}</h2>
		    <p><strong>${event.title}</strong></p>
		    <div style="font-size: 0.8em;font-weight: normal;"><base:wikiMarkup2Html value="${event.description}" /></div>
		        <p class="ui-li-aside"><strong>${event.min}분</strong>, ${event.startTime} ~ ${event.endTime}</p>
		    </li>
		    </c:forEach>
		</c:forEach>
		</ul>
	</c:if>
	</div>
	<div data-role="footer" data-position="fixed">
		<div class="ui-bar">
			<a href="#" id="prev" class="ui-btn ui-btn-icon-right ui-icon-arrow-l">이전</a>
        	<a href="#" id="next" class="ui-btn ui-btn-icon-right ui-icon-arrow-r">이후</a>
        	<select id="category" data-mini="true" data-inline="true">
        		<option value="">전체</option>
        	<c:forEach var="item" items="${enabledCategoryList}">
        		<option value="${item.id}">${item.name}</option>
        	</c:forEach>
        	</select>
		</div>
	</div>
	<script type="text/javascript" src="/js/daily.js"></script>
</div>
</body>
</html>

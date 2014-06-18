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
<div data-role="page" id="main">
	<jsp:include page="../header/header.jsp" />
	
	<div data-role="main" class="ui-content">
	<c:if test="${empty recordInProgress}">
		<form data-ajax="false" id="startForm" action="/start.do" method="post">
			<input type="hidden" name="categoryName">
		<c:if test="${not empty categoryList}">
			<fieldset data-role="controlgroup" data-mini="true" id="fieldset">
			<c:forEach var="item" items="${categoryList}" varStatus="loop">
		        <input name="categoryId" id="${item.id}" value="${item.id}" type="radio">
		        <label data-label-id="${item.id}" for="${item.id}">${item.name}</label>
			</c:forEach>
		        <input type="submit" value="시작" data-icon="clock" data-iconpos="right">
		    </fieldset>
		</c:if>
		<c:if test="${empty categoryList}">
			<ul data-role="listview">
				<li><a href="/categoryManage.do">카테고리를 추가하세요.</a></li>
			</ul>
		</c:if>
		</form>
	</c:if>
	<c:if test="${not empty recordInProgress}">
		<form data-ajax="false" id="finishForm" action="" method="post">
		<div data-role="collapsible" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-mini="true">
		    <h4>${recordInProgress.categoryName}</h4>
	        <div class="ui-field-contain"><input name="title" value='<c:out value="${recordInProgress.title}" escapeXml="true" />' placeholder="제목"></div>
	        <div class="ui-field-contain"><textarea rows="4" cols="40" name="description" placeholder="내용"><c:out value="${recordInProgress.description}" escapeXml="true" /></textarea></div>
	        <div class="ui-field-contain"><input id="btnSave" type="button" value="저장" data-icon="action" data-mini="true"></div>
		</div>
		<div class="ui-field-contain"><input id="btnFinish" type="button" value="종료" data-icon="clock" data-mini="true"></div>
		</form>
	</c:if>
	</div>
	
	<script type="text/javascript" src="/js/main.js"></script>
</div>
</body>
</html>

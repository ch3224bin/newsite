<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="base" uri="http://john-na.com/tlds/base" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1" />
<title>John-na.com</title>
<script type="text/javascript" src="/js/header.js"></script>
</head>
<body>
<input type="hidden" name="access_token" value="${daumProfileApiVo.accessToken}">
<input type="hidden" name="token_secret" value="${daumProfileApiVo.tokenSecret}">
<div data-role="page" id="category_manage">
	<jsp:include page="../header/header.jsp" />
	
	<div data-role="main">
		<c:if test="${not empty categoryList}">
		<ul data-role="listview">
			<c:forEach var="item" items="${categoryList}">
			<li>
				<div data-role="fieldcontain">
					<label for="${item.id}">${item.name}</label>
			        <select name="category" id="${item.id}" data-role="flipswitch">
			        	<c:if test="${item.useYn eq 'Y'}">
			            <option value="N">Off</option>
			            <option value="Y" selected="">On</option>
			        	</c:if>
			        	<c:if test="${item.useYn ne 'Y'}">
			            <option value="N" selected="">Off</option>
			            <option value="Y">On</option>
			        	</c:if>
			        </select>
				</div>
			</li>
			</c:forEach>
		</ul>
		</c:if>
	</div>
	<script type="text/javascript" src="/js/category_manage.js"></script>
</div>
</body>
</html>

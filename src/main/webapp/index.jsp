<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1" />
<title>John-na.com</title>
<link rel="stylesheet" href="/js/jquery_mobile/jquery.mobile.min.css" />
<script type="text/javascript" src="/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery_mobile/jquery.mobile.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript">
$(function(){
	<%-- 사용자 다음 프로필 access token 가져오기 --%>
	var access_token = localStorage.getItem("ACCESS_TOKEN");
	var token_secret = localStorage.getItem("TOKEN_SECRET");
	
	if (access_token && token_secret) {
		window.location.href = "/index.do?access_token=" + access_token + "&token_secret=" + token_secret;
	} else {
		window.location.href = "/index.do";
	}
});
</script>
</head>
<body>
</body>
</html>

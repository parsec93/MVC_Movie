<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String sId = (String)session.getAttribute("sId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<header>
		<p align="right">
		<%if(sId != null) { %>
			<a href="MemberInfo.me"><%=sId %>님</a> | <a href="MemberLogoutPro.me">로그아웃</a> 
		<%} else { %>	
			<a href="MemberLoginForm.me">로그인</a> 
		<%} %>
		</p>
	</header>
<body>
	<h1>영화관 회원 정보</h1>
</body>
</html>
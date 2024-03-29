<%@page import="vo.MovieBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//저장된 세션 아이디 가져오기
	String sId = (String)session.getAttribute("sId");

	// 영화 목록 정보 가져오기
	ArrayList<ArrayList> reservationList = (ArrayList)request.getAttribute("reservationList");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	h1 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 1024px;
		border: 1px solid darkgray;
	}
	
	div {
		width: 1100px;
	}
	
	#link {
		border: none;
	}

	#tr_top {
		background: orange;
		width: 800px; 
		text-align: center;
		font-weight: bold;
	}
	
	#td_center {
		text-align: center;
	}
</style>
</head>
<body>
	<header>
		<!-- 세션 아이디가 있을 경우 로그아웃 링크(Logout.me) 표시, 없을 경우 로그인 링크(LoginForm.me) 표시 -->
		<p align="right">
		<%if(sId != null) { %>
			<a href="MemberInfo.me"><%=sId %>님</a> | <a href="MemberLogoutPro.me">로그아웃</a> 
		<%} else { %>	
			<a href="MemberLoginForm.me">로그인</a> 
		<%} %>
		</p>
	</header>
	<h1>전체 예약 목록</h1>
	<%if(reservationList == null) {%>
		<h1>예약 목록이 존재하지 않습니다.</h1>
	<%}else {%>
		 <% for(ArrayList reservationInfo : reservationList){%>
		 		<table>
		 		<tr>
		 			<td>제목</td><td>영화번호</td><td>영화시간</td><td>예매인원</td><td>총금액</td>
		 		</tr>
		 		<tr>
		 		<td id="td_center"><a href="MovieInfoDetail.mo?movie_idx=<%=reservationInfo.get(5)%>"><%=reservationInfo.get(0)%></a></td>
				<td id="td_center"><%=reservationInfo.get(1) %>관</td>
				<td id="td_center"><%=reservationInfo.get(2) %>분</td>
				<td id="td_center"><%=reservationInfo.get(3)%>명</td>
				<td id="td_center"><%=reservationInfo.get(4)%>원</td>
		 		</tr>
		 		</table>
		 <%}%>
	<%}%>
</body>
</html>




















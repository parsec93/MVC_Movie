<%@page import="java.text.SimpleDateFormat"%>
<%@page import="vo.MovieBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
MovieBean movieBean = (MovieBean)request.getAttribute("movieBean");
%>
</head>
<body>
	<h1>영화 상세 정보 </h1>
	<%if(movieBean == null){%>
		<h3>영화정보가 없습니다. </h3>
	<% }%> 
	<form action = "MovieReservation.mo?idx=<%=movieBean.getMovie_idx()%>" >
	
	<table>
		<tr><td>영화제목</td><td>영화내용</td><td>상영시간</td><td>상영관</td><td>상영시작</td><td>상영마감</td></tr>
		<tr>
			<td><%=movieBean.getMovie_title() %></td>
			<td><%=movieBean.getMovie_content() %></td>
			<td id="td_center"><%=movieBean.getMovie_time() %>분</td>
			<td id="td_center"><%=movieBean.getMovie_hall_num() %>관</td>
			<td id="td_center"><%=new SimpleDateFormat("yyyy-MM-dd").format(movieBean.getMovie_end_day()) %></td>
			<td id="td_center"><%=new SimpleDateFormat("yyyy-MM-dd").format(movieBean.getMovie_end_day()) %></td>
		</tr>
	</table>
	<input type="button" id="button" value="예매하기" 
					onclick="location.href='MovieReservation.mo?movie_idx=<%=movieBean.getMovie_idx()%>'">
	<input type="button" value="뒤로가기" onclick="history.back()">
	</form>
</body>
</html>
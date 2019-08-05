<%@page import="java.net.URLDecoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="vo.MovieBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
String sId = (String)session.getAttribute("sId");

//영화 목록 정보 가져오기 
ArrayList<MovieBean> movieList = (ArrayList<MovieBean>)request.getAttribute("movieList");

//목록 종류, 정렬 방식 가져오기
String listType = (String)request.getAttribute("listType");
String sort = (String)request.getAttribute("sort");

ArrayList<String> todayMovieList = (ArrayList<String>)request.getAttribute("todayMovieList");
ArrayList<String> todayMovieIndexList = (ArrayList<String>)request.getAttribute("todayMovieIndexList");

%>
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
	<h1>MovieList</h1>
	
	<% if(movieList == null){%>
		<h3>영화 목록이 존재하지 않습니다.</h3>		
	<%}%>
	
	<div align="left">
		<a href="MovieList.mo?listType=now&sort=<%=sort%>">상영중</a>&nbsp;&nbsp;
		<a href="MovieList.mo?listType=soon&<%=sort%>">개봉예정</a>
	</div>
	
	
	<div align="right">
		<a href="MovieList.mo?sort=title&listType=<%=listType%>">제목순</a>&nbsp;&nbsp;
		<a href="MovieList.mo?sort=date&listType=<%=listType%>">최신순</a>
	</div>
	<table>
		<tr><td>영화제목</td><td>영화내용</td><td>상영시간</td><td>상영관</td><td>상영시작</td><td>상영마감</td></tr>
		<% 
		for(int i = 0 ; i<movieList.size(); i++){
			MovieBean mb = (MovieBean)movieList.get(i);
		%>	
		<tr>
			<td><a href="MovieInfoDetail.mo?idx=<%=mb.getMovie_idx()%>"><%=mb.getMovie_title() %></a></td>
			<td><%=mb.getMovie_content() %></td>
			<td id="td_center"><%=mb.getMovie_time() %>분</td>
			<td id="td_center"><%=mb.getMovie_hall_num() %>관</td>
			<td id="td_center"><%=new SimpleDateFormat("yyyy-MM-dd").format(mb.getMovie_end_day()) %></td>
			<td id="td_center"><%=new SimpleDateFormat("yyyy-MM-dd").format(mb.getMovie_end_day()) %></td>
		</tr>
		
		
			
		<%
		}
		%>
	</table>
	
	<p>
		<table>
		<%for(int i = 0 ; i<todayMovieList.size(); i++) {%>
			<tr>
				<td><a href="MovieInfoDetail.mo?idx=<%=todayMovieIndexList.get(i)%>"><%=URLDecoder.decode(todayMovieList.get(i), "UTF-8") %></a>
			</tr>
		<%} %>
		</table>
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>
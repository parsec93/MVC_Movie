<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
String movie_idx = request.getParameter("movie_idx");

%>
<script type="text/javascript">
	function up(){
		var rNum = document.getElementById('rNum');
		rNum.value ++;
		return;
	}
	
	function down(){
		var rNum = document.getElementById('rNum');
		if(rNum.value	 > 0){
			rNum.value --;	
		} else {
			rNum.value = 0;
		}
		
		return;
	}
</script>
<body>
	<h1>영화 예매 페이지 </h1>
	<form action="MovieReservationPro.mo?movie_idx=<%=movie_idx %>" method="post" id="reservationForm" onsubmit="return check()">
	<table>
	<tr>
		<td>영화 번호 : <%=movie_idx %></td>
		<td></td>
	</tr>
	<tr>
		<td> 상영 시간 : </td>  
		<td>
			<select name="time">
            	<option value=1 >12:00 ~ 13:30</option>
                <option value=2 >14:00 ~ 15:30</option>
                <option value=3 >16:00 ~ 17:30</option>
                <option value=4 >18:00 ~ 19:30</option>	
			</select>
		</td>
	</tr>
	<tr>
		<td>인원 : </td> 
		<td>
			<input type="text" size="3" value="0" id="rNum" > 
			<input type="button" value="▲" onclick="up()">
			<input type="button" value="▼" onclick="down()">
		</td>
	</tr>
	<tr>
		<td>최종 금액 : </td>
		<td>
			<input type="texts" name="pee" value="0">
		</td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="예매하기">
			<input type="button" value="뒤로가기" onclick="history.back()">
		</td>
	</tr>
	</table>
	</form>
</body>
</html>
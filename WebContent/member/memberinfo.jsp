<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	String sId = (String)session.getAttribute("sId");
// if(sId != null){
// 	 out.println("<script>");
// 	    out.println("alert('잘못된 접근입니다!')");
// 	    out.println("location.href='index.jsp'");
// 	    out.println("</script>");
// }


	MemberBean mb = (MemberBean)request.getAttribute("mb");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function changeEditMode(){
		document.infoForm.name.readOnly = false;
	} 
</script>
</head>
<body>
	<h1>회원 정보 수정 페이지</h1><br>
	<form action="MemberJoinPro.me" method="post" name="infoForm">
	이름 : <input type="text" name="name" readonly="readonly" value="mb.getMember_name"><br>
	아이디 : <input type="text" name="id"> <input type="button" value="아이디 중복 확인"><br>
	비밀번호 : <input type="text" name="password"><br>
	성별 : <input type="radio" value="남" name="gender">남<input type="radio" value="여" name="gender">여<br>
	주민번호 : <input type="text" name="jumin1" onkeyup="chkJumin(this, 1)"> - <input type="text" name="jumin2" onkeyup="chkJumin(this, 2)"><br>
	E-Mail : <input type="text" name="email1" id="email1" value="" maxlength="30"/>@ <input type="text" name="email2" id="email2" value="naver.com" maxlength="30"/> 
	<select onchange="chkEmailDomainSelect(this)" style="width:100px;margin-right:10px" name="selectEmail" id="selectEmail"> 
	<option value="">직접입력</option> 
	<option value="naver.com" selected>naver.com</option> 
	<option value="hanmail.net">hanmail.net</option> 
	<option value="hotmail.com">hotmail.com</option> 
	<option value="nate.com">nate.com</option> 
	<option value="yahoo.co.kr">yahoo.co.kr</option> 
	<option value="empas.com">empas.com</option> 
	<option value="dreamwiz.com">dreamwiz.com</option> 
	<option value="freechal.com">freechal.com</option> 
	<option value="lycos.co.kr">lycos.co.kr</option> 
	<option value="korea.com">korea.com</option> 
	<option value="gmail.com">gmail.com</option> 
	<option value="hanmir.com">hanmir.com</option> 
	<option value="paran.com">paran.com</option> 
	</select>
	<br>
	전화번호 : <input type="text" name="phone1"> - <input type="text" name="phone2"> - <input type="text" name="phone3"><br><br>
	<input type="submit" value="회원가입">&nbsp;&nbsp;
	<input type="button" value="수정모드로 전환" onclick = "changeEditMode()">&nbsp;&nbsp;
	<input type="button" onclick="history.back()" value="취소">&nbsp;&nbsp;
	<input type="button" onclick="location.href='MemberReservationInfo.me'" value="예매내역 확인">
	</form>
	
</body>
</html>
<%-- <%@page import="LoginTask.service.insertJoinerService"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:include page="index.jsp" flush="false" />

<%-- <jsp:useBean id="joiner" class="LoginTask.model.Joiner"/>
<jsp:setProperty name="joiner" property="*"/> --%>

<% 
int resultCnt = (int)request.getAttribute("resultCntTmp");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/MyPageForm.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 확인</title>
<style>
</style>
</head>
<body>
<%if(resultCnt > 0){ %>
<div id="containAll">

	<div id="container">
		<h1 style="font-size:50px;">회원정보 확인</h1>
		<p>회원가입을 축하드립니다!</p>
		<table>
		    <tr>
		        <td>ID</td>  
		        <td>${joiner.id}</td>
		    </tr>
		    
		    <tr>
		        <td>비밀번호</td>
		        <td>${joiner.pwd}</td>
		    </tr>
		    
		    <tr>
		        <td>이름</td>
		        <td>${joiner.name}</td>
		    </tr>
		 
		    <tr>
		        <td>생일</td>
		        <td>${joiner.birth_year}/${joiner.birth_mon}/${joiner.birth_day}</td>
		    </tr>
		    
		    <tr>
		        <td>성별</td>
		        <td>${joiner.gender}</td>
		    </tr>
		    
		    <tr>
		        <td>이메일</td>
		        <td>${joiner.email}</td>
		    </tr>
		    
		    <tr>
		        <td>핸드폰 번호</td>
		        <td>${joiner.phone}</td>
		    </tr>
		    
		     <tr>
		        <td colspan="" style="text-align: center;"><input type="button" value="인증메일 발송하기" onclick="location.href='<c:url value="/mail/RegiConfirmMail/${joiner.id}"/>'"></td>
		        <td colspan="" style="text-align: center;"><input type="button" value="로그인" onclick="location.href='<c:url value="/login/loginForm"/>'"></td>
		    </tr>
		 </table>
	</div>
</div>

<%}else{%>

<h1>회원가입이 정상적으로 이루어지지 않았습니다.</h1>
<a href="<c:url value="/regi/RegisterFirst"/>">회원가입 다시하기</a><br>
<a href="<c:url value="/indexHome"/>">홈으로 가기</a>
<%}%>

</body>
</html>

<jsp:include page="footer.jsp" flush="false" />
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	<%
		request.setCharacterEncoding("UTF-8");
		String message = request.getParameter("message");
		if(StringUtils.isNotBlank(message)){
			%>
			alert("<%=message %>");
			<%
		}
	%>
	
	function clickHandler(event){
		event.preventDefault();//a 태그가 원래의 기능을 잃어버림
		let href = event.target.dataset["href"];
		let logoutForm = document.logoutForm;
		logoutForm.action=href;
		logoutForm.submit(); //a 태그를 클릭해도, form은 post방식으로 보내라
		return false; //a 태그가 원래의 기능을 잃어버림
	}
</script>
</head>
<body>
<% String authMember = (String)session.getAttribute("authMember");  %>
<%if(StringUtils.isNotBlank(authMember)){
	%>
	<form name="logoutForm" method="post"></form>
	<%=authMember %>님 <a href="#" onclick="clickHandler(event);" data-href="<%=request.getContextPath()%>/login/logout.do">로그아웃</a>
	<% 	
}else{
	%> 
	<a href="<%=request.getContextPath() %>/login/loginForm.do">로그인하러 가기</a>
	<a href="">회원가입</a>
	
<%	
}
%>
</body>
</html>
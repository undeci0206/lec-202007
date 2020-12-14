<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#topMenu{
		width: 100%;
		height: 50px;
	}
	
	#topMenu li{
		float : left;
		list-style: none;
		padding-right: 50px;
	}
	
	#leftMenu{
		float: left;
		width: 15%;
		height: 500px;
	}
	
	#leftMenu a{
		cursor: pointer;
	}
	
	#contents{
		float: right;
		width: 84%;
		height: 500px;
	}

	#footer{
		float:left;
		width:100%;
		height: 50px;
	}
	
	div{
		border: 1px solid black;	
	}
</style>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<div id = "topMenu">
	<jsp:include page="/includee/topMenu.jsp"></jsp:include>
</div>
<div id = "leftMenu">
	<jsp:include page="/includee/leftMenu.jsp"></jsp:include>
</div>
<div id = "contents">
	<%
	String includePath = (String)request.getAttribute("includePath");
	if(StringUtils.isNotBlank(includePath)){
		pageContext.include(includePath); 
	}
	%>
</div>
<div id = "footer">
	<jsp:include page="/includee/footer.jsp"></jsp:include>
</div>
</body>
</html>
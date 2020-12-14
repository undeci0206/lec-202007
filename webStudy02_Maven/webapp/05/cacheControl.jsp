<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/cacheControl.jsp</title>
<script type ="text/javascript" src="<%=request.getContextPath() %>/js/sample.jsp"></script>
</head>
<body>
<h4>Cache 제어</h4>
<pre>
캐시 : 클라이언트 저장소에 저장됨
server.xml
﻿Cache-Control(HTTP/1.1), Pragma(HTTP/1.0), Expires(캐시데이터의 만료시간 설정)
<%
	response.setHeader("Pragma", "no-cache");
	response.addHeader("Pragma", "no-store");
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);	
%>

</pre>
<%-- <img src="<%=request.getContextPath() %>/images/cute4.JPG" /> --%>
</body>
</html>
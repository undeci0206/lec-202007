<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>다른 경로</h4>
<% 
Cookie[] cookies = request.getCookies(); //최대 20개까지 가능하므로 배열 형태로 돌려줌
	if(cookies!=null){
		for(Cookie tmp : cookies){
			out.println(String.format("%s : %s <br />", 
					tmp.getName(), URLDecoder.decode(tmp.getValue(), "UTF-8")));
		}
	}
%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- scope의 데이터 꺼내서 담아주기 -->
<% 
	String responseData = (String)request.getAttribute("data");
	String now = (String)request.getAttribute("now");
%>
<h4><%=responseData %></h4>
<h4><%=now %></h4>

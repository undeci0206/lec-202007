<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//scope에 있는 데이터 꺼내기
	String message = (String)request.getAttribute("message");
%>
<h4><%=message %></h4>
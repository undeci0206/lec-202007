<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/event-stream; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb"%>
  <%
  boolean flag = true;
  while(flag==true){
  		Thread.sleep(1000);
  		Date now = new Date();
  %>
id:<%=now.getTime()%>
event:test
data:<%=now%>
data:line data
<%
	out.flush();
}
%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	long start = System.currentTimeMillis();
	for(int i = 0; i<100; i++){
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			String sql = "SELECT MEM_NAME FROM MEMBER WHERE MEM_ID='a001' ";
			stmt.executeQuery(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			out.println(rs.getString(1));
		}
	}
	long end = System.currentTimeMillis();
	
	
%>
<%=end-start %>ms
</body>
</html>
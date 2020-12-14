<%@page import="kr.or.ddit.enumpkg.OperateType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/calculateForm.jsp</title>
</head>
<body>
<form action = "<%=request.getContextPath() %>/03/calculate.do" method ="post">
	<input type="number" name="leftOp" required />
	<select name = "operator" required>
		<option value="">연산자</option>
		<%
		for( OperateType tmp : OperateType.values()){
			%>
			<option value="<%=tmp.name()%>"><%=tmp.getSign() %></option>
			<%
		}
		%>
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULPLY">*</option>
		<option value="DIVIDE">/</option>
	</select>	
	<input type="number" name="rightOp" required/>
	<button type="submit">=</button>
</form>
</body>
</html>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("UTF-8");
	String param = request.getParameter("param");

%>
<%=param %>
<title>02/requestDesc.jsp</title>
</head>
<body>
<h4>HttpServletRequest</h4>
<pre>
	: http 프로토콜을 이용하여 서블릿을 대상으로 발생한 요청에 대한 캡슐화

	꼭 알아야 하는 것
	HTTP request 패키징 방식
	1) Request Line : URL Method Protocol/version
		http method : 요청의 목적, 요청의 패키징 방식
		GET(R) : 조회
		POST(C) : 생성
		PUT/PATCH(U) : 수정
		DELETE(D) : 삭제
		HEADER
		OPTION : ﻿ preflight요청에 사용
		TRACE : 디버깅에 사용됨. 이 메서드는 거의 지원하지 않음
		
	2) Request Header : meta data (진짜 데이터가 아닌 부가 데이터)
		
	3) Request Body (only POST): content body, message body
	
	**request의 메서드 종류
	<%=request.getCharacterEncoding()%>
	<%=request.getContentLength() %>
	<%=request.getContentType() %>
	server : <%=request.getLocalAddr() %>, <%=request.getLocalName() %>, <%=request.getLocalPort() %>
	client : <%=request.getRemoteAddr() %>, <%=request.getRemoteHost() %>, <%=request.getRemotePort() %>
	
	<%=request.getRequestURI() %>, <%=request.getMethod() %>, <%=request.getProtocol() %>
	<%=request.getRequestURL() %>
	<%=request.getQueryString() %> : 질의문자열->  ?sector1&sector2...sectorN
											     sector= "param_name=param_value"
	<%=request.getLocale() %>
	
	** 요청파라미터 확보
	String getParameter(name), String[] getParameterValues(name) //**근본적으로 파라미터는 맵의 형태로 관리된다.
	Map&lt;String, String[]&gt; getParameterMap(), Enumeration&lt;String&gt; getParameterNames() 
	
	**파라미터에 포함된 특수문자 처리(REC 2396 규약에 따라 URL encoding 방식으로 인코딩되어 전달됨)
	GET
	POST
	둘의 처리 방식이 다른 이유? 바디의 존재 여부가 달라지기 때문..(get은 파라미터가 라인에 출력됨 / post는 파라미터가 바디에 출력됨)
	공통점? 데이터를 꺼내기 전에 decoding방식을 설정한다.
	
</pre>
<form method = "post">
	<input type="text" name="param" />
	<input type="submit" value="전송" />
</form>
<table>
	<thead>
		<tr>
			<th>헤더명</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
	<%
		Enumeration<String> names = request.getHeaderNames();
		while(names.hasMoreElements()){
			String headerName = names.nextElement();
			String headerValue = request.getHeader(headerName);
			%>
			<tr>
				<td><%=headerName %></td>
				<td><%=headerValue %></td>
			</tr>
			<%
		}
	%>
	</tbody>
</table>
</body>
</html>
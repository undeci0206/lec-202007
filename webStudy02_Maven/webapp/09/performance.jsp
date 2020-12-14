<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/performance.jsp</title>
</head>
<body>
<h4>성능 확인</h4>
<pre>
	소요 시간(response time) : latency time + processing time
	<a href="oneConnOneProcess.jsp">한번의 연결 한번 처리 : 10ms, 0ms(두 번째 시간 : pulling을 썼을 때)</a>
	<a href="100Conn100Process.jsp">백번 연결, 백번 처리 : 961ms, 7ms</a>
	<a href="oneConn100Process.jsp">한번 연결, 백번 처리 : 21ms, 5ms</a>
</pre>
</body>
</html>
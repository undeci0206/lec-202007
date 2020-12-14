<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 디렉티브. 지시자. page라는 지시자는 거의 필수. 현재 페이지 자체에 대한 환경설정 값 변경함. -->
<%@ page import="kr.or.ddit.enumpkg.*" %> 
<html>
<head>
<meta charset="UTF-8">
<title>02/userAgent.jsp</title>
</head>
<body>
<h4>User Agent</h4>
<!-- 클라이언트가 전송한 요청에서 시스템에 대한 정보를 추출.
최종적으로 클라이언트에게 당신의 브라우저는 "크롬"입니다. 형태의 응답메시지를 클라이언트에게 다시 전송

1단계: 시스템에 대한 정보를 추출
2단계: 메세지 포맷팅 필요(한글 데이터 확인.. 각 브라우저의 헤더를 확인해 보아야 함. enum문법 활용) -->
<%-- <%
	String agent = request.getHeader("user-agent"); 
	String name = Browser.getBrowserName(agent);
	Date now = null;

%>
당신의 브라우저는 <%=name %>입니다. --%>
</body>
</html>
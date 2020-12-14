<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>자원의 식별</h4>
<pre>
	자원의 종류
	1) File System Resource : 파일시스템 절대 경로(drive)를 통해 접근
	2) ClassPath Resource : classpath 이후의 절대 경로(qualified name)를 통해 접근
	3) Web Resource : URL 체계를 통해 웹상에서 접근
	
	웹리소스 식별(identify) 방법
	URI : Uniform Resource Identifier -> URL, URN, URC를 포함하고 있는 개념
	URL(Locator) : 자원의 위치를 기준으로 식별 
	URN(Name) : 자원의 명칭을 기준으로 식별 -> 자원의 이름이 어딘가에 다 등록이 되어있어야 하기 때문에 과부하 우려 / 중복 해결 어려움
	URC(Content) : 자원의 속성을 기준으로 식별
	
	URL 표기 방식
	protocol://IP[domainname]:port/depth..../name
		
	ex) http://www.naver.com:80/depth.../name
	ex) http://IP:80/depth.../name과 같음
	
	절대경로 : 
		protocol://IP[domainname]:port/depth..../name
		client side : 반드시 context root부터 경로 기술
		ex) /webStudy01/images/cute8.jpg
		server side : context root 이후의 경로를 기술
	상대경로 : 브라우저의 주소를 기준으로 상대 경로가 시작됨.
		ex) ../depth/name
		
	//헷갈리면 이 주소가 어느 측면에서 사용될 것인지를 생각하면 된다.	
	//ex)서블릿 : 톰캣이 사용하는 키가 된다. 서블릿 매핑. WebServlet("/imageView.do")주소는 서버가 사용하는 주소가 된다.
	
</pre>
<%
	String url = "/images/cute8.JPG";
	URL resURL = getServletContext().getResource(url);
	File file = new File(resURL.getFile());
%>
<%=file.getAbsolutePath() %>
<%=file.length() %>
<img src="<%=request.getContextPath() %>/images/cute8.JPG" /> <!-- 클라이언트 사이드. ip주소까지밖에 모름. 도메인 맵핑까지만 사용 가능. context root부터 시작 가능 -->
<img src="../images/cute8.JPG" />
</body>
</html>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/servletContextDesc.jsp</title>
</head>
<body>
<h4>ServletContext application</h4>
<pre>
	CAC(Context Aware Computing)
	:하나의 웹어플리케이션과 어플리케이션이 운영되는 컨테이너에 대한 정보를 캡슐화한 객체(singleton).
	
	서블릿컨텍스트를 활용할 수 있는 경우 //서버 자체를 확인할 때 주로 사용
	1. 서블릿 스펙 버전은 서블릿 컨테이너를 따라간다. 서블릿 스펙 버전을 확인하려면 톰캣 이용..
	//우리가 사용하는 서블릿 스펙 3.1.. 2버전들과 part데이터를 가지고 노는 방법이 완전히 다름
	dynamic web module version 확인
	<%=application.getMajorVersion() %>.<%=application.getMinorVersion() %> //3.1
	
	2. 파일의 mime type 확인 -> 톰캣을 열어보면 mimeDB있음.
	
	3. logging : 로그 메시지를 기록하는 행위. %catalina_home%->logs->localhost, localhost_access_log 확인
	로그 메시지 : 클라이언트를 위한 게 아니라 서버를 위한 메세지. 웹상에서는 안보이지만 console창에서 확인 가능
	<% application.log("의도적으로 기록한 로그 메시지"); %>
	지금은 이것보다 log4j를 더 많이 씀. appender를 통해 로그를 출력하는 위치 제어 가능(console appender, file appender쓰면 콘솔창같이 기록 가능)
	jdbc appender통해 DB에 기록도 가능
	
	4. context parameter 확보 : <%=application.getInitParameter("contentFolder") %>
	web.xml에서 context-param.(어플리케이션 전체에서 사용할 수 있는 파라미터).
	
	5. **제일 중요** web resource 확보 : getRealPath, getResource, getResourceAsStream.
	<%

		//06번에 파일 복사해보자
		//외부 라이브러리 써서 입출력 작업 해보자
		
		String url = "images/cute4.JPG";
		String targetUrl = "/06";
		String path = application.getRealPath(url);
		String targetPath = application.getRealPath(targetUrl);
		out.println(path);
		
		File file = new File(path);
		File targetFile = new File(targetPath, file.getName());
		
		try(
			FileInputStream input = new FileInputStream(file);
			FileOutputStream output = new FileOutputStream(targetFile);
		){
			IOUtils.copy(input, output);
		}
		out.println(targetFile.exists());
		
		
	%>
	<img src="<%=request.getContextPath()+targetUrl+"/"+targetFile.getName()%>" />
</pre>
</body>
</html>
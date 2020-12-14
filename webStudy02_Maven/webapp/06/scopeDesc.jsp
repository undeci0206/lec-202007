<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/scopeDesc.jsp</title>
</head>
<body>
<h4>SCOPE(영역)</h4>
<pre>
	: 속성(attribute) 데이터를 공유하기 위해 정의된 4가지 저장영역
	setAttribute(name, value), getAttribute(name), removeAttribute(name)(세션과 어플리케이션은 자동으로 지워지지 않는다.)
	1. page Scope : pageContext가 관리하는 맵, 한 페이지 내에서만 공유되는 영역.
	2. request Scope : request가 관리하는 Map&lt;String,Object&gt;, request와 생명주기 동일.
	3. session Scope : session이 관리하는 Map&lt;String,Object&gt;, session과 생명주기 동일.
	4. application Scope : servletContext가 관리하는 Map&lt;String,Object&gt;, servletContext와 생명주기 동일.
</pre>
</body>
</html>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Action Tag</h4>
<pre>
	Custom Tag 사용방법 ex) &lt;prefix:tagName attributes &gt;
	1. custom tag 라이브러리를 빌드패스에 추가(jar파일)
	2. taglib지시자로 라이브러리를 로딩(prefix 결정).
	3. &lt;prefix:tagName attributes &gt;와 같이 사용
	액션 태그 : jsp 스펙에 따라 기본 제공되는 커스텀 태그, prefix:jsp
<%-- 	<jsp:forward page="/02/userAgent.jsp"></jsp:forward> --%>
<%-- 	<jsp:include page="/02/userAgent.jsp" /> --%>﻿<!-- 기본으로 제공해주는 커스텀태그이기 때문에 1,2번 과정 생략 -->
	일반 태그는 클라이언트 사이드 모듈. 
	prefix구조 서버사이드. 응답데이터에는 커스텀 태그 포함되지 않음. -> 클라이언트에게 소스 보이지 않음
	
	** include 방식의 종류(시점, 대상)
		1. 정적 내포 : jsp소스를 파싱하는 단계에서 소스 자체를 내포하는 구조
 			1)page 하나를 대상으로 한 내포 : include 지시자
 			2)어플리케이션 자체를 대상으로 한 내포 : web.xml의 jsp-config 활용
		2. 동적 내포 : 요청에 대한 처리가 이루어질때 runtime에 실행 결과를 내포하는 구조.
			1)RequestDispatcher.inclue
			2)PageContext.include
			3)include 액션 태그
		<%
		//	pageContext.include("/02/userAgent.jsp");
		//	out.println("===============>"+now.getTime());
		%>
		<%-- 
		1. 스크립틀릿 기호 생성하지 않고도 Date객체 생성
		2. 해당 객체를 scope에 넣어줌 
		3. scope를 통해 다른 scope의 영역을 설정할 수 있음
		4. 무조건 객체 생성하진 않고 scope를 뒤져서 객체가 있으면 그걸 가져오고 없으면 새로 생성함! 똑똑하군.. today가 null일 수가 없기 때문에
		NullPointException을 피해갈 수 있다.
		--%>
		<jsp:useBean id="today" class="java.util.Date" scope="request"></jsp:useBean>
		<%-- 아래 스크립틀릿 안 내용을 위 한 줄로 해결 가능하다. --%>
		<%--
			Date today = (Date)request.getAttribute("today");
			if(today==null){
				today = new Date();
				request.setAttribute("today", today);
			}
			today.setTime(0);
		--%> 
		 
		<%-- useBean을 활용해 생성된 property를 꺼낼 수 있음.. 자바 코드 짜지 않아도 property 꺼내올 수 있음 --%>
		<jsp:getProperty property="time" name="today"/>
		<jsp:setProperty property="time" name="today" value="0"/>
		<jsp:getProperty property="time" name="today"/>
		<%-- <jsp:setProperty property="year" name="today" param="year"/>
		<jsp:setProperty property="month" name="today" param="month"/>
		<jsp:setProperty property="date" name="today" param="date"/> --%>
		<jsp:setProperty property="*" name="today"/>
		<%=today %>
		<%=request.getAttribute("today") %>
		
</pre>
</body>
</html>
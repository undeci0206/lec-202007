<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/implicitObject.jsp</title>
</head>
<body>
<h4>기본 객체(Implicit Object)</h4>
<pre>
1. request(HttpServletRequest)
2. response(HttpServletResponse)
3. out(JspWriter//2바이트 단위로 데이터 사용) : 응답 데이터 기록이나 응답 버퍼 제어 및 관리에 사용
4. <a href="sessionDesc.jsp">session(HttpSession)</a>
5. <a href="servletContextDesc.jsp">application(ServletContext)</a>
6. page(Object)==this 
7. config(ServletConfig) // init에서 사용. 초기화 callback.서블릿 대상으로 하는 파라미터 꺼낼 때
//ServletConfig : 서블릿을 등록할 때 모든 옵션들에 대한 정보를 가지고 있다. web.xml태그로 만들어진다.
//jsp에도 이들이 들어있는데(jsp가 곧 서블릿이기 때문) jsp는 등록이나 mapping하지않기 때문에 우리는 이 사실을 잘 모름... 
8. exception(Throwable) : 일반페이지에서는 비활성화(page 지시자의 isErrorPage="true"로 활성화시킴)
//에러를 처리하기 위한 목적인 페이지에서만 사용된다.
현재 페이지가 에러 처리 목적이라는 걸 알려줘야 함. 
맨 윗줄 page에서 errorPage="" 에러를 다른곳에서 처리
isErrorPage="true" 이면 <%  %> 으로 찾을 때 errorPage 나옴
9. pageContext(*****). 제일 중요한 객체 : 모든 기본 객체 중 가장 먼저 생성됨. 나머지 기본 객체에 대한 getter를 가짐. 
	<%=pageContext.getRequest() == request %>
	<%=((HttpServletRequest)pageContext.getRequest()).getContextPath() %> //casting필요
	${pageContext.request.contextPath }
	﻿pageContext?
	1) Scope 제어 <% pageContext.setAttribute("test", "value", PageContext.REQUEST_SCOPE); %>
				<%=request.getAttribute("test") %> //꺼낼 때. 여기서 value가 출력된다면 하나의 scope를 가지고 노는 것
	2) 흐름 제어 : 여기서는 dispatch 방식. redirect방식은 포함하지 않는다.
	<% 
		//pageContext.include("/06/sessionDesc.jsp");
		request.getRequestDispatcher("/06/sessionDesc.jsp").include(request, response);
	%>
	3)에러 데이터 확보 : exception이 활성화되는 페이지에서 활용한다.
	
	이게 어디에서 보이나 확인!!
</pre>
</body>
</html>
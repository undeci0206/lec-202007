<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="1kb" autoFlush="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/JSPStandard.jsp</title>
</head>
<body>
<h4>JSP (Java Server Page)</h4>
<pre>
	: jsp 소스 표준 구성 요소 (스펙)
	1. 정적 텍스트 요소
	2. 스크립트(동적 구성 요소)
		1) 스크립틀릿(scriptlet) : <% //java code %>
		2) 표현식(expression) : <%="출력데이터" %> <!-- //리터럴이나 변수 들어갈 수 있다. -->
		3) 지시자(Directive) : <%--@ 설정정보 --%>
		4) 선언부(Declaration) : <%! //전역 멤버 %> <!-- 지역코드화가 된다는 스트립틀릿 특성 때문. 
												WAS에서 scope를 쓰는 이유? 전역으로 빼도 인스턴스 책임은 톰캣에게 있기 때문에.. scope쓴다.-->
		5) 주석(Comment) : <%-- 주석(서버사이드 주석) --%>
			<!-- 주석1 . 클라이언트 사이드 주석. 응답데이터가 나가는 시점에 주석으로 나간다-->
			<%-- 주석2 . 서버사이드 주석이기 때문에 응답데이터에 포함되지 않음. 실행해서 소스 보기 하면 안 보임--%>
			
			<%-- 
			**개발자가 개발할 때 어떤 주석을 써야할까? 서버사이드 주석.
			이유? 
			1. 개발과 관련된 정보가 노출될 수 있다.
			2. 클라이언트 주석은 응답데이터에 포함. 주석이 많으면 응답 데이터가 커진다. size늘리지 않는게 좋다.
			--%>
			client side : HTML JavaScript css
			server side : java jsp
	3. 기본객체(내장객체)
	4. 액션 태그(jsp action tag)
	5. 표현 언어(Expression Language, EL)
	6. JSTL(tag library)
	
	<%
		for(int i = 1; i<600; i++){
			out.println(i+"번째 출력");
			if(i==70){
				throw new RuntimeException("강제 발생 예외");
			}
		}
	
	%>
	
	
	
</pre>
</body>
</html>
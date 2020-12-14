<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/sessionDesc.jsp</title>
</head>
<body>
<h4> HttpSession </h4>
<pre>
	세션 이란? (웹에서는 기간의 의미로 많이 사용된다. 웹의 http는 connectless이기 때문)
		통로 : 두 피어사이에 유일하게 개방된 연결
		기간 : *한 클라이언트가 하나의 브라우저를 이용*해서 어플리케이션 사용 시작 이벤트를 발생시킨 후,
			 종료 이벤트를 발생시킬 때까지의 기간.
	세션의 생명주기
		시작 이벤트 : 최초의 요청이 발생하면 세션 시작
			session id : <%=session.getId() %> //세션이 하나 만들어질 때 식별자로 부여된 ID값
					<%=new Date(session.getCreationTime()) %> //최초 요청이 언제 들어왔는지 확인 가능. long type return이므로
					<%=new Date(session.getLastAccessedTime()) %> //마지막 요청시간
					<%=session.getMaxInactiveInterval() %> //초단위. 아무것도 안하고 있어도 되는 최장시간 =timeOut시간
					<%
						session.setMaxInactiveInterval(3*60);
					%> //초단위
					<%=session.getMaxInactiveInterval() %>
					data객체로 묶어주면 날짜 데이터 형식으로 출력 가능.
		종료 이벤트 : 
			1) invalidate(명확한 로그아웃)
			2) timeout 이내에 새로운 요청이 발생하지 않으면 세션 만료.
			(한 세션에서 마지막 리퀘스트와 다음 새로운 리퀘스트전까지의 갭..단순히 세션이 타임아웃 시간동안만 유지된다는 의미가 아니다!)
			3) 브라우저 종료(무조건 일어나는 것은 아니다.)
			4) 쿠키 삭제(session tracking를 알아야 이해가능)
	세션이 유지되는 구조(session tracking) : 세션 유지를 위한 session id 재전송 방법. 우선순위 쿠키가 제일 높다.
		1) COOKIE : session id 가 쿠키의 형태로 클라이언트에 저장되어 재전송
		//쿠키 : 세션의 유지 여부가 클라이언트에 달려있다->반쪽짜리이다,,,
		2) URL : 세션 파라미터 재전송 구조<a href ="sessionDesc.jsp;jsessionid=<%=session.getId()%>">세션 유지</a>
		//jsessionid를 주고 재작성하게 되면..세션 유지되지만..!
		but 이방법의 문제점->모든 요청이 재작성을 활용해서 작성되어야 함
		request line에 있으므로 세션아이디가 보안에 취약해진다. 보안취약문제를 해결하는법? 약속된 당사자만 읽을 수 있게 바꾸기.(양방향 암호화 기법. 복호화 가능하게)
		양방향 사용했을 때 나,톰캣만 해석가능해야 함. 나와 톰캣은 똑같은 프로토콜(https!!!)을 사용해야 한다.->secure socket을 이용해 데이터주고받음
		secure socket의 문제점 : 나,톰캣만 해석가능할 수 있는 key교환이라는 과정 필요->100% 안전한 암호화 기법은 없으니 항상 조심하기
		3) SSL : secure socket layer 를 이용해 암호화된 재전송 구조
</pre>
<%=application.hashCode() %>
</body>
</html>
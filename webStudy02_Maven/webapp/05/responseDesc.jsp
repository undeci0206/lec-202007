<%@ page language="java" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/responseDesc.js[]</title>
</head>
<body>
<h4>HttpServletResponse</h4>
<pre>
	: 서버에서 클라이언트로 전송되는 응답과 관련된 모든 정보 캡슐화
	Http 에서 response 패키징 방식
	1. response Line : Status Code, Protocol/ver 
		Status code : 처리 결과를 지칭하는 숫자 코드(sendError, sendRedirect)
		1) 100~ : ING....(WebSocket) 
			100쓰려면 HttpProtocol이 갖는 환경을 이해해야함(Stateless, connectless(하나의 요청이 들어오고 응답이 나가면 연결끊김))
			->문제점 : 서버쪽에서 계속 갱신되는 데이터가 있을때 알려줄 수 있는 방법이 없다.(연결이 모두 끊겼기 때문에)
			웹소켓은 연결을 억지로 끊지 않는 이상 끊기지 않음!(여전히 통로가 유지된다.)
		2) 200~ : OK
		3) 300~ : 이번 시간에 볼 가장 중요한 상태코드!! 공통점 : 클라이언트의 추가 액션을 요구한다.
			304(Not Modified) 브라우저는 정적 데이터를 캐시로 저장해두는 특성이 있음.(캐싱)
			이미지를 불러와서 새로고침 계속 하면 304
			네가 가져온 자원은 캐시에 저장된 자원이니 캐시영역을 한번 다시 찾아봐라라는 말..
			브라우저는 자신이 가진 캐시 영역을 한번 더 찾아봐야 하는 추가 액션이 필요하다. (클라이언트의 추가 액션을 요구한다.) 
			302/307(Moved) : 상헌이 자리...자리바꿔서 그 자리에 내가 앉았음(나: 1. 전 상헌이가 아닙니다.. 2. 상헌이는 맨 뒷줄로 이사갔어여) Location 헤더와 병행 사용-> 위치 알려줘야 함
		4) 400~ : 클라이언트 오류로 실패. 클라이언트의 잘못이기 때문에 오류 내용을 구체적으로 보여줘야 클라이언트가 수정해서 다시 요청해 줌
			404(Not Found), 400(Bad request)
			401(UnAuthorized, 허가받지 않은 사용자가 접속할 때), 403(Forbidden)
			405(Method Not Allowed) ex.doXXX메서드를 오버라이딩할때 super빼지 않은 경우 발생
			415(Media Not Supported) ex.내 서버사이드에 특정 데이터의 형식(json, xml등..)으로 내보내는 마샬러가 없을 때
		5) 500~ : 서버 오류로 실패(500). 서버 쪽 정보를 모두 보여줄 수 없기 때문에 500으로 보통 보여줌
	2. response Header : metadata header
		Content-Type,
		<a href="cacheControl.jsp">Cache-Control,</a> 
		<a href="refresh.jsp">Refresh,</a>
		<a href="flowControl.jsp">Location</a> 
		Location
		<%
			//response.setHeader("Content-Type", "text/plain;charset=UTF-8");
			//위 코드보다 안정적임(오타 날 확률 적음)
			response.setContentType("text/plain;charset=UTF-8");
		%>
	3. response Body : message body, content body
</pre>
<img src="<%=request.getContextPath() %>/images/cute4.JPG" />
</body>
</html>
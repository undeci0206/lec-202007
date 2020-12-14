<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flowControl.jsp</title>
</head>
<body>
<h4>Flow Control</h4>
<pre>
	HTTP : Connectless, Stateless
	1. Dispatch : RequestDispatcher를 이용해서 서버 내에서 이동. 공통점 : status를 유지한다. 
	(중간에서 응답데이터가 나가지 않음. 요청정보가 사용하지 않는다. 출발지 데이터를 도착지까지 가져간다.)
	클라이언트는 B에 대한 정보를 모른다. 모든 정보가 A에서 나온 줄 앎(서버 안에서만 이동을 했기 때문에..)
	페이지 랜더링 후 사용자가 새로고침을 하면? 새로운 요청은 A로 넘어감.
	ex) 회원가입 양식 작성 시 아이디 중복. 가입양식 페이지로 다시 넘겨줘야 할때  request에 저장되어 있으므로 클라이언트는 이전 정보를 다시 입력할 필요X
		1) forward : 최종 도착지에서 완전한 UI가 전송되는 구조. ex)model2구조. 컨트롤러가 뷰단으로 이동할 때 / validation 문제가 생길때 
		2) include : 출발지와 도착지에서 만들어진 모든 UI가 전송되는 구조. ex) 페이지 모듈화. 각각의 jsp를 한페이지에 우겨넣을때
	2. Redirect : 최초 요청이 전송되고, 일부 처리가 이루어진 후, body가 없고 302/307(location) 메타 데이터를 가진 응답이 클라이언트로 전송.
				  브라우저는 location 방향으로 새로운 요청을 전송하고, 최종 응답이 최종 도착지에서 전송됨.
				  클라이언트는 B에 대한 정보를 안다.
				  페이지 랜더링 후 사용자가 새로고침을 하면? 새로운 요청은 B로 넘어감
				  ex) 회원가입하기 위해 양식 적고 가입 완료 후 로그인페이지로 보낼 때
				  
				  
</pre>
</body>
</html>
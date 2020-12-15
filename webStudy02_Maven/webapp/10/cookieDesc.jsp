<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/cookieDesc.jsp</title>
</head>
<body>
<h4>Cookie</h4>
<pre>
	: Http의 stateless의 단점을 보완하기 최소한의 상태정보를 저장하는 개념.
	상태정보를 서버측에 저장 : session
	상태정보를 클라이언트측에 저장 : cookie
	
	Cookie 사용 단계
	**쿠키를 사용해서는 문자열만 저장 가능하다. 인식할 수 있는 데이터 표현 방식으로 변환(인코딩) 필요. 
	-> 첫 번째로 쓸 수 있는 것이 Base64.
	[클라이언트 측] 
	<%
//	1. 쿠키생성
	Cookie sampleCookie = new Cookie("sample", "sampleValue");
//	2. response에 쿠키를 실어 전송(header-문자 형태로 전송. 길이 제한. 
	response.addCookie(sampleCookie);
	%>
	
	클라이언트의 저장공간(브라우저가 가지고 있는 쿠키저장소) 빌려쓰는 것이므로 쿠키의 정책 고려해야 함)
	3. 브라우저가 자기 쿠키 저장소에 저장
	4. 다음 번 요청시에 쿠키를 서버로 재전송(header)
	
	[서버 측]
	<% 
//	5. request를 통해 전송된 쿠키로 상태 복원
	String searched = null;
	Cookie[] cookies = request.getCookies(); //최대 20개까지 가능하므로 배열 형태로 돌려줌
	if(cookies!=null){
		for(Cookie tmp : cookies){
			if("korean".equals(tmp.getName())){
				searched = URLDecoder.decode(tmp.getValue(), "UTF-8");
			}
		}
	}
	%>
	복원된 쿠키 값 : <%=searched %>
	
	쿠키의 속성
	1. name (required) : 영문자/숫자/_
	2. value (required) : 모든 문자 포함될 수 있으나 인코딩에 주의하기!
					 	  특수문자는 URLEncoding으로 전송
	3. path(optional) : path로 설정된 경로 이하로 발생하는 요청에만 쿠키를 재전송. 
						생략 시 쿠키의 생성 경로가 반영됨.
	4. domain/host(optional) : 쿠키의 재전송 여부 결정에 사용, 생략 시 쿠키 생성 도메인 반영.		
		domain level 구조  **하위가 상위에 종속되어 있다.
		3 level : www.naver.com (GTLD. GlobalTopLevelDomain) www:가장 하위 com:가장 상위
		4 level : www.naver.co.kr (NTLD. NationalTLD) kr:나라 제한. 가장 상위
		.naver.com / .naver.co.kr : 도메인 설정 시 호스트명을 생략한 패턴을 사용하면, 
									해당 도메인의 모든 호스트로 쿠키 재전송
	5. maxAge(expire, optional) : 쿠키의 저장 기간을 제한. 생략 시 세션이 만료될 때 제거됨.
		ex) 0 : 쿠키 삭제에 사용. (**value 이외의 모든 속성이 동일한 경우 삭제)
		ex) -1 : 브라우저 종료 시 쿠키 삭제
		
	<%
		String koreanValue = URLEncoder.encode("한글값", "UTF-8"); //조합형 한글 쓰겠다
		Cookie koreanCookie = new Cookie("korean", koreanValue);
		response.addCookie(koreanCookie);
		
		Cookie allPathCookie = new Cookie("allPath", "All~Path");
		//브라우저(클라이언트 측)에서 사용하므로 request.getContextPath()
		allPathCookie.setPath(request.getContextPath());
		//allPathCookie.setPath("/"); //cookie를 통해 cross context가능해짐 (context들 사이에서 경로를 주고받을 수 있다.)
		response.addCookie(allPathCookie);
		
		Cookie allDomainCookie = new Cookie("allDomain", "All~Domain~");
		//도메인의 레벨 구조를 패턴 매칭으로 설정 가능 . : 생략했으므로 모든 도메인 가능
		//allDomainCookie.setDomain(".chy.com"); //dummyVersion7d에서 테스트
		response.addCookie(allDomainCookie);
		
		Cookie longLiveCookie = new Cookie("longLive", "Long~Live~");
		longLiveCookie.setMaxAge(0); 
//		longLiveCookie.setPath(request.getContextPath());
		response.addCookie(longLiveCookie);
		
		Cookie others = new Cookie("others", "Other~~");
		others.setHttpOnly(true);
		others.setSecure(true);
		response.addCookie(others);
		
	%>				 	  
	<a href="viewCookie.jsp">동일경로에서 쿠키 확인</a>	 	  
	<a href="<%=request.getContextPath() %>/09/viewCookie.jsp">다른경로에서 쿠키 확인</a>	 	  
</pre>
</body>
</html>
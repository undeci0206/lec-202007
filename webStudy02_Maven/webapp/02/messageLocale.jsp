<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.flag{
		width: 100px;
		height: 100px;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<input type="image" src="<%=request.getContextPath() %>/images/korean.jpg" 
	class="flag" id="KO"
/>
<input type="image" src="<%=request.getContextPath() %>/images/us.png" 
	class="flag" id="EN"
/>
<div id="resultArea">

</div>
<pre>
<%-- <%=Locale.ENGLISH.toLanguageTag() %>
accept-language : <%=acceptLanguage %>
<%=message %> --%>
</pre>
<script type="text/javascript">
	//자바스크립트가 인터프리팅 방식 언어라 아래에 적는다.
	// location : 현재 페이지 출처에 대한 정보를 가지고 있는 기본 객체 -> href라는 properties가 가지고 있다.
	//?는 쿼리스트링으로 파라미터 넘기겠다는 말.. 
	let resultArea = $("#resultArea");
	$(".flag").on("click", function(event){
		let language = $(this).prop("id");
<%-- 		location.href="<%=request.getContextPath()%>/02/getMessage.jsp?lang="+language; --%>
		$.ajax({
			//요청을 어떤 방식으로 넘길지 결정
			url:"<%=request.getContextPath()%>/02/getMessage.do",
			data: { //파라미터
				//"lang="+language로 직접 쿼리스트링을 쓰는 것보다는 아래와 같이 씀
				lang:language
			},
			method:"get", //get은 기본메서드이므로 생략 가능
			
			//돌아오는 응답 데이터 결정
			dataType:"xml", // Accept : application/json dataType 설정 : accept라는 헤더에 마임을 생성하기 위함..
			//Content-Type : 
			
			//행위에 대한 정보
			success:function(resp){ //resp type: 여기서는 text. dataType에 따라 달라짐
				//jquery context 호출 : callback함수 호출. 응답데이터를 가지고 놀려고..	
				//$("#resultArea").html(resp.message);
				let xml = $(resp);
				let message = xml.find("message");
				console.log(xml);
				resultArea.html(message.html());
			},
			error:function(xhr){ //xhr : xhr은 요청, 응답에 관한 정보를 모두 담고 있음(xml http request). 기존 api객체를 통해 정보 확인 가능
				console.log(xhr.status); //응답 상태 코드	
			}
		});
	});
</script>
</body>
</html>
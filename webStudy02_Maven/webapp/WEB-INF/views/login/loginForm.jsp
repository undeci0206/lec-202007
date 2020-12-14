<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript">
	$(function(){
		$("#loginForm").on("submit", function(){
			let valid = true;
			//validation 수행->validation대상이 되는 요소들 먼저 가져와야 함
			$(":input[name]").each(function(index, element){ // 모든 입력 태그 중 name이라는 속성 가지는 입력태그 // : -> 모든이라는 뜻 .
				//required 여부(boolean타입 함수 꺼낼때 prop)
				//attr : 문자열로 관리하는 속성에 접근할 때. required에는 사용하지 않음
					if($(this).prop("required")){//required를 가지고 있다면
						let value = $(this).val();
						if(!value || value.trim().length==0){ //스페이스바 세번띄우고 로그인시도하거나.. 이런거 방지하기 위해서
							valid = valid && false; //마지막으로 true가 만들어지더라도 그전이 false면 실패해야 하므로	
						}
						//패턴 확인. attr사용(문자열이므로) //원래 데이터의 타입을 유지할 때 prop이므로 prop도 상관없음
						let ptrn = $(this).attr("required");
						if(ptrn){
							console.log(ptrn);
							let regex = new RegExp(ptrn);
							valid = valid && regex.test(value); //html5를 지원하지 않는 브라우저에서도 똑같은 방법으로 검증이 가능하다.
						}
					}
				//pattern 여부
			});
			return valid;
		});
	});
</script>
</head>
<body>
<%
	String authMember = (String)session.getAttribute("authMember");
	if(StringUtils.isBlank(authMember)){ //authMember존재할 경우 로그인 페이지 보이면 안된다.
%>
<form id="loginForm" action="<%=request.getContextPath() %>/login/loginProcess.do" method="post">
	<ul>
		<li>
			<%
				String mem_id = (String)session.getAttribute("mem_id");
				session.removeAttribute("mem_id"); //flashAttribute. 한번 쓰이고 지워짐
			%>
			<input type="text" name="mem_id" value="<%=Objects.toString(mem_id, "")%>" required/>
		</li>
		<li>
			<input type="text" name="mem_pass" required />
			<input type="submit" value="로그인" />
		</li>
	</ul>
	
</form>
<%
}
%>
</body>
</html>

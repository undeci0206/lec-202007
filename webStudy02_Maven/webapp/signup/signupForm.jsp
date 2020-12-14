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
		$("#signupForm").on("submit", function(){
			let valid = true;
			$("input[name]").each(function(index, element){ 
					if($(this).prop("required")){
						let value = $(this).val();
						if(!value || value.trim().length==0){ 
							valid = valid && false
						}
						let ptrn = $(this).attr("required");
						if(ptrn){
							console.log(ptrn);
							let regex = new RegExp(ptrn);
							valid = valid && regex.test(value);
						}
					}
			});
			return valid;
		});
	});
</script>
</head>
<body>

<form id="signupForm" action="<%=request.getContextPath() %>/signup/signupProcess.do" method="post">
	<ul>
		<li>
			ID : <input type="text" name="mem_id" required pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$"/>
		</li>
		<li>
			비밀번호 : <input type="text" name="mem_pass" required pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$"/>
		</li>
		<li>
			email : <input type="text" name="mem_mail" required pattern="/^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$"/>
		</li>
			<input type="submit" value="회원가입" />
	</ul>
</form>
</body>
</html>











<%@page import="kr.or.ddit.enumpkg.OperateType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/calculateForm.jsp</title>
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

	//2020.11.27. calculateForm 비동기로 만들기!
	$(function(){
		let resultArea = $("#resultArea");
		let now = $("#now");
		$(document).ajaxError(function(xhr){
			console.log(xhr);
		})
		//$("button").on("submit", function(){ 안됨 (submit은 버튼에 대해 발생하는 이벤트가 아니다.)
		$("form").on("submit", function(){
			event.preventDefault();
			console.log(this);
			console.log($(this));
			let url = this.action? this.action : "" ;
			let data = $(this).serialize(); //쿼리스트링 만들어 줌
			let method = this.method ? this.method : "get"; //보통의 요청은 get
			let dataType = "json";
			let success = function(resp){//dataType이 json 이라면 resp에는 자바스크립트 객체가 들어가있다(역직렬화후 언마샬링 거친 이후이다.)
				//console.log(resp);
				resultArea.html(resp.data);
				now.html(resp.now);
			}
			console.log(data);
			let options = {
				url : url
				, data : data
				, method : method
				, dataType : dataType
				, success : success
			}
			$.ajax(options); //객체 미리 만들고 properties설정
			return false; 
		});
	});
</script>
</head>
<body>
<form action = "<%=request.getContextPath() %>/03/calculate.do" method ="post" >
	<input type="number" name="leftOp" required />
	<select name = "operator" required>
		<option value="">연산자</option>
		<%
		for( OperateType tmp : OperateType.values()){
			%>
			<option value="<%=tmp.name()%>"><%=tmp.getSign() %></option>
			<%
		}
		%>
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULPLY">*</option>
		<option value="DIVIDE">/</option>
	</select>	
	<input type="number" name="rightOp" required/>
	<button type="submit">=</button>
	<span id="now"></span>
	<span id = "resultArea"></span>
</form>
</body>
</html>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<style type="text/css">
		body{
			background-color: pink;
		}
	</style>
	<script type="text/javascript" src = "https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script type="text/javascript">
		/*function changeCallback(event){
		console.log(event);
			console.log(event.target.value);
			location.href="/webStudy01/imageView.do?image="+event.target.value; //request - line, header, body
		}*/		
		$(function(){
			$("select").on("change", function(event){ //callback 메서드
				let imageName = $(this).val();
				//<img src="../imageView.do?image="/>
				("#imageArea").html($("<img>").attr("src", "<%=request.getContextPath()%>/imageView.do?image="+imageName));
			});
		});
	</script>
	<body>
	<% 
		String[] imageFiles = (String[]) request.getAttribute("imageFiles");
	%>
		<h4>${title }</h4> <!--$｛｝ : %%와 같음. title이라는 키만 넣어주면 됨  -->
		<h4>${today }</h4>
		<select>
			<%
				for(String image : imageFiles){
					%>
					<option><%=image %></option>
					<% 
				}
			%><!-- jsp에서는 여러 종류의 토큰 동시에 사용할 수 있음  -->
		</select>
		<div id="imageArea">
			
		</div>
	</body>
</html>

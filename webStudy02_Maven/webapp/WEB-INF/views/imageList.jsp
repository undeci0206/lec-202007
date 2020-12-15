<%@page import="java.util.Arrays"%>
<%@page import="java.net.CookieStore"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
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
				let imageNames = $(this).val();
				let imgTags = [];
				$(imageNames).each(function(index,imageName){
					imgTags.push(
						$("<img>").attr("src", "<%=request.getContextPath()%>/imageView.do?image="+imageName)
					);
				});
				//<img src="../imageView.do?image="/>
				$("#imageArea").html(imgTags);
				
				if(imgTags.length>0){
					let data = JSON.stringify(imageNames);
					console.log(data);
					$.ajax({
						url : "<%=request.getContextPath()%>/imageView.do",
						data : data, //imageNames로 배열 보낼 수는 없음. json으로 변환
						//넘기는 데이터(요청)의 마임 설정! 응답데이터 아님.request header중 contentType설정한 것
						method : "post", //이미지 정보를 한번에 보낼 때 post활용
						contentType:"application/json;charset=UTF-8", 
					});
				}
			});
		});
	</script>
	<body>
	
	
	<% 
		String[] imageFiles = (String[]) request.getAttribute("imageFiles");
		String[] array = (String[])request.getAttribute("array"); //마지막으로 확인한 이미지 목록들
		if(array==null) array = new String[0];
	%>
		<h4>${title }</h4> <!--$｛｝ : %%와 같음. title이라는 키만 넣어주면 됨  -->
		<h4>${today }</h4>
		<select size="10" multiple="multiple">
			<%
				Arrays.sort(array);
				for(String image : imageFiles){
					String selected = Arrays.binarySearch(array, image)>=0 ? "selected":""; //정렬, 검색 결과로 이진 검색(int로 돌아옴)
					%>
					<option <%=selected %>><%=image %></option>
					<% 
				}
			%><!-- jsp에서는 여러 종류의 토큰 동시에 사용할 수 있음  -->
		</select>
		<div id="imageArea">
			<%
			for(String image : array){
				%>
				<img src = "<%=request.getContextPath() %>/imageView.do?image=<%=image%>"/>
				<%
			}
			%>
		</div>
	</body>
</html>

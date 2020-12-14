<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08.jdbcDesc.jsp</title>
<jsp:include page="/includee/preScript.jsp" />

</head>
<body>
	<h4>JDBC(Java Database Connectivity)</h4>
<jsp:useBean id="paramVO" class="kr.or.ddit.vo.DataBasePropertyVO" />
<jsp:setProperty property="*" name="paramVO"/>
<form id="searchForm">
	<input type="text" name="property_name" value='<jsp:getProperty property="property_name" name="paramVO"/>'/>
	<input type="text" name="property_value" value='<jsp:getProperty property="property_value" name="paramVO"/>'/>
	<input type="text" name="description" value='<jsp:getProperty property="description" name="paramVO"/>'/>
	<input type="submit" value="검색" />
</form>
<pre>
1. driver를 빌드패스에 추가
2. driver loading
<jsp:useBean id="dbProps" class="java.util.ArrayList" scope="request" />

7. 자원 해제
</pre>
<table>
	<tbody id="listBody">
		<%
			for(Object propVO : dbProps){
				%>
				<tr>
					<td><%=((DataBasePropertyVO)propVO).getProperty_name() %></td>
					<td><%=((DataBasePropertyVO)propVO).getProperty_value() %></td>
					<td><%=((DataBasePropertyVO)propVO).getDescription() %></td>
				</tr>
				<%
		    }
		
		
		%>
	</tbody>	
</table>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/asyncForm.js" > </script>
<script type="text/javascript">
	let listBody = $("#listBody");
	$("#searchForm").asyncForm({
		success:function(resp){
			listBody.empty(); //listBody갱신
			let trTags = [];
			if(resp.dbProps.length==0){ //검색결과 없을 시 메세지
				trTags.push(
					$("<tr>").html(
						$("<td>").text("검색 결과 없음.")
								 .attr("colspan", "3")
					)
				);
				
			}else{
				$.each(resp.dbProps, function(index, propVO){
					trTags.push(
						$("<tr>").append(
							$("<td>").text(propVO.property_name)
							,$("<td>").text(propVO.property_value)
							,$("<td>").text(propVO.description)
						)
					);
				});
			}	
			listBody.html(trTags);
		}
	});
</script>
</body>
</html>
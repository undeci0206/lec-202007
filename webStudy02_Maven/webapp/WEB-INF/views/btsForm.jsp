<%@page import="java.util.Map.Entry"%>
<%@page import="kr.or.ddit.vo.BtsVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src = "https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	
</script>
	<% 
		Map<String, BtsVO> btsDB = (Map)application.getAttribute("btsDB");
		BtsVO selected = (BtsVO) session.getAttribute("selected");
	%>
<form id="btsForm" action ="?service=BTS" method="post">
	<select name="bts" onchange="$('#btsForm').submit()" required>
		<option value="">멤버선택</option>
		<%
			for(Entry<String,BtsVO> entry : btsDB.entrySet()){
				BtsVO btsVO = entry.getValue(); //멤버 한명에 대한 VO
				String selectedAttr = btsVO.equals(selected)?"selected":"";
				%>
					<option value="<%=btsVO.getCode()%>" <%=selectedAttr %>><%=btsVO.getName() %></option>
				<%
			}
		%>
	</select>
</form>


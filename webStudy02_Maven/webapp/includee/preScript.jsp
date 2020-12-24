<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel = "stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>

<script src="<%=request.getContextPath()%>/js/jquery.form.min.js"></script>

<script type="text/javascript">
	$.getContextPath = function(){
		return "<%=request.getContextPath() %>";
	}
</script>
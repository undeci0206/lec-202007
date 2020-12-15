<%@page import="kr.or.ddit.enumpkg.FileWrapper"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath() %>/js/fancytree/jquery.fancytree-all-deps.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/fancytree/skin-win8/ui.fancytree.min.css" />
<script src="<%=request.getContextPath() %>/js/fancytree/jquery.fancytree.min.js"></script>
<script>
	$(function(){
		$("#tree1").fancytree();
	});
</script>
<h4>탐색기</h4>
<div id ="tree1">
	<ul id="explorerUL">
		<%
			List<FileWrapper> list = (List)request.getAttribute("children");
			for(FileWrapper tmp : list){
				%>
				<li id = "<%=tmp.getRelativePath() %>" class="<%=tmp.getClzName()%>"><%=tmp.getName() %></li>
				<%
			}
		%>
	</ul>
</div>
<script type="text/javascript">
	$("#explorerUL").on("click", ".folder", function(){
		let serverSidePath = $(this).prop("id"); //어떤 폴더를 클릭했는지
		location.href="?base="+serverSidePath;
	});
</script>
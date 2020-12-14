<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.enumpkg.ServiceKind"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<form id="menuForm" action="<%=request.getContextPath() %>/module.do">
<input type="hidden" name="service" />
</form>  
      <div class="position-sticky pt-3 m-3" >
        <ul class="nav flex-column menuUl">
              <%
				ServiceKind[] kinds = ServiceKind.values();
				for(ServiceKind service : kinds){
					boolean model2 = StringUtils.isNotBlank(service.getMenu().getMenuURI());
			  %>
        	  <li class="nav-item">
              
               <% 
				if(model2){ //바로 요청이 넘어가야 함
					%>
					<a class="nav-link active" aria-current="page" href="<%=request.getContextPath() %><%=service.getMenu().getMenuURI()%>">
					<%=service.name() %>
					</a>
					<%
					
				}else{ //form타고 요청이 넘어가야 함
					%>
					<a class="nav-link active model1" aria-current="page" data-service="<%=service.name() %>">
					<%=service.getMenu().getMenuText() %>
					</a>					
					<%
				}
				%>
         	   </li>
			<%
				}
			%>
        </ul>
      </div>

	<!-- 
	이렇게 하드코딩 할 필요 없다.
	<li><a data-service="GUGUDAN">구구단</a></li>
	<li><a data-service="LOCALEMESSAGE">로케일메시지</a></li>
	<li><a data-service="CALCULATOR">사칙연산기</a></li>
	<li><a data-service="CALENDER">달력</a></li>
	<li><a data-service="IMPLICITOBJECT">기본객체</a></li>
	<li><a data-service="BTS">BTS</a></li> 
	-->

<script type="text/javascript">
	let menuForm = $("#menuForm");
	//이렇게 했을 때 장점 : menuUl의 내용이 동적으로 바뀌게 되면 a가 없어질 수도 있는데, 
	//$(".menuUl a")로 선택 시 동적으로 생성된 것에는 이벤트가 적용되지 않는다. 그러나 이 방식으로 하면 문제없음
	//**동적으로 변경되는 element에는 handler를 붙이지 않기!
	$(function(){ 
		$(".menuUl").on("click", "a.model1", function(event){
			event.preventDefault();
			menuForm.find("[name='service']").val($(this).data("service"));
			menuForm.submit();
			return false;
		});
	});
</script>
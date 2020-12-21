<%@page import="java.util.Objects"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="pagingVO" class ="kr.or.ddit.vo.PagingVO" scope="request"/>
<table class="table">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>주소</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
	<%
		List<MemberVO> memberList = pagingVO.getDataList();
		for(Object element : memberList){
			MemberVO member = (MemberVO)element;
		
	%>
		<tr>
			<th><%=member.getMem_id() %></th>
			<th><%=member.getMem_name() %></th>
			<th><%=member.getMem_hp() %></th>
			<th><%=member.getMem_mail() %></th>
			<th><%=member.getMem_add1() %></th>
			<th><%=member.getMem_mileage() %></th>
		</tr>
	<%
		}
	%>
	</tbody>
	<tfoot>
		<tr>
         <td colspan="6">
            <form id="searchForm">
               <input type="hidden" name="page"/>
               <input type="hidden" name="searchType" />
               <input type="hidden" name="searchWord" value="<%=pagingVO.getSearchVO().getSearchWord()%>"/>
            </form>
            <div id="inputUI">
               <select name="searchType">
                  <option value="">전체</option>
                  <option value="name">이름</option>
                  <option value="address">지역</option>
               </select>
               <input type="text" name="searchWord" value="<%=pagingVO.getSearchVO().getSearchWord()%>"/>
               <input type="submit" value="검색" id="searchBtn"/>
            </div>
            <%=pagingVO.getPagingHTML() %>
         </td>
      </tr>
	</tfoot>
</table>
<script type="text/javascript">
	let searchForm = $("#searchForm");
	//이전에 검색했던 상태가 그대로 유지되어 있어야 함
	$("[name='searchType']").val("<%=Objects.toString(pagingVO.getSearchVO().getSearchType(),"")%>"); 
	$(".pagination").on("click", "a", function(event){ //descendent구조
		event.preventDefault();
		let page = $(this).data("page"); //key : 속성 다음에 나오는 글자(data-page)
		searchForm.find("[name='page']").val(page);
		searchForm.submit(); //a태그를 클릭하더라도 searchForm 전해지도록
		return false; //a태그 이벤트 막기
	});
	
	$("#searchBtn").on("click", function(){
		let inputs = $(this).parents("div#inputUI").find(":input[name]");
		$(inputs).each(function(index, input){
			let name = $(this).attr("name");
			let value = $(this).val();
			let hidden = searchForm.find("[name='"+name+"']");
			hidden.val(value); //현재 name값과 같은 hidden태그 
		});
		searchForm.submit();
	});
</script>




































</body>
</html>
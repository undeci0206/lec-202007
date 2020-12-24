<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	#listBody>tr{
		cursor: pointer;
	}
</style>    
<!--    분류명, 상품명, 거래처명으로 검색 기능 제공  -->
<table class="table">
	<thead>
		<tr>
			<th>상품코드</th>
			<th>상품분류명</th>
			<th>상품명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>거래처명</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody id="listBody">
		</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<form id="searchForm">
					<input type="hidden" name="page" />
					<input type="hidden" name="prod_lgu" />
					<input type="hidden" name="prod_buyer" />
					<input type="hidden" name="prod_name" />
				</form>
				<div id="inputUI" class="row justify-content-center mb-3">
					<div class="col-auto">
						<select name="prod_lgu" class="form-control">
							<option value>분류선택</option>
						</select>
					</div>
					<div class="col-auto">
						<select name="prod_buyer" class="form-control">
							<option value>거래처선택</option>
						</select>
					</div>
					<div class="col-auto">
						<input type="text" name="prod_name" class="form-control"/>
					</div>
					<div class="col-auto">
						<input type="button" value="검색"  id="searchBtn" class="btn btn-primary"/>
						<input type="button" value="신규등록"  id="createBtn" class="btn btn-primary"/>
					</div>
				</div>	
				<div id="pagingArea"></div>
			</td>
		</tr>
	</tfoot>
</table>
<div class="modal-dialog modal-fullscreen" id="prodViewModal">
  <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title h4" id="prodViewModalLabel">Full screen modal</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
</div>
<script src="<%=request.getContextPath() %>/js/prod/others.js"></script>
<script type="text/javascript">
	$("#createBtn").on("click", function(){
		location.href="<%=request.getContextPath() %>/prod/prodInsert.do";
	});

	let prod_buyerTag = $("[name='prod_buyer']");
	let prod_lguTag = $("[name='prod_lgu']").getLprodAndBuyer({
		prod_buyerTag : prod_buyerTag
		, prod_lgu:"${pagingVO.searchDetail.prod_lgu}"
		, prod_buyer:"${pagingVO.searchDetail.prod_buyer}"
	});	

	$("#inputUI :input").on("change", function(){
		searchBtn.trigger("click");
	});
	
	
	let listBody = $("#listBody");
	
	listBody.on("click", "tr", function(){
		let prod = $(this).data("prod");
		location.href="<%=request.getContextPath()%>/prod/prodView.do?what="+prod.prod_id;
	});
	
	let pagingArea = $("#pagingArea").on("click", "a" ,function(event){//descendent구조
		event.preventDefault();
		let page = $(this).data("page");//key : 속성 다음에 나오는 글자(data-page)
		searchForm.find("[name='page']").val(page);
		searchForm.submit();//a태그를 클릭하더라도 searchForm 전해지도록
		searchForm.find("[name='page']").val("");
		return false;//a태그 이벤트 막기
	});
	
	let searchForm = $("#searchForm").ajaxForm({
		dataType:"json",
		success:function(resp){
		  	let prodList = resp.pagingVO.dataList;
		  	let pagingHTML = resp.pagingVO.pagingHTML;
		  	let trTags = [];
		  	if(prodList.length>0){
		  		$(prodList).each(function(idx, prod){
		  			trTags.push(
		  				$("<tr>").append(
		  					$("<td>").text(prod.prod_id)		
		  					, $("<td>").text(prod.prod_lgu)		
		  					, $("<td>").text(prod.prod_name)		
		  					, $("<td>").text(prod.prod_cost)		
		  					, $("<td>").text(prod.prod_price)		
		  					, $("<td>").text(prod.prod_buyer)		
		  					, $("<td>").text(prod.prod_mileage)		
		  				).data("prod", prod)		
		  			);
		  		});
		  	}else{
		  		trTags.push(
			  		$("<tr>").html(
			  			$("<td colspan='7'>").addClass("text-center").text("검색 결과 없음.")
			  		)
		  		);
		  		
		  	}
	  		let remainRowCnt = resp.pagingVO.screenSize - trTags.length;
	  		for(let i=0; i<remainRowCnt; i++){
	  			trTags.push($("<tr>").html($("<td colspan='7'>").html("&nbsp;")));
	  		}
		  	listBody.html(trTags);
		  	pagingArea.html(pagingHTML);
		}
	}).submit();
	
	let searchBtn = $("#searchBtn").on("click", function(){
		let inputs = $(this).parents("div#inputUI").find(":input[name]");
		$(inputs).each(function(index, input){
			let name = $(this).attr("name");
			let value = $(this).val();
			let hidden = searchForm.find("[name='"+name+"']");
			hidden.val(value);//현재 name값과 같은 hidden태그 
		});
		searchForm.submit();
	});
</script>

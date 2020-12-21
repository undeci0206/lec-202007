<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/js/DataTables/datatables.min.css">
<script
	src="<%=request.getContextPath()%>/js/DataTables/datatables.min.js"></script>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" />
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request" />
<form method="post" id="memberForm">
	<table class="table table-responsive">
		<tr class="mb-1">
			<th class="text-center">아이디</th>
			<td class="pb-1">
				<div class="input-group">
					<input type="text" class="form-control editable" required
						name="mem_id"
						value="<%=Objects.toString(member.getMem_id(), "")%>"
						placeholder="아이디 기록하기 전에는 빠져나갈수 없숴~" />
					<button id="idCheckBtn" type="button"
						class="btn btn-primary insertOnly">아이디중복확인</button>
					<span class="error"><%=errors.get("mem_id")%></span>
				</div>
			</td>
		</tr>
		<tr>
			<th class="text-center">비밀번호</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" name="mem_pass" required
				pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$" /> <span
				class="error"><%=errors.get("mem_pass")%></span></td>
		</tr>
		<tr>
			<th class="text-center">이름</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" required name="mem_name"
				value="<%=Objects.toString(member.getMem_name(), "")%>" /> <span
				class="error"><%=errors.get("mem_name")%></span></td>
		</tr>
		<tr>
			<th class="text-center">주민번호1</th>
			<td class="pb-1"><input type="text" class="form-control"
				required name="mem_regno1"
				value="<%=Objects.toString(member.getMem_regno1(), "")%>"
				pattern="\d{6}" maxlength="6" /> <span class="error"><%=errors.get("mem_regno1")%></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">주민번호2</th>
			<td class="pb-1"><input type="text" class="form-control"
				required name="mem_regno2"
				value="<%=Objects.toString(member.getMem_regno2(), "")%>" size="7" />
				<span class="error"><%=errors.get("mem_regno2")%></span></td>
		</tr>
		<tr>
			<th class="text-center">생일</th>
			<td class="pb-1"><input type="date" class="form-control"
				name="mem_bir"
				value="<%=Objects.toString(member.getMem_bir(), "")%>" /> <span
				class="error"><%=errors.get("mem_bir")%></span></td>
		</tr>
		<tr>
			<th class="text-center">우편번호</th>
			<td class="pb-1">
				<div class="input-group">
					<input type="text" class="form-control editable" required readonly
						name="mem_zip"
						value="<%=Objects.toString(member.getMem_zip(), "")%>"
						id="mem_zip" tabindex="-1" />
					<button id="zipBtn" type="button" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#zipModal">우편번호 검색</button>
					<span class="error"><%=errors.get("mem_zip")%></span>
				</div>
			</td>
		</tr>
		<tr>
			<th class="text-center">주소1</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" required readonly name="mem_add1"
				value="<%=Objects.toString(member.getMem_add1(), "")%>"
				id="mem_add1" tabindex="-1" /> <span class="error"><%=errors.get("mem_add1")%></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">주소2</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" required readonly name="mem_add2"
				value="<%=Objects.toString(member.getMem_add2(), "")%>"
				id="mem_add2" tabindex="-1" /> <span class="error"><%=errors.get("mem_add2")%></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">집전번</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" required name="mem_hometel"
				value="<%=Objects.toString(member.getMem_hometel(), "")%>" /> <span
				class="error"><%=errors.get("mem_hometel")%></span></td>
		</tr>
		<tr>
			<th class="text-center">회사전번</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" required name="mem_comtel"
				value="<%=Objects.toString(member.getMem_comtel(), "")%>" /> <span
				class="error"><%=errors.get("mem_comtel")%></span></td>
		</tr>
		<tr>
			<th class="text-center">휴대폰</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" name="mem_hp"
				value="<%=Objects.toString(member.getMem_hp(), "")%>" /> <span
				class="error"><%=errors.get("mem_hp")%></span></td>
		</tr>
		<tr>
			<th class="text-center">메일</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" required name="mem_mail"
				value="<%=Objects.toString(member.getMem_mail(), "")%>" /> <span
				class="error"><%=errors.get("mem_mail")%></span></td>
		</tr>
		<tr>
			<th class="text-center">직업</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" name="mem_job"
				value="<%=Objects.toString(member.getMem_job(), "")%>" /> <span
				class="error"><%=errors.get("mem_job")%></span></td>
		</tr>
		<tr>
			<th class="text-center">취미</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" name="mem_like"
				value="<%=Objects.toString(member.getMem_like(), "")%>" /> <span
				class="error"><%=errors.get("mem_like")%></span></td>
		</tr>
		<tr>
			<th class="text-center">기념일</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" name="mem_memorial"
				value="<%=Objects.toString(member.getMem_memorial(), "")%>" /> <span
				class="error"><%=errors.get("mem_memorial")%></span></td>
		</tr>
		<tr>
			<th class="text-center">기념일자</th>
			<td class="pb-1"><input type="date"
				class="form-control editable" name="mem_memorialday"
				value="<%=Objects.toString(member.getMem_memorialday(), "")%>" />
				<span class="error"><%=errors.get("mem_memorialday")%></span></td>
		</tr>
		<tr>
			<th class="text-center">마일리지</th>
			<td class="pb-1">3000</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center pt-2"><input type="submit"
				class="btn btn-primary ml-5" value="저장" /> <input type="reset"
				class="btn btn-secondary" value="취소" /></td>
		</tr>
	</table>
</form>
<div class="modal fade" id="zipModal" data-bs-backdrop="static"
	data-bs-keyboard="false" aria-labelledby="staticBackdropLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel">우편번호 검색</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form id="inModalForm">
				<div class="modal-body">
					<table id="zipTable" class="w-100">
						<thead>
							<tr>
								<th class="text-center">우편번호</th>
								<th class="text-center">주소</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="input-group input-group-sm mt-3 mb-3">
						<span class="input-group-text" id="inputGroup-sizing-sm">우편번호</span>
						<input readonly name="modalZipCode" type="text"
							class="form-control" aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm" tabindex="-1" required />
					</div>
					<div class="input-group input-group-sm mb-3">
						<span class="input-group-text" id="inputGroup-sizing-sm">주소1</span>
						<input readonly name="address1" type="text" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm" tabindex="-1" required />
					</div>
					<div class="input-group input-group-sm mb-3">
						<span class="input-group-text" id="inputGroup-sizing-sm">주소2</span>
						<input name="address2" type="text" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm" required />
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-secondary">주소입력</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
//========입력제한 UI 설정==============================================================
		const EDITABLE = <%="MODIFY".equals(request.getAttribute("command"))%>;
		if(EDITABLE){
			$("#memberForm input:not(.editable)").prop("readonly", true);
			$("button.insertOnly").hide().prop("disable", true);
//==================================================================================
		}else{
//========아이디 중복 체크===============================================================
			const IDCHECKED = "idChecked";
			let idCheckBtn = $("#idCheckBtn").on("click", function(){
				if(!mem_idTag.val()){
					mem_idTag.focus();
					return false;
				}
				$.ajax({
					url: "<%=request.getContextPath()%>/member/idCheck.do",
					data:{
						inputId : mem_idTag.val()
					},
					method:"post",
					dataType:"text",
					success:function(resp){
						if("OK"==resp.trim()){
							mem_idTag.tooltip("hide");
							mem_idTag.data(IDCHECKED, true);
							mem_idTag[0].setCustomValidity('');
						}else{
							mem_idTag.data(IDCHECKED, false);
							mem_idTag[0].setCustomValidity('이미 사용중인 아이디임.');
							mem_idTag.trigger("invalid");
							mem_idTag.tooltip("show");
						}
					},
					error:function(xhr){
						console.log(xhr);
					}
				});
			});
			
			// 입력 폼 제출 전 아이디 중복 체크 여부 확인 
			let memberForm = $("#memberForm").on("submit", function(){
				if(mem_idTag.data(IDCHECKED)===true){
					return true;	
				}else{
					mem_idTag.select();
					mem_idTag.focus();
					return false;
				}
			});
			
			// 아이디 중복 상황에 대한 UI
			let mem_idTag = $("[name='mem_id']").on("blur", function(){
				idCheckBtn.trigger("click");
			}).tooltip({
				title: "이미 사용중인 아이디입니다. 다른 아이디로 바꿔주세요."
				, placement: "bottom"
				, trigger: "manual"
				, delay: { show: 500, hide: 100 }
			}).on("shown.bs.tooltip", function(){
				let tag = $(this);
				setTimeout(() => {
					tag.tooltip("hide");
				}, 3000);
			}).focus();
		}	
		
		// 입력데이터 유효성 체크 UI
		$("#memberForm input[name]").on("invalid", function(){
			$(this).focus();
			$(this).select();
			$(this).addClass("invalid");
		}).on("keydown", function(){
			$(this).tooltip("hide");
			$(this).removeClass("invalid");
		});
//==================================================================================
		
//========우편번호 검색=================================================================
		let zipTable = $('#zipTable').DataTable( {
		    ajax: {
		        url: '<%=request.getContextPath()%>/commons/searchZip.do'
		        , dataSrc:"zipList"
		    },
		    columns: [ 
		    	{ data: 'zipcode' }
		        , { data: 'address' }
			], 
			select: {
				style: "single"
			},
			pageLength: 7,
			lengthChange: false,
			info: false
		    
		} ).on( 'select', function ( e, dt, type, indexes ) {
			let zipVO = dt.data();
			inModalForm[0].modalZipCode.value = zipVO.zipcode;
			inModalForm[0].address1.value = zipVO.address;
		} );
		
		$("#zipModal").on("hidden.bs.modal", function(event){
			zipTable.search("");	
			zipTable.draw();	
		});
		
		let inModalForm = $("#inModalForm").on("submit", function(event){
			event.preventDefault();
			mem_zip.value = inModalForm[0].modalZipCode.value;
			mem_add1.value = inModalForm[0].address1.value;
			mem_add2.value= inModalForm[0].address2.value;
			$("#zipModal").modal("hide");
			return false;
		});
//==================================================================================
	});
</script>




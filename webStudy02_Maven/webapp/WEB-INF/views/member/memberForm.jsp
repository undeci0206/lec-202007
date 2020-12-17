<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/DataTables/datatables.min.css"> 
<script src="<%=request.getContextPath() %>/js/DataTables/datatables.min.js"></script>  

<form method="post" id="memberForm">
	<table class="table">
		<tr>
			<th>아이디</th>
			<td>
				<div class="input-group">
				<input type="text" class="form-control" required name="mem_id" value="" placeholder="아이디 기록하기 전에는 빠져나갈수 없숴~"/>
				<button id="idCheckBtn" type="button" class="btn btn-primary">아이디중복확인</button>
				</div>	
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="text" class="form-control" required name="mem_pass" value="" pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$"/>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type="text" class="form-control" required name="mem_name" value="" />
			</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>
				<input type="text" class="form-control" required name="mem_regno1" value="" size="6" />
			</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>
				<input type="text" class="form-control" required name="mem_regno2" value="" size="7"/>
			</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>
				<input type="date" class="form-control" name="mem_bir" value="" />
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>
				<div class="input-group">
				<input type="text" class="form-control" required readonly name="mem_zip" value="" id="mem_zip"/>
				<button id="zipBtn" type="button" class="btn btn-primary" data-bs-toggle="modal" 
							data-bs-target="#zipModal">우편번호 검색</button>
				</div>			
			</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>
				<input type="text" class="form-control" required readonly name="mem_add1" value="" id="mem_add1"/>
			</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>
				<input type="text" class="form-control" required readonly name="mem_add2" value="" id="mem_add2"/>
			</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td>
				<input type="text" class="form-control" required name="mem_hometel" value="" />
			</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>
				<input type="text" class="form-control" required name="mem_comtel" value="" />
			</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>
				<input type="text" class="form-control" name="mem_hp" value="" />
			</td>
		</tr>
		<tr>
			<th>메일</th>
			<td>
				<input type="text" class="form-control" required name="mem_mail" value="" />
			</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>
				<input type="text" class="form-control" name="mem_job" value="" />
			</td>
		</tr>
		<tr>
			<th></th>
			<td>
				<input type="text" class="form-control" name="mem_like" value="" />
			</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>
				<input type="text" class="form-control" name="mem_memorial" value="" />
			</td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td>
				<input type="date" class="form-control" name="mem_memorialday" value="" />
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<input type="number" class="form-control" name="mem_mileage" value="" />
			</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>
				<input type="text" class="form-control" name="mem_delete" value="" />
			</td>
		</tr>
		<tr>
			<th>메일인증</th>
			<td>
				<input type="text" class="form-control" name="recv_email_yn" value="" />
			</td>
		</tr>
		<tr>
			<th>주석</th>
			<td>
				<input type="text" class="form-control" name="mem_comment" value="" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" class="btn btn-primary" value="저장" />
				<input type="reset" class="btn btn-secondary" value="취소" />
			</td>
		</tr>
	</table>
</form>
<div class="modal fade" id="zipModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel">우편번호 검색</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<table id="zipTable">
					<thead>
						<tr>
							<th>우편번호</th>
							<th>주소</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="input-group input-group-sm mb-3">
				  <span class="input-group-text" id="inputGroup-sizing-sm">우편번호</span>
				  <input readonly id="modalZipCode" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" />
				</div>
				<div class="input-group input-group-sm mb-3">
				  <span class="input-group-text" id="inputGroup-sizing-sm">주소1</span>
				  <input readonly id="address1" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" />
				</div>
				<div class="input-group input-group-sm mb-3">
				  <span class="input-group-text" id="inputGroup-sizing-sm">주소2</span>
				  <input id="address2" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" />
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" id="modalCloseBtn">주소입력</button>
			</div>
		</div>
	</div>	
</div>
<script type="text/javascript">
	$(function(){
		const IDCHECKED = "idChecked";
		let mem_idTag = $("[name='mem_id']").on("blur", function(){
			idCheckBtn.trigger("click");
		}).on("keydown", function(){
			$(this).tooltip("hide");
		}).tooltip({
			title: "이미 사용중인 아이디입니다. 다른 아이디로 바꿔주세요."
			, placement: "bottom"
			, trigger: "manual"
		}).focus();
		let idCheckBtn = $("#idCheckBtn").on("click", function(){
			if(!mem_idTag.val()){
				mem_idTag.focus();
				return false;
			}
			$.ajax({
				url: "<%=request.getContextPath() %>/member/idCheck.do",
				data:{
					inputId : mem_idTag.val()
				},
				method:"post",
				dataType:"text",
				success:function(resp){
					if("OK"==resp.trim()){
						mem_idTag.tooltip("hide");
						mem_idTag.data(IDCHECKED, true);
					}else{
						mem_idTag.data(IDCHECKED, false);
						mem_idTag.tooltip("show");
						mem_idTag.focus();
						mem_idTag.select();
					}
				},
				error:function(xhr){
					console.log(xhr);
				}
			});
		});
		
		$("#memberForm").on("submit", function(){
			if(mem_idTag.data(IDCHECKED)===true){
				return true;	
			}else{
				mem_idTag.select();
				mem_idTag.focus();
				return false;
			}
		});
		
		let zipTable = $('#zipTable').DataTable( {
		    ajax: {
		        url: '<%=request.getContextPath() %>/commons/searchZip.do'
		    },
		    columns: [ 
		    	{ data: 'zipcode' }
		        , { data: 'address' }
			], 
			select: {
				style: "single"
			},
			lengthChange: false
		    
		} ).on( 'select', function ( e, dt, type, indexes ) {
			let zipVO = dt.data();
			console.log(zipVO);
			modalZipCode.value = zipVO.zipcode;
			address1.value = zipVO.address;
		} );
		
		$("#modalCloseBtn").on("click", function(){
			if(modalZipCode.value && address1.value && address2.value){
				mem_zip.value = modalZipCode.value;
				mem_add1.value = address1.value;
				mem_add2.value= address2.value;
				$("#zipModal").modal("hide");
			}
			else $(address2).focus();
		});
	});
</script>
	
	
	
	
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" />
<h4><%=member.getMem_name()%>님의 상세 정보</h4>
<table class="col-md-7 table-bordered table-responsive">
	<tr>
		<th class="text-center">아이디</th>
		<td class="text-center pb-1"><%=member.getMem_id()%></td>
	</tr>
	<tr>
		<th class="text-center">이름</th>
		<td class="text-center pb-1"><%=member.getMem_name()%></td>
	</tr>
	<tr>
		<th class="text-center">주민번호1</th>
		<td class="text-center pb-1"><%=member.getMem_regno1()%></td>
	</tr>
	<tr>
		<th class="text-center">주민번호2</th>
		<td class="text-center pb-1"><%=member.getMem_regno2()%></td>
	</tr>
	<tr>
		<th class="text-center">생일</th>
		<td class="text-center pb-1"><%=member.getMem_bir()%></td>
	</tr>
	<tr>
		<th class="text-center">우편번호</th>
		<td class="text-center pb-1"><%=member.getMem_zip()%></td>
	</tr>
	<tr>
		<th class="text-center">주소1</th>
		<td class="text-center pb-1"><%=member.getMem_add1()%></td>
	</tr>
	<tr>
		<th class="text-center">주소2</th>
		<td class="text-center pb-1"><%=member.getMem_add2()%></td>
	</tr>
	<tr>
		<th class="text-center">집전번</th>
		<td class="text-center pb-1"><%=member.getMem_hometel()%></td>
	</tr>
	<tr>
		<th class="text-center">회사전번</th>
		<td class="text-center pb-1"><%=member.getMem_comtel()%></td>
	</tr>
	<tr>
		<th class="text-center">휴대폰</th>
		<td class="text-center pb-1"><%=member.getMem_hp()%></td>
	</tr>
	<tr>
		<th class="text-center">메일</th>
		<td class="text-center pb-1"><%=member.getMem_mail()%></td>
	</tr>
	<tr>
		<th class="text-center">직업</th>
		<td class="text-center pb-1"><%=member.getMem_job()%></td>
	</tr>
	<tr>
		<th class="text-center">취미</th>
		<td class="text-center pb-1"><%=member.getMem_like()%></td>
	</tr>
	<tr>
		<th class="text-center">기념일</th>
		<td class="text-center pb-1"><%=member.getMem_memorial()%></td>
	</tr>
	<tr>
		<th class="text-center">기념일자</th>
		<td class="text-center pb-1"><%=member.getMem_memorialday()%></td>
	</tr>
	<tr>
		<th class="text-center">마일리지</th>
		<td class="text-center pb-1"><%=member.getMem_mileage()%></td>
	</tr>
	<tr>
		<th class="text-center">탈퇴여부</th>
		<td class="text-center pb-1"><%=member.getMem_delete()%></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="수정" class="btn btn-primary" id="modifyBtn"/>
			<input type="button" value="탈퇴" class="btn btn-warning" id="removeBtn" />
		</td>
	</tr>
	<tr>
		<th>구매목록</th>
		<td>
			<%=member.getProdList() %>
			<table>
				<thead>
					<tr>
						<th>상품코드</th>
						<th>상품분류</th>
						<th>상품명</th>
						<th>구매가</th>
						<th>판매가</th>
					</tr>
				</thead>
			</table>
		</td>
	</tr>
</table>
<!-- Modal -->
<div class="modal fade" id="passwordModal" tabindex="-1" aria-labelledby="passwordModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="<%=request.getContextPath() %>/member/removeMember.do" method="post">
	      <div class="modal-header">
	        <h5 class="modal-title" id="passwordModalLabel">Modal title</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
			<div class="row g-3 align-items-center">
				<div class="col-auto">
					<label for="mem_pass" class="col-form-label">Password</label>
				</div>
				<div class="col-auto">
					<input type="text" name="mem_pass" id="mem_pass"
						class="form-control" required
						pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$" />
				</div>
			</div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">탈퇴</button>
	      </div>
	  </form>
    </div>
  </div>
</div>
<script type="text/javascript">
	$(function(){
		let passwordModal = $("#passwordModal").modal({
								show:false
							}).on("hidden.bs.modal", function(){
								$(this).find("form").get(0).reset();
							});
		$("#removeBtn").on("click", function(){
			passwordModal.modal("show");
		});
		$("#modifyBtn").on("click", function(){
			location.href="<%=request.getContextPath() %>/member/modifyMember.do";
		});
	});
</script>













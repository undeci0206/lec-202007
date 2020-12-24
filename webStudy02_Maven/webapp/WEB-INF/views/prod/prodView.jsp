<%@page import="java.util.Set"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="prod" class="kr.or.ddit.vo.ProdVO" scope="request" />	
<table class="table table-bordered">
	<tr>
		<th class="text-center">상품코드</th>
		<td class="pb-1"><%=prod.getProd_id()%></td>
	</tr>
	<tr>
		<th class="text-center">상품명</th>
		<td class="pb-1"><%=prod.getProd_name()%></td>
	</tr>
	<tr>
		<th class="text-center">분류</th>
		<td class="pb-1"><%=prod.getProd_lgu()%></td>
	</tr>
	<tr>
		<th class="text-center">거래처</th>
		<td class="pb-1">
			<table class="table">
				<thead>
					<tr>
						<th>거래처코드</th>
						<th>거래처명</th>
						<th>담당자</th>
						<th>연락처</th>
						<th>팩스번호</th>
						<th>이메일</th>
						<th>지역</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=prod.getBuyer().getBuyer_id() %></td>
						<td><%=prod.getBuyer().getBuyer_name() %></td>
						<td><%=prod.getBuyer().getBuyer_charger() %></td>
						<td><%=prod.getBuyer().getBuyer_comtel() %></td>
						<td><%=prod.getBuyer().getBuyer_fax() %></td>
						<td><%=prod.getBuyer().getBuyer_mail() %></td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<th class="text-center">구매가</th>
		<td class="pb-1"><%=prod.getProd_cost()%></td>
	</tr>
	<tr>
		<th class="text-center">판매가</th>
		<td class="pb-1"><%=prod.getProd_price()%></td>
	</tr>
	<tr>
		<th class="text-center">세일가</th>
		<td class="pb-1"><%=prod.getProd_sale()%></td>
	</tr>
	<tr>
		<th class="text-center">상품요약</th>
		<td class="pb-1"><%=prod.getProd_outline()%></td>
	</tr>
	<tr>
		<th class="text-center">상세정보</th>
		<td class="pb-1"><%=prod.getProd_detail()%></td>
	</tr>
	<tr>
		<th class="text-center">이미지경로</th>
		<td class="pb-1"><%=prod.getProd_img()%></td>
	</tr>
	<tr>
		<th class="text-center">재고수량</th>
		<td class="pb-1"><%=prod.getProd_totalstock()%></td>
	</tr>
	<tr>
		<th class="text-center">입고일</th>
		<td class="pb-1"><%=prod.getProd_insdate()%></td>
	</tr>
	<tr>
		<th class="text-center">적정재고</th>
		<td class="pb-1"><%=prod.getProd_properstock()%></td>
	</tr>
	<tr>
		<th class="text-center">상품크기</th>
		<td class="pb-1"><%=prod.getProd_size()%></td>
	</tr>
	<tr>
		<th class="text-center">색상</th>
		<td class="pb-1"><%=prod.getProd_color()%></td>
	</tr>
	<tr>
		<th class="text-center">배송방법</th>
		<td class="pb-1"><%=prod.getProd_delivery()%></td>
	</tr>
	<tr>
		<th class="text-center">단위</th>
		<td class="pb-1"><%=prod.getProd_unit()%></td>
	</tr>
	<tr>
		<th class="text-center">입고량</th>
		<td class="pb-1"><%=prod.getProd_qtyin()%></td>
	</tr>
	<tr>
		<th class="text-center">출고량</th>
		<td class="pb-1"><%=prod.getProd_qtysale()%></td>
	</tr>
	<tr>
		<th class="text-center">마일리지</th>
		<td class="pb-1"><%=prod.getProd_mileage()%></td>
	</tr>
	<tr>
		<th>구매자목록</th>
		<td>
			<table class="table">
				<thead class="text-center">
					<tr>
						<th>아이디</th>
						<th>이름</th>
						<th>휴대폰</th>
						<th>이메일</th>
						<th>주소1</th>
						<th>마일리지</th>
					</tr>
				</thead>
				<tbody>
					<%
						Set<MemberVO> memberList = prod.getMemberList();
						if(memberList.size()>0){
							for(MemberVO member : memberList){
								%>
								<tr>
									<td><%=member.getMem_id() %></td>
									<td><%=member.getMem_name() %></td>
									<td><%=member.getMem_hp() %></td>
									<td><%=member.getMem_mail() %></td>
									<td><%=member.getMem_add1() %></td>
									<td><%=member.getMem_mileage() %></td>
								</tr>
								<%
							}
						}else{
							%>
							<tr>
								<td colspan="6">구매자 정보가 없음.</td>
							</tr>
							<%
						}
					%>
					
				</tbody>
			</table>
		</td>
	</tr>
</table>

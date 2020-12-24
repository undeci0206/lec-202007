<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="prod" class="kr.or.ddit.vo.ProdVO" scope="request" />
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request" />
<form method="post">
	<table class="table table-responsive">
		<tr class="mb-1">
		</tr>
		<tr>
			<th class="text-center">상품ID</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_id"
				value="<%=Objects.toString(prod.getProd_id(), "")%>" /> <span
				class="error"><%=errors.get("prod_id")%></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">상품명</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_name"
				value="<%=Objects.toString(prod.getProd_name(), "")%>" /> <span
				class="error"><%=errors.get("prod_name")%></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">상품분류코드</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_lgu"
				value="<%=Objects.toString(prod.getProd_lgu(), "")%>" /> <span
				class="error"><%=errors.get("prod_lgu")%></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">판매자</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_buyer"
				value="<%=Objects.toString(prod.getProd_buyer(), "")%>" /> <span
				class="error"><%=errors.get("prod_buyer")%></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">원가</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_cost"
				value="<%=Objects.toString(prod.getProd_cost(), "")%>" /> <span
				class="error"><%=errors.get("prod_cost")%></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">가격</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_price"
				value="<%=Objects.toString(prod.getProd_price(), "")%>" /> <span
				class="error"><%=errors.get("prod_price")%></span>
			</td>
		</tr>
			<tr>
			<th class="text-center">outline</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_sale"
				value="<%=Objects.toString(prod.getProd_sale(), "")%>" /> <span
				class="error"><%=errors.get("prod_sale")%></span>
			</td>
		</tr>
			<tr>
			<th class="text-center">detail</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_detail"
				value="<%=Objects.toString(prod.getProd_detail(), "")%>" /> <span
				class="error"><%=errors.get("prod_detail")%></span>
			</td>
		</tr>
			<tr>
			<th class="text-center">사진</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_img"
				value="<%=Objects.toString(prod.getProd_img(), "")%>" /> <span
				class="error"><%=errors.get("prod_img")%></span>
			</td>
		</tr>
			<tr>
			<th class="text-center">전체재고</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_totalstock"
				value="<%=Objects.toString(prod.getProd_totalstock(), "")%>" /> <span
				class="error"><%=errors.get("prod_totalstock")%></span>
			</td>
		</tr>
			<tr>
			<th class="text-center">입고일</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_insdate"
				value="<%=Objects.toString(prod.getProd_insdate(), "")%>" /> <span
				class="error"><%=errors.get("prod_insdate")%></span>
			</td>
		</tr>
			<tr>
			<th class="text-center">적정재고</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_properstock"
				value="<%=Objects.toString(prod.getProd_properstock(), "")%>" /> <span
				class="error"><%=errors.get("prod_properstock")%></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">상품크기</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_size"
				value="<%=Objects.toString(prod.getProd_size(), "")%>" /> <span
				class="error"><%=errors.get("prod_size")%></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">색상</th>
			<td class="pb-1"><input type="text"
				class="form-control"  name="prod_color"
				value="<%=Objects.toString(prod.getProd_color(), "")%>" /> <span
				class="error"><%=errors.get("prod_color")%></span>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center pt-2"><input type="submit"
				class="btn btn-primary ml-5" value="저장" /> <input type="reset"
				class="btn btn-secondary" value="취소" /></td>
		</tr>
	</table>
</form>

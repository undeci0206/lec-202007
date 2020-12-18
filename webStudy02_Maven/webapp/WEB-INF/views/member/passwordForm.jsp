<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h4>상세 정보 조회를 위한 이중인증</h4>
<form method="post" class="form-inline">
<div class="row g-3 align-items-center">
  <div class="col-auto">
    <label for="mem_pass" class="col-form-label">Password</label>
  </div>
  <div class="col-auto">
	<input type="text" name="mem_pass" id="mem_pass" 
		class="form-control" required pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$"/>
  </div>
  <div class="col-auto">
	<input type="submit" class="btn btn-primary" value="인증" />
  </div>	
  <div class="col-auto">
    <span id="passwordHelpInline" class="form-text">
      5-12 글자 사이, 영대소문자와 숫자는 반드시 포함.
    </span>
  </div>
</div>
</form>
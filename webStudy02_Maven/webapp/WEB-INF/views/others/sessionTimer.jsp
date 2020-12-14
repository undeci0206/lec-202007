<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 
tiles.xml에 definition
논리적인 definition명 등록

세션 타이머 완성하기 
1. 세션 안 타임아웃 값 가져와서 1초 지날때마다 줄어들게 2:00 -> 0:00 
2. 디스카운트 계속 시켜야되고 0이 되면 타임이 멈춰야함
3. 1분 남았을 때 세션 연장 여부. 연장 선택하면 타이머 2분으로, 선택안하면 시간 계속 진행
4. 연장하겠다고 할 경우 보여지는 시간뿐 아니라 진짜 세션 만료시간도 리셋시키기(1.타임아웃 흘러가기 전에 서버쪽으로
새로운 요청 보내기. 페이지바뀌면 안되니까 비동기로..! 2.타이머리셋
5. 페이지모듈화)
-->
<div id="timerArea">

</div>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/sessionTimer.js"></script>
<script type="text/javascript">
	$(function(){
		//timeToText와의 차이 : 앞에 selector 대상 ($("#timerArea"))이 있다
		//파라미터는 순서가 없기 때문에 식별 불가..-> 이들을 객체로 넘기면, 식별할 수 있다.
		$("#timerArea").sessionTimer({
			timeout:<%=session.getMaxInactiveInterval()%>, 
			sessionURL:"<%=request.getContextPath()%>/02/getMessage.do"
		}); 
	});
</script>

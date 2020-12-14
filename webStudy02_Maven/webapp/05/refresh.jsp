<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="5;url=https://www.naver.com"> --> <!-- 동기방식 2. 클라이언트사이드의 refresh설정 가능 -->
<title>05/refresh.jsp</title>
<script type="text/javascript" src = "https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	 function init(){ //document가 완성된 이후 함수 실행될 수 있도록 지연시킨 것
		/* $("#stopBtn").on("click", function(){
			es.close();
		});
	 	//EventSource : 건건의 데이터를 의미. 
		let es = new EventSource("getServerTime_SSE.jsp");
		es.onopen=function(event){
			console.log(event);
		}
		es.addEventListener("test", function(event){ //한 건의 이벤트에서 타입을 test로 해 줌
			console.log(event);
			timeArea.text(event.data);
		}); */
		/* $(es).on("test", function(event){
			console.log(event);
			//코드상으로는 let timeArea = $("#timeArea");가 아래에 있어도, 
			//function이 선언보다 나중에 실행되기 때문에 출력에 문제가 없다.
			timeArea.text(event.originalEvent.data); 
		}) */
		es.onerror=function(event){
			if(event.target.readyState!=EventSource.CLOSED){//이벤트가 끊기지 않았다면
				es.close(); //강제로 끊어줌
			} 
			console.log(event);
		}
		//interval(주기 설정해주고 반복 작업)과 timeout(지연 시간 설정해둠. 일정 시간 지나면 딱 1번의 작업) 
		//동기방식에서는 timeout 설정한다.
/* 		setTimeout(() => {
			location.reload();
		}, 1000);//동기방식 3.	 */	
		let timeArea = $("#timeArea");
/* 		setInterval(() => { //longfolling~~
			fetch("getServerTime.jsp")
			.then(function(response){ // 콜백 함수 필요. 응답 데이터 필요하다
				if(response.ok){ //처리에 성공했다면. fetch에서는 100부터 500까지 모두 response로 받아옴. ajax로 따지면 success
					console.log(response);
					return response.text(); //promise 객체가 return값으로 들어온다.
				}else{ //ok(200)가 아닌 다른 처리가 나왔다면.. //ajax로 따지면 error
					console.log(response.status);
				}
			}).then(function(text){ //돌아와야 하는 것 : 완성된 문자열(ex.1GB의 완성된 문자열)
				timeArea.text(text);
			}); */
	/* 	 $.ajax({
			url:"getServerTime.jsp",
			method:"get",
			dataType:"text",
	/* 		success:function(resp){ //향후 언젠가 실행될 수 있는 함수. ajax가 호출될 때 바로 실행되는 함수가 아니다.
				timeArea.text(resp); 
			},*/
	 /*		error:function(xhr){
				console.log(xhr);
			}
		}).done(function(xhr){ //status code를 통해 성공/실패 여부를 알 수 있음 
			timeArea.text(resp); //success하나만 처리하는 것 아님. 성공과 실패 둘 다를 커버할 수 있다.
		}) */
//		}, 1000); //Long Polling방식 
	} 
</script>
</head>
<body onload="init();">
<h4>Refresh : 서버사이드의 갱신데이터 조회 방법</h4>
<button type="button" id="stopBtn">STOP!</button>
<%--
	//int형이기 때문에 초단위 (long이면 milliseconds)
	response.setIntHeader("Refresh", 1);  //동기방식 1.
--%>
<pre>
	현재 *서버*의 시각 : <span id="timeArea"></span>
</pre>
</body>
</html>
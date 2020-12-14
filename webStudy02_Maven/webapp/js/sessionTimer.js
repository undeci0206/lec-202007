/**
 * 
 */

if($ && !$.fn.sessionTimer){ //제이쿼리는 있는데 세션타이머가 없으면

	// 초가 줄어드는 형태를 제이쿼리 함수 형태로 만들기
	$.timeToText = function(time){
		//let min = time/60; 이렇게 하면 실수값 나오므로 정수값으로 만들기
		let min = Math.trunc(time/60); //정수값 (내림. trunc 또는 floor함수)
		let sec = time%60;
		return min + ":" + sec;
	}

	
	
	$.fn.sessionTimer = function(param){
		const TIMEOUT = param.timeout;
		const SESSIONURL = param.sessionURL;
		let timeoutId = null;
		//const TIMEOUT = <%=session.getMaxInactiveInterval() %>없앰
		const TIMEUNIT = 100;
		let time = TIMEOUT; //초기값
		let timerArea = this; //$("#timerArea");
		let jobId=null;
		let msgArea = null;
		let makeMessageModal = function(){ 
			//trim() 맨 아랫줄 </div>뒤에 공백이 너무 많아서 trim() 필요 
			let msgArea = $("<div>").html(MSGMODALSRC.trim()).find("#messageModal").modal(); //selecting되어있는 element객체를 modal 객체로 만듦
			let idTime = (new Date()).getTime();
			let id = msgArea.prop("id");
			msgArea.prop("id", id+"_"+idTime);
			msgArea.find("[id]").each(function(index, element){ //element : this와 같음.
				let tmpId = $(this).prop("id"); //decendent 
				$(this).prop("id", tmpId+"_"+idTime);
			});
			
			//document에는 동적으로 element을 추가할 수 없다.$(document).append(msgArea);(X)
			$("body").append(msgArea);
			
			//this : timerArea
		//	this.after(msgArea); //메세지 영역에 추가됨
			
			//msgBtn의 length는 2 
			//함수의  element에 대한 reference가 return값으로 돌아온다.(빌더 패턴에서 setter와 비슷..)
			msgArea.on("click", ".msgBtn", function(){ 
		//		$(this).closest("div").hide(); //나와 가장 가까운 : 메세지창
				msgArea.modal("hide");
				if(this.id.indexOf("yes")==0){//prop: 데이터 타입 유지한 채로 가져올 수 있다. this.id => $(this).prop("id")와 같다.
					//ajaxc 적고 ctrl shift
					$.ajax({
						url : SESSIONURL,
						method : "head", //body가 없는 응답데이터를 받아오겠다
					}).done(function(){ //.done() : ajax에서 promise 객체를 쓸 수 있는 방법.
					
						
						init();
					});
				}
			});
			return msgArea;
		}
		
		//$.init = function(){ : 글로벌은 아니지만 제이쿼리에서는 전역으로 쓸 수 있다.
		//이렇게 하면 함수 안의 함수. 함수가 하나의 객체처럼 동작. 하나의 변수가 evaluation될때는 가까운 변수...?범위 한정됨(closure)
		let init = function(){
			time = TIMEOUT;
			if(msgArea==null)
				msgArea = makeMessageModal();
			if(timeoutId==null)
				timeoutId = setTimeout(function(){ //timeoutId : 글로벌 변수, scope의 제한 받지 않음
					//alert, confirm창으로 띄우지 않기(창 뜰 때 본 창의 시간중단됨, 본 창의 스레드 중단됨 )
					//dialog의 형태가 필요하다(msgArea 만들자)
					msgArea.modal("show");
					timeoutId = null;
				}, (TIMEOUT-60)*TIMEUNIT); //millisecond로 만들기
			
			if(jobId==null)
				jobId = setInterval(() => { //setInterval 적용 시 언제나 return값으로 돌아오는 것 : jobId
					if(time==0) {
						clearInterval(jobId);
					}
					else{
						timerArea.text($.timeToText(--time));
					}
				}, TIMEUNIT);
		}
		
		/*
		이 부분을 아래에서 동적으로 만들어보자.
		<div id = "msgArea">
			세션 연장?
			<button class="msgBtn" id="yes">예</button>
			<button class="msgBtn" id="no">아니오</button>
		</div>*/
		
	//	let msgBtn = [];
	//	msgBtn.push($("<button>").prop("id", "yes").addClass("msgBtn").text("예"));//클래스는 멀티클래스 지정가능. addClass
	//	msgBtn.push($("<button>").prop("id", "no").addClass("msgBtn").text("아니오"));
	//	
	//	//$("<div>").prop("id", "msgArea") => <div id = "msgArea">와 같음
	//	//동적으로 만들겠다. 
	//	let msgArea = $("<div>").prop("id", "msgArea").html("세션 연장?").append(msgBtn).hide();
	
	//	let msgArea = $.parseHTML(MSGMODALSRC.trim());

		init(); //제이쿼리에 init이라는 함수 생성되어야 함
		return this;
		
	}
	
	const MSGMODALSRC = 
	'<div class="modal fade" id="messageModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">   '+
	'<div class="modal-dialog">                                                                                        '+
	'  <div class="modal-content">                                                                                     '+
	'    <div class="modal-header">                                                                                    '+
	'      <h5 class="modal-title" id="messageModalLabel">Modal title</h5>                                             '+
	'    </div>                                                                                                        '+
	'    <div class="modal-body">                                                                                      '+
	'      세션 연장?                                                                                                  '+
	'    </div>                                                                                                        '+
	'    <div class="modal-footer">                                                                                    '+
	'    	<button class="msgBtn" id="yes">예</button>                                                                '+
	'    	<button class="msgBtn" id="no">아니오</button>                                                             '+
	'    </div>                                                                                                        '+
	'  </div>                                                                                                          '+
	'</div>                                                                                                            '+
	'</div>	                                                                                                           ';
}
/**
 * form 태그를 대상으로 비동기 요청 사용.
 */

//제이쿼리 플러그인 방식으로 라이브러리 만들자

$.fn.asyncForm=function(param){
	
	let form = this; //form을 this로 미리 받아놔야함
	let makeData = function(){
		let data = {}
		let inputs = form.find(":input[name]"); //input태그들이 돌아옴, 배열
		
		$(inputs).each(function(index, input){
			let name = $(this).attr("name"); //연상배열구조 사용..
			let value = $(this).val();
			//data.name = value;(X) 이렇게 쓰면 name = "TEST"가 된다.(name이라는 이름으로 고정됨)
			//이렇게 쓰면 객체의 구조 자체를 동적으로 변경 가능
			//동적인 name이름 = "TEST"
			data[name]=value; 
		});
		return data;
	}
	
	console.log(this);
	this.on("submit", function(event){
		event.preventDefault();
		let options = {}
		options.url = this.action;//동적으로 받아올 수 있게
		options.method = this.method;
		options["data"] = makeData(); //submit 제외한 나머지(파라미터 중 name값 가지고 있는 input tag넘기자)
		if(param){
			options.dataType = param.dataType ? param.dataType : "json"; //동적
			options.success = param.success ? param.success : function(resp){ //동적
				console.log("success function 필요");
				console.log(resp);
			};
			if(param.error)	options.error = param.error; //에러여부는 필수가 아니기 때문에 if문
		}
		
		console.log(options);
		$.ajax(options);
		return false;
		
	});
	return this;
}


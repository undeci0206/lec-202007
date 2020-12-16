<%@page import="kr.or.ddit.explorer.FileWrapper"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/fancytree/skin-win8/ui.fancytree.min.css" />
<script src="<%=request.getContextPath() %>/js/fancytree/jquery.fancytree-all.min.js"></script>
<script>
	$(function(){
		const NODEURL="<%=request.getContextPath()%>/server/explorer.do";
		const COMMANDURL = "<%=request.getContextPath() %>/server/fileCommand.do";
		function commandProcess(param){
			let srcFile = param.srcNode.getKeyPath();
			let destFolder = param.destNode? param.destNode.getKeyPath():"";
			$.ajax({
				url : COMMANDURL,
				data :
					{
					command : param.command
					,srcFile : srcFile
					,destFolder : destFolder
					}
				,
				method : "post",
				dataType : "text",
				success : function(resp) {
					//서버사이드 뿐만 아니라 화면에서도 수정해줘야 함
					if("SUCCESS"==resp){
						switch (param.command) {
						case "COPY":
							param.srcNode.copyTo(param.destNode, "child"); //hitMode over와 같음-child	
							break;
						case "MOVE":
							param.srcNode.moveTo(param.destNode, "child");
							break;
						case "DELETE":
							param.srcNode.remove();
						default:
							break;
						}
					}
				},
				error : function(xhr) {
					console.log(xhr);
				}
			});
		};
		
		//delete key눌렀을 때 ajax로 따로 처리하는 법
		//$('html') : document 자체에 keyEvent Binding. 모든 html 소스 selecting
		/* 
		$('html').keyup(function(event){
			if(event.keyCode==46){
				var node = $.ui.fancytree.getTree("#tree").getActiveNode();
				$.ajax({
					url : COMMANDURL,
					data : {
						command : "DELETE"
						,srcFile : node.getKeyPath()
						,destFolder : null
					},
					method : "post",
					dataType : "text",
					success : function(resp) {
						node.remove();
					},
					error : function(xhr) {
						console.log(xhr);
					}
				});
			}
		}); 
		*/
		
		let tree1 = $("#tree").fancytree({
		  extensions:["filter", "dnd"],
		  source: {
			//url : 클라이언트측에서 플러그인이 사용할 url
		    url: NODEURL,
		    cache: false
		  },
		  lazyLoad: function(event, data) {
		     var node = data.node;
		     data.result = {
		        url: NODEURL,
		        data: {
		     		base : node.getKeyPath()
		        },
		        cache : false
		     };
		  },
		  filter: {
		        autoApply: true,   // Re-apply last filter if lazy data is loaded
		        autoExpand: false, // Expand all branches that contain matches while filtered
		        counter: true,     // Show a badge with number of matching child nodes near parent icons
		        fuzzy: false,      // Match single characters in order, e.g. 'fb' will match 'FooBar'
		        hideExpandedCounter: true,  // Hide counter badge if parent is expanded
		        hideExpanders: false,       // Hide expanders if all child nodes are hidden by filter
		        highlight: true,   // Highlight matches by wrapping inside <mark> tags
		        leavesOnly: false, // Match end nodes only
		        nodata: true,      // Display a 'no data' status node if result is empty
		        mode: "dimm"       // Grayout unmatched nodes (pass "hide" to remove unmatched node instead)
		  },
			dnd: {
			      autoExpandMS: 400,
			      focusOnClick: true,
			      preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
			      preventRecursiveMoves: true, // Prevent dropping nodes on own descendants
			      dragStart: function(node, data) {
			         console.log("========start=========");
			         console.log(node);
			         console.log(data);
			         console.log("======================");
			        return !node.folder;
			      },
				  dragEnter: function(node, data) { 
				  	 console.log("========enter=========");
				     console.log(node);
				     console.log(data);
				     console.log("======================");
				     return node.folder && node!==data.otherNode.parent?["over"]:false;
				  },
			      dragDrop: function(node, data) { //node : drop을 받는 node. data : reference 제공
			      	 console.log("========drop=========");
			        console.log(node);
			        console.log(data);
			        console.log("======================");
			        
			        let param = {
			        	srcNode : data.otherNode
			        	, destNode : node
			        	, command : data.originalEvent.ctrlKey?"COPY":"MOVE"
			        }
			        
			        commandProcess(param);
			      }
			  }
		});
		
		let tree = $.ui.fancytree.getTree(tree1);

		$("#search").on("change", function(){
			//f12 - __proto__: Object에서 사용 메서드 확인
			console.log(tree);
			let keyword = $(this).val();
			//let node = $.ui.fancytree.getNode("#tree"),
			tree.filterNodes(keyword);
		});
		
		//delete처리하기
		$(window).on("keyup",function(event){
			let node = tree.getActiveNode();
			
			//파일만 지울 수 있도록(폴더X)
			if(node && node.folder) return false;
			
			//confirm : 자바스크립트는 싱글스레드. 스레드 멈춤. 모달 띄우는 걸로 나중에 바꿔보자
			if(event.keyCode==46 && confirm("진짜 지울래?")){
				let param = {
					command:"DELETE"
					, srcNode:node
					, desNode : ""
						
				}
				commandProcess(param);
				//파라미터를 구성해서 commandProcess() 호출
			}
			
			//console.log(event);
		});
		
		
		
		
	});	
</script>
<h4>탐색기</h4>
<input type="text" id="search" class="form-control"/>
<div id ="tree">
	
</div>

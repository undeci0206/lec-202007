<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
   $(function(){
      $("#loginForm").on("submit", function(){
         let valid = true;
         $(":input[name]").each(function(index, element){
            if($(this).prop("required") ){
               let value = $(this).val();
               if(!value || value.trim().length==0){
                  valid = valid && false;
               }
//                let ptrn = $(this).attr("pattern");
//                if(ptrn){
//                   console.log(ptrn);
//                   let regex = new RegExp(ptrn);
//                   valid = valid && regex.test(value);
//                }
            }
         });
         return valid;
      });
   });
</script>
<%
   MemberVO authMember =(MemberVO) session.getAttribute("authMember");
   if(authMember==null){
%>
<form id="loginForm" action="<%=request.getContextPath() %>/login/loginProcess.do" method="post">
   <ul>
      <li>
         <%
            String mem_id =(String) session.getAttribute("mem_id");
            session.removeAttribute("mem_id");
         %>
         id : <input type="text" name="mem_id" value="<%=Objects.toString(mem_id, "") %>" required/>
         <label><input type="checkbox" name="saveId" value="saveId"/> 아이디 기억하기 </label>
      </li>
      <li>
         pass : <input type="text" name="mem_pass" required pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$"/>
         <input type="submit" value="로그인" />
      </li>
   </ul>
</form>
<%
   }
%>








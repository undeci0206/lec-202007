<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>template</title>
<style type="text/css">
div {
   border: 1px solid black;
}

#topMenu {
   width: 100%;
   height: 50px;
}

#topMenu li {
   float: left;
   list-style: none;
   padding-right: 50px;
}

#topMenu a {
   cursor: pointer;
}

#leftMenu {
   float: left;
   width: 15%;
   height: 500px;
}

#contents {
   float: right;
   width: 84%;
   height: 500px;
}

#footer {
   float: left;
   width: 100;
   height: 50px;
}
</style>
<tiles:insertAttribute name="preScript" />
</head>

<body>
   <header>
      <tiles:insertAttribute name="topMenu" />
   </header>
   <div class="container-fluid border-0">
      <div class="row">
         <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar border-0">
            <tiles:insertAttribute name="leftMenu" />
         </nav>
         <div class="col-md-9 ms-sm-auto col-lg-10 px-md-4 border-0">
            <div class="content m-5 border-0" >
               <tiles:insertAttribute name="contents" />
            </div> 
         </div>
      </div>
   </div>
   <div id="footer">
      <tiles:insertAttribute name="footer" />
      <%
      	String message = (String)request.getAttribute("message");
      if(StringUtils.isBlank(message)){
    	  message = (String)session.getAttribute("message");
    	  session.removeAttribute("message");
      }
      if(StringUtils.isNoneBlank(message)){
      %>
      <script type="text/javascript">
      	alert("<%=message %>");
      </script>
      <%
      	}
      %>
   </div>
</body>

</html>
<%@page import="java.lang.*"%>
<%@page language="java" import="java.util.* ,java.awt.*"
	pageEncoding="UTF-8"%>
	    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<%--
  Created by IntelliJ IDEA.
  User: qjfwow
  Date: 2018/3/30
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>

<head>
<title>实验安排导出</title>
<script>
	document.write("<center><h1>实验安排导出功能</h1></center>");
</script>
<script type="text/javascript">
  function check(){
	  <c:if test="${ empty user}">
	     alert("用户未登陆，请先登录！");
	     </c:if>
	  
  }
</script>
</head>
<body>
	<center>
		<h1>${message}</h1>
		<form action="ToExcel" method="post">
			<input type="submit" value="导出" onclick="check()">
		</form>
	</center>
</body>
</html>

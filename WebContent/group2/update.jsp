<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.*,factory.*,dao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改实验室信息</title>
<link rel="stylesheet" href="shiyan2.css" type="text/css">
<style type="text/css">
<!--
body{background-image:url(/goderyuJavaWeb/group2/image/13.jpg);}
-->
</style>
<script type="text/javascript">
  function check(){
		<c:if test="${ empty user}">
		alert("用户未登陆，请先登录！");
		window.location = "update.jsp";
		return false;
		</c:if>
	
	
		<c:if test="${ user.getCategory() == 0}">
		alert("非管理员不能进行此操作！");
		window.location = "update.jsp";
		return false;
		</c:if>
  }
</script>

</head>
<body>
<%
	request.setCharacterEncoding("UTF-8") ;
%>
<center> 

<%
	String id = "" ;
	try {
		id = request.getParameter("id") ;
	} catch (Exception e){}
	LabInfo labinfo = DAOFactory.getILabInfoDAOInstance().findById(id) ;
%>
<%
	if(labinfo != null) {
%>
<h2 align="center">修改实验室信息</h2>
<form name="form1" action="update_do.jsp" method="post" onSubmit="return check()">
<table align="center" width="40%" border="1">
	<tr><th width="100%">实验室编号：</th>
		<td><input type="text" name="id" value="<%=labinfo.getId()%>"></td></tr>
	<tr><th>位置：</th>
		<td><input type="text" name="weizhi" value="<%=labinfo.getWeizhi()%>"></td></tr>
	<tr><th>实验室类型：</th>
		<td><input type="text" name="leixing" value="<%=labinfo.getLeixing()%>"></td></tr>
	<tr><th>可容纳人数：</th>
		<td><input type="text" name="renshu" value="<%=labinfo.getRenshu()%>"></td></tr>	
	<tr><th>备注：</th>
		<td><textarea cols="25" rows="5" name="beizhu" value="<%=labinfo.getBeizhu()%>"></textarea></td></tr>
	
	<tr><th colspan="2">
	<input type="submit" value="修改">
	<input type="reset" value="重置"></th></tr>
</table>
</form>
<%
	} else {
%>
		<script language="javascript">
			alert("没有此实验室的信息！") ;
			window.location = "list.jsp";
		</script>
<%		
	}
%>
</center>


</body>
</html>
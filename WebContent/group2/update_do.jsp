<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.*" %>
<%@ page import="vo.*,factory.*" %>
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
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8") ;
%>
<%
    String id = request.getParameter("id") ;
	String weizhi = request.getParameter("weizhi") ;
	String leixing = request.getParameter("leixing") ;
	int renshu = Integer.parseInt(request.getParameter("renshu")) ;
	String beizhu = request.getParameter("beizhu") ;
%>
<%
	String msg = "实验室信息修改失败！" ;
	LabInfo labinfo = new LabInfo() ;
	labinfo.setId(id) ;
	labinfo.setWeizhi(weizhi) ;
	labinfo.setLeixing(leixing);
	labinfo.setRenshu(renshu);
	labinfo.setBeizhu(beizhu);
	if(DAOFactory.getILabInfoDAOInstance().doUpdate(labinfo)){
		msg = "实验室信息修改成功！" ;
		%>
		<h3>实验室信息修改成功！</h3>
		<p align="left"><a href="list.jsp">返回</a><p>
<%
	}
%>
<script language="javascript">
	alert("<%=msg%>") ;
	window.location = "list.jsp";
</script>



</body>
</html>
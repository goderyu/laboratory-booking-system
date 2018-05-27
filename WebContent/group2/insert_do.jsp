<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="factory.*,vo.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加实验室信息</title>
<link rel="stylesheet" href="shiyan2.css" type="text/css">
<style type="text/css">
<!--
body{background-image:url(/goderyuJavaWeb/group2/image/13.jpg);}
-->
</style>
</head>
<%	request.setCharacterEncoding("UTF-8");	%>
<body>
<%
	LabInfo labinfo = new LabInfo() ;
	labinfo.setId(request.getParameter("id")) ;
	labinfo.setWeizhi(request.getParameter("weizhi")) ;
	labinfo.setLeixing(request.getParameter("leixing")) ;
	labinfo.setRenshu(Integer.parseInt(request.getParameter("renshu"))) ;
	labinfo.setBeizhu(request.getParameter("beizhu")) ;
try{
	if(DAOFactory.getILabInfoDAOInstance().doCreate(labinfo)){
%>
		<h3>实验室信息添加成功！</h3>
		<p align="left"><a href="list.jsp">返回</a><p>
<%
	} else {
%>
		<h3>实验室信息添加失败！</h3>
<%
	}
%>
<%
}catch(Exception e){
	e.printStackTrace() ;
}
%>
</body>
</html>
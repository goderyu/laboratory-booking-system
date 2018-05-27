<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="factory.*,vo.*"%>
<%@ page import="java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实验室信息</title>
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
		window.location = "list.jsp";
		return false;
		</c:if>
	
	
		<c:if test="${ user.getCategory() == 0}">
		alert("非管理员不能进行此操作！");
		window.location = "list.jsp";
		return false;
		</c:if>
     <c:if test="${ !empty user}">
        return confirm('确定要删除吗？');
     </c:if>

	  
  }
  function check1(){
		<c:if test="${ empty user}">
		alert("用户未登陆，请先登录！");
		window.location = "list.jsp";
		return false;
		</c:if>
	
	
		<c:if test="${ user.getCategory() == 0}">
		alert("非管理员不能进行此操作！");
		window.location = "list.jsp";
		return false;
		</c:if>
 
}
</script>

</head>
<%	request.setCharacterEncoding("UTF-8") ;	%>
<body>
<%
try{
	String keyWord = request.getParameter("kw") ;//接受查询关键字
	//System.out.println("keyWord="+keyWord);

	if(keyWord == null){
		keyWord = "" ;	// 如果没有查询关键字，则查询全部
	}
	List<LabInfo> all = DAOFactory.getILabInfoDAOInstance().findAll(keyWord) ;//取得全部记录
	Iterator<LabInfo> iter = all.iterator() ;//实例化Iterator对象
%>
<center>
<form action="list.jsp" method="post" onsubmit="return check1()">
	请输入查询关键字(实验室位置或类型)：<input type="text" name="kw">
	<input type="submit" value="查询">
</form>
<p><a href="insert.jsp">添加实验室信息</a><p>
<table border="2" width="80%"> 
	<tr>
		<td>实验室编号</td>
		<td>位置</td>
		<td>实验室类型</td>
		<td>可容纳人数</td>
		<td>备注</td>
		<td>管理</td>
	</tr>
<%
	while(iter.hasNext()){
		LabInfo labinfo = iter.next() ;
%>
	<tr>
		<td><%=labinfo.getId()%></td>
		<td><%=labinfo.getWeizhi()%></td>
		<td><%=labinfo.getLeixing()%></td>
		<td><%=labinfo.getRenshu()%></td>
		<td><%=labinfo.getBeizhu()%></td>
		<td><a href="update.jsp?id=<%=labinfo.getId() %>" onclick="return check1()">修改</a>&nbsp;
		<a href="delete_do.jsp?id=<%=labinfo.getId()%>" onclick="return check()">删除</a></td>
	</tr>
<%
	}
%>
</table>
<%
}catch(Exception e){
	e.printStackTrace();
}
%>
</center>
</body>
</html>
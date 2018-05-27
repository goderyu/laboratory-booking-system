<%@ page contentType="text/html; charset=GBK" import="java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>文件上载</title>
<script language="javascript" type="text/javascript">
	function check() {
		var file = document.getElementById("file").value;
		if (file == "") {
			alert("请选择文件");
			return false;
		}
		var strTemp = file.split(".");
		var strCheck = strTemp[strTemp.length - 1];
		if (strCheck.toUpperCase() == 'XLS') {
			return true;
		} else {
			alert('请选择。xls文件进行上传！');
			return false;
		}
	}
	  function check1(){
		  <c:if test="${ empty user}">
		     alert("用户未登陆，请先登录！");
		     </c:if>
		  
	  }
</script>
</head>
<body style="background: url(images/6.jpg)">
	<center>
		<h1 style="font-family: STCaiyun";>
			用户的批量导入
			<h1>
	</center>
	<hr>
	<center>
		<form method="post" action="TomysqlServlet" onsubmit="return check()">
			选择文件1:<input type="file" name="file" id="file"> <input
				type="submit" value="导入" onclick= "check1()">
		</form>
	</center>
	<%
		request.setCharacterEncoding("GBK");
	%>

	<%
		List<String> info = (List<String>) request.getAttribute("info");
		List<String> info1 = (List<String>) request.getAttribute("info1");
		List<String> info2 = (List<String>) request.getAttribute("info2");
		List<String> info3 = (List<String>) request.getAttribute("info3");
		boolean flag = true;
		if (info != null) { // 有信息返回
			Iterator<String> iter = info.iterator();
			Iterator<String> iter1 = info1.iterator();
			Iterator<String> iter2 = info2.iterator();
	%>

	<%
		while (iter.hasNext() && iter1.hasNext() && iter2.hasNext()) {
				if (flag) {
	%>
	<center>
		<div  style="align:center; overflow:scroll; width:310px; height:390px;">
			<table border="1" align="center">
				<tr>
					<td width="100">教师工号</td>
					<td width="100">教师姓名</td>
					<td width="100">联系方式</td>
				</tr>
				<%
					flag = false;
							}
				%>
				<tr>
					<td><%=iter.next()%></td>
					<td><%=iter1.next()%></td>
					<td><%=iter2.next()%></td>
				</tr>
				<%
					}
					}
				%>
		
			</table>
		</div>
	</center>
	<%
		if (info3 != null) { // 有信息返回
			Iterator<String> iter3 = info3.iterator();
			if (iter3.hasNext()) {
	%>
	<h3 align="center"><%=iter3.next()%></h3>
	<%
		}
		}
	%>

</body>
</html>
<%@ page contentType="text/html; charset=GB2312" import="java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>文件上载</title>
<script type="text/javascript">
function check(){
	<c:if test="${ empty user}">
		alert("用户未登陆，请先登录！");
		window.location = "import.jsp";
		return false;
	</c:if>
	
	
	
	<c:if test="${ user.getCategory() == 0}">
		alert("非管理员不能进行此操作！");
		window.location = "import.jsp";
		return false;
	</c:if>
}

</script>
</head>
<body>
	<center>
		<h1>理论课表的导入</h1>
	</center>
	<br>
	<br>
	<center>
		<form action="ImportServlet" onSubmit="return check()" method="post">
			选择课表:<input type="file" name="excel" />
	</center>
	<br>
	<center>
		<input type="submit" value="导入"/>
	</center>
	</form>
	<%
		request.setCharacterEncoding("GBK");
	%>
	<%
		List<String> info = (List<String>) request.getAttribute("info");
		if (info != null) {
			Iterator<String> iter = info.iterator();
			while (iter.hasNext()) {
	%>
	<h4><%=iter.next()%></h4>
	<%
		}
		}
	%>
</body>
</html>
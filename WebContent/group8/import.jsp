<%@ page contentType="text/html; charset=GB2312" import="java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>�ļ�����</title>
<script type="text/javascript">
function check(){
	<c:if test="${ empty user}">
		alert("�û�δ��½�����ȵ�¼��");
		window.location = "import.jsp";
		return false;
	</c:if>
	
	
	
	<c:if test="${ user.getCategory() == 0}">
		alert("�ǹ���Ա���ܽ��д˲�����");
		window.location = "import.jsp";
		return false;
	</c:if>
}

</script>
</head>
<body>
	<center>
		<h1>���ۿα�ĵ���</h1>
	</center>
	<br>
	<br>
	<center>
		<form action="ImportServlet" onSubmit="return check()" method="post">
			ѡ��α�:<input type="file" name="excel" />
	</center>
	<br>
	<center>
		<input type="submit" value="����"/>
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
<%@ page contentType="text/html; charset=GBK" import="java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>�ļ�����</title>
<script language="javascript" type="text/javascript">
	function check() {
		var file = document.getElementById("file").value;
		if (file == "") {
			alert("��ѡ���ļ�");
			return false;
		}
		var strTemp = file.split(".");
		var strCheck = strTemp[strTemp.length - 1];
		if (strCheck.toUpperCase() == 'XLS') {
			return true;
		} else {
			alert('��ѡ��xls�ļ������ϴ���');
			return false;
		}
	}
	  function check1(){
		  <c:if test="${ empty user}">
		     alert("�û�δ��½�����ȵ�¼��");
		     </c:if>
		  
	  }
</script>
</head>
<body style="background: url(images/6.jpg)">
	<center>
		<h1 style="font-family: STCaiyun";>
			�û�����������
			<h1>
	</center>
	<hr>
	<center>
		<form method="post" action="TomysqlServlet" onsubmit="return check()">
			ѡ���ļ�1:<input type="file" name="file" id="file"> <input
				type="submit" value="����" onclick= "check1()">
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
		if (info != null) { // ����Ϣ����
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
					<td width="100">��ʦ����</td>
					<td width="100">��ʦ����</td>
					<td width="100">��ϵ��ʽ</td>
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
		if (info3 != null) { // ����Ϣ����
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
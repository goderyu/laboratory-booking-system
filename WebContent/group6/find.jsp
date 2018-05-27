<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="java.sql.*" import="factory.DatabaseConnectionFactory"
	import="dbc.DatabaseConnection"%>
<%@ page import="java.util.*" import="factory.DAOFactory"
	import="vo.FindLabInfo"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>多条件查询及浏览</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="jm.css">
<link rel="stylesheet" href="dh.css" type="text/css">
<link rel="stylesheet" href="ss.css" type="text/css">

</head>

<body>
	<div id="main">
		<header> </header>

		<aside>
			<div id="navigation">
				<ul>
					<li><a href="#">首页</a></li>
					<li><a href="find.jsp">实验室多条件查询</a></li>
				</ul>
			</div>
		</aside>

		<article>
			<h2 class="title">多条件查询界面</h2>
			<form action="FindLabInfoServlet" method="post">
				<table class="default" align="center">
					<tr>
						<td class="item">实验室编号： <%
							List<String> list1 = DAOFactory.getISearchDAOInstance().number();
							List<String> list2 = DAOFactory.getISearchDAOInstance().teacherName();
							request.setAttribute("list1", list1);
							request.setAttribute("list2", list2);
						%> <select size="1" name="id">
								<option value="-">-</option>
								<c:forEach items="${list1}" var="l">
									<option>${l}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td class="item">教师姓名： <select size="1" name="tname">
								<option value="-">-</option>
								<c:forEach items="${list2}" var="l">
									<option>${l}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td class="item">起 始 周： <select size="1" name="sweek">
								<option value="-">-</option>
								<%
									for (int i = 1; i <= 20; i++) {
								%>
								<option><%=i%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td class="item">结 束 周： <select size="1" name="eweek">
								<option value="-">-</option>
								<%
									for (int i = 1; i <= 20; i++) {
								%>
								<option><%=i%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td class="item">周 几： <select size="1" name="day">
								<option value="-">-</option>
								<%
									for (int i = 1; i <= 7; i++) {
								%>
								<option><%=i%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td class="item">节 次： <select size="1" name="time">
								<option value="-">-</option>
								<option>上午1，2节</option>
								<option>上午3，4节</option>
								<option>中午1，2节</option>
								<option>下午5，6节</option>
								<option>下午7，8节</option>
								<option>晚上9，10节</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="查询" class="btn"></td>
					</tr>
				</table>
			</form>
		</article>

		<article>
			<fieldset>
				<h2 class="title">查找的实验室信息如下</h2>
				<table class="default" align="center">
					<tr>
						<td class="item">实验室编号</td>
						<td class="item">教师姓名</td>
						<td class="item">起始周</td>
						<td class="item">结束周</td>
						<td class="item">周几</td>
						<td class="item">节次</td>
					</tr>


					<%
						List<FindLabInfo> info = null;
						info = (List<FindLabInfo>) request.getAttribute("result");
						if (info != null) {
							for (FindLabInfo n : info) {
					%>
					<tr>
						<td class="item"><%=n.getLabID()%></td>
						<td class="item"><%=n.getTeaID()%></td>
						<td class="item"><%=n.getSweek()%></td>
						<td class="item"><%=n.getEweek()%></td>
						<td class="item"><%=n.getDay()%></td>
						<td class="item"><%=n.getSection()%></td>
					</tr>
					<%
							}
						}
					%>


				</table>
			</fieldset>
		</article>
</body>
</html>

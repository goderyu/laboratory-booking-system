<%@page import="vo.UserInfo"%>
<%@ page import="java.util.List" %>
<%@ page import="vo.teacherCourse" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>表单</title>
	<style type="text/css">
		#left{
	width:100%;
	height:100%;
	margin:0px;
	padding:0px;
	float:left;
}

#left ul li{
	text-align: center;
	padding: 20px 0px;
	list-style-type:none;
	font-size:16px;
}
	</style>
	
<!-- <script type="text/javascript"> 
	function check() {
		<c:if test="${ empty user}">
			alert("用户未登陆，请先登录！");
			window.location = "addform.jsp";
			return false;
		</c:if>
		
		
		<c:if test="${ user.getCategory() == 0}">
			alert("非管理员不能进行此操作！");
			window.location = "addform.jsp";
			return false;
		</c:if>

	}
</script> 
-->
</head>
<body>

<!--  	<c:if test="${ empty user}">
			<script>
			alert("用户未登陆，请先登录！");
			window.location = "AddClass.jsp";
			
			</script>
		</c:if>

		<c:if test="${ user.getCategory() == 1}">
			<script>
			alert("非教师不能进行此操作！");
			window.location = "AddClass.jsp";

			</script>
		</c:if>
-->
	<!--整体外层容器-->
	<div id="container">
		<div id="left">
			
<%
	
	UserInfo user = (UserInfo) session.getAttribute("user");
	String teacherID = "";
	if(user != null){		
		teacherID = user.getNumber();
		if(user.getCategory() != 0){
			%>
			<script>
			alert("非教师不能进行此操作！");
			window.location = "AddClass.jsp";

			</script>
			<%
		}
	}else{
%>
			<script>
			alert("用户未登陆，请先登录！");
			window.location = "AddClass.jsp";
			
			</script>
<% 
	}
	List<teacherCourse> all = DAOFactory.getIResDAOInstance().findByteacherID(teacherID);//获得该教师的所有课程
%>
			<ul>
				<form name="regform" method="post" action="AddServlet" onSubmit="return check()">
					<input name="teacherName" value="<%=teacherID%>" type="hidden">
				<li>课程名：<select name="course">
					<%
						if(all!=null){
							Iterator<teacherCourse> iter = all.iterator();
							while(iter.hasNext()){
							    teacherCourse tc = iter.next();
								%><option value="<%=tc.getCourseID() %>"><%=tc.getCourseName() %></option><%
							}
						}
					%>
				</select><br></li>
				<li>实验室编号<input type="text" name="laboratoryID" id="laboratoryID" readonly="readonly" value="<%=request.getParameter("laboratoryID")%>" /><br></li>
				<li><select name='whichDay'>
				<%
					for(int i=1;i<=7;i++){
					    if(i == Integer.parseInt(request.getParameter("whichday"))){
					        if(i==1){
					            %><option value='1'>周一</option><%
							}
							if(i==2){
								%><option value='2'>周二</option><%
							}
							if(i==3){
								%><option value='3'>周三</option><%
							}
							if(i==4){
								%><option value='4'>周四</option><%
							}
							if(i==5){
								%><option value='5'>周五</option><%
							}
							if(i==6){
								%><option value='6'>周六</option><%
							}
							if(i==7){
								%><option value='7'>周日</option><%
							}
							break;
						}
					}
				%>
				</select>
				<select name='whichSection'>
					<%
						for(int i=1;i<=6;i++){
							if(i == Integer.parseInt(request.getParameter("section"))){
								if(i==1){
									%><option value='1'>上午1，2节</option><%
								}
								if(i==2){
									%><option value='2'>上午3，4节</option><%
								}
								if(i==3){
									%><option value='3'>中午1，2节</option><%
								}
								if(i==4){
									%><option value='4'>下午5，6节</option><%
								}
								if(i==5){
									%><option value='5'>下午7，8节</option><%
								}
								if(i==6){
									%><option value='6'>晚上9，10节</option><%
								}
								break;
							}
						}
					%>
				</select><br></li>
				<li>第<select name='startweek'>
				<%
					for(int i=1;i<=20;i++){
						%><option value="<%=i%>"><%=i%></option><%
					}
				%>
				</select>周到第
				<select name='endweek'>
					<%
						for(int i=1;i<=20;i++){
							%><option value="<%=i%>"><%=i%></option><%
						}
					%>
				</select>周</li>
				
				<li><input type="submit" name="submit" value="预约"/></li>
				</form>
			</ul>
			<%--<%=request.getParameter("section")%>--%>
			<%--<%=request.getParameter("whichday")%>--%>
			<%--<%=request.getParameter("week")%>--%>
			<%--<%=request.getParameter("laboratoryID")%>--%>
		</div>
		
		
	</div>
</body>
</html>
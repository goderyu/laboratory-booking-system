<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="vo.UserInfo"%>
<%@page import="vo.teacherCourse"%>
<%@page import="java.util.List"%>
<%@page import="factory.DAOFactory"%>
<%@page import="java.util.Iterator"%>
<%@ page import="vo.ReserveExperiment" %>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>课程信息</title>
	<style type="text/css">
		table,th,td{border: 1px solid gray}
		table{border-collapse: collapse;}
		.head{width: 100px}
		.left{width: 20px;height: 70px}
		.content{}
		.table{text-align: center;}
		.memu{
			width: 400px;
			height: 25px;
			margin: 0px auto;
			border: 1px solid #999;
		}
		.memu ul{
			margin: 0px;
			padding: 0px;
			list-style: none;
		}
		.memu ul li{
			text-align: center;
			width: 100px;
			position: relative;
			float: left;
		}
		
	</style>
</head>
<body>
	<div class="table">
	<%
	
	String b[][]= new String [7][8];
	String ID=request.getParameter("classID");
	List<teacherCourse> res = DAOFactory.getIResDAOInstance().findClassName(ID);
	Iterator<teacherCourse> iter = res.iterator();
	while(iter.hasNext()){
		teacherCourse Res=iter.next();
		String Name=Res.getCourseName();
		List<ReserveExperiment> res1 = DAOFactory.getIResDAOInstance().findMassage(ID);
		Iterator<ReserveExperiment> iter1 = res1.iterator();
		while(iter1.hasNext()){
			ReserveExperiment Res1=iter1.next();
			int i=0,j=0;
			 i = Res1.getSection();
			 j = Res1.getWhichDay();
			if(b[i-1][j-1]!=null)
				b[i-1][j-1]+="<br>地点:"+Res1.getLaboratoryID()+"<br>周次:"+Res1.getStartweek()+"-"+Res1.getEndweek();
			else{
				String name = null;
				name = DAOFactory.getIResDAOInstance().findTeacherName(Res1.getTeacherName());
				b[i-1][j-1]="课程:"+Name+"<br>"+"教师:"+name+"<br>"+"地点:"+Res1.getLaboratoryID()+"<br>"+"周次:"+Res1.getStartweek()+"-"+Res1.getEndweek();
			}
		}
	}
	%>
	<table align="center">
		<caption>课程安排表</caption>
		<tr>
			<td colspan="2">节次</td>
			<td class="head">星期一</td>
			<td class="head">星期二</td>
			<td class="head">星期三</td>
			<td class="head">星期四</td>
			<td class="head">星期五</td>
			<td class="head">星期六</td>
			<td class="head">星期日</td>			
		</tr>
<%
for(int i=0;i<6;i++){
	out.println("<tr>");
	if(i==0){
		out.println("<td class='left' rowspan='2'>上午</td>");
		out.println("<td class='left'>第一二节</td>");
	}else if(i==1){
		out.println("<td class='left'>第三四节</td>");
	}else if(i==2){
		out.println("<td class='left'>中午</td>");
		out.println("<td class='left'>第一二节</td>");
	}else if(i==3){
		out.println("<td class='left' rowspan='2'>下午</td>");
		out.println("<td class='left'>第五六节</td>");
	}else if(i==4){
		out.println("<td class='left'>第七八节</td>");
	}else{
		out.println("<td class='left'>晚上</td>");
		out.println("<td class='left'>第九十节</td>");
	}
	for(int j=0;j<7;j++){
		if(b[i][j]==null)
			out.println("<td class='content'></td>");
		else
			out.println("<td class='content'>"+b[i][j]+"</td>");
	}
	out.println("</tr>");
}
%>
	</table>
	<form name="regform" method="post" action="SearchCourse.jsp">
	<input type="submit" name="submit" value="返回"/>
	</form>
	</div>
</body>
</html>
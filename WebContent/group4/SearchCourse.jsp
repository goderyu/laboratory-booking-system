<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="vo.UserInfo"%>
<%@page import="java.util.Iterator"%>
<%@page import="factory.DAOFactory"%>
<%@page import="vo.laboratory"%>
<%@page import="java.util.List"%>
<%@ page import="vo.ReserveExperiment" %>
<%@page import="vo.teacherCourse"%>

<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询课程</title>
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
	<%
	String t=request.getParameter("identify");
	String Value1=request.getParameter("week");
	String Value2=request.getParameter("room");
	
	if(Value1==null){
		Value1="1";
	}
	%>
	<div class="table">
	<form name="regform" method="post" action="SearchCourse.jsp">
	<select name="week">
            <%
                for (int i=1;i<=20;i++){
                    String str=String.valueOf(i);
                    if(str.equals(request.getParameter("week"))){%>
            <option value="<%=i %>" selected="selected">第<%=i %>周</option><%
        }else{%>
            <option value="<%=i %>">第<%=i %>周</option><%
                }
            }%>
        </select>
        <select name="room">
            <%
                List<laboratory> all = null;
                try {
                    all = DAOFactory.getIResDAOInstance().findAllLab();
                    Iterator<laboratory> iter = all.iterator();
					int k=1;
                    while(iter.hasNext()){
                        laboratory lab = iter.next();
                        if(k==1){
                        	if(Value2 == null){
                        		
                        	Value2=lab.getLaboratoryID();
                        	}
                        }
                        k++;
                        if(lab.getLaboratoryID().equals(request.getParameter("room"))){
            %><option selected="selected"><%=lab.getLaboratoryID()%></option><%
        }else{
        %><option><%=lab.getLaboratoryID()%></option><%
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
        </select>
		<br>
	<input type="radio" name="identify" value="1">只看本人课程</input>
	<input type="submit" name="submit" value="查询"/>
	</form>
	<% 
	String a[][]= new String [7][8];
	
	if(t!=null){
		//当教师登陆时在此处读出教师编号，然后根据编号查询教师名称先假定教师名称为张三
		String teacherNumber="";
		UserInfo user = (UserInfo) session.getAttribute("user");
		teacherNumber = user.getNumber();
		if(Value1==null||Value2==null)
		{
			out.print("请选择！");}
		else
		{
			//只查询本人课程信息
		List<ReserveExperiment> res = DAOFactory.getIResDAOInstance().find(Value2,Integer.parseInt(Value1),teacherNumber);
		Iterator<ReserveExperiment> iter = res.iterator();
		while(iter.hasNext()){
		    ReserveExperiment Res = iter.next();
		    int i=0,j=0;
		    i = Res.getSection();
		    j = Res.getWhichDay();
		    String ID=Res.getCourseID();
		    List<teacherCourse> res1 = DAOFactory.getIResDAOInstance().findClassName(ID);
			Iterator<teacherCourse> iter1 = res1.iterator();
			while(iter1.hasNext()){
				teacherCourse Res1=iter1.next();
				String Name=Res1.getCourseName();
				String teacherName = DAOFactory.getIResDAOInstance().findTeacherName(Res.getTeacherName());
				a[i-1][j-1] ="<a href=ClassMassage.jsp?classID="+ID+">"+Name+"</a>"+"<br>"+"教师:"+teacherName+"<br>"+"地点:"+Res.getLaboratoryID();
			}
		}
		}
	}else{
		if(Value1==null||Value2==null)
		{
			out.print("请选择！");}
		else
			//查询所有课程信息
		{
			List<ReserveExperiment> res = DAOFactory.getIResDAOInstance().find(Value2,Integer.parseInt(Value1),null);
			Iterator<ReserveExperiment> iter = res.iterator();
			while(iter.hasNext()){
			    ReserveExperiment Res = iter.next();
			    int i=0,j=0;
			    i = Res.getSection();
			    j = Res.getWhichDay();
			    String ID=Res.getCourseID();
			    List<teacherCourse> res1 = DAOFactory.getIResDAOInstance().findClassName(ID);
				Iterator<teacherCourse> iter1 = res1.iterator();
				while(iter1.hasNext()){
					teacherCourse Res1=iter1.next();
					String Name=Res1.getCourseName();
					String teacherName = DAOFactory.getIResDAOInstance().findTeacherName(Res.getTeacherName());
					a[i-1][j-1] ="<a href=ClassMassage.jsp?classID="+ID+">"+Name+"</a>"+"<br>"+"教师:"+teacherName+"<br>"+"地点:"+Res.getLaboratoryID();
				}
			}
		}
	}
	%>	

	
	<table align="center">
		<caption>实验课程安排表</caption>
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
for(int m=0;m<6;m++){
	out.println("<tr>");
	if(m==0){
		out.println("<td class='left' rowspan='2'>上午</td>");
		out.println("<td class='left'>第一二节</td>");
	}else if(m==1){
		out.println("<td class='left'>第三四节</td>");
	}else if(m==2){
		out.println("<td class='left'>中午</td>");
		out.println("<td class='left'>第一二节</td>");
	}else if(m==3){
		out.println("<td class='left' rowspan='2'>下午</td>");
		out.println("<td class='left'>第五六节</td>");
	}else if(m==4){
		out.println("<td class='left'>第七八节</td>");
	}else{
		out.println("<td class='left'>晚上</td>");
		out.println("<td class='left'>第九十节</td>");
	}

	for(int i=0;i<7;i++){
		if(a[m][i]==null)
			out.println("<td class='content'></td>");
		else
			out.println("<td class='content'>"+a[m][i]+"</td>");
	}
	out.println("</tr>");
}

%>
	</table>
	</div>
</body>
</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.Iterator"%>
<%@page import="factory.DAOFactory"%>
<%@page import="vo.laboratory"%>
<%@page import="vo.labBan"%>
<%@page import="java.util.List"%>
<%@ page import="vo.ReserveExperiment" %>
<%@ page import="vo.teacherCourse" %>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=utf-8"
			 pageEncoding="utf-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>实验预约</title>
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
	String laboratoryID = request.getParameter("room");
	String week = request.getParameter("week");
	if(week==null){
		week="1";
	}
%>
<div class="table">
	<form action="AddClass.jsp">
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
                        	if(laboratoryID == null){
                        	laboratoryID=lab.getLaboratoryID();
                        		
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
		<input type="submit" name="submit" value="查询"/>
	</form>
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
			
			String table[][] = new String[7][8];
			List<ReserveExperiment> res = DAOFactory.getIResDAOInstance().find(laboratoryID,Integer.parseInt(week),null);
			Iterator<ReserveExperiment> iter = res.iterator();
			while(iter.hasNext()){
				
				ReserveExperiment Res = iter.next();
				int i=0,j=0;
				i = Res.getSection();
				j = Res.getWhichDay();
				String ID=Res.getCourseID();
				List<teacherCourse> res1 = DAOFactory.getIResDAOInstance().findClassName(ID);
				Iterator<teacherCourse> iter1 = res1.iterator();
				while(iter1.hasNext()) {
					teacherCourse tc = iter1.next();
					String Name = tc.getCourseName();
					String teacherName = DAOFactory.getIResDAOInstance().findTeacherName(Res.getTeacherName());
					table[i][j] = Name + "<br>" + teacherName + "/" + Res.getLaboratoryID();

				}
			}

			//设置禁用
			
			List<labBan> allLabBan = DAOFactory.getIResDAOInstance().findLabBan(laboratoryID, Integer.parseInt(week));
			Iterator<labBan> iter2 = allLabBan.iterator();
			labBan lab = null;
			if(iter2.hasNext()){
				lab = iter2.next();
			}
			if(lab != null){			
				int banDay = lab.getWhichday();
				int banSection = lab.getSection();
				for(int i=1;i<7;i++){
					int k1 = banSection/((int)Math.pow(10, 6-i));
					
					banSection = banSection%((int)Math.pow(10, 6-i));
					if(k1 == 1){
						int banDay1 = banDay;
						for(int j=1;j<8;j++){
							int k2 = banDay1/((int)Math.pow(10, 7-j));
							
							banDay1 = banDay1%((int)Math.pow(10, 7-j));
							if(k2 == 1){
								table[i][j] = "关闭";
							}
						}
					}
				}
			}
			//拼入添加链接
			for(int i=1;i<7;i++){
			    for(int j=1;j<8;j++){
			        if(table[i][j]==null){
			            table[i][j] = "<a href='addform.jsp?section="+i+"&whichday="+j+"&week="+week+"&laboratoryID="+laboratoryID+"'>预约</a>";
					}
				}
			}
			for(int i=1;i<=6;i++){
				out.println("<tr>");
				if(i==1){
					out.println("<td class='left' rowspan='2'>上午</td>");
					out.println("<td class='left'>第一二节</td>");
				}else if(i==2){
					out.println("<td class='left'>第三四节</td>");
				}else if(i==3){
					out.println("<td class='left'>中午</td>");
					out.println("<td class='left'>第一二节</td>");
				}else if(i==4){
					out.println("<td class='left' rowspan='2'>下午</td>");
					out.println("<td class='left'>第五六节</td>");
				}else if(i==5){
					out.println("<td class='left'>第七八节</td>");
				}else{
					out.println("<td class='left'>晚上</td>");
					out.println("<td class='left'>第九十节</td>");
				}
				for(int j=1;j<=7;j++){
					out.println("<td class='content'>"+table[i][j]+"</td>");
				}
				out.println("</tr>");
			}
		%>

	</table>
</div>
</body>
</html>
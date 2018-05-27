<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="vo.UserInfo"%>
<%@page import="java.util.Iterator"%>
<%@page import="factory.DAOFactory"%>
<%@page import="vo.laboratory"%>
<%@page import="java.util.List"%>
<%@ page import="vo.ReserveExperiment" %>
<%@ page import="vo.teacherCourse" %>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>更改预约</title>
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
    //String teacherName=request.getParameter("teacherName");
    //默认值
    if(week==null)
        week="1";
    //if(laboratoryID==null)
       // laboratoryID = "6313";
    UserInfo user = (UserInfo)session.getAttribute("user");
    String teacherID = "";
    if(user != null){
    teacherID = user.getNumber();
    	
    }else{
    	%>
		<script>
		alert("用户未登陆，请先登录！");
		window.location = "/goderyuJavaWeb/main.html";
		
		</script>
<%

    }
    int weekvalue=Integer.parseInt(week);
    String oid=null;
%>
<div class="table">
    <form action="updateOrderRecord.jsp">
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
            String table[][] = new String[6][7];
            int orderID[][] = new int[6][7];
            List<ReserveExperiment> res = null;
            try {
                res = DAOFactory.getIResDAOInstance().find(laboratoryID,weekvalue,teacherID);
                Iterator<ReserveExperiment> iter2 = res.iterator();
                while(iter2.hasNext()){
                    ReserveExperiment Res = iter2.next();
                    int i=0,j=0;
                    i = Res.getSection();
                    j = Res.getWhichDay();
                    orderID[i-1][j-1]=Res.getOrderID();
                    String courseID=Res.getCourseID();
                    List<teacherCourse> res2=DAOFactory.getIResDAOInstance().findCourseName(courseID);
                    Iterator<teacherCourse> iter3=res2.iterator();
                    if(iter3.hasNext()){
                        teacherCourse cs=iter3.next();
                        String name = DAOFactory.getIResDAOInstance().findTeacherName(Res.getTeacherName());
                        table[i-1][j-1] = cs.getCourseName()+"("+cs.getCourseID()+")<br>"+name+"/"+Res.getLaboratoryID();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                    if(table[i][j]==null)
                        out.println("<td class='content'>&nbsp</td>");
                    else {
                        out.println("<td class='content'>"+table[i][j]
                                +"<a href='updateform.jsp?orderID="+orderID[i][j]+"&week="+week+"'>更改</a></td>");
                    }
                }out.println("</tr>");
            }

        %>

    </table>
</div>
</body>
</html>


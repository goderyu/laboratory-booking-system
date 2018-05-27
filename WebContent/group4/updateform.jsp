<%@ page import="java.util.List" %>
<%@ page import="vo.teacherCourse" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="vo.ReserveExperiment" %>
<%@ page import="vo.laboratory" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>更改</title>
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
</head>
<body>

<!--  		<c:if test="${ empty user}">
			<script>
			alert("用户未登陆，请先登录！");
			window.location = "updateOrderRecord.jsp";
			
			</script>
		</c:if>
		
		<c:if test="${ user.getCategory() == 1}">
			<script>
			alert("非教师不能进行此操作！");
			window.location = "updateOrderRecord.jsp";

			</script>
		</c:if>

-->
<!--整体外层容器-->
<div id="container">
    <div id="left">

        <%
            int orderID =Integer.parseInt(request.getParameter("orderID"));
            int week=Integer.parseInt(request.getParameter("week"));

            String update=request.getParameter("update");
            if(update!=null){
                String teacherName=request.getParameter("teacherName");
                String courseID=request.getParameter("course");
                String labID=request.getParameter("lab");
                int weekvalue=Integer.parseInt(request.getParameter("weekvalue"));
                int whichDay=Integer.parseInt(request.getParameter("whichDay"));
                int Section=Integer.parseInt(request.getParameter("Section"));
                System.out.print(teacherName);
                List<ReserveExperiment> res2=null;
                res2=DAOFactory.getIResDAOInstance().searchOrder(orderID,weekvalue);
                Iterator<ReserveExperiment> iter2 = res2.iterator();
                while(iter2.hasNext()){
                    ReserveExperiment Res = iter2.next();
                    if(courseID==Res.getCourseID()&&labID==Res.getLaboratoryID()&&whichDay==Res.getWhichDay()&&Section==Res.getSection()&&weekvalue==week){
                       break;
                    }else{
                        if(!DAOFactory.getIResDAOInstance().banJudge(labID,Section,whichDay,weekvalue,weekvalue)){
                            break;
                        }else{
                            if(!DAOFactory.getIResDAOInstance().insertJudge(labID,Section,whichDay,weekvalue,weekvalue)){
                                break;
                            }else{
                                int k1=DAOFactory.getIResDAOInstance().deleteOrder(orderID,week);
                                if(k1==1) {
                                    boolean k2 = DAOFactory.getIResDAOInstance().insertRes(teacherName, labID, courseID, whichDay, Section, weekvalue, weekvalue);
                                    if(k2) {
                                        out.print("<script>alert('更改成功!'); window.location.href='updateOrderRecord.jsp';</script>");
                                        
                                    }else{
                                        out.print("<script>alert('更改失败!'); window.location.href='updateOrderRecord.jsp';</script>");
                                    }
                                }
                            }
                        }

                    }
                }


            }

            List<ReserveExperiment> res = null;
            res = DAOFactory.getIResDAOInstance().searchOrder(orderID,week);
            Iterator<ReserveExperiment> iter2 = res.iterator();
            while(iter2.hasNext()) {
                ReserveExperiment Res = iter2.next();
                String courseID=Res.getCourseID();
                String laborataryID=Res.getLaboratoryID();
                String teacherName=Res.getTeacherName();
                int whichDay=Res.getWhichDay();
                int section=Res.getSection();
                List<teacherCourse> all = null;//获得该教师的所有课程
                try {
                    all = DAOFactory.getIResDAOInstance().findByteacherID(teacherName);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        %>

        <ul>
            <form name="regform" method="post" action="updateform.jsp?update='yes'&orderID=<%=orderID%>&teacherName=<%=teacherName%>&oid=<%=orderID%>&week=<%=week%>">
                <li>课程名：<select name="course">
                    <%
                        if(all!=null){
                            Iterator<teacherCourse> iter = all.iterator();
                            while(iter.hasNext()){
                                teacherCourse tc = iter.next();
                                String csID=tc.getCourseID();
                                List<teacherCourse> cs=DAOFactory.getIResDAOInstance().findCourseName(csID);
                                Iterator<teacherCourse> iter3=cs.iterator();
                                if(iter3.hasNext()){
                                    teacherCourse cs2=iter3.next();

                    %><option value="<%=csID %>"><%=cs2.getCourseName() %></option><%
                                 }
                            }
                        }
                %>
                </select><br></li>
                <li>实验室编号
                    <select name="lab"><%
                    List<laboratory> all2 = null;
                    try {
                        all2 = DAOFactory.getIResDAOInstance().findAllLab();
                        Iterator<laboratory> iter = all2.iterator();
                        while(iter.hasNext()){
                            laboratory lab = iter.next();
                            if(lab.getLaboratoryID().equals(laborataryID)){
                %><option selected="selected" value="<%=lab.getLaboratoryID()%>"><%=lab.getLaboratoryID()%></option><%
                }else{
                %><option value="<%=lab.getLaboratoryID()%>"><%=lab.getLaboratoryID()%></option><%
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    %></select></li>
                <li>第<select name='weekvalue'>
                    <%
                        for(int i=1;i<=20;i++){
                            if(i==week){
                    %><option value="<%=i%>" selected="selected"><%=i%></option><%
                        }else{
                                %><option value="<%=i%>"><%=i%></option><%
                        }
                    }

                %></select>周</li>
                <li><select name='whichDay'>
                    <%
                        String[] a=new String[]{"周一","周二","周三","周四","周五","周六","周日"};
                        for(int i=1;i<8;i++){
                            if(i==whichDay){
                                %> <option value='<%=i%>' selected="selected"><%=a[i-1]%></option><%
                            }else{
                                %><option value='<%=i%>'><%=a[i-1]%></option><%
                            }
                        }
                    %>

                    </select>
                    <select name='Section'>
                        <%
                            String[] b=new String[]{"上午1，2节","上午3，4节","中午1，2节","下午5，6节","下午7，8节","晚上9，10节"};
                            for(int i=1;i<7;i++){
                                if(i==section){
                        %> <option value='<%=i%>' selected="selected"><%=b[i-1]%></option><%
                    }else{
                    %><option value='<%=i%>'><%=b[i-1]%></option><%
                            }
                        }
                    %>
                    </select><br></li>
                <script type="text/javascript">
                    $("#whichDay").val("<%=request.getParameter("whichday")%>");
                    $("#Section").val("<%=request.getParameter("section")%>");
                </script>


                <li><input type="submit" name="submit" value="录入"/>
                    <input type="reset" name="reset" value="重置"/></li>
            </form>
        </ul>
        <%}%>
    </div>


</div>
</body>
</html>
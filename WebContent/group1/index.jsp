<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8" />
<title></title>
<style>
*{
padding:0px;
margin:0px;
}
a{color:White}
body{
font-family:Arial, Helvetica, sans-serif;
background:url(images_f/1.jpg) no-repeat center;
font-size:13px; 
}
img{
border:0;
}
.lg{
width:468px; height:468px; margin:100px auto;
 background:url(images_f/2.png) no-repeat center;}
.lg_top{ height:200px; width:468px;}
.lg_main{width:400px; height:180px; margin:0 25px;}
.select
{
width:auto;
 height:auto;
 margin-left:75px;
}
#select_1
{
width:120px;
 height:40px;
 font-size:25px;
 line-height:40px;
font-family:Arial, Helvetica, sans-serif;
  text-align:center; 
   border:3px solid #99CC99;
}
#select_2
{
width:120px;
 height:40px;
  font-size:25px;
 line-height:40px;
font-family:Arial, Helvetica, sans-serif;
  text-align:center; 
   border:3px solid #99CC99;
}
#select_3
{
width:220px;
 height:40px;
 font-size:25px;
 line-height:40px;
 margin-left:30px;
 margin-bottom:10px;
font-family:Arial, Helvetica, sans-serif;
  text-align:center; 
  color:black;
   border:3px solid #99CC99;
}
#lg_m_1{
width:290px;
height:100px;
padding:10px 55px 10px 55px;
  display:block;
}
#lg_m_12{
width:290px;
height:100px;
padding:10px 55px 10px 55px;
  display:none;
}

.lg_foot{
height:80px;
width:330px;
text-align:center;
 background:url(images_f/5.png) no-repeat; 
margin-left:65px;
}

</style>

<script language="javascript" type="text/javascript">
function display1()
{
var lg_m_1=document.getElementById("lg_m_1");
var lg_m_2=document.getElementById("lg_m_2");
	lg_m_1.style.display="block";
	lg_m_12.style.display="none";
}
function display2()
{
var lg_m_1=document.getElementById("lg_m_1");
var lg_m_2=document.getElementById("lg_m_2");
	lg_m_1.style.display="none";
	lg_m_12.style.display="block";
}
</script>
</head>

<body class="b">
<div class="lg">
<form action="#" method="POST">
<div class="lg_top"></div>
<div class="lg_main">
<div class="select">
<table>
<tr>
<td>
<div id="select_1" ><a href="login.jsp">登录</a>
</div>
</td>

<td>
<div id="select_2" ><a href="register.jsp">注册</a>
</div>
</td>
</tr>
</table>
</div>



</div>

<div class="lg_foot">
<h2>实验室管理系统</h2>
</div>
</form>
</div>

</body>
</html>
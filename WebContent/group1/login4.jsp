<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ page errorPage="" import="java.util.*"%>
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
width:468px; height:468px;margin:100px auto 10px auto;
 background:url(images_f/2.png) no-repeat;}
.lg_top{ height:185px; width:468px;}
.lg_main{width:400px; height:170px; margin:0 25px; }
.select
{
width:240px;
 height:30px;
 margin-left:75px;
}
#select_1
{
width:240px;
 height:30px;
 font-size:20px;
 line-height:30px;
font-family:Arial, Helvetica, sans-serif;
  text-align:center; 
   border:3px solid #99CC99;
}

#lg_m_1{
width:220px;
height:90px;
padding:5px 55px 5px 55px;
margin-left:40px;
  display:block;
  margin-top:20px;
}
.lg_foot{
height:80px;
width:330px;
text-align:center;
 background:url(images_f/5.png) no-repeat; 
margin-left:65px;
margin-top:15px;
}
#picture
{
height:35px;
width:330px;
font-family:Arial, Helvetica, sans-serif;
  text-align:center; 
   font-size:20px;
 line-height:30px;
 margin-top:3px;
 margin-left:40px;
}
.bn{
width:100px;
  height:30px;
  text-align:center;
  display:block; 
  font-size:18px;
  color:#FFF;
  font-family:Arial, Helvetica, sans-serif; 
  font-weight:bolder;}
  .bn2{
width:100px;
  height:30px;
  margin-left:110px;
  display:block; 
  font-size:18px;
  color:#FFF;
   background:url(images_f/10.png) no-repeat; 
  font-family:Arial, Helvetica, sans-serif; 
  font-weight:bolder;}
</style>

<script language="javascript" type="text/javascript">
	function validate(f){
	function validate(f){
		if(!(/^\w{1,12}$/.test(f.userid.value))){
			alert("�û�ID������1~12λ��") ;
			f.userid.focus() ;
			return false ;
		}
		if(!(/^\w{1,12}$/.test(f.userpass.value))){
			alert("���������1~12λ��") ;
			f.userpass.focus() ;
			return false ;
		}
		if(!(/^\w{1,12}$/.test(f.name.value))){
			alert("�û�ID������1~12λ��") ;
			f.name.focus() ;
			return false ;
		}
		if(!(/^\w{1,15}$/.test(f.Numbers.value))){
			alert("�ֻ��ű�����1~15λ��") ;
			f.Numbers.focus() ;
			return false ;
		}

	}
</script>
</head>

<body class="b">
<div class="lg">
<form action="LoginServlet4" method="POST" onSubmit="return validate(this)">
<div class="lg_top"></div>
<div class="lg_main">
<div class="select">
<table>
<tr>
<td>
<div id="select_1" onClick="display1()">
����Աע��
</div>
</td>
</tr>
</table>
</div>

<div id="lg_m_1">
	��¼ID��<input type="text" value="1-12λ" name="userid" onfocus="javascript:if(this.value=='1-12λ')this.value='';"><br>
	��&nbsp;&nbsp;&nbsp;�룺<input type="password" value="1-12λ" name="userpass" onfocus="javascript:if(this.value=='1-12λ')this.value='';"><br>	
	��&nbsp;&nbsp;&nbsp;����<input type="text"   value="1-12λ" name="name" onfocus="javascript:if(this.value=='1-12λ')this.value='';"><br>
	��&nbsp;&nbsp;&nbsp;��<input type="radio" name="Sex" value="��" checked>��
    								<input type="radio" name="Sex" value="Ů" >Ů<br>
	�ֻ��ţ�<input type="text"  value="1-15λ" name="Numbers" onfocus="javascript:if(this.value=='1-15λ')this.value='';"><br>
</div>

<div id="picture">
<center>
<%
	List<String> info = (List<String>) request.getAttribute("info") ;
	if(info != null){	// ����Ϣ����
		Iterator<String> iter = info.iterator() ;
		while(iter.hasNext()){
%>
			<div style="width:300px;height:30px;">
			<h4><%=iter.next()%></h4>
			</div>
			
<%
		}
	}
%>
</center>
</div>
</div>

<div class="lg_foot">
<table><tr>
<td>
<a href="index.jsp" class="bn" style="text-decoration:none;margin-left:40px">����������</a>
</td>
<td>
<a href="login2.jsp" class="bn" style="text-decoration:none">ȥ��¼</a>
</td>
</tr>
<tr>
<td>
<input type="submit" value="������ע��" class="bn2" />
</td>

</tr>
</table>
</div>

</form>
</div>




</body>
</html>
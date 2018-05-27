<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>top</title>
<link rel="stylesheet" href="css/style.css">
<style type="text/css">

#login{
    position: absolute;
	top:100px;
	left:70%;
	}

a{
    padding-top:5px;
    padding-right:20px;
}
.biaozhi{
    position:absolute;
    length:25px;
    width:25px;
    right:110px;
}
a:link{
text-decoration:none;
color:white;
}
a:hover{
  color:red;
  font-weight:bold;
}

a:active{
  color:blue;
}
#picture{
   position:absolute;
   left:20%;
   
}
body{
      background: url(image/back.jpg) no-repeat;
}
.clock{
    position:absolute;
    right:9%;
    top:45%;
}

    
</style>
</head>
<body>
<div id="picture"><img alt="首页头部图片" src="image/top.png"></div>
  <c:if test="${ empty user}">
   <div id="login"><img class="biaozhi" src="image/biaozhi.png" /><a href="group1/index.jsp" target="_parent">登录/注册</a></div>
   </c:if>
   <c:if test="${ !empty user}">
      <div id="login">欢迎您,${user.name }老师<a href="loginout.jsp" >退出</a></div>
   </c:if>
   <div class="clock">
<script type="text/javascript" src='js/react.min.js'></script>
<script type="text/javascript" src='js/react-dom.min.js'></script>
<script type="text/javascript" src='js/polyfill.min.js'></script>
<script type="text/javascript" src="js/index.js"></script>
</div>
</body>
</html>
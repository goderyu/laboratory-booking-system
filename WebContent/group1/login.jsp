<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title></title>
<style>
* {
	padding: 0px;
	margin: 0px;
}

a {
	color: White
}

body {
	font-family: Arial, Helvetica, sans-serif;
	background: url(images_f/1.jpg) no-repeat center;
	font-size: 13px;
}

img {
	border: 0;
}

.lg {
	width: 468px;
	height: 468px;
	margin: 100px auto 10px auto;
	/*background:url(images_f/2.png) no-repeat;*/
}

.lg_top {
	height: 185px;
	width: 468px;
}

.lg_main {
	width: 400px;
	height: 180px;
	margin: 0 25px;
}

.select {
	width: 240px;
	height: 30px;
	margin-left: 75px;
}

#select_1 {
	width: 240px;
	height: 30px;
	font-size: 20px;
	line-height: 30px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: center;
	border: 3px solid #99CC99;
}

#lg_m_1 {
	width: 290px;
	height: 85px;
	padding: 5px 55px 5px 55px;
	display: block;
}

.ur {
	height: 37px;
	border: 0;
	color: #666;
	width: 236px;
	margin: 4px 28px;
	/*background:url(images_f/3.png) no-repeat;*/
	padding-left: 10px;
	font-size: 16pt;
	font-family: Arial, Helvetica, sans-serif;
}

.pw {
	height: 37px;
	border: 0;
	color: #666;
	width: 236px;
	margin: 4px 28px;
	/*background:url(images_f/4.png) no-repeat;*/
	padding-left: 10px;
	font-size: 16pt;
	font-family: Arial, Helvetica, sans-serif;
}

.bn {
	width: 100px;
	height: 30px;
	text-align: center;
	display: block;
	font-size: 18px;
	color: #FFF;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bolder;
}

.bn2 {
	width: 100px;
	height: 30px;
	margin-left: 110px;
	display: block;
	font-size: 18px;
	color: #FFF;
	/*background:url(images_f/10.png) no-repeat; */
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bolder;
}

.lg_foot {
	height: 80px;
	width: 330px;
	text-align: center;
	/*background:url(images_f/5.png) no-repeat;*/
	margin-left: 65px;
}

#picture {
	height: 45px;
	width: 330px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: center;
	font-size: 20px;
	line-height: 30px;
	margin-left: 50px;
}
</style>

<script language="javascript" type="text/javascript">
	function validate(f) {
		if (!(/^\w{1,12}$/.test(f.userid.value))) {
			alert("用户ID必须是1~12位！");
			f.userid.focus();
			return false;
		}
		if (!(/^\w{1,12}$/.test(f.userpass.value))) {
			alert("密码必须是1~12位！");
			f.userpass.focus();
			return false;
		}

	}
</script>
</head>

<body class="b">
	<div class="lg">
		<form action="CheckServlet" method="POST"
			onSubmit="return validate(this)">
			<div class="lg_top"></div>
			<div class="lg_main">
				<div class="select">
					<table>
						<tr>
							<td></td>
						</tr>
					</table>
				</div>

				<div id="lg_m_1">

					<input name="userid" value="Teacher" class="ur"
						onfocus="javascript:if(this.value=='Teacher')this.value='';" /> <input
						name="userpass" type="password" value="Password" class="pw"
						onfocus="javascript:if(this.value=='Password')this.value='';" />

				</div>

				<div id="picture">
					<center>
						<%
							List<String> info = (List<String>) request.getAttribute("info");
							if (info != null) { // 有信息返回
								Iterator<String> iter = info.iterator();
								while (iter.hasNext()) {
						%>
						<div style="width: 300px; height: 30px;">
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
				<table>
					<tr>
						<td><a href="index.jsp" class="bn"
							style="text-decoration: none; margin-left: 40px">返回主界面</a></td>
						<td><a href="register.jsp" class="bn"
							style="text-decoration: none">去注册</a></td>
					</tr>
					<tr>
						<td><input type="submit" value="点这里登录" class="bn2" /></td>

					</tr>
				</table>
			</div>

		</form>
	</div>




</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加实验室信息</title>
<link rel="stylesheet" href="shiyan2.css" type="text/css">
<style type="text/css">
<!--
body {
	background-image: url(/goderyuJavaWeb/group2/image/13.jpg);
}
-->
</style>
<script type="text/javascript">
	function check(f) {
		<c:if test="${ empty user}">
			alert("用户未登陆，请先登录！");
			window.location = "insert.jsp";
			return false;
		</c:if>
		
		
		
		<c:if test="${ user.getCategory() == 0}">
			alert("非管理员不能进行此操作！");
			window.location = "insert.jsp";
			return false;
		</c:if>
		
		if (null == f.id.value || "" == f.id.value) {
			alert("实验室编号不能为空！");
			f.id.focus();
			return false;
		}
		if (null == f.weizhi.value || "" == f.weizhi.value) {
			alert("实验室位置不能为空！");
			f.weizhi.focus();
			return false;
		}
		if (null == f.leixing.value || "" == f.leixing.value) {
			alert("实验室类型不能为空！");
			f.leixing.focus();
			return false;
		}
		if (null == f.renshu.value || "" == f.renshu.value) {
			alert("容纳人数不能为空！");
			f.renshu.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<h2 align="center">添加实验室信息</h2>
	<form name="form1" onSubmit="return check(this)" action="insert_do.jsp"
		method="post">
		<table align="center" width="40%" border="1">
			<tr>
				<th width="100%">实验室编号：</th>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>位置：</th>
				<td><input type="text" name="weizhi"></td>
			</tr>
			<tr>
				<th>实验室类型：</th>
				<td><input type="text" name="leixing"></td>
			</tr>
			<tr>
				<th>可容纳人数：</th>
				<td><input type="text" name="renshu"></td>
			</tr>
			<tr>
				<th>备注：</th>
				<td><textarea cols="25" rows="5" name="beizhu"></textarea></td>
			</tr>

			<tr>
				<th colspan="2"><input type="submit" value="添加"> <input
					type="reset" value="重置"></th>
			</tr>
		</table>
	</form>
</body>
</html>
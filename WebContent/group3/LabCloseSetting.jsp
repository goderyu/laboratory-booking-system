<%@ page import="factory.DAOFactory" %>
<%@ page import="vo.LabName" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">

	




    function selectAll(objectName) {
        var objectNameList = document.getElementsByName(objectName);
        if (null != objectNameList)
            for (var i = 0; i < objectNameList.length; i++)
                objectNameList[i].checked = true;
    }

    function selectNone(objectName) {
        var objectNameList = document.getElementsByName(objectName);
        if (null != objectNameList)
            for (var i = 0; i < objectNameList.length; i++)
                objectNameList[i].checked = false;
    }

    function selectInvert(objectName) {
        var objectNameList = document.getElementsByName(objectName);
        if (null != objectNameList)
            for (var i = 0; i < objectNameList.length; i++)
                objectNameList[i].checked = objectNameList[i].checked !== true;
    }

    function validate(f) {
    	<c:if test="${ empty user}">
			alert("用户未登陆，请先登录！");
			window.location = "LabCloseSetting.jsp";
			return false;
		</c:if>
	

		<c:if test="${ user.getCategory() == 0}">
			alert("非管理员不能进行此操作！");
			window.location = "LabCloseSetting.jsp";
			return false;
		</c:if>
		
        if (f.labName.value === "请选择实验室") {
            alert("未选择实验室");
            return false;
        }
        if (!/^[1-9]\d*$/.test(f.startWeek.value)) {

            alert("开始周格式错误");
            return false;
        }
        if (!(/^[1-9]\d*$/.test(f.endWeek.value))) {
            alert("结束周格式错误");
            return false;
        }
        return true;
    }

    function checkLabName(labName) {
        if (labName === "请选择实验室") {
            document.getElementById("labNameFormat").innerHTML = "未选择实验室";
        } else {
            document.getElementById("labNameFormat").innerHTML = "";
        }

    }

    function checkStartWeek(startWeek) {
        if (!/^[1-9]\d*$/.test(startWeek)) {
            document.getElementById("startWeekFormat").innerHTML = "开始周未正确输入";
        } else {
            document.getElementById("startWeekFormat").innerHTML = "";
        }

    }

    function checkEndWeek(endWeek) {
        if (!/^[1-9]\d*$/.test(endWeek)) {
            document.getElementById("endWeekFormat").innerHTML = "结束周未正确输入";
        } else {
            document.getElementById("endWeekFormat").innerHTML = "";
        }
    }

</script>
<html>
<head>
    <title>实验室关闭时间设置</title>
</head>
<body>
<div style="text-align: center;">
    <h1>实验室关闭时间设置</h1>
    <br>

    <form method="post" action="SubmitServlet" onsubmit="return validate(this)">
        <table align="center">
            <tr>
                <td align="right">实验室名字：</td>
                <td width="250"><label> <select name="labName"
                                                onblur="checkLabName(this.value)">
                    <option selected>请选择实验室</option>
                    <%
                        ArrayList<LabName> labNameList = DAOFactory.getLabNameDAOInstance().selectLabName();
                        for (LabName labName : labNameList) {
                    %>
                    <option value="<%=labName.getLabName()%>"><%=labName.getLabName()%>
                    </option>
                    <%
                        }
                    %>
                </select>
                </label> <span style="color: #ff0000;" id="labNameFormat"></span></td>

            </tr>
            <tr>
                <td align="right">开始周：</td>
                <td><label> <input name="startWeek" type="number"
                                   onblur="checkStartWeek(this.value)">
                </label> <span style="color: #ff0000;" id="startWeekFormat"></span></td>
            </tr>
            <tr>
                <td align="right">结束周：</td>
                <td><label> <input name="endWeek" type="number"
                                   onblur="checkEndWeek(this.value)">
                </label> <span style="color: #ff0000;" id="endWeekFormat"></span></td>
            </tr>
            <tr>
                <td align="right">关闭节数：</td>
                <td><label> <input type="checkbox" name="ke" value="100000"> </label> 上午12节<br>
                    <label> <input type="checkbox" name="ke" value="10000"> </label> 上午34节<br>
                    <label> <input type="checkbox" name="ke" value="1000"> </label> 中午<br>
                    <label> <input type="checkbox" name="ke" value="100"> </label> 下午12节<br>
                    <label> <input type="checkbox" name="ke" value="10"> </label> 下午34节<br>
                    <label> <input type="checkbox" name="ke" value="1" checked="checked"> </label> 晚上<br>
                    <button onclick="selectAll('ke')" type="button">全选</button>
                    <button onclick="selectNone('ke')" type="button">取消全选</button>
                    <button onclick="selectInvert('ke')" type="button">反选</button>
                </td>
            </tr>
            <tr>
                <td align="right">周几：</td>
                <td><label> <input type="checkbox" name="dayOfWeek" value="1000000"> </label> 周一<br>
                    <label> <input type="checkbox" name="dayOfWeek" value="100000"> </label> 周二<br>
                    <label> <input type="checkbox" name="dayOfWeek" value="10000"> </label> 周三<br>
                    <label> <input type="checkbox" name="dayOfWeek" value="1000"> </label> 周四<br>
                    <label> <input type="checkbox" name="dayOfWeek" value="100"> </label> 周五<br>
                    <label> <input type="checkbox" name="dayOfWeek" value="10"> </label> 周六 <br>
                    <label> <input type="checkbox" name="dayOfWeek" value="1" checked="checked"> </label> 周日<br>
                    <input onclick="selectAll('dayOfWeek')" type="button" value="全选">
                    <input onclick="selectNone('dayOfWeek')" type="button" value="取消全选">
                    <input onclick="selectInvert('dayOfWeek')" type="button" value="反选">

                </td>
            </tr>
	        <tr>
	        	<td align="right"></td>
	        	<td>
	        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        		<input type="submit" value="提交">
	        		<input type="reset" value="重置">
                </td>
	        </tr>
        </table>
    </form>
    <%
        List<String> info = (List<String>) request.getAttribute("info");
        if (info != null) {
            for (String s : info) {
    %>
    <h4><%=s%>
    </h4>
    <%
            }
        }
    %>
</div>
</body>
</html>

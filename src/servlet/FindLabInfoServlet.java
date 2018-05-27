package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.DAOFactory;
import vo.FindLabInfo;

/**
* @author 于好贤
* @version 创建时间：2018年5月22日 上午11:41:16
* @description 
*/

@WebServlet(name = "FindLabInfoServlet", urlPatterns = "/group6/FindLabInfoServlet")
public class FindLabInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String id1 = request.getParameter("id");
		String tname1 = request.getParameter("tname");
		String sweek1 = request.getParameter("sweek");
		String eweek1 = request.getParameter("eweek");
		String day1 = request.getParameter("day");
		String time1 = request.getParameter("time");
		String sql = "SELECT 实验室编号,教师编号,起始周,结束周,周几,节次 FROM 实验室预约记录表 WHERE 1 = 1 {criteria}";
		String condition = "";
		if (id1 != null && !id1.equals("-")) {
			condition += "AND 实验室编号 =" + id1 + " ";
		}
		if (tname1 != null && !tname1.equals("-")) {
			if (tname1.equals("王高平")) {
				condition += "AND 教师编号 =" + 10001 + " ";
			}
			if (tname1.equals("阎娟")) {
				condition += "AND 教师编号 =" + 10002 + " ";
			}
			if (tname1.equals("白浩")) {
				condition += "AND 教师编号 =" + 10003 + " ";
			}
			if (tname1.equals("唐建国")) {
				condition += "AND 教师编号 =" + 10004 + " ";
			}
			if (tname1.equals("侯惠芳")) {
				condition += "AND 教师编号 =" + 10005 + " ";
			}
			if (tname1.equals("孙宜贵")) {
				condition += "AND 教师编号 =" + 10006 + " ";
			}
			if (tname1.equals("于俊伟")) {
				condition += "AND 教师编号 =" + 10007 + " ";
			}
			if (tname1.equals("张继新")) {
				condition += "AND 教师编号 =" + 10008 + " ";
			}
			if (tname1.equals("陈亮")) {
				condition += "AND 教师编号 =" + 10009 + " ";
			}
			if (tname1.equals("朱正")) {
				condition += "AND 教师编号 =" + 10010 + " ";
			}
			if (tname1.equals("王赞民")) {
				condition += "AND 教师编号 =" + 10011 + " ";
			}
			/* condition+="AND 教师编号 like '%'||(select 教师编号 from 实验室预约记录表 where 教师编号 in (select 教师编号 from 教师信息表 where 教师姓名='tname1'))||'%'";*/

		}
		if (sweek1 != null && !sweek1.equals("-")) {
			if (sweek1.equals("1")) {
				condition += "AND 起始周 =1" + " ";
			}
			if (sweek1.equals("2")) {
				condition += "AND 起始周 =2" + " ";
			}
			if (sweek1.equals("3")) {
				condition += "AND 起始周 =3" + " ";
			}
			if (sweek1.equals("4")) {
				condition += "AND 起始周 =5" + " ";
			}
			if (sweek1.equals("6")) {
				condition += "AND 起始周 =6" + " ";
			}
			if (sweek1.equals("7")) {
				condition += "AND 起始周 =7" + " ";
			}
			if (sweek1.equals("8")) {
				condition += "AND 起始周 =8" + " ";
			}
			if (sweek1.equals("9")) {
				condition += "AND 起始周 =9" + " ";
			}
		}
		if (eweek1 != null && !eweek1.equals("-")) {
			if (eweek1.equals("1")) {
				condition += "AND 结束周 =1" + " ";
			}
			if (eweek1.equals("2")) {
				condition += "AND 结束周 =2" + " ";
			}
			if (eweek1.equals("3")) {
				condition += "AND 结束周 =3" + " ";
			}
			if (eweek1.equals("4")) {
				condition += "AND 结束周 =4" + " ";
			}
			if (eweek1.equals("5")) {
				condition += "AND 结束周 =5" + " ";
			}
			if (eweek1.equals("6")) {
				condition += "AND 结束周 =6" + " ";
			}
			if (eweek1.equals("7")) {
				condition += "AND 结束周 =7" + " ";
			}
			if (eweek1.equals("8")) {
				condition += "AND 结束周 =8" + " ";
			}
			if (eweek1.equals("9")) {
				condition += "AND 结束周 =9" + " ";
			}
		}
		if (day1 != null && !day1.equals("-")) {
			condition += "AND 周几 like '%" + day1 + "%'" + " ";
		}

		if (time1 != null && !time1.equals("-")) {
			if (time1.equals("上午1，2节")) {
				condition += "AND 节次 like '%" + "1" + "%'";
			}
			if (time1.equals("上午3，4节")) {
				condition += "AND 节次 like '%" + "2" + "%'";
			}
			if (time1.equals("中午1，2节")) {
				condition += "AND 节次 like '%" + "3" + "%'";
			}
			if (time1.equals("下午5，6节")) {
				condition += "AND 节次 like '%" + "4" + "%'";
			}
			if (time1.equals("下午7，8节")) {
				condition += "AND 节次 like '%" + "5" + "%'";
			}
			if (time1.equals("晚上9，10节")) {
				condition += "AND 节次 like '%" + "6" + "%'";
			}
		}
		sql = sql.replace("{criteria}", condition);
		List<FindLabInfo> info = null;

		try {
			info = DAOFactory.getISearchDAOInstance().find(sql);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for (FindLabInfo findLabInfo : info) {
			System.out.println("123" + findLabInfo);
		}
		Iterator<FindLabInfo> iter = info.iterator();
		while (!iter.hasNext()) {
			out.print("<script type='text/javascript'>");
			out.print("alert('无查询结果！');");
			out.print("window.location.href='find.jsp';");
			out.print("</script>");
		}
		request.setAttribute("result", info);
		request.getRequestDispatcher("find.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
}

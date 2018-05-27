package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.InputTeacherInfoImpl;
import excel.Excel;
import vo.Teacher;

public class Tomysqlservlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tomysqlservlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InputTeacherInfoImpl in = null;
		try {
			in = new InputTeacherInfoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Excel ex = new Excel();
		String path1 = request.getParameter("file");
		System.out.println(path1);
		String path = new String(path1.getBytes("ISO-8859-1"), "gb2312");
		System.out.println(path);

		File file = new File(path);
		List<Teacher> ls = new ArrayList<Teacher>();
		ls = ex.addCustomerAssign(file);
		List<String> info = new ArrayList<String>();
		List<String> info1 = new ArrayList<String>();
		List<String> info2 = new ArrayList<String>();
		List<String> info3 = new ArrayList<String>();
		boolean flag = false;
		Iterator<Teacher> iter = ls.iterator();
		while (iter.hasNext()) {
			Teacher ms = (Teacher) iter.next();
			if (!in.findTea(ms)) {
				if (in.insertexcel(ms)) {
					info.add(ms.getNumber());
					info1.add(ms.getName());
					info2.add(ms.getPhone());
					System.out.println("成功");
					flag = true;
				}
			} else
				System.out.println("失败");

		}

		if (flag) {
			info3.add("导入成功！");
		} else
			info3.add("导入失败！");
		request.setAttribute("info", info);
		request.setAttribute("info1", info1);
		request.setAttribute("info2", info2);
		request.setAttribute("info3", info3);
		request.getRequestDispatcher("tomysql.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

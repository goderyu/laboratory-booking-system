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

import excel.InputCourseInfoFromExcel;
import factory.DAOFactory;
import vo.CourseInfo;

public class ImportServlet extends HttpServlet {

	private static final long serialVersionUID = 8312352600418259971L;

	public ImportServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputCourseInfoFromExcel ex = new InputCourseInfoFromExcel();
		String path1 = request.getParameter("excel");
		String path = new String(path1.getBytes("ISO-8859-1"), "gb2312");
		System.out.println(path);
		File file = new File(path);
		List<?> ls = ex.addCustomerAssign(file);
		List<String> info = new ArrayList<String>();
		boolean flag = false;
		Iterator<?> iter = ls.iterator();
		while (iter.hasNext()) {
			CourseInfo ms = (CourseInfo) iter.next();
			try {
				if (!DAOFactory.getIInputCourseInfoDAOInstance().findTea(ms)) {
					if (DAOFactory.getIInputCourseInfoDAOInstance().insertExcel(ms))
						/*info.add("数据导入成功，请前往数据库查看");*/
						System.out.println("成功了");
					flag = true;

				} else
					System.out.println("失败");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (flag) {
			info.add("数据导入成功");
		} else
			info.add("数据导入失败");
		request.setAttribute("info", info);
		request.getRequestDispatcher("import.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

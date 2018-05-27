package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.RegisterDaoImpl;
import vo.UserInfo;

@WebServlet("/ValdateRegistration")
public class ValdateRegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = -5608610159742355371L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user_id = request.getParameter("user_id");
		String uid_tips = "";

		RegisterDaoImpl register = null;
		try {
			register = new RegisterDaoImpl();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			UserInfo user = null;
			if (register.findUserId(user_id) == true) {

				uid_tips = "<font color='red'>用户名已存在</font>";
				response.setContentType("text/html");
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");

				response.getWriter().println(uid_tips);

				return;
			} else {

				uid_tips = "<font color='green'>用户名可用</font>";

				response.setContentType("text/html");
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().println(uid_tips);

				String password = request.getParameter("password");
				
				if(null != password && !"".equals(password)) {
					user = new UserInfo();
					user.setNumber(user_id);
					user.setPassword(password);

					if (register.doCreate(user))
						response.sendRedirect(request.getContextPath() + "/group1/login.jsp");
				}
				//				System.out.println(user_id);
				//				System.out.println(password);
				//				System.out.println(email);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

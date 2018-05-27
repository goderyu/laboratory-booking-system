package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.impl.CheckDaoImpl;
import vo.UserInfo;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession();
		 String number=request.getParameter("userid");
		 String password=request.getParameter("userpass");
		 CheckDaoImpl check=new CheckDaoImpl();
		 UserInfo user=null;
   	  List<String> info=new ArrayList<>();

		try {
			user = check.findLogin(number, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 if(user.getNumber()!=null){
			 session.setAttribute("user", user);
			 response.sendRedirect(request.getContextPath());

		 }
		 else{
			 info.add("用户名或密码错误");
			 request.setAttribute("info", info);
			 request.getRequestDispatcher("/group1/login.jsp").forward(request, response);
		 }
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

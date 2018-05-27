package servlet;

import factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 李浩 on 2018/5/15
 */
@WebServlet(name = "AddServlet" ,urlPatterns = "/group4/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String teacherName = request.getParameter("teacherName");
        String laboratoryID = request.getParameter("laboratoryID");
        String courseID = request.getParameter("course");
        int whichDay = Integer.parseInt(request.getParameter("whichDay"));
        int section = Integer.parseInt(request.getParameter("whichSection"));
        int startweek = Integer.parseInt(request.getParameter("startweek"));
        int endweek = Integer.parseInt(request.getParameter("endweek"));
        try {
            if(DAOFactory.getIResDAOInstance().banJudge(laboratoryID,section,whichDay,startweek,endweek)){
                if(DAOFactory.getIResDAOInstance().insertJudge(laboratoryID,section,whichDay,startweek,endweek)){
                    if(DAOFactory.getIResDAOInstance().insertRes(teacherName,laboratoryID,courseID,whichDay,section,startweek,endweek)){
                        out.print("<script type='text/javascript'>");
                        out.print("alert('预约成功！');");
                        out.print("window.location.href='AddClass.jsp';");
                        out.print("</script>");
                        //out.flush();
                        //response.sendRedirect("/LaboratoryAppointment/AddClass.jsp");//可加参数
                    }else{
                        out.print("<script type='text/javascript'>");
                        out.print("alert('插入失败！');");
                        out.print("window.location.href='AddClass.jsp';");
                        out.print("</script>");

                    }
                }else{
                    out.print("<script type='text/javascript'>");
                    out.print("alert('预约时间冲突！');");
                    out.print("window.location.href='AddClass.jsp';");
                    out.print("</script>");
                }
            }else{
                out.print("<script type='text/javascript'>");
                out.print("alert('包含实验实关闭时间！');");
                out.print("window.location.href='AddClass.jsp';");
                out.print("</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

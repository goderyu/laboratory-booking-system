package servlet;

import factory.DAOFactory;
import vo.LabCloseSetting;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SubmitServlet", value = "/SubmitServlet")
public class SubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String labName = new String(request.getParameter("labName").getBytes("iso-8859-1"), "utf-8");
        Integer startWeek = Integer.valueOf(request.getParameter("startWeek"));
        Integer endWeek = Integer.valueOf(request.getParameter("endWeek"));
        String[] ke = request.getParameterValues("ke");
        int keSum = 0;
        for (String k : ke) {
            keSum += Integer.valueOf(k);
        }
        String[] dayOfWeek = request.getParameterValues("dayOfWeek");
        int dayOfWeekSum = 0;
        for (String d : dayOfWeek) {
            dayOfWeekSum += Integer.valueOf(d);
        }
        LabCloseSetting labCloseSetting = new LabCloseSetting();
        List<String> info = new ArrayList<>();
        if (info.size() == 0) {
            labCloseSetting.setLabName(labName);
            labCloseSetting.setStartWeek(startWeek);
            labCloseSetting.setEndWeek(endWeek);
            labCloseSetting.setKeSum(keSum);
            labCloseSetting.setDayOfWeekSum(dayOfWeekSum);
            if (DAOFactory.getLabCloseSettingDAOInstance().insertLabCloseSetting(labCloseSetting)) {
                info.add("设置成功");
            } else {
                info.add("设置失败");
            }
        }
        request.setAttribute("info", info);
        request.getRequestDispatcher("LabCloseSetting.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}

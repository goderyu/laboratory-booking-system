package excel;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@WebServlet(name = "Toexcel")
public class Toexcel extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DBtoexcel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("message", "导出成功");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void WriteExcel(ResultSet rs, String filePath, String sheetName, Vector<String> columnName) {
		WritableWorkbook workbook = null;
		WritableSheet sheet = null;

		int rowNum = 1; // 从第一行开始写入
		try {
			workbook = Workbook.createWorkbook(new File(filePath)); // 创建Excel文件
			sheet = workbook.createSheet(sheetName, 0); // 创建名为 sheetName 的工作簿

			this.writeCol(sheet, columnName, 0); // 首先将列名写入
			// 将结果集写入
			while (rs.next()) {
				Vector col = new Vector(); // 用以保存一行数据

				for (int i = 1; i <= columnName.size(); i++) { // 将一行内容保存在col中
					col.add(rs.getString(i));
				}
				// 写入Excel
				this.writeCol(sheet, col, rowNum++);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭
				workbook.write();
				workbook.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/***
	 * 将数组写入工作簿
	 * @param sheet 要写入的工作簿
	 * @param col 要写入的数据数组
	 * @param rowNum 要写入哪一行
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void writeCol(WritableSheet sheet, Vector<String> col, int rowNum)
			throws RowsExceededException, WriteException {
		int size = col.size(); // 获取集合大小

		for (int i = 0; i < size; i++) { // 写入每一列
			Label label = new Label(i, rowNum, col.get(i));
			sheet.addCell(label);
		}
	}

	public void DBtoexcel() throws Exception {
		String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String URL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=实验室安排预约系统;";
		String USERNAME = "sa";
		String USERPASSWORD = "123456";

		String sql = "Select * from 实验室预约记录表"; // 查询语句
		Vector<String> columnName = new Vector<String>(); // 列名
		columnName.add("预约编号");
		columnName.add("课程编号");
		columnName.add("实验室编号");
		columnName.add("教师编号");
		columnName.add("起始周");
		columnName.add("结束周");
		columnName.add("周几");
		columnName.add("节次");

		// 连接数据库
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		// 导出文件
		new Toexcel().WriteExcel(rs, "C:\\Users\\Administrator\\Desktop\\test\\课表.xls", "课程表", columnName);
	}//这里可以更改输出的文件路径
}

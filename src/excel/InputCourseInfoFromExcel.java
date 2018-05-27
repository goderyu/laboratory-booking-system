package excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import vo.CourseInfo;

public class InputCourseInfoFromExcel {
	public List<CourseInfo> addCustomerAssign(File file) {
		List<CourseInfo> ls = new ArrayList<CourseInfo>();
		Workbook rwb;
		try {
			rwb = Workbook.getWorkbook(file);
			Sheet rs = rwb.getSheet(0);

			int rsRows = rs.getRows();
			System.out.println(rsRows);
			for (int i = 1; i < rsRows; i++) {
				String cell = rs.getCell(0, i).getContents() + " ";
				if (cell != null && !cell.equals(" "))//判断当前行是否为有效行   是插入否找下行
				{
					CourseInfo ms = new CourseInfo();
					ms.setPk_Id(rs.getCell(0, i).getContents());
					ms.setSub_Id(rs.getCell(1, i).getContents());
					ms.setRoom_Id(rs.getCell(2, i).getContents());
					ms.setSub_Name(rs.getCell(3, i).getContents());
					ms.setClass_Id(rs.getCell(4, i).getContents());
					ms.setTea_Id(rs.getCell(5, i).getContents());
					ms.setSweek(Integer.parseInt(rs.getCell(6, i).getContents().trim()));
					ms.setEweek(Integer.parseInt(rs.getCell(7, i).getContents().trim()));
					ms.setDay(Integer.parseInt(rs.getCell(8, i).getContents().trim()));
					ms.setPart(Integer.parseInt(rs.getCell(9, i).getContents().trim()));
					ls.add(ms);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ls;
	}
}

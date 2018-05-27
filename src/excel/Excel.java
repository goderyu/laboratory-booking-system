package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import vo.Teacher;

// 
public class Excel {
	public List<Teacher> addCustomerAssign(File file)//添加客服中心数据
	{
		List<Teacher> ls = new ArrayList<Teacher>();
		jxl.Workbook rwb = null;
		try {
			//构建Workbook对象,   只读Workbook对象
			//直接从本地文件创建Workbook
			//从输入流创建Workbook
			InputStream is = new FileInputStream(file);
			rwb = Workbook.getWorkbook(is);
			// String   createTime   =   DateUtil.getDateTime( "yyyy-MM-dd   HH:mm ",new   Date()).toString();
			//Sheet(术语：工作表)就是Excel表格左下角的Sheet1,Sheet2,Sheet3但在程序中
			//Sheet的下标是从0开始
			//获取第一张Sheet表
			Sheet rs = rwb.getSheet(0);
			//获取Sheet表中所包含的总列数
			//   int   rsColumns   =   rs.getColumns();
			//获取Sheet表中所包含的总行数
			int rsRows = rs.getRows();
			//获取指定单元格的对象引用
			//   rs.getCell(列,行);
			for (int i = 1; i < rsRows; i++) {//如第一行为属性项则从第二行开始取数据(int   i=0   ;i <rsRows;i++)
				//for(int   j=0;j <rsColumns;j++){
				//Cell   cell   =   rs.getCell(j,i);
				//   System.out.print(cell.getContents()+ " ");
				//   }
				String cell = rs.getCell(0, i).getContents() + " ";
				//String   cell1=   rs.getCell(2,i).getContents()+ " ";//教师联系方式
				if (cell != null && !cell.equals(" "))//判断当前行是否为有效行   是插入否找下行
				{
					Teacher ms = new Teacher();
					ms.setNumber(rs.getCell(0, i).getContents());//教师编号
					ms.setName(rs.getCell(1, i).getContents());//教师姓名
					ms.setFaculty(rs.getCell(2, i).getContents());//系别
					ms.setPhone(rs.getCell(3, i).getContents());//联系方式
					ls.add(ms);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ls;
	}

	public void importXlsx(InputStream inputStream) {
		// TODO Auto-generated method stub

	}
}

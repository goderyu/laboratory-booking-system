package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.IInputCourseInfoDao;
import vo.CourseInfo;

/**
* @author 于好贤
* @version 创建时间：2018年5月19日 上午10:30:18
* @description 实现接口IInputCourseInfoDao的主题
*/
public class InputCourseInfoDaoImpl implements IInputCourseInfoDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public InputCourseInfoDaoImpl(Connection conn) {//通过构造方法取得数据库连接
		this.conn = conn;//取得数据库连接
	}

	@Override
	public boolean insertExcel(CourseInfo ms) throws Exception {
		boolean flag = false;//定义标志位
		String sql = "insert into 班级课程表(排课编号,课程编号,教室编号,课程名称,班级编号,教师编号,起始周,结束周,周几,节次) values(?,?,?,?,?,?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);//实例化prepareStatement对象
		this.pstmt.setString(1, ms.getPk_Id());
		this.pstmt.setString(2, ms.getSub_Id());
		this.pstmt.setString(3, ms.getRoom_Id());
		this.pstmt.setString(4, ms.getSub_Name());
		this.pstmt.setString(5, ms.getClass_Id());
		this.pstmt.setString(6, ms.getTea_Id());
		this.pstmt.setInt(7, ms.getSweek());
		this.pstmt.setInt(8, ms.getEweek());
		this.pstmt.setInt(9, ms.getDay());
		this.pstmt.setInt(10, ms.getpart());
		if (this.pstmt.executeUpdate() > 0) {//更新记录的行数大于0
			flag = true;//修改标志位
		}
		this.pstmt.close();//关闭prepareStatement操作
		return flag;
	}

	@Override
	public boolean findTea(CourseInfo ms) throws Exception {
		boolean flag = false;//定义标志位
		String sql = "select 排课编号 from 班级课程表 where 排课编号=?";
		this.pstmt = this.conn.prepareStatement(sql);//实例化prepareStatement对象
		this.pstmt.setString(1, ms.getPk_Id());
		ResultSet rs = this.pstmt.executeQuery();//执行查询操作
		if (rs.next()) {
			flag = true;
		}
		this.pstmt.close();//关闭prepareStatement操作
		return flag;
	}

}

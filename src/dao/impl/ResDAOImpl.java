package dao.impl;
/**
* @author 李浩
* 类说明
*/

import java.util.ArrayList;
import java.util.List;

import dao.IResDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.ReserveExperiment;
import vo.labBan;
import vo.laboratory;
import vo.teacherCourse;

public class ResDAOImpl implements IResDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public ResDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public List<ReserveExperiment> find(String laboratoryID, int week, String teacherID) throws Exception {
		List<ReserveExperiment> all = new ArrayList<ReserveExperiment>();
		String sql = null;
		if (teacherID == null)
			sql = "select 教师编号,课程编号,周几,节次,实验室编号,预约编号 from 实验室预约记录表 " + "where 实验室编号 = ? and 起始周<=? and 结束周>=?";
		else
			sql = "select 教师编号,课程编号,周几,节次,实验室编号,预约编号 from 实验室预约记录表 "
					+ " where 实验室编号 = ? and 起始周<=? and 结束周>=? and 教师编号=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, laboratoryID);
		this.pstmt.setInt(2, week);
		this.pstmt.setInt(3, week);
		if (teacherID != null)
			this.pstmt.setString(4, teacherID);
		ResultSet rs = this.pstmt.executeQuery();
		ReserveExperiment reserveExperiment = null;
		while (rs.next()) {
			reserveExperiment = new ReserveExperiment();
			reserveExperiment.setTeacherName(rs.getString(1));
			reserveExperiment.setCourseID(rs.getString(2));
			reserveExperiment.setWhichDay(rs.getInt(3));
			reserveExperiment.setSection(rs.getInt(4));
			reserveExperiment.setLaboratoryID(rs.getString(5));
			reserveExperiment.setOrderID(rs.getInt(6));
			all.add(reserveExperiment);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public List<laboratory> findAllLab() throws Exception {
		// TODO 自动生成的方法存根
		List<laboratory> all = new ArrayList<laboratory>();
		String sql = "select 实验室编号 from 实验室信息表";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		laboratory lab = null;
		while (rs.next()) {
			lab = new laboratory();
			lab.setLaboratoryID(rs.getString(1));
			all.add(lab);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public boolean insertJudge(String laboratoryID, int section, int whichday, int startweek, int endweeek)
			throws Exception {
		boolean flag = false;
		String sql = "select * from 实验室预约记录表 where 实验室编号=? and 节次=? and 周几=? and 起始周<=? and 结束周>=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, laboratoryID);
		this.pstmt.setInt(2, section);
		this.pstmt.setInt(3, whichday);
		this.pstmt.setInt(4, endweeek);
		this.pstmt.setInt(5, startweek);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean banJudge(String laboratoryID, int section, int whichday, int startweek, int endweeek)
			throws Exception {
		boolean flag = false;
		String sql = "select * from 实验室关闭时间表 where 实验室编号=? and (课%POWER(10,7-?))/POWER(10,6-?)=1 and (周几%POWER(10,8-?))/POWER(10,7-?)=1 and 开始周<=? and 结束周>=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, laboratoryID);
		this.pstmt.setInt(2, section);
		this.pstmt.setInt(3, section);
		this.pstmt.setInt(4, whichday);
		this.pstmt.setInt(5, whichday);
		this.pstmt.setInt(6, endweeek);
		this.pstmt.setInt(7, startweek);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<labBan> findLabBan(String laboratoryID, int week) throws Exception {
		String sql = "select 实验室编号,开始周,结束周,周几,课 from 实验室关闭时间表 where 实验室编号 = ? and 开始周<=? and 结束周>=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, laboratoryID);
		this.pstmt.setInt(2, week);
		this.pstmt.setInt(3, week);
		ResultSet rs = this.pstmt.executeQuery();
		List<labBan> all = new ArrayList<labBan>();
		labBan lab = null;
		while (rs.next()) {
			lab = new labBan();
			lab.setLaboratoryID(rs.getString(1));
			lab.setStartweek(rs.getInt(2));
			lab.setEndweek(rs.getInt(3));
			lab.setWhichday(rs.getInt(4));
			lab.setSection(rs.getInt(5));
			all.add(lab);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public List<teacherCourse> findByteacherID(String teacherID) throws Exception {
		List<teacherCourse> all = new ArrayList<teacherCourse>();
		String sql = "select distinct 课程编号,课程名称 from 班级课程表 where 教师编号=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, teacherID);
		ResultSet rs = this.pstmt.executeQuery();
		teacherCourse tc = null;
		while (rs.next()) {
			tc = new teacherCourse();
			tc.setTeacherID(teacherID);
			tc.setCourseID(rs.getString(1));
			tc.setCourseName(rs.getString(2));
			all.add(tc);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public boolean insertRes(String teacherID, String laboratoryID, String courseID, int whichDay, int section,
			int startweek, int endweek) throws Exception {
		boolean flag = false;
		String sql = "insert into 实验室预约记录表(教师编号,实验室编号,课程编号,周几,节次,起始周,结束周)values (?,?,?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, teacherID);
		this.pstmt.setString(2, laboratoryID);
		this.pstmt.setString(3, courseID);
		this.pstmt.setInt(4, whichDay);
		this.pstmt.setInt(5, section);
		this.pstmt.setInt(6, startweek);
		this.pstmt.setInt(7, endweek);
		if (this.pstmt.executeUpdate() != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Integer deleteOrder(int orderID, int week) throws Exception {
		int k1, k2, k3;
		String sql = "select 教师编号,实验室编号,课程编号,周几,节次,起始周,结束周 " + "from 实验室预约记录表  where 预约编号=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, orderID);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			String teacherID = rs.getString(1);
			String laboratoryID = rs.getString(2);
			String courseID = rs.getString(3);
			int whichDay = rs.getInt(4);
			int section = rs.getInt(5);
			int startweek = rs.getInt(6);
			int endweek = rs.getInt(7);
			if (week >= startweek && week <= endweek) {
				if (startweek <= week - 1) {
					String sql2 = "insert into 实验室预约记录表(教师编号,实验室编号,课程编号,周几,节次,起始周,结束周) " + "values(?,?,?,?,?,?,?)";
					this.pstmt = this.conn.prepareStatement(sql2);
					this.pstmt.setString(1, teacherID);
					this.pstmt.setString(2, laboratoryID);
					this.pstmt.setString(3, courseID);
					this.pstmt.setInt(4, whichDay);
					this.pstmt.setInt(5, section);
					this.pstmt.setInt(6, startweek);
					this.pstmt.setInt(7, week - 1);
					k1 = this.pstmt.executeUpdate();
				} else {
					k1 = 1;
				}
				if (k1 == 1) {
					if (endweek >= week + 1) {
						String sql3 = "insert into 实验室预约记录表(教师编号,实验室编号,课程编号,周几,节次,起始周,结束周) " + "values(?,?,?,?,?,?,?)";
						this.pstmt = this.conn.prepareStatement(sql3);
						this.pstmt.setString(1, teacherID);
						this.pstmt.setString(2, laboratoryID);
						this.pstmt.setString(3, courseID);
						this.pstmt.setInt(4, whichDay);
						this.pstmt.setInt(5, section);
						this.pstmt.setInt(6, week + 1);
						this.pstmt.setInt(7, endweek);
						k2 = this.pstmt.executeUpdate();
					} else {
						k2 = 1;
					}
				} else {
					k2 = 0;
				}
				if (k2 == 1) {
					String sql4 = "delete from 实验室预约记录表  where 预约编号=?";
					this.pstmt = this.conn.prepareStatement(sql4);
					this.pstmt.setInt(1, orderID);
					k3 = this.pstmt.executeUpdate();
					if (k3 == 1) {
						this.pstmt.close();
						return k3;
					} else
						return 0;
				} else
					return 0;
			} else
				return 0;
		} else
			return 0;
	}

	public List<teacherCourse> findCourseName(String courseID) throws Exception {
		List<teacherCourse> all = new ArrayList<teacherCourse>();
		String sql = "select 课程名称 from 班级课程表 where 课程编号=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, courseID);
		ResultSet rs = this.pstmt.executeQuery();
		teacherCourse cs = null;
		while (rs.next()) {
			cs = new teacherCourse();
			cs.setCourseID(courseID);
			cs.setCourseName(rs.getString(1));
			all.add(cs);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public List<ReserveExperiment> searchOrder(int orderID, int week) throws Exception {
		List<ReserveExperiment> all = new ArrayList<ReserveExperiment>();
		String sql = "select 实验室编号,课程编号,周几,节次,教师编号 " + "from 实验室预约记录表  where 预约编号=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, orderID);
		ResultSet rs = this.pstmt.executeQuery();
		ReserveExperiment reserveExperiment = null;
		if (rs.next()) {
			reserveExperiment = new ReserveExperiment();
			reserveExperiment.setLaboratoryID(rs.getString(1));
			reserveExperiment.setCourseID(rs.getString(2));
			reserveExperiment.setWhichDay(rs.getInt(3));
			reserveExperiment.setSection(rs.getInt(4));
			reserveExperiment.setTeacherName(rs.getString(5));
			reserveExperiment.setStartweek(week);
			reserveExperiment.setEndweek(week);
			all.add(reserveExperiment);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public List<teacherCourse> findClassName(String ClassID) throws Exception {
		List<teacherCourse> all = new ArrayList<teacherCourse>();
		String sql = "select distinct 课程名称 from 班级课程表  where 课程编号=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, ClassID);
		ResultSet rs = this.pstmt.executeQuery();
		teacherCourse classmassage = null;
		while (rs.next()) {
			classmassage = new teacherCourse();
			classmassage.setCourseName(rs.getString(1));
			all.add(classmassage);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public List<ReserveExperiment> findMassage(String ClassID) throws Exception {
		List<ReserveExperiment> all = new ArrayList<ReserveExperiment>();
		String sql = "select 课程编号,教师编号,实验室编号,起始周,结束周,节次,周几 from 实验室预约记录表 where 课程编号=? order by 起始周";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, ClassID);
		ResultSet rs = this.pstmt.executeQuery();
		ReserveExperiment reserveExperiment = null;
		while (rs.next()) {
			reserveExperiment = new ReserveExperiment();
			reserveExperiment.setCourseID(rs.getString(1));
			reserveExperiment.setTeacherName(rs.getString(2));
			reserveExperiment.setLaboratoryID(rs.getString(3));
			reserveExperiment.setStartweek(rs.getInt(4));
			reserveExperiment.setEndweek(rs.getInt(5));
			reserveExperiment.setSection(rs.getInt(6));
			reserveExperiment.setWhichDay(rs.getInt(7));
			all.add(reserveExperiment);
		}
		this.pstmt.close();
		return all;
	}

	public String findTeacherName(String number) throws Exception {
		// TODO Auto-generated method stub
		String name = null;
		String sql = "select 教师姓名  from 教师信息表 where 教师编号 = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, number);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			name = rs.getString(1);

		}
		return name;
	}

}

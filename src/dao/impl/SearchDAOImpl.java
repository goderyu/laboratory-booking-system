package dao.impl;
/**
* @author 于好贤
* @version 创建时间：2018年5月22日 上午11:47:34
* @description 
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.ISearchDao;
import vo.FindLabInfo;

public class SearchDAOImpl implements ISearchDao {
	private Connection conn;
	private PreparedStatement pstmt;

	public SearchDAOImpl(Connection conn) throws Exception {
		this.conn = conn;//取得数据库连接
	}

	//查询实验室编号
	public List<String> number() throws Exception {
		String sql = "select 实验室编号 from 实验室信息表  ";
		this.pstmt = conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		List<String> list = new ArrayList<>();
		while (rs.next()) {
			list.add(rs.getString(1));
		}
		this.pstmt.close();
		return list;
	}

	// 查询教师姓名
	public List<String> teacherName() throws Exception {
		String sql = "select 教师姓名 from 教师信息表  ";
		this.pstmt = conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		List<String> list = new ArrayList<>();
		while (rs.next()) {
			list.add(rs.getString(1));
		}
		this.pstmt.close();
		return list;
	}

	@Override
	public List<FindLabInfo> find(String sql) throws Exception {
		this.pstmt = conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		List<FindLabInfo> list = new ArrayList<>();
		while (rs.next()) {
			FindLabInfo info = new FindLabInfo();
			info.setLabID(rs.getString(1));
			info.setTeaID(rs.getString(2));
			info.setSweek(rs.getInt(3));
			info.setEweek(rs.getInt(4));
			info.setDay(rs.getInt(5));
			info.setSection(rs.getInt(6));
			list.add(info);
		}
		this.pstmt.close();
		return list;
	}
}

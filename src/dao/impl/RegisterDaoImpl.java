package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbc.DatabaseConnection;
import factory.DatabaseConnectionFactory;
import dao.IRegisterDao;
import vo.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterDaoImpl implements IRegisterDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private DatabaseConnection dbc;

	public RegisterDaoImpl() throws Exception {
		dbc = DatabaseConnectionFactory.getDatabaseConnection();
		this.conn = dbc.getConnection();
	}

	@Override
	public boolean doCreate(UserInfo user) throws SQLException {

		String sql = "INSERT INTO 用户登录信息表(用户编号,用户密码,用户类别) VALUES(?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getNumber());
		pstmt.setString(2, user.getPassword());
		pstmt.setInt(3,0);
		boolean flag = false;
		int row = pstmt.executeUpdate();
		if (row > 0) {
			flag = true;
		}
		pstmt.close();
		return flag;

	}

	@Override
	public boolean findLogin(UserInfo vo) throws Exception {
		boolean flag = false;
		// TODO Auto-generated method stub
		String sql = "SELECT 用户编号 FROM 用户登录信息表 WHERE 用户编号=? AND 用户密码=?";
		this.pstmt = conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getNumber());
		this.pstmt.setString(2, vo.getPassword());

		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			flag = true;
		}

		rs.close();
		pstmt.close();

		return flag;
	}

	@Override
	public boolean findUserId(String user_id) throws Exception {

		boolean flag = false;
		String sql = "SELECT 用户编号 FROM 用户登录信息表 WHERE 用户编号=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_id);
		ResultSet rs = this.pstmt.executeQuery();

		if (rs.next()) {
			flag = true;
		}

		rs.close();
		pstmt.close();

		return flag;
	}

}

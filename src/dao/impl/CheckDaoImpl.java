package dao.impl;

import dbc.DatabaseConnection;
import dao.ICheckDao;
import vo.UserInfo;

import factory.DatabaseConnectionFactory;
import factory.DAOFactory;
import java.sql.*;

public class CheckDaoImpl implements ICheckDao{
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	 
	public CheckDaoImpl(){
		
	}
	public UserInfo findLogin(String username,String password) throws Exception{
		DatabaseConnection dbc=DatabaseConnectionFactory.getDatabaseConnection();
		this.conn=dbc.getConnection();
		String sql = "SELECT * FROM 用户登录信息表 WHERE 用户编号=? and 用户密码=?" ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		this.pstmt.setString(1,username) ;
		this.pstmt.setString(2,password) ;
		ResultSet rs = this.pstmt.executeQuery() ;
		UserInfo user= new UserInfo();
		
		if(rs.next()){
			user.setNumber(username);
			user.setPassword(password);
			user.setCategory(rs.getInt(3));
			user.setName(DAOFactory.getIResDAOInstance().findTeacherName(username));
		}
		System.out.println(user);
		this.pstmt.close() ;
		dbc.close();
		return user;

	}
}
	
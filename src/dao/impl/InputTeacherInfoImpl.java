package dao.impl;


import dbc.DatabaseConnection;
import factory.DatabaseConnectionFactory;
import dao.IInputTeacherInfo;
import vo.Teacher;

import java.sql.*;

public class InputTeacherInfoImpl implements IInputTeacherInfo{

	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	private ResultSet rs = null ;
	
	public InputTeacherInfoImpl() throws Exception  {
			DatabaseConnection dbc=DatabaseConnectionFactory.getDatabaseConnection();
		    this.conn=dbc.getConnection();

	}
	public Boolean insertexcel(Teacher ms)
	{
		Boolean jiaqi=false;
		String sql="insert into 教师信息表(教师编号,教师姓名,所属院系,联系方式) values(?,?,?,?)";
		
		try
		{
			this.pstmt = this.conn.prepareStatement(sql) ;
			this.pstmt.setString(1, ms.getNumber());
			this.pstmt.setString(2, ms.getName());
			this.pstmt.setString(3, ms.getFaculty());
			this.pstmt.setString(4, ms.getPhone());
			  this.pstmt.executeUpdate() ;

			jiaqi=true;
	
 	 	}catch(Exception e){
	 		e.printStackTrace();
		}
	return jiaqi;
}
	public boolean findTea(Teacher ms)
	{
		Boolean flag=false;
		String sql1="select 教师编号  from 教师信息表  where 教师编号=?";
		
		try
		{
			this.pstmt = this.conn.prepareStatement(sql1) ;
			this.pstmt.setString(1, ms.getNumber());
			rs=pstmt.executeQuery();
			if(rs.next()){
			flag=true;
			}
 	 	}catch(Exception e){
	 		e.printStackTrace();
		}
	return flag;
	}

}

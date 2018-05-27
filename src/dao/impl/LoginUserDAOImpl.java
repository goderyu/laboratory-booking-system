package dao.impl;
/**
* @author 于好贤
* @version 创建时间：2018年4月12日 上午10:43:38
* @description 真实主题实现类
*/
import java.util.* ;
import java.sql.* ;
import dao.* ;
import vo.* ;
public class LoginUserDAOImpl implements ILoginUserDAO {
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	public LoginUserDAOImpl(Connection conn){
		this.conn = conn ;
	}
	public boolean doCreate(LoginUser loginUser) throws Exception{
		boolean flag = false ;
		String sql = "INSERT INTO 用户登录信息表(用户编号,用户密码,用户类别) VALUES (?,?,?)" ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		this.pstmt.setString(1,loginUser.getUserId()) ;
		this.pstmt.setString(2,loginUser.getUserPass()) ;
		this.pstmt.setInt(3,loginUser.getUserType()) ;

		if(this.pstmt.executeUpdate() > 0){
			flag = true ;
		}
		this.pstmt.close() ;
		return flag ;
	}
	public List<LoginUser> findAll(String keyWord) throws Exception{
		List<LoginUser> all = new ArrayList<LoginUser>() ;
		String sql = "SELECT 用户编号,用户密码,用户类别 FROM 用户登录信息表 WHERE 用户编号 LIKE ?" ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		this.pstmt.setString(1,"%"+keyWord+"%") ;
		ResultSet rs = this.pstmt.executeQuery() ;
		LoginUser loginUser = null ;
		while(rs.next()){
			loginUser = new LoginUser() ;
			loginUser.setUserId(rs.getString(1)) ;
			loginUser.setUserPass(rs.getString(2)) ;
			loginUser.setUserType(rs.getInt(3)) ;
			all.add(loginUser) ;
		}
		this.pstmt.close() ;
		return all ;
	}
	public LoginUser findById(String loginId) throws Exception{
		LoginUser loginUser = null ;
		String sql = "SELECT 用户编号,用户密码,用户类别 FROM 用户登录信息表 WHERE 用户编号=?" ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		this.pstmt.setString(1,loginId) ;
		ResultSet rs = this.pstmt.executeQuery() ;
		if(rs.next()){
			loginUser = new LoginUser() ;
			loginUser.setUserId(rs.getString(1)) ;
			loginUser.setUserPass(rs.getString(2)) ;
			loginUser.setUserType(rs.getInt(3)) ;
		}
		this.pstmt.close() ;
		return loginUser ;
	}
}

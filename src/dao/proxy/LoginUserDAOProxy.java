package dao.proxy;
/**
* @author 于好贤
* @version 创建时间：2018年4月12日 上午10:55:49
* @description 代理主题实现类
*/
import java.util.* ;
import dao.* ;
import dbc.* ;
import dao.impl.* ;
import vo.* ;
import factory.* ;
public class LoginUserDAOProxy implements ILoginUserDAO{
	private DatabaseConnection dbc = null ;
	private ILoginUserDAO dao = null ;
	public LoginUserDAOProxy() throws Exception {
		this.dbc = DatabaseConnectionFactory.getDatabaseConnection() ;
		this.dao = new LoginUserDAOImpl(this.dbc.getConnection()) ;
	}
	public boolean doCreate(LoginUser loginUser) throws Exception{
		boolean flag = false ;
		try{
			if(this.dao.findById(loginUser.getUserId()) == null){
				flag = this.dao.doCreate(loginUser) ;
			}
		}catch(Exception e){
			throw e ;
		}finally{
			this.dbc.close() ;
		}
		return flag ;
	}
	public List<LoginUser> findAll(String keyWord) throws Exception{
		List<LoginUser> all = null ;
		try{
			all = this.dao.findAll(keyWord) ;
		}catch(Exception e){
			throw e ;
		}finally{
			this.dbc.close() ;
		}
		return all ;
	}
	public LoginUser findById(String loginId) throws Exception{
		LoginUser loginUser = null ;
		try{
			loginUser = this.dao.findById(loginId) ;
		}catch(Exception e){
			throw e ;
		}finally{
			this.dbc.close() ;
		}
		return loginUser ;
	}
}

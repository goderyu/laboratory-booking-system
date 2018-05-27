package dao.proxy;
import java.util.* ;
import dao.* ;
import dbc.* ;
import factory.DatabaseConnectionFactory;
import dao.impl.* ;
import vo.* ;
//代理主题实现类
public class LabInfoDAOProxy implements ILabInfoDAO {
	private DatabaseConnection dbc = null ;
	private ILabInfoDAO dao = null ;//声明DAO对象
	public LabInfoDAOProxy() throws Exception {//在构造方法中实例化连接，同时实例化dao对象
		this.dbc = DatabaseConnectionFactory.getDatabaseConnection() ;
		this.dao = new LabInfoDAOImpl(this.dbc.getConnection()) ;//实例化真实主题类
	}
	public boolean doCreate(LabInfo labinfo) throws Exception{
		boolean flag = false ;
		try{
			if(this.dao.findById(labinfo.getId()) == null){//如果要插入的实验室编号不存在
				flag = this.dao.doCreate(labinfo) ;//调用真实主题操作
			}
		}catch(Exception e){
			throw e ;
		}finally{
			this.dbc.close() ;
		}
		return flag ;
	}
	public List<LabInfo> findAll(String keyWord) throws Exception{
		List<LabInfo> all = null ;//定义返回的集合
		try{
			all = this.dao.findAll(keyWord) ;//调用真实主题
		}catch(Exception e){
			throw e ;
		}finally{
			this.dbc.close() ;
		}
		return all ;
	}
	public LabInfo findById(String id) throws Exception{
		LabInfo labinfo = null ;
		try{
			labinfo = this.dao.findById(id) ;
		}catch(Exception e){
			throw e ;
		}finally{
			this.dbc.close() ;
		}
		return labinfo ;
	}
	public boolean doUpdate(LabInfo labinfo) throws Exception{
		boolean flag = false;
		try {
			flag = this.dao.doUpdate(labinfo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}
	public boolean doRemove(String id) throws Exception{
		boolean flag = false;
		try {
			flag = this.dao.doRemove(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}
}
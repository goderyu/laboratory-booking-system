package dao.proxy;

import java.util.ArrayList;
import java.util.List;

import dao.ISearchDao;
import dao.impl.SearchDAOImpl;
import dbc.DatabaseConnection;
import factory.DatabaseConnectionFactory;
import vo.FindLabInfo;

/**
* @author 于好贤
* @version 创建时间：2018年5月22日 下午12:13:05
* @description 
*/
public class SearchDAOProxy implements ISearchDao {
	private DatabaseConnection dbc = null;
	private ISearchDao dao = null;//声明DAO对象

	public SearchDAOProxy() throws Exception {
		this.dbc = DatabaseConnectionFactory.getDatabaseConnection();
		this.dao = new SearchDAOImpl(this.dbc.getConnection());//实例化真实主题类
	}

	@Override
	public List<String> number() throws Exception {
		List<String> number = new ArrayList<>();
		try {
			number = this.dao.number();//调用真实主题操作
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return number;
	}

	@Override
	public List<String> teacherName() throws Exception {
		List<String> teacherName = new ArrayList<>();
		try {
			teacherName = this.dao.teacherName();//调用真实主题操作
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return teacherName;
	}

	@Override
	public List<FindLabInfo> find(String sql) throws Exception {
		List<FindLabInfo> info = new ArrayList<>();
		try {
			info = this.dao.find(sql);//调用真实主题操作
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return info;
	}

}

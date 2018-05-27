package dao.proxy;

import dao.IInputCourseInfoDao;
import dao.impl.InputCourseInfoDaoImpl;
import dbc.DatabaseConnection;
import factory.DatabaseConnectionFactory;
import vo.CourseInfo;

/**
* @author 于好贤
* @version 创建时间：2018年5月19日 上午10:42:30
* @description 
*/
public class InputCourseInfoDaoProxy implements IInputCourseInfoDao {
	private DatabaseConnection dbc = null;
	private IInputCourseInfoDao dao = null;//声明DAO对象

	public InputCourseInfoDaoProxy() throws Exception {//在构造方法中实例化连接，同时实例化dao对象
		this.dbc = DatabaseConnectionFactory.getDatabaseConnection();
		this.dao = new InputCourseInfoDaoImpl(this.dbc.getConnection());//实例化真实主题类

	}

	@Override
	public boolean insertExcel(CourseInfo ms) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.insertExcel(ms);//调用真实主题操作
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean findTea(CourseInfo ms) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.findTea(ms);//调用真实主题操作
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}
}

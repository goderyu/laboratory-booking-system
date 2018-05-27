package factory;

import dao.IInputCourseInfoDao;
import dao.ILabCloseSettingDAO;
import dao.ILabInfoDAO;
import dao.ILabNameDAO;
/**
* @author 于好贤
* @version 创建时间：2018年4月12日 上午11:01:59
* @description DAO工厂类
*/
import dao.ILoginUserDAO;
import dao.IResDAO;
import dao.ISearchDao;
import dao.proxy.InputCourseInfoDaoProxy;
import dao.proxy.LabCloseSettingDAOProxy;
import dao.proxy.LabInfoDAOProxy;
import dao.proxy.LabNameDAOProxy;
import dao.proxy.LoginUserDAOProxy;
import dao.proxy.ResDAOProxy;
import dao.proxy.SearchDAOProxy;

public class DAOFactory {
	public static ILoginUserDAO getILoginUserDAOInstance() throws Exception {
		return new LoginUserDAOProxy();
	}

	//取得实验室信息类的实例
	public static ILabInfoDAO getILabInfoDAOInstance() throws Exception {
		return new LabInfoDAOProxy();
	}

	public static ILabCloseSettingDAO getLabCloseSettingDAOInstance() {
		return new LabCloseSettingDAOProxy();
	}

	public static ILabNameDAO getLabNameDAOInstance() {
		return new LabNameDAOProxy();
	}

	public static IResDAO getIResDAOInstance() throws Exception {
		return new ResDAOProxy();
	}

	//取得导入教师信息表类的实例
	public static IInputCourseInfoDao getIInputCourseInfoDAOInstance() throws Exception {
		return new InputCourseInfoDaoProxy();
	}
	
	public static ISearchDao getISearchDAOInstance() throws Exception {
		return new SearchDAOProxy();
	}
}

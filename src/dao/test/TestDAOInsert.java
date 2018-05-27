package dao.test;
/**
* @author 于好贤
* @version 创建时间：2018年4月12日 上午11:06:00
* @description 测试DAO插入功能
*/
import factory.DAOFactory;
import vo.LoginUser;
public class TestDAOInsert {
	public static void main(String[] args) throws Exception {
		LoginUser loginUser = null;
		for(int x = 0; x < 5; x++) {
			loginUser = new LoginUser();
			loginUser.setUserId("1000" + x);
			loginUser.setUserPass("1504" + x);
			loginUser.setUserType(0);
			DAOFactory.getILoginUserDAOInstance().doCreate(loginUser);
		}
	}
}

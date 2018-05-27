package dao.test;
/**
* @author 于好贤
* @version 创建时间：2018年4月12日 上午11:18:07
* @description 测试DAO查询功能
*/
import java.util.* ;
import factory.DAOFactory ;
import vo.* ;
public class TestDAOSelect {
	public static void main(String args[]) throws Exception{
		List<LoginUser> all = DAOFactory.getILoginUserDAOInstance().findAll("") ;
		Iterator<LoginUser> iter = all.iterator() ;
		while(iter.hasNext()){
			LoginUser loginUser = iter.next() ;
			System.out.println(loginUser.getUserId() + "、" + loginUser.getUserPass() + " --> " + loginUser.getUserType()) ;
		}
	}
}

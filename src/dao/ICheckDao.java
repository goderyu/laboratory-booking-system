package dao;

import vo.UserInfo;

public interface ICheckDao {
	public UserInfo findLogin(String username,String password) throws Exception ;

}

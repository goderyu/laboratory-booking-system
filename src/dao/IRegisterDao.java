package dao;

import java.sql.SQLException;

import vo.UserInfo;

public interface IRegisterDao {
	public boolean findLogin(UserInfo vo) throws Exception;
	public boolean findUserId(String user_id) throws Exception;
	public boolean doCreate(UserInfo vo) throws SQLException;
}

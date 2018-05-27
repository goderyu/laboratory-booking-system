package dbc;
/**
* @author 于好贤
* @version 创建时间：2018年4月12日 上午10:15:05
* @description 数据库连接接口类
*/
import java.sql.Connection;
public interface DatabaseConnection {
	// 取得数据库连接
	public Connection getConnection();
	// 关闭数据库连接
	public void close() throws Exception ;
}

package factory;
/**
* @author 于好贤
* @version 创建时间：2018年4月12日 上午10:18:35
* @description 取得数据库连接的工厂类
*/
import dbc.DatabaseConnection;
import dbc.impl.SQLServerDatabaseConnection;
public class DatabaseConnectionFactory {
	// 取得DatabaseConnection接口实例
	public static DatabaseConnection getDatabaseConnection() {
		try {
			return new SQLServerDatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}

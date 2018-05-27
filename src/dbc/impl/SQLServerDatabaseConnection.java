package dbc.impl;
/**
* @author 于好贤
* @version 创建时间：2018年4月12日 上午9:56:58
* @description 数据库连接类
*/
import java.sql.Connection;
import java.sql.DriverManager;
import dbc.DatabaseConnection;
import util.Config;
public class SQLServerDatabaseConnection implements DatabaseConnection{
	private Connection conn = null;
	// 在构造方法中进行数据库连接
	public SQLServerDatabaseConnection() throws Exception {
		
		try {
			// 加载驱动程序，调用util包中的数据库配置类的属性
			Class.forName(Config.driver);
			// 连接数据库
			this.conn = DriverManager.getConnection(Config.url, Config.username, Config.password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 取得数据库连接
	public Connection getConnection() {
		return this.conn;
	}
	// 数据库关闭操作
	public void close() throws Exception {
		if(this.conn != null) {
			try {
				this.conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

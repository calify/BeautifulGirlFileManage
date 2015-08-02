package cn.calify.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	String url = "jdbc:mysql://127.0.0.1:3306/beautiful";
	String username = "root";
	String password = "root";
	Connection connection = null;
	
	public DBConnection(){
		try {
			// 1、加载JDBC驱动程序
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {		}
	}
	
	public Connection getConnection(){
		try {
			if (null == connection) {
				// 2、创建数据库的连接
				connection = DriverManager.getConnection(url, username,
						password);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			connection = null;
		}
		return connection;
	}
}

package com.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {
	public static Connection getConnection() {
		Connection conn = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// com.microsoft.sqlserver.jdbc.SQLServerDriver
		String dbURL = "jdbc:sqlserver://localhost:1433;Database=MicroPower";// jdbc:sqlserver://localhost:1433;Database=Game2048
		String userName = "sa";
		String userPwd = "123";
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbURL, userName, userPwd);
			//System.out.print(dbURL);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("连接失败");
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		// 判断conn是否为空
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
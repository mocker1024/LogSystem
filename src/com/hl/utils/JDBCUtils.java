package com.hl.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtils {
	// 
	public static final String DRIVERNAME = "com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3306/systemdb?characterEncoding=utf-8";
	public static final String USER = "root";
	public static final String PASSWORD = "111";
		
	static {
		//1加载 mysql 驱动
		try {
			Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		}
		
	// 1 创建连接
	public static Connection getConnection() throws Exception {
		Connection con = DriverManager.getConnection(URL,USER, PASSWORD);
		return con;
	}
	// 关闭连接
	public static void closeAll(Connection con,PreparedStatement ps,ResultSet rs) throws Exception {
		if(rs!=null) {
			rs.close();
		}
		if(ps!=null) {
			ps.close();
		}
		if(con!=null) {
			con.close();
		}
	}
}

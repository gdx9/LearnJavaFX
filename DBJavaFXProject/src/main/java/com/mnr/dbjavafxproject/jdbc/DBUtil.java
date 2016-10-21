package com.mnr.dbjavafxproject.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	static final String user = "jfx_user";
	static final String password = "1234";
	static final String url = "jdbc:mysql://localhost:3306/jfx_db";
	
	public static Connection getConnection() throws SQLException{
		
		return DriverManager.getConnection(url, user, password);
		
	}
	
	
}

package com.mnr.dbjavafxproject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {

	private static final String user = "jfx_user";
	private static final String password = "1234";
	private static final String url = "jdbc:mysql://localhost:3306/jfx_db";
	
	public static Connection getConnection() throws SQLException{
		
		return DriverManager.getConnection(url, user, password);
		
	}
	
	
}

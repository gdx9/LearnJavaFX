package com.mnr.dbjavafxproject.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCHelper {
	
	public void writeToDB(String name, String email, int age) throws SQLException{
		
		String sql = "INSERT INTO `users`(`user_name`,`user_email`,`user_age`) VALUES(?,?,?)";
		
		try(
			Connection conn = DBUtil.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);	
		){
			
			conn.setAutoCommit(false);// don't give to Insert values before commit
			
			statement.setString(1, name);
			statement.setString(2, email);
			statement.setInt(3, age);
			
			int result = statement.executeUpdate();
			if(result == 1){
				System.out.println("Added successfully!");
			}else{
				System.out.println("Error while adding!");
			}
		}catch (SQLException e) {
			System.out.println("something go wrong");
		}
		
	}

}

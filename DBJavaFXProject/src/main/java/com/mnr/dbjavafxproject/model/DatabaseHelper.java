package com.mnr.dbjavafxproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.mnr.dbjavafxproject.entities.User;

public class DatabaseHelper {

	private static Object obj = new Object();
	
	/**
	 * Call Hibernate or JDBC Insert methods depends on conMeth parameter.
	 * 
	 * @param name name of {@link User}
	 * @param email email of {@link User}
	 * @param age age of {@link User}
	 * @param conMeth connection type
	 */
	public static void addToDB(String name, String email, int age, ConnType conMeth){
		
		if(conMeth == ConnType.HIBERNATE){
			
			synchronized (obj) {
				//hibernate connection
				writeUserHibernate(name,email,age);
			}
			
		}else{
			
			synchronized (obj) {
				//JDBC
				writeUserJDBC(name, email, age);
			}
			
		}
		System.out.println( name + " " + email + " " + age );
		
	}
	
	/**
	 * Open Hibernate session, create a new {@code User}} and write it into database
	 * 
	 * @param name The name of user
	 * @param email The email of user
	 * @param age the age of user
	 * 
	 */
	private static void writeUserHibernate(String name,String email, int age){
		
		try{
			
			Session session = HibernateUtil.getSessionFactory().openSession();
	
			session.beginTransaction();
			
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setAge(age);
			
			session.save(user);
			session.getTransaction().commit();
			
			session.close();
			//HibernateUtil.getSessionFactory().close();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception on session error");
		}
	}
	
	/**
	 * Return a {@code User} with given id
	 * 
	 * @param id The id of user for finding
	 * 
	 * @return User with given id from database
	 */
	public static User readUserFromDB(Long id){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		User user = (User) session.get(User.class, id);
		
		session.close();
		//HibernateUtil.getSessionFactory().close();
		return user;
		
	}
	
	/**
	 * Returns list of {@code User}'s, taken from database
	 * return a list with all Users from database
	 * 
	 * @return list of users
	 */
	@SuppressWarnings("unchecked")
	public static List<User> getAllUsersFromDB(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> userList = session.createCriteria(User.class).list();
		
		session.close();
		//HibernateUtil.getSessionFactory().close();
		System.out.println(userList.size() + " users in database");
		
		return userList;
		
	}
	
	/**
	 * Write taken params into database
	 * 
	 * @param name The name of user
	 * @param email The email of user
	 * @param age the age of user
	 * 
	 * @throws
	 */
	private static void writeUserJDBC(String name, String email, int age){
		
		String sql = "INSERT INTO `users`(`user_name`,`user_email`,`user_age`) VALUES(?,?,?)";
		
		try(
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);	
		){
			
			conn.setAutoCommit(false);// don't give to Insert values before commit
			
			statement.setString(1, name);
			statement.setString(2, email);
			statement.setInt(3, age);
			
			int result = statement.executeUpdate();
			
			if(result == 1){
				System.out.println("Waiting for commit");
			}else{
				System.out.println("Error while adding!");
			}
			
			conn.commit();
			
			System.out.println("Added!");
			
		}catch (SQLException e) {
			System.out.println("Something go wrong with SQL");
			e.printStackTrace();
		}
		
	}
	
}

package com.mnr.dbjavafxproject;

import java.util.List;

import org.hibernate.Session;

import com.mnr.dbjavafxproject.entities.User;


public class DatabaseHelper {
	
	/**
	 * Create a new {@code User}} and write it into database
	 * 
	 * @param name The name of user
	 * @param email The email of user
	 * @param age the age of user
	 */
	public static void writeUserToDB(String name,String email, int age){
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
		return user;
		
	}
	
	/**
	 * Returns list of {@code User}'s, taken from database
	 * return a list with all Users from database
	 * 
	 * @return list of users
	 */
	public static List<User> getAllUsersFromDB(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> bList = session.createCriteria(User.class).list();
		session.close();
		return bList; 
		
	}
	

}

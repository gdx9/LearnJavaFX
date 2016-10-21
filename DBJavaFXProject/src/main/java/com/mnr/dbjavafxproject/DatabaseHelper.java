package com.mnr.dbjavafxproject;

import java.util.List;

import org.hibernate.Session;

import com.mnr.dbjavafxproject.entities.User;


public class DatabaseHelper {
	
	/*
	 * write User into database
	 * 
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
		HibernateUtil.getSessionFactory().close();
	
	}
	
	/*
	 * @return User with given id from database
	 */
	public static User readUserFromDB(Long id){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		User user = (User) session.get(User.class, id);
		
		session.close();
		HibernateUtil.getSessionFactory().close();
		return user;
		
	}
	
	/*
	 * return a list with all Users from database
	 */
	public static List<User> getAllUsersFromDB(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> bList = session.createCriteria(User.class).list();
		session.close();
		HibernateUtil.getSessionFactory().close();
		return bList; 
		
	}
	

}

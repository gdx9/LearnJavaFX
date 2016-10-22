package com.mnr.dbjavafxproject.model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.mnr.dbjavafxproject.entities.User;

public class HibernateUtil {
	
	private static final SessionFactory SESSION_FACTORY = buildSessionFactory();
			
	/**
	 * Create and return a {@link SessionFactory}
	 * 
	 * @return static SessionFactory for User class
	 * @throw RuntimeException on fail
	 */
	private static SessionFactory buildSessionFactory(){
		try {
			Configuration config = new Configuration();
			
			config.addAnnotatedClass(User.class);
			config.configure();
			
			return config
				.buildSessionFactory(new StandardServiceRegistryBuilder()
					.applySettings(config.getProperties())
						.build());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Building the factory error");
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return SESSION_FACTORY;
	}
	
}

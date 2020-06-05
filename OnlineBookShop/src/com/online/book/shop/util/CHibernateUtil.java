package com.online.book.shop.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CHibernateUtil {

	static SessionFactory factory;
	
	static{
		Configuration cfg = new Configuration();
			cfg = cfg.configure();
			factory = cfg.buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory(){
		return factory;
	}
	
}

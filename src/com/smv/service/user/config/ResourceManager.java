package com.smv.service.user.config;

import org.hibernate.SessionFactory;

import com.smv.util.config.ApplicationProperties;

public class ResourceManager {
	private static SessionFactory  sessionFactory = null;
	private static ApplicationProperties APP_PROPERTIES;
	
	static {
		initApplication();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void setSessionFactory(SessionFactory hsf) {
		sessionFactory = hsf;
	}
	
	public static ApplicationProperties getApplicationProperties() {
		return APP_PROPERTIES;
	}
	
	private static void initApplication() {
		APP_PROPERTIES = new ApplicationProperties("application.properties");
	}
	
}

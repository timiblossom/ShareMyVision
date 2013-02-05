package com.smv.service.mailer.config;

import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.hibernate.SessionFactory;

import com.smv.util.config.ApplicationProperties;


/**
 * @author Minh Do
 * 07/22/2010
 */
public class ResourceManager {
	private static SessionFactory sessionFactory = null;
	private static ApplicationProperties appProperties;
	
	static {
		initApplication();
		//initVelocity();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory hsf) {
		sessionFactory = hsf;
	}

	public static ApplicationProperties getApplicationProperties() {
		return appProperties;
	}
	
	private static void initApplication() {
		appProperties = new ApplicationProperties("application.properties");
	}
	
	public static void initVelocity() {
		try {
			Properties p = new Properties();

			//p.setProperty("resource.loader", "class");
			//p.setProperty("class.resource.loader.description", "Velocity Classpath Resource Loader");
			//p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

			
			p.setProperty("runtime.log", "logs/velocity.log");
			p.setProperty("runtime.log.error.stacktrace", "false");
			p.setProperty("runtime.log.warn.stacktrace", "false");
			p.setProperty("runtime.log.info.stacktrace", "false");
			p.setProperty("runtime.log.invalid.references", "true");

			p.setProperty("resource.loader", "webapp");
			p.setProperty("webapp.resource.loader.class", "com.smv.service.mailer.config.WebAppVelocityResourceLoader");
			//p.setProperty("webapp.resource.loader.path", "/WEB-INF/templates/");
			p.setProperty("ClasspathResourceLoader.resource.loader.cache", "true");
			p.setProperty("parser.pool.size", "20");
			
			Velocity.init(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

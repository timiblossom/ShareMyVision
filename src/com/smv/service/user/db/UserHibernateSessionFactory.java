package com.smv.service.user.db;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.smv.service.user.UserConstant;
import com.smv.service.user.config.ResourceManager;
import com.smv.util.db.HibernateSessionFactory;
import com.smv.util.spring.SpringXmlConfigReader;



public class UserHibernateSessionFactory extends HibernateSessionFactory {
	
	private static final Logger LOGGER = Logger.getLogger(UserHibernateSessionFactory.class);
	private static UserHibernateSessionFactory instance;
	
	public UserHibernateSessionFactory() {
		super("hibernate.user.xml");
	}
	
	public UserHibernateSessionFactory(SessionFactory sessionFactory) {
		super(sessionFactory);	
	}
	
	
	public static UserHibernateSessionFactory getInstance() {
		if (instance == null) {
			synchronized (UserHibernateSessionFactory.class) {
				if (ResourceManager.getSessionFactory() == null) {
					LOGGER.debug("Loading sessionFactory using SpringConfigReader");
					SpringXmlConfigReader manager = new SpringXmlConfigReader(UserConstant.USER_DB_CONFIG_FILE);
					SessionFactory sessionFactory = (SessionFactory) manager.getBeanById(UserConstant.USER_SESSION_FACTORY_BEAN_ID);
					instance = new UserHibernateSessionFactory(sessionFactory);
				} else {
					LOGGER.debug("Loading sessionFactory using WebContext");
					instance = new UserHibernateSessionFactory(ResourceManager.getSessionFactory());
				}
			}
		}
		
		return instance;
	}
}

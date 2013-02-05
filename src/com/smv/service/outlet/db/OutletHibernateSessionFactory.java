package com.smv.service.outlet.db;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.smv.service.outlet.OutletConstant;
import com.smv.service.outlet.config.ResourceManager;
import com.smv.util.db.HibernateSessionFactory;
import com.smv.util.spring.SpringXmlConfigReader;



public class OutletHibernateSessionFactory extends HibernateSessionFactory {
	
	private static final Logger LOGGER = Logger.getLogger(OutletHibernateSessionFactory.class);
	private static OutletHibernateSessionFactory instance;
	
	public OutletHibernateSessionFactory() {
		super("hibernate.outlet.xml");
	}
	
	public OutletHibernateSessionFactory(SessionFactory sessionFactory) {
		super(sessionFactory);	
	}
	
	
	public static OutletHibernateSessionFactory getInstance() {
		if (instance == null) {
			synchronized (OutletHibernateSessionFactory.class) {
				if (ResourceManager.getSessionFactory() == null) {
					LOGGER.debug("Loading sessionFactory using SpringConfigReader");
					SpringXmlConfigReader manager = new SpringXmlConfigReader(OutletConstant.OUTLET_DB_CONFIG_FILE);
					SessionFactory sessionFactory = (SessionFactory) manager.getBeanById(OutletConstant.OUTLET_SESSION_FACTORY_BEAN_ID);
					instance = new OutletHibernateSessionFactory(sessionFactory);
				} else {
					LOGGER.debug("Loading sessionFactory using WebContext");
					instance = new OutletHibernateSessionFactory(ResourceManager.getSessionFactory());
				}
			}
		}
		
		return instance;
	}
}

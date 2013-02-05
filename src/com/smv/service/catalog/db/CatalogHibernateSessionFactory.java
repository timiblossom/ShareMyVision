package com.smv.service.catalog.db;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.smv.service.catalog.CatalogConstant;
import com.smv.service.catalog.config.ResourceManager;
import com.smv.util.db.HibernateSessionFactory;
import com.smv.util.spring.SpringXmlConfigReader;



public class CatalogHibernateSessionFactory extends HibernateSessionFactory {
	
	private static final Logger LOGGER = Logger.getLogger(CatalogHibernateSessionFactory.class);
	private static CatalogHibernateSessionFactory instance;
	
	public CatalogHibernateSessionFactory() {
		super("hibernate.catalog.xml");
	}
	
	public CatalogHibernateSessionFactory(SessionFactory sessionFactory) {
		super(sessionFactory);	
	}
	
	
	public static CatalogHibernateSessionFactory getInstance() {
		if (instance == null) {
			synchronized (CatalogHibernateSessionFactory.class) {
				if (ResourceManager.getSessionFactory() == null) {
					LOGGER.debug("Loading sessionFactory using SpringConfigReader");
					SpringXmlConfigReader manager = new SpringXmlConfigReader(CatalogConstant.CATALOG_DB_CONFIG_FILE);
					SessionFactory sessionFactory = (SessionFactory) manager.getBeanById(CatalogConstant.CATALOG_SESSION_FACTORY_BEAN_ID);
					instance = new CatalogHibernateSessionFactory(sessionFactory);
				} else {
					LOGGER.debug("Loading sessionFactory using WebContext");
					instance = new CatalogHibernateSessionFactory(ResourceManager.getSessionFactory());
				}
			}
		}
		
		return instance;
	}
}

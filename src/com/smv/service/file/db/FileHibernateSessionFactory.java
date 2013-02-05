package com.smv.service.file.db;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.smv.service.file.config.ResourceManager;
import com.smv.util.db.HibernateSessionFactory;


/**
 * @author Minh Do
 * 03/2010
 */

public class FileHibernateSessionFactory extends HibernateSessionFactory {
	private static final Logger LOGGER = Logger.getLogger(FileHibernateSessionFactory.class);
	private static FileHibernateSessionFactory instance;
	
	public FileHibernateSessionFactory() {
		super("hibernate.file.xml");
	}
	
	public FileHibernateSessionFactory(SessionFactory sessionFactory) {
		super(sessionFactory);	
	}
	
	
	public static FileHibernateSessionFactory getInstance() {
		if (instance == null) {
			synchronized (FileHibernateSessionFactory.class) {
				if (ResourceManager.getSessionFactory() == null) {
					LOGGER.debug("Loading sessionFactory using SpringConfigReader");
					//SpringXmlConfigReader manager = new SpringXmlConfigReader(FileConstant.FILE_DB_CONFIG_FILE);
					//SessionFactory sessionFactory = (SessionFactory) manager.getBeanById(FileConstant.FILE_SESSION_FACTORY_BEAN_ID);
					instance = new FileHibernateSessionFactory();
				} else {
					LOGGER.debug("Loading sessionFactory using WebContext");
					instance = new FileHibernateSessionFactory(ResourceManager.getSessionFactory());
				}
			}
		}
		
		return instance;
	}
}

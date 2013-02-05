package com.smv.service.mailer.db;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.smv.service.mailer.MailerConstant;
import com.smv.service.mailer.config.ResourceManager;
import com.smv.service.mailer.db.MailerHibernateSessionFactory;
import com.smv.util.db.HibernateSessionFactory;
import com.smv.util.spring.SpringXmlConfigReader;



/**
 * @author Minh Do
 * 07/22/2010
 */
public class MailerHibernateSessionFactory extends HibernateSessionFactory {
	
	private static final Logger LOGGER = Logger.getLogger(MailerHibernateSessionFactory.class);
	private static MailerHibernateSessionFactory instance;
	
	public MailerHibernateSessionFactory() {
		super("hibernate.mailer.xml");
	}
	
	public MailerHibernateSessionFactory(SessionFactory sessionFactory) {
		super(sessionFactory);	
	}
	
	
	public static MailerHibernateSessionFactory getInstance() {
		if (instance == null) {
			synchronized (MailerHibernateSessionFactory.class) {
				if (ResourceManager.getSessionFactory() == null) {
					LOGGER.debug("Loading sessionFactory using SpringConfigReader");
					SpringXmlConfigReader manager = new SpringXmlConfigReader(MailerConstant.MAILER_DB_CONFIG_FILE);
					SessionFactory sessionFactory = (SessionFactory) manager.getBeanById(MailerConstant.MAILER_SESSION_FACTORY_BEAN_ID);
					instance = new MailerHibernateSessionFactory(sessionFactory);
				} else {
					LOGGER.debug("Loading sessionFactory using WebContext");
					instance = new MailerHibernateSessionFactory(ResourceManager.getSessionFactory());
				}
			}
		}
		
		return instance;
	}
}

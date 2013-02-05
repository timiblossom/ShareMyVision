package com.smv.service.payment.db;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.smv.service.payment.PaymentConstant;
import com.smv.service.payment.config.ResourceManager;
import com.smv.util.db.HibernateSessionFactory;
import com.smv.util.spring.SpringXmlConfigReader;



public class PaymentHibernateSessionFactory extends HibernateSessionFactory {
	
	private static final Logger LOGGER = Logger.getLogger(PaymentHibernateSessionFactory.class);
	private static PaymentHibernateSessionFactory instance;
	
	public PaymentHibernateSessionFactory() {
		super("hibernate.payment.xml");
	}
	
	public PaymentHibernateSessionFactory(SessionFactory sessionFactory) {
		super(sessionFactory);	
	}
	
	
	public static PaymentHibernateSessionFactory getInstance() {
		if (instance == null) {
			synchronized (PaymentHibernateSessionFactory.class) {
				if (ResourceManager.getSessionFactory() == null) {
					LOGGER.debug("Loading sessionFactory using SpringConfigReader");
					SpringXmlConfigReader manager = new SpringXmlConfigReader(PaymentConstant.payment_DB_CONFIG_FILE);
					SessionFactory sessionFactory = (SessionFactory) manager.getBeanById(PaymentConstant.payment_SESSION_FACTORY_BEAN_ID);
					instance = new PaymentHibernateSessionFactory(sessionFactory);
				} else {
					LOGGER.debug("Loading sessionFactory using WebContext");
					instance = new PaymentHibernateSessionFactory(ResourceManager.getSessionFactory());
				}
			}
		}
		
		return instance;
	}
}

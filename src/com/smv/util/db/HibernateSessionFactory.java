package com.smv.util.db;


import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.EntityMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.Type;

/**
 * @author Minh Do
 * 03/2010
 */

public abstract class HibernateSessionFactory {
	
	private static final Logger log = Logger.getLogger(HibernateSessionFactory.class);

	private SessionFactory sessionFactory;

	protected HibernateSessionFactory() {
		init(getConfigFile());
	}
	
	protected HibernateSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}
	
	protected HibernateSessionFactory(String configFile) {
		init(configFile);
	}
	
    protected void init(String configFile) {
    	try {
    		sessionFactory  = new Configuration().configure(configFile).buildSessionFactory();
    	} catch (Exception e) {
    		log.fatal("Creating SessionFactory failed for config file : " + configFile);
    		e.printStackTrace();
    		throw new ExceptionInInitializerError(e);
    	}
    }
    

    public SessionFactory getSessionFactory() {
    	return sessionFactory;
    }
    
    public SmvHibernateSession getSession() {
    	Session session = getSessionFactory().getCurrentSession();
    	SmvHibernateSession ysiSession = new SmvHibernateSession(session);    	
    	return ysiSession;
	}
	
	
    public SmvHibernateSession openSession() {
    	Session session = getSessionFactory().openSession();
    	SmvHibernateSession ysiSession = new SmvHibernateSession(session);
    	return ysiSession;
	}
	
	
	
	public Serializable getIdentifier(Object vo) {
		SessionFactory factory = getSessionFactory();
		
		return factory.getClassMetadata(vo.getClass()).getIdentifier(vo, EntityMode.POJO);
	}
	
	
	public Object[] getPropertyValues(Object vo) {
		SessionFactory factory = getSessionFactory();
		
		return factory.getClassMetadata(vo.getClass()).getPropertyValues(vo, EntityMode.POJO);
	}
	
	public Type[] getPropertyTypes(Object vo) {
		SessionFactory factory = getSessionFactory();
		
		return factory.getClassMetadata(vo.getClass()).getPropertyTypes();
	}
	
	public String[] getPropertyNames(Object vo) {
		SessionFactory factory = getSessionFactory();
		return factory.getClassMetadata(vo.getClass()).getPropertyNames();
	}
	
	public Type getPropertyType(String propertyName, Object vo) {
		SessionFactory factory = getSessionFactory();
		return factory.getClassMetadata(vo.getClass()).getPropertyType(propertyName);
	}

	protected String getConfigFile() {
		return "hibernate.xml";
	}
	
}


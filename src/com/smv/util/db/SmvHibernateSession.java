package com.smv.util.db;


import java.io.Serializable;
import java.sql.Connection;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.Filter;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.hibernate.stat.SessionStatistics;

/**
 * @author Minh Do
 * 03/2010
 */


public class SmvHibernateSession implements Session {

	private static final Logger log = Logger.getLogger(SmvHibernateSession.class);

	private Session sessionImpl;
	private Transaction tx;

	private SmvHibernateSession() {};

	public SmvHibernateSession(Session session) {
		this.sessionImpl = session;
	}


	public static void beginTransaction(SmvHibernateSession session) throws HibernateException {
		if (session != null)
			session.smvbeginTransaction();
	}

	public static void commitTransaction(SmvHibernateSession session) throws HibernateException {
		if (session != null)
			session.smvCommitTransaction();
	}


	public static void rollbackTransaction(SmvHibernateSession session) throws HibernateException {
		if (session != null)
			session.smvRollback();
	}


	public void smvbeginTransaction() throws HibernateException {
		tx = sessionImpl.beginTransaction();
	}



	public void smvCommitTransaction() throws HibernateException {

		try {
			if (tx != null)
				tx.commit();
		} catch (StaleObjectStateException e) {
			log.error(e);
			throw e;
		} catch (HibernateException e) {
			log.error(e);
			throw e;
		} catch (Exception e) {
			log.error(e);
			//e.printStackTrace();
			throw new HibernateException("Internal error on db");
		} finally {
			if (sessionImpl.isOpen())
				try {
					sessionImpl.close();
				} catch (Exception ee) {
					log.error(ee);
				}
		}
	}

	public void smvRollback() throws HibernateException {

		try {
			if (tx != null)
				tx.rollback();
		} catch (HibernateException e) {
			log.error(e); 
			throw e;
		} catch (Exception ee) {
			log.error(ee);
			ee.printStackTrace();
		} finally {
			if (sessionImpl.isOpen())
				try {
					sessionImpl.close();
				} catch (Exception ee) {
					log.error(ee);
				}
		}

	}



	public Transaction beginTransaction() throws HibernateException {

		return sessionImpl.beginTransaction();

	}


	public void cancelQuery() throws HibernateException {

		sessionImpl.cancelQuery();
	}

	public void clear() {

		sessionImpl.clear();
	}

	public Connection close() throws HibernateException {

		return sessionImpl.close();

	}

	public Connection connection() throws HibernateException {

		return sessionImpl.connection();

	}

	public boolean contains(Object arg0) {

		return sessionImpl.contains(arg0);

	}

	public Criteria createCriteria(Class arg0) {

		return sessionImpl.createCriteria(arg0);

	}

	public Criteria createCriteria(String arg0) {

		return sessionImpl.createCriteria(arg0);

	}


	public Criteria createCriteria(Class arg0, String arg1) {

		return sessionImpl.createCriteria(arg0, arg1);

	}

	public Criteria createCriteria(String arg0, String arg1) {

		return sessionImpl.createCriteria(arg0, arg1);

	}

	public Query createFilter(Object arg0, String arg1)
	throws HibernateException {

		return sessionImpl.createFilter(arg0, arg1);

	}


	public Query createQuery(String arg0) throws HibernateException {

		return sessionImpl.createQuery(arg0);

	}


	public SQLQuery createSQLQuery(String arg0) throws HibernateException {

		return sessionImpl.createSQLQuery(arg0);

	}

	public void delete(Object arg0) throws HibernateException {
		sessionImpl.delete(arg0);

	}

	public void delete(String arg0, Object arg1) throws HibernateException {
		sessionImpl.delete(arg0, arg1);
	}

	public void disableFilter(String arg0) {

		sessionImpl.disableFilter(arg0);
	}


	public Connection disconnect() throws HibernateException {

		return sessionImpl.disconnect();

	}

	public void doWork(Work arg0) throws HibernateException {

		sessionImpl.doWork(arg0);

	}

	public Filter enableFilter(String arg0) {

		return sessionImpl.enableFilter(arg0);

	}

	public void evict(Object arg0) throws HibernateException {

		sessionImpl.evict(arg0);
	}

	public void flush() throws HibernateException {

		sessionImpl.flush();
	}


	public Object get(Class arg0, Serializable arg1) throws HibernateException {

		return sessionImpl.get(arg0, arg1);

	}


	public Object get(String arg0, Serializable arg1) throws HibernateException {

		return sessionImpl.get(arg0, arg1);

	}


	public Object get(Class arg0, Serializable arg1, LockMode arg2)
	throws HibernateException {
		throw new UnsupportedOperationException();
	}


	public Object get(String arg0, Serializable arg1, LockMode arg2)
	throws HibernateException {
		throw new UnsupportedOperationException();
	}


	public CacheMode getCacheMode() {

		return sessionImpl.getCacheMode();
	}

	public LockMode getCurrentLockMode(Object arg0) throws HibernateException {

		return sessionImpl.getCurrentLockMode(arg0);
	}

	public Filter getEnabledFilter(String arg0) {

		return sessionImpl.getEnabledFilter(arg0);
	}

	public EntityMode getEntityMode() {

		return sessionImpl.getEntityMode();

	}


	public String getEntityName(Object arg0) throws HibernateException {

		return sessionImpl.getEntityName(arg0);
	}

	public FlushMode getFlushMode() {

		return sessionImpl.getFlushMode();
	}

	public Serializable getIdentifier(Object arg0) throws HibernateException {

		return sessionImpl.getIdentifier(arg0);
	}

	public Query getNamedQuery(String arg0) throws HibernateException {

		return sessionImpl.getNamedQuery(arg0);
	}

	public Session getSession(EntityMode arg0) {

		return sessionImpl.getSession(arg0);
	}


	public SessionFactory getSessionFactory() {

		return sessionImpl.getSessionFactory();
	}


	public SessionStatistics getStatistics() {

		return sessionImpl.getStatistics();
	}


	public Transaction getTransaction() {

		return sessionImpl.getTransaction();
	}

	public boolean isConnected() {
		return sessionImpl.isConnected();
	}

	public boolean isDirty() throws HibernateException {
		return sessionImpl.isDirty();
	}

	public boolean isOpen() {
		return sessionImpl.isOpen();
	}


	public Object load(Class arg0, Serializable arg1) throws HibernateException {
		throw new UnsupportedOperationException();
	}


	public Object load(String arg0, Serializable arg1)
	throws HibernateException {
		throw new UnsupportedOperationException();
	}


	public void load(Object arg0, Serializable arg1) throws HibernateException {
		throw new UnsupportedOperationException();

	}


	public Object load(Class arg0, Serializable arg1, LockMode arg2)
	throws HibernateException {
		throw new UnsupportedOperationException();
	}


	public Object load(String arg0, Serializable arg1, LockMode arg2)
	throws HibernateException {
		throw new UnsupportedOperationException();
	}


	public void lock(Object arg0, LockMode arg1) throws HibernateException {
		throw new UnsupportedOperationException();

	}


	public void lock(String arg0, Object arg1, LockMode arg2)
	throws HibernateException {
		throw new UnsupportedOperationException();

	}


	public Object merge(Object arg0) throws HibernateException {
		return sessionImpl.merge(arg0);
	}


	public Object merge(String arg0, Object arg1) throws HibernateException {
		return sessionImpl.merge(arg0, arg1);
	}


	public void persist(Object arg0) throws HibernateException {
		sessionImpl.persist(arg0);

	}


	public void persist(String arg0, Object arg1) throws HibernateException {
		sessionImpl.persist(arg0, arg1);

	}


	public void reconnect() throws HibernateException {
		throw new UnsupportedOperationException();

	}


	public void reconnect(Connection arg0) throws HibernateException {
		throw new UnsupportedOperationException();

	}

	public void refresh(Object arg0) throws HibernateException {
		throw new UnsupportedOperationException();

	}


	public void refresh(Object arg0, LockMode arg1) throws HibernateException {
		throw new UnsupportedOperationException();

	}


	public void replicate(Object arg0, ReplicationMode arg1)
	throws HibernateException {
		throw new UnsupportedOperationException();

	}


	public void replicate(String arg0, Object arg1, ReplicationMode arg2)
	throws HibernateException {
		throw new UnsupportedOperationException();

	}


	public Serializable save(Object arg0) throws HibernateException {
		return sessionImpl.save(arg0);
	}


	public Serializable save(String arg0, Object arg1)
	throws HibernateException {
		return sessionImpl.save(arg0, arg1);
	}


	public void saveOrUpdate(Object arg0) throws HibernateException {
		throw new UnsupportedOperationException();

	}


	public void saveOrUpdate(String arg0, Object arg1)
	throws HibernateException {
		throw new UnsupportedOperationException();

	}


	public void setCacheMode(CacheMode arg0) {
		throw new UnsupportedOperationException();
	}


	public void setFlushMode(FlushMode arg0) {
		throw new UnsupportedOperationException();
	}

	public void setReadOnly(Object arg0, boolean arg1) {
		throw new UnsupportedOperationException();
	}

	public void update(Object arg0) throws HibernateException {
		sessionImpl.update(arg0);
	}

	public void update(String arg0, Object arg1) throws HibernateException {
		sessionImpl.update(arg0, arg1);
	}

}

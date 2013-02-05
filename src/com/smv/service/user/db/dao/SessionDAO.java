package com.smv.service.user.db.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.SessionDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;


public class SessionDAO extends UserBaseDAO implements Serializable {

	private static final long serialVersionUID = -1750742073053591390L;
	private static final Logger LOGGER = Logger.getLogger(SessionDAO.class);
	
	public SessionDAO(SessionDO clientDO) {
		super(clientDO);
	}
	
	public SessionDO getSessionDO() {
		return (SessionDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		SessionDO clientDO = (SessionDO) obj1;
		SessionDO serverDO = (SessionDO) super.merge(obj1, obj2);

		if (clientDO.getGuid() != null) {
			serverDO.setGuid(clientDO.getGuid());
		}
		
		if (clientDO.getUid() != null) {
			serverDO.setUid(clientDO.getUid());
		}
		
		if (clientDO.getClientIp() != null) {
			serverDO.setClientIp(clientDO.getClientIp());
		}
			
		if (clientDO.getLastLogin() != null) {
			serverDO.setLastLogin(clientDO.getLastLogin());
		}
		
		if (clientDO.getUserAgent() != null) {
			serverDO.setUserAgent(clientDO.getUserAgent());
		}
		
		return serverDO;
	}

	public static List<SessionDO> getAllSessions() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<SessionDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allSessions");
			result = (List<SessionDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}
	
	public static SessionDO getSessionById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		SessionDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (SessionDO) session.get(SessionDO.class, id);
			session.smvCommitTransaction();
		}
		catch(Exception he) 
		{
			LOGGER.error(he);
			if(session != null)	session.smvRollback();
			return null;
	    }
		return retVal;
	}

	public static SessionDO getSessionByUserId(Long uid) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		SessionDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupSessionByUserId");
			query.setLong(0, uid);
			Object result = query.uniqueResult();
			if(result != null) retVal = (SessionDO) result;
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}

	public static SessionDO getSessionByUserEmail(String email) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		SessionDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupSessionByUserEmail");
			query.setString(0, email);
			Object result = query.uniqueResult();
			if(result != null) retVal = (SessionDO) result;
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}

}

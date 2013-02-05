package com.smv.service.outlet.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.catalog.db.CatalogHibernateSessionFactory;
import com.smv.service.outlet.db.OutletHibernateSessionFactory;
import com.smv.service.outlet.db.dbobject.UserOutletDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class UserOutletDAO extends OutletBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(UserOutletDAO.class);
	
	public UserOutletDAO(UserOutletDO clientDO) {
		super(clientDO);
	}
	
	public UserOutletDO getUserOutletDO() {
		return (UserOutletDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		UserOutletDO clientDO = (UserOutletDO) obj1;
		UserOutletDO serverDO = (UserOutletDO) super.merge(obj1, obj2);

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		if (clientDO.getOutletId() != null) {
			serverDO.setOutletId(clientDO.getOutletId());
		}
		
		if (clientDO.getUserId() != null) {
			serverDO.setUserId(clientDO.getUserId());
		}
		
		if (clientDO.getStatusId() != null) {
			serverDO.setStatusId(clientDO.getStatusId());
		}
		
		return serverDO;
	}

	public static List<UserOutletDO> getAllUserOutlets() {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<UserOutletDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allUserOutlets");
			result = (List<UserOutletDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static UserOutletDO getUserOutletById(Long id) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		UserOutletDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (UserOutletDO) session.get(UserOutletDO.class, id);
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
	
	public static UserOutletDO getUserOutletByOutletId(Long outletId) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		UserOutletDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupUserOutletByOutletId");
			query.setLong(0, outletId);
			Object result = query.uniqueResult();
            if(result != null) {
            	retVal = (UserOutletDO) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return retVal;
	}
	
	public static List<UserOutletDO> getUserOutletByUserId(Long userId) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<UserOutletDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupUserOutletByUserId");
			query.setLong(0, userId);
			result = (List<UserOutletDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}
	
	public static List<UserOutletDO> getUserOutletByUserIdAndOutletId(Long userId, Long outletId) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<UserOutletDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupUserOutletByUserIdAndOutletId");
			query.setLong(0, userId);
			query.setLong(1, outletId);
			result = (List<UserOutletDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}
	
	public static List<UserOutletDO> getUserOutletByStatusId(Long statusId) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<UserOutletDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupUserOutletByStatusId");
			query.setLong(0, statusId);
			result = (List<UserOutletDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}
	
	public static List<UserOutletDO> getUserOutletByKeyValuePair(Long keyValuePairId) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<UserOutletDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupUserOutletByKeyValuePair");
			query.setLong(0, keyValuePairId);
			result = (List<UserOutletDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}

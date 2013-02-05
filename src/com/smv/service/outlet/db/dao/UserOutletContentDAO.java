package com.smv.service.outlet.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.catalog.db.CatalogHibernateSessionFactory;
import com.smv.service.outlet.db.OutletHibernateSessionFactory;
import com.smv.service.outlet.db.dbobject.UserOutletContentDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class UserOutletContentDAO extends OutletBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(UserOutletContentDAO.class);
	
	public UserOutletContentDAO(UserOutletContentDO clientDO) {
		super(clientDO);
	}
	
	public UserOutletContentDO getUserOutletContentDO() {
		return (UserOutletContentDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		UserOutletContentDO clientDO = (UserOutletContentDO) obj1;
		UserOutletContentDO serverDO = (UserOutletContentDO) super.merge(obj1, obj2);

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		if (clientDO.getUserId() != null) {
			serverDO.setUserId(clientDO.getUserId());
		}
		
		return serverDO;
	}

	public static List<UserOutletContentDO> getAllUserOutletContents() {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<UserOutletContentDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allUserOutletContents");
			result = (List<UserOutletContentDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static UserOutletContentDO getUserOutletContentById(Long id) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		UserOutletContentDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (UserOutletContentDO) session.get(UserOutletContentDO.class, id);
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
	
	public static UserOutletContentDO getUserOutletContentByUserId(Long userId) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		UserOutletContentDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupUserOutletContentByUserId");
			query.setLong(0, userId);
			Object result = query.uniqueResult();
            if(result != null) {
            	retVal = (UserOutletContentDO) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return retVal;
	}
	
	public static List<UserOutletContentDO> getUserOutletContentByKeyValuePair(Long keyValuePairId) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<UserOutletContentDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupUserOutletContentByKeyValuePair");
			query.setLong(0, keyValuePairId);
			result = (List<UserOutletContentDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}


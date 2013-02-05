package com.smv.service.outlet.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.outlet.db.OutletHibernateSessionFactory;
import com.smv.service.outlet.db.dbobject.OutletTypeDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class OutletTypeDAO extends OutletBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(OutletTypeDAO.class);
	
	/**
	 * @param clientDO
	 */
	public OutletTypeDAO(OutletTypeDO clientDO) {
		super(clientDO);
	}
	
	public OutletTypeDO getOutletTypeDO() {
		return (OutletTypeDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		OutletTypeDO clientDO = (OutletTypeDO) obj1;
		OutletTypeDO serverDO = (OutletTypeDO) super.merge(obj1, obj2);

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		if (clientDO.getPrefix() != null) {
			serverDO.setPrefix(clientDO.getPrefix());
		}
		
		if (clientDO.getStatusId() != null) {
			serverDO.setStatusId(clientDO.getStatusId());
		}
		
		return serverDO;
	}

	public static List<OutletTypeDO> getAllOutletTypes() {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<OutletTypeDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allOutletTypes");
			result = (List<OutletTypeDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static OutletTypeDO getOutletTypeById(Long id) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		OutletTypeDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (OutletTypeDO) session.get(OutletTypeDO.class, id);
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
	
	public static OutletTypeDO getOutletStatusTypeByName(String name) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		OutletTypeDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupOutletTypeByName");
			query.setString(0, name);
			Object result = query.uniqueResult();
            if(result != null) {
            	retVal = (OutletTypeDO) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return retVal;
	}

	public static OutletTypeDO getOutletStatusTypeByPrefix(String prefix) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		OutletTypeDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupOutletTypeByPrefix");
			query.setString(0, prefix);
			Object result = query.uniqueResult();
            if(result != null) {
            	retVal = (OutletTypeDO) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return retVal;
	}

	public static OutletTypeDO getOutletTypeByStatusId(Long statusId) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		OutletTypeDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupOutletTypeByStatusId");
			query.setLong(0, statusId);
			Object result = query.uniqueResult();
            if(result != null) {
            	retVal = (OutletTypeDO) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return retVal;
	}
	
	public static List<OutletTypeDO> getOutletTypeByUserId(Long userId) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<OutletTypeDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupOutletTypeByUserId");
			query.setLong(0, userId);
			result = (List<OutletTypeDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}
	
}

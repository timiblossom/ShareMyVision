package com.smv.service.outlet.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.outlet.db.OutletHibernateSessionFactory;
import com.smv.service.outlet.db.dbobject.OutletStatusTypeDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class OutletStatusTypeDAO extends OutletBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(OutletStatusTypeDAO.class);
	
	/**
	 * @param clientDO
	 */
	public OutletStatusTypeDAO(OutletStatusTypeDO clientDO) {
		super(clientDO);
	}
	
	public OutletStatusTypeDO getOutletStatusTypeDO() {
		return (OutletStatusTypeDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		OutletStatusTypeDO clientDO = (OutletStatusTypeDO) obj1;
		OutletStatusTypeDO serverDO = (OutletStatusTypeDO) super.merge(obj1, obj2);

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		return serverDO;
	}

	public static List<OutletStatusTypeDO> getAllOutletStatusTypes() {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<OutletStatusTypeDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allOutletStatusTypes");
			result = (List<OutletStatusTypeDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static OutletStatusTypeDO getOutletStatusTypeById(Long id) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		OutletStatusTypeDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (OutletStatusTypeDO) session.get(OutletStatusTypeDO.class, id);
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
	
	public static OutletStatusTypeDO getOutletStatusTypeByName(String name) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		OutletStatusTypeDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupOutletStatusTypeByName");
			query.setString(0, name);
			Object result = query.uniqueResult();
            if(result != null) {
            	retVal = (OutletStatusTypeDO) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return retVal;
	}

}

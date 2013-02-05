/**
 * 
 */
package com.smv.service.catalog.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.catalog.db.dbobject.StatusDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.SmvHibernateSession;
import com.smv.service.catalog.db.CatalogHibernateSessionFactory;

/**
 * @author TriNguyen
 *
 */
public class StatusDAO extends CatalogBaseDAO {


	private static final Logger LOGGER = Logger.getLogger(StatusDAO.class);

	/**
	 * @param clientDO
	 */
	public StatusDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}

	public StatusDO getStatusDO() {
		return (StatusDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		StatusDO clientDO = (StatusDO) obj1;
		StatusDO serverDO = (StatusDO) super.merge(obj1, obj2);
		
		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		return serverDO;
	}

	public static List<StatusDO> getAllStatuses() {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<StatusDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allStatuses");
			result = (List<StatusDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static StatusDO getStatusById(Long id) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		StatusDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (StatusDO) session.get(StatusDO.class, id);
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
	
}

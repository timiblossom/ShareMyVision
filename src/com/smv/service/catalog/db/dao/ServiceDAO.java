/**
 * 
 */
package com.smv.service.catalog.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.catalog.db.CatalogHibernateSessionFactory;
import com.smv.service.catalog.db.dbobject.ServiceDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class ServiceDAO extends CatalogBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(ServiceDAO.class);

	/**
	 * @param clientDO
	 */
	public ServiceDAO(ServiceDO clientDO) {
		super(clientDO);
	}

	public ServiceDO getServiceDO() {
		return (ServiceDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		ServiceDO clientDO = (ServiceDO) obj1;
		ServiceDO serverDO = (ServiceDO) super.merge(obj1, obj2);
		
		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		return serverDO;
	}

	public static List<ServiceDO> getAllServices() {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ServiceDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allServices");
			result = (List<ServiceDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static List<ServiceDO> getServiceByKeyValuePair(Long keyValuePairId) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ServiceDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupServiceByKeyValuePair");
			query.setLong(0, keyValuePairId);
			result = (List<ServiceDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}

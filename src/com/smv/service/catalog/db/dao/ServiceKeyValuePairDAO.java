/**
 * 
 */
package com.smv.service.catalog.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.catalog.db.CatalogHibernateSessionFactory;
import com.smv.service.catalog.db.dbobject.ServiceKeyValuePairDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class ServiceKeyValuePairDAO extends CatalogBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(ServiceKeyValuePairDAO.class);

	/**
	 * @param clientDO
	 */
	public ServiceKeyValuePairDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}

	public ServiceKeyValuePairDO getServiceKeyValuePairDO() {
		return (ServiceKeyValuePairDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		ServiceKeyValuePairDO clientDO = (ServiceKeyValuePairDO) obj1;
		ServiceKeyValuePairDO serverDO = (ServiceKeyValuePairDO) super.merge(obj1, obj2);
		
		if (clientDO.getKeyPair() != null) {
			serverDO.setKeyPair(clientDO.getKeyPair());
		}
		
		if (clientDO.getValuePair() != null) {
			serverDO.setValuePair(clientDO.getValuePair());
		}
		
		if (clientDO.getParentContainerId() != null) {
			serverDO.setParentContainerId(clientDO.getParentContainerId());
		}
		
		return serverDO;
	}

	public static List<ServiceKeyValuePairDO> getAllServiceKeyValuePairs() {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ServiceKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allServiceKeyValuePairs");
			result = (List<ServiceKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static List<ServiceKeyValuePairDO> getKeyValuePairByService(Long serviceId) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ServiceKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupKeyValuePairsByService");
			query.setLong(0, serviceId);
			result = (List<ServiceKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}

/**
 * 
 */
package com.smv.service.catalog.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.catalog.db.CatalogHibernateSessionFactory;
import com.smv.service.catalog.db.dbobject.ServiceCompositionDO;
import com.smv.service.catalog.db.dbobject.ServiceDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class ServiceCompositionDAO extends CatalogBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(ServiceCompositionDAO.class);

	/**
	 * @param clientDO
	 */
	public ServiceCompositionDAO(ServiceCompositionDO clientDO) {
		super(clientDO);
	}

	public ServiceCompositionDO getServiceCompositionDO() {
		return (ServiceCompositionDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		ServiceCompositionDO clientDO = (ServiceCompositionDO) obj1;
		ServiceCompositionDO serverDO = (ServiceCompositionDO) super.merge(obj1, obj2);
		
		if (clientDO.getParentServiceId() != null) {
			serverDO.setParentServiceId(clientDO.getParentServiceId());
		}
		
		if (clientDO.getChildServiceId() != null) {
			serverDO.setChildServiceId(clientDO.getChildServiceId());
		}
		
		return serverDO;
	}

	public static List<ServiceCompositionDO> getAllServiceCompositions() {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ServiceCompositionDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allServiceCompositions");
			result = (List<ServiceCompositionDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}

/**
 * 
 */
package com.smv.service.catalog.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.catalog.db.CatalogHibernateSessionFactory;
import com.smv.service.catalog.db.dbobject.CatalogDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class CatalogDAO extends CatalogBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(CatalogDAO.class);

	/**
	 * @param clientDO
	 */
	public CatalogDAO(CatalogDO clientDO) {
		super(clientDO);
	}

	public CatalogDO getCatalogDO() {
		return (CatalogDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		CatalogDO clientDO = (CatalogDO) obj1;
		CatalogDO serverDO = (CatalogDO) super.merge(obj1, obj2);
		
		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getProductId() != null) {
			serverDO.setProductId(clientDO.getProductId());
		}
		
		if (clientDO.getServiceId() != null) {
			serverDO.setServiceId(clientDO.getServiceId());
		}
		
		return serverDO;
	}

	public static List<CatalogDO> getAllCatalogs() {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<CatalogDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allCatalogs");
			result = (List<CatalogDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static List<CatalogDO> getCatalogByKeyValuePair(Long keyValuePairId) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<CatalogDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupCatalogByKeyValuePair");
			query.setLong(0, keyValuePairId);
			result = (List<CatalogDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}

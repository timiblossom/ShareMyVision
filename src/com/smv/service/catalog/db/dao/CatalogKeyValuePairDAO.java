/**
 * 
 */
package com.smv.service.catalog.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.catalog.db.CatalogHibernateSessionFactory;
import com.smv.service.catalog.db.dbobject.CatalogKeyValuePairDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class CatalogKeyValuePairDAO extends CatalogBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(CatalogKeyValuePairDAO.class);

	/**
	 * @param clientDO
	 */
	public CatalogKeyValuePairDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}

	public CatalogKeyValuePairDO getCatalogKeyValuePairDO() {
		return (CatalogKeyValuePairDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		CatalogKeyValuePairDO clientDO = (CatalogKeyValuePairDO) obj1;
		CatalogKeyValuePairDO serverDO = (CatalogKeyValuePairDO) super.merge(obj1, obj2);
		
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

	public static List<CatalogKeyValuePairDO> getAllCatalogKeyValuePairs() {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<CatalogKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allCatalogKeyValuePairs");
			result = (List<CatalogKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static List<CatalogKeyValuePairDO> getKeyValuePairByCatalog(Long catalogId) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<CatalogKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupKeyValuePairsByCatalog");
			query.setLong(0, catalogId);
			result = (List<CatalogKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}

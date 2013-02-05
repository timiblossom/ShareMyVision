/**
 * 
 */
package com.smv.service.catalog.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.catalog.db.CatalogHibernateSessionFactory;
import com.smv.service.catalog.db.dbobject.ProductDO;
import com.smv.service.catalog.db.dbobject.ProductKeyValuePairDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class ProductKeyValuePairDAO extends CatalogBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(ProductKeyValuePairDAO.class);

	/**
	 * @param clientDO
	 */
	public ProductKeyValuePairDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}

	public ProductKeyValuePairDO getProductKeyValuePairDO() {
		return (ProductKeyValuePairDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		ProductKeyValuePairDO clientDO = (ProductKeyValuePairDO) obj1;
		ProductKeyValuePairDO serverDO = (ProductKeyValuePairDO) super.merge(obj1, obj2);
		
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

	public static List<ProductKeyValuePairDO> getAllProductKeyValuePairs() {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ProductKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allProductKeyValuePairs");
			result = (List<ProductKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static List<ProductKeyValuePairDO> getKeyValuePairByProduct(Long productId) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ProductKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupKeyValuePairsByProduct");
			query.setLong(0, productId);
			result = (List<ProductKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}

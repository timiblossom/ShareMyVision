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
import com.smv.service.catalog.db.dbobject.StatusDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class ProductDAO extends CatalogBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(ProductDAO.class);

	/**
	 * @param clientDO
	 */
	public ProductDAO(ProductDO clientDO) {
		super(clientDO);
	}

	public ProductDO getProductDO() {
		return (ProductDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		ProductDO clientDO = (ProductDO) obj1;
		ProductDO serverDO = (ProductDO) super.merge(obj1, obj2);
		
		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		return serverDO;
	}

	public static List<ProductDO> getAllProducts() {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ProductDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allProducts");
			result = (List<ProductDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static List<ProductDO> getProductByKeyValuePair(Long keyValuePairId) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ProductDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupProductByKeyValuePair");
			query.setLong(0, keyValuePairId);
			result = (List<ProductDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static List<ProductDO> getProductsByStatusId(Long statusId) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ProductDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupProductsByStatusId");
			query.setLong(0, statusId);
			result = (List<ProductDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static ProductDO getProductById(Long id) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		ProductDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (ProductDO) session.get(ProductDO.class, id);
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
	
	public static List<ProductDO> getProductByIdAndStatusId(Long id, Long statusId) {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ProductDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupProductByIdAndStatusId");
			query.setLong(0, id);
			query.setLong(1, statusId);
			result = (List<ProductDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}

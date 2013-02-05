/**
 * 
 */
package com.smv.service.catalog.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.catalog.db.CatalogHibernateSessionFactory;
import com.smv.service.catalog.db.dbobject.ProductCompositionDO;
import com.smv.service.catalog.db.dbobject.ProductDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class ProductCompositionDAO extends CatalogBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(ProductCompositionDAO.class);

	/**
	 * @param clientDO
	 */
	public ProductCompositionDAO(ProductCompositionDO clientDO) {
		super(clientDO);
	}

	public ProductCompositionDO getProductCompositionDO() {
		return (ProductCompositionDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		ProductCompositionDO clientDO = (ProductCompositionDO) obj1;
		ProductCompositionDO serverDO = (ProductCompositionDO) super.merge(obj1, obj2);
		
		if (clientDO.getParentProductId() != null) {
			serverDO.setParentProductId(clientDO.getParentProductId());
		}
		
		if (clientDO.getChildProductId() != null) {
			serverDO.setChildProductId(clientDO.getChildProductId());
		}
		
		return serverDO;
	}

	public static List<ProductCompositionDO> getAllProductCompositions() {
		SmvHibernateSession session = CatalogHibernateSessionFactory.getInstance().getSession();
		List<ProductCompositionDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allProductCompositions");
			result = (List<ProductCompositionDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}

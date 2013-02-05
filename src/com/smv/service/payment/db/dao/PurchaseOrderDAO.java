/**
 * 
 */
package com.smv.service.payment.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.payment.db.PaymentHibernateSessionFactory;
import com.smv.service.payment.db.dbobject.PurchaseOrderDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class PurchaseOrderDAO extends PaymentBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(PurchaseOrderDAO.class);

	/**
	 * @param clientDO
	 */
	public PurchaseOrderDAO(PurchaseOrderDO clientDO) {
		super(clientDO);
	}

	public PurchaseOrderDO getPurchaseOrderDO() {
		return (PurchaseOrderDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		PurchaseOrderDO clientDO = (PurchaseOrderDO) obj1;
		PurchaseOrderDO serverDO = (PurchaseOrderDO) super.merge(obj1, obj2);
		
		if (clientDO.getOrderNumberId() != null) {
			serverDO.setOrderNumberId(clientDO.getOrderNumberId());
		}
		
		if (clientDO.getTotalOrderAmount() != null) {
			serverDO.setTotalOrderAmount(clientDO.getTotalOrderAmount());
		}
		
		if (clientDO.getTotalOrderQuantity() != null) {
			serverDO.setTotalOrderQuantity(clientDO.getTotalOrderQuantity());
		}
		
		if (clientDO.getOrderCode() != null) {
			serverDO.setOrderCode(clientDO.getOrderCode());
		}
		
		if (clientDO.getOrderDescription() != null) {
			serverDO.setOrderDescription(clientDO.getOrderDescription());
		}
		
		if (clientDO.getUserId() != null) {
			serverDO.setUserId(clientDO.getUserId());
		}
		
		if (clientDO.getAccountId() != null) {
			serverDO.setAccountId(clientDO.getAccountId());
		}
		
		return serverDO;
	}

	public static List<PurchaseOrderDO> getAllPurchaseOrders() {
		SmvHibernateSession session = PaymentHibernateSessionFactory.getInstance().getSession();
		List<PurchaseOrderDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allPurchaseOrders");
			result = (List<PurchaseOrderDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}


}

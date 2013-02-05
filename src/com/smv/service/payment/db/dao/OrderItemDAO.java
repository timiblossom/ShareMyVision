/**
 * 
 */
package com.smv.service.payment.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.payment.db.PaymentHibernateSessionFactory;
import com.smv.service.payment.db.dbobject.OrderItemDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class OrderItemDAO extends PaymentBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(OrderItemDAO.class);

	/**
	 * @param clientDO
	 */
	public OrderItemDAO(OrderItemDO clientDO) {
		super(clientDO);
	}

	public OrderItemDO getOrderItemDO() {
		return (OrderItemDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		OrderItemDO clientDO = (OrderItemDO) obj1;
		OrderItemDO serverDO = (OrderItemDO) super.merge(obj1, obj2);
		
		if (clientDO.getOrderId() != null) {
			serverDO.setOrderId(clientDO.getOrderId());
		}
		
		if (clientDO.getItemAmount() != null) {
			serverDO.setItemAmount(clientDO.getItemAmount());
		}
		
		if (clientDO.getItemQuantity() != null) {
			serverDO.setItemQuantity(clientDO.getItemQuantity());
		}
		
		if (clientDO.getItemCode() != null) {
			serverDO.setItemCode(clientDO.getItemCode());
		}
		
		if (clientDO.getItemDescription() != null) {
			serverDO.setItemDescription(clientDO.getItemDescription());
		}
		
		if (clientDO.getIsService() != null) {
			serverDO.setIsService(clientDO.getIsService());
		}
		
		return serverDO;
	}

	public static List<OrderItemDO> getAllOrderItems() {
		SmvHibernateSession session = PaymentHibernateSessionFactory.getInstance().getSession();
		List<OrderItemDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allOrderItems");
			result = (List<OrderItemDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}


}

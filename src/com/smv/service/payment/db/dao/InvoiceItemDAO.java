/**
 * 
 */
package com.smv.service.payment.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.payment.db.PaymentHibernateSessionFactory;
import com.smv.service.payment.db.dbobject.InvoiceItemDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class InvoiceItemDAO extends PaymentBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(InvoiceItemDAO.class);

	/**
	 * @param clientDO
	 */
	public InvoiceItemDAO(InvoiceItemDO clientDO) {
		super(clientDO);
	}

	public InvoiceItemDO getInvoiceItemDO() {
		return (InvoiceItemDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		InvoiceItemDO clientDO = (InvoiceItemDO) obj1;
		InvoiceItemDO serverDO = (InvoiceItemDO) super.merge(obj1, obj2);
		
		if (clientDO.getInvoiceId() != null) {
			serverDO.setInvoiceId(clientDO.getInvoiceId());
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
		
		return serverDO;
	}

	public static List<InvoiceItemDO> getAllInvoiceItems() {
		SmvHibernateSession session = PaymentHibernateSessionFactory.getInstance().getSession();
		List<InvoiceItemDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allInvoiceItems");
			result = (List<InvoiceItemDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}


}

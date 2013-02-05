/**
 * 
 */
package com.smv.service.payment.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.payment.db.PaymentHibernateSessionFactory;
import com.smv.service.payment.db.dbobject.InvoiceDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class InvoiceDAO extends PaymentBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(InvoiceDAO.class);

	/**
	 * @param clientDO
	 */
	public InvoiceDAO(InvoiceDO clientDO) {
		super(clientDO);
	}

	public InvoiceDO getInvoiceDO() {
		return (InvoiceDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		InvoiceDO clientDO = (InvoiceDO) obj1;
		InvoiceDO serverDO = (InvoiceDO) super.merge(obj1, obj2);
		
		if (clientDO.getInvoiceNumberId() != null) {
			serverDO.setInvoiceNumberId(clientDO.getInvoiceNumberId());
		}
		
		if (clientDO.getTotalInvoicedAmount() != null) {
			serverDO.setTotalInvoicedAmount(clientDO.getTotalInvoicedAmount());
		}
		
		if (clientDO.getTotalInvoicedQuantity() != null) {
			serverDO.setTotalInvoicedQuantity(clientDO.getTotalInvoicedQuantity());
		}
		
		if (clientDO.getOrderId() != null) {
			serverDO.setOrderId(clientDO.getOrderId());
		}
		
		if (clientDO.getInvoiceCode() != null) {
			serverDO.setInvoiceCode(clientDO.getInvoiceCode());
		}
		
		if (clientDO.getInvoiceDescription() != null) {
			serverDO.setInvoiceDescription(clientDO.getInvoiceDescription());
		}
		
		if (clientDO.getUserId() != null) {
			serverDO.setUserId(clientDO.getUserId());
		}
		
		if (clientDO.getAccountId() != null) {
			serverDO.setAccountId(clientDO.getAccountId());
		}
		
		return serverDO;
	}

	public static List<InvoiceDO> getAllInvoices() {
		SmvHibernateSession session = PaymentHibernateSessionFactory.getInstance().getSession();
		List<InvoiceDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allInvoices");
			result = (List<InvoiceDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}


}

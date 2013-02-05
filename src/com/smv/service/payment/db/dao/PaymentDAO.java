/**
 * 
 */
package com.smv.service.payment.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.payment.db.PaymentHibernateSessionFactory;
import com.smv.service.payment.db.dbobject.PaymentDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class PaymentDAO extends PaymentBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(PaymentDAO.class);

	/**
	 * @param clientDO
	 */
	public PaymentDAO(PaymentDO clientDO) {
		super(clientDO);
	}

	public PaymentDO getPaymentDO() {
		return (PaymentDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		PaymentDO clientDO = (PaymentDO) obj1;
		PaymentDO serverDO = (PaymentDO) super.merge(obj1, obj2);
		
		if (clientDO.getPaymentId() != null) {
			serverDO.setPaymentId(clientDO.getPaymentId());
		}
		
		if (clientDO.getPaymentDate() != null) {
			serverDO.setPaymentDate(clientDO.getPaymentDate());
		}
		
		if (clientDO.getPaymentMethod() != null) {
			serverDO.setPaymentMethod(clientDO.getPaymentMethod());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		if (clientDO.getInvoiceId() != null) {
			serverDO.setInvoiceId(clientDO.getInvoiceId());
		}
		
		if (clientDO.getUserId() != null) {
			serverDO.setUserId(clientDO.getUserId());
		}
		
		if (clientDO.getAccountId() != null) {
			serverDO.setAccountId(clientDO.getAccountId());
		}
		
		return serverDO;
	}

	public static List<PaymentDO> getAllPayments() {
		SmvHibernateSession session = PaymentHibernateSessionFactory.getInstance().getSession();
		List<PaymentDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allPayments");
			result = (List<PaymentDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}


}

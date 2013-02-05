/**
 * 
 */
package com.smv.service.payment.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.payment.db.PaymentHibernateSessionFactory;
import com.smv.service.payment.db.dbobject.PaymentMethodDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class PaymentMethodDAO extends PaymentBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(PaymentMethodDAO.class);

	/**
	 * @param clientDO
	 */
	public PaymentMethodDAO(PaymentMethodDO clientDO) {
		super(clientDO);
	}

	public PaymentMethodDO getPaymentMethodDO() {
		return (PaymentMethodDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		PaymentMethodDO clientDO = (PaymentMethodDO) obj1;
		PaymentMethodDO serverDO = (PaymentMethodDO) super.merge(obj1, obj2);
		
		if (clientDO.getMethodName() != null) {
			serverDO.setMethodName(clientDO.getMethodName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		return serverDO;
	}

	public static List<PaymentMethodDO> getAllPaymentMethods() {
		SmvHibernateSession session = PaymentHibernateSessionFactory.getInstance().getSession();
		List<PaymentMethodDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allPaymentMethods");
			result = (List<PaymentMethodDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}


}

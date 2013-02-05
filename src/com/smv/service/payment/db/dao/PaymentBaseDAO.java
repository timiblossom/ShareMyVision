package com.smv.service.payment.db.dao;

import org.apache.log4j.Logger;

import com.smv.service.payment.db.PaymentHibernateSessionFactory;
import com.smv.util.db.AbstractVersionedDatedDAO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.HibernateSessionFactory;

public abstract class PaymentBaseDAO extends AbstractVersionedDatedDAO {

	private static final Logger LOGGER = Logger.getLogger(PaymentBaseDAO.class);

	public PaymentBaseDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}
	
	@Override
	public HibernateSessionFactory getHibernateSessionFactory() {
		return PaymentHibernateSessionFactory.getInstance();
	}


}

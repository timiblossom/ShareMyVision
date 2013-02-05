package com.smv.service.mailer.db.dao;

import org.apache.log4j.Logger;

import com.smv.service.catalog.db.CatalogHibernateSessionFactory;
import com.smv.util.db.AbstractVersionedDatedDAO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.HibernateSessionFactory;


/**
 * @author Minh Do
 * 07/22/2010
 */
public abstract class MailerBaseDAO extends AbstractVersionedDatedDAO {

	private static final Logger LOGGER = Logger.getLogger(MailerBaseDAO.class);

	public MailerBaseDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}
	
	@Override
	public HibernateSessionFactory getHibernateSessionFactory() {
		return CatalogHibernateSessionFactory.getInstance();
	}


}

package com.smv.service.outlet.db.dao;

import org.apache.log4j.Logger;

import com.smv.service.outlet.db.OutletHibernateSessionFactory;
import com.smv.util.db.AbstractDatedDAO;
import com.smv.util.db.AbstractDatedDO;
import com.smv.util.db.HibernateSessionFactory;

public abstract class OutletBaseDAO extends AbstractDatedDAO {

	private static final Logger LOGGER = Logger.getLogger(OutletBaseDAO.class);

	public OutletBaseDAO(AbstractDatedDO clientDO) {
		super(clientDO);
	}
	
	@Override
	public HibernateSessionFactory getHibernateSessionFactory() {
		return OutletHibernateSessionFactory.getInstance();
	}


}

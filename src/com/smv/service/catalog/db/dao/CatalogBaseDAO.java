package com.smv.service.catalog.db.dao;

import org.apache.log4j.Logger;

import com.smv.service.catalog.db.CatalogHibernateSessionFactory;
import com.smv.util.db.AbstractVersionedDatedDAO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.HibernateSessionFactory;

public abstract class CatalogBaseDAO extends AbstractVersionedDatedDAO {

	private static final Logger LOGGER = Logger.getLogger(CatalogBaseDAO.class);

	public CatalogBaseDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}
	
	@Override
	public HibernateSessionFactory getHibernateSessionFactory() {
		return CatalogHibernateSessionFactory.getInstance();
	}


}

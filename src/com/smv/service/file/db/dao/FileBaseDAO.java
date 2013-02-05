package com.smv.service.file.db.dao;


import org.apache.log4j.Logger;

import com.smv.service.file.db.FileHibernateSessionFactory;
import com.smv.util.db.AbstractVersionedDatedDAO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.HibernateSessionFactory;

/**
 * @author Minh Do
 * 03/2010
 */

public abstract class FileBaseDAO extends AbstractVersionedDatedDAO {

	private static final Logger LOGGER = Logger.getLogger(FileBaseDAO.class);

	public FileBaseDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}
	
	@Override
	public HibernateSessionFactory getHibernateSessionFactory() {
		return FileHibernateSessionFactory.getInstance();
	}


}

package com.smv.service.user.db.dao;

import org.apache.log4j.Logger;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.util.db.AbstractVersionedDatedDAO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.HibernateSessionFactory;

public abstract class UserBaseDAO extends AbstractVersionedDatedDAO {

	private static final Logger LOGGER = Logger.getLogger(UserBaseDAO.class);

	public UserBaseDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}
	
	@Override
	public HibernateSessionFactory getHibernateSessionFactory() {
		return UserHibernateSessionFactory.getInstance();
	}


}

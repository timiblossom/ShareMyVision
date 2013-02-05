package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.AccountExtraInfoDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class AccountExtraInfoDAO extends UserBaseDAO {
	
	private static final Logger LOGGER = Logger.getLogger(AccountExtraInfoDAO.class);
	
	public AccountExtraInfoDAO(AccountExtraInfoDO clientDO) {
		super(clientDO);		
	}

	public AccountExtraInfoDO getAccountExtraInfoDO() {
		return (AccountExtraInfoDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		AccountExtraInfoDO clientDO = (AccountExtraInfoDO) obj1;
		AccountExtraInfoDO serverDO = (AccountExtraInfoDO) super.merge(obj1, obj2);

		if (clientDO.getKeyPair() != null) {
			serverDO.setKeyPair(clientDO.getKeyPair());
		}
		
		if (clientDO.getValuePair() != null) {
			serverDO.setValuePair(clientDO.getValuePair());
		}
		
		return serverDO;
	}

	public static List<AccountExtraInfoDO> getAllAccountExtraInfos() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<AccountExtraInfoDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allAccountExtraInfos");
			result = (List<AccountExtraInfoDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static AccountExtraInfoDO getAccountExtraInfoById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		AccountExtraInfoDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (AccountExtraInfoDO) session.get(AccountExtraInfoDO.class, id);
			session.smvCommitTransaction();
		}
		catch(Exception he) 
		{
			LOGGER.error(he);
			if(session != null)	session.smvRollback();
			return null;
	    }
		return retVal;
	}
	
}

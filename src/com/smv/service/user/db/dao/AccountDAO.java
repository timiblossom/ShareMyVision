package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.AccountDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;



public class AccountDAO extends UserBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(AccountDAO.class);
	
	public AccountDAO(AccountDO clientDO) {
		super(clientDO);
	}
	
	public AccountDO getAccountDO() {
		return (AccountDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		AccountDO clientDO = (AccountDO) obj1;
		AccountDO serverDO = (AccountDO) super.merge(obj1, obj2);

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		if (clientDO.getType() != null) {
			serverDO.setType(clientDO.getType());
		}
		
		if (clientDO.getStatus() != null) {
			serverDO.setStatus(clientDO.getStatus());
		}
		
		if (clientDO.getContactId() > 0) {
			serverDO.setContactId(clientDO.getContactId());
		}
		
		if (clientDO.getCcInfoId() > 0) {
			serverDO.setCcInfoId(clientDO.getCcInfoId());
		}
		
		if (clientDO.getExtraInfo() > 0) {
			serverDO.setExtraInfo(clientDO.getExtraInfo());
		}
		
		return serverDO;
	}

	public static List<AccountDO> getAllAccounts() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<AccountDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allAccounts");
			result = (List<AccountDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static AccountDO getAccountById(long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		AccountDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (AccountDO) session.get(AccountDO.class, id);
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

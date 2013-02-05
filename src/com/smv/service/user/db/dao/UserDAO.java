package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.UserDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class UserDAO extends UserBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(UserDAO.class);
	
	public UserDAO(UserDO clientDO) {
		super(clientDO);
	}
	
	public UserDO getUserDO() {
		return (UserDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		UserDO clientDO = (UserDO) obj1;
		UserDO serverDO = (UserDO) super.merge(obj1, obj2);

		if (clientDO.getDisplayName() != null) {
			serverDO.setDisplayName(clientDO.getDisplayName());
		}
		
		if (clientDO.getEmail() != null) {
			serverDO.setEmail(clientDO.getEmail());
		}
		
		if (clientDO.getPassword() != null) {
			serverDO.setPassword(clientDO.getPassword());
		}
		
		if (clientDO.getSalt() != null) {
			serverDO.setSalt(clientDO.getSalt());
		}
		
		if (clientDO.getStatusId() != null) {
			serverDO.setStatusId(clientDO.getStatusId());
		}
		
		if (clientDO.getTypeId() != null) {
			serverDO.setTypeId(clientDO.getTypeId());
		}
		
		if (clientDO.getActivationCode() != null) {
			serverDO.setActivationCode(clientDO.getActivationCode());
		}
		
		if (clientDO.getPasswordResetCode() != null) {
			serverDO.setPasswordResetCode(clientDO.getPasswordResetCode());
		}
		
		if (clientDO.getAccountId() != null) {
			serverDO.setAccountId(clientDO.getAccountId());
		}
		
		if (clientDO.getContactId() != null) {
			serverDO.setContactId(clientDO.getContactId());
		}
		
		if (clientDO.getUserExtraInfoId() != null) {
			serverDO.setUserExtraInfoId(clientDO.getUserExtraInfoId());
		}
		
		if (clientDO.getRoleId() != null) {
			serverDO.setRoleId(clientDO.getRoleId());
		}
		
		if (clientDO.getLanguage() != null) {
			serverDO.setLanguage(clientDO.getLanguage());
		}
		
		
		return serverDO;
	}

	public static List<UserDO> getAllUsers() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<UserDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allUsers");
			result = (List<UserDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static UserDO getUserByActivationCode(String activationCode) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		UserDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupUserByActivationCode");
			query.setString(0, activationCode);
			Object result = query.uniqueResult();
            if(result != null) retVal = (UserDO) result;
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return retVal;
	}

	public static UserDO getUserByEmail(String email) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		UserDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupUserByEmail");
			query.setString(0, email);
			Object result = query.uniqueResult();
            if(result != null) retVal = (UserDO) result;
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return retVal;
	}

	public static UserDO getUserByEmailAndPassword(String email, String password) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		UserDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupUserByEmailAndPassword");
			query.setString(0, email);
			query.setString(1, password);
			Object result = query.uniqueResult();
            if(result != null) retVal = (UserDO) result;
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return retVal;
	}

	public static UserDO getUserByAccountId(Long accountId) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		UserDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupUserByAccountId");
			query.setLong(0, accountId);
			Object result = query.uniqueResult();
            if(result != null) retVal = (UserDO) result;
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}

	public static UserDO getUserById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		UserDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (UserDO) session.get(UserDO.class, id);
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


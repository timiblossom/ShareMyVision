package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.BlockedEmailDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class BlockedEmailDAO extends UserBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(BlockedEmailDAO.class);

	public BlockedEmailDAO(BlockedEmailDO clientDO) {
		super(clientDO);
	}
	
	public BlockedEmailDO getBlockedEmailDO() {
		return (BlockedEmailDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		BlockedEmailDO clientDO = (BlockedEmailDO) obj1;
		BlockedEmailDO serverDO = (BlockedEmailDO) super.merge(obj1, obj2);

		if (clientDO.getEmailAddress() != null) {
			serverDO.setEmailAddress(clientDO.getEmailAddress());
		}
		
		return serverDO;
	}

	public static List<BlockedEmailDO> getAllBlockedEmails() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<BlockedEmailDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allBlockedEmails");
			result = (List<BlockedEmailDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static BlockedEmailDO getBlockedEmailById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		BlockedEmailDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (BlockedEmailDO) session.get(BlockedEmailDO.class, id);
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

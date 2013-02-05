package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.PermissionDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class PermissionDAO extends UserBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(PermissionDAO.class);

	public PermissionDAO(PermissionDO clientDO) {
		super(clientDO);
	}
	
	public PermissionDO getPermissionDO() {
		return (PermissionDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		PermissionDO clientDO = (PermissionDO) obj1;
		PermissionDO serverDO = (PermissionDO) super.merge(obj1, obj2);

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		return serverDO;
	}

	public static List<PermissionDO> getAllPermissions() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<PermissionDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allPermissions");
			result = (List<PermissionDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static PermissionDO getPermissionById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		PermissionDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (PermissionDO) session.get(PermissionDO.class, id);
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

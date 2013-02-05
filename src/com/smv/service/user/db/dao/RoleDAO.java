package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.RoleDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class RoleDAO extends UserBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(RoleDAO.class);

	
	public RoleDAO(RoleDO clientDO) {
		super(clientDO);
	}
	
	public RoleDO getRoleDO() {
		return (RoleDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		RoleDO clientDO = (RoleDO) obj1;
		RoleDO serverDO = (RoleDO) super.merge(obj1, obj2);

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		return serverDO;
	}

	public static List<RoleDO> getAllRoles() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<RoleDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allRoles");
			result = (List<RoleDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static RoleDO getRoleById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		RoleDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (RoleDO) session.get(RoleDO.class, id);
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
	
	public static RoleDO getRoleByNameAndDescription(String name, String description) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		RoleDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupRoleByNameAndDescription");
			query.setString(0, name);
			query.setString(1, description);
			Object result = query.uniqueResult();
            if(result != null) retVal = (RoleDO) result;
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

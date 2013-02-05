package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.RolePermissionDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class RolePermissionDAO extends UserBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(RolePermissionDAO.class);
	
	public RolePermissionDAO(RolePermissionDO clientDO) {
		super(clientDO);
	}
	
	public RolePermissionDO getRolePermissionDO() {
		return (RolePermissionDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		RolePermissionDO clientDO = (RolePermissionDO) obj1;
		RolePermissionDO serverDO = (RolePermissionDO) super.merge(obj1, obj2);

		if (clientDO.getRoleId() != null) {
			serverDO.setRoleId(clientDO.getRoleId());
		}
		
		if (clientDO.getPermissionId() != null) {
			serverDO.setPermissionId(clientDO.getPermissionId());
		}
		
		return serverDO;
	}

	public static List<RolePermissionDO> getAllRolePermissions() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<RolePermissionDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allRolePermissions");
			result = (List<RolePermissionDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static RolePermissionDO getRolePermissionById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		RolePermissionDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (RolePermissionDO) session.get(RolePermissionDO.class, id);
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
	
	public static List<RolePermissionDO> getRolePermissionByRoleId(Long roleId) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<RolePermissionDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupRolePermissionsByRoleId");
			query.setLong(0, roleId);
			result = (List<RolePermissionDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}
	
}

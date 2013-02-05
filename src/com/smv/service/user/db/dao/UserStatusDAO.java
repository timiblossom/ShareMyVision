/**
 * 
 */
package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.UserStatusDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class UserStatusDAO extends UserBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(UserStatusDAO.class);
	
	public UserStatusDAO(UserStatusDO clientDO) {
		super(clientDO);
	}
	
	public UserStatusDO getUserStatusDO() {
		return (UserStatusDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		UserStatusDO clientDO = (UserStatusDO) obj1;
		UserStatusDO serverDO = (UserStatusDO) super.merge(obj1, obj2);

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		return serverDO;
	}

	public static List<UserStatusDO> getAllUserStatus() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<UserStatusDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allUserStatus");
			result = (List<UserStatusDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static UserStatusDO getUserStatusById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		UserStatusDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (UserStatusDO) session.get(UserStatusDO.class, id);
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

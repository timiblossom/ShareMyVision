/**
 * 
 */
package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.UserTypeDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class UserTypeDAO extends UserBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(UserTypeDAO.class);
	
	/**
	 * @param clientDO
	 */
	public UserTypeDAO(UserTypeDO clientDO) {
		super(clientDO);
	}
	
	public UserTypeDO getUserTypeDO() {
		return (UserTypeDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		UserTypeDO clientDO = (UserTypeDO) obj1;
		UserTypeDO serverDO = (UserTypeDO) super.merge(obj1, obj2);

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		return serverDO;
	}

	public static List<UserTypeDO> getAllUserTypes() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<UserTypeDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allUserTypes");
			result = (List<UserTypeDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static UserTypeDO getUserTypeById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		UserTypeDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (UserTypeDO) session.get(UserTypeDO.class, id);
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

package com.smv.service.outlet.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.outlet.db.OutletHibernateSessionFactory;
import com.smv.service.outlet.db.dbobject.UserOutletKeyValuePairDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class UserOutletKeyValuePairDAO extends OutletBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(UserOutletKeyValuePairDAO.class);

	/**
	 * @param clientDO
	 */
	public UserOutletKeyValuePairDAO(UserOutletKeyValuePairDO clientDO) {
		super(clientDO);
	}

	public UserOutletKeyValuePairDO getUserOutletKeyValuePairDO() {
		return (UserOutletKeyValuePairDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		UserOutletKeyValuePairDO clientDO = (UserOutletKeyValuePairDO) obj1;
		UserOutletKeyValuePairDO serverDO = (UserOutletKeyValuePairDO) super.merge(obj1, obj2);
		
		if (clientDO.getKeyPair() != null) {
			serverDO.setKeyPair(clientDO.getKeyPair());
		}
		
		if (clientDO.getValuePair() != null) {
			serverDO.setValuePair(clientDO.getValuePair());
		}
		
		if (clientDO.getParentContainerId() != null) {
			serverDO.setParentContainerId(clientDO.getParentContainerId());
		}
		
		return serverDO;
	}

	public static List<UserOutletKeyValuePairDO> getAllUserOutletKeyValuePairs() {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<UserOutletKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allUserOutletKeyValuePairs");
			result = (List<UserOutletKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static List<UserOutletKeyValuePairDO> getKeyValuePairByUserOutlet(Long userOutletId) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<UserOutletKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupKeyValuePairsByUserOutletId");
			query.setLong(0, userOutletId);
			result = (List<UserOutletKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}

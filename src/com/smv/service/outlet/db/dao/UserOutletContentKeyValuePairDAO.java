package com.smv.service.outlet.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.outlet.db.OutletHibernateSessionFactory;
import com.smv.service.outlet.db.dbobject.UserOutletContentKeyValuePairDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class UserOutletContentKeyValuePairDAO extends OutletBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(UserOutletContentKeyValuePairDAO.class);

	/**
	 * @param clientDO
	 */
	public UserOutletContentKeyValuePairDAO(UserOutletContentKeyValuePairDO clientDO) {
		super(clientDO);
	}

	public UserOutletContentKeyValuePairDO getUserOutletContentKeyValuePairDO() {
		return (UserOutletContentKeyValuePairDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		UserOutletContentKeyValuePairDO clientDO = (UserOutletContentKeyValuePairDO) obj1;
		UserOutletContentKeyValuePairDO serverDO = (UserOutletContentKeyValuePairDO) super.merge(obj1, obj2);
		
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

	public static List<UserOutletContentKeyValuePairDO> getAllUserOutletContentKeyValuePairs() {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<UserOutletContentKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allUserOutletContentKeyValuePairs");
			result = (List<UserOutletContentKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static List<UserOutletContentKeyValuePairDO> getKeyValuePairByUserOutletContent(Long userOutletContentId) {
		SmvHibernateSession session = OutletHibernateSessionFactory.getInstance().getSession();
		List<UserOutletContentKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupKeyValuePairsByUserOutletContentId");
			query.setLong(0, userOutletContentId);
			result = (List<UserOutletContentKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

}

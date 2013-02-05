package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.CcInfoDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class CcInfoDAO extends UserBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(CcInfoDAO.class);

	
	public CcInfoDAO(CcInfoDO clientDO) {
		super(clientDO);
	}

	
	public CcInfoDO getCcInfoDO() {
		return (CcInfoDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		CcInfoDO clientDO = (CcInfoDO) obj1;
		CcInfoDO serverDO = (CcInfoDO) super.merge(obj1, obj2);

		if (clientDO.getLastFour() != null) {
			serverDO.setLastFour(clientDO.getLastFour());
		}
		
		if (clientDO.getExpireMmyy() != null) {
			serverDO.setExpireMmyy(clientDO.getExpireMmyy());
		}
		
		if (clientDO.getType() != null) {
			serverDO.setType(clientDO.getType());
		}
		
		if (clientDO.getStatus() != null) {
			serverDO.setStatus(clientDO.getStatus());
		}
		
		if (clientDO.getAddressId() > 0) {
			serverDO.setAddressId(clientDO.getAddressId());
		}
		
		return serverDO;
	}

	public static List<CcInfoDO> getAllCcInfos() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<CcInfoDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allCcInfos");
			result = (List<CcInfoDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static CcInfoDO getCcInfoById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		CcInfoDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (CcInfoDO) session.get(CcInfoDO.class, id);
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

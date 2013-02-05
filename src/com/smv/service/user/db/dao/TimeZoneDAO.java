package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.TimeZoneDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class TimeZoneDAO extends UserBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(TimeZoneDAO.class);

	public TimeZoneDAO(TimeZoneDO clientDO) {
		super(clientDO);
	}
	
	public TimeZoneDO getTimeZoneDO() {
		return (TimeZoneDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		TimeZoneDO clientDO = (TimeZoneDO) obj1;
		TimeZoneDO serverDO = (TimeZoneDO) super.merge(obj1, obj2);

		if (clientDO.getTimeZone() != null) {
			serverDO.setTimeZone(clientDO.getTimeZone());
		}
		
		if (clientDO.getLabel() != null) {
			serverDO.setLabel(clientDO.getLabel());
		}
		
		return serverDO;
	}

	public static List<TimeZoneDO> getAllTimeZones() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<TimeZoneDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allTimeZones");
			result = (List<TimeZoneDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static TimeZoneDO getTimeZoneById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		TimeZoneDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (TimeZoneDO) session.get(TimeZoneDO.class, id);
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

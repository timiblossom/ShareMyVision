/**
 * 
 */
package com.smv.service.mailer.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.mailer.db.MailerHibernateSessionFactory;
import com.smv.service.mailer.db.dbobject.EventCodeDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.SmvHibernateSession;


/**
 * @author TriNguyen
 *
 */
public class EventCodeDAO extends MailerBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(EventCodeDAO.class);
	
	public EventCodeDAO(EventCodeDO clientDO) {
		super(clientDO);
	}
	
	/**
	 * @param clientDO
	 */
	public EventCodeDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}

	public EventCodeDO getEventCodeDO() {
		return (EventCodeDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		EventCodeDO clientDO = (EventCodeDO) obj1;
		EventCodeDO serverDO = (EventCodeDO) super.merge(obj1, obj2);
		
		// Mapping from client <-> server objects
		
		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		return serverDO;
	}

	public static List<EventCodeDO> getAllEventCodes() {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<EventCodeDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allEventCodes");
			result = (List<EventCodeDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static EventCodeDO getEventCodeById(Long id) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		EventCodeDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (EventCodeDO) session.get(EventCodeDO.class, id);
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

	public static EventCodeDO getEventCodeByName(String name) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		EventCodeDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupEventCodeByName");
			query.setString(0, name);
			Object result = query.uniqueResult();
            if(result != null) {
            	retVal = (EventCodeDO) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}
	
}

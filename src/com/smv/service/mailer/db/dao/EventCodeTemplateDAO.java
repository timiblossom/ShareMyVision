/**
 * 
 */
package com.smv.service.mailer.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.mailer.db.MailerHibernateSessionFactory;
import com.smv.service.mailer.db.dbobject.EventCodeTemplateDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class EventCodeTemplateDAO extends MailerBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(EventCodeTemplateDAO.class);
	
	/**
	 * @param clientDO
	 */
	public EventCodeTemplateDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}

	public EventCodeTemplateDO getEventCodeTemplateDO() {
		return (EventCodeTemplateDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		EventCodeTemplateDO clientDO = (EventCodeTemplateDO) obj1;
		EventCodeTemplateDO serverDO = (EventCodeTemplateDO) super.merge(obj1, obj2);
		
		// Mapping from client <-> server objects

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getEventCodeId() != null) {
			serverDO.setEventCodeId(clientDO.getEventCodeId());
		}
		
		if (clientDO.getSubjectTemplateId() != null) {
			serverDO.setSubjectTemplateId(clientDO.getSubjectTemplateId());
		}
		
		if (clientDO.getBodyTemplateId() != null) {
			serverDO.setBodyTemplateId(clientDO.getBodyTemplateId());
		}
		
		return serverDO;
	}

	public static List<EventCodeTemplateDO> getAllEventCodeTemplates() {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<EventCodeTemplateDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allEventCodeTemplates");
			result = (List<EventCodeTemplateDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static EventCodeTemplateDO getEventCodeTemplateById(Long id) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		EventCodeTemplateDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (EventCodeTemplateDO) session.get(EventCodeTemplateDO.class, id);
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

	public static List<EventCodeTemplateDO> getEventCodeTemplateByName(String name) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<EventCodeTemplateDO> retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupEventCodeTemplateByName");
			query.setString(0, name);
			Object result = query.list();
            if(result != null)  {
            	retVal = (List<EventCodeTemplateDO>) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}
	
	public static EventCodeTemplateDO getEventCodeTemplateByEventCodeId(Long eventCodeId) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		EventCodeTemplateDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupEventCodeTemplateByEventCodeId");
			query.setLong(0, eventCodeId);
			Object result = query.uniqueResult();
            if(result != null) {
            	retVal = (EventCodeTemplateDO) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}
	
	public static List<EventCodeTemplateDO> lookupEventCodeTemplateBySubjectTemplateId(Long subjectTemplateId) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<EventCodeTemplateDO> retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupEventCodeTemplateBySubjectTemplateId");
			query.setLong(0, subjectTemplateId);
			Object result = query.list();
            if(result != null)  {
            	retVal = (List<EventCodeTemplateDO>) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}
	
	public static List<EventCodeTemplateDO> lookupEventCodeTemplateByBodyTemplateId(Long bodyTemplateId) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<EventCodeTemplateDO> retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupEventCodeTemplateByBodyTemplateId");
			query.setLong(0, bodyTemplateId);
			Object result = query.list();
            if(result != null)  {
            	retVal = (List<EventCodeTemplateDO>) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}
	
}

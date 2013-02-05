/**
 * 
 */
package com.smv.service.mailer.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.mailer.db.MailerHibernateSessionFactory;
import com.smv.service.mailer.db.dbobject.BodyTemplateDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class BodyTemplateDAO extends MailerBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(BodyTemplateDAO.class);
	
	/**
	 * @param clientDO
	 */
	public BodyTemplateDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}

	public BodyTemplateDO getBodyTemplateDO() {
		return (BodyTemplateDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		BodyTemplateDO clientDO = (BodyTemplateDO) obj1;
		BodyTemplateDO serverDO = (BodyTemplateDO) super.merge(obj1, obj2);
		
		// Mapping from client <-> server objects

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		if (clientDO.getFileLocation() != null) {
			serverDO.setFileLocation(clientDO.getFileLocation());
		}
		
		return serverDO;
	}

	public static List<BodyTemplateDO> getAllBodyTemplates() {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<BodyTemplateDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allBodyTemplates");
			result = (List<BodyTemplateDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static BodyTemplateDO getBodyTemplateById(Long id) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		BodyTemplateDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (BodyTemplateDO) session.get(BodyTemplateDO.class, id);
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

	public static List<BodyTemplateDO> getBodyTemplateByName(String name) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<BodyTemplateDO> retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupBodyTemplateByName");
			query.setString(0, name);
			Object result = query.list();
            if(result != null)  {
            	retVal = (List<BodyTemplateDO>) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}
	
}

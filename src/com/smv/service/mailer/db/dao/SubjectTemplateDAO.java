/**
 * 
 */
package com.smv.service.mailer.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.mailer.db.MailerHibernateSessionFactory;
import com.smv.service.mailer.db.dbobject.EventCodeDO;
import com.smv.service.mailer.db.dbobject.SubjectTemplateDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class SubjectTemplateDAO extends MailerBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(SubjectTemplateDAO.class);
	
	/**
	 * @param clientDO
	 */
	public SubjectTemplateDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}

	public SubjectTemplateDO getSubjectTemplateDO() {
		return (SubjectTemplateDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		SubjectTemplateDO clientDO = (SubjectTemplateDO) obj1;
		SubjectTemplateDO serverDO = (SubjectTemplateDO) super.merge(obj1, obj2);
		
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

	public static List<SubjectTemplateDO> getAllSubjectTemplates() {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<SubjectTemplateDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allSubjectTemplates");
			result = (List<SubjectTemplateDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static SubjectTemplateDO getSubjectTemplateById(Long id) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		SubjectTemplateDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (SubjectTemplateDO) session.get(SubjectTemplateDO.class, id);
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

	public static List<SubjectTemplateDO> getSubjectTemplateByName(String name) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<SubjectTemplateDO> retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupSubjectTemplateByName");
			query.setString(0, name);
			Object result = query.list();
            if(result != null)  {
            	retVal = (List<SubjectTemplateDO>) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}
	
}

/**
 * 
 */
package com.smv.service.mailer.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.mailer.db.MailerHibernateSessionFactory;
import com.smv.service.mailer.db.dbobject.SubjectTemplateKeyValuePairDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class SubjectTemplateKeyValuePairDAO extends MailerBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(SubjectTemplateKeyValuePairDAO.class);
	
	/**
	 * @param clientDO
	 */
	public SubjectTemplateKeyValuePairDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}

	public SubjectTemplateKeyValuePairDO getSubjectTemplateKeyValuePairDO() {
		return (SubjectTemplateKeyValuePairDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		SubjectTemplateKeyValuePairDO clientDO = (SubjectTemplateKeyValuePairDO) obj1;
		SubjectTemplateKeyValuePairDO serverDO = (SubjectTemplateKeyValuePairDO) super.merge(obj1, obj2);
		
		// Mapping from client <-> server objects

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		if (clientDO.getSubjectTemplateId() != null) {
			serverDO.setSubjectTemplateId(clientDO.getSubjectTemplateId());
		}
		
		if (clientDO.getKeyPair() != null) {
			serverDO.setKeyPair(clientDO.getKeyPair());
		}
		
		if (clientDO.getDefaultValuePair() != null) {
			serverDO.setDefaultValuePair(clientDO.getDefaultValuePair());
		}
		
		return serverDO;
	}

	public static List<SubjectTemplateKeyValuePairDO> getAllSubjectTemplateKeyValuePairs() {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<SubjectTemplateKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allSubjectTemplateKeyValuePairs");
			result = (List<SubjectTemplateKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static SubjectTemplateKeyValuePairDO getSubjectTemplateKeyValuePairById(Long id) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		SubjectTemplateKeyValuePairDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (SubjectTemplateKeyValuePairDO) session.get(SubjectTemplateKeyValuePairDO.class, id);
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

	public static List<SubjectTemplateKeyValuePairDO> getSubjectTemplateKeyValuePairByName(String name) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<SubjectTemplateKeyValuePairDO> retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupSubjectTemplateKeyValuePairByName");
			query.setString(0, name);
			Object result = query.list();
            if(result != null)  {
            	retVal = (List<SubjectTemplateKeyValuePairDO>) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}
	
}

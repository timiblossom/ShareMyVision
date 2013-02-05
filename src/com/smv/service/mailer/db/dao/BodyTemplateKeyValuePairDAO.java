/**
 * 
 */
package com.smv.service.mailer.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.mailer.db.MailerHibernateSessionFactory;
import com.smv.service.mailer.db.dbobject.EventCodeTemplateDO;
import com.smv.service.mailer.db.dbobject.BodyTemplateKeyValuePairDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.AbstractVersionedDatedDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author TriNguyen
 *
 */
public class BodyTemplateKeyValuePairDAO extends MailerBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(BodyTemplateDAO.class);
	
	/**
	 * @param clientDO
	 */
	public BodyTemplateKeyValuePairDAO(AbstractVersionedDatedDO clientDO) {
		super(clientDO);
	}

	public BodyTemplateKeyValuePairDO getBodyTemplateKeyValuePairDO() {
		return (BodyTemplateKeyValuePairDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		BodyTemplateKeyValuePairDO clientDO = (BodyTemplateKeyValuePairDO) obj1;
		BodyTemplateKeyValuePairDO serverDO = (BodyTemplateKeyValuePairDO) super.merge(obj1, obj2);
		
		// Mapping from client <-> server objects

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		if (clientDO.getBodyTemplateId() != null) {
			serverDO.setBodyTemplateId(clientDO.getBodyTemplateId());
		}
		
		if (clientDO.getKeyPair() != null) {
			serverDO.setKeyPair(clientDO.getKeyPair());
		}
		
		if (clientDO.getDefaultValuePair() != null) {
			serverDO.setDefaultValuePair(clientDO.getDefaultValuePair());
		}
		
		return serverDO;
	}

	public static List<BodyTemplateKeyValuePairDO> getAllBodyTemplateKeyValuePairs() {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<BodyTemplateKeyValuePairDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allBodyTemplateKeyValuePairs");
			result = (List<BodyTemplateKeyValuePairDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static BodyTemplateKeyValuePairDO getBodyTemplateKeyValuePairById(Long id) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		BodyTemplateKeyValuePairDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (BodyTemplateKeyValuePairDO) session.get(BodyTemplateKeyValuePairDO.class, id);
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

	public static List<BodyTemplateKeyValuePairDO> getBodyTemplateKeyValuePairByName(String name) {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<BodyTemplateKeyValuePairDO> retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("lookupBodyTemplateKeyValuePairByName");
			query.setString(0, name);
			Object result = query.list();
            if(result != null)  {
            	retVal = (List<BodyTemplateKeyValuePairDO>) result;
            }
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
		}
		
		return retVal;
	}
	
}

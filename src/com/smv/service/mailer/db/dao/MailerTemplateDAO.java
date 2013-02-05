/**
 * 
 */
package com.smv.service.mailer.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.mailer.db.MailerHibernateSessionFactory;
import com.smv.service.mailer.db.dbobject.MailerTemplateDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author Minh Do
 *
 */
public class MailerTemplateDAO extends MailerBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(MailerTemplateDAO.class);

	/**
	 * @param clientDO
	 */
	public MailerTemplateDAO(MailerTemplateDO clientDO) {
		super(clientDO);
	}

	public MailerTemplateDO getEmailerTemplateDO() {
		return (MailerTemplateDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		MailerTemplateDO clientDO = (MailerTemplateDO) obj1;
		MailerTemplateDO serverDO = (MailerTemplateDO) super.merge(obj1, obj2);
		
		//TODO: finishing the mapping here from client <-> server objects
		
		return serverDO;
	}

	public static List<MailerTemplateDO> getAllTemplates() {
		SmvHibernateSession session = MailerHibernateSessionFactory.getInstance().getSession();
		List<MailerTemplateDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allTemplates");
			result = (List<MailerTemplateDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static MailerTemplateDO getTemplateByName(String name) {
		return null; //TODO: retrieve template by name from db
	}
	
	public static MailerTemplateDO getTemplateById(Integer id) {
		return null; //TODO: retrieve template by id from db
	}
}

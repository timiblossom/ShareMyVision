package com.smv.service.file.db.dao;



import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.file.db.FileHibernateSessionFactory;
import com.smv.service.file.db.dbobject.FilePolicyDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;


/**
 * @author Minh Do
 * 03/2010
 */

public class FilePolicyDAO extends FileBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(FilePolicyDAO.class);
	
	public FilePolicyDAO(FilePolicyDO clientDO) {
		super(clientDO);
	}
	
	public FilePolicyDO getFilePolicyDO() {
		return (FilePolicyDO) iClientDO;
	}

	@Override
	public AbstractDO merge(final AbstractDO obj1, final AbstractDO obj2) {
		FilePolicyDO clientDO = (FilePolicyDO) obj1;
		FilePolicyDO serverDO = (FilePolicyDO) super.merge(obj1, obj2);
		
		
		if (clientDO.getPolicyId() != null) {
			serverDO.setPolicyId(clientDO.getPolicyId());
		}

		if (clientDO.getName() != null) {
			serverDO.setName(clientDO.getName());
		}
		
		if (clientDO.getValue() != null) {
			serverDO.setValue(clientDO.getValue());
		}
		
		
		return serverDO;
	}

	
	public static FilePolicyDO geFilePolicyById(final Long id) {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter geFilePolicyById - input : { id = " + id + "}");
		}
		
		SmvHibernateSession session = FileHibernateSessionFactory.getInstance().getSession();
		FilePolicyDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (FilePolicyDO) session.get(FilePolicyDO.class, id);			
			session.smvCommitTransaction();
		} catch(Exception he) {
			LOGGER.error(he);
			session.smvRollback();
	    }
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit geFilePolicyById - input : { id = " + id + "}");
		}
		
		return retVal;
	}
	
	public static List<FilePolicyDO> getFilePoliciesByPolicyId(final Long policyId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getFilePoliciesByPolicyId - input : { policyId = " + policyId + "}");
		}
		
		SmvHibernateSession session = FileHibernateSessionFactory.getInstance().getSession();
		List<FilePolicyDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("getFilePoliciesByPolicyId");		
			query.setLong(0, policyId);
			result = (List<FilePolicyDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			session.smvRollback();
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getFilePoliciesByPolicyId - input : { policyId = " + policyId + "}");
		}
		
		return result;
	}

}

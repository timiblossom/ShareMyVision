package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.UserExtraInfoDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class UserExtraInfoDAO extends UserBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(UserExtraInfoDAO.class);
	
	public UserExtraInfoDAO(UserExtraInfoDO clientDO) {
		super(clientDO);
	}
	
	public UserExtraInfoDO getUserExtraInfoDO() {
		return (UserExtraInfoDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		UserExtraInfoDO clientDO = (UserExtraInfoDO) obj1;
		UserExtraInfoDO serverDO = (UserExtraInfoDO) super.merge(obj1, obj2);

		if (clientDO.getIndustry() != null) {
			serverDO.setIndustry(clientDO.getIndustry());
		}
		
		if (clientDO.getCompany() != null) {
			serverDO.setCompany(clientDO.getCompany());
		}
		
		if (clientDO.getCompanySize() != null) {
			serverDO.setCompanySize(clientDO.getCompanySize());
		}
		
		if (clientDO.getZipCode() != null) {
			serverDO.setZipCode(clientDO.getZipCode());
		}
		
		if (clientDO.getTimeZoneId() != null) {
			serverDO.setTimeZoneId(clientDO.getTimeZoneId());
		}
		
		if (clientDO.getTitle() != null) {
			serverDO.setTitle(clientDO.getTitle());
		}
		
		if (clientDO.getJobTitle() != null) {
			serverDO.setJobTitle(clientDO.getJobTitle());
		}
		
		if (clientDO.getMobileDeviceModel() != null) {
			serverDO.setMobileDeviceModel(clientDO.getMobileDeviceModel());
		}
		
		if (clientDO.getMobileManufacturer() != null) {
			serverDO.setMobileManufacturer(clientDO.getMobileManufacturer());
		}
		
		return serverDO;
	}

	public static List<UserExtraInfoDO> getAllUserExtraInfos() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<UserExtraInfoDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allUserExtraInfos");
			result = (List<UserExtraInfoDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static UserExtraInfoDO getUserExtraInfoById(Long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		UserExtraInfoDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (UserExtraInfoDO) session.get(UserExtraInfoDO.class, id);
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

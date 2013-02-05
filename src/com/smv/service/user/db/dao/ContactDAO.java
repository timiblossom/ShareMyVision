package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.ContactDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

public class ContactDAO extends UserBaseDAO {

	private static final Logger LOGGER = Logger.getLogger(ContactDAO.class);
	
	public ContactDAO(ContactDO clientDO) {
		super(clientDO);
	}
	
	public ContactDO getContactDO() {
		return (ContactDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		ContactDO clientDO = (ContactDO) obj1;
		ContactDO serverDO = (ContactDO) super.merge(obj1, obj2);

		if (clientDO.getFirstName() != null) {
			serverDO.setFirstName(clientDO.getFirstName());
		}
		
		if (clientDO.getLastName() != null) {
			serverDO.setLastName(clientDO.getLastName());
		}
		
		if (clientDO.getAdditionalEmail() != null) {
			serverDO.setAdditionalEmail(clientDO.getAdditionalEmail());
		}
		
		if (clientDO.getResidentAddressId() > 0) {
			serverDO.setResidentAddressId(clientDO.getResidentAddressId());
		}
		
		if (clientDO.getShippingAddressId() > 0) {
			serverDO.setShippingAddressId(clientDO.getShippingAddressId());
		}
		
		if (clientDO.getBillingAddressId() > 0) {
			serverDO.setBillingAddressId(clientDO.getBillingAddressId());
		}
		
		if (clientDO.getWorkPhone() != null) {
			serverDO.setWorkPhone(clientDO.getWorkPhone());
		}
		
		if (clientDO.getMobilePhone() != null) {
			serverDO.setMobilePhone(clientDO.getMobilePhone());
		}
		
		if (clientDO.getHomePhone() != null) {
			serverDO.setHomePhone(clientDO.getHomePhone());
		}
		
		if (clientDO.getAim() != null) {
			serverDO.setAim(clientDO.getAim());
		}
		
		if (clientDO.getYim() != null) {
			serverDO.setYim(clientDO.getYim());
		}
		
		if (clientDO.getSkype() != null) {
			serverDO.setSkype(clientDO.getSkype());
		}
		
		if (clientDO.getFacebook() != null) {
			serverDO.setFacebook(clientDO.getFacebook());
		}
		
		if (clientDO.getTwitter() != null) {
			serverDO.setTwitter(clientDO.getTwitter());
		}
		
		return serverDO;
	}

	public static List<ContactDO> getAllContacts() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<ContactDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allContacts");
			result = (List<ContactDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}

	public static ContactDO getContactById(long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		ContactDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (ContactDO) session.get(ContactDO.class, id);
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

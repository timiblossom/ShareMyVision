package com.smv.service.user.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.user.db.UserHibernateSessionFactory;
import com.smv.service.user.db.dbobject.AddressDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;


public class AddressDAO extends UserBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(AddressDAO.class);
	
	public AddressDAO(AddressDO clientDO) {
		super(clientDO);
	}

	public AddressDO getAddressDO() {
		return (AddressDO) iClientDO;
	}

	protected void initializeSubDAOs() {
	}

	protected boolean saveSubBOs() {
		return true;
	}

	protected boolean reconcileDefaultValues() {
		
		iServerDO = merge(getClientDO(), getServerDO());
		
		return true;
	}
	

	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		AddressDO clientDO = (AddressDO) obj1;
		AddressDO serverDO = (AddressDO) obj2;

		if (obj1 == null) {
			LOGGER.debug("obj1 or clientVO is null - failed to continue merging");
			return obj1;
		}

		if (obj2 == null) {
			LOGGER.debug("obj2 or serverVO is null - failed to continue merging");
			return obj2;
		}

		
		if (obj1 == obj2) {
			return obj1;
		}


		if (clientDO.getStreet() != null) {
			serverDO.setStreet(clientDO.getStreet());
		}

		if (clientDO.getCity() != null) {
			serverDO.setCity(clientDO.getCity());
		}

		if (clientDO.getState() != null) {
			serverDO.setState(clientDO.getState());
		}
		
		if (clientDO.getZipCode() != null) {
			serverDO.setZipCode(clientDO.getZipCode());
		}
		
		if (clientDO.getCountry() != null) {
			serverDO.setCountry(clientDO.getCountry());
		}
		
		if (clientDO.getLatitude() != 0) {
			serverDO.setLatitude(clientDO.getLatitude());
		}
		
		if (clientDO.getLongitude() != 0) {
			serverDO.setLongitude(clientDO.getLongitude());
		}
		
		return serverDO;
	}

	public static List<AddressDO> getAllAddresses() {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<AddressDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("allAddresses");
			result = (List<AddressDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
	}
	
	public static AddressDO getAddressById(long id) {
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		AddressDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (AddressDO) session.get(AddressDO.class, id);
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
	
	public static List<AddressDO> getAddressesByState(String state) {
		
		SmvHibernateSession session = UserHibernateSessionFactory.getInstance().getSession();
		List<AddressDO> result = null;
		
		try {
			session.beginTransaction();
			
			Query query = session.getNamedQuery("lookupAddressesByState");
			query.setString(0, state);
			result = (List<AddressDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			if(session != null) session.smvRollback();
			return null;
		}
		
		return result;
		
	}

}

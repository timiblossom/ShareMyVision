package com.smv.service.file.db.dao;



import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.common.Constant;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.file.db.FileHibernateSessionFactory;
import com.smv.service.file.db.dbobject.EventDO;
import com.smv.service.file.db.dbobject.ItemDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;


/**
 * @author Minh Do
 * 03/2010
 */

public class EventDAO extends FileBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(EventDAO.class);
	
	public EventDAO(EventDO clientDO) {
		super(clientDO);
	}
	
	public EventDO getEventDO() {
		return (EventDO) iClientDO;
	}

	@Override
	protected boolean reconcileDerivedValues() {
		EventDO event = getEventDO();
		
		if (event.getOperation() == AbstractDO.CREATE) {		
			event.setPostedOn(new Timestamp(System.currentTimeMillis()) );
			if (event.getIsPublic() == null) {
				event.setIsPublic(Boolean.FALSE);
			}
			
			if (event.getStatus() == null) {
				event.setStatus(Constant.ACTIVE_STATUS);
			}
				
		}
		return false;
	}
	
	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		EventDO clientDO = (EventDO) obj1;
		EventDO serverDO = (EventDO) super.merge(obj1, obj2);

		//if (clientDO.getAccountId() != null) {
		//	serverDO.setAccountId(clientVO.getAccountId());
		//}
		
		if (clientDO.getEventCode() != null) {
			serverDO.setEventCode(clientDO.getEventCode());
		}
		
		if (clientDO.getUid() != null) {
			serverDO.setUid(clientDO.getUid());
		}
		
		if (clientDO.getAid() != null && clientDO.getAid().longValue() != 0) {
			serverDO.setAid(clientDO.getAid());
		}

		if (clientDO.getEventTitle() != null) {
			serverDO.setEventTitle(clientDO.getEventTitle());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		if (clientDO.getFolderUrl() != null) {
			serverDO.setFolderUrl(clientDO.getFolderUrl());
		}
		
		if (clientDO.getStatus() != null) {
			serverDO.setStatus(clientDO.getStatus());
		}
		
		if (clientDO.getTagText() != null) {
			serverDO.setTagText(clientDO.getTagText());
		}

		if (clientDO.getIsPublic() != null) {
			serverDO.setIsPublic(clientDO.getIsPublic());
		}
		
		if (clientDO.getPostedOn() != null) {
			serverDO.setPostedOn(clientDO.getPostedOn());
		}
		
		if (clientDO.getExpiredOn() != null) {
			serverDO.setExpiredOn(clientDO.getExpiredOn());
		}
		
		
		return serverDO;
	}

	
	public static EventDO getEventById(Long id) throws SmvServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getEventById - input : { id = " + id + "}");
		}
		
		SmvHibernateSession session = FileHibernateSessionFactory.getInstance().getSession();
		EventDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (EventDO) session.get(EventDO.class, id);
			Set<ItemDO> items = retVal.getItems();
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				iter.next();
			}
			
			session.smvCommitTransaction();
		} catch(Exception he) {
			LOGGER.error(he);
			session.smvRollback();
			throw new SmvServiceException(SmvErrorCode.SYSTEM_DATABASE_ISSUE_EXCEPTION, SmvErrorCode.SYSTEM_DATABASE_ISSUE_EXCEPTION_MSG);
	    }
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getEventById - input : { id = " + id + "}");
		}
		
		return retVal;
	}
	
	public static List<EventDO> lookupEventsByUserId(Long uid, boolean loadItems) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter lookupEventsByUserId - input : { uid = " + uid + ", loadItems = " + loadItems + "}");
		}
		
		SmvHibernateSession session = FileHibernateSessionFactory.getInstance().getSession();
		List<EventDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("getEventsByUserId");
			query.setLong(0, uid);
			
			result = (List<EventDO>) query.list();
			if (result != null) {
				if (loadItems) {
					for(EventDO event : result) {
						//load items into the set now - using this way due to lazy set
						Set<ItemDO> items = event.getItems();
						Iterator iter = items.iterator();
						while (iter.hasNext()) {
							iter.next();
						}
					}
				} else {				
					for(EventDO event : result) {
						event.setItems(null);
					}
				}
			}
			session.smvCommitTransaction();
			

		} catch (Exception e) {
			LOGGER.error(e);
			session.smvRollback();
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit lookupEventsByUserId - input : { uid = " + uid + ", loadItems = " + loadItems + "}");
		}
		
		return result;
	}
	

	public static List<EventDO> lookupEventsNoItemsByUserId(Long uid) {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter lookupEventsNoItemsByUserId - uid : " + uid);
		}
		
		SmvHibernateSession session = FileHibernateSessionFactory.getInstance().getSession();
		List<EventDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("getEventsByUserId");
			query.setLong(0, uid);
			result = (List<EventDO>) query.list();
			session.smvCommitTransaction();
			
			for(EventDO event : result) {
				event.setItems(null); 
			}
		} catch (Exception e) {
			LOGGER.error(e);
			session.smvRollback();
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit lookupEventsNoItemsByUserId - uid : " + uid);
		}
		
		return result;
	}
	
	public static EventDO lookupEventByEventCode(String eventCode) {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter lookupEventByEventCode - eventCode : " + eventCode);
		}
		
		SmvHibernateSession session = FileHibernateSessionFactory.getInstance().getSession();
		List<EventDO> result = null;
		EventDO retVal = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("getEventByEventCode");
			query.setString(0, eventCode);
			result = (List<EventDO>) query.list();
			session.smvCommitTransaction();
			
			if (result != null && !result.isEmpty()) {
				for(EventDO event : result) {
					event.setItems(null); 
				}
				retVal = result.get(0);
			}
		} catch (Exception e) {
			LOGGER.error(e);
			session.smvRollback();
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit lookupEventByEventCode - eventCode : " + eventCode);
		}
		
		return retVal;
	}
}

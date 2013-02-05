package com.smv.service.file.db.dao;



import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.smv.service.file.db.FileHibernateSessionFactory;
import com.smv.service.file.db.dbobject.ItemDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;

/**
 * @author Minh Do
 * 03/2010
 */

public class ItemDAO extends FileBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(ItemDAO.class);
	
	public ItemDAO(ItemDO clientDO) {
		super(clientDO);
	}
	
	public ItemDO getItemDO() {
		return (ItemDO) iClientDO;
	}

	@Override
	protected boolean reconcileDerivedValues() {
		if (getItemDO().getOperation() == AbstractDO.CREATE) {		
			getItemDO().setPostedOn(new Timestamp(System.currentTimeMillis()) );
		}
		return false;
	}
	
	@Override
	public AbstractDO merge(final AbstractDO obj1, final AbstractDO obj2) {
		ItemDO clientDO = (ItemDO) obj1;
		ItemDO serverDO = (ItemDO) super.merge(obj1, obj2);

		//if (clientDO.getAccountId() != null) {
		//	serverDO.setAccountId(clientVO.getAccountId());
		//}
		
		if (clientDO.getIsPublic() != null) {
			serverDO.setIsPublic(clientDO.getIsPublic());
		}
		
		if (clientDO.getDescription() != null) {
			serverDO.setDescription(clientDO.getDescription());
		}
		
		if (clientDO.getDeviceId() != null) {
			serverDO.setDeviceId(clientDO.getDeviceId());
		}
		

		
		if (clientDO.getLatitude() != null) {
			serverDO.setLatitude(clientDO.getLatitude());
		}
		
		if (clientDO.getLongitude() != null) {
			serverDO.setLongitude(clientDO.getLongitude());
		}
		
		if (clientDO.getAltitude() != null) {
			serverDO.setAltitude(clientDO.getAltitude());
		}
		
		if (clientDO.getLocation() != null) {
			serverDO.setLocation(clientDO.getLocation());
		}
		
		if (clientDO.getItemCode() != null) {
			serverDO.setItemCode(clientDO.getItemCode());				
		}
		
		if (clientDO.getItemTitle() != null) {
			serverDO.setItemTitle(clientDO.getItemTitle());				
		}
		
		if (clientDO.getUid() != null) {
			serverDO.setUid(clientDO.getUid());
		}
		
		if (clientDO.getAid() != null && clientDO.getAid().longValue() != 0) {
			serverDO.setAid(clientDO.getAid());
		}
		
		if (clientDO.getEventId() != null) {
			serverDO.setEventId(clientDO.getEventId());
		}
		
		if (clientDO.getDuration() != null) {
			serverDO.setDuration(clientDO.getDuration());
		}
		
		if (clientDO.getHeight() != null) {
			serverDO.setHeight(clientDO.getHeight());
		}
		if (clientDO.getWidth() != null) {
			serverDO.setWidth(clientDO.getWidth());
		}

		if (clientDO.getSequenceId() != null) {
			serverDO.setSequenceId(clientDO.getSequenceId());
		}
		
		if (clientDO.getSize() != null) {
			serverDO.setSize(clientDO.getSize());
		}
		
		if (clientDO.getStatus() != null) {
			serverDO.setStatus(clientDO.getStatus());
		}
		
		if (clientDO.getStorageId() != null) {
			serverDO.setStorageId(clientDO.getStorageId());
		}
		
		if (clientDO.getStorageId1() != null) {
			serverDO.setStorageId1(clientDO.getStorageId1());
		}
		
		if (clientDO.getStorageId2() != null) {
			serverDO.setStorageId2(clientDO.getStorageId2());
		}
		
		if (clientDO.getStorageId3() != null) {
			serverDO.setStorageId3(clientDO.getStorageId3());
		}
		
		if (clientDO.getProcessDone() != null) {
			serverDO.setProcessDone(clientDO.getProcessDone());
		}
		
		if (clientDO.getMimeType() != null) {
			serverDO.setMimeType(clientDO.getMimeType());
		}
		
		if (clientDO.getPostedOn() != null) {
			serverDO.setPostedOn(clientDO.getPostedOn());
		}
		
		if (clientDO.getExpiredOn() != null) {
			serverDO.setExpiredOn(clientDO.getExpiredOn());
		}
		
		return serverDO;
	}

	public static ItemDO getItemById(final Long id) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getItemById - input : { id = " + id + "}");
		}
		
		SmvHibernateSession session = FileHibernateSessionFactory.getInstance().getSession();
		ItemDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (ItemDO) session.get(ItemDO.class, id);
			session.smvCommitTransaction();
		} catch(Exception he) {
			LOGGER.error(he);
			session.smvRollback();
	    }
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getItemById - input : { id = " + id + "}");
		}
		
		return retVal;
	}
	
	public static List<ItemDO> getMostRecentItems(final Integer num) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getMostRecentItems - input : { num = " + num + "}");
		}
		
		final SmvHibernateSession session = FileHibernateSessionFactory.getInstance().getSession();
		List<ItemDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("getMostRecentItems");		
			query.setMaxResults(num);
			result = (List<ItemDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			session.smvRollback();
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getMostRecentItems - input : { num = " + num + "}");
		}
		
		return result;
	}
	
	
	public static List<ItemDO> getMostRecentItemsByUserId(Long uid, Integer num) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getMostRecentItems - input : { uid = " + uid + ", num = " + num + "}");
		}
		
		SmvHibernateSession session = FileHibernateSessionFactory.getInstance().getSession();
		List<ItemDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("getMostRecentItemsByUserId");		
			query.setLong(0, uid);
			query.setMaxResults(num);
			result = (List<ItemDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			session.smvRollback();
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getMostRecentItems - input : { uid = " + uid + ", num = " + num + "}");
		}
		
		return result;
	}
	
	
	public static List<ItemDO> getItemsByEventId(Long eid) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getItemsByEventId - input : { eid = " + eid + "}");
		}
		
		SmvHibernateSession session = FileHibernateSessionFactory.getInstance().getSession();
		List<ItemDO> result = null;
		
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("getItemsByEventId");		
			query.setLong(0, eid);
			result = (List<ItemDO>) query.list();
			session.smvCommitTransaction();
		} catch (Exception e) {
			LOGGER.error(e);
			session.smvRollback();
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getItemsByEventId - input : { eid = " + eid + "}");
		}
		
		return result;
	}
}



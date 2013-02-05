package com.smv.service.file.db.dao;


import org.apache.log4j.Logger;

import com.smv.service.file.db.FileHibernateSessionFactory;
import com.smv.service.file.db.dbobject.FileSystemDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.db.SmvHibernateSession;


/**
 * @author Minh Do
 * 03/2010
 */

public class FileSystemDAO extends FileBaseDAO {
	private static final Logger LOGGER = Logger.getLogger(FileSystemDAO.class);
	
	public FileSystemDAO(FileSystemDO clientDO) {
		super(clientDO);
	}
	
	public FileSystemDO getFileSystemDO() {
		return (FileSystemDO) iClientDO;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		FileSystemDO clientDO = (FileSystemDO) obj1;
		FileSystemDO serverDO = (FileSystemDO) super.merge(obj1, obj2);
		
		
		if (clientDO.getEventCount() != 0) {
			serverDO.setEventCount(clientDO.getEventCount());
		}

		if (clientDO.getFileCount() != 0) {
			serverDO.setFileCount(clientDO.getFileCount());
		}
		
		if (clientDO.getFileId1() != null && !clientDO.getFileId1().isEmpty()) {
			serverDO.setFileId1(clientDO.getFileId1());
		}
		
		if (clientDO.getFileId2() != null && !clientDO.getFileId2().isEmpty()) {
			serverDO.setFileId2(clientDO.getFileId2());
		}
		
		if (clientDO.getFileId3() != null && !clientDO.getFileId3().isEmpty()) {
			serverDO.setFileId3(clientDO.getFileId3());
		}
		
		if (clientDO.getUsedSpace() != 0) {
			serverDO.setUsedSpace(clientDO.getUsedSpace());
		}
		
		return serverDO;
	}

	
	public static FileSystemDO geFileSystemByUid(Long uid) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter geFileSystemByUid - input : { uid = " + uid + "}");
		}
		
		SmvHibernateSession session = FileHibernateSessionFactory.getInstance().getSession();
		FileSystemDO retVal = null;
		
		try {
			session.beginTransaction();
			retVal = (FileSystemDO) session.get(FileSystemDO.class, uid);			
			session.smvCommitTransaction();
		} catch(Exception he) {
			LOGGER.error(he);
			session.smvRollback();
	    }
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit geFileSystemByUid - input : { uid = " + uid + "}");
		}
		
		return retVal;
	}
	


}

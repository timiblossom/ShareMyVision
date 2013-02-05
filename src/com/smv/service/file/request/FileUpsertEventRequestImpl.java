package com.smv.service.file.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.smv.common.Constant;
import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.bean.KeyValueEntryDTO;
import com.smv.common.bean.KeyValueMapDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.file.db.dao.EventDAO;
import com.smv.service.file.db.dao.FilePolicyDAO;
import com.smv.service.file.db.dao.FileSystemDAO;
import com.smv.service.file.db.dao.ItemDAO;
import com.smv.service.file.db.dbobject.EventDO;
import com.smv.service.file.db.dbobject.FilePolicyDO;
import com.smv.service.file.db.dbobject.FileSystemDO;
import com.smv.service.file.db.dbobject.ItemDO;
import com.smv.service.file.helper.FileHelper;
import com.smv.service.file.mapper.EventMapper;
import com.smv.service.file.mapper.ItemMapper;
import com.smv.util.db.AbstractDO;



/**
 * @author Minh Do
 * 09/26/2010
 */
public class FileUpsertEventRequestImpl extends AbstractFileRequest {
	//private static Executor executor = Executors.newFixedThreadPool(5);
	private static final Logger LOGGER = Logger.getLogger(FileUpsertEventRequestImpl.class);
	
	public FileUpsertEventRequestImpl(EventDTO inputEvent, Map<String, String> inputHttpEnv) {
		super(inputEvent, inputHttpEnv);
	}

	
	public Object execute() throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter execute - EventDTO : " + event);
		}
		
		validate();
		
		boolean isNewEvent = false;
		EventDO eventDO = EventDAO.lookupEventByEventCode(event.getEventCode());
		ItemDO itemDO = ItemMapper.itemDTO2DO(event.getItems()[0]);

		FileSystemDO fs = FileHelper.getOrGenerateFileSystemDO(event.getUserId());
		List<FilePolicyDO> policies = FilePolicyDAO.getFilePoliciesByPolicyId(fs.getFilePolicyId());
				
		itemDO.setOperation(AbstractDO.CREATE);						

		String[] ss = FileHelper.getPathAndFileName(itemDO.getStorageId());
		itemDO.setItemCode(ss[0] + "/" + ss[1]);
		itemDO.setStorageId(FileHelper.generateDownloadUrlForStoring(ss));
		ss[0] = ss[0] + "/a";
		
		String fileName = ss[1];
		Map<String, String> policyMap = new HashMap<String, String>(policies.size());
		int numThumbnailsForWidth = 0;
		int numThumbnailsForHeight = 0;

		for(FilePolicyDO policy : policies) {
			policyMap.put(policy.getName(), policy.getValue());
			if (Constant.NUM_THUMBNAILS_FOR_WIDTH.equalsIgnoreCase(policy.getName())) {
				numThumbnailsForWidth = Integer.parseInt(policy.getValue());
			} else if (Constant.NUM_THUMBNAILS_FOR_HEIGHT.equalsIgnoreCase(policy.getName())) {
				numThumbnailsForHeight = Integer.parseInt(policy.getValue());
			} 
		}
		
		//Set storageId	
		for(int i=1; i<=numThumbnailsForWidth; i++) {
			if (i==1) {
				String width = policyMap.get(Constant.THUMBNAIL_WIDTH_PREFIX + i);
				ss[1] = fileName.substring(0, fileName.indexOf('.')) + "-" + width + ".jpg";
				itemDO.setStorageId1(FileHelper.generateDownloadUrlForStoring(ss));
			} else if (i==2) {
				String width = policyMap.get(Constant.THUMBNAIL_WIDTH_PREFIX + i);
				ss[1] = fileName.substring(0, fileName.indexOf('.')) + "-" + width + ".jpg";
				itemDO.setStorageId2(FileHelper.generateDownloadUrlForStoring(ss));
			}
		}
		
		for(int i=1; i<=numThumbnailsForHeight; i++) {
            if (i==1) {
				String height = policyMap.get(Constant.THUMBNAIL_HEIGHT_PREFIX + i);
				ss[1] = fileName.substring(0, fileName.indexOf('.')) + "-h" + height + ".jpg";
				itemDO.setStorageId3(FileHelper.generateDownloadUrlForStoring(ss));
			}
		}
		
		/*
		for(FilePolicyDO policy : policies) {
			ss[1] = fileName.substring(0, fileName.indexOf('.')) + "-" + policy.getValue() + ".jpg";
			
			if (Constant.THUMBNAIL_1_WIDTH.equalsIgnoreCase(policy.getName())) {
				itemDO.setStorageId2(FileHelper.generateDownloadUrlForStoring(ss));
			} else if (Constant.THUMBNAIL_2_WIDTH.equalsIgnoreCase(policy.getName())) {
				itemDO.setStorageId3(FileHelper.generateDownloadUrlForStoring(ss));
			}
		}
		*/
		
		if (eventDO == null) {
			//insert
			eventDO = EventMapper.eventDTO2DOMapper(event);
			eventDO.setOperation(AbstractDO.CREATE);
			
			//set folder url
			ss[1] = fileName.substring(0, fileName.indexOf('.')) + "-folder.jpg";
			eventDO.setFolderUrl(FileHelper.generateDownloadUrlForStoring(ss));			
			
			
			EventDAO eventBo = new EventDAO(eventDO);
			
			try {
				eventBo.save(true, false);
			} catch (Exception e) {  //someone just inserts it so retrieve it now
				e.printStackTrace();
				eventDO = EventDAO.lookupEventByEventCode(event.getEventCode());
			}
			isNewEvent = true;
		}
		
		
		itemDO.setEventId(eventDO.getId());	
		itemDO.setProcessDone(Boolean.FALSE);
		ItemDAO itemBo = new ItemDAO(itemDO);
		itemBo.save(true, false);
		

		upsertFileSystem(event.getItems()[0].getUserId(), event.getEventCode(), event.getItems()[0], isNewEvent);

		
		EventDTO retVal = EventMapper.eventDO2DTOMapper(eventDO);
		
		//set this flag to generate folder url
		retVal.setIsNew(isNewEvent);
		
		retVal.setItems(new ItemDTO[] {ItemMapper.itemDO2DTO(itemBo.getItemDO())});
		retVal.setPolicy(new KeyValueMapDTO());
						
		for(FilePolicyDO policy : policies) {
			retVal.getPolicy().getEntries().add(new KeyValueEntryDTO(policy.getName(), policy.getValue()));
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit execute - EventDTO : " + retVal);
		}
		
		return retVal;
	}

	private void upsertFileSystem(Long uid, String eventCode, ItemDTO item, boolean isNewEvent) {
		FileSystemDO fs = FileSystemDAO.geFileSystemByUid(uid);

		if (fs == null) { //Probably no need
				
			fs = new FileSystemDO();
			fs.setId(uid);
			fs.setFileId1(eventCode.substring(0, eventCode.indexOf('_')));
			fs.setFileCount(1);
			fs.setEventCount(1);
			fs.setUsedSpace(item.getFileSize().longValue());				
			fs.setOperation(AbstractDO.CREATE);

			FileSystemDAO fsBo = new FileSystemDAO(fs);
			fsBo.save(true, false);
		} else {
			
			fs.setFileCount(fs.getFileCount() + 1);
			if (isNewEvent) {
				fs.setEventCount(fs.getEventCount() + 1);
			}
			fs.setUsedSpace(fs.getUsedSpace() + item.getFileSize());
			fs.setOperation(AbstractDO.UPDATE);

			FileSystemDAO fsBo = new FileSystemDAO(fs);
			fsBo.save(true, false);
		}

	}


	@Override
	public boolean validate() throws SmvServiceException {
		if (event == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_EVENT_INPUT, SmvErrorCode.FS_NULL_EVENT_INPUT_MSG);
		}
		
		if (event.getEventCode() == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_EVENT_INPUT, SmvErrorCode.FS_NULL_EVENT_INPUT_MSG);
		}
		
		if (event.getUserId() == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_USER_ID_INPUT, SmvErrorCode.FS_NULL_USER_ID_INPUT_MSG);
		}
		
		return true;
	}

}

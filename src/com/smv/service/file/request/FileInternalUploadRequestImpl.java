package com.smv.service.file.request;


import java.util.Map;

import org.apache.log4j.Logger;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.file.helper.impl.FileHelperS3UrlSigner;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class FileInternalUploadRequestImpl extends AbstractFileRequest {
	private static final Logger LOGGER = Logger.getLogger(FileInternalUploadRequestImpl.class);
	
	public FileInternalUploadRequestImpl(EventDTO inputEvent, Map<String, String> inputHttpEnv) {
		super(inputEvent, inputHttpEnv);
	}

	@Override
	public Object execute() throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter execute - EventDTO : " + event);
		}
		
        validate();
		
		EventDTO result = FileHelperS3UrlSigner.getInstance().signUploadFileUrls(event);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit execute - EventDTO : " + result);
		}
		
		return result;
	}

	@Override
	public boolean validate() throws SmvServiceException {		
		if (event == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_EVENT_INPUT, SmvErrorCode.FS_NULL_EVENT_INPUT_MSG);
		}
				
		if (event.getItems() == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NO_ITEM_IN_EVENT_INPUT, SmvErrorCode.FS_NO_ITEM_IN_EVENT_INPUT_MSG);
		}
		
		for(ItemDTO item : event.getItems()) {
			if (item.getPath() == null) {
				throw new SmvServiceException(SmvErrorCode.FS_AN_ITEM_MISSING_PATH, SmvErrorCode.FS_AN_ITEM_MISSING_PATH_MSG);
			}
			
			if (item.getFileName() == null) {
				throw new SmvServiceException(SmvErrorCode.FS_AN_ITEM_MISSING_FILENAME, SmvErrorCode.FS_AN_ITEM_MISSING_FILENAME_MSG);
			}
			
			if (item.getFileSize() == null) {
				throw new SmvServiceException(SmvErrorCode.FS_AN_ITEM_MISSING_FILESIZE, SmvErrorCode.FS_AN_ITEM_MISSING_FILESIZE_MSG);
			}
		}	
        		
		return true;
	}

}

package com.smv.service.file.request;

import java.util.Map;

import org.apache.log4j.Logger;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.file.db.dbobject.FileSystemDO;
import com.smv.service.file.helper.FileHelper;
import com.smv.service.file.helper.impl.FileHelperS3UrlSigner;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class FileUploadRequestImpl extends AbstractFileRequest {
	private static final Logger LOGGER = Logger.getLogger(FileUploadRequestImpl.class);
	
	public FileUploadRequestImpl(EventDTO inputEvent, Map<String, String> inputHttpEnv) {
		super(inputEvent, inputHttpEnv);
	}

	@Override
	public Object execute() throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter execute - EventDTO : " + event);
		}
				
		validate();
		
		//Set the full path
		//TODO: need to validate that there is userid in each item
		ItemDTO[] items = event.getItems();		
		FileSystemDO fs = FileHelper.getOrGenerateFileSystemDO(event.getUserId());
		if (event.getEventCode() == null) {
			event.setEventCode(fs.getFileId1() + "_" + cleanString(event.getEventTitle()));
		}
		
		for(ItemDTO item : items) {
			item.setPath(fs.getServer() + "/" + fs.getFileId1() + "/" + cleanString(item.getPath()));
		}
		
		EventDTO result = FileHelperS3UrlSigner.getInstance().signUploadFileUrls(event);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit execute - EventDTO : " + event);
		}
		
		return result;
	}

	@Override
	public boolean validate() throws SmvServiceException {	
		
		if (event == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_EVENT_INPUT, SmvErrorCode.FS_NULL_EVENT_INPUT_MSG);
		}
		
		if (event.getUserId() == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_USER_ID_INPUT, SmvErrorCode.FS_NULL_USER_ID_INPUT_MSG);
		}
		
		if (event.getEventTitle() == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_EVENT_TITLE_INPUT, SmvErrorCode.FS_NULL_EVENT_TITLE_INPUT_MSG);
		}
		
		if (event.getItems() == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NO_ITEM_IN_EVENT_INPUT, SmvErrorCode.FS_NO_ITEM_IN_EVENT_INPUT_MSG);
		}
		
		for(ItemDTO item : event.getItems()) {
			if (item.getPath() == null) {
				throw new SmvServiceException(SmvErrorCode.FS_AN_ITEM_MISSING_PATH, SmvErrorCode.FS_AN_ITEM_MISSING_PATH_MSG);
			}
		}
		
		return true;
	}
	
	
	//replace all special characters [',blank,`,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},<,>,,,.,/,?,\,|]
	private String cleanString(String str) {		
		String retVal = null;
		
		if (str == null) {
			return null;
		}
		
		retVal = str.replace('\'', '_');
		retVal = retVal.replace(' ', '_');
		retVal = retVal.replace('`', '_');
		retVal = retVal.replace('~', '_');
		retVal = retVal.replace('!', '_');
		retVal = retVal.replace('@', '_');
		retVal = retVal.replace('#', '_');
		retVal = retVal.replace('$', '_');
		retVal = retVal.replace('%', '_');
		retVal = retVal.replace('^', '_');
		retVal = retVal.replace('&', '_');
		retVal = retVal.replace('*', '_');
		retVal = retVal.replace('(', '_');
		retVal = retVal.replace(')', '_');
		retVal = retVal.replace('-', '_');
		retVal = retVal.replace('=', '_');
		retVal = retVal.replace('+', '_');
		retVal = retVal.replace('[', '_');
		retVal = retVal.replace('{', '_');
		retVal = retVal.replace(']', '_');
		retVal = retVal.replace('}', '_');
		retVal = retVal.replace('<', '_');
		retVal = retVal.replace('>', '_');
		retVal = retVal.replace(',', '_');
		retVal = retVal.replace('.', '_');
		retVal = retVal.replace('/', '_');
		retVal = retVal.replace('?', '_');
		retVal = retVal.replace('\\', '_');
		retVal = retVal.replace('|', '_');
		
		return retVal;
	}

}

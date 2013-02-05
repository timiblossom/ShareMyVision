package com.smv.service.file;


import java.util.Map;

import org.apache.log4j.Logger;

import com.smv.common.bean.EventDTO;
import com.smv.service.file.request.FileDeleteRequestImpl;
import com.smv.service.file.request.FileDetailRequestImpl;
import com.smv.service.file.request.FileDownloadRequestImpl;
import com.smv.service.file.request.FileInternalUploadRequestImpl;
import com.smv.service.file.request.FileUploadRequestImpl;
import com.smv.service.file.request.FileUpsertEventRequestImpl;

/**
 * @author Minh Do
 * 07/22/2010
 */
public class FileRequestFactory {
	private static final Logger LOGGER = Logger.getLogger(FileRequestFactory.class);
	
	public static IFileRequest constructRequest(EventDTO event, Map<String, String> httpEnv) {
		IFileRequest retVal = null;
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter constructRequest");
		}
		
		if (event == null) {
			retVal = null;
		    //TODO: use hash map lookup here
		} else if (FileConstant.SIGN_UPLOAD_URL.equalsIgnoreCase(event.getAction())) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Generate FileUploadRequestImpl instance");
			}
			retVal = new FileUploadRequestImpl(event, httpEnv);
		} else if (FileConstant.SIGN_DOWNLOAD_URL.equalsIgnoreCase(event.getAction())) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Generate FileDownloadRequestImpl instance");
			}
			retVal = new FileDownloadRequestImpl(event, httpEnv);
		} else if (FileConstant.SIGN_FILE_DETAIL_URL.equalsIgnoreCase(event.getAction())) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Generate FileDetailRequestImpl instance");
			}
			retVal = new FileDetailRequestImpl(event, httpEnv);
		} else if (FileConstant.SIGN_DELETE_URL.equalsIgnoreCase(event.getAction())) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Generate FileDeleteRequestImpl instance");
			}
			retVal = new FileDeleteRequestImpl(event, httpEnv);
		} else if (FileConstant.UPSERT_EVENT.equalsIgnoreCase(event.getAction())) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Generate FileUpsertEventRequestImpl instance");
			}
			retVal = new FileUpsertEventRequestImpl(event, httpEnv);
		} else if (FileConstant.SIGN_INTERNAL_UPLOAD_URL.equalsIgnoreCase(event.getAction())) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Generate FileInternalUploadRequestImpl instance");
			}
			retVal = new FileInternalUploadRequestImpl(event, httpEnv);
		}
		
		if (LOGGER.isDebugEnabled() && retVal == null) {
			LOGGER.debug("Generate NULL instance");
		}
		
		return retVal;
	}

}

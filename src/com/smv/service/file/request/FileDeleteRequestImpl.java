package com.smv.service.file.request;

import java.util.Map;

import org.apache.log4j.Logger;

import com.smv.common.bean.EventDTO;
import com.smv.service.file.helper.impl.FileHelperS3UrlSigner;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class FileDeleteRequestImpl extends AbstractFileRequest {
	private static final Logger LOGGER = Logger.getLogger(FileDeleteRequestImpl.class);
	
	public FileDeleteRequestImpl(EventDTO inputEvent, Map<String, String> inputHttpEnv) {
		super(inputEvent, inputHttpEnv);
	}

	@Override
	public Object execute() throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter execute - EventDTO : " + event);
		}
		
		EventDTO result = FileHelperS3UrlSigner.getInstance().signDeleteFileUrl(event);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit execute - EventDTO : " + result);
		}
		
		return result;
	}

	@Override
	public boolean validate() {
		return true;
	}

}

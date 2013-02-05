package com.smv.service.file.request;

import java.util.Map;

import com.smv.common.bean.EventDTO;
import com.smv.service.file.IFileRequest;


/**
 * @author Minh Do
 * 03/01/2010
 */
public abstract class AbstractFileRequest implements IFileRequest {
	
	protected EventDTO event;
	protected Map<String, String> httpEnv;
	
	public AbstractFileRequest(EventDTO inputEvent, Map<String, String> inputHttpEnv) {
		event = inputEvent;
		httpEnv = inputHttpEnv;
	}
	
	
}

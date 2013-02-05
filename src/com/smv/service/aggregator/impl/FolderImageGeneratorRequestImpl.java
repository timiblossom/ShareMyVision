package com.smv.service.aggregator.impl;




import java.util.List;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;

import com.smv.common.Constant;
import com.smv.common.bean.EventDTO;
import com.smv.service.aggregator.IRequest;
import com.smv.service.aggregator.config.ResourceManager;


/**
 * @author Minh Do
 * 10/09/2010
 */
public class FolderImageGeneratorRequestImpl implements IRequest<EventDTO> {
	private static final Logger LOGGER = Logger.getLogger(FolderImageGeneratorRequestImpl.class);
	private EventDTO event;	
	private List<Header> headers;
	
	public FolderImageGeneratorRequestImpl() {}
	
	public FolderImageGeneratorRequestImpl(EventDTO event, List<Header> headers) {
		this.event = event;
		this.headers = headers;
	}	
	
	public EventDTO getEvent() {
		return event;
	}

	public void setEvent(EventDTO event) {
		this.event = event;
	}
	
	public String execute() throws Exception {	
		return ResourceManager.getGson().toJson(call());
	}
	
	@Override
	public boolean validate() {		

		if (event == null || event.getItems() == null)
			return false;
		
		return true;
	}


	@Override
	public EventDTO call() throws Exception {		
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter call - {event : " + event + "}");
		}
		
		if (!validate()) {
			return null;
		}
		
		((BindingProvider) ResourceManager.getFileProxyService()).getRequestContext().put(Header.HEADER_LIST, headers);
		
		EventDTO retVal =  ResourceManager.getImageProxyService().processImageByRatio(event, Constant.FOLDER_IMAGE_MAX_WIDTH, Constant.FOLDER_IMAGE_MAX_HEIGHT);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit call - {event : " + event + "}");
		}
		
		return retVal;
	}

}

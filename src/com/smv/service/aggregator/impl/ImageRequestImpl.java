package com.smv.service.aggregator.impl;



import java.util.List;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;

import com.smv.common.bean.EventDTO;
import com.smv.service.aggregator.IRequest;
import com.smv.service.aggregator.config.ResourceManager;


/**
 * @author Minh Do
 * 10/09/2010
 */
public class ImageRequestImpl implements IRequest<EventDTO> {
	private static final Logger LOGGER = Logger.getLogger(ImageRequestImpl.class);
	private EventDTO event;	
	private List<Header> headers;
	
	public ImageRequestImpl() {}
	
	public ImageRequestImpl(EventDTO event, List<Header> headers) {
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
		if (event == null)
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
		
		EventDTO retVal = ResourceManager.getImageProxyService().processImage(event);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit call - {retVal : " + retVal + "}");
		}
		
		return retVal;
	}

}

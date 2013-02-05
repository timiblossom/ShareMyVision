package com.smv.service.aggregator.impl;

import java.util.List;

import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.EventRequestDTO;
import com.smv.common.bean.EventResponseDTO;
import com.smv.service.aggregator.IRequest;
import com.smv.service.aggregator.config.ResourceManager;

public class UpsertEventRequestImpl implements IRequest<Object> {
	private static final Logger LOGGER = Logger.getLogger(EventRequestImpl.class);
	private EventRequestDTO eventReq;
	private List<Header> headers;
	
	public UpsertEventRequestImpl() {}
	
	public UpsertEventRequestImpl(EventRequestDTO eventReq, List<Header> headers) {
		this.eventReq = eventReq;
		this.headers = headers;
	}
	
	@Override
	public String execute() throws Exception { 
		return ResourceManager.getGson().toJson(call());
	}
	
	@Override
	public boolean validate() {
		if (eventReq != null && eventReq.getEvents() != null && eventReq.getEvents().size() != 0)
			return true;
		
		return false;
	}
	
	@Override
	public Object call() throws Exception {		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter call - {eventReq : " + eventReq + "}");
		}
		
		for(EventDTO event : eventReq.getEvents()) {
			event.setAction(eventReq.getAction());
		}
		
		IRequest<EventResponseDTO> eventRequest = new EventRequestImpl(eventReq, headers);
		
		EventResponseDTO eventRequestResult = eventRequest.call();		
		
		EventDTO processedEvent = eventRequestResult.getEvents().get(0);
		IRequest<EventDTO> imageProcessingRequest = new ImageRequestImpl(processedEvent, headers);
		imageProcessingRequest.call();
		
		if (processedEvent.getIsNew()) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("About to generate folder's image");
			}
			
			EventDTO inputEvent = eventReq.getEvents().get(0);
		
			inputEvent.getItems()[0].setUrl(processedEvent.getItems()[0].getUrl());
			IRequest<EventDTO> folderImageGeneratorRequest = new FolderImageGeneratorRequestImpl(inputEvent, headers);
			
			if (folderImageGeneratorRequest.call() == null) {
				LOGGER.error("Can't generat folder image");
			}
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit call - {eventReq : " + eventReq + "}");
		}
		
		//TODO: call back to File Engine to update process_done = true
		return null;
	}


}

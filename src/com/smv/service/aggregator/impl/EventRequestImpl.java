package com.smv.service.aggregator.impl;

import java.util.List;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;

import com.smv.common.Constant;
import com.smv.common.bean.EventDTO;
import com.smv.common.bean.EventRequestDTO;
import com.smv.common.bean.EventResponseDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.aggregator.IRequest;
import com.smv.service.aggregator.config.ResourceManager;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class EventRequestImpl implements IRequest<EventResponseDTO> {
	private static final Logger LOGGER = Logger.getLogger(EventRequestImpl.class);
	private EventRequestDTO eventReq;
	private List<Header> headers;
	
	public EventRequestImpl() {}
	
	public EventRequestImpl(EventRequestDTO eventReq, List<Header> headers) {
		this.eventReq = eventReq;
		this.headers = headers;
	}
	
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
	public EventResponseDTO call() throws Exception {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter call - {eventReq : " + eventReq + "}");
		}
		
		EventResponseDTO response = new EventResponseDTO();
		
		if (!validate()) {
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);			
		} else {		
			((BindingProvider) ResourceManager.getFileProxyService()).getRequestContext().put(Header.HEADER_LIST, headers);		
		     EventDTO event = null;
		     
		     try {
		    	 for(EventDTO e : eventReq.getEvents()) {
		    		 e.setAction(eventReq.getAction());
		    	 }
		    	 
		    	 event = ResourceManager.getFileProxyService().eventRequest(eventReq.getEvents().get(0));
		    	 
		    	 response.setMessage(Constant.OK_MSG);
			     response.setStatusCode(Constant.OK);
			     response.getEvents().add(event);
			     
		     } catch (SmvServiceException e) {
		    	 response.setMessage(e.getErrorMessage());
		    	 response.setStatusCode(e.getErrorCode());		 
		     } catch (Exception e) {
		    	 response.setMessage(SmvErrorCode.SYSTEM_UNKNOWN_EXCEPTION_MSG);
		    	 response.setStatusCode(SmvErrorCode.SYSTEM_UNKNOWN_EXCEPTION);		
		     }

		}     
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit call - {eventReq : " + eventReq + "}");
		}
		
		return response;
	}



}

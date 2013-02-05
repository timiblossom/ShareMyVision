package com.smv.service.aggregator.impl;


import java.util.List;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;

import com.smv.common.Constant;
import com.smv.common.bean.UserDTO;
import com.smv.common.bean.UserRequestDTO;
import com.smv.common.bean.UserResponseDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.aggregator.IRequest;
import com.smv.service.aggregator.config.ResourceManager;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class UserRequestImpl<T> implements IRequest<UserResponseDTO> {
	private static final Logger LOGGER = Logger.getLogger(UserRequestImpl.class);
	private UserRequestDTO userReq;
	private List<Header> headers;
	
	public UserRequestImpl() {}
	
	public UserRequestImpl(UserRequestDTO userReq, List<Header> headers) {
		this.userReq = userReq;
		this.headers = headers;
	}
	
	public String execute() throws Exception {
		return ResourceManager.getGson().toJson(call());
	}
	
	public boolean validate() {
		if (userReq != null && userReq.getUsers() != null && userReq.getUsers().size() != 0)
			return true;
		
		return false;
	}

	@Override
	public UserResponseDTO call() throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter call - {userReq : " + userReq + "}");
		}
		
		UserResponseDTO response = new UserResponseDTO();
		
		if (!validate()) {
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);			
		} else {
			((BindingProvider) ResourceManager.getFileProxyService()).getRequestContext().put(Header.HEADER_LIST, headers);
			UserDTO user = null;
			
			try {
				for(UserDTO usr : userReq.getUsers()) {
					usr.setAction(userReq.getAction());
				}
				
				user = ResourceManager.getUserProxyService().userRequest(userReq.getUsers().get(0));
				
				response.setMessage(Constant.OK_MSG);
			    response.setStatusCode(Constant.OK);
			    response.getUsers().add(user);
			} catch (SmvServiceException e) {
		    	 response.setMessage(e.getErrorMessage());
		    	 response.setStatusCode(e.getErrorCode());		 
		    } catch (Exception e) {
		    	 response.setMessage(SmvErrorCode.SYSTEM_UNKNOWN_EXCEPTION_MSG);
		    	 response.setStatusCode(SmvErrorCode.SYSTEM_UNKNOWN_EXCEPTION);		
		    }
		    
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit call - {userReq : " + userReq + "}");
		}
		
		return response;
	}

}

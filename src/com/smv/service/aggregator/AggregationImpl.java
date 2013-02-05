package com.smv.service.aggregator;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.log4j.Logger;

import com.smv.common.Constant;
import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.bean.MailerDataDTO;
import com.smv.common.bean.OutletDTO;
import com.smv.common.bean.ProductDTO;
import com.smv.common.bean.ResponseDTO;
import com.smv.common.bean.UserDTO;
import com.smv.common.bean.UserOutletContentDTO;
import com.smv.common.bean.UserOutletDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.aggregator.config.ResourceManager;
import com.smv.service.aggregator.helper.AggregationHelper;
import com.smv.service.aggregator.helper.InstanceGenerator;
import com.smv.service.catalog.ICatalogService;
import com.smv.service.file.IFileService;
import com.smv.service.mailer.IMailerService;
import com.smv.service.outlet.IOutletService;
import com.smv.service.user.IUserService;
import com.smv.util.http.HttpUtil;
import com.smv.util.text.StringUtils;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class AggregationImpl implements IAggregation {

	private static final Logger LOGGER = Logger.getLogger(AggregationImpl.class);


	/*****************************************************************************************************
	 * For User API
	 *****************************************************************************************************/

	@Override
	public ResponseDTO login(UserDTO user, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {		   
			LOGGER.debug("Calling login: {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();
		if (user == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IUserService userService = ResourceManager.getUserProxyService();
			((BindingProvider) userService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			response.setValue(userService.login(user));
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			mapErrorStatus(e, response);
		}


		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting login {user : " + user + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}



	@Override
	public ResponseDTO activateUser(String activationCode, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling activateUser: {activationCode : " + activationCode + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();
		if (activationCode == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {			
			IUserService userService = ResourceManager.getUserProxyService();
			((BindingProvider) userService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			response.setValue(userService.activateUser(activationCode));
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			mapErrorStatus(e, response);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting activateUser : {activationCode : " + activationCode + ", httpEnv : " + httpEnv + "}");
		}

		return response;

	}



	@Override
	public ResponseDTO changePassword(UserDTO user, String newPassword, Map<String, String> httpEnv) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling changePassword: {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();
		if (user == null || newPassword == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {			
			IUserService userService = ResourceManager.getUserProxyService();
			((BindingProvider) userService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));

			response.setValue(userService.changePassword(user, newPassword));
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			mapErrorStatus(e, response);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting changePassword : {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		return response;
	}



	@Override
	public ResponseDTO getAccount(UserDTO user, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling getAccount: {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (user == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IUserService userService = ResourceManager.getUserProxyService();
			((BindingProvider) userService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			response.setValue(userService.getAccount(user));
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			mapErrorStatus(e, response);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting getAccount : {user : " + user + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}



	@Override
	public ResponseDTO getUser(UserDTO user, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling getUser: {user : " + user + ", httpEnv : " + httpEnv + "}");
		}
		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (user == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IUserService userService = ResourceManager.getUserProxyService();
			((BindingProvider) userService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			response.setValue(userService.getUser(user));
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			mapErrorStatus(e, response);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting getUser {user : " + user + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}


	@Override
	public ResponseDTO isUserExist(String emailAddress, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling isUserExist: {emailAddress : " + emailAddress + ", httpEnv : " + httpEnv + "}");
		}
		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (StringUtils.nullOrEmptyOrBlankString(emailAddress)) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IUserService userService = ResourceManager.getUserProxyService();
			((BindingProvider) userService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			response.setValue(userService.isUserExist(emailAddress));
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			mapErrorStatus(e, response);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting isUserExist: {emailAddress : " + emailAddress + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}



	@Override
	public ResponseDTO logout(UserDTO user, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling logout: {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();
		if (user == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IUserService userService = ResourceManager.getUserProxyService();
			((BindingProvider) userService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			response.setValue(userService.logout(user));
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			mapErrorStatus(e, response);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting logout: {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		return response;
	}



	@Override
	public ResponseDTO regenerateActivationCode(UserDTO user, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling regenerateActivationCode: {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (user == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IUserService userService = ResourceManager.getUserProxyService();
			((BindingProvider) userService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			response.setValue(userService.regenerateActivationCode(user));
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  			

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			mapErrorStatus(e, response);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting regenerateActivationCode: {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		return response;
	}



	@Override
	public ResponseDTO register(UserDTO user, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling register: : {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (user == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IUserService userService = ResourceManager.getUserProxyService();
			((BindingProvider) userService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			response.setValue(userService.register(user));	
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  


		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			mapErrorStatus(e, response);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting register: {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		return response;
	}


	@Override
	public ResponseDTO forgotPassword(UserDTO user, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling forgotPassword: : {user : " + user + ", httpEnv : " + httpEnv + "}");
		}
		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();		


		if (user == null) {			
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {		
			IUserService userService = ResourceManager.getUserProxyService();
			((BindingProvider) userService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));		    			
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);
			response.setValue(userService.forgetPassword(user));

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			mapErrorStatus(e, response);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting forgotPassword: {user : " + user + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}



	@Override
	public ResponseDTO changeForgottenPassword(UserDTO user, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling changeForgottenPassword: : {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (user == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {		
			IUserService userService = ResourceManager.getUserProxyService();
			((BindingProvider) userService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			response.setValue(userService.changeForgottenPassword(user));	
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			mapErrorStatus(e, response);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting forgotPassword: {user : " + user + ", httpEnv : " + httpEnv + "}");
		}

		return response;
	}



	/*****************************************************************************************************
	 * For File API
	 *****************************************************************************************************/

	@Override
	public ResponseDTO getFeatureImages(Map<String, String> httpEnv) {
		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();
		response.setMessage(Constant.OK_MSG);
		response.setStatusCode(Constant.OK);
		response.setValue(AggregationHelper.FEATURE_IMAGES_LIST[AggregationHelper.RANDOM.nextInt(24)]);
		return response;		
	}


	public ResponseDTO getEventById(Long eventId, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling getEventById: {eventId : " + eventId + ", httpEnv : " + httpEnv + "}");
		}
		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (eventId == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IFileService fileService = ResourceManager.getFileProxyService();
			((BindingProvider) fileService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			EventDTO event = fileService.getEventById(eventId);
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(event);	
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting getEventById: {eventId : " + eventId + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}

	public ResponseDTO getEventsByUser(UserDTO user, Boolean showItems, Map<String, String> httpEnv) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling getEventsByUser: {user : " + user + ", showItems : " + showItems + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (user == null || showItems == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IFileService fileService = ResourceManager.getFileProxyService();
			((BindingProvider) fileService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			List<EventDTO> retVal = fileService.getEventsByUser(user, showItems);
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting getEventsByUser: {user : " + user + ", showItems : " + showItems + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}


	public ResponseDTO getItemsByEvent(EventDTO event, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling getItemsByEvent: {event : " + event + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (event == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {			
			IFileService fileService = ResourceManager.getFileProxyService();
			((BindingProvider) fileService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			List<ItemDTO> retVal = fileService.getItemsByEvent(event);
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting getItemsByEvent: {event : " + event + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}


	public ResponseDTO getMostRecentItemsByUser(UserDTO user, Integer num, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling getMostRecentItemsByUser: {user : " + user + ", num : " + num + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (user == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IFileService fileService = ResourceManager.getFileProxyService();
			((BindingProvider) fileService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			if (num == null) {
				num = Integer.valueOf(10); //TODO: will change number 10 later
			}
			List<ItemDTO> retVal = fileService.getMostRecentItemsByUser(user, num); 
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting getMostRecentItemsByUser: {user : " + user + ", num : " + num + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}


	public ResponseDTO getMostRecentItems(Integer num, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling getMostRecentItems: {num : " + num + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (num == null) {
			num = Integer.valueOf(10);
		}

		try {

			IFileService fileService = ResourceManager.getFileProxyService();
			((BindingProvider) fileService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			List<ItemDTO> retVal = fileService.getMostRecentItems(num); 
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting getMostRecentItems: {num : " + num + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}


	public ResponseDTO getItemById(Long itemId, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling getItemById: {itemId : " + itemId + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (itemId == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IFileService fileService = ResourceManager.getFileProxyService();
			((BindingProvider) fileService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			ItemDTO retVal = fileService.getItemById(itemId);
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting getItemById: {itemId : " + itemId + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}



	@Override
	public ResponseDTO updateEvent(EventDTO event, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling updateEvent: {event : " + event + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (event == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IFileService fileService = ResourceManager.getFileProxyService();
			((BindingProvider) fileService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			boolean retVal = fileService.updateEvent(event);
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting updateEvent: {event : " + event + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}


	@Override
	public ResponseDTO updateItem(ItemDTO item, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling updateItem: {item : " + item + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (item == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {
			IFileService fileService = ResourceManager.getFileProxyService();
			((BindingProvider) fileService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			boolean retVal = fileService.updateItem(item);
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting updateItem: {item : " + item + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}



	/*****************************************************************************************************
	 * For Catalog API
	 *****************************************************************************************************/

	public ResponseDTO getAllProducts(Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling getAllProducts: {httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		try {

			ICatalogService catalogService = ResourceManager.getCatalogProxyService();
			((BindingProvider) catalogService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			ProductDTO[] retVal = catalogService.getAllProducts(); 
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting getAllProducts: {httpEnv : " + httpEnv + "}");
		}
		return response;
	}
	

	/*****************************************************************************************************
	 * For Mailer API
	 *****************************************************************************************************/

	public ResponseDTO sendMail(MailerDataDTO mailerData, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter sendMail: {mailerData : " + mailerData + ", httpEnv : " + httpEnv + "}");
		}
		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();
		try {
			IMailerService mailService = ResourceManager.getMailerProxyService();
			((BindingProvider) mailService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			Boolean retVal = mailService.sendMail(mailerData);
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);
			response.setValue(retVal);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting sendMail: {mailerData : " + mailerData + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}
	
	

	/*****************************************************************************************************
	 * For Outlet API
	 *****************************************************************************************************/

	/* (non-Javadoc)
	 * @see com.smv.service.aggregator.IAggregation#getOutlet(java.util.Map)
	 */
	@Override
	public ResponseDTO getOutlet(Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling getOutlet: {httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		try {

			IOutletService outletService = ResourceManager.getOutletProxyService();
			((BindingProvider) outletService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			OutletDTO[] retVal = outletService.getOutlet(); 
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting getOutlet: {httpEnv : " + httpEnv + "}");
		}
		return response;
	}



	/* (non-Javadoc)
	 * @see com.smv.service.aggregator.IAggregation#getOutletForUser(java.lang.Long, java.util.Map)
	 */
	@Override
	public ResponseDTO getOutletForUser(Long userId, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling getOutletForUser: {userId : " + userId + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (userId == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {

			IOutletService outletService = ResourceManager.getOutletProxyService();
			((BindingProvider) outletService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			OutletDTO[] retVal = outletService.getOutletForUser(userId); 
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting getOutletForUser: {userId : " + userId + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}



	/* (non-Javadoc)
	 * @see com.smv.service.aggregator.IAggregation#enableOutletForUser(com.smv.common.bean.UserOutletDTO, java.util.Map)
	 */
	@Override
	public ResponseDTO enableOutletForUser(UserOutletDTO userOutlet,
			Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling enableOutletForUser: {userOutlet : " + userOutlet + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (userOutlet == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {

			IOutletService outletService = ResourceManager.getOutletProxyService();
			((BindingProvider) outletService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			UserOutletDTO retVal = outletService.enableOutletForUser(userOutlet);
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting enableOutletForUser: {userOutlet : " + userOutlet + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}



	/* (non-Javadoc)
	 * @see com.smv.service.aggregator.IAggregation#disableOutletForUser(com.smv.common.bean.UserOutletDTO, java.util.Map)
	 */
	@Override
	public ResponseDTO disableOutletForUser(UserOutletDTO userOutlet,
			Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling disableOutletForUser: {userOutlet : " + userOutlet + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if (userOutlet == null) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {

			IOutletService outletService = ResourceManager.getOutletProxyService();
			((BindingProvider) outletService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			UserOutletDTO retVal = outletService.disableOutletForUser(userOutlet);
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting disableOutletForUser: {userOutlet : " + userOutlet + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}



	/* (non-Javadoc)
	 * @see com.smv.service.aggregator.IAggregation#publishToOutlets(java.lang.Long, com.smv.common.bean.UserOutletContentDTO, com.smv.common.bean.OutletDTO[], java.util.Map)
	 */
	@Override
	public ResponseDTO publishToOutlets(Long userId,
			UserOutletContentDTO content, OutletDTO[] outlets,
			Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling publishToOutlets: {userId : " + userId + ", content : " + content + ", outlets : " + outlets + ", httpEnv : " + httpEnv + "}");
		}

		ResponseDTO response = InstanceGenerator.getResponseDTOInstance();

		if ((userId == null) || (content == null)) {
			response.setStatusCode(SmvErrorCode.SYSTEM_ILLEGALL_INPUT);
			response.setMessage(SmvErrorCode.SYSTEM_ILLEGALL_INPUT_MSG);  
			return response;
		}

		try {

			IOutletService outletService = ResourceManager.getOutletProxyService();
			((BindingProvider) outletService).getRequestContext().put(Header.HEADER_LIST, constructProxyHeaders(httpEnv));
			Boolean retVal = outletService.publishToOutlets(userId, content, outlets);
			response.setStatusCode(Constant.OK);
			response.setMessage(Constant.OK_MSG);  
			response.setValue(retVal);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting publishToOutlets: {userId : " + userId + ", content : " + content + ", outlets : " + outlets + ", httpEnv : " + httpEnv + "}");
		}
		return response;
	}



	/*****************************************************************************************************
	 * For Mobile API
	 *****************************************************************************************************/

	/*
	@Override
	public Object processRequest(Map input, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Calling AggregationImpl.processRequest: ");
			//log.debug(debugHelper(input, true) + "\n");
			LOGGER.debug(debugHelper(httpEnv, false));
		}

		Object retVal = null;

		try {
			IRequest request = RequestFactory.constructRequest(input, constructProxyHeaders(httpEnv));

			if (request != null && request.validate())
				retVal = request.execute();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting AggregationImpl.processRequest");
		}
		return retVal;
	}
	 */


	@Override
	public Object processRequest(String input, Map<String, String> httpEnv) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter processRequest - input : {input : " + input + ", httpEnv : " + httpEnv + "}");
		}

		Object retVal = null;

		if (input == null)
			return null;

		try {
			IRequest request = null;

			if (Constant.FILE_SERVICE_REQUEST.equalsIgnoreCase(httpEnv.get(Constant.REQUEST_URI))) {
				request = RequestFactory.constructFileRequest(input, constructProxyHeaders(httpEnv));				
			} else if (Constant.USER_SERVICE_REQUEST.equalsIgnoreCase(httpEnv.get(Constant.REQUEST_URI))) {
				request = RequestFactory.constructUserRequest(input, constructProxyHeaders(httpEnv));
			}

			if (request == null) {
				retVal = null; //TODO: return an error response here
			} else if (Constant.TRUE.equalsIgnoreCase(httpEnv.get(Constant.HTTP_ASYNC_REQUEST))) {					
				ResourceManager.getExecutor().submit(request);
				retVal = null;
			} else if (request != null && request.validate()) {
				retVal = request.execute();
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exiting processRequest - input : {input : " + input + ", httpEnv : " + httpEnv + "}");
		}
		return retVal;
	}


	

	/***************************************************
	 * All private methods are below
	 ***************************************************/

	private List<Header> constructProxyHeaders(Map<String, String> httpEnv) throws Exception {
		List<Header> headers = new ArrayList<Header>();

		Iterator iter = HttpUtil.getRegisteredHttpHeaders().keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (httpEnv.get(key) != null) {
				Header header = new Header(new QName("uri:org.apache.cxf", key), httpEnv.get(key), new JAXBDataBinding(String.class));
				headers.add(header);
			}
		}

		return headers;
	}

	private String debugHelper(Map event, boolean isRecursive) {
		StringBuilder sb = new StringBuilder();

		sb.append("event size : " + event.size());
		Iterator iter = event.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			if (entry == null || entry.getKey() == null || entry.getValue() == null) {
				System.out.println("Null thing happens");
				continue;
			}

			sb.append("key : " + entry.getKey() + ", value : " + entry.getValue() + ", value class : " + entry.getValue().getClass());

			if (entry.getValue() instanceof Map) {
				sb.append(debugHelper(((Map) entry.getValue()), isRecursive));
			}
		}

		return sb.toString();
	}

	private void mapErrorStatus(Exception e, ResponseDTO resp) {
		if (e instanceof SmvServiceException) {
			SmvServiceException smvException = (SmvServiceException) e;
			resp.setStatusCode(smvException.getErrorCode());
			resp.setMessage(smvException.getErrorMessage());
			resp.setValue(null);
		} else {
			resp.setStatusCode(SmvErrorCode.SYSTEM_UNKNOWN_EXCEPTION);
			resp.setMessage(SmvErrorCode.SYSTEM_UNKNOWN_EXCEPTION_MSG);
			resp.setValue(null);
		}
	}

}

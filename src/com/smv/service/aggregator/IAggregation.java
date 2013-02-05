package com.smv.service.aggregator;

import java.util.List;
import java.util.Map;

import javax.jws.WebParam;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.bean.MailerDataDTO;
import com.smv.common.bean.OutletDTO;
import com.smv.common.bean.ResponseDTO;
import com.smv.common.bean.UserDTO;
import com.smv.common.bean.UserOutletContentDTO;
import com.smv.common.bean.UserOutletDTO;
import com.smv.common.exception.SmvServiceException;


/**
 * @author Minh Do
 * 03/01/2010
 */
public interface IAggregation {
		
	// For User Engine
	public ResponseDTO getFeatureImages(Map<String, String> httpEnv);
	public ResponseDTO login(UserDTO user, Map<String, String> httpEnv);
	public ResponseDTO logout(UserDTO user, Map<String, String> httpEnv);
	public ResponseDTO register(UserDTO user, Map<String, String> httpEnv);
	public ResponseDTO getAccount(UserDTO user, Map<String, String> httpEnv);
	public ResponseDTO getUser(UserDTO user, Map<String, String> httpEnv); 
	public ResponseDTO isUserExist(String emailAddress, Map<String, String> httpEnv); 
	public ResponseDTO changePassword(UserDTO user, String newPassword, Map<String, String> httpEnv);
	public ResponseDTO activateUser(String activationCode, Map<String, String> httpEnv);
	public ResponseDTO regenerateActivationCode(UserDTO user, Map<String, String> httpEnv);
	public ResponseDTO forgotPassword(UserDTO user, Map<String, String> httpEnv);
	public ResponseDTO changeForgottenPassword(UserDTO user, Map<String, String> httpEnv);
	
	
	// For File Engine
	public ResponseDTO getEventById(Long eventId, Map<String, String> httpEnv);
	public ResponseDTO getEventsByUser(UserDTO user, Boolean showItems, Map<String, String> httpEnv);
	public ResponseDTO getItemsByEvent(EventDTO event, Map<String, String> httpEnv);
	public ResponseDTO getMostRecentItemsByUser(UserDTO user, Integer num, Map<String, String> httpEnv);
	public ResponseDTO getMostRecentItems(Integer num, Map<String, String> httpEnv);
	public ResponseDTO getItemById(Long itemId, Map<String, String> httpEnv);
	public ResponseDTO updateItem(ItemDTO item, Map<String, String> httpEnv);
	public ResponseDTO updateEvent(EventDTO event, Map<String, String> httpEnv);
	
	// For Catalog Engine
	public ResponseDTO getAllProducts(Map<String, String> httpEnv);
	
	
	//For Android
	//public Object processRequest(Map input, Map<String, String> httpEnv);
	public Object processRequest(String input, Map<String, String> httpEnv);
	

	//For Mailer Engine
	public ResponseDTO sendMail(MailerDataDTO mailer, Map<String, String> httpEnv); 
  

	//For Outlet Engine
	public ResponseDTO getOutlet(Map<String, String> httpEnv); 
	public ResponseDTO getOutletForUser(Long userId, Map<String, String> httpEnv); 
	public ResponseDTO enableOutletForUser(UserOutletDTO userOutlet, Map<String, String> httpEnv); 
	public ResponseDTO disableOutletForUser(UserOutletDTO userOutlet, Map<String, String> httpEnv); 
	public ResponseDTO publishToOutlets(Long userId, UserOutletContentDTO content, OutletDTO[] outlets, Map<String, String> httpEnv); 
  
}

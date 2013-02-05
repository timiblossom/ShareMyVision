package com.smv.service.aggregator.helper;

import java.util.Map;

import com.smv.common.Constant;
import com.smv.common.bean.AccountDTO;
import com.smv.common.bean.ContactDTO;
import com.smv.common.bean.EventDTO;
import com.smv.common.bean.UserDTO;
import com.smv.common.bean.UserOutletDTO;


/**
 * @author Minh Do
 * 07/01/2010
 */
public class ObjectConverter {
	//For File Module
	public static Long getEventId(Map input) {
		Map<String, String> eventMap = getObjectFromInput("event", input.get(Constant.HTML_FORM_PREFIX), input);
		
		if (eventMap == null)
			return null;
		
		return new Long(eventMap.get("eventId"));
	}
	
	public static Long getItemId(Map input) {
		Map<String, String> itemMap = getObjectFromInput("item", input.get(Constant.HTML_FORM_PREFIX), input);
		
		if (itemMap == null)
			return null;
		
		return new Long(itemMap.get("itemId"));
	}
	
	public static Boolean getShowIems(Map input) {
		Map<String, String> eventMap = getObjectFromInput("event", input.get(Constant.HTML_FORM_PREFIX), input);
		
		if (eventMap == null)
			return null;
		
		return new Boolean(eventMap.get("showItems"));
	}
	
	public static EventDTO mapToEventDTO(Map input) {
				
		Map<String, String> eventMap = getObjectFromInput("event", input.get(Constant.HTML_FORM_PREFIX), input);
		
		if (eventMap == null)
			return null;
		
		EventDTO event = new EventDTO();
		String commit = eventMap.get("commit");
		event.setAction(commit);
		event.setEventCode(eventMap.get("eventCode")==null? "" : eventMap.get("eventCode"));
		event.setEventDescription(eventMap.get("eventDescription")==null? "" : eventMap.get("eventDescription"));
		event.setEventTitle(eventMap.get("eventTitle")==null? "" : eventMap.get("eventTitle"));
		event.setAccountId(eventMap.get("accountId")==null ? null : new Long(eventMap.get("accountId")));
		event.setEventId(eventMap.get("eventId")==null? null : new Long(eventMap.get("eventId")));
		event.setEventPublic(eventMap.get("public")==null? null : new Boolean(eventMap.get("public")));
		event.setStatus(eventMap.get("eventStatus")==null? null : eventMap.get("eventStatus"));
		event.setEventTagText(eventMap.get("eventTags")==null? null : eventMap.get("eventTags"));
		event.setUserId(eventMap.get("userId")==null? null : new Long(eventMap.get("userId")));
		
		return event;
	}
	
	//For User Module
	public static String mapToNewPassword(Map input) {
		Map<String, String> userMap = getObjectFromInput("user", input.get(Constant.HTML_FORM_PREFIX), input);

		if (userMap == null)
			return null;
		
		String newPassword = userMap.get("newPassword")==null? "" : userMap.get("newPassword");        
		return newPassword;
	}

	//For User Module
	public static String mapToEmailAddress(Map input) {
		Map<String, String> userMap = getObjectFromInput("user", input.get(Constant.HTML_FORM_PREFIX), input);

		if (userMap == null)
			return null;
		
		String emailAddress = userMap.get("email")==null? "" : userMap.get("email");        
		return emailAddress;
	}
	
	public static String mapToActivationCode(Map input) {
		Map<String, String> userMap = getObjectFromInput("user", input.get(Constant.HTML_FORM_PREFIX), input);

		if (userMap == null)
			return null;
		
		String newPassword = userMap.get("activationCode")==null? "" : userMap.get("activationCode");

		return newPassword;
	}
	
	public static String mapToRegenerateActivationCode(Map input) {

		Map<String, String> userMap = getObjectFromInput("user", input.get(Constant.HTML_FORM_PREFIX), input);
		
		if (userMap == null)
			return null;
		
		String newPassword = userMap.get("activationCode")==null? "" : userMap.get("activationCode");

		return newPassword;
	}
	
	public static UserDTO mapToUserDTO(Map input) {
		UserDTO user = new UserDTO();
		
		Map<String, String> userMap = getObjectFromInput("user", input.get(Constant.HTML_FORM_PREFIX), input);
		
		if (userMap == null)
			return null;
		
		String commit = userMap.get("commit");
		user.setAction(commit);
		
		user.setUserEmail(userMap.get("email")==null? "" : userMap.get("email"));
		user.setUserPassword(userMap.get("password")==null? "" : userMap.get("password"));
		user.setUserId(userMap.get("userId")== null ? (0L) : (new Long(userMap.get("userId"))));
		user.setUserStatus(userMap.get("status")==null? "" : userMap.get("status"));
		user.setUserType(userMap.get("type")==null? "" : userMap.get("type"));
		
		user.setContact(new ContactDTO());
		user.getContact().setContactLastName(userMap.get("lastName")==null? "" : userMap.get("lastName"));
		user.getContact().setContactFirstName(userMap.get("firstName")==null? "" : userMap.get("firstName"));
		
				
		return user;
	}
	
	public static AccountDTO mapToAccountDTO(Map input) {
		AccountDTO acct = new AccountDTO();
		
		return acct;
	}
	
	private static Map<String, String> getObjectFromInput(String defaultName, Object customName, Map input) {
		Map<String, String> retVal = (Map<String, String>) input.get(defaultName);
		
		if (retVal != null)
			return retVal;
		
		if (customName != null) {
			retVal = (Map<String, String>) input.get(customName); 
		}
		
		return retVal;
	}
	
/*	public static UserOutletDTO mapToUserOutletDTO(Map input)
	{
	   UserOutletDTO outlet = new UserOutletDTO();		
	   Map<String, String> outletMap = getObjectFromInput("outlet", input.get(Constant.HTML_FORM_PREFIX), input);
		
		if (outletMap == null)
			return null;
			
		String commit = outletMap.get("commit");
		outlet.setAction(commit);
		outlet.setUserId(outletMap.get("userId")==null? null : new Long(outletMap.get("userId")));		
		
		return outlet;
	}*/
}

package com.smv.service.aggregator;

import java.util.List;
import java.util.Map;

import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;

import com.smv.common.Constant;
import com.smv.common.bean.ContactDTO;
import com.smv.common.bean.EventRequestDTO;
import com.smv.common.bean.UserDTO;
import com.smv.common.bean.UserRequestDTO;
import com.smv.service.aggregator.config.ResourceManager;
import com.smv.service.aggregator.impl.EventRequestImpl;
import com.smv.service.aggregator.impl.UpsertEventRequestImpl;
import com.smv.service.aggregator.impl.UserRequestImpl;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class RequestFactory {
	private static final Logger LOGGER = Logger.getLogger(RequestFactory.class);
	
   /*
	public static IRequest constructRequest(Map input, List<Header> headers) {
		if (input == null)
			return null;

		//debugHttpEnv(httpEnv);
		debugInput(input);

		//if (input.get(Constant.EVENT) != null) {
		//EventDTO event = unmarshallEventFromMap((Map) input.get(Constant.EVENT));
		EventDTO event = unmarshallEventFromMap(input);
		if (event == null || event.getAction().equals("")) {
			return null;
		}

		//TODO: optimize this if/else flow
		if (Constant.CREATE_NEW_EVENT.equalsIgnoreCase(event.getAction()) ||
				Constant.SIGN_DELETE_URL.equalsIgnoreCase(event.getAction()) ||
				Constant.SIGN_DOWNLOAD_URL.equalsIgnoreCase(event.getAction()) ||
				Constant.SIGN_FILE_DETAIL_URL.equalsIgnoreCase(event.getAction()) ||
				Constant.SIGN_UPLOAD_URL.equalsIgnoreCase(event.getAction()) ||
				Constant.UPSERT_EVENT.equalsIgnoreCase(event.getAction())) {
			return new EventRequestImpl(event, headers);
		} 
			
		//} else if (input.get(Constant.USER) != null) {
		//	UserDTO user = unmarshallUserFromMap((Map) input.get(Constant.USER));

		//	if (user == null || user.getAction() == null) {
		//		return null;
		//	}

		//	if (Constant.REGISTER.equalsIgnoreCase(user.getAction()) ||
		//		Constant.LOGIN.equalsIgnoreCase(user.getAction())) {
		//		return new UserRequestImpl(user, headers);
		//	} 
		//}
	
		return null;
	}
    */

	public static IRequest constructFileRequest(String input, List<Header> headers) {

		EventRequestDTO eventReq = ResourceManager.getGson().fromJson(input, EventRequestDTO.class); 
		if (eventReq == null || eventReq.getAction() == null || eventReq.getAction().equals("")) {
			return null;
		}

		//TODO: optimize this if/else flow
		if (Constant.CREATE_NEW_EVENT.equalsIgnoreCase(eventReq.getAction()) ||
			Constant.SIGN_DELETE_URL.equalsIgnoreCase(eventReq.getAction()) ||
			Constant.SIGN_DOWNLOAD_URL.equalsIgnoreCase(eventReq.getAction()) ||
			Constant.SIGN_FILE_DETAIL_URL.equalsIgnoreCase(eventReq.getAction()) ||
			Constant.SIGN_UPLOAD_URL.equalsIgnoreCase(eventReq.getAction())) {
			return new EventRequestImpl(eventReq, headers);
		} else if (Constant.UPSERT_EVENT.equalsIgnoreCase(eventReq.getAction())) {
			return new UpsertEventRequestImpl(eventReq, headers);
		}

		return null;
	}


	public static IRequest constructUserRequest(String input, List<Header> headers) {

		UserRequestDTO userReq = ResourceManager.getGson().fromJson(input, UserRequestDTO.class);  

		if (userReq == null || userReq.getAction() == null || userReq.getAction().equals("")) {
			return null;
		}

		if (Constant.REGISTER.equalsIgnoreCase(userReq.getAction()) ||
				Constant.LOGIN.equalsIgnoreCase(userReq.getAction())) {
			return new UserRequestImpl(userReq, headers);
		} 

		return null;
	}

	/*
	private static UserDTO unmarshallUserFromMap(Map userMap) {
		UserDTO user = new UserDTO();

		user.setAction(userMap.get(Constant.ACTION) == null? null : String.valueOf(userMap.get(Constant.ACTION)));		
		user.setUserEmail(userMap.get("userEmail")==null? null : String.valueOf(userMap.get("userEmail")));
		user.setUserPassword(userMap.get("userPassword")==null? null : String.valueOf(userMap.get("userPassword")));
		user.setUserId(userMap.get("userId")== null ? (0L) : new Long(String.valueOf(userMap.get("userId"))));
		user.setUserStatus(userMap.get("userStatus")==null? null : String.valueOf(userMap.get("userStatus")));
		user.setUserType(userMap.get("userType")==null? null : String.valueOf(userMap.get("userType")));

		Map contactMap = (Map) userMap.get("contact");

		if (contactMap != null) {
			user.setContact(new ContactDTO());
			user.getContact().setContactLastName(contactMap.get("contactLastName")==null? null : String.valueOf(contactMap.get("contactLastName")));
			user.getContact().setContactFirstName(contactMap.get("contactFirstName")==null? null : String.valueOf(contactMap.get("contactFirstName")));
		}

		return user;
	}


	private static EventDTO unmarshallEventFromMap1(Map eventMap) {
		EventDTO event = new EventDTO();
		event.setAction(eventMap.get(Constant.ACTION) == null? "" : (String) eventMap.get(Constant.ACTION));
		event.setEventId(eventMap.get(Constant.EVENT_ID) == null? 0L : ((Long)eventMap.get(Constant.EVENT_ID)).longValue());
		event.setEventTitle(eventMap.get(Constant.TITLE) == null? "" : (String)eventMap.get(Constant.TITLE));
		event.setEventDescription(eventMap.get(Constant.DESCRIPTION) == null? "" : (String)eventMap.get(Constant.DESCRIPTION));
		event.setAccountId(eventMap.get(Constant.AID) == null? 0 : ((Long)eventMap.get(Constant.AID)).longValue());
		event.setUserId(eventMap.get(Constant.UID) == null? 0 : ((Long)eventMap.get(Constant.UID)).longValue());
		event.setEventCode((String)eventMap.get("eventCode"));
		//event.setEventPublic(eventMap.get())
		event.setEventTagText((String)eventMap.get("eventTagText"));
		event.setExpiredOn(eventMap.get("expiredOn") == null ? null : (Long) eventMap.get("expiredOn"));
		event.setStatus((String) eventMap.get("status"));


		if (eventMap.get("items") != null) {
			List<ItemDTO> itemDtoList = new ArrayList<ItemDTO>();
			Map itemsMap = (Map) eventMap.get("items");
			List itemList = (RubyArray) itemsMap.get(Constant.ITEM);
			ItemDTO item;
			for(int i=0; i<itemList.size(); i++) {
				Map entryMap = (Map) itemList.get(i);
				item = new ItemDTO();
				item.setAction(entryMap.get("action") == null? "" : (String)entryMap.get("action"));
				Object obj = entryMap.get("userId");
				item.setUserId(entryMap.get("userId") == null? null : (Long) entryMap.get("userId"));
				item.setFileName(entryMap.get("fileName") == null? "" : (String) entryMap.get("fileName"));
				item.setFileSize(entryMap.get("fileSize") == null? 0L : (Long) entryMap.get("fileSize"));
				item.setPath(entryMap.get("path") == null? "" : (String)entryMap.get("path"));
				item.setMimeType(entryMap.get("mimeType") == null? "" : (String) entryMap.get("mimeType"));
				item.setSequenceId(entryMap.get("sequenceId") == null? 0 : (Long) entryMap.get("sequenceId"));
				item.setStatus(entryMap.get("status") == null? "" : (String) entryMap.get("status"));
				item.setUrl(entryMap.get("url") == null? "" : (String) entryMap.get("url"));

				itemDtoList.add(item);
			}

			event.setItems(itemDtoList.toArray(new ItemDTO[0]));
		}

		return event;
	}
  
	private static EventDTO unmarshallEventFromMap(Map eventMap) {
		EventDTO event = new EventDTO();
		event.setAction(eventMap.get(Constant.ACTION) == null? "" : (String) eventMap.get(Constant.ACTION));
		event.setEventId(eventMap.get(Constant.EVENT_ID) == null? 0L : ((Long)eventMap.get(Constant.EVENT_ID)).longValue());
		event.setEventTitle(eventMap.get(Constant.TITLE) == null? "" : (String)eventMap.get(Constant.TITLE));
		event.setEventDescription(eventMap.get(Constant.DESCRIPTION) == null? "" : (String)eventMap.get(Constant.DESCRIPTION));
		event.setAccountId(eventMap.get(Constant.AID) == null? 0 : ((Long)eventMap.get(Constant.AID)).longValue());
		event.setUserId(eventMap.get(Constant.UID) == null? 0 : ((Long)eventMap.get(Constant.UID)).longValue());
		event.setEventCode((String)eventMap.get("eventCode"));
		//event.setEventPublic(eventMap.get())
		event.setEventTagText((String)eventMap.get("eventTagText"));
		event.setExpiredOn(eventMap.get("expiredOn") == null ? null : (Long) eventMap.get("expiredOn"));
		event.setStatus((String) eventMap.get("status"));


		if (eventMap.get("items") != null) {
			List<ItemDTO> itemDtoList = new ArrayList<ItemDTO>();

			List itemList = (List) eventMap.get("items");
			ItemDTO item;
			for(int i=0; i<itemList.size(); i++) {
				Map entryMap = (Map) itemList.get(i);
				item = new ItemDTO();
				item.setAction(entryMap.get("action") == null? "" : (String)entryMap.get("action"));
				Object obj = entryMap.get("userId");
				item.setUserId(entryMap.get("userId") == null? null : (Long) entryMap.get("userId"));
				item.setFileName(entryMap.get("fileName") == null? "" : (String) entryMap.get("fileName"));
				item.setFileSize(entryMap.get("fileSize") == null? 0L : (Long) entryMap.get("fileSize"));
				item.setPath(entryMap.get("path") == null? "" : (String)entryMap.get("path"));
				item.setMimeType(entryMap.get("mimeType") == null? "" : (String) entryMap.get("mimeType"));
				item.setSequenceId(entryMap.get("sequenceId") == null? 0 : (Long) entryMap.get("sequenceId"));
				item.setStatus(entryMap.get("status") == null? "" : (String) entryMap.get("status"));
				item.setUrl(entryMap.get("url") == null? "" : (String) entryMap.get("url"));

				itemDtoList.add(item);
			}

			event.setItems(itemDtoList.toArray(new ItemDTO[0]));
		}

		return event;
	}


	private static void debugHttpEnv(Map httpEnv) {
		System.out.println("==============debugHttpEnv==========================");
		Iterator iter = httpEnv.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			System.out.println("Http Env : " + key + ", " + httpEnv.get(key));
		}

	}

	private static void debugInput(Map input) {
		System.out.println("========================================");
		Iterator iter = input.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			System.out.println("Input : -->" + key + "--<, -->" + input.get(key) + "<--");
		}

	}
	
	  */
}

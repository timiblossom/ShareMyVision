package com.smv.test.dto;

import com.google.gson.Gson;
import com.smv.common.Constant;
import com.smv.common.bean.ContactDTO;
import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.bean.UserDTO;
import com.smv.common.bean.UserRequestDTO;
import com.smv.common.bean.UserResponseDTO;

public class GSonTest {
	private static UserDTO createUserForRegistration() {
		UserDTO user = new UserDTO();
		
		user.setAction(Constant.REGISTER);
		user.setUserEmail("mdo2@qualys.comn");
		user.setUserPassword("123");
		
		user.setContact(new ContactDTO());
		user.getContact().setContactFirstName("Minh");
		user.getContact().setContactLastName("DO");
		
		return user;
	}
	
	private static EventDTO createSignedUploadURLRequest() {
		
		
		EventDTO event = new EventDTO();
		event.setAction(Constant.SIGN_UPLOAD_URL);
		
		ItemDTO item = new ItemDTO();
		item.setUserId(3L);
		item.setFileName("coloseum.jpg");
		item.setFileSize(1111L);
		//item.setPath(PATH);
		item.setPath("abc/d1212");
	
		
		event.setItems(new ItemDTO[]{item});						
		
		return event;
	}

	public static void main(String[] args) {
		Gson gson = new Gson();
		UserDTO user = createUserForRegistration();
		String gStr = gson.toJson(user);
		System.out.println("GSon : " + gStr);
		//UserDTO user = gson.fromJson(gStr, UserDTO.class);
		//System.out.println("User action : " + user.getAction());
		
		//XStream xstream = SmvJSonXStream.getXStream(); 
		//System.out.println("Xstream : " + xstream.toXML(createUserForRegistration()));
		
		gStr = gson.toJson(createSignedUploadURLRequest());
		System.out.println("Gson for Event : " + gStr);
		
		//System.out.println("Xstream : " + xstream.toXML(createSignedUploadURLRequest()));
		
		Group group = new Group();
		group.setUser(user);
		group.setType("typeA");
		
		System.out.println("333 : " + gson.toJson(group));
		
		UserRequestDTO uRequest = new UserRequestDTO();
		
		uRequest.getUsers().add(user);
		
		System.out.println("4 : " + gson.toJson(uRequest));
		
		UserResponseDTO uResponse = new UserResponseDTO();
		uResponse.getUsers().add(user);
		uResponse.setMessage("A message");
		uResponse.setStatusCode(1);
		
		System.out.println("5 : " + gson.toJson(uResponse));
		
		UserResponseDTO u2Response = gson.fromJson(gson.toJson(uResponse), UserResponseDTO.class);
		System.out.println("66 : " + u2Response.getMessage());
		
	}
	
	
	private static class Group {
		private String type;
		private UserDTO user;
		
		public void setType(String type) {
			this.type = type;
		}
		public String getType() {
			return type;
		}
		
		public void setUser(UserDTO user) {
			this.user = user;
		}
		public UserDTO getUser() {
			return user;
		}
	}
	
}

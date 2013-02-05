package com.smv.common.bean;



import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class UserRequestDTO {
	
	private String action;
	
    @XmlElement(name = "user", required = true)
    private List<UserDTO> userList = new ArrayList<UserDTO>();

    public List<UserDTO> getUsers() {
        return userList;
    }

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	
}

package com.smv.common.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class UserResponseDTO {
	private int statusCode;
	private String message;
	
    @XmlElement(name = "user", required = true)
    private List<UserDTO> userList = new ArrayList<UserDTO>();

    public List<UserDTO> getUsers() {
        return userList;
    }

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}

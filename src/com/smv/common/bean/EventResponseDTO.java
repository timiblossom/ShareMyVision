package com.smv.common.bean;



import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class EventResponseDTO {
	private int statusCode;
	private String message;
	
	@XmlElement(name = "event", required = true)
	private List<EventDTO> eventList = new ArrayList<EventDTO>();

	public List<EventDTO> getEvents() {
		return eventList;
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

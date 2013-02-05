package com.smv.common.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class EventRequestDTO {
	private String action;

	@XmlElement(name = "event", required = true)
	private List<EventDTO> eventList = new ArrayList<EventDTO>();

	public List<EventDTO> getEvents() {
		return eventList;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

}
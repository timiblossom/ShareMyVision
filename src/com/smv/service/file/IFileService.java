package com.smv.service.file;


import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.bean.UserDTO;
import com.smv.common.exception.SmvServiceException;


/**
 * @author Minh Do
 * 03/2010
 */

@WebService
public interface IFileService {

    public EventDTO eventRequest(@WebParam(name = "event") EventDTO event) throws SmvServiceException;
        
	public EventDTO getEventById(@WebParam(name = "eventId") Long id) throws SmvServiceException;
	public List<EventDTO> getEventsByUser(@WebParam(name = "user") UserDTO user, @WebParam(name = "showItems") Boolean showItems) throws SmvServiceException;
	public List<ItemDTO> getItemsByEvent(@WebParam(name = "event") EventDTO event) throws SmvServiceException;
	public List<ItemDTO> getMostRecentItemsByUser(@WebParam(name = "user") UserDTO user, @WebParam(name = "num") Integer num) throws SmvServiceException;
	public List<ItemDTO> getMostRecentItems(@WebParam(name = "num") Integer num) throws SmvServiceException;
	public ItemDTO getItemById(@WebParam(name = "itemId") Long id) throws SmvServiceException;
	
	public boolean updateEvent(@WebParam(name = "event") EventDTO event) throws SmvServiceException;
	public boolean updateItem(@WebParam(name = "item") ItemDTO item) throws SmvServiceException;
}


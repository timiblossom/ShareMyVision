package com.smv.service.file;

import java.util.List;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;

/**
 * @author Minh Do
 * 03/2010
 */
public interface IFile {
	
	public List<EventDTO> getEventsByUserId(Long uid, boolean showItems) throws Exception;
	public List<ItemDTO> getMostRecentItems(Integer num) throws Exception;
	public List<ItemDTO> getMostRecentItemsByUserId(Long uid, Integer num) throws Exception;
	public List<ItemDTO> getItemsByEventId(Long eid) throws Exception;
	
}

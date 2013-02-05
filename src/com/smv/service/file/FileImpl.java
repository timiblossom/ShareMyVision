package com.smv.service.file;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.service.file.db.dao.EventDAO;
import com.smv.service.file.db.dao.ItemDAO;
import com.smv.service.file.db.dbobject.EventDO;
import com.smv.service.file.db.dbobject.ItemDO;
import com.smv.service.file.mapper.EventMapper;
import com.smv.service.file.mapper.ItemMapper;


/**
 * @author Minh Do
 * 03/2010
 */
public class FileImpl implements IFile {
	private static final Logger LOGGER = Logger.getLogger(FileImpl.class);


	@Override
	public List<EventDTO> getEventsByUserId(Long uid, boolean showItems) throws Exception {
		if (uid == null)
			return null;
		
		List<EventDTO> retVal = new ArrayList<EventDTO>();
		List<EventDO> eventList = EventDAO.lookupEventsByUserId(uid, showItems);
		
		for(EventDO event : eventList) {
			retVal.add(EventMapper.eventDO2DTOMapper(event));
		}
		
		return retVal;

	}
	
	
	

	@Override
	public List<ItemDTO> getMostRecentItems(Integer num) throws Exception {
		
		List<ItemDTO> retVal = new ArrayList<ItemDTO>();
		List<ItemDO> list = ItemDAO.getMostRecentItems(num);
		
		for(ItemDO item : list) {
			retVal.add(ItemMapper.itemDO2DTO(item));
		}
		
		return retVal;
	}

	@Override
	public List<ItemDTO> getMostRecentItemsByUserId(Long uid, Integer num) 	throws Exception {
		if (uid == null)
			return null;
		
		List<ItemDTO> retVal = new ArrayList<ItemDTO>();
		List<ItemDO> list = ItemDAO.getMostRecentItemsByUserId(uid, num);
		
		for(ItemDO item : list) {
			retVal.add(ItemMapper.itemDO2DTO(item));
		}
		
		return retVal;
	}

	@Override
	public List<ItemDTO> getItemsByEventId(Long eid) throws Exception {
		
		if (eid == null)
			return null;
		
		List<ItemDTO> retVal = new ArrayList<ItemDTO>();
		List<ItemDO> list = ItemDAO.getItemsByEventId(eid);
		
		for(ItemDO item : list) {
			retVal.add(ItemMapper.itemDO2DTO(item));
		}
		
		return retVal;
	}


}

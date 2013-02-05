package com.smv.service.file.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.service.file.db.dbobject.EventDO;
import com.smv.service.file.db.dbobject.ItemDO;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class EventMapper {
	public static EventDTO eventDO2DTOMapper(EventDO eventDo) {
		EventDTO eventDto;
		
		if (eventDo == null)
			return null;
		
		eventDto = new EventDTO();
		eventDto.setAccountId(eventDo.getAid());
		eventDto.setUserId(eventDo.getUid());
		eventDto.setEventId(eventDo.getId());
		eventDto.setEventCode(eventDo.getEventCode());
		eventDto.setEventDescription(eventDo.getDescription());
		eventDto.setFolderUrl(eventDo.getFolderUrl());
		eventDto.setEventTitle(eventDo.getEventTitle());
		eventDto.setExpiredOn(eventDo.getExpiredOn()!=null? eventDo.getExpiredOn().getTime() : null);
		eventDto.setPostedOn(eventDo.getPostedOn()!= null? eventDo.getPostedOn().getTime() : null);
		eventDto.setStatus(eventDo.getStatus());
		eventDto.setEventPublic(eventDo.getIsPublic());
		eventDto.setEventTagText(eventDo.getTagText());

		
		Set<ItemDO> items = eventDo.getItems();
		if (items != null && !items.isEmpty()) {
			List<ItemDTO> itemList = new ArrayList<ItemDTO>();

			for(ItemDO item : items) {
				ItemDTO itemDto = ItemMapper.itemDO2DTO(item);
				itemList.add(itemDto);
			}
			eventDto.setItems(itemList.toArray(new ItemDTO[0]));
		}
		
		return eventDto;
	}
	
	
	public static EventDO eventDTO2DOMapper(EventDTO eventDTO) {
		EventDO eventDO = null;
		if (eventDTO == null) {
			return null;
		}
		
		eventDO = new EventDO();
		eventDO.setAid(eventDTO.getAccountId());		
		eventDO.setEventCode(eventDTO.getEventCode());
		eventDO.setEventTitle(eventDTO.getEventTitle());
		eventDO.setDescription(eventDTO.getEventDescription());
		eventDO.setFolderUrl(eventDTO.getFolderUrl());
		eventDO.setExpiredOn(eventDTO.getExpiredOn() == null? null : new Date(eventDTO.getExpiredOn()));
		eventDO.setId(eventDTO.getEventId());
		eventDO.setIsPublic(eventDTO.isEventPublic());
		eventDO.setStatus(eventDTO.getStatus());
		eventDO.setPostedOn(eventDTO.getPostedOn() == null? null : new Date(eventDTO.getPostedOn()));
		eventDO.setTagText(eventDTO.getEventTagText());
		eventDO.setUid(eventDTO.getUserId());
		
		return eventDO;
	}

}

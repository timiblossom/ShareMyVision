package com.smv.service.file.mapper;

import java.util.Date;

import com.smv.common.bean.ItemDTO;
import com.smv.service.file.db.dbobject.ItemDO;


/**
 * @author Minh Do
 * 07/01/2010
 */
public class ItemMapper {
	
	public static ItemDTO itemDO2DTO(ItemDO itemDo) {
		ItemDTO retVal = null;
		
		if (itemDo != null) {
			retVal = new ItemDTO();
			
			retVal.setItemId(itemDo.getId());
			retVal.setItemDescription(itemDo.getDescription());
			retVal.setDeviceId(itemDo.getDeviceId());
			retVal.setUserId(itemDo.getUid());
			retVal.setAccountId(itemDo.getAid());
			retVal.setDuration(itemDo.getDuration());
			retVal.setEventId(itemDo.getEventId());
			retVal.setExpiredOn(itemDo.getExpiredOn()!=null? itemDo.getExpiredOn().getTime() : null);
			retVal.setFileSize(itemDo.getSize());
			retVal.setLatitude(itemDo.getLatitude());
			retVal.setLongitude(itemDo.getLongitude());
			retVal.setAltitude(itemDo.getAltitude());
			retVal.setLocation(itemDo.getLocation());
			
			retVal.setHeight(itemDo.getHeight());
			retVal.setWidth(itemDo.getWidth());
			retVal.setItemCode(itemDo.getItemCode());
			retVal.setItemTitle(itemDo.getItemTitle());
			retVal.setMimeType(itemDo.getMimeType());
			retVal.setPostedOn(itemDo.getPostedOn()!=null? itemDo.getPostedOn().getTime() : null);
			retVal.setItemPublic(itemDo.getIsPublic());
			retVal.setSequenceId(itemDo.getSequenceId());
			retVal.setStatus(itemDo.getStatus());
			
			retVal.setUrl(itemDo.getStorageId());
			retVal.setUrl1(itemDo.getStorageId1());
			retVal.setUrl2(itemDo.getStorageId2());
			retVal.setUrl3(itemDo.getStorageId3());
			retVal.setProcessDone(itemDo.getProcessDone());
		}
		
		return retVal;
	}
	
	
	public static ItemDO itemDTO2DO(ItemDTO itemDTO) {
		ItemDO retVal = null;
		
		if (itemDTO != null) {
			retVal = new ItemDO();
			retVal.setAid(itemDTO.getAccountId());
			retVal.setDescription(itemDTO.getItemDescription());
			retVal.setDeviceId(itemDTO.getDeviceId());
			retVal.setDuration(itemDTO.getDuration());
			retVal.setEventId(itemDTO.getEventId());
			retVal.setExpiredOn(itemDTO.getExpiredOn() == null? null : new Date(itemDTO.getExpiredOn()));
			retVal.setLatitude(itemDTO.getLatitude());
			retVal.setLongitude(itemDTO.getLongitude());
			retVal.setAltitude(itemDTO.getAltitude());
			retVal.setLocation(itemDTO.getLocation());
			
			retVal.setHeight(itemDTO.getHeight());
			retVal.setId(itemDTO.getItemId());
			retVal.setIsPublic(itemDTO.isItemPublic());
			retVal.setItemCode(itemDTO.getItemCode());
			retVal.setItemTitle(itemDTO.getItemTitle());
			retVal.setMimeType(itemDTO.getMimeType());
			retVal.setSequenceId(itemDTO.getSequenceId());
			retVal.setSize(itemDTO.getFileSize());
			retVal.setStatus(itemDTO.getStatus());
			retVal.setStorageId(itemDTO.getUrl());
			retVal.setStorageId1(itemDTO.getUrl1());
			retVal.setStorageId2(itemDTO.getUrl2());
			retVal.setStorageId3(itemDTO.getUrl3());
			retVal.setProcessDone(itemDTO.getProcessDone());
			retVal.setUid(itemDTO.getUserId());
			retVal.setWidth(itemDTO.getDuration());
		}
		
		return retVal;
	}
}

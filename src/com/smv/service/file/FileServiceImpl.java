package com.smv.service.file;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.apache.log4j.Logger;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.bean.UserDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.file.db.dao.EventDAO;
import com.smv.service.file.db.dao.ItemDAO;
import com.smv.service.file.db.dbobject.EventDO;
import com.smv.service.file.db.dbobject.ItemDO;
import com.smv.service.file.mapper.EventMapper;
import com.smv.service.file.mapper.ItemMapper;
import com.smv.util.db.AbstractDO;
import com.smv.util.soap.SoapUtil;
import com.smv.util.thread.InstanceGenerator;


/**
 * @author Minh Do
 * 03/2010
 */

@WebService(endpointInterface = "com.smv.service.file.IFileService", serviceName = "FileService", targetNamespace = "http://smv")
public class FileServiceImpl implements IFileService {
	private static final Logger LOGGER = Logger.getLogger(FileServiceImpl.class);
	
	@Resource
	private WebServiceContext ctx;
	
	
	public EventDTO getEventById(Long id) throws SmvServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getEventById - id : " + id);
		}
		
		if (id == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_EVENT_ID_EXCEPTION, SmvErrorCode.FS_NULL_EVENT_ID_EXCEPTION_MSG);
		}
		
		EventDO event = EventDAO.getEventById(id);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getEventById - id : " + id);
		}
		
		return EventMapper.eventDO2DTOMapper(event);
	}

	@Override
	public List<EventDTO> getEventsByUser(UserDTO user, Boolean showItems) throws SmvServiceException {
		List<EventDTO> retVal = null;
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getEventsByUser - UserDTO : " + user);
		}
		
		if (user == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_USER_EXCEPTION, SmvErrorCode.FS_NULL_USER_EXCEPTION_MSG);
		}			
		
		if (user.getUserId() == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_USER_ID_INPUT, SmvErrorCode.FS_NULL_USER_ID_INPUT_MSG);
		}			
		
		try {			
			if (showItems == null || showItems) {
				retVal = InstanceGenerator.getFileImplInstance().getEventsByUserId(user.getUserId().longValue(), true);
			} else {
				retVal = InstanceGenerator.getFileImplInstance().getEventsByUserId(user.getUserId().longValue(), false);
			}
		} catch (SmvServiceException e) {
			throw e;
		} catch (Exception e) {
			SmvServiceException sse = new SmvServiceException();
			throw sse; //TODO: will set the parameters late
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getEventsByUser - UserDTO : " + user);
		}
		
		return retVal;
	}

	@Override
	public List<ItemDTO> getItemsByEvent(EventDTO event)
			throws SmvServiceException {
		
		List<ItemDTO> retVal = null;
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getItemsByEvent - EventDTO : " + event);
		}
				
		if (event == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_EVENT_INPUT, SmvErrorCode.FS_NULL_EVENT_INPUT_MSG);
		}
		
		if (event.getEventId() == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_EVENT_ID_EXCEPTION, SmvErrorCode.FS_NULL_EVENT_ID_EXCEPTION_MSG);
		}
			
		
		try {
			retVal = InstanceGenerator.getFileImplInstance().getItemsByEventId(event.getEventId());			
		} catch (SmvServiceException e) {
			throw e;
		} catch (Exception e) {
			SmvServiceException sse = new SmvServiceException();
			throw sse; //TODO: will set the parameters late
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getItemsByEvent - EventDTO : " + event);
		}
		
		return retVal;
	}

	@Override
	public List<ItemDTO> getMostRecentItemsByUser(UserDTO user, Integer num)
			throws SmvServiceException {
		List<ItemDTO> retVal = null;
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getMostRecentItemsByUser - UserDTO : " + user);
		}
		
		if (user == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_USER_EXCEPTION, SmvErrorCode.FS_NULL_USER_EXCEPTION_MSG);
		}	
		
		if (user.getUserId() == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_USER_ID_INPUT, SmvErrorCode.FS_NULL_USER_ID_INPUT_MSG);
		}	
		
		try {
			retVal = InstanceGenerator.getFileImplInstance().getMostRecentItemsByUserId(user.getUserId().longValue(), num);			
		} catch (SmvServiceException e) {
			LOGGER.error(e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(e);
			SmvServiceException sse = new SmvServiceException();
			throw sse; //TODO: will set the parameters late
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getMostRecentItemsByUser - UserDTO : " + user);
		}
		
		return retVal;
	}
	
	public List<ItemDTO> getMostRecentItems(Integer num) throws SmvServiceException {
		List<ItemDTO> retVal = null;
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getMostRecentItems - Integer : " + num);
		}
		
		if (num == null) {
			num = new Integer(1);
		}
		
		try {			
			retVal = InstanceGenerator.getFileImplInstance().getMostRecentItems(num);			
		} catch (SmvServiceException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e);
			SmvServiceException sse = new SmvServiceException();
			throw sse; //TODO: will set the parameters late
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getMostRecentItems - Integer : " + num);
		}
		
		return retVal;
	}
	


	@Override
	public ItemDTO getItemById(Long id) throws SmvServiceException {
		ItemDTO retVal = null;
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getItemById - Long : " + id);
		}
		
		if (id == null) {
			throw new SmvServiceException(SmvErrorCode.FS_ITEM_ID_MISSING_EXCEPTION, SmvErrorCode.FS_ITEM_ID_MISSING_EXCEPTION_MSG);			
		}
						
		retVal = ItemMapper.itemDO2DTO(ItemDAO.getItemById(id));
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getItemById - Long : " + id);
		}
		
		return retVal;
	}


	@Override
	public boolean updateEvent(EventDTO event) throws SmvServiceException {
		boolean retVal = true;
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter updateEvent - EventDTO : " + event);
		}
		
		EventDO eventDO;
		
		if (event == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_EVENT_INPUT, SmvErrorCode.FS_NULL_EVENT_INPUT_MSG);
		}
		
		if (event.getEventId() == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_EVENT_ID_EXCEPTION, SmvErrorCode.FS_NULL_EVENT_ID_EXCEPTION_MSG);
		}	
		
		eventDO = EventMapper.eventDTO2DOMapper(event);
		eventDO.setOperation(AbstractDO.UPDATE);
				
		try {
			EventDAO eventBo = new EventDAO(eventDO);
			eventBo.save(true, false);
		} catch (Exception e) {  
			LOGGER.error(e);
			retVal = false;
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit updateEvent - EventDTO : " + event);
		}
	
		return retVal;
	}


	@Override
	public boolean updateItem(ItemDTO item) throws SmvServiceException {
		boolean retVal = true;
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter updateItem - ItemDTO : " + item);
		}
		
		ItemDO itemDO;
		
		if (item == null) {
			throw new SmvServiceException(SmvErrorCode.FS_NULL_ITEM_EXCEPTION, SmvErrorCode.FS_NULL_ITEM_EXCEPTION_MSG);
		}
		
		if (item.getItemId() == null) {
			throw new SmvServiceException(SmvErrorCode.FS_ITEM_ID_MISSING_EXCEPTION, SmvErrorCode.FS_ITEM_ID_MISSING_EXCEPTION_MSG);
		}
		
		itemDO = ItemMapper.itemDTO2DO(item);
		itemDO.setOperation(AbstractDO.UPDATE);
		
		try {
			ItemDAO itemBo = new ItemDAO(itemDO);
			itemBo.save(true, false);
		} catch (Exception e) {
			LOGGER.error(e);
			retVal = false;
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit updateItem - ItemDTO : " + item);
		}
		
		return retVal;
	}
	
	
	/*************************************************************************************
	 * For Mobile API
	 *************************************************************************************/
	
	@Override
	public EventDTO eventRequest(EventDTO event) throws SmvServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter eventRequest - EventDTO : " + event);
		}
		
		Map<String, String> httpEnv = getHttpEnv();
		//System.out.println(httpEnv.get(Constant.REMOTE_ADDR));
		//System.out.println(httpEnv.get(Constant.HTTP_ACCEPT_ENCODING));
		//System.out.println(httpEnv.get(Constant.SESSION));
		
		
		//event.setGuid(EventHelper.generateGuid(event, httpEnv));
		IFileRequest request = FileRequestFactory.constructRequest(event, httpEnv);
		EventDTO retVal = null;
		try {
			//TODO: call request.validate first
			retVal = (EventDTO) request.execute();
		} catch (SmvServiceException e) {
			LOGGER.error(e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(e);
			throw new SmvServiceException(SmvErrorCode.SYSTEM_UNKNOWN_EXCEPTION, SmvErrorCode.SYSTEM_UNKNOWN_EXCEPTION_MSG);						
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit eventRequest - EventDTO : " + event);
		}
		
		return retVal;
	}
	
	
	/********************************************
	 * private methods
	 ********************************************/
	private Map<String, String> getHttpEnv() {
		return SoapUtil.getHttpHeaders(ctx);
	}
	
}


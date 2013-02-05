package com.smv.test.dao.file;

import java.util.List;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.file.FileServiceImpl;
import com.smv.service.file.IFileService;
import com.smv.service.file.db.dao.EventDAO;
import com.smv.service.file.db.dao.FilePolicyDAO;
import com.smv.service.file.db.dao.FileSystemDAO;
import com.smv.service.file.db.dao.ItemDAO;
import com.smv.service.file.db.dbobject.EventDO;
import com.smv.service.file.db.dbobject.FilePolicyDO;
import com.smv.service.file.db.dbobject.FileSystemDO;
import com.smv.service.file.db.dbobject.ItemDO;
import com.smv.service.file.helper.FileHelper;
import com.smv.util.db.AbstractDO;

public class TestFileDAO {
	public void testInsertEvent() {
		EventDO event = new EventDO();
		event.setAid(1L);
		//event.setDescription("Testing");
		event.setEventTitle("Title testing");

		//event.setIsPublic(true);
		
		//event.setStatus("active");
		//event.setTagText("san francisco, city hall");
		event.setUid(1L);
		
		event.setOperation(AbstractDO.CREATE);
		
		EventDAO eventBo = new EventDAO(event);
		eventBo.save(true, false);
		
		System.out.println("event id : " + event.getId());
		
		
		EventDO eventTemDo = new EventDO();
		eventTemDo.setOperation(AbstractDO.FETCH);
		eventTemDo.setId(event.getId());
		eventBo = new EventDAO(eventTemDo);
		//eventBo.save(true, false);
		eventTemDo = (EventDO) eventBo.getServerDO();
		System.out.println("Description : " + eventTemDo.getDescription());
		
		insertItem(event);
		testAddItemToEvent(event.getId());
	}
	
	
	private void insertItem(EventDO event) {
		ItemDO item = new ItemDO();
		item.setDuration(111);
		item.setEventId(event.getId());
		item.setHeight(100);
		//item.setSequenceId(1);
		item.setSize(10000L);
		item.setStatus("Good1");
		item.setStorageId1("http://img.sharemyvision.com/aaa/ccc/pic.jpg");
		item.setWidth(100);
		
		
		item.setOperation(AbstractDO.CREATE);
		
		ItemDAO itemBo = new ItemDAO(item);
		itemBo.save(true, false);
		
		System.out.println("item id : " + item.getId());
		
		
		ItemDO itemTemDo = new ItemDO();
		itemTemDo.setOperation(AbstractDO.FETCH);
		itemTemDo.setId(item.getId());
		itemBo = new ItemDAO(itemTemDo);
		//itemBo.save(true, false);
		itemTemDo = (ItemDO) itemBo.getServerDO();
		System.out.println("Status : " + itemTemDo.getStatus());
		
		
		ItemDO updatedItemDo = new ItemDO();
		updatedItemDo.setOperation(AbstractDO.UPDATE);
		updatedItemDo.setId(item.getId());
		updatedItemDo.setSize(111L);
		itemBo = new ItemDAO(updatedItemDo);
		itemBo.save(true, false);
		updatedItemDo = itemBo.getItemDO();
		System.out.println("New size : " + updatedItemDo.getSize());
		
	}
	
	public void testAddItemToEvent(Long id) {
		EventDO eventTemDo = new EventDO();
		eventTemDo.setOperation(AbstractDO.FETCH);
		eventTemDo.setId(id);
		EventDAO eventBo = new EventDAO(eventTemDo);
		eventTemDo = (EventDO) eventBo.getServerDO();
		
		insertItem(eventTemDo);
		
	}
	
	public void testUpdateEvent() {		
		
		EventDO event = new EventDO();
		event.setOperation(AbstractDO.UPDATE);
		event.setId(1L);	

		event.setStatus("Badd");

		EventDAO entityDAO = null;
		
		try {
			entityDAO = new EventDAO(event);
		
			entityDAO.save(true, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    event = entityDAO.getEventDO();
	    System.out.println("New status : " + event.getStatus());
	}
	
	
	public void testUpdateEvent2() throws SmvServiceException {		
		
		EventDTO event = new EventDTO();
		event.setEventId(1L);	
		event.setStatus("Bad");

		IFileService fileService = new FileServiceImpl();
		
		fileService.updateEvent(event);

	}
	
	
	public void testUpdateItem2() throws SmvServiceException {		
		
		ItemDTO item = new ItemDTO();
		item.setItemId(10L);	
		item.setStatus("Bad");

		IFileService fileService = new FileServiceImpl();
		
		fileService.updateItem(item);

	}
	
	/////For EventDO and ItemDO
	public  void getEventsByUserId() {
		List<EventDO> result = EventDAO.lookupEventsByUserId(1L, true);
		System.out.println("result size : " + result.size());
		for(EventDO event : result) {
			System.out.println(event.getItems().size());
		}
	}
	
	public  void getMostRecent10Items() {
		List<ItemDO> result = ItemDAO.getMostRecentItems(5);
		System.out.println("result size : " + result.size());
		for(ItemDO event : result) {
			System.out.println(event.getItemCode());
		}
	}
	
	
	public  void getMostRecent10ItemsByUserId() {
		List<ItemDO> result = ItemDAO.getMostRecentItemsByUserId(1L, 5);
		System.out.println("result size : " + result.size());
		for(ItemDO event : result) {
			System.out.println(event.getItemCode());
		}
	}
	
	public void getItemById() {
		ItemDO item = ItemDAO.getItemById(1L);
		System.out.println("Item : " + item.getItemCode());
	}
	
	
	public void getEventById() {
		EventDO event = EventDAO.getEventById(1L);
		System.out.println("Event : " + event.getItems().size());
		for(ItemDO item : event.getItems()) {
			System.out.println("Item : " + item.getItemTitle());
		}
	}
	
	
	public void getEventByEventCode() {
		EventDO event = EventDAO.lookupEventByEventCode("abc_Rome");
		System.out.println(event.getId());
	}
	
	////FOR FileSystemDO
	public void getFileSystemByUid() {
		FileSystemDO result = FileSystemDAO.geFileSystemByUid(1L);
		System.out.println("result's folder (fileid1) : " + result.getFileId1());

	}
	
	
	
	public void insertFileSystem() {
		FileSystemDO fs = new FileSystemDO();
		fs.setId(3L);
		fs.setServer("img1.sharmyvision.com");
		fs.setEventCount(10);
		fs.setFileCount(10);
		fs.setFileId1("dddd11");
		fs.setFileId2("eeee22");
		fs.setFileId3("eeee33");
		fs.setUsedSpace(100);
	
		
		fs.setOperation(AbstractDO.CREATE);
		
		FileSystemDAO fsBo = new FileSystemDAO(fs);
		fsBo.save(true, false);
	}
	
	
	public void getOrGenerateFileSystemDO() {
		FileSystemDO fs = FileHelper.getOrGenerateFileSystemDO(4L);
		
		System.out.println("Id : " + fs.getId() + " - folder : " + fs.getFileId1());
	}
	
	public void getFilePolicyById() {
		FilePolicyDO policy = FilePolicyDAO.geFilePolicyById(1L);
		System.out.println("Name : " + policy.getName());
	}
	
	
	public void getFilePoliciesByPolicyId() {
		List<FilePolicyDO> policies = FilePolicyDAO.getFilePoliciesByPolicyId(1L);
		System.out.println("Name : " + policies.size());
	}
	
	public static void main(String[] args) throws SmvServiceException {
		TestFileDAO test = new TestFileDAO();
		//test.testInsertEvent();
		//test.testUpdateEvent();
		//test.testUpdateEvent2();
		test.testUpdateItem2();
		//test.getEventsByUserId();
		//test.getMostRecent10Items();
		//test.getMostRecent10ItemsByUserId();
		//test.getItemById();
		//test.getEventById();
		//test.getFileSystemByUid();
		//test.insertFileSystem();
		//test.getFilePolicyById();
		//test.getFilePoliciesByPolicyId();
		////test.getOrGenerateFileSystemDO();
		//test.getEventByEventCode();
	}
}

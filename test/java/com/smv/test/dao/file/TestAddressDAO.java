package com.smv.test.dao.file;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.AddressDAO;
import com.smv.service.user.db.dbobject.AddressDO;
import com.smv.util.db.AbstractDO;



public class TestAddressDAO {
	private static final Logger log = Logger.getLogger(TestAddressDAO.class);
	
	public void testInsertAddress() {
		AddressDO address = new AddressDO();
		
		address.setLatitude(1.1);
		address.setLongitude(2.2);
		address.setState("CA");
		address.setStreet("1600 Santa Teresa Blvd");
		address.setZipCode("95109");
		address.setCity("San Jose");
		
		address.setOperation(AbstractDO.CREATE);
		
		AddressDAO addressBo = new AddressDAO(address);
		addressBo.save(true, false);
		
		System.out.println("address id : " + address.getId());
		
		AddressDO addressTemVO = new AddressDO();
		addressTemVO.setOperation(AbstractDO.FETCH);
		addressTemVO.setId(address.getId());
		addressBo = new AddressDAO(addressTemVO);
		addressBo.save(true, false);
		addressTemVO = (AddressDO) addressBo.getServerDO();
		System.out.println("Street : " + addressTemVO.getStreet());
	}
	
	
	public void testGetAllAddress() {
		List<AddressDO> list = AddressDAO.getAllAddresses();
		System.out.println("size : " + list.size());
	}
	
	
	public void testGetAddressById() {
		AddressDO address = AddressDAO.getAddressById(1L);
		System.out.println("Address street : " + address.getStreet());
	}
	
	public void testGetAddressesByState() {
		List<AddressDO> list = AddressDAO.getAddressesByState("CA");
		System.out.println("size : " + list.size());
	}
	
	
	public void testUpdateAddress() {		
		
		AddressDO address = new AddressDO();
		address.setOperation(AbstractDO.UPDATE);
		address.setId(1L);	

		address.setState("AZ");

		AddressDAO entityDAO = null;
		
		try {
			entityDAO = new AddressDAO(address);
		
			entityDAO.save(true, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    address = entityDAO.getAddressDO();
	    System.out.println("New state : " + address.getState());
	}
	
	public static void main(String[] args)  {
		TestAddressDAO test = new TestAddressDAO();
		//test.testInsertAddress();
		test.testGetAllAddress();
		//test.testGetAddressById();
		//test.testGetAddressesByState();
		//test.testUpdateAddress();
	}
}

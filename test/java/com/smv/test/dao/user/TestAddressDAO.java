/**
 * 
 */
package com.smv.test.dao.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.AddressDAO;
import com.smv.service.user.db.dbobject.AddressDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */
public class TestAddressDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestAddressDAO.class);

	public TestAddressDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertAddress() {
		AddressDO address = new AddressDO();
		
		// Set attributes of DO
		address.setStreet("My Street");
		address.setCity("San Jose");
		address.setState("California");
		address.setZipCode("95131-3439");
		address.setCountry("United States");
		address.setLatitude(10.0);
		address.setLongitude(55.0);
		address.setRecordTs(22L);

		// Set operation to "CREATE" -- i.e. insert new row into database
		address.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		AddressDAO AddressDao = new AddressDAO(address);
		AddressDao.save(true, false);

		// Output/log results
		String resultLog = "address: " + address.toString(); 
		log(resultLog);
	}

	public void testGetAllAddresss() {
		List<AddressDO> allAddresss = AddressDAO.getAllAddresses();

		// Get and print out size of all Addresses
		String allAddresssSize = "size: " + allAddresss.size();
		log(allAddresssSize);
		
		// Print out all Addresses
		log("Enumeration of all objects: ");
		for (AddressDO address : allAddresss) {
			log(address);
		}

	}
	
	public void testGetAddressById() {
		AddressDO address = AddressDAO.getAddressById(1L);
		log(address);
	}
	
	private void createNewAddressWithIdOne() {
		// Create
		AddressDO address = new AddressDO();
		
		// Set attributes of DO
		address.setId(1L);
		address.setStreet("My Street");
		address.setCity("San Jose");
		address.setState("California");
		address.setZipCode("95131-3439");
		address.setCountry("United States");
		address.setLatitude(10.0);
		address.setLongitude(55.0);
		address.setRecordTs(22L);

		// Set operation to "CREATE" -- i.e. insert new row into database
		address.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		AddressDAO addressDao = new AddressDAO(address);
		addressDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created object: " + address.toString(); 
		log(resultLog);
	}


	public static AddressDO createNewAddressWithRandomId() {
		// Create
		AddressDO address = new AddressDO();
		
		// Set attributes of DO
		address.setId(getRandomNumberGenerator().nextLong());
		address.setStreet("My Street");
		address.setCity("San Jose");
		address.setState("California");
		address.setZipCode("95131-3439");
		address.setCountry("United States");
		address.setLatitude(10.0);
		address.setLongitude(55.0);
		address.setRecordTs(22L);

		// Set operation to "CREATE" -- i.e. insert new row into database
		address.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		AddressDAO addressDao = new AddressDAO(address);
		addressDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created Address object: " + address.toString(); 
		log(resultLog);
		
		return address;
	}

	public void testUpdateAddress() {
		// Create/insert object before retrieval
		createNewAddressWithIdOne();
		
		AddressDO address = new AddressDO();
		address.setOperation(AbstractDO.UPDATE);
		address.setId(1L);

		// Output/log object before update
		String beforeMsg = "BEFORE update";
		log(beforeMsg);
		log(address);

		// Update attributes
		address.setStreet("My Street " + getRandomNumberGenerator().nextGaussian());
		address.setCity("San Jose " + getRandomNumberGenerator().nextGaussian());
		address.setState("California " + getRandomNumberGenerator().nextGaussian());
		address.setZipCode("95131-3439 " + getRandomNumberGenerator().nextGaussian());
		address.setCountry("United States " + getRandomNumberGenerator().nextGaussian());
		address.setLatitude(10.0 + getRandomNumberGenerator().nextGaussian());
		address.setLongitude(55.0 + getRandomNumberGenerator().nextGaussian());
		address.setRecordTs(22L + getRandomNumberGenerator().nextLong());

		AddressDAO entityDAO = null;

		try {
			entityDAO = new AddressDAO(address);

			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    address = entityDAO.getAddressDO();

		// Output/log object AFTER update
		String afterMsg = "AFTER update";
		log(afterMsg);
		log(address);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestAddressDAO test = new TestAddressDAO();

		test.testInsertAddress();
		test.testGetAllAddresss();
		test.testGetAddressById();
		test.testUpdateAddress();
		
	}

}

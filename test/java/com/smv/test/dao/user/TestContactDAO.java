/**
 * 
 */
package com.smv.test.dao.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.ContactDAO;
import com.smv.service.user.db.dbobject.AddressDO;
import com.smv.service.user.db.dbobject.ContactDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */
public class TestContactDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestContactDAO.class);

	public TestContactDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertContact() {
		ContactDO contact = new ContactDO();
		
		// Set attributes of DO
		contact.setFirstName("My first name");
		contact.setLastName("My last name");
		contact.setAdditionalEmail("me@gmail.com");
		contact.setResidentAddressId(1L);
		contact.setShippingAddressId(1L);
		contact.setBillingAddressId(1L);
		contact.setWorkPhone("6503901716");
		contact.setMobilePhone("408-215-8179");
		contact.setHomePhone("(408) 646-0123");
		contact.setAim("me@aol.com");
		contact.setYim("me@yahoo.com");
		contact.setSkype("me@skype.com");
		contact.setFacebook("me@facebook.com");
		contact.setTwitter("me@twitter.com");

		// Set operation to "CREATE" -- i.e. insert new row into database
		contact.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		ContactDAO ContactDao = new ContactDAO(contact);
		ContactDao.save(true, false);

		// Output/log results
		String resultLog = "contact: " + contact.toString(); 
		log(resultLog);
	}

	public void testGetAllContacts() {
		List<ContactDO> allContacts = ContactDAO.getAllContacts();

		// Get and print out size of all Contacts
		String allContactsSize = "size: " + allContacts.size();
		log(allContactsSize);
		
		// Print out all Contacts
		log("Enumeration of all objects: ");
		for (ContactDO contact : allContacts) {
			log(contact);
		}

	}
	
	public void testGetContactById() {
		ContactDO contact = ContactDAO.getContactById(1L);
		log(contact);
	}
	
	private void createNewContactWithIdOne() {
		// Create
		ContactDO contact = new ContactDO();
		
		// Set attributes of DO
		contact.setId(1L);
		contact.setFirstName("My first name");
		contact.setLastName("My last name");
		contact.setAdditionalEmail("me@gmail.com");
		contact.setResidentAddressId(1L);
		contact.setShippingAddressId(1L);
		contact.setBillingAddressId(1L);
		contact.setWorkPhone("6503901716");
		contact.setMobilePhone("408-215-8179");
		contact.setHomePhone("(408) 646-0123");
		contact.setAim("me@aol.com");
		contact.setYim("me@yahoo.com");
		contact.setSkype("me@skype.com");
		contact.setFacebook("me@facebook.com");
		contact.setTwitter("me@twitter.com");

		// Set operation to "CREATE" -- i.e. insert new row into database
		contact.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		ContactDAO contactDao = new ContactDAO(contact);
		contactDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created object: " + contact.toString(); 
		log(resultLog);
	}

	public static ContactDO createNewContactWithRandomId() {
		// Create
		ContactDO contact = new ContactDO();
		
		
		// Create related objects for DO
		AddressDO residentAddress = TestAddressDAO.createNewAddressWithRandomId();
		AddressDO shippingAddress = TestAddressDAO.createNewAddressWithRandomId();
		AddressDO billingAddress = TestAddressDAO.createNewAddressWithRandomId();
		
		// Set attributes of DO
		contact.setId(getRandomNumberGenerator().nextLong());
		contact.setFirstName("My first name");
		contact.setLastName("My last name");
		contact.setAdditionalEmail("me@gmail.com");
		contact.setResidentAddressId(residentAddress.getId());
		contact.setShippingAddressId(shippingAddress.getId());
		contact.setBillingAddressId(billingAddress.getId());
		contact.setWorkPhone("6503901716");
		contact.setMobilePhone("408-215-8179");
		contact.setHomePhone("(408) 646-0123");
		contact.setAim("me@aol.com");
		contact.setYim("me@yahoo.com");
		contact.setSkype("me@skype.com");
		contact.setFacebook("me@facebook.com");
		contact.setTwitter("me@twitter.com");

		// Set operation to "CREATE" -- i.e. insert new row into database
		contact.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		ContactDAO contactDao = new ContactDAO(contact);
		contactDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created Contact object: " + contact.toString(); 
		log(resultLog);
		
		return contact;
	}

	public void testUpdateContact() {
		// Create/insert object before retrieval
		createNewContactWithIdOne();
		
		ContactDO contact = new ContactDO();
		contact.setOperation(AbstractDO.UPDATE);
		contact.setId(1L);

		// Output/log object before update
		String beforeMsg = "BEFORE update";
		log(beforeMsg);
		log(contact);

		// Update attributes
		contact.setFirstName("My first name " + getRandomNumberGenerator().nextGaussian());
		contact.setLastName("My last name " + getRandomNumberGenerator().nextGaussian());
		contact.setAdditionalEmail("me@gmail.com " + getRandomNumberGenerator().nextGaussian());
		contact.setWorkPhone("6503901716 " + getRandomNumberGenerator().nextGaussian());
		contact.setMobilePhone("408-215-8179 " + getRandomNumberGenerator().nextGaussian());
		contact.setHomePhone("(408) 646-0123 " + getRandomNumberGenerator().nextGaussian());
		contact.setAim("me@aol.com " + getRandomNumberGenerator().nextGaussian());
		contact.setYim("me@yahoo.com " + getRandomNumberGenerator().nextGaussian());
		contact.setSkype("me@skype.com " + getRandomNumberGenerator().nextGaussian());
		contact.setFacebook("me@facebook.com " + getRandomNumberGenerator().nextGaussian());
		contact.setTwitter("me@twitter.com " + getRandomNumberGenerator().nextGaussian());

		ContactDAO entityDAO = null;

		try {
			entityDAO = new ContactDAO(contact);

			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    contact = entityDAO.getContactDO();

		// Output/log object AFTER update
		String afterMsg = "AFTER update";
		log(afterMsg);
		log(contact);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestContactDAO test = new TestContactDAO();

		test.testInsertContact();
		test.testGetAllContacts();
		test.testGetContactById();
		test.testUpdateContact();
		
	}

}

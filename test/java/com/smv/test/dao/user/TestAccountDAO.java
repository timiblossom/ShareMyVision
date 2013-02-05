/**
 * 
 */
package com.smv.test.dao.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.AccountDAO;
import com.smv.service.user.db.dbobject.AccountDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */

public class TestAccountDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestAccountDAO.class);

	public TestAccountDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertAccount() {
		AccountDO account = new AccountDO();
		
		// Set attributes of DO
		account.setName("My name");
		account.setDescription("My description");
		account.setType("My type");
		account.setStatus("My status");

		// Set operation to "CREATE" -- i.e. insert new row into database
		account.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		AccountDAO AccountDao = new AccountDAO(account);
		AccountDao.save(true, false);

		// Output/log results
		String resultLog = "account: " + account.toString(); 
		log(resultLog);
	}

	public void testGetAllAccounts() {
		List<AccountDO> allAccounts = AccountDAO.getAllAccounts();

		// Get and print out size of all Accounts
		String allAccountsSize = "size: " + allAccounts.size();
		log(allAccountsSize);
		
		// Print out all Accounts
		log("Enumeration of all objects: ");
		for (AccountDO account : allAccounts) {
			log(account);
		}

	}
	
	public void testGetAccountById() {
		AccountDO account = AccountDAO.getAccountById(1L);
		log(account);
	}
	
	public void createNewAccountWithIdOne() {
		// Create
		AccountDO account = new AccountDO();
		
		// Set attributes of DO
		account.setId(1L);
		account.setName("My name");
		account.setDescription("My description");
		account.setType("My type");
		account.setStatus("My status");

		// Set operation to "CREATE" -- i.e. insert new row into database
		account.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		AccountDAO accountDao = new AccountDAO(account);
		accountDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created object: " + account.toString(); 
		log(resultLog);
	}

	public static AccountDO createNewAccountWithRandomId() {
		// Create
		AccountDO account = new AccountDO();
		
		// Set attributes of DO
		account.setId(getRandomNumberGenerator().nextLong());
		account.setName("My name");
		account.setDescription("My description");
		account.setType("My type");
		account.setStatus("My status");

		// Set operation to "CREATE" -- i.e. insert new row into database
		account.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		AccountDAO accountDao = new AccountDAO(account);
		accountDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created Account object: " + account.toString(); 
		log(resultLog);
		
		return account;
	}

	public void testUpdateAccount() {
		// Create/insert object before retrieval
		createNewAccountWithIdOne();
		
		AccountDO account = new AccountDO();
		account.setOperation(AbstractDO.UPDATE);
		account.setId(1L);

		// Output/log object before update
		String beforeMsg = "BEFORE update";
		log(beforeMsg);
		log(account);

		// Update attributes
		account.setName("New name" + getRandomNumberGenerator().nextGaussian());
		account.setDescription("New description" + getRandomNumberGenerator().nextGaussian());
		account.setType("New type" + getRandomNumberGenerator().nextGaussian());
		account.setStatus("New status" + getRandomNumberGenerator().nextGaussian());

		AccountDAO entityDAO = null;

		try {
			entityDAO = new AccountDAO(account);
			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    account = entityDAO.getAccountDO();

		// Output/log object AFTER update
		String afterMsg = "AFTER update";
		log(afterMsg);
		log(account);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestAccountDAO test = new TestAccountDAO();

		test.testInsertAccount();
		test.testGetAllAccounts();
		test.testGetAccountById();
		test.testUpdateAccount();
	}

}

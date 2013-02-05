/**
 * 
 */
package com.smv.test.dao.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.AccountExtraInfoDAO;
import com.smv.service.user.db.dbobject.AccountExtraInfoDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */
public class TestAccountExtraInfoDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestAccountExtraInfoDAO.class);

	public TestAccountExtraInfoDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertAccountExtraInfo() {
		AccountExtraInfoDO accountExtraInfo = new AccountExtraInfoDO();
		
		// Set attributes of DO
		accountExtraInfo.setKeyPair("My key");
		accountExtraInfo.setValuePair("My value");

		// Set operation to "CREATE" -- i.e. insert new row into database
		accountExtraInfo.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		AccountExtraInfoDAO AccountExtraInfoDao = new AccountExtraInfoDAO(accountExtraInfo);
		AccountExtraInfoDao.save(true, false);

		// Output/log results
		String resultLog = "accountExtraInfo: " + accountExtraInfo.toString(); 
		log(resultLog);
	}

	public void testGetAllAccountExtraInfos() {
		List<AccountExtraInfoDO> allAccountExtraInfos = AccountExtraInfoDAO.getAllAccountExtraInfos();

		// Get and print out size of all AccountExtraInfos
		String allAccountExtraInfosSize = "size: " + allAccountExtraInfos.size();
		log(allAccountExtraInfosSize);
		
		// Print out all AccountExtraInfos
		log("Enumeration of all objects: ");
		for (AccountExtraInfoDO accountExtraInfo : allAccountExtraInfos) {
			log(accountExtraInfo);
		}

	}
	
	public void testGetAccountExtraInfoById() {
		AccountExtraInfoDO accountExtraInfo = AccountExtraInfoDAO.getAccountExtraInfoById(1L);
		log(accountExtraInfo);
	}
	
	private void createNewAccountExtraInfoWithIdOne() {
		// Create
		AccountExtraInfoDO accountExtraInfo = new AccountExtraInfoDO();
		
		// Set attributes of DO
		accountExtraInfo.setId(1L);
		accountExtraInfo.setKeyPair("My new key");
		accountExtraInfo.setValuePair("My new value");

		// Set operation to "CREATE" -- i.e. insert new row into database
		accountExtraInfo.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		AccountExtraInfoDAO accountExtraInfoDao = new AccountExtraInfoDAO(accountExtraInfo);
		accountExtraInfoDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created object: " + accountExtraInfo.toString(); 
		log(resultLog);
	}

	public void testUpdateAccountExtraInfo() {
		// Create/insert object before retrieval
		createNewAccountExtraInfoWithIdOne();
		
		AccountExtraInfoDO accountExtraInfo = new AccountExtraInfoDO();
		accountExtraInfo.setOperation(AbstractDO.UPDATE);
		accountExtraInfo.setId(1L);

		// Output/log object before update
		String beforeMsg = "BEFORE update";
		log(beforeMsg);
		log(accountExtraInfo);

		// Update attributes
		accountExtraInfo.setKeyPair("My key" + getRandomNumberGenerator().nextGaussian());
		accountExtraInfo.setValuePair("My value" + getRandomNumberGenerator().nextGaussian());

		AccountExtraInfoDAO entityDAO = null;

		try {
			entityDAO = new AccountExtraInfoDAO(accountExtraInfo);

			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    accountExtraInfo = entityDAO.getAccountExtraInfoDO();

		// Output/log object AFTER update
		String afterMsg = "AFTER update";
		log(afterMsg);
		log(accountExtraInfo);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestAccountExtraInfoDAO test = new TestAccountExtraInfoDAO();

		test.testInsertAccountExtraInfo();
		test.testGetAllAccountExtraInfos();
		test.testGetAccountExtraInfoById();
		test.testUpdateAccountExtraInfo();
		
	}

}

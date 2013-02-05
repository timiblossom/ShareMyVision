/**
 * 
 */
package com.smv.test.dao.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.CcInfoDAO;
import com.smv.service.user.db.dbobject.CcInfoDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */
public class TestCcInfoDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestCcInfoDAO.class);

	public TestCcInfoDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertCcInfo() {
		CcInfoDO ccInfo = new CcInfoDO();
		
		// Set attributes of DO
		ccInfo.setLastFour("1234");
		ccInfo.setExpireMmyy("0312");
		ccInfo.setType("Visa");
		ccInfo.setStatus("Active");
		ccInfo.setAddressId(1L);

		// Set operation to "CREATE" -- i.e. insert new row into database
		ccInfo.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		CcInfoDAO CcInfoDao = new CcInfoDAO(ccInfo);
		CcInfoDao.save(true, false);

		// Output/log results
		String resultLog = "ccInfo: " + ccInfo.toString(); 
		log(resultLog);
	}

	public void testGetAllCcInfos() {
		List<CcInfoDO> allCcInfos = CcInfoDAO.getAllCcInfos();

		// Get and print out size of all CcInfos
		String allCcInfosSize = "size: " + allCcInfos.size();
		log(allCcInfosSize);
		
		// Print out all CcInfos
		log("Enumeration of all objects: ");
		for (CcInfoDO ccInfo : allCcInfos) {
			log(ccInfo);
		}

	}
	
	public void testGetCcInfoById() {
		CcInfoDO ccInfo = CcInfoDAO.getCcInfoById(1L);
		log(ccInfo);
	}
	
	private void createNewCcInfoWithIdOne() {
		// Create
		CcInfoDO ccInfo = new CcInfoDO();
		
		// Set attributes of DO
		ccInfo.setId(1L);
		ccInfo.setLastFour("1234");
		ccInfo.setExpireMmyy("0312");
		ccInfo.setType("Visa");
		ccInfo.setStatus("Active");
		ccInfo.setAddressId(1L);

		// Set operation to "CREATE" -- i.e. insert new row into database
		ccInfo.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		CcInfoDAO ccInfoDao = new CcInfoDAO(ccInfo);
		ccInfoDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created object: " + ccInfo.toString(); 
		log(resultLog);
	}

	public void testUpdateCcInfo() {
		// Create/insert object before retrieval
		createNewCcInfoWithIdOne();
		
		CcInfoDO ccInfo = new CcInfoDO();
		ccInfo.setOperation(AbstractDO.UPDATE);
		ccInfo.setId(1L);

		// Output/log object before update
		String beforeMsg = "BEFORE update";
		log(beforeMsg);
		log(ccInfo);

		// Update attributes
		ccInfo.setLastFour("1234 " + getRandomNumberGenerator().nextGaussian());
		ccInfo.setExpireMmyy("0312 " + getRandomNumberGenerator().nextGaussian());
		ccInfo.setType("Visa " + getRandomNumberGenerator().nextGaussian());
		ccInfo.setStatus("Active " + getRandomNumberGenerator().nextGaussian());
		ccInfo.setAddressId(1L + getRandomNumberGenerator().nextLong());

		CcInfoDAO entityDAO = null;

		try {
			entityDAO = new CcInfoDAO(ccInfo);

			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    ccInfo = entityDAO.getCcInfoDO();

		// Output/log object AFTER update
		String afterMsg = "AFTER update";
		log(afterMsg);
		log(ccInfo);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestCcInfoDAO test = new TestCcInfoDAO();

		test.testInsertCcInfo();
		test.testGetAllCcInfos();
		test.testGetCcInfoById();
		test.testUpdateCcInfo();
		
	}

}

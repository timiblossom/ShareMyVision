/**
 * 
 */
package com.smv.test.dao.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.UserExtraInfoDAO;
import com.smv.service.user.db.dbobject.TimeZoneDO;
import com.smv.service.user.db.dbobject.UserExtraInfoDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */
public class TestUserExtraInfoDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestUserExtraInfoDAO.class);

	public TestUserExtraInfoDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertUserExtraInfo() {
		UserExtraInfoDO userExtraInfo = new UserExtraInfoDO();
		
		// Create related objects for DO
		TimeZoneDO timezone = TestTimeZoneDAO.createNewTimeZoneWithRandomId();
		
		// Set attributes of DO
		userExtraInfo.setIndustry("My industry");
		userExtraInfo.setCompany("My company");
		userExtraInfo.setCompanySize("My companySize");
		userExtraInfo.setZipCode("My zipCode");
		userExtraInfo.setTimeZoneId(timezone.getId());
		userExtraInfo.setTitle("My title");
		userExtraInfo.setJobTitle("My jobTitle");
		userExtraInfo.setMobileDeviceModel("My mobileDeviceModel");
		userExtraInfo.setMobileManufacturer("My mobileManufacturer");

		// Set operation to "CREATE" -- i.e. insert new row into database
		userExtraInfo.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		UserExtraInfoDAO UserExtraInfoDao = new UserExtraInfoDAO(userExtraInfo);
		UserExtraInfoDao.save(true, false);

		// Output/log results
		String resultLog = "userExtraInfo: " + userExtraInfo.toString(); 
		log(resultLog);
	}

	public void testGetAllUserExtraInfos() {
		List<UserExtraInfoDO> allUserExtraInfos = UserExtraInfoDAO.getAllUserExtraInfos();

		// Get and print out size of all UserExtraInfos
		String allUserExtraInfosSize = "size: " + allUserExtraInfos.size();
		log(allUserExtraInfosSize);
		
		// Print out all UserExtraInfos
		log("Enumeration of all objects: ");
		for (UserExtraInfoDO userExtraInfo : allUserExtraInfos) {
			log(userExtraInfo);
		}

	}
	
	public void testGetUserExtraInfoById() {
		UserExtraInfoDO userExtraInfo = UserExtraInfoDAO.getUserExtraInfoById(1L);
		log(userExtraInfo);
	}
	
	private void createNewUserExtraInfoWithIdOne() {
		// Create
		UserExtraInfoDO userExtraInfo = new UserExtraInfoDO();
		
		// Create related objects for DO
		TimeZoneDO timezone = TestTimeZoneDAO.createNewTimeZoneWithRandomId();
		
		// Set attributes of DO
		userExtraInfo.setId(1L);
		userExtraInfo.setIndustry("My industry");
		userExtraInfo.setCompany("My company");
		userExtraInfo.setCompanySize("My companySize");
		userExtraInfo.setZipCode("My zipCode");
		userExtraInfo.setTimeZoneId(timezone.getId());
		userExtraInfo.setTitle("My title");
		userExtraInfo.setJobTitle("My jobTitle");
		userExtraInfo.setMobileDeviceModel("My mobileDeviceModel");
		userExtraInfo.setMobileManufacturer("My mobileManufacturer");

		// Set operation to "CREATE" -- i.e. insert new row into database
		userExtraInfo.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		UserExtraInfoDAO userExtraInfoDao = new UserExtraInfoDAO(userExtraInfo);
		userExtraInfoDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created object: " + userExtraInfo.toString(); 
		log(resultLog);
	}

	public static UserExtraInfoDO createNewUserExtraInfoWithRandomId() {
		// Create
		UserExtraInfoDO userExtraInfo = new UserExtraInfoDO();
		
		// Create related objects for DO
		TimeZoneDO timezone = TestTimeZoneDAO.createNewTimeZoneWithRandomId();
		
		// Set attributes of DO
		userExtraInfo.setId(getRandomNumberGenerator().nextLong());
		userExtraInfo.setIndustry("My industry");
		userExtraInfo.setCompany("My company");
		userExtraInfo.setCompanySize("My companySize");
		userExtraInfo.setZipCode("My zipCode");
		userExtraInfo.setTimeZoneId(timezone.getId());
		userExtraInfo.setTitle("My title");
		userExtraInfo.setJobTitle("My jobTitle");
		userExtraInfo.setMobileDeviceModel("My mobileDeviceModel");
		userExtraInfo.setMobileManufacturer("My mobileManufacturer");

		// Set operation to "CREATE" -- i.e. insert new row into database
		userExtraInfo.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		UserExtraInfoDAO userExtraInfoDao = new UserExtraInfoDAO(userExtraInfo);
		userExtraInfoDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created User Extra Info object: " + userExtraInfo.toString(); 
		log(resultLog);
		
		return userExtraInfo;
	}

	public void testUpdateUserExtraInfo() {
		// Create/insert object before retrieval
		createNewUserExtraInfoWithIdOne();
		
		UserExtraInfoDO userExtraInfo = new UserExtraInfoDO();
		userExtraInfo.setOperation(AbstractDO.UPDATE);
		userExtraInfo.setId(1L);

		// Output/log object before update
		String beforeMsg = "BEFORE update";
		log(beforeMsg);
		log(userExtraInfo);

		// Create related objects for DO
		TimeZoneDO timezone = TestTimeZoneDAO.createNewTimeZoneWithRandomId();
		
		// Update attributes
		userExtraInfo.setIndustry("My industry " + getRandomNumberGenerator().nextGaussian());
		userExtraInfo.setCompany("My company " + getRandomNumberGenerator().nextGaussian());
		userExtraInfo.setCompanySize("My companySize " + getRandomNumberGenerator().nextGaussian());
		userExtraInfo.setZipCode("My zipCode " + getRandomNumberGenerator().nextGaussian());
		userExtraInfo.setTimeZoneId(timezone.getId());
		userExtraInfo.setTitle("My title " + getRandomNumberGenerator().nextGaussian());
		userExtraInfo.setJobTitle("My jobTitle " + getRandomNumberGenerator().nextGaussian());
		userExtraInfo.setMobileDeviceModel("My mobileDeviceModel " + getRandomNumberGenerator().nextGaussian());
		userExtraInfo.setMobileManufacturer("My mobileManufacturer " + getRandomNumberGenerator().nextGaussian());

		UserExtraInfoDAO entityDAO = null;

		try {
			entityDAO = new UserExtraInfoDAO(userExtraInfo);
			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    userExtraInfo = entityDAO.getUserExtraInfoDO();

		// Output/log object AFTER update
		String afterMsg = "AFTER update";
		log(afterMsg);
		log(userExtraInfo);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestUserExtraInfoDAO test = new TestUserExtraInfoDAO();

		test.testInsertUserExtraInfo();
		test.testGetAllUserExtraInfos();
		test.testGetUserExtraInfoById();
		test.testUpdateUserExtraInfo();
	}

}

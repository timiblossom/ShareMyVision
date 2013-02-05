/**
 * 
 */
package com.smv.test.dao.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.TimeZoneDAO;
import com.smv.service.user.db.dbobject.TimeZoneDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */
public class TestTimeZoneDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestTimeZoneDAO.class);

	public TestTimeZoneDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertTimeZone() {
		TimeZoneDO timeZone = new TimeZoneDO();
		
		// Set attributes of DO
		timeZone.setTimeZone("My time zone");
		timeZone.setLabel("My label");

		// Set operation to "CREATE" -- i.e. insert new row into database
		timeZone.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		TimeZoneDAO TimeZoneDao = new TimeZoneDAO(timeZone);
		TimeZoneDao.save(true, false);

		// Output/log results
		String resultLog = "timeZone: " + timeZone.toString(); 
		log(resultLog);
	}

	public void testGetAllTimeZones() {
		List<TimeZoneDO> allTimeZones = TimeZoneDAO.getAllTimeZones();

		// Get and print out size of all TimeZones
		String allTimeZonesSize = "size: " + allTimeZones.size();
		log(allTimeZonesSize);
		
		// Print out all TimeZones
		log("Enumeration of all objects: ");
		for (TimeZoneDO timeZone : allTimeZones) {
			log(timeZone);
		}

	}
	
	public void testGetTimeZoneById() {
		TimeZoneDO timeZone = TimeZoneDAO.getTimeZoneById(1L);
		log(timeZone);
	}
	
	public static TimeZoneDO createNewTimeZoneWithRandomId() {
		// Create
		TimeZoneDO timeZone = new TimeZoneDO();
		
		// Set attributes of DO
		timeZone.setId(getRandomNumberGenerator().nextLong());

		// Set operation to "CREATE" -- i.e. insert new row into database
		timeZone.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		TimeZoneDAO timeZoneDao = new TimeZoneDAO(timeZone);
		timeZoneDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created Time Zone object: " + timeZone.toString(); 
		log(resultLog);
		
		return timeZone;
	}

	private void createNewTimeZoneWithIdOne() {
		// Create
		TimeZoneDO timeZone = new TimeZoneDO();
		
		// Set attributes of DO
		timeZone.setId(1L);
		timeZone.setTimeZone("My time zone");
		timeZone.setLabel("My label");

		// Set operation to "CREATE" -- i.e. insert new row into database
		timeZone.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		TimeZoneDAO timeZoneDao = new TimeZoneDAO(timeZone);
		timeZoneDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created object: " + timeZone.toString(); 
		log(resultLog);
	}

	public void testUpdateTimeZone() {
		// Create/insert object before retrieval
		createNewTimeZoneWithIdOne();
		
		TimeZoneDO timeZone = new TimeZoneDO();
		timeZone.setOperation(AbstractDO.UPDATE);
		timeZone.setId(1L);

		// Output/log object before update
		String beforeMsg = "BEFORE update";
		log(beforeMsg);
		log(timeZone);

		// Update attributes
		timeZone.setTimeZone("My time zone " + getRandomNumberGenerator().nextGaussian());
		timeZone.setLabel("My label " + getRandomNumberGenerator().nextGaussian());

		TimeZoneDAO entityDAO = null;

		try {
			entityDAO = new TimeZoneDAO(timeZone);
			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    timeZone = entityDAO.getTimeZoneDO();

		// Output/log object AFTER update
		String afterMsg = "AFTER update";
		log(afterMsg);
		log(timeZone);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestTimeZoneDAO test = new TestTimeZoneDAO();

		test.testInsertTimeZone();
		test.testGetAllTimeZones();
		test.testGetTimeZoneById();
		test.testUpdateTimeZone();
	}

}

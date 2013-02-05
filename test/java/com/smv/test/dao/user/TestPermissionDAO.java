/**
 * 
 */
package com.smv.test.dao.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.PermissionDAO;
import com.smv.service.user.db.dbobject.PermissionDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */
public class TestPermissionDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestPermissionDAO.class);

	public TestPermissionDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertPermission() {
		PermissionDO permission = new PermissionDO();
		
		// Set attributes of DO
		permission.setName("My name");
		permission.setDescription("My description");

		// Set operation to "CREATE" -- i.e. insert new row into database
		permission.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		PermissionDAO PermissionDao = new PermissionDAO(permission);
		PermissionDao.save(true, false);

		// Output/log results
		String resultLog = "permission: " + permission.toString(); 
		log(resultLog);
	}

	public void testGetAllPermissions() {
		List<PermissionDO> allPermissions = PermissionDAO.getAllPermissions();

		// Get and print out size of all Permissions
		String allPermissionsSize = "size: " + allPermissions.size();
		log(allPermissionsSize);
		
		// Print out all Permissions
		log("Enumeration of all objects: ");
		for (PermissionDO permission : allPermissions) {
			log(permission);
		}

	}
	
	public void testGetPermissionById() {
		PermissionDO permission = PermissionDAO.getPermissionById(1L);
		log(permission);
	}
	
	private void createNewPermissionWithIdOne() {
		// Create
		PermissionDO permission = new PermissionDO();
		
		// Set attributes of DO
		permission.setId(1L);
		permission.setName("My name");
		permission.setDescription("My description");

		// Set operation to "CREATE" -- i.e. insert new row into database
		permission.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		PermissionDAO permissionDao = new PermissionDAO(permission);
		permissionDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created object: " + permission.toString(); 
		log(resultLog);
	}

	public static PermissionDO createNewPermissionWithRandomId() {
		// Create
		PermissionDO permission = new PermissionDO();
		
		// Set attributes of DO
		permission.setId(getRandomNumberGenerator().nextLong());
		permission.setName("My Permission name");
		permission.setDescription("My Permission description");

		// Set operation to "CREATE" -- i.e. insert new row into database
		permission.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		PermissionDAO permissionDao = new PermissionDAO(permission);
		permissionDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created Permission object: " + permission.toString(); 
		log(resultLog);
		
		return permission;
	}

	public void testUpdatePermission() {
		// Create/insert object before retrieval
		createNewPermissionWithIdOne();
		
		PermissionDO permission = new PermissionDO();
		permission.setOperation(AbstractDO.UPDATE);
		permission.setId(1L);

		// Output/log object before update
		String beforeMsg = "BEFORE update";
		log(beforeMsg);
		log(permission);

		// Update attributes
		permission.setName("My name " + getRandomNumberGenerator().nextGaussian());
		permission.setDescription("My description " + getRandomNumberGenerator().nextGaussian());

		PermissionDAO entityDAO = null;

		try {
			entityDAO = new PermissionDAO(permission);
			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    permission = entityDAO.getPermissionDO();

		// Output/log object AFTER update
		String afterMsg = "AFTER update";
		log(afterMsg);
		log(permission);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestPermissionDAO test = new TestPermissionDAO();

		test.testInsertPermission();
		test.testGetAllPermissions();
		test.testGetPermissionById();
		test.testUpdatePermission();
	}

}

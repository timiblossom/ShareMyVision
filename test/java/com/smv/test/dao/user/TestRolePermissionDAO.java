/**
 * 
 */
package com.smv.test.dao.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.RolePermissionDAO;
import com.smv.service.user.db.dbobject.PermissionDO;
import com.smv.service.user.db.dbobject.RoleDO;
import com.smv.service.user.db.dbobject.RolePermissionDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */
public class TestRolePermissionDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestRolePermissionDAO.class);

	public TestRolePermissionDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertRolePermission() {
		RolePermissionDO rolePermission = new RolePermissionDO();

		// Create related objects for DO
		PermissionDO permission = TestPermissionDAO.createNewPermissionWithRandomId();
		RoleDO role = TestRoleDAO.createNewRoleWithRandomId();
		
		// Set attributes of DO
		rolePermission.setRoleId(role.getId());
		rolePermission.setPermissionId(permission.getId());
		
		// Set operation to "CREATE" -- i.e. insert new row into database
		rolePermission.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		RolePermissionDAO rolePermissionDao = new RolePermissionDAO(rolePermission);
		rolePermissionDao.save(true, false);

		// Output/log results
		String resultLog = "rolePermission: " + rolePermission.toString(); 
		log(resultLog);
	}

	public void testGetAllRolePermissions() {
		List<RolePermissionDO> allRolePermissions = RolePermissionDAO.getAllRolePermissions();

		// Get and print out size of all Roles
		String allRolePermissionsSize = "size: " + allRolePermissions.size();
		log(allRolePermissionsSize);
		
		// Print out all Roles
		log("Enumeration of all objects: ");
		for (RolePermissionDO rolePermission : allRolePermissions) {
			log(rolePermission);
		}

	}
	
	public void testGetRolePermissionById() {
		RolePermissionDO role = RolePermissionDAO.getRolePermissionById(1L);
		log(role);
	}
	
	private void createNewRolePermissionWithIdOne() {
		// Create
		RolePermissionDO rolePermission = new RolePermissionDO();
		
		// Create related objects for DO
		PermissionDO permission = TestPermissionDAO.createNewPermissionWithRandomId();
		RoleDO role = TestRoleDAO.createNewRoleWithRandomId();
		
		// Set attributes of DO
		rolePermission.setId(1L);
		rolePermission.setRoleId(role.getId());
		rolePermission.setPermissionId(permission.getId());
		
		// Set attributes of DO


		// Set operation to "CREATE" -- i.e. insert new row into database
		rolePermission.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		RolePermissionDAO rolePermissionDao = new RolePermissionDAO(rolePermission);
		rolePermissionDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created Role Permission object: " + rolePermission.toString(); 
		log(resultLog);
	}

	public void testUpdateRolePermission() {
		// Create/insert object before retrieval
		createNewRolePermissionWithIdOne();
		
		RolePermissionDO rolePermission = new RolePermissionDO();
		rolePermission.setOperation(AbstractDO.UPDATE);
		rolePermission.setId(1L);

		// Output/log object before update
		String beforeMsg = "BEFORE update";
		log(beforeMsg);
		log(rolePermission);

		// Create related objects for DO
		PermissionDO permission = TestPermissionDAO.createNewPermissionWithRandomId();
		RoleDO role = TestRoleDAO.createNewRoleWithRandomId();
		
		// Update attributes
		rolePermission.setRoleId(role.getId());
		rolePermission.setPermissionId(permission.getId());
		
		RolePermissionDAO entityDAO = null;

		try {
			entityDAO = new RolePermissionDAO(rolePermission);
			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		rolePermission = entityDAO.getRolePermissionDO();

		// Output/log object AFTER update
		String afterMsg = "AFTER update";
		log(afterMsg);
		log(rolePermission);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestRolePermissionDAO test = new TestRolePermissionDAO();

		test.testInsertRolePermission();
		test.testGetAllRolePermissions();
		test.testGetRolePermissionById();
		test.testUpdateRolePermission();
	}

}

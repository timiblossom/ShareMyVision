/**
 * 
 */
package com.smv.test.dao.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.RoleDAO;
import com.smv.service.user.db.dbobject.RoleDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */
public class TestRoleDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestRoleDAO.class);

	public TestRoleDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertRole() {
		RoleDO role = new RoleDO();
		
		// Set attributes of DO
		role.setName("My name");
		role.setDescription("My description");

		// Set operation to "CREATE" -- i.e. insert new row into database
		role.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		RoleDAO RoleDao = new RoleDAO(role);
		RoleDao.save(true, false);

		// Output/log results
		String resultLog = "role: " + role.toString(); 
		log(resultLog);
	}

	public void testGetAllRoles() {
		List<RoleDO> allRoles = RoleDAO.getAllRoles();

		// Get and print out size of all Roles
		String allRolesSize = "size: " + allRoles.size();
		log(allRolesSize);
		
		// Print out all Roles
		log("Enumeration of all objects: ");
		for (RoleDO role : allRoles) {
			log(role);
		}

	}
	
	public void testGetRoleById() {
		RoleDO role = RoleDAO.getRoleById(1L);
		log(role);
	}
	
	private void createNewRoleWithIdOne() {
		// Create
		RoleDO role = new RoleDO();
		
		// Set attributes of DO
		role.setId(1L);
		role.setName("My name");
		role.setDescription("My description");

		// Set operation to "CREATE" -- i.e. insert new row into database
		role.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		RoleDAO roleDao = new RoleDAO(role);
		roleDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created object: " + role.toString(); 
		log(resultLog);
	}

	public static RoleDO createNewRoleWithRandomId() {
		// Create
		RoleDO role = new RoleDO();
		
		// Set attributes of DO
		role.setId(getRandomNumberGenerator().nextLong());
		role.setName("My Role name");
		role.setDescription("My Role description");

		// Set operation to "CREATE" -- i.e. insert new row into database
		role.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		RoleDAO roleDao = new RoleDAO(role);
		roleDao.save(true, false);

		// Output/log results
		String resultLog = "Newly created Role object: " + role.toString(); 
		log(resultLog);
		
		return role;
	}

	public void testUpdateRole() {
		// Create/insert object before retrieval
		createNewRoleWithIdOne();
		
		RoleDO role = new RoleDO();
		role.setOperation(AbstractDO.UPDATE);
		role.setId(1L);

		// Output/log object before update
		String beforeMsg = "BEFORE update";
		log(beforeMsg);
		log(role);

		// Update attributes
		role.setName("My name " + getRandomNumberGenerator().nextGaussian());
		role.setDescription("My description " + getRandomNumberGenerator().nextGaussian());

		RoleDAO entityDAO = null;

		try {
			entityDAO = new RoleDAO(role);
			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    role = entityDAO.getRoleDO();

		// Output/log object AFTER update
		String afterMsg = "AFTER update";
		log(afterMsg);
		log(role);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestRoleDAO test = new TestRoleDAO();

		test.testInsertRole();
		test.testGetAllRoles();
		test.testGetRoleById();
		test.testUpdateRole();
	}

}

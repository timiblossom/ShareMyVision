/**
 * 
 */
package com.smv.test.dao.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.AccountDAO;
import com.smv.service.user.db.dao.UserDAO;
import com.smv.service.user.db.dbobject.AccountDO;
import com.smv.service.user.db.dbobject.ContactDO;
import com.smv.service.user.db.dbobject.RoleDO;
import com.smv.service.user.db.dbobject.UserDO;
import com.smv.service.user.db.dbobject.UserExtraInfoDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */
public class TestUserDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestUserDAO.class);

	public TestUserDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertUser() {
		UserDO user = new UserDO();

		// Create related objects for DO
		AccountDO account = TestAccountDAO.createNewAccountWithRandomId();
		ContactDO contact = TestContactDAO.createNewContactWithRandomId();
		UserExtraInfoDO userExtraInfo = TestUserExtraInfoDAO.createNewUserExtraInfoWithRandomId();
		RoleDO role = TestRoleDAO.createNewRoleWithRandomId();
		
		// Set attributes of DO
		user.setDisplayName("My displayName");
		user.setEmail("My email");
		user.setPassword("My password");
		user.setSalt("My salt");
		user.setStatusId(1L);
		user.setActivationCode("My activationCode");
		user.setPasswordResetCode("My passwordResetCode");
		user.setAccountId(account.getId());
		user.setContactId(contact.getId());
		user.setUserExtraInfoId(userExtraInfo.getId());
		user.setRoleId(role.getId());
		user.setLanguage("My language");
		
		// Set operation to "CREATE" -- i.e. insert new row into database
		user.setOperation(AbstractDO.CREATE);

		// Link DAO-DO
		UserDAO userDao = new UserDAO(user);
		userDao.save(true, false);

		// Output/log results
		String resultLog = "user: " + user.toString(); 
		log(resultLog);
	}

	public void testGetAllUsers() {
		List<UserDO> allUsers = UserDAO.getAllUsers();

		// Get and print out size of all Roles
		String allUsersSize = "size: " + allUsers.size();
		log(allUsersSize);
		
		// Print out all Roles
		log("Enumeration of all objects: ");
		for (UserDO user : allUsers) {
			log(user);
		}

	}
	
	public void testGetUserById() {
		UserDO user = UserDAO.getUserById(1L);
		log(user);
	}
	
	public UserDO createNewUserWithIdOne() {
		return this.createNewUser(1L);
	}

	public UserDO createNewUserWithRandomId() {
		return this.createNewUser(getRandomNumberGenerator().nextInt());
	}

	public UserDO createNewUser(long userId) {
		// Create
		UserDO user = UserDAO.getUserById(userId);
		if (user == null) {
			user = new UserDO();
			
			// Create related objects for DO
			AccountDO account = TestAccountDAO.createNewAccountWithRandomId();
			ContactDO contact = TestContactDAO.createNewContactWithRandomId();
			UserExtraInfoDO userExtraInfo = TestUserExtraInfoDAO.createNewUserExtraInfoWithRandomId();
			RoleDO role = TestRoleDAO.createNewRoleWithRandomId();
			
			// Set attributes of DO
			user.setId(1L);
			user.setDisplayName("My displayName " + getRandomNumberGenerator().nextGaussian());
			user.setEmail("My email " + getRandomNumberGenerator().nextGaussian());
			user.setPassword("My password " + getRandomNumberGenerator().nextGaussian());
			user.setSalt("My salt " + getRandomNumberGenerator().nextGaussian());
			user.setStatusId(1l);
			user.setActivationCode("My activationCode " + getRandomNumberGenerator().nextGaussian());
			user.setPasswordResetCode("My passwordResetCode " + getRandomNumberGenerator().nextGaussian());
			user.setAccountId(account.getId());
			user.setContactId(contact.getId());
			user.setUserExtraInfoId(userExtraInfo.getId());
			user.setRoleId(role.getId());
			user.setLanguage("My language");
			
			// Set operation to "CREATE" -- i.e. insert new row into database
			user.setOperation(AbstractDO.CREATE);

			// Link DAO-DO
			UserDAO userDao = new UserDAO(user);
			userDao.save(true, false);

			// Output/log results
			String resultLog = "Newly created object: " + user.toString(); 
			log(resultLog);
		} else {
			// Output/log results
			String resultLog = "Object already exists: " + user.toString(); 
			log(resultLog);
		}
		
		return user;
	}

	public void testUpdateUser() {
		// Create/insert object before retrieval if it does not exist
		UserDO user = UserDAO.getUserByEmailAndPassword("My email", "My password");
		if (user == null) {
			user = createNewUserWithIdOne();
		}
		
		user.setOperation(AbstractDO.UPDATE);
		user.setId(1L);

		// Output/log object before update
		String beforeMsg = "BEFORE update";
		log(beforeMsg);
		log(user);
		
		// Create related objects for DO
		AccountDO account = TestAccountDAO.createNewAccountWithRandomId();
		ContactDO contact = TestContactDAO.createNewContactWithRandomId();
		UserExtraInfoDO userExtraInfo = TestUserExtraInfoDAO.createNewUserExtraInfoWithRandomId();
		RoleDO role = TestRoleDAO.createNewRoleWithRandomId();
		
		// Update attributes
		user.setDisplayName("My displayName " + getRandomNumberGenerator().nextGaussian());
		user.setEmail("My email " + getRandomNumberGenerator().nextGaussian());
		user.setPassword("My password " + getRandomNumberGenerator().nextGaussian());
		user.setSalt("My salt " + getRandomNumberGenerator().nextGaussian());
		user.setStatusId(1L);
		user.setActivationCode("My activationCode " + getRandomNumberGenerator().nextGaussian());
		user.setPasswordResetCode("My passwordResetCode" + getRandomNumberGenerator().nextGaussian());
		user.setAccountId(account.getId());
		user.setContactId(contact.getId());
		user.setUserExtraInfoId(userExtraInfo.getId());
		user.setRoleId(role.getId());
		user.setLanguage("My language " + getRandomNumberGenerator().nextGaussian());
		
		UserDAO entityDAO = null;

		try {
			entityDAO = new UserDAO(user);
			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		user = entityDAO.getUserDO();

		// Output/log object AFTER update
		String afterMsg = "AFTER update";
		log(afterMsg);
		log(user);
	}

	public void testGetUserByEmailAndPassword() {
		UserDO user = UserDAO.getUserByEmailAndPassword("My email", "My password");
		if (user == null) {
			createNewUserWithIdOne();
		}

		user = UserDAO.getUserByEmailAndPassword("My email", "My password");
		
		log(user);
	}
	
	public void testGetUserByAccountId() {
		AccountDO account = AccountDAO.getAccountById(1L);
		if (account == null) {
			TestAccountDAO testAccountDAO = new TestAccountDAO();
			testAccountDAO.createNewAccountWithIdOne();
			account = AccountDAO.getAccountById(1L);
		}
		
		UserDO user = new UserDO();
		user.setOperation(AbstractDO.CREATE);
		
		// Create related objects for DO
		ContactDO contact = TestContactDAO.createNewContactWithRandomId();
		UserExtraInfoDO userExtraInfo = TestUserExtraInfoDAO.createNewUserExtraInfoWithRandomId();
		RoleDO role = TestRoleDAO.createNewRoleWithRandomId();
		
		// Update attributes
		user.setDisplayName("My displayName " + getRandomNumberGenerator().nextGaussian());
		user.setEmail("My email " + getRandomNumberGenerator().nextGaussian());
		user.setPassword("My password");
		user.setSalt("My salt " + getRandomNumberGenerator().nextGaussian());
		user.setStatusId(1l);
		user.setActivationCode("My activationCode " + getRandomNumberGenerator().nextGaussian());
		user.setPasswordResetCode("My passwordResetCode");
		user.setAccountId(account.getId());
		user.setContactId(contact.getId());
		user.setUserExtraInfoId(userExtraInfo.getId());
		user.setRoleId(role.getId());
		user.setLanguage("My language " + getRandomNumberGenerator().nextGaussian());
		
		// Link DAO-DO
		UserDAO userDao = new UserDAO(user);
		userDao.save(true, false);

		// Output/log results
		String resultLog = "user: " + user.toString(); 
		log(resultLog);

		// Output/log results
		UserDO retrievedUser = UserDAO.getUserByAccountId(account.getId());

		// Output/log results
		String retrievalResultLog = "Retrieved user: " + retrievedUser.toString(); 
		log(retrievalResultLog);

		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestUserDAO test = new TestUserDAO();

		test.testInsertUser();
		test.testGetAllUsers();
		test.testGetUserById();
		test.testUpdateUser();
		test.testGetUserByEmailAndPassword();
		test.testGetUserByAccountId();
	}

}

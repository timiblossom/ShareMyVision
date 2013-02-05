/**
 * 
 */
package com.smv.test.dao.user;

import org.apache.log4j.Logger;

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
public class UserDataSeeder extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(UserDataSeeder.class);

	/**
	 * Default Constructor 
	 */
	public UserDataSeeder() {
		super();
		super.setLogger(logger);
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
			user.setEmail("My email " + userId);
			user.setPassword("My password " + userId);
			user.setSalt("My salt " + getRandomNumberGenerator().nextGaussian());
			user.setStatusId(1L);
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

	/**
	 * Entry point from Operating System
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		UserDataSeeder userDataSeeder = new UserDataSeeder();
		
		userDataSeeder.createNewUserWithIdOne();
	}

}

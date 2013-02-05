/**
 * 
 */
package com.smv.test.service.user;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.smv.common.bean.AccountDTO;
import com.smv.common.bean.PermissionDTO;
import com.smv.common.bean.SessionDTO;
import com.smv.common.bean.UserDTO;

import com.smv.service.user.IUser;
import com.smv.service.user.UserImpl;
import com.smv.service.user.db.dbobject.UserDO;

import com.smv.test.dao.user.TestUserDAO;
import com.smv.test.service.AbstractTestService;

/**
 * @author Tri Nguyen
 *
 */
public class TestUser extends AbstractTestService {

	private static final Logger logger = Logger.getLogger(TestUser.class);
	
	public IUser iUser = new UserImpl();

	/**
	 * 
	 */
	public TestUser(String name) {
		super(name);
		super.setLogger(logger);
	}
	
	public static Test suite(){
		return new TestSuite(TestUser.class);
	}
	
	public void testLogin() throws Exception {
		// Create User DO
		TestUserDAO testUserDAO = new TestUserDAO();
		UserDO userDO = testUserDAO.createNewUserWithRandomId();
		
		// Create User DTO
		UserDTO userDTO = new UserDTO();
		
		// Copy bare minimum attributes of User DO over to User DTO to allow login
		try {
			userDTO.setSessionClientIp(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException uhe) {
			String errorMessage = "Unable to get localhost address: " + uhe.toString();
			log(errorMessage);
			throw uhe;
		}
		userDTO.setUserEmail(userDO.getEmail());
		userDTO.setUserPassword(userDO.getPassword());
		userDTO.setUserId(userDO.getId());
		userDTO.setUserAgent("Mozilla");
		
		SessionDTO sessionDTO = null;
		try {
			String message = "Logging in with user = " + userDTO;
			TestUser.log(message);
			//sessionDTO = iUser.login(userDTO);
			message = "Logged in with session = " + sessionDTO;
			TestUser.log(message);
		} catch (Exception exception) {
			TestUser.log(exception, Level.INFO);
		}
		TestUser.log(sessionDTO, Level.INFO);
	}

	public void testLogout() throws Exception {
		// Create User DO
		TestUserDAO testUserDAO = new TestUserDAO();
		UserDO userDO = testUserDAO.createNewUserWithRandomId();
		
		// Create User DTO
		UserDTO userDTO = new UserDTO();
		
		// Copy bare minimum attributes of User DO over to User DTO to allow login
		try {
			userDTO.setSessionClientIp(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException uhe) {
			String errorMessage = "Unable to get localhost address: " + uhe.toString();
			log(errorMessage);
			throw uhe;
		}
		userDTO.setUserEmail(userDO.getEmail());
		userDTO.setUserPassword(userDO.getPassword());
		userDTO.setUserId(userDO.getId());
		userDTO.setUserAgent("Mozilla");
		
		SessionDTO sessionDTO = null;
		try {
			String message = "Logging in with user = " + userDTO;
			TestUser.log(message);
			//sessionDTO = iUser.login(userDTO);
			message = "Logged in with session = " + sessionDTO;
			TestUser.log(message);

			//sessionDTO = iUser.logout(userDTO);
			message = "Logged out with session = " + sessionDTO;
			TestUser.log(message);
		} catch (Exception exception) {
			TestUser.log(exception, Level.INFO);
		}
		TestUser.log(sessionDTO, Level.INFO);
	}

	public void testRegister() throws Exception {
		
	}

	public void testGetAccount() throws Exception {
		
	}

	public void testGetUser() throws Exception {
		
	}

	public void testUpsertUser() throws Exception {
		
	}

	public void testUpsertAccount() throws Exception {
		
	}

	public void testGetUserPermission() throws Exception {
		
	}

	public void testUpsertUserPermission() throws Exception {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TestUser testUser = new TestUser("Test User Interface and Implementation");
			testUser.setLogger(logger);
			
			testUser.testLogin();
			testUser.testLogout();
			testUser.testRegister();
			testUser.testGetAccount();
			testUser.testGetUser();
			testUser.testUpsertUser();
			testUser.testUpsertAccount();
			testUser.testGetUserPermission();
			testUser.testUpsertUserPermission();
		} catch (Exception exception) {
			String errorMessage = "Exiting because of exception = " + exception.getMessage();
			TestUser.log(exception);
		}
	}

}

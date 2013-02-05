
package com.zaptoe.user.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.bean.UserDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.UserConstant;
import com.zaptoe.user.db.dao.UserDAO;
import com.zaptoe.user.db.dbo.UserDO;


public class RegisterHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(RegisterHelper.class);

	public static UserDTO register(UserDTO user) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			ParameterChecker.checkUserPassword(user);
			performRegisterWork(user);
			return cleansePasswordFromUserDTO(user);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "register(UserDTO user) failed. " + 
									"\n" +
									"User = " + 
									user + 
									"\n " + 
									"Exception = " +
									iae;
			LOGGER.error(errorMessage);

			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG + errorMessage);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "register(UserDTO user) failed. " + 
									"\n" +
									"User = " + 
									user + 
									"\n " + 
									"Exception = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "register(UserDTO user) failed. " + 
									"\n" +
									"User = " + 
									user + 
									"\n " + 
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);

			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;

		}
	}

	protected static UserDTO cleansePasswordFromUserDTO(UserDTO user) throws Exception {
		// Remove password from User DTO
		user.setUserPassword(null);

		return user;
	}

	protected static UserDTO performRegisterWork(UserDTO user) throws Exception {
		
		// Encrypt user's password
		String passwordSalt = PasswordEncryptionHelper.generatePasswordSalt();
		user.setUserSalt(passwordSalt);
		String encryptedPassword = PasswordEncryptionHelper.encryptPassword(user.getUserPassword(), passwordSalt);
		user.setUserPassword(encryptedPassword);

		// Check if User exists in the system.
		UserDO userDO = UserDAO.getUserByEmail(user.getUserEmail());

		if (userDO != null) {
			String errorMessage = "User already exists. " +
									"\n" +
									"User = " + 
									user;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_USER_ALREADY_EXISTS,
					ZapToeErrorCode.USER_SERVICE_USER_ALREADY_EXISTS_MSG);
		}

		user.setAction(UserConstant.INSERT);
		return UpsertUserHelper.upsertUser(user);

	}

}


package com.zaptoe.user.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.bean.UserDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.db.dao.UserDAO;
import com.zaptoe.user.db.dbo.UserDO;
import com.zaptoe.util.db.AbstractDO;
import com.zaptoe.util.text.StringUtils;


public class ChangePasswordHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(ChangePasswordHelper.class);


	protected static void performChangePasswordWork(UserDTO user, String newPassword) throws ZapToeServiceException, Exception {
		try {
			// Basic parameter checking on UserDTO, including email, old password, and new password.
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			ParameterChecker.checkUserPassword(user);
			ParameterChecker.checkPassword(newPassword);

			// Retrieve UserDO based on UserDTO's user email address
			UserDO userDO = UserDAO.getUserByEmail(user.getUserEmail());

			// Check if User exists in the system.
			if (userDO == null) {
				// Throw exception if user does not exist (i.e. UserDO is null)
				String errorMessage = "User does not exist. " +
										"\n" +
										"User = " + 
										user;
				LOGGER.error(errorMessage);
				throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NONE_EXISTENT_USER,
						ZapToeErrorCode.USER_SERVICE_NONE_EXISTENT_USER_MSG);
			}
			
			// Get salt of both UserDO and UserDTO
			String userDOSalt = userDO.getSalt();

			// Get passwords of both UserDO and UserDTO
			String userDOPassword = userDO.getPassword();

			// Encrypt user's password
			String encryptedUserDTOPassword = PasswordEncryptionHelper.encryptPassword(user.getUserPassword(), userDOSalt);
			
			// Encrypt new user's password
			String encryptedNewPassword = PasswordEncryptionHelper.encryptPassword(newPassword, userDOSalt);
			
			// Compare UserDO's password with UserDTO's password
			if (!StringUtils.nullOrEmptyOrBlankString(userDOPassword) && 
					!StringUtils.nullOrEmptyOrBlankString(encryptedUserDTOPassword) && 
					userDOPassword.equals(encryptedUserDTOPassword)) {
				// If they match, then change password to new password
				userDO.setPassword(encryptedNewPassword);
			} else {
				// If they do not match, then throw exception
				throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORDS_DO_NOT_MATCH,
						ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORDS_DO_NOT_MATCH_MSG);
			}
			
			// Save changes to User DO: new password changes
			userDO.setOperation(AbstractDO.UPDATE);
			UserDAO userDao = new UserDAO(userDO);
			userDao.save(true, false);
			
		} catch (Exception exception) {
			String errorMessage = "performChangePasswordWork(UserDTO user, String newPassword) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"NewPassword = " +
									newPassword +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);
			throw exception;
		}
	}
	
	public static void changePassword(UserDTO user, String newPassword) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			ParameterChecker.checkUserPassword(user);
			ParameterChecker.checkPassword(newPassword);
			performChangePasswordWork(user, newPassword);
			return;
		} catch (IllegalArgumentException iae) {
			String errorMessage = "changePassword(UserDTO user, String newPassword) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"NewPassword = " +
									newPassword +
									"\n " +
									"Exception = " +
									iae;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG + errorMessage);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "changePassword(UserDTO user, String newPassword) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"NewPassword = " +
									newPassword +
									"\n " +
									"Exception = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "changePassword(UserDTO user, String newPassword) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"NewPassword = " +
									newPassword +
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

}

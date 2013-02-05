
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


public class ChangeForgottenPasswordHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(ChangeForgottenPasswordHelper.class);
	

	protected static void performChangeForgottenPasswordWork(UserDTO user) throws ZapToeServiceException, Exception {
		try {
			
			// Basic parameter checking on UserDTO, including email, old password, and new password.
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			ParameterChecker.checkUserPassword(user);

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
			
			// Get Password Reset Codes of both UserDO and UserDTO
			String userDOPasswordResetCode = userDO.getPasswordResetCode();
			String userDTOPasswordResetCode = user.getUserPasswordResetUserCode();
			
			// Compare UserDO's Password Reset Code with UserDTO's Password Reset Code
			if (!StringUtils.nullOrEmptyOrBlankString(userDOPasswordResetCode) && 
					!StringUtils.nullOrEmptyOrBlankString(userDTOPasswordResetCode) && 
					userDOPasswordResetCode.equals(userDTOPasswordResetCode)) {
				// Get salt of both UserDO and UserDTO
				String userDOSalt = userDO.getSalt();

				String newPassword = user.getUserPassword();

				// Encrypt new user's password
				String encryptedNewPassword = PasswordEncryptionHelper.encryptPassword(newPassword, userDOSalt);
				
				// If they match, then change password to new password
				userDO.setPassword(encryptedNewPassword);
			} else {
				// If they do not match, then throw exception
				throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORD_RESET_CODES_DO_NOT_MATCH,
						ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORD_RESET_CODES_DO_NOT_MATCH_MSG);
			}
			
			// Save changes to User DO: new password changes
			userDO.setOperation(AbstractDO.UPDATE);
			UserDAO userDao = new UserDAO(userDO);
			userDao.save(true, false);
			
		} catch (Exception exception) {
			String errorMessage = "performChangeForgottenPasswordWork(UserDTO user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);
			throw exception;
		}
	}
	
	public static void changeForgottenPassword(UserDTO user) 
		throws IllegalArgumentException, 
				ZapToeServiceException, 
				Exception {
		try {
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			ParameterChecker.checkUserPassword(user);
			ParameterChecker.checkPassword(user.getUserPassword());
			performChangeForgottenPasswordWork(user);
			return;
		} catch (IllegalArgumentException iae) {
			String errorMessage = "changeForgottenPassword(UserDTO user) failed. " +
									"\n " +
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
			String errorMessage = "changeForgottenPassword(UserDTO user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"Exception = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "changeForgottenPassword(UserDTO user) failed. " +
									"\n " +
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

}

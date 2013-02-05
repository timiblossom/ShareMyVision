
package com.zaptoe.user.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.bean.UserDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.db.dao.UserDAO;
import com.zaptoe.user.db.dbo.UserDO;
import com.zaptoe.util.db.AbstractDO;



public class ForgetPasswordHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(ForgetPasswordHelper.class);
	

	public static UserDTO forgetPassword(UserDTO user) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			return performForgetPasswordWork(user);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "forgetPassword(UserDTO user) failed. " +
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
			String errorMessage = "forgetPassword(UserDTO user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"Exception = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "forgetPassword(UserDTO user) failed. " +
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
	
	protected static UserDTO performForgetPasswordWork(UserDTO user) throws ZapToeServiceException, Exception {
		try {
			
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
			
			// Reset user's Password Reset Code to something quasi-random
			long time = System.currentTimeMillis();
			String emailAddress = user.getUserEmail();
			String newPasswordResetCode = ActivationHelper.generateEncryptionString(time, emailAddress);
			userDO.setPasswordResetCode(newPasswordResetCode);
			
			// Save changes to User DO: new password changes
			userDO.setOperation(AbstractDO.UPDATE);
			UserDAO userDao = new UserDAO(userDO);
			userDao.save(true, false);
			
			// Propagate Password Reset Code from User DO to User DTO
			user.setUserPasswordResetUserCode(userDO.getPasswordResetCode());
			
			return user;
			
		} catch (Exception exception) {
			String errorMessage = "performForgetPasswordWork(UserDTO user) failed. " +
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
	
}


package com.zaptoe.user.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.bean.UserDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.db.dao.UserDAO;
import com.zaptoe.user.db.dbo.UserDO;
import com.zaptoe.user.db.dbo.UserStatusDO;
import com.zaptoe.util.db.AbstractDO;
import com.zaptoe.util.encryption.TriDesCipher;
import com.zaptoe.util.number.Conversion;
import com.zaptoe.util.text.StringUtils;


public class ActivationHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(ActivationHelper.class);
	private static final String DUMMY_USER_EMAIL_ADDRESS = "junk@gmail.com";

	public static String generateActivationCode(UserDTO user) {
		long now = System.currentTimeMillis();
		String userEmailAddress = user.getUserEmail();
        return generateEncryptionString(now, userEmailAddress);
	}
	
	public static String generateEncryptionString(long time, String emailAddress) {
		
		byte[] emailAddressInBytes = !StringUtils.nullOrEmptyOrBlankString(emailAddress) ? emailAddress.getBytes() : ActivationHelper.DUMMY_USER_EMAIL_ADDRESS.getBytes();
		byte[] timeInBytes = Conversion.longToByteArray(time);
		
		int n = timeInBytes.length + emailAddressInBytes.length;
		byte[] result = new byte[n];
		int offset = 1;
		
		//time
		for(int i=0; i<timeInBytes.length; i++) {
			result[n-offset] = timeInBytes[i];
			offset++;
		}
		
		//email address
		for(int i=0; i<emailAddressInBytes.length; i++) {
			result[n-offset] = emailAddressInBytes[i];
			offset++;
		}
		
		TriDesCipher cipher = EncryptionDecryptionHelper.getCipher();
		String encryptedString = cipher.encrypt(result);
		
		return encryptedString;
		
	}
	
	public static void activateUser(String activationCode) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			// Validate parameter(s)
			ParameterChecker.checkActivationCode(activationCode);
			performActivateUserWork(activationCode);
			return;
		} catch (IllegalArgumentException iae) {
			String errorMessage = "activateUser(String activationCode) failed. " +
									"\n " +
									"ActivationCode = " +
									activationCode +
									"\n " +
									iae;
			LOGGER.error(errorMessage);
			ZapToeServiceException swe = new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT, 
					ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "activateUser(String activationCode) failed. " +
									"\n " +
									"ActivationCode = " +
									activationCode +
									"\n " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "activateUser(String activationCode) failed. " +
									"\n " +
									"ActivationCode = " +
									activationCode +
									"\n " +
									exception;
			LOGGER.error(errorMessage);

			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
		}
	}

	protected static void performActivateUserWork(String activationCode) throws Exception {
		try {
			// Retrieve user by activation code
			UserDO userDO = UserDAO.getUserByActivationCode(activationCode);
			
			// Check if UserDO was retrieved by using activationCode
			// Throw exception if there is no UserDO
			if (userDO == null) {
				String errorMessage = "User does not exist with " +
										"\n " +
										"ActivationCode = " +
										activationCode;
				LOGGER.error(errorMessage);

				throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NONE_EXISTENT_USER_WITH_SPECIFIED_ACTIVATION_CODE,
						ZapToeErrorCode.USER_SERVICE_NONE_EXISTENT_USER_WITH_SPECIFIED_ACTIVATION_CODE_MSG); 
			}
			
			// Otherwise, i.e. User does exist in system with parameterized activation code 
			// Set user status to "Activated"

			// Set attributes of UserDO
			UserStatusDO activateUserStatus = UserStatusDO.getUserStatus(UserStatusDO.USER_STATUS_NAME_ACTIVATED);
			userDO.setStatusId(activateUserStatus.getId());
			
			// Save changes to User DO
			userDO.setOperation(AbstractDO.UPDATE);
			UserDAO userDao = new UserDAO(userDO);
			userDao.save(true, false);

		} catch (Exception exception) {
			// Throw exceptions in error cases and if necessary
			String errorMesssage = "Error in performing performActivateUserWork(String activationCode); " +
									"\n " +
									"ActivationCode = " + 
									activationCode + 
									"\n " +
									"Exception = " + 
									exception; 
			LOGGER.error(errorMesssage);
			throw exception;
		}
	}

	protected static String performActivationCodeRegenerationWork(UserDTO user) throws IllegalArgumentException, ZapToeServiceException, Exception {
		// Set activation code attribute of User DO
		UserDO userDO = UserDAO.getUserByEmail(user.getUserEmail());

		// Check if UserDO was retrieved by using user email address
		// Throw exception if there is no UserDO
		if (userDO == null) {
			String errorMessage = "User does not exist with " +
									"\n " +
									"User Email Address = " +
									user.getUserEmail();
			LOGGER.error(errorMessage);

			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NONE_EXISTENT_USER,
					ZapToeErrorCode.USER_SERVICE_NONE_EXISTENT_USER_MSG); 
		}
		
		// Regenerate activation code
		String regeneratedActivationCode = generateActivationCode(user);

		// Set activation code attribute of User DTO
		user.setUserActivationCode(regeneratedActivationCode);
		
		// Set activation code attribute of User DTO
		userDO.setActivationCode(regeneratedActivationCode);
		
		// Save changes to User DO
		userDO.setOperation(AbstractDO.UPDATE);
		UserDAO userDao = new UserDAO(userDO);
		userDao.save(true, false);
		
		return regeneratedActivationCode;
	}
	
	public static String regenerateActivationCode(UserDTO user) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			// Validate parameter(s)
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			return performActivationCodeRegenerationWork(user);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "regenerateActivationCode(UserDTO user) failed. " +
									"\n " +
									"UserDTO = " +
									user +
									"\n " +
									iae;
			LOGGER.error(errorMessage);
			ZapToeServiceException swe = new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT, 
					ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "regenerateActivationCode(UserDTO user) failed. " +
									"\n " +
									"UserDTO = " +
									user +
									"\n " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "regenerateActivationCode(UserDTO user) failed. " +
									"\n " +
									"UserDTO = " +
									user +
									"\n " +
									exception;
			LOGGER.error(errorMessage);

			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
		}
	}

}

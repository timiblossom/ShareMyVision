
package com.zaptoe.user.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.db.dao.UserDAO;
import com.zaptoe.user.db.dbo.UserDO;


public class IsUserExistedHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(IsUserExistedHelper.class);


	public static Boolean isUserExisted(String emailAddress) throws ZapToeServiceException {
		try {
			ParameterChecker.checkEmailAddress(emailAddress);
			return performIsUserExistedWork(emailAddress);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "isUserExist(String emailAddress) failed. " +
									"\n " +
									"EmailAddress = " +
									emailAddress +
									"\n " +
									iae;
			LOGGER.error(errorMessage);

			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG + errorMessage);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "isUserExist(String emailAddress) failed. " +
									"\n " +
									"EmailAddress = " +
									emailAddress +
									"\n " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "isUserExist(String emailAddress) failed. " +
									"\n " +
									"EmailAddress = " +
									emailAddress +
									"\n " +
									exception;
			LOGGER.error(errorMessage);

			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
		}
	} // isUserExist()

	public static Boolean performIsUserExistedWork(String emailAddress) throws ZapToeServiceException {
		try {
			// Retrieve User and related objects
			UserDO userDO = UserDAO.getUserByEmail(emailAddress);

			// Return result based on whether User DO is null or not
			return (userDO != null) ? Boolean.TRUE : Boolean.FALSE;

		} catch (Exception exception) {
			String errorMessage = "performIsUserExist(String emailAddress) failed. " +
									"\n " +
									"EmailAddress = " +
									emailAddress +
									"\n " +
									exception;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
		}
	} // performIsUserExistWork()

	public static boolean doesUserExist(long userId) {
		UserDO userDO = UserDAO.getUserById(userId);
		return (userDO == null) ? false : true;
	}

}

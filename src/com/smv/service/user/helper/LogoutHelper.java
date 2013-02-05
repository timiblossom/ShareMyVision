
package com.zaptoe.user.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.bean.SessionDTO;
import com.zaptoe.common.bean.UserDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.db.dao.SessionDAO;
import com.zaptoe.user.db.dao.UserDAO;
import com.zaptoe.user.db.dbo.SessionDO;
import com.zaptoe.user.db.dbo.UserDO;
import com.zaptoe.util.db.AbstractDO;


public class LogoutHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(LogoutHelper.class);


	public static SessionDTO logout(UserDTO user) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			return performLogoutWork(user);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "logout(UserDTO user) failed. " +
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
			String errorMessage = "logout(UserDTO user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"Exception = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "logout(UserDTO user) failed. " +
									"\n " +
									"User = " +
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

	protected static SessionDTO performLogoutWork(UserDTO user) throws ZapToeServiceException, Exception {
		try {
			// Check if User exists in the system.
			UserDO userDO = UserDAO.getUserByEmail(user.getUserEmail());

			if (userDO == null) {
				String errorMessage = "User does not exist." +
										"\n" +
										"User = " + 
										user;
				LOGGER.error(errorMessage);
				throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NONE_EXISTENT_USER,
						ZapToeErrorCode.USER_SERVICE_NONE_EXISTENT_USER_MSG);
			}

			// Retrieve Session by User
			SessionDO sessionDO = SessionDAO.getSessionByUserEmail(user.getUserEmail());

			// If Session does exist, then update the logged_in state to true and return that Session
			if (sessionDO != null) {
				// Create new Session DTO
				SessionDTO session = new SessionDTO();
				
				// Set attributes of Session DO
				sessionDO.setLoggedIn(false);

				// Save changes to Session DO
				sessionDO.setOperation(AbstractDO.UPDATE);
				SessionDAO entityDAO = new SessionDAO(sessionDO);
				entityDAO.save(true, false);

				// Create new SessionDTO
				session = new SessionDTO();
				
				// Set attributes of Session DTO
				session.setId(sessionDO.getId());
				session.setSessionLastLogin(sessionDO.getLastLogin());
				session.setSessionLoggedIn(sessionDO.getLoggedIn());
				session.setSessionClieptIp(sessionDO.getClientIp());
				session.setSessionGuid(sessionDO.getGuid());
				session.setSessionUid(sessionDO.getUid());
				session.setSessionUserAgent(sessionDO.getUserAgent());
				return session;
			} else {
				throw new Exception("There is no session associated with this user: " + user);
			}
		} catch (Exception exception) {
			LOGGER.error("Error in performing performLogoutWork(); User = " + user + "; Exception = " + exception);
			throw exception;
		}
	}

}

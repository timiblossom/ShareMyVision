
package com.zaptoe.user.helper;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.Constant;
import com.zaptoe.common.bean.SessionDTO;
import com.zaptoe.common.bean.UserDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.db.dao.SessionDAO;
import com.zaptoe.user.db.dao.UserDAO;
import com.zaptoe.user.db.dbo.SessionDO;
import com.zaptoe.user.db.dbo.UserDO;
import com.zaptoe.util.db.AbstractDO;
import com.zaptoe.util.encryption.TriDesCipher;
import com.zaptoe.util.number.Conversion;
import com.zaptoe.util.text.StringUtils;
import com.zaptoe.util.webcontainer.DefaultContextLoaderListener;


public class LoginHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(LoginHelper.class);
	
	private static final String DUMMY_CLIENT_IP_ADDRESS = "127.0.0.1";
	private static final String DUMMY_USER_EMAIL_ADDRESS = "junk@gmail.com";
	private static final String DUMMY_USER_AGENT = "Mozilla";
	
	private LoginHelper() {
	}

	public static SessionDTO login(UserDTO user, Map<String, String> httpEnv) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			ParameterChecker.checkUserPassword(user);
			return performLoginWork(user, httpEnv);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "login(UserDTO user, Map<String, String> httpEnv) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"HttpEnv = " +
									httpEnv +
									"\n " +
									"Exception = " +
									iae;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG + errorMessage);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "login(UserDTO user, Map<String, String> httpEnv) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"HttpEnv = " +
									httpEnv +
									"\n " +
									"Exception = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "login(UserDTO user, Map<String, String> httpEnv) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"HttpEnv = " +
									httpEnv +
									exception;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
		}
	}

	protected static String getSessionClientIpAddress(Map<String, String> httpEnv) {
        String callerIp = (String) httpEnv.get(Constant.REMOTE_ADDR);
        if (StringUtils.nullOrEmptyOrBlankString(callerIp)) {
        	callerIp = LoginHelper.DUMMY_CLIENT_IP_ADDRESS;
        }
        return callerIp;
	}

	public static String generateGuid(String clientIp, String serverIp, long time, String emailAddress) {
	    return EncryptionDecryptionHelper.getCipher().encrypt(clientIp + serverIp + time + emailAddress);
	}
	
	public static String generateGuid(byte[] clientIP, byte[] serverIP, long time, String emailAddress) {
		
		byte[] emailAddressInBytes = !StringUtils.nullOrEmptyOrBlankString(emailAddress) ? emailAddress.getBytes() : LoginHelper.DUMMY_USER_EMAIL_ADDRESS.getBytes();
		byte[] timeInBytes = Conversion.longToByteArray(time);
		
		
		int n = clientIP.length + serverIP.length + timeInBytes.length + emailAddressInBytes.length;
		byte[] result = new byte[n]; //should be at least 4 + 4 + 8 = 16
		int offset = 1;
		
		//clientIp : [n-1]-[n-4]		
		for(int i=0; i<clientIP.length; i++ ) {
			result[n-offset] = clientIP[i];
			offset++;
		}
		
		//serverIp : [n-5]-[n-8]
		for(int i=0; i<serverIP.length; i++) {
			result[n-offset] = serverIP[i];
			offset++;
		}
		
		//time : [n-9] - [n-16]
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
		String guid = cipher.encrypt(result);
		
		return guid;
	}

	protected static String generateGuidForUserSession(UserDTO user, Map<String, String> httpEnv) throws NumberFormatException, Exception {
        try {
        	long now = System.currentTimeMillis();
        	String userEmailAddress = user.getUserEmail();
        	//byte[] serverIp = DefaultContextLoaderListener.context.hostname;
        	String serverIp = DefaultContextLoaderListener.context.hostname;
        	String callerIp = getSessionClientIpAddress(httpEnv);
        	//byte[] clientIp = Conversion.getByteArrayFromIp(callerIp);
	        return generateGuid(callerIp, serverIp, now, userEmailAddress);
		} catch (NumberFormatException nfe) {
			String errorMessage = "Error in performing generateGuidForUserSession(UserDTO user, Map<String, String> httpEnv);" +
									"\n" +
									"User = " + 
									user + 
									"\n" +
									"httpEnv = " + 
									httpEnv + 
									"\n" + 
									"NumberFormatException = " + 
									nfe;
			LOGGER.error(errorMessage);
			throw nfe;
		} catch (Exception exception) {
			String errorMessage = "Error in performing generateGuidForUserSession(UserDTO user, Map<String, String> httpEnv);" +
									"\n" +
									"User = " + 
									user + 
									"\n" +
									"httpEnv = " + 
									httpEnv + 
									"\n" + 
									"Exception = " + 
									exception;
			LOGGER.error(errorMessage);
			throw exception;
		}
	}
	
	protected static SessionDTO performLoginWork(UserDTO user, Map<String, String> httpEnv) throws Exception { 
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
			
			
			// Get passwords of both UserDO and UserDTO
			String userDOPassword = userDO.getPassword();
			String userDOSalt = userDO.getSalt();
			
			// Encrypt user's password
			String encryptedUserDTOPassword = PasswordEncryptionHelper.encryptPassword(user.getUserPassword(), userDOSalt);
			
			// Compare UserDO's password with UserDTO's password
			if (StringUtils.nullOrEmptyOrBlankString(userDOPassword) || 
					StringUtils.nullOrEmptyOrBlankString(encryptedUserDTOPassword) || 
					!userDOPassword.equals(encryptedUserDTOPassword)) {
				// If they match, then change password to new password
				// If they do not match, then throw exception
				throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORDS_DO_NOT_MATCH,
						ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORDS_DO_NOT_MATCH_MSG);
			}
			
			// Create new Session DTO
			SessionDTO session = new SessionDTO();

			// Retrieve Session by User Email
			SessionDO sessionDO = SessionDAO.getSessionByUserEmail(user.getUserEmail());

			// If Session does not exist, then create a new one
			if (sessionDO == null) {
				// Create new Session only if user matches existing one in User System
				sessionDO = new SessionDO();

				// Calculate GUID of session for new Session
				String sessionGuid = generateGuidForUserSession(user, httpEnv);

				// Get current Date
				Date currentDate = new Date();

				// Set attributes of Session DO
				sessionDO.setClientIp(getSessionClientIpAddress(httpEnv));
				sessionDO.setGuid(sessionGuid);
				sessionDO.setLastLogin(currentDate);
				sessionDO.setLoggedIn(true);
				sessionDO.setUserAgent(getUserAgent(httpEnv));
				sessionDO.setUid(userDO.getId());
				
				// Save changes to Session DO
				sessionDO.setOperation(AbstractDO.CREATE);
				SessionDAO entityDAO = new SessionDAO(sessionDO);
				entityDAO.save(true, false);

				// Set attributes of Session DTO
				session.setId(sessionDO.getId());
				session.setSessionLoggedIn(sessionDO.getLoggedIn());
				session.setSessionClieptIp(sessionDO.getClientIp());
				session.setSessionGuid(sessionDO.getGuid());
				session.setSessionUid(sessionDO.getUid());
				session.setSessionUserAgent(sessionDO.getUserAgent());

			} 
			// Otherwise, if Session does exist, then update the logged_in state to true and return that Session
			else {

				// Calculate GUID of session of existing User
				String sessionGuid = generateGuidForUserSession(user, httpEnv);

				// Get current Date
				Date currentDate = new Date();

				// Set attributes of Session DO
				sessionDO.setClientIp(getSessionClientIpAddress(httpEnv));
				sessionDO.setGuid(sessionGuid);
				sessionDO.setLastLogin(currentDate);
				sessionDO.setLoggedIn(true);
				sessionDO.setUserAgent(getUserAgent(httpEnv));
				
				// Save changes to Session DO
				sessionDO.setOperation(AbstractDO.UPDATE);
				SessionDAO entityDAO = new SessionDAO(sessionDO);
				entityDAO.save(true, false);
				
				// Set attributes of Session DTO
				session.setId(sessionDO.getId());
				session.setSessionLoggedIn(sessionDO.getLoggedIn());
				session.setSessionClieptIp(sessionDO.getClientIp());
				session.setSessionGuid(sessionDO.getGuid());
				session.setSessionUid(sessionDO.getUid());
				session.setSessionUserAgent(sessionDO.getUserAgent());
				
			} // if (sessionDO == null) {
			
			// Return Session DTO
			return session;

		} catch (Exception exception) {
			String errorMessage = "Error in performing performLoginWork();" +
									"\n" +
									"User = " + 
									user + 
									"\n" + 
									"Exception = " + 
									exception;
			LOGGER.error(errorMessage);
			throw exception;
		}
	}

	protected static String getUserAgent(Map<String, String> httpEnv) {
		String userAgent = (String) httpEnv.get(Constant.HTTP_USER_AGENT);
		if (StringUtils.nullOrEmptyOrBlankString(userAgent)) {
			userAgent = LoginHelper.DUMMY_USER_AGENT;
		}
		return userAgent;
	}
	
}

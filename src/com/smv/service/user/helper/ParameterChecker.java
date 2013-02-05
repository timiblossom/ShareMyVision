
package com.zaptoe.user.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.bean.AccountDTO;
import com.zaptoe.common.bean.ContactDTO;
import com.zaptoe.common.bean.PermissionDTO;
import com.zaptoe.common.bean.UserDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.helper.validator.EmailValidator;
import com.zaptoe.user.helper.validator.NameValidator;
import com.zaptoe.user.helper.validator.PasswordValidator;
import com.zaptoe.user.helper.validator.ValidatorResourceManager;
import com.zaptoe.util.config.ApplicationProperties;
import com.zaptoe.util.text.StringUtils;


public class ParameterChecker {

	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterChecker.class);

	private static String USER_ID_MINIMUM_KEY = "user.id.minimum";
	protected static long DEFAULT_USER_ID_MINIMUM = 1;


	public static void checkPermissionParameter(PermissionDTO permission) throws IllegalArgumentException {
		checkPermissionNonNull(permission);
		checkPermissionId(permission);
	}
	
	public static void checkPermissionNonNull(PermissionDTO permission) throws IllegalArgumentException {
		if (permission == null) {
			String errorMessage = "Permission parameter is null.";
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkPermissionId(PermissionDTO permission) throws IllegalArgumentException {
		checkPermissionNonNull(permission);
		
		if (permission.getId() == null) {
			String errorMessage = "Permission id is null";
			LOGGER.error(errorMessage + "; \nPermission = " + permission);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkAccountParameter(AccountDTO account) throws IllegalArgumentException, ZapToeServiceException {
		checkAccountNonNull(account);
		checkAccountId(account);
		checkName(account.getAccountName());
	}
	
	public static void checkAccountNonNull(AccountDTO account) throws IllegalArgumentException {
		if (account == null) {
			String errorMessage = "Account parameter is null.";
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkAccountId(AccountDTO account) throws IllegalArgumentException {
		checkAccountNonNull(account);
		
		if (account.getId() == null) {
			String errorMessage = "Account id is null";
			LOGGER.error(errorMessage + "; \nAccount = " + account);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkContactNonNull(ContactDTO contact) throws IllegalArgumentException {
		if (contact == null) {
			String errorMessage = "Contact parameter is null.";
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkFirstName(String firstName) throws ZapToeServiceException {
		if (StringUtils.nullOrEmptyOrBlankString(firstName)) {
			String errorMessage = "First Name is null, empty, or blank.";
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_FIRST_NAME,
					ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_FIRST_NAME_MSG);
		}

		if (!NameValidator.isFirstNameValid(firstName)) {
			String errorMessage = ZapToeErrorCode.USER_SERVICE_INVALID_FIRST_NAME +
									"\n" +
									"firstName = " + 
									firstName;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_INVALID_FIRST_NAME,
					ZapToeErrorCode.USER_SERVICE_INVALID_FIRST_NAME_MSG);
		}
	}
	
	public static void checkContactFirstName(ContactDTO contact) throws ZapToeServiceException {
		checkContactNonNull(contact);

		if (StringUtils.nullOrEmptyOrBlankString(contact.getContactFirstName())) {
			String errorMessage = "Contact First Name is null, empty, or blank.";
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_FIRST_NAME,
					ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_FIRST_NAME_MSG);
		}
		
		checkFirstName(contact.getContactFirstName());
	}
	
	public static void checkLastName(String lastName) throws ZapToeServiceException {
		if (StringUtils.nullOrEmptyOrBlankString(lastName)) {
			String errorMessage = "Last Name is null, empty, or blank.";
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_LAST_NAME,
					ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_LAST_NAME_MSG);
		}

		if (!NameValidator.isLastNameValid(lastName)) {
			String errorMessage = ZapToeErrorCode.USER_SERVICE_INVALID_LAST_NAME +
									"\n" +
									"lastName = " + 
									lastName;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_INVALID_LAST_NAME,
					ZapToeErrorCode.USER_SERVICE_INVALID_LAST_NAME_MSG);
		}
	}
	
	public static void checkContactLastName(ContactDTO contact) throws ZapToeServiceException {
		checkContactNonNull(contact);

		if (StringUtils.nullOrEmptyOrBlankString(contact.getContactLastName())) {
			String errorMessage = "Contact Last Name is null, empty, or blank.";
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_LAST_NAME,
					ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_LAST_NAME_MSG);
		}
		
		
		checkFirstName(contact.getContactLastName());
	}
	
	public static void checkName(String name) throws ZapToeServiceException {
		if (StringUtils.nullOrEmptyOrBlankString(name)) {
			LOGGER.error(ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_NAME_MSG);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_NAME,
					ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_NAME_MSG);
		}

		if (!NameValidator.isNameValid(name)) {
			String errorMessage = ZapToeErrorCode.USER_SERVICE_INVALID_NAME_MSG +
									"\n" +
									"name = " + 
									name;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_INVALID_NAME,
					ZapToeErrorCode.USER_SERVICE_INVALID_NAME_MSG);
		}
	}
	
	public static void checkUserNonNull(UserDTO user) throws IllegalArgumentException {
		if (user == null) {
			String errorMessage = "User parameter is null.";
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkUserAction(UserDTO user) throws IllegalArgumentException {
		checkUserNonNull(user);
		
		if (StringUtils.nullOrEmptyOrBlankString(user.getAction())) {
			String errorMessage = "User action is null, empty, or blank";
			LOGGER.error(errorMessage + "; \nUser = " + user);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkUserEmail(UserDTO user) throws ZapToeServiceException {
		checkUserNonNull(user);
		
		if (StringUtils.nullOrEmptyOrBlankString(user.getUserEmail())) {
			String errorMessage = ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORD_MSG +
									"\n" +
									"User = " + 
									user;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORD,
					ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORD_MSG);
		}
		
		if (!EmailValidator.isValid(user.getUserEmail())) {
			String errorMessage = ZapToeErrorCode.USER_SERVICE_INVALID_USER_EMAIL_MSG +
									"\n" +
									"User = " + 
									user;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_INVALID_USER_EMAIL,
					ZapToeErrorCode.USER_SERVICE_INVALID_USER_EMAIL_MSG);
		}
	}

	public static void checkUserPassword(UserDTO user) throws ZapToeServiceException {
		checkUserNonNull(user);
		
		if (StringUtils.nullOrEmptyOrBlankString(user.getUserPassword())) {
			String errorMessage = "User Password is null, empty, or blank." +
									"\n" +
									"User = " + 
									user;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORD,
					ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORD_MSG);
		}
		
		checkPassword(user.getUserPassword());
	}

	public static void checkActivationCode(String activationCode) throws ZapToeServiceException {
		if (StringUtils.nullOrEmptyOrBlankString(activationCode)) {
			String errorMessage = "ActivationCode is null, empty, or blank." +
									"\n" +
									"ActivationCode = " + 
									activationCode;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_ACTIVATION_CODE,
					ZapToeErrorCode.USER_SERVICE_NULL_BLANK_EMPTY_ACTIVATION_CODE_MSG);
		}
	}

	public static void checkPassword(String password) throws ZapToeServiceException {
		if (StringUtils.nullOrEmptyOrBlankString(password)) {
			String errorMessage = "Password is null, empty, or blank." +
									"\n" +
									"Password = " + 
									password;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORD,
					ZapToeErrorCode.USER_SERVICE_NULL_USER_PASSWORD_MSG);
		}

		if (!PasswordValidator.isValid(password)) {
			String errorMessage = ZapToeErrorCode.USER_SERVICE_INVALID_PASSWORD +
									"\n" +
									"password = " + 
									password;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_INVALID_PASSWORD,
					                         ZapToeErrorCode.USER_SERVICE_INVALID_PASSWORD_MSG);
		}
	}

	public static void checkEmailAddress(String emailAddress) throws ZapToeServiceException {
		if (StringUtils.nullOrEmptyOrBlankString(emailAddress)) {
			String errorMessage = "EmailAddress is null, empty, or blank." +
									"\n" +
									"EmailAddress = " + 
									emailAddress;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NULL_USER_EMAIL,
					                         ZapToeErrorCode.USER_SERVICE_NULL_USER_EMAIL_MSG);
		}

		if (!EmailValidator.isValid(emailAddress)) {
			String errorMessage = ZapToeErrorCode.USER_SERVICE_INVALID_USER_EMAIL_MSG +
									"\n" +
									"emailAddress = " + 
									emailAddress;
			LOGGER.error(errorMessage);
			throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_INVALID_USER_EMAIL,
											ZapToeErrorCode.USER_SERVICE_INVALID_USER_EMAIL_MSG);
		}
	}

	public static void checkUserId(Long userId) throws IllegalArgumentException {
		checkUserIdNonNull(userId);
		checkUserIdRange(userId);
	}

	public static void checkUserIdNonNull(Long userId) throws IllegalArgumentException {
		if (userId == null) {
			String errorMessage = "User Id parameter is null.";
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkUserIdRange(Long userId) throws IllegalArgumentException {
    	ApplicationProperties applicationProperties = ValidatorResourceManager.getApplicationProperties();
    	Long userIdMin = applicationProperties.getSetting(ParameterChecker.USER_ID_MINIMUM_KEY, 
    														ParameterChecker.DEFAULT_USER_ID_MINIMUM);
    	
		if (userId < userIdMin) {
			String errorMessage = "User Id parameter is not within valid range: " +
						        	"\n " +
						        	"userId = " +
						        	userId +
						        	"\n " +
						            "userIdMin = " + 
						            userIdMin;
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

}


package com.zaptoe.user.helper.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.util.config.ApplicationProperties;
import com.zaptoe.util.text.StringUtils;


public class PasswordValidator {

	private final static Logger LOGGER = LoggerFactory.getLogger(PasswordValidator.class);

	private static String MAXIMUM_PASSWORD_LENGTH_KEY = "password.maximum.length";
	private static Integer DEFAULT_PASSWORD_MAXIMUM_LENGTH_VALUE = 100;
	private static String MINIMUM_PASSWORD_LENGTH_KEY = "password.minimum.length";
	private static Integer DEFAULT_PASSWORD_MINIMUM_LENGTH_VALUE = 6;

	public static boolean isValid(String password) {
		if (StringUtils.nullOrEmptyOrBlankString(password)) {
			String errorMessage = "password is null, empty, or blank." +
									"\n" +
									"password = " + 
									password;
			LOGGER.error(errorMessage);
			return false;
		}
		
    	ApplicationProperties applicationProperties = ValidatorResourceManager.getApplicationProperties();
    	Integer maxLength = applicationProperties.getSetting(PasswordValidator.MAXIMUM_PASSWORD_LENGTH_KEY, 
    			PasswordValidator.DEFAULT_PASSWORD_MAXIMUM_LENGTH_VALUE);
    	Integer minLength = applicationProperties.getSetting(PasswordValidator.MINIMUM_PASSWORD_LENGTH_KEY, 
    			PasswordValidator.DEFAULT_PASSWORD_MINIMUM_LENGTH_VALUE);

    	return isLengthValid(password, maxLength, minLength) && isAlphaNumeric(password);
		
	}
	
    protected static boolean isLengthValid(String password, int maxLength, int minLength) {
    	return isWithinMaxLength(password, maxLength) && isWithinMinLength(password, minLength);
    }

    protected static boolean isWithinMaxLength(String password, int maxLength) {
    	return !(password.length() > maxLength);
    }

    protected static boolean isWithinMinLength(String password, int minLength) {
    	return !(password.length() < minLength);
    }

    protected static boolean isAlphaNumeric(String password) {
    	for (int index = 0; index < password.length(); index++) {
    		char ch = password.charAt(index);
    		if (!Character.isLetterOrDigit(ch)) {
    			return false;
    		}
    	}
    		
    	return true;
    }
    
}


package com.zaptoe.user.helper.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.util.config.ApplicationProperties;
import com.zaptoe.util.text.StringUtils;


public class EmailValidator {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailValidator.class);

	private static String MAXIMUM_LENGTH_KEY = "email.maximum.length";
	private static Integer DEFAULT_MAXIMUM_LENGTH_VALUE = 255;

	/**
     * <p>Checks if a field has a valid e-mail address.</p>
     *
     * @param email The value validation is being performed on.  A <code>null</code>
     * value is considered invalid.
     * @return true if the email address is valid.
     */
    public static boolean isValid(String email) {
    	boolean result = true;
    	
		if (StringUtils.nullOrEmptyOrBlankString(email)) {
			String errorMessage = "email is null, empty, or blank." +
									"\n" +
									"email = " + 
									email;
			LOGGER.error(errorMessage);
			return false;
		}
		
		if (!org.apache.commons.validator.EmailValidator.getInstance().isValid(email) || !isLengthValid(email)) {
			return false;
		}
    	
    	return result;
    }

    protected static boolean isLengthValid(String email) {
    	ApplicationProperties applicationProperties = ValidatorResourceManager.getApplicationProperties();
    	Integer maxLength = applicationProperties.getSetting(EmailValidator.MAXIMUM_LENGTH_KEY, 
    			EmailValidator.DEFAULT_MAXIMUM_LENGTH_VALUE);
    	
    	if (email.length() > maxLength) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
}

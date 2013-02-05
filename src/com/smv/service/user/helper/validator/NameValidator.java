
package com.zaptoe.user.helper.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.util.config.ApplicationProperties;
import com.zaptoe.util.text.StringUtils;


public class NameValidator {

	private final static Logger LOGGER = LoggerFactory.getLogger(NameValidator.class);

	private static String MAXIMUM_FIRST_NAME_LENGTH_KEY = "first.name.maximum.length";
	private static Integer DEFAULT_FIRST_NAME_MAXIMUM_LENGTH_VALUE = 100;

	private static String MAXIMUM_LAST_NAME_LENGTH_KEY = "last.name.maximum.length";
	private static Integer DEFAULT_LAST_NAME_MAXIMUM_LENGTH_VALUE = 100;

	private static String MAXIMUM_NAME_LENGTH_KEY = "name.maximum.length";
	private static Integer DEFAULT_NAME_MAXIMUM_LENGTH_VALUE = 100;

	private static String MAXIMUM_DISPLAY_NAME_LENGTH_KEY = "display.name.maximum.length";
	private static Integer DEFAULT_DISPLAY_NAME_MAXIMUM_LENGTH_VALUE = 45;

	public static boolean isFirstNameValid(String firstName) {
    	ApplicationProperties applicationProperties = ValidatorResourceManager.getApplicationProperties();
    	Integer maxLength = applicationProperties.getSetting(NameValidator.MAXIMUM_FIRST_NAME_LENGTH_KEY, 
    			NameValidator.DEFAULT_FIRST_NAME_MAXIMUM_LENGTH_VALUE);
    	
    	return isValid(firstName, maxLength);
	}

	public static boolean isLastNameValid(String lastName) {
    	ApplicationProperties applicationProperties = ValidatorResourceManager.getApplicationProperties();
    	Integer maxLength = applicationProperties.getSetting(NameValidator.MAXIMUM_LAST_NAME_LENGTH_KEY, 
    			NameValidator.DEFAULT_LAST_NAME_MAXIMUM_LENGTH_VALUE);
    	
    	return isValid(lastName, maxLength);
	}

	public static boolean isNameValid(String name) {
    	ApplicationProperties applicationProperties = ValidatorResourceManager.getApplicationProperties();
    	Integer maxLength = applicationProperties.getSetting(NameValidator.MAXIMUM_NAME_LENGTH_KEY, 
    			NameValidator.DEFAULT_NAME_MAXIMUM_LENGTH_VALUE);
    	
    	return isValid(name, maxLength);
	}

	public static boolean isDisplayNameValid(String displayName) {
    	ApplicationProperties applicationProperties = ValidatorResourceManager.getApplicationProperties();
    	Integer maxLength = applicationProperties.getSetting(NameValidator.MAXIMUM_NAME_LENGTH_KEY, 
    			NameValidator.DEFAULT_NAME_MAXIMUM_LENGTH_VALUE);
    	
    	return isLengthValid(displayName, maxLength);
	}

    protected static boolean isValid(String name, int maxLength) {
		if (StringUtils.nullOrEmptyOrBlankString(name)) {
			String errorMessage = "name is null, empty, or blank." +
									"\n" +
									"name = " + 
									name;
			LOGGER.error(errorMessage);
			return false;
		}
		
		return isLengthValid(name, maxLength) && isAlphabetical(name);
    }

    protected static boolean isLengthValid(String name, int maxLength) {
    	return !(name.length() > maxLength);
    }

    protected static boolean isAlphabetical(String name) {
    	for (int index = 0; index < name.length(); index++) {
    		char ch = name.charAt(index);
    		if (!Character.isLetter(ch)) {
    			return false;
    		}
    	}
    		
    	return true;
    }
    
}

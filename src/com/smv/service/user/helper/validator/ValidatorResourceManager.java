package com.zaptoe.user.helper.validator;

import com.zaptoe.user.config.ResourceManager;
import com.zaptoe.util.config.ApplicationProperties;


public class ValidatorResourceManager {

	
	public static ApplicationProperties getApplicationProperties() {
		return ResourceManager.getApplicationProperties();
	}

}

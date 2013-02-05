package com.smv.service.outlet.helper.validator;

import com.smv.service.outlet.config.ResourceManager;
import com.smv.util.config.ApplicationProperties;

/**
 * @author TriNguyen
 *
 */
public class ValidatorResourceManager {

	public static ApplicationProperties getApplicationProperties() {
		return ResourceManager.getApplicationProperties();
	}

}

package com.smv.service.outlet.helper;

import org.apache.log4j.Logger;

import com.smv.common.bean.OutletDTO;
import com.smv.common.bean.UserOutletDTO;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.outlet.helper.validator.ValidatorResourceManager;
import com.smv.util.config.ApplicationProperties;

/**
 * @author TriNguyen
 *
 */
public class ParameterChecker {

	private static final Logger LOGGER = Logger.getLogger(ParameterChecker.class);
	
	private static String OUTLET_ID_MINIMUM_KEY = "outlet.id.minimum";
	protected static long DEFAULT_OUTLET_ID_MINIMUM = 1;

	protected ParameterChecker() {
	}
	
	public static void checkUserOutlet(UserOutletDTO userOutlet) throws IllegalArgumentException {
		checkUserOutletNonNull(userOutlet);
		com.smv.service.user.helper.ParameterChecker.checkUserId(userOutlet.getUserId());
	}

	public static void checkUserOutletNonNull(UserOutletDTO userOutlet) throws IllegalArgumentException {
		if (userOutlet == null) {
			String errorMessage = "User Outlet parameter is null.";
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkOutlet(OutletDTO outlet) throws IllegalArgumentException, SmvServiceException {
		checkOutletNonNull(outlet);
		checkOutletIdRange(outlet.getId());
		OutletHelper.checkOutletTypeExists(outlet.getId());
	}

	public static void checkOutletNonNull(OutletDTO outlet) throws IllegalArgumentException {
		if (outlet == null) {
			String errorMessage = "Outlet parameter is null.";
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkOutletIdRange(Long outletId) throws IllegalArgumentException {
    	ApplicationProperties applicationProperties = ValidatorResourceManager.getApplicationProperties();
    	Long outletIdMin = applicationProperties.getSetting(ParameterChecker.OUTLET_ID_MINIMUM_KEY, 
    			ParameterChecker.DEFAULT_OUTLET_ID_MINIMUM);
    	
		if (outletId < outletIdMin) {
			String errorMessage = "Outlet Id parameter is not within valid range: " +
						        	"\n " +
						        	"outletId = " +
						        	outletId +
						        	"\n " +
						            "outletIdMin = " + 
						            outletIdMin;
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

}

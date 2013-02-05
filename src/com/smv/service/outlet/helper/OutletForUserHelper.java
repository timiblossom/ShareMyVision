package com.smv.service.outlet.helper;

import org.apache.log4j.Logger;

import com.smv.common.bean.UserOutletDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.outlet.OutletConstant;

/**
 * @author TriNguyen
 *
 */
public class OutletForUserHelper {

	private static final Logger LOGGER = Logger.getLogger(OutletForUserHelper.class);

    protected OutletForUserHelper() {
    }
    
    public static UserOutletDTO performOutletForUserWork(UserOutletDTO userOutlet, 
    														String statusType) 
    	throws SmvServiceException, Exception {
		try {
			// Comprehensive checking of User Outlet
			UserOutletHelper.checkUserOutlet(userOutlet);

    		userOutlet.setStatusType(statusType);

    		// Verify if User-Outlet exists
        	Long userId = userOutlet.getUserId();
        	Long outletId = userOutlet.getOutlet().getId();
        	if (UserOutletHelper.doesUserOutletExist(userId, outletId)) {
	        	// If it does, then mark it as disabled
        		userOutlet.setAction(OutletConstant.UPDATE);
        	} else {
	        	// Otherwise, create it and mark it as disabled
        		// During creation of User-Outlet, iterate through Key-Value Pair and create each entry
        		userOutlet.setAction(OutletConstant.INSERT);
        	}
		
        	return UpsertUserOutletHelper.upsertUserOutlet(userOutlet);

		} catch (IllegalArgumentException iae) {
            String errorMessage = "disableOutletForUser() failed. " + 
						        	"\n " +
						        	"userOutlet = " +
						        	userOutlet +
						        	"\n " +
						            "Exception = " + 
									iae;
			LOGGER.error(errorMessage);
			
			SmvServiceException swe = new SmvServiceException();
			swe.setErrorCode(SmvErrorCode.OUTLET_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(SmvErrorCode.OUTLET_SERVICE_ILLEGAL_ARGUMENT_MSG + 
					errorMessage);
			throw swe;
		} catch (SmvServiceException swe) {
            String errorMessage = "disableOutletForUser() failed. " + 
						        	"\n " +
						        	"userOutlet = " +
						        	userOutlet +
						        	"\n " +
						            "Exception = " + 
						            swe;

			LOGGER.error(errorMessage);

            SmvServiceException wrappedSwe = new SmvServiceException();
            wrappedSwe.setErrorCode(SmvErrorCode.OUTLET_SERVICE_EXCEPTION);
            wrappedSwe.setErrorMessage(SmvErrorCode.OUTLET_SERVICE_EXCEPTION_MSG +
            		errorMessage +
            		swe);

			throw wrappedSwe;
        } catch (Exception exception) {
            String errorMessage = "performOutletForUserWork() failed. " + 
					            	"\n " +
					            	"userOutlet = " +
					            	userOutlet +
					            	"\n " +
					            	"statusType = " +
					            	statusType +
					            	"\n " +
					                "Exception = " + 
					                exception;

            LOGGER.error(errorMessage);

            SmvServiceException swe = new SmvServiceException();
            swe.setErrorCode(SmvErrorCode.OUTLET_SERVICE_EXCEPTION);
            swe.setErrorMessage(SmvErrorCode.OUTLET_SERVICE_EXCEPTION_MSG +
                exception);

            throw swe;
        }
    }
}

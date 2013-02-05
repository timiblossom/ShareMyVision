package com.smv.service.outlet.helper;

import com.smv.common.bean.UserOutletDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;

import com.smv.service.outlet.db.dbobject.OutletStatusTypeDO;

import org.apache.log4j.Logger;

/**
 * @author TriNguyen
 *
 */
public class DisableOutletForUserHelper {

	private static final Logger LOGGER = Logger.getLogger(DisableOutletForUserHelper.class);

    protected DisableOutletForUserHelper() {
    }
    
	public static UserOutletDTO disableOutletForUser(UserOutletDTO userOutlet) 
	throws SmvServiceException, 
			IllegalArgumentException, 
			Exception 
	{
		try {
			return OutletForUserHelper.performOutletForUserWork(userOutlet, OutletStatusTypeDO.INACTIVE);
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
            String errorMessage = "disableOutletForUser() failed. " + 
					            	"\n " +
					            	"userOutlet = " +
					            	userOutlet +
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

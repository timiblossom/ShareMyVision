package com.smv.service.outlet.helper;

import com.smv.common.bean.OutletDTO;
import com.smv.common.bean.UserOutletContentDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.outlet.adaptor.DispatchFactory;
import com.smv.service.outlet.adaptor.IDispatch;

import org.apache.log4j.Logger;

/**
 * @author TriNguyen
 *
 */
public class PublishHelper {

	private static final Logger LOGGER = Logger.getLogger(PublishHelper.class);

    protected PublishHelper() {
    }
    
	public static Boolean publishToOutlets(Long userId, 
			UserOutletContentDTO content,
			OutletDTO[] outlets) throws SmvServiceException, Exception {
        try {
        	// Basic non-null parameter checking
        	checkParameters(userId, content);
        	
        	// Verify Outlet exists
        	if ((outlets != null) && (outlets.length > 0)) {
        		for (OutletDTO outlet : outlets) {
                	if ((outlet != null) && (outlet.getId() != null)) {
                    	OutletHelper.checkOutletTypeExists(outlet.getId());
                	}
        		}
        	}

			IDispatch dispatcher = DispatchFactory.getDispatchInstance();

			// Perform publish work by loading up multiplexing, switching table map
        	// Call adaptee based on that table map
        	if (((outlets != null) && (outlets.length > 1)) || ((outlets != null) && (outlets.length == 1) && (outlets[0].getId() != null))) {
        		
        		for (OutletDTO outlet : outlets) {
        			// TODO: Fill in with adaptor pattern work.
            		// TODO: Verify that user has established relationship/association between Outlet Type and him.
            		// TODO: Verify following: if ((outlet != null) && (outlet.getId() != null))
        			String message = "Publishing: " +
										"\n" +
										"userId = " + 
										userId +
										"\n" +
										"content = " + 
										content +
										"\n" +
										"to outlet = " + 
										outlet
										;
        			LOGGER.info(message);
        			dispatcher.dispatchToSingleOutlet(userId, content, outlet);
        		}
        	} else {
        		
        		// Publish to all outlets
        		// TODO: Fill in with adaptor pattern work.
        		// TODO: Verify that user has established relationship/association between Outlet Type and him.
    			String message = "Publishing: " +
									"\n" +
									"userId = " + 
									userId +
									"\n" +
									"content = " + 
									content +
									"\n" +
									"to all outlets."
									;
    			LOGGER.info(message);
    			OutletDTO[] allOutlets = GetOutletHelper.getOutlet();
    			dispatcher.dispatchToMultipleOutlets(userId, content, allOutlets);
        	}
        		
        	return Boolean.TRUE;

        } catch (Exception exception) {
    		
            String errorMessage = "disableOutletForUser() failed. " + 
            	"\n " +
            	"userId = " +
            	userId +
            	"\n " +
            	"content = " +
            	content +
            	"\n " +
            	"outlets = " +
            	outlets +
            	"\n " +
                "Exception = " + exception;

            LOGGER.error(errorMessage);

            SmvServiceException swe = new SmvServiceException();
            swe.setErrorCode(SmvErrorCode.OUTLET_SERVICE_EXCEPTION);
            swe.setErrorMessage(SmvErrorCode.OUTLET_SERVICE_EXCEPTION_MSG +
                exception);

            throw swe;
        }
	}
	
	protected static void checkParameters(Long userId, UserOutletContentDTO content) 
		throws IllegalArgumentException {
		if ((userId == null) || (content == null)) {
			String errorMessage = "Parameter(s) to Publish() is(are) null." +
									"\n" +
									"userId = " + 
									userId +
									"\n" +
									"content = " + 
									content
									;
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

}

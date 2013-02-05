package com.smv.service.outlet.adaptor;

import org.apache.log4j.Logger;

import com.smv.common.bean.OutletDTO;
import com.smv.common.bean.UserOutletContentDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.outlet.adaptee.facebook.FacebookPublisher;
import com.smv.service.outlet.adaptee.smv.ShareMyVisionPublisher;

/**
 * @author TriNguyen
 *
 */
public class BasicMultiplexor implements IDispatch {

	protected static final Logger LOGGER = Logger.getLogger(BasicMultiplexor.class);
	protected static final String SHARE_MY_VISION_OUTLET_NAME = "ShareMyVision";
	protected static final int SHARE_MY_VISION_OUTLET_ID = 1;
	protected static final String FACEBOOK_OUTLET_NAME = "Facebook";
	protected static final int FACEBOOK_OUTLET_ID = 2;
	
    public BasicMultiplexor() {
    }
 
	/* (non-Javadoc)
	 * @see com.smv.service.outlet.adaptor.IDispatch#dispatchToMultipleOutlets(java.lang.Long, com.smv.common.bean.UserOutletContentDTO, com.smv.common.bean.OutletDTO[])
	 */
	@Override
	public Boolean dispatchToMultipleOutlets(Long userId, 
												UserOutletContentDTO content, 
												OutletDTO[] outlets) 
	throws SmvServiceException, 
			Exception 
	{
		for (OutletDTO outlet : outlets) {
			if (dispatchToSingleOutlet(userId, content, outlet) != true) {
				return Boolean.FALSE;
			}
		}
		
		return Boolean.TRUE;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.adaptor.IDispatch#dispatchToSingleOutlet(java.lang.Long, com.smv.common.bean.UserOutletContentDTO, com.smv.common.bean.OutletDTO)
	 */
	public Boolean dispatchToSingleOutlet(Long userId, 
											UserOutletContentDTO content, 
											OutletDTO outlet) 
	throws SmvServiceException, 
			Exception 
	{
		if ((outlet.getName().equals(BasicMultiplexor.SHARE_MY_VISION_OUTLET_NAME)) || 
				(outlet.getId() == BasicMultiplexor.SHARE_MY_VISION_OUTLET_ID)) 
			{
				ShareMyVisionPublisher.getInstance().publish(userId, content);
			} 
			// Facebook Outlet specified
			// So dispatch to Facebook Publisher adaptee
			else if ((outlet.getName().equals(BasicMultiplexor.FACEBOOK_OUTLET_NAME)) || 
				(outlet.getId() == BasicMultiplexor.FACEBOOK_OUTLET_ID)) 
			{
				FacebookPublisher.getInstance().publish(userId, content);
			} 
			// Outlet specified does not match to any that we know of
			// So throw Exception
			else {
	            String errorMessage = "BasicMultiplexor.dispatchToOutlets() failed. " +
							        	"\n " +
							        	"userId = " +
							        	userId +
							        	"\n " +
							        	"content = " +
							        	content +
							        	"\n " +
							        	"outlet = " +
							        	outlet +							        	
							        	"\n " +
							        	"Unknown outlet name = " +
							        	outlet.getName()
							        	;
	            LOGGER.error(errorMessage);

				SmvServiceException swe = new SmvServiceException();
				swe.setErrorCode(SmvErrorCode.OUTLET_SERVICE_ILLEGAL_ARGUMENT);
				swe.setErrorMessage(SmvErrorCode.OUTLET_SERVICE_ILLEGAL_ARGUMENT_MSG + 
				errorMessage);
				throw swe;
			}

		return Boolean.TRUE;
	}

}

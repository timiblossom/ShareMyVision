/**
 * 
 */
package com.smv.service.outlet.adaptee.facebook;

import org.apache.log4j.Logger;

import com.smv.common.bean.UserOutletContentDTO;
import com.smv.service.outlet.adaptee.IPublish;

/**
 * @author TriNguyen
 *
 */
public class FacebookPublisher implements IPublish {

	protected static final Logger LOGGER = Logger.getLogger(FacebookPublisher.class);
    private static final FacebookPublisher INSTANCE = new FacebookPublisher();
 
    // Private constructor prevents instantiation from other classes
    private FacebookPublisher() {
    }
 
    public static FacebookPublisher getInstance() {
        return INSTANCE;
    }

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.adaptee.IPublish#publish(java.lang.Long, com.smv.common.bean.UserOutletContentDTO)
	 */
	@Override
	public Boolean publish(Long userId, UserOutletContentDTO content)
			throws Exception {
		// TODO Fill in with real publishing body implementation for Facebook
		String message = "Mock publishing to Facebook. " +
					    	"\n " +
					    	"userId = " +
					    	userId +
					    	"\n " +
					    	"content = " +
					    	content
					    	;
		LOGGER.info(message);
		return Boolean.TRUE;
	}

}

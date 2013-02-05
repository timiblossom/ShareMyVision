/**
 * 
 */
package com.smv.service.outlet.adaptor;

import org.apache.log4j.Logger;

/**
 * @author TriNguyen
 *
 */
public class DispatchFactory {

	private static final Logger LOGGER = Logger.getLogger(DispatchFactory.class);
	private static IDispatch dispatchInstance = new BasicMultiplexor();	
	
	public DispatchFactory() {}
	
	public static IDispatch getDispatchInstance() {
		String message = "Get IDispatch in DispatchFactory.";
		LOGGER.info(message);
		return dispatchInstance;
	}
}

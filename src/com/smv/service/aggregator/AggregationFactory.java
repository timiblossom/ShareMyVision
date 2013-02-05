package com.smv.service.aggregator;

import org.apache.log4j.Logger;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class AggregationFactory {
	private static final Logger LOGGER = Logger.getLogger(AggregationFactory.class);
	private static IAggregation aggregationInstance = new AggregationImpl();	
	
	public AggregationFactory() {}
	
	public static IAggregation getAggregationInstance() {
		return aggregationInstance;
	}
	
	
}

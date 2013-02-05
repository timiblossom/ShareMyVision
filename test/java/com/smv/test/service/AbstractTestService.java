/**
 * 
 */
package com.smv.test.service;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import junit.framework.TestCase;

/**
 * @author Tri Nguyen
 *
 */
public abstract class AbstractTestService extends TestCase {

	protected static Logger logger = Logger.getLogger(AbstractTestService.class);

	public AbstractTestService(String name) {
		super(name);
		PropertyConfigurator.configure("log4j.properties"); 
	}
	
	public static Logger getLogger() {
		return AbstractTestService.logger;
	}

	public static void setLogger(Logger logger) {
		PropertyConfigurator.configure("log4j.properties"); 
		AbstractTestService.logger = logger;
	}

	public static void log(Object object) {
		//System.out.println(object);
		AbstractTestService.getLogger().log(Level.INFO, object);
	}
	
	public static void log(Object object, Level level) {
		//System.out.println(object);
		AbstractTestService.getLogger().log(level, object);
	}
	
}

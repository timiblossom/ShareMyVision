/**
 * 
 */
package com.smv.test.dao;

import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public abstract class AbstractTestDAO {

	protected static Logger log = Logger.getLogger(AbstractTestDAO.class);;
	protected static Random rand = new Random();

	/**
	 * @return the log
	 */
	public static Logger getLogger() {
		return AbstractTestDAO.log;
	}

	/**
	 * @param log the log to set
	 */
	public static void setLogger(Logger log) {
		PropertyConfigurator.configure("log4j.properties"); 
		AbstractTestDAO.log = log;
	}

	/**
	 * @return the randomNumberGenerator
	 */
	public static Random getRandomNumberGenerator() {
		return AbstractTestDAO.rand;
	}

	/**
	 * @param randomNumberGenerator the randomNumberGenerator to set
	 */
	public static void setRandomNumberGenerator(Random randomNumberGenerator) {
		AbstractTestDAO.rand = randomNumberGenerator;
	}

	public static void log(Object object) {
		AbstractTestDAO.getLogger().log(Level.INFO, object);
	}
	

	public static void log(Object object, Level level) {
		AbstractTestDAO.getLogger().log(level, object);
	}
	
}

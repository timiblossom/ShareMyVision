package com.smv.util.cache.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.smv.util.cache.ICache;




/**
 * @author Minh Do
 */

public class MockMemcache implements ICache {
	private static final Logger log = Logger.getLogger(MockMemcache.class);

	public MockMemcache() {	
	}

	public Object get(String key) {		
		log.debug("MockMemcache");
		return null;
	}


	public Map<String, Object> getMulti(String... keys) {		
		log.debug("MockMemcache.getMulti");
		return null;
	}



	public void set(String key, Object value, int cacheTimeSeconds) {		
		log.debug("MockMemcache.put with timeout");
	}

	@Override
	public void set(String key, Object value) {	
		log.debug("MockMemcache.put");
	}

	public boolean append(String key, Object value) {
		log.debug("MockMemcache.append");
		return true;
	}

	public void incr(String key, int factor, int startingValue) {
		log.debug("MockMemcache.incr");
	}

	public void shutdown() {
		log.debug("MockMemcache.shutdown");
	}

	@Override
	public void flush() {	
		log.debug("MockMemcache.flush");
	}


	@Override
	public void remove(String key) {	
		log.debug("MockMemcache.remove");
	}




	@Override
	public void replace(String key, Object value, int expTimeInSec) {
		// TODO Auto-generated method stub
		
	}


}

package com.smv.util.cache;

import java.util.Map;



/**
 * @author Minh Do
 */
public interface ICache {
		
	public Object get(String key);	
	
	public Map<String, Object> getMulti(String... keys);		

	public void set(String key, Object value, int cacheTimeSeconds);
	
	public void set(String key, Object value);
	
	public boolean append(String key, Object value);
	
	public void remove(String key);		
	
	public void replace(String key, Object value, int expTimeInSec);
	
	public void flush();
	
	public void shutdown();

}







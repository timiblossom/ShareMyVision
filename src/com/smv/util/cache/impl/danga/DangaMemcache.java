package com.smv.util.cache.impl.danga;


import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.smv.util.cache.CacheConfig;
import com.smv.util.cache.ICache;
import com.smv.util.cache.helper.BinarySerializer;
import com.smv.util.cache.helper.XmlSerializer;

/**
 * @author Minh Do
 */
public class DangaMemcache implements ICache {
	private static final Logger log = LoggerFactory.getLogger(DangaMemcache.class);

	private final MemCachedClient memcachedClient;
	private final String poolName;
	private boolean isBinarySerializerEnable = false;
	private int defaultCacheExpiration = CacheConfig.DEFAULT_CACHE_EXPIRATION; 
	private boolean isTimingLogEnable = false;
	private long windowTimeoutCheckInMillis = CacheConfig.DEFAULT_WINDOW_TIMEOUT_CHECK_MILLIS;
	private long backoffTimeInMillis = CacheConfig.DEFAULT_CACHE_BACKOFF_TIME_MILLIS;

	//private MemcacheExceptionHandler exceptionHandler = new LoggingMemcacheExceptionHandler();

	/* Constructor
	 *
	 * @param memcachedClient Instance of Danga's MemCachedClient
	 * @param poolName SockIOPool name used to instantiate memcachedClient
	 */
	public DangaMemcache(MemCachedClient memcachedClient, String poolName) {
		this.memcachedClient = memcachedClient;
		this.poolName = poolName;
	}

	public Object get(String key) {
		try {
			log.debug("MemCachedClient.get({})", key);
			return memcachedObjectToJavaObject(memcachedClient.get(key, SockIOPool.NEW_COMPAT_HASH));

		} catch (Exception e) {
			e.printStackTrace();
			// exceptionHandler.handleErrorOnGet(key, e);
		}
		return null;
	}

	public Map<String, Object> getMulti(String... keys) {
		try {
			return memcachedClient.getMulti(keys);
		} catch (Exception e) {
			e.printStackTrace();
			//exceptionHandler.handleErrorOnGet(StringUtils.join(keys, ", "), e);
		}
		return null;
	}

	

	public void delete(String key) {
		try {
			memcachedClient.delete(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void incr(String key, int factor, int startingValue) {
		try {
			//Try to incr
			long rv = memcachedClient.incr(key, factor);

			//If the key is not found, add it with startingValue
			if (-1 == rv)
				memcachedClient.addOrIncr(key, startingValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void shutdown() {
		log.debug("Shutting down danga MemCachedClient");

		//Danga's MemCachedClient does not provide a method to shutdown or
		//close it, let's shutdown its SockIOPool instead
		SockIOPool.getInstance(poolName).shutDown();
	}

	@Override
	public boolean append(String key, Object value) {
		return false;
	}

	@Override
	public void flush() {
		memcachedClient.flushAll();
	}


	@Override
	public void set(String key, Object value, int cacheTimeSeconds) {
		if (value == null)
			return;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.SECOND, cacheTimeSeconds);
		
		memcachedClient.set(key, javaObjectToMemcachedObject(value), calendar.getTime(), SockIOPool.NEW_COMPAT_HASH);

	}

	@Override
	public void set(String key, Object value){
		if (value == null)
			return;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.SECOND, defaultCacheExpiration);
		
		memcachedClient.set(key, javaObjectToMemcachedObject(value), calendar.getTime(), SockIOPool.NEW_COMPAT_HASH);

	}

	@Override
	public void remove(String key) {
		memcachedClient.delete(key);
	}			

	@Override
	public void replace(String key, Object value, int expTimeInSec) {
		// TODO Auto-generated method stub
		
	}
	
	private Object memcachedObjectToJavaObject(Object obj) {
		if (obj == null)
			return null;

		if (this.isBinarySerializerEnable) {
			return BinarySerializer.getInstance().toObject((byte[]) obj);
		}

		return XmlSerializer.getInstance().fromXml((String) obj);
	}

	private Object javaObjectToMemcachedObject(Object obj) {
		if (this.isBinarySerializerEnable) {
			return BinarySerializer.getInstance().fromObject(obj);
		}
		String a = XmlSerializer.getInstance().toXml(obj);
		return XmlSerializer.getInstance().toXml(obj);
	}


}

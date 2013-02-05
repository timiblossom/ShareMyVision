package com.smv.util.cache;

import java.util.Properties;



/**
 * @author Minh Do
 */
public class CacheConfig {
	
	public static final String CACHE_CLIENT_FACTORY_NAME                 = "smv.cacheClientFactoryName";
	public static final String CACHE_ENABLE                              = "smv.cacheEnable";
	public static final String BINARY_SERIALIZER                         = "smv.binarySerializerEnable";
	public static final String KEY_STRATEGY                              = "smv.keyStrategy";
	public static final String DEFAULT_CACHE_EXPIRATION_IN_SECONDS       = "smv.defaultCacheExpirationInSeconds";
	public static final String SERVER_LIST                               = "smv.serverList";
	public static final String CACHE_ENABLE_TIMING_LOG                   = "smv.enableTimingLog";
	public static final String WINDOW_TIMEOUT_CHECK_MILLIS               = "smv.windowTimeoutCheckInMillis";
	public static final String BACKOFF_TIME_MILLIS                       = "smv.backoffTimeInMillis";
	public static final String COMPRESS_ENABLE                           = "smv.compressEnable";
	
	

	
	//DangaMemcached
	public static final String DANGA_DEFAULT_ENCODING                     = "smv.danga.defaultEncoding";
	public static final String DANGA_POOL_NAME                            = "smv.danga.poolName";
	public static final String DANGA_ERROR_HANDLER                        = "smv.danga.errorHandler";
	public static final String DANGA_WEIGHTS                              = "smv.danga.weights";
	public static final String DANGA_INIT_CONN                            = "smv.danga.initConn";
	public static final String DANGA_MIN_CONN                             = "smv.danga.minConn";
	public static final String DANGA_MAX_CONN                             = "smv.danga.maxConn";
	public static final String DANGA_MAX_IDLE                             = "smv.danga.maxIdle";
	public static final String DANGA_MAINT_SLEEP                          = "smv.danga.maintSleep";
	public static final String DANGA_SOCKET_TIMEOUT                       = "smv.danga.socketTimeout";
	public static final String DANGA_SOCKET_CONNECT_TIMEOUT               = "smv.danga.socketConnectTimeout";
	
	

	
	
	public static final String DEFAULT_CACHE_CLIENT_FACTORY_NAME = "com.smv.util.cache.impl.danga.DangaMemcacheFactory";
    public static final int DEFAULT_CACHE_EXPIRATION      = 10800; //in seconds - default to 3 hours
    public static final long DEFAULT_WINDOW_TIMEOUT_CHECK_MILLIS = 1000;
    public static final long DEFAULT_CACHE_BACKOFF_TIME_MILLIS   = 120000;  //2 minutes
    
    //Default stuff for danga
    public static final boolean DEFAULT_COMPRESS_ENABLE = true;
    public static final String DEFAULT_DEFAULT_ENCODING = "UTF-8";
    public static final String DEFAULT_POOL_NAME = "default";
    public static final String DEFAULT_ERROR_HANDLER = "com.smv.util.cache.impl.danga.SimpleErrorHandler";
    public static final String DEFAULT_SERVERS = "localhost:11211";
    public static final int DEFAULT_INIT_CONN = 1;
    public static final int DEFAULT_MIN_CONN = 1;
    public static final int DEFAULT_MAX_CONN = 10;
    public static final int DEFAULT_MAX_IDLE = 1000 * 60 * 5; //5 minutes
    public static final int DEFAULT_MAINT_SLEEP = 1000 * 30;     //30 seconds
    public static final int DEFAULT_SOCKET_TIMEOUT = 1000 * 30;     //30 seconds
    public static final int DEFAULT_SOCKET_CONNECT_TIMEOUT = 1000 * 3;      //3 seconds
    
    
 
	public static Properties getDefaultCacheProperties() {
		final Properties properties = new Properties();
		
		properties.setProperty(CACHE_CLIENT_FACTORY_NAME, "com.smv.util.cache.impl.danga.DangaMemcacheFactory");

		properties.setProperty(SERVER_LIST, "locahost:11211");
		properties.setProperty(CACHE_ENABLE, "true");
		properties.setProperty(BINARY_SERIALIZER, "false");
		properties.setProperty(DEFAULT_CACHE_EXPIRATION_IN_SECONDS, "500");
		properties.setProperty(CACHE_ENABLE_TIMING_LOG, "false");
		properties.setProperty(WINDOW_TIMEOUT_CHECK_MILLIS, "1001");
		properties.setProperty(BACKOFF_TIME_MILLIS, "60001");
		properties.setProperty(COMPRESS_ENABLE, "false");
		

		properties.setProperty(DANGA_INIT_CONN, "5");
		properties.setProperty(DANGA_MIN_CONN, "5");
		properties.setProperty(DANGA_MAX_CONN, "10");
		properties.setProperty(DANGA_MAX_IDLE, "10");
		properties.setProperty(DANGA_MAINT_SLEEP, "30");
		properties.setProperty(DANGA_SOCKET_TIMEOUT, "30000");
		properties.setProperty(DANGA_SOCKET_CONNECT_TIMEOUT, "3000");
		properties.setProperty(COMPRESS_ENABLE, "false");
		properties.setProperty(DANGA_DEFAULT_ENCODING, "UTF-8");
		properties.setProperty(DANGA_POOL_NAME, "Danga Pool");
		

		return properties;
	}
	
	
}

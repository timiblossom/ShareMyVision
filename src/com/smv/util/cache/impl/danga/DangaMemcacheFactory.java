package com.smv.util.cache.impl.danga;


import java.util.Properties;

import org.hibernate.cache.CacheException;

import com.danga.MemCached.ErrorHandler;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.smv.util.cache.CacheConfig;
import com.smv.util.cache.ICache;
import com.smv.util.cache.ICacheFactory;
import com.smv.util.cache.helper.PropertiesHelper;
import com.smv.util.cache.impl.MockMemcache;

/**
 * @author Minh Do
 */
public class DangaMemcacheFactory implements ICacheFactory {

	public static final String PROP_CACHE_ENABLE                 = CacheConfig.CACHE_ENABLE;	
    public static final String PROP_COMPRESS_ENABLE              = CacheConfig.COMPRESS_ENABLE;
    public static final String PROP_DEFAULT_ENCODING             = CacheConfig.DANGA_DEFAULT_ENCODING;
    public static final String PROP_POOL_NAME                    = CacheConfig.DANGA_POOL_NAME;
    public static final String PROP_ERROR_HANDLER                = CacheConfig.DANGA_ERROR_HANDLER;
    public static final String PROP_SERVERS                      = CacheConfig.SERVER_LIST;
    public static final String PROP_WEIGHTS                      = CacheConfig.DANGA_WEIGHTS;
    public static final String PROP_INIT_CONN                    = CacheConfig.DANGA_INIT_CONN;
    public static final String PROP_MIN_CONN                     = CacheConfig.DANGA_MIN_CONN;
    public static final String PROP_MAX_CONN                     = CacheConfig.DANGA_MAX_CONN;
    public static final String PROP_MAX_IDLE                     = CacheConfig.DANGA_MAX_IDLE;
    public static final String PROP_MAINT_SLEEP                  = CacheConfig.DANGA_MAINT_SLEEP;
    public static final String PROP_SOCKET_TIMEOUT               = CacheConfig.DANGA_SOCKET_TIMEOUT;
    public static final String PROP_SOCKET_CONNECT_TIMEOUT       = CacheConfig.DANGA_SOCKET_CONNECT_TIMEOUT;
    public static final String PROP_CACHE_ENABLE_TIMING_LOG      = CacheConfig.CACHE_ENABLE_TIMING_LOG;
    public static final String PROP_WINDOW_TIMEOUT_CHECK_MILLIS  = CacheConfig.WINDOW_TIMEOUT_CHECK_MILLIS; 
    public static final String PROP_BACKOF_TIME_MILLIS           = CacheConfig.BACKOFF_TIME_MILLIS;
    

    private Properties properties;

    public DangaMemcacheFactory(Properties properties) {
        this.properties = properties;
    }
        
    public ICache createCache() throws Exception {

    	if (this.isCacheEnable()) {
    		String poolName = getPoolName();

    		// grab an instance of our connection pool
    		SockIOPool pool = SockIOPool.getInstance(poolName);

    		// set the servers and the weights
    		pool.setServers(getServers());
    		pool.setWeights(getWeights());

    		// set some basic pool settings
    		pool.setInitConn(getInitConn());
    		pool.setMinConn(getMinConn());
    		pool.setMaxConn(getMaxConn());
    		pool.setMaxIdle(getMaxIdle());
    		pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);

    		// set the sleep for the maint thread
    		// it will wake up every x seconds and
    		// maintain the pool size
    		pool.setMaintSleep(getMaintSleep());

    		// set some TCP settings
    		pool.setNagle(false);
    		pool.setSocketTO(getSocketTimeout());
    		pool.setSocketConnectTO(getSocketConnectTimeout());

    		// initialize the connection pool
    		pool.initialize();

    		MemCachedClient client = new MemCachedClient(getClassLoader(),
    				getErrorHandler(),
    				poolName);
    		client.setCompressEnable(isCompressEnable());
    		client.setDefaultEncoding(getDefaultEncoding());

    		return new DangaMemcache(client, poolName);
    	}
    	
    	return new MockMemcache();
    }

    public ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public boolean isCompressEnable() {
        return PropertiesHelper.getBoolean(properties, PROP_COMPRESS_ENABLE, CacheConfig.DEFAULT_COMPRESS_ENABLE);
    }

    public String getDefaultEncoding() {
        return PropertiesHelper.get(properties, PROP_DEFAULT_ENCODING, CacheConfig.DEFAULT_DEFAULT_ENCODING);
    }

    public String getPoolName() {
        return PropertiesHelper.get(properties, PROP_POOL_NAME, CacheConfig.DEFAULT_POOL_NAME);
    }

    public ErrorHandler getErrorHandler() {
        String errorHandlerName =  PropertiesHelper.get(properties, PROP_ERROR_HANDLER, CacheConfig.DEFAULT_ERROR_HANDLER);

        ErrorHandler errorHandler;
        try {
            errorHandler =
                    (ErrorHandler) Class.forName(errorHandlerName).newInstance();
        } catch (ClassNotFoundException e) {
            throw new CacheException(
                    "Unable to find error handler class [" + errorHandlerName + "]", e);
        } catch (IllegalAccessException e) {
            throw new CacheException(
                    "Illegally accessed error handler class [" + errorHandlerName + "]", e);
        } catch (InstantiationException e) {
            throw new CacheException(
                    "Failed to instantiate error handler class [" + errorHandlerName + "]", e);
        }

        return errorHandler;
    }

    public String[] getServers() {
        return PropertiesHelper.get(properties, PROP_SERVERS, CacheConfig.DEFAULT_SERVERS).split(" ");
    }

    public Integer[] getWeights() {
        String[] servers = getServers();
        Integer[] weights = new Integer[servers.length];
        String weightsValue = PropertiesHelper.get(properties, PROP_WEIGHTS);

        if (weightsValue == null || "".equals(weightsValue)) {
            for (int i = 0; i < weights.length; i++)
                weights[i] = 1;
        } else {
            String[] weightsStrings = weightsValue.split(" ");
            if (weightsStrings.length == servers.length) {
                for (int i = 0; i < weights.length; i++)
                    weights[i] = new Integer(weightsStrings[i]);
            } else
                throw new CacheException(
                        "Count of weight number mismatch count of server");
        }

        return weights;
    }

    public int getInitConn() {
        return PropertiesHelper.getInt(properties, PROP_INIT_CONN, CacheConfig.DEFAULT_INIT_CONN);
    }

    public int getMinConn() {
        return PropertiesHelper.getInt(properties, PROP_MIN_CONN, CacheConfig.DEFAULT_MIN_CONN);
    }

    public int getMaxConn() {
        return PropertiesHelper.getInt(properties, PROP_MAX_CONN, CacheConfig.DEFAULT_MAX_CONN);
    }

    public int getMaxIdle() {
        return PropertiesHelper.getInt(properties, PROP_MAX_IDLE, CacheConfig.DEFAULT_MAX_IDLE);
    }

    public int getMaintSleep() {
        return PropertiesHelper.getInt(properties, PROP_MAINT_SLEEP, CacheConfig.DEFAULT_MAINT_SLEEP);
    }

    public int getSocketTimeout() {
        return PropertiesHelper.getInt(properties, PROP_SOCKET_TIMEOUT, CacheConfig.DEFAULT_SOCKET_TIMEOUT);
    }

    public int getSocketConnectTimeout() {
        return PropertiesHelper.getInt(properties, PROP_SOCKET_CONNECT_TIMEOUT, CacheConfig.DEFAULT_SOCKET_CONNECT_TIMEOUT);
    }
    
    public boolean isCacheEnable() {
        return PropertiesHelper.getBoolean(properties, PROP_CACHE_ENABLE, false);
    }
    
    public boolean isCacheTimingLogEnable() {
        return PropertiesHelper.getBoolean(properties, PROP_CACHE_ENABLE_TIMING_LOG, false);
    }
    
    public long getWindowTimeoutCheckMillis() { 
    	return PropertiesHelper.getLong(properties, PROP_WINDOW_TIMEOUT_CHECK_MILLIS, CacheConfig.DEFAULT_WINDOW_TIMEOUT_CHECK_MILLIS);
    }
        
    public long getBackoffTimeMillis() { 
    	return PropertiesHelper.getLong(properties, PROP_BACKOF_TIME_MILLIS, CacheConfig.DEFAULT_CACHE_BACKOFF_TIME_MILLIS);
    }
}

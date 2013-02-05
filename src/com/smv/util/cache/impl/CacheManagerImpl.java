package com.smv.util.cache.impl;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.hibernate.cache.CacheException;

import com.smv.util.cache.CacheConfig;
import com.smv.util.cache.ICache;
import com.smv.util.cache.ICacheFactory;
import com.smv.util.cache.helper.PropertiesHelper;


/**
 * @author Minh Do
 */
public class CacheManagerImpl {

	private static final Logger log = Logger.getLogger(CacheManagerImpl.class);
	private static final String DEFAULT_CACHE_NAME = "SMV_CACHE";
    
	private static CacheManagerImpl DEFAULT_CACHE_MANAGER = null;
	private final Map<String, ICache> smvCaches = new ConcurrentHashMap<String, ICache>();

	private ICacheFactory cacheClientFactory;
	private Properties cacheProperties;
	
	
	private CacheManagerImpl() {
		this(CacheConfig.getDefaultCacheProperties());
	}
	
	public CacheManagerImpl(Properties properties) {
		cacheProperties = properties;
		init();
	}
	
	public static CacheManagerImpl getDefaultCacheManager() {
		if (DEFAULT_CACHE_MANAGER == null) {
			DEFAULT_CACHE_MANAGER = new CacheManagerImpl();
		}
		
		return DEFAULT_CACHE_MANAGER;
	}

		
	private void init() {
		log.info("Starting MemcachedClient...");
        try {
        	cacheClientFactory = getCachedClientFactory();
        } catch (Exception e) {
            throw new CacheException("Unable to initialize MemcachedClient", e);
        }
	}
	
	
	private ICacheFactory getCachedClientFactory() {        
		String cacheClientFactoryName = PropertiesHelper.get(cacheProperties, CacheConfig.CACHE_CLIENT_FACTORY_NAME, CacheConfig.DEFAULT_CACHE_CLIENT_FACTORY_NAME);

        ICacheFactory clientFactory;
        Constructor constructor;
        
        try {
            constructor = Class.forName(cacheClientFactoryName).getConstructor(Properties.class);       
            clientFactory = (ICacheFactory) constructor.newInstance(getCacheProperties());
        } catch (ClassNotFoundException e) {
            throw new CacheException("Unable to find factory class [" + cacheClientFactoryName + "]", e);
        } catch (NoSuchMethodException e) {
            throw new CacheException("Unable to find PropertiesHelper constructor for factory class [" + cacheClientFactoryName + "]", e);        
        } catch (Exception e) {
            throw new CacheException("Unable to instantiate factory class [" + cacheClientFactoryName + "]", e);
        }

        return clientFactory;
    }

	
	public ICache getCache(String cacheName) {
		ICache cache = null;
		
		if (cacheName == null || cacheName.length() == 0) {
            cacheName = DEFAULT_CACHE_NAME;
        }
		
		if (smvCaches.get(cacheName) != null) {
            return smvCaches.get(cacheName);
        }
		
		try {
			if (cacheClientFactory == null) {
				init();
			}
			cache = cacheClientFactory.createCache();
			smvCaches.put(cacheName, cache);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cache;
	}

	
	public ICache getCache() {
		return getCache(DEFAULT_CACHE_NAME);
	}
	
	public void shutdown() {
        synchronized (CacheManagerImpl.class) {
           Iterator cacheIter = smvCaches.entrySet().iterator();
           
           while (cacheIter.hasNext()) {
        	   Map.Entry<String, ICache> entry = (Map.Entry<String, ICache>) cacheIter.next();
        	   
        	   System.out.println("Shutting Down Cache [" + entry.getKey() + "] ....");
        	   entry.getValue().shutdown();
           }
                      
        }
    }


	public void setCacheProperties(Properties cacheProperties) {
		this.cacheProperties = cacheProperties;
	}


	public Properties getCacheProperties() {
		if (this.cacheProperties == null) {
			this.cacheProperties = new Properties();
		}
		return cacheProperties;
	}

	
}

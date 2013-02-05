package com.smv.util.thread;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * hold maximum one instance of a particular class per thread.
 * 
 * @author Minh Do
 */
public class LocalThreadObjectsHolder {

	private final static Logger LOGGER = Logger.getLogger(LocalThreadObjectsHolder.class);
	private final static ThreadLocal<Map<Class<?>, Object>> holder = new ThreadLocal<Map<Class<?>, Object>>();
	private final static Map<String, AbstractBaseGenerator> generators = new HashMap<String, AbstractBaseGenerator>();
	private final static ThreadLocal<Map> generalContainer = new ThreadLocal<Map>();

	public static void setInstanceGenerators(final List<AbstractBaseGenerator> list) {
		if (list == null) {
			return;
		}

		for (AbstractBaseGenerator gen : list) {
			generators.put(gen.getClassName(), gen);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("LocalThreadObjectsHolder::setInstanceGenerators: creating a new generator " + gen.getClassName());
			}
		}
	}

	public static void addInstanceGenerator(final AbstractBaseGenerator gen) {
		if (gen == null) {
			return;
		}

		generators.put(gen.getClassName(), gen);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("LocalThreadObjectsHolder::addInstanceGenerator: creating a new generator " + gen.getClassName());
		}
	}

	public static <T> T getThreadInstance(final Class<T> type) {
		if (type == null) {
			return null;
		}

		if (holder.get() == null) {
			holder.set(new HashMap<Class<?>, Object>());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("LocalThreadObjectsHolder::getThreadInstance: adding a new Hashmap ");
			}
		}

		Object instance = holder.get().get(type);
		if (instance == null) {
			try {
				if (generators.get(type.getName()) != null) {
					instance = generators.get(type.getName()).generateInstance();
					if (instance == null) {
						throw new IllegalArgumentException("Generator can't be used to generate an instance of class [" + type.getName() + "]");
					} else {
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("An instance of class [" + instance.getClass().getName() +
									"] is just generated for thread id [" +
									Thread.currentThread().getId() + "]");
						}
					}


					holder.get().put(type, instance);
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("LocalThreadObjectsHolder::getThreadInstance: adding a new type " + type.getCanonicalName());
					}
				} else {
					throw new NullPointerException("There is no InstanceGenerator associated with class type : " + type.getName());
				}
			} catch (Exception e) {
				//e.printStackTrace();
				LOGGER.error(e);
				return null;
			}

		}

		return type.cast(instance);
	}


	public static Object getLocalThreadObject(final Object key) {
		if (key == null) {
			return null;
		}

		if (generalContainer.get() == null) {
			generalContainer.set(new HashMap());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("LocalThreadObjectsHolder::getLocalThreadObject: creating a new HashMap ");
			}
		}

		return generalContainer.get().get(key);
	}


	public static void setLocalThreadObject(final Object key, final Object value) {
		if (key == null) {
			return;
		}

		if (generalContainer.get() == null) {
			generalContainer.set(new HashMap());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("LocalThreadObjectsHolder::setLocalThreadObject: adding a new Hashmap ");
			}
		}

		generalContainer.get().put(key, value);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("LocalThreadObjectsHolder::setLocalThreadObject: adding a new key " + key + ", generalContainer.get().size() " + generalContainer.get().size());
		}
	}

}
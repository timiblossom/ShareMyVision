package com.smv.util.cache.helper;


import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @author Minh Do
 */
public class PropertiesHelper {
	private static final Logger logger = Logger.getLogger(PropertiesHelper.class);
	
    public static String get(Properties properties, String key) {
        return properties.getProperty(key);
    }

    public static String get(Properties properties, String key, String defaultVal) {
        String val = get(properties, key);
        return val == null ? defaultVal : val;
    }

    public static String findValue(Properties properties, String... keys) {
        for (String key : keys) {
            String value = get(properties, key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    public static boolean getBoolean(Properties properties, String key, boolean defaultVal) {
        String val = get(properties, key);
        return val == null ? defaultVal : Boolean.parseBoolean(val);
    }

    public static long getLong(Properties properties, String key, long defaultVal) {
        String val = get(properties, key);
        return val == null ? defaultVal : Long.parseLong(val);
    }

    public static int getInt(Properties properties, String key, int defaultVal) {
        return (int) getLong(properties, key, defaultVal);
    }

    public static double getDouble(Properties properties, String key, double defaultVal) {
        String val = get(properties, key);
        return val == null ? defaultVal : Double.parseDouble(val);
    }

    public static <T extends Enum<T>> T getEnum(Properties properties, String key, Class<T> type, T defaultValue) {
        String val = get(properties, key);
        return val == null ? defaultValue : Enum.valueOf(type, val);
    }
}
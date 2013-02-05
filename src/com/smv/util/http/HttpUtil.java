package com.smv.util.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * @author Minh Do
 * 03/01/2010
 */
//TODO: should load this from properties files
public class HttpUtil {
	private static final Logger log = Logger.getLogger(HttpUtil.class);
	private static Map<String, Boolean> REGISTERED_HTTP_HEADER_MAP = new HashMap<String, Boolean>();
	
	static {
		REGISTERED_HTTP_HEADER_MAP.put("CONTENT_TYPE", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("CONTENT_LENGTH", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("REQUEST_METHOD", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("SCRIPT_NAME", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("REQUEST_URI", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("PATH_INFO", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("QUERY_STRING", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("SERVER_NAME", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("REMOTE_HOST", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("REMOTE_ADDR", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("REMOTE_USER", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("SERVER_PORT", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("HTTP_USER_AGENT", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("HTTP_ACCEPT", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("HTTP_ACCEPT_LANGUAGE", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("HTTP_ACCEPT_ENCODING", Boolean.TRUE);
		REGISTERED_HTTP_HEADER_MAP.put("HTTP_CACHE_CONTROL", Boolean.TRUE);		
		REGISTERED_HTTP_HEADER_MAP.put("SESSION", Boolean.TRUE);		
	}
	
	public static Map<String, Boolean> getRegisteredHttpHeaders() {
		return REGISTERED_HTTP_HEADER_MAP;
	}
}

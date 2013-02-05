package com.smv.service.iprocessor.config;

import com.smv.common.Constant;
import com.smv.service.file.IFileService;
import com.smv.util.config.ApplicationProperties;
import com.smv.util.spring.SpringBeanReader;

public class ResourceManager {
	private static ApplicationProperties APP_PROPERTIES;
	
	static {
		initApplication();
	}

	private static void initApplication() {
		APP_PROPERTIES = new ApplicationProperties("application.properties");
	}
	
	public static ApplicationProperties getApplicationProperties() {
		return APP_PROPERTIES;
	}
	
	public static IFileService getFileProxyService() {
		return (IFileService) SpringBeanReader.getInstance().getBeanById(Constant.FILE_PROXY);
	}
}

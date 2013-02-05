package com.smv.service.aggregator.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.ws.BindingProvider;

import com.google.gson.Gson;
import com.smv.common.Constant;
import com.smv.service.catalog.ICatalogService;
import com.smv.service.file.IFileService;
import com.smv.service.iprocessor.IImageService;
import com.smv.service.mailer.IMailerService;
import com.smv.service.outlet.IOutletService;
import com.smv.service.user.IUserService;
import com.smv.util.config.ApplicationProperties;
import com.smv.util.spring.SpringBeanReader;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class ResourceManager {
	private static ExecutorService EXECUTOR = null;
	private static Gson GSON = null;
	private static ApplicationProperties APP_PROPERTIES;
	
	static {
		initApplication();
	}

	private static void initApplication() {
		APP_PROPERTIES = new ApplicationProperties("application.properties");
		GSON = new Gson();
		EXECUTOR = Executors.newFixedThreadPool(Constant.NUM_ASYNC_THREADS);
		((BindingProvider) SpringBeanReader.getInstance().getBeanById(Constant.FILE_PROXY)).getRequestContext().put("thread.local.request.context", "true");
		((BindingProvider) SpringBeanReader.getInstance().getBeanById(Constant.USER_PROXY)).getRequestContext().put("thread.local.request.context", "true");
		((BindingProvider) SpringBeanReader.getInstance().getBeanById(Constant.CATALOG_PROXY)).getRequestContext().put("thread.local.request.context", "true");
		((BindingProvider) SpringBeanReader.getInstance().getBeanById(Constant.MAILER_PROXY)).getRequestContext().put("thread.local.request.context", "true");
		((BindingProvider) SpringBeanReader.getInstance().getBeanById(Constant.OUTLET_PROXY)).getRequestContext().put("thread.local.request.context", "true");
	}
	
	public static ApplicationProperties getApplicationProperties() {
		return APP_PROPERTIES;
	}
	
	public static Gson getGson() {
		return GSON;
	}
	
	public static ExecutorService getExecutor() {
		return EXECUTOR;
	}
	
	
	public static IFileService getFileProxyService() {
		return (IFileService) SpringBeanReader.getInstance().getBeanById(Constant.FILE_PROXY);
	}
	
	public static IImageService getImageProxyService() {
		return (IImageService) SpringBeanReader.getInstance().getBeanById(Constant.IMAGE_PROXY);
	}
	
	public static IUserService getUserProxyService() {
		return (IUserService) SpringBeanReader.getInstance().getBeanById(Constant.USER_PROXY);
	}
	
	public static IMailerService getMailerProxyService() {
		return (IMailerService) SpringBeanReader.getInstance().getBeanById(Constant.MAILER_PROXY);
	}

	public static ICatalogService getCatalogProxyService() {
		return (ICatalogService) SpringBeanReader.getInstance().getBeanById(Constant.CATALOG_PROXY);
	}

	public static IOutletService getOutletProxyService() {
		return (IOutletService) SpringBeanReader.getInstance().getBeanById(Constant.OUTLET_PROXY);
	}
	
	public static String getSmvEmailAddress() {
		return APP_PROPERTIES.getSetting(Constant.SMV_EMAIL_ADDRESS, "");
	}
	
	public static String getSmvEmailName() {
		return APP_PROPERTIES.getSetting(Constant.SMV_EMAIL_NAME, "");
	}
	public static String getSmvHostName() {
		return APP_PROPERTIES.getSetting(Constant.SMV_HOST_NAME, "");
	}
}

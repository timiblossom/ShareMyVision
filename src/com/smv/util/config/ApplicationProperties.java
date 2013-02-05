package com.smv.util.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.smv.util.reflect.TypeUtil;


/**
 * @author Minh Do
 * 07/22/2010
 */
public class ApplicationProperties {
	
	private static final Logger log = Logger.getLogger(ApplicationProperties.class);
	
	private final boolean    debug;
	private final Properties userSettings = new Properties();
	private final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	
	public ApplicationProperties() {
		this("smv.properties");
	}
	
	public ApplicationProperties(String settingsFileName) {		
		debug = getSetting("developerMode", false);
	
		try {
			userSettings.putAll(getProperties(settingsFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.println("Application settings loaded: "); 
		System.out.println("Debug is " + (debug ? "enabled" : "disabled"));
			
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getSetting(String key, T defaultVal) {
		return TypeUtil.fromString(System.getProperty(key), 
			                       getUserProperty(key, (Class<T>) defaultVal.getClass(), defaultVal));
	}
	
	public String requireSetting(String key) {
		String value = getUserProperty(key, null, null);
		if(value == null)
			throw new RuntimeException("No default value for setting: "+key);
		else
			return value;
	}
	
	
	private <T> T getUserProperty(String key, Class<T> retClass, T defaultVal) {
		return TypeUtil.fromString(userSettings.getProperty(key), 
			retClass, 
			defaultVal);
	}
	
	
	/**
	 * Will look for a given properties file in the dev configuration folder or the production
	 * configuration folder depending on the devloperMode.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public Properties getProperties(String fileName) throws IOException {
		String path = debug ? "dev/" : "";
		Properties properties = new Properties();
		
		Resource[] resources = resolver.getResources("classpath*:"+(path + fileName));
		if(resources.length == 0 && debug)	
			resources = resolver.getResources("classpath*:"+(fileName));
		
		if(resources.length == 0)
			throw new FileNotFoundException("Could not find file "+fileName);
		
		InputStream in = resources[0].getInputStream();
		
		try {
			properties.load(in);			
		} finally {
			in.close();
		}
		
		return properties;
	}
	
	public Properties getProperties(String filename, String defaultFileName) {
		try {
			try {
				return getProperties(filename);
			} catch(FileNotFoundException e) {
				return getProperties(defaultFileName);
			}
		} catch(IOException e) {
			log.error("Unable to load properties file: "+filename+" or the default file: "+defaultFileName, e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Same as getProperties(String) except that in the absence of the specified file the
	 * defaults collection will be used. If the file does exist, it's values will be
	 * composite with the defaults collection.
	 * 
	 * @param fileName
	 * @param defaults
	 * @return
	 */
	public Properties getProperties(String fileName, Map<String, String> defaults) {
		Properties props = new Properties();
		try {
			return getProperties(fileName);
			
		} catch(FileNotFoundException e) {
			System.err.println("Settings file not found:'" + fileName + "'. Using defaults.");
			
		} catch(IOException e) {
			System.err.println("Error reading settings file:'" + fileName + "'. Using defaults.");
			e.printStackTrace(System.err);
		}
		
		for(Entry<String, String> entry: defaults.entrySet()) {
			if(props.getProperty(entry.getKey()) == null)
				props.setProperty(entry.getKey(), entry.getValue());
		}
		
		return props;
	}
	
	public boolean isDebugMode() {
		return debug;
	}
		

}

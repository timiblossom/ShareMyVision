/**
 * 
 */
package com.smv.service.mailer.helper;

import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * @author TriNguyen
 *
 */
public class TemplateApplicator {

	private static final Logger LOGGER = Logger.getLogger(TemplateApplicator.class);
	
	protected TemplateApplicator() {
	}

	public static String applyTemplate(String templateFileName, Map<String, Object> mapContext) 
			throws ResourceNotFoundException, ParseErrorException, Exception {
		try {
			//  First, get the Template from Velocity
			Template template = Velocity.getTemplate(templateFileName);
		
			StringWriter writer = new StringWriter();
		
			// Create a Velocity Context with Map Data
			VelocityContext context = new VelocityContext(mapContext);
		
		    // Now render the Template into a StringWriter
			template.merge(context, writer);
		
			// Return serialized writer as String
			return writer.toString();
		
		} catch (ResourceNotFoundException resourceNotFoundException) {
			String errorMessage = "applyTemplate(String templateFileName, Map<String, Object> mapContext) failed. " +
									"\n " +
									"TemplateFileName = " + 
									templateFileName +
									"\n " +
									"MapContext = " + 
									mapContext +
									"\n " +
									"ResourceNotFoundException = " +
									resourceNotFoundException;
			LOGGER.error(errorMessage);
			throw resourceNotFoundException;
		} catch (ParseErrorException parseErrorException) {
			String errorMessage = "applyTemplate(String templateFileName, Map<String, Object> mapContext) failed. " +
									"\n " +
									"TemplateFileName = " + 
									templateFileName +
									"\n " +
									"MapContext = " + 
									mapContext +
									"\n " +
									"ParseErrorException = " +
									parseErrorException;
			LOGGER.error(errorMessage);
			throw parseErrorException;
		} catch (Exception exception) {
			String errorMessage = "applyTemplate(String templateFileName, Map<String, Object> mapContext) failed. " +
									"\n " +
									"TemplateFileName = " + 
									templateFileName +
									"\n " +
									"MapContext = " + 
									mapContext +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);
			throw exception;
		}
	}

	public String alternateApplyTemplate(String templateFileName, Map<String, Object> mapContext) 
			throws ResourceNotFoundException, ParseErrorException, Exception {
		try {
			//  First, get and initialize an Velocity Engine
		    VelocityEngine velocityEngine = new VelocityEngine();
		    velocityEngine.init();
		
		    // Next, get the Template
		    Template template = velocityEngine.getTemplate(templateFileName);
		
		    // Create a Velocity Context and add data
		    VelocityContext velocityContext = new VelocityContext();
		    putMapContext(velocityContext, mapContext);
		
		    // Now render the Template into a StringWriter
		    StringWriter writer = new StringWriter();
		    template.merge(velocityContext, writer);
		    
		    // Serialize writer as String
		    String templatizedResult = writer.toString();
		    
		    // Return result
		    return templatizedResult;

		} catch (ResourceNotFoundException resourceNotFoundException) {
			String errorMessage = "alternateApplyTemplate(String templateFileName, Map<String, Object> mapContext) failed. " +
									"\n " +
									"TemplateFileName = " + 
									templateFileName +
									"\n " +
									"MapContext = " + 
									mapContext +
									"\n " +
									"ResourceNotFoundException = " +
									resourceNotFoundException;
			LOGGER.error(errorMessage);
			throw resourceNotFoundException;
		} catch (ParseErrorException parseErrorException) {
			String errorMessage = "alternateApplyTemplate(String templateFileName, Map<String, Object> mapContext) failed. " +
									"\n " +
									"TemplateFileName = " + 
									templateFileName +
									"\n " +
									"MapContext = " + 
									mapContext +
									"\n " +
									parseErrorException;
			LOGGER.error(errorMessage);
			throw parseErrorException;
		} catch (Exception exception) {
			String errorMessage = "alternateApplyTemplate(String templateFileName, Map<String, Object> mapContext) failed. " +
									"\n " +
									"TemplateFileName = " + 
									templateFileName +
									"\n " +
									"MapContext = " + 
									mapContext +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);
			throw exception;
		}
	}
	
	protected void putMapContext(VelocityContext velocityContext, Map<String, Object> mapContext) {
		if ((velocityContext != null) && (mapContext != null)) {
			Set<Map.Entry<String, Object>> mapContextEntrySet = mapContext.entrySet();
			for (Map.Entry<String, Object> mapEntry : mapContextEntrySet) {
				velocityContext.put(mapEntry.getKey(), mapEntry.getValue());
			}
		}
	}
	
}

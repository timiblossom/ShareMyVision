package com.smv.service.mailer.helper.templates;

import java.util.Map;

import com.smv.service.mailer.helper.AbstractTemplateData;


/**
 * 

 * @author Minh Do
 * 07/22/2010
 * If you don't want to define your own AbstractTemplate class per template (though, this is 
 * recommended for type safety and required parameter compiler checking) you may use this 
 * class that just takes a templateName and Map<String,Object> as its data
 * 
 * data will be merged in with the VelocityContext

 *
 */
public class GenericTemplateData extends AbstractTemplateData {

	private final String templateName;
	private final Map<String, Object> data;
	
	public GenericTemplateData(String templateName, Map<String, Object> data) {
		this.templateName = templateName;
		this.data = data;
	}
	
	public GenericTemplateData(String to, String templateName, Map<String, Object> data) {
		this.templateName = templateName;
		this.data = data;
		this.addTo(to, to);
		this.setFrom("support@sharemyvision.com", "ShareMyVision");
	}
	
	@Override
	public Map<String, Object> getData() {
		return this.data;
	}

	@Override
	public String getTemplateName() {
		return this.templateName;
	}

}

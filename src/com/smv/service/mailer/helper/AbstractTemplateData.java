package com.smv.service.mailer.helper;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.smv.service.mailer.db.dbobject.MailerTemplateDO;



/**
 * @author Minh Do
 * 07/22/2010
 */
public abstract class AbstractTemplateData {
	
	protected Set<InternetAddress> to = new HashSet<InternetAddress>();
	protected InternetAddress from;
	protected Map<String, Object> data = new HashMap<String, Object>();
	
	public void addTo(String toEmail, String toName) {
		try {
			this.to.add(new InternetAddress(toEmail, toName));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setFrom(String fromEmail, String fromName) {
		try {
			this.from = new InternetAddress(fromEmail, fromName);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Set<InternetAddress> getTo() {
		return to;
	}

	public InternetAddress getFrom() {
		return from;
	}

	public String getTemplateName(){
		String cls = this.getClass().getSimpleName();
		return "Email." + (cls.endsWith("Template") ? cls.substring(0, cls.lastIndexOf("Template")) : cls);
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	
	/**
	 * sets the html inside the HtmlEmail and processes the template
	 * TODO: if img tags are used, fetch & embed those images inside the email
	 * @param dataProvider
	 * @throws Exception 
	 * @throws ParseErrorException 
	 * @throws ResourceNotFoundException 
	 */
	public String applyBodyTemplate(SmvMailerData dataProvider) 
			throws ResourceNotFoundException, ParseErrorException, Exception {
		Template template = Velocity.getTemplate(getTemplateName());
		VelocityContext context = new VelocityContext(dataProvider.apply(getData()));
		context.put("mail", this);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		
		String content = writer.toString();
		
		return content;
	}
	
	/**
	 * @param mailerTemplateDO
	 * @param dataProvider
	 * @throws Exception 
	 * @throws ParseErrorException 
	 * @throws ResourceNotFoundException 
	 */
	public String applyBodyTemplate(MailerTemplateDO mailerTemplateDO, SmvMailerData dataProvider) 
			throws ResourceNotFoundException, ParseErrorException, Exception {
		Template template = Velocity.getTemplate(mailerTemplateDO.getBodyTemplate());
		VelocityContext context = new VelocityContext(dataProvider.apply(getData()));
		context.put("mail", this);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		
		String content = writer.toString();
		
		return content;
	}
	
	/**
	 * @param mailerTemplateDO
	 * @param dataProvider
	 * @throws Exception 
	 * @throws ParseErrorException 
	 * @throws ResourceNotFoundException 
	 */
	public String applySubjectTemplate(MailerTemplateDO mailerTemplateDO, SmvMailerData dataProvider)
			throws ResourceNotFoundException, ParseErrorException, Exception {
		Template template = Velocity.getTemplate(mailerTemplateDO.getSubjectTemplate());
		StringWriter subWriter = new StringWriter();
		VelocityContext context = new VelocityContext(dataProvider.apply(getData()));
		template.merge(context, subWriter);
		return subWriter.toString();
	}

	/**
	 * sets the html inside the HtmlEmail and processes the template
	 * TODO: if img tags are used, fetch & embed those images inside the email
	 * @param dataProvider
	 * @throws Exception 
	 * @throws ParseErrorException 
	 * @throws ResourceNotFoundException 
	 */
	public String applyTemplate(SmvMailerData dataProvider) 
			throws ResourceNotFoundException, ParseErrorException, Exception {
		Template template = Velocity.getTemplate(getTemplateName());
		VelocityContext context = new VelocityContext(dataProvider.apply(getData()));
		context.put("mail", this);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		
		String content = writer.toString();
		
		return content;
	}
	
	/**
	 * @param mailerTemplateDO
	 * @param dataProvider
	 * @throws Exception 
	 * @throws ParseErrorException 
	 * @throws ResourceNotFoundException 
	 */
	public String applyTemplate(MailerTemplateDO mailerTemplateDO, SmvMailerData dataProvider) 
			throws ResourceNotFoundException, ParseErrorException, Exception {
		Template template = Velocity.getTemplate(mailerTemplateDO.getBodyTemplate());
		VelocityContext context = new VelocityContext(dataProvider.apply(getData()));
		context.put("mail", this);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		
		String content = writer.toString();
		
		return content;
	}
	
}

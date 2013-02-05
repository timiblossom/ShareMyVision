package com.smv.service.mailer.helper;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.smv.service.mailer.MailTemplateCouplet;
import com.smv.service.mailer.config.ResourceManager;
import com.smv.service.mailer.db.dbobject.MailerTemplateDO;
import com.smv.util.config.ApplicationProperties;

/**
 * @author Minh Do
 * 07/22/2010
 * You must have smtpHost, smtpUsername, and smtpPassword in your application properties file
 */
public class SmtpTemplateMailer implements ITemplateMailer {
	private static final Logger LOGGER = Logger.getLogger(SmtpTemplateMailer.class);
	private static SmtpTemplateMailer instance = new SmtpTemplateMailer(ResourceManager.getApplicationProperties(), 
			                                                            new SmvMailerData(ResourceManager.getApplicationProperties()));
	
	private final String mailHost;
	private final String smtpUsername;
	private final String smtpPassword;
	private final boolean debug;
	private final SmvMailerData provider;
	
	
	private SmtpTemplateMailer(ApplicationProperties settings, SmvMailerData provider) {		
		this.mailHost = settings.requireSetting("smtpHost");
		this.smtpUsername = settings.getSetting("smtpUsername", "");
		this.smtpPassword = settings.getSetting("smtpPassword", "");
		this.debug = settings.getSetting("smtpDebug", false);
		this.provider = provider;				
	}
	
	
	public static SmtpTemplateMailer getInstance() {
		return instance;
	}
	
	public void sendEmail(MailerTemplateDO mailerTemplateDO, AbstractTemplateData mail) throws Exception {
		try { 
			HtmlEmail email = new HtmlEmail();
			email.setCharset("UTF8");
			email.setHostName(this.mailHost);
			email.setTo(mail.getTo());
			email.setFrom(mail.getFrom().getAddress(), mail.getFrom().getPersonal());
			email.setHtmlMsg(mail.applyBodyTemplate(mailerTemplateDO, provider));
			email.setAuthentication(this.smtpUsername, this.smtpPassword);
			email.setDebug(this.debug);
			email.setSubject(mail.applySubjectTemplate(mailerTemplateDO, provider));
			
			email.send();
			
		} catch(Exception exception) {
			String errorMessage = "Error sending SMTP message. " +
									"\n " +
									"MailerTemplateDO = " +
									mailerTemplateDO +
									"\n " +
									"AbstractTemplateData = " +
									mail +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);
			throw exception;
		}
	}
	
	public void sendEmail(MailTemplateCouplet mailTemplateCouplet, AbstractTemplateData mailTemplateData) throws Exception {
		try {
			
			// Construct Email
			HtmlEmail email = new HtmlEmail();

			// Set attributes
			email.setCharset("UTF8");
			email.setHostName(this.mailHost);
			email.setTo(mailTemplateData.getTo());
			email.setFrom(mailTemplateData.getFrom().getAddress(), mailTemplateData.getFrom().getPersonal());
			// Set Subject
			email.setSubject(mailTemplateCouplet.getSubject());
			// Set message/body
			email.setHtmlMsg(mailTemplateCouplet.getBody());
			email.setAuthentication(this.smtpUsername, this.smtpPassword);
			email.setDebug(this.debug);
			
			email.send();
			
		} catch(Exception exception) {
			String errorMessage = "Error sending SMTP message. " +
									"\n " +
									"MailTemplateCouplet = " +
									mailTemplateCouplet +
									"\n " +
									"AbstractTemplateData = " +
									mailTemplateData +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);
			throw exception;
		}
	}

}

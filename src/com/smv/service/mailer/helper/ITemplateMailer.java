package com.smv.service.mailer.helper;

import com.smv.service.mailer.MailTemplateCouplet;
import com.smv.service.mailer.db.dbobject.MailerTemplateDO;

/**
 * @author Minh Do
 * 07/22/2010
 */
public interface ITemplateMailer {

	public void sendEmail(MailerTemplateDO mailerTemplateDO, AbstractTemplateData mail) throws Exception;
	public void sendEmail(MailTemplateCouplet mailTemplateCouplet, AbstractTemplateData mailTemplateData) throws Exception;

}

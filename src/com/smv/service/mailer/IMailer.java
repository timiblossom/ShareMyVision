/**
 * 
 */
package com.smv.service.mailer;

import com.smv.common.bean.MailerDataDTO;

/**
 * @author TriNguyen
 *
 */
public interface IMailer {

	public Boolean sendMail(MailerDataDTO mailerData) throws Exception;
	public String applySubjectTemplate(MailerDataDTO mailerData) throws Exception;
	public String applyBodyTemplate(MailerDataDTO mailerData) throws Exception;
	public MailTemplateCouplet applyTemplate(MailerDataDTO mailerData) throws Exception;
	
}

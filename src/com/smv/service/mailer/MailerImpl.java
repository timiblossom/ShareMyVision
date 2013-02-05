/**
 * 
 */
package com.smv.service.mailer;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.smv.common.bean.MailerDataDTO;
import com.smv.service.mailer.helper.ApplyBodyTemplateHelper;
import com.smv.service.mailer.helper.ApplySubjectTemplateHelper;
import com.smv.service.mailer.helper.ApplyTemplateHelper;
import com.smv.service.mailer.helper.SendMailHelper;

/**
 * @author TriNguyen
 *
 */
public class MailerImpl implements IMailer {

	protected static Logger LOGGER = Logger.getLogger(MailerImpl.class);

	/**
	 * 
	 */
	public MailerImpl() {
	    BasicConfigurator.configure();
	}

	/* (non-Javadoc)
	 * @see com.smv.service.mailer.IMailer#sendMail(com.smv.common.bean.MailerDataDTO)
	 */
	@Override
	public Boolean sendMail(MailerDataDTO mailerData) throws Exception {
		return SendMailHelper.sendMail(mailerData);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.mailer.IMailer#applySubjectTemplate(com.smv.common.bean.MailerDataDTO)
	 */
	@Override
	public String applySubjectTemplate(MailerDataDTO mailerData)
			throws Exception {
		return ApplySubjectTemplateHelper.applySubjectTemplate(mailerData);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.mailer.IMailer#applyBodyTemplate(com.smv.common.bean.MailerDataDTO)
	 */
	@Override
	public String applyBodyTemplate(MailerDataDTO mailerData)
			throws Exception {
		return ApplyBodyTemplateHelper.applyBodyTemplate(mailerData);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.mailer.IMailer#applyTemplate(com.smv.common.bean.MailerDataDTO)
	 */
	@Override
	public MailTemplateCouplet applyTemplate(MailerDataDTO mailerData)
			throws Exception {
		return ApplyTemplateHelper.applyTemplate(mailerData);
	}

}

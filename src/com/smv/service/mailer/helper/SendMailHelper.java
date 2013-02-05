/**
 * 
 */
package com.smv.service.mailer.helper;

import org.apache.log4j.Logger;

import com.smv.common.bean.EmailNameEntryDTO;
import com.smv.common.bean.MailerDataDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.mailer.MailTemplateCouplet;
import com.smv.service.mailer.helper.templates.SmtpTemplateData;

/**
 * @author TriNguyen
 *
 */
public class SendMailHelper {

	private static final Logger LOGGER = Logger.getLogger(SendMailHelper.class);

	protected SendMailHelper() {
	}

	public static Boolean sendMail(MailerDataDTO mailerData) throws SmvServiceException, Exception {
		try {

			// Validate parameter(s)
			ParameterChecker.checkMailerDataNonNull(mailerData);
			ParameterChecker.checkMailerEventCodeNonNullEmptyBlank(mailerData);
			ParameterChecker.checkRecipientNonNull(mailerData);
			
			// At this point, MailerData is "safe" and passes basic parameter checking. 

			// Get Mail Template Couplet based on the mailerData's mailerEventCode
			MailTemplateCouplet mailTemplateCouplet = ApplyTemplateHelper.applyTemplate(mailerData);
			
	
			// Construct SMTP Template Data for email transmission
			SmtpTemplateData mailTemplateData = new SmtpTemplateData();

			// Add email addressees (recipients)
			for(EmailNameEntryDTO entry : mailerData.getTo().getEntries()) {
				if (entry != null) {
					mailTemplateData.addTo(entry.getEmail(), entry.getName());
				}
			}
			
			// Set email sender ("from" information)
			mailTemplateData.setFrom(mailerData.getFromEmail(), mailerData.getFromName());
			
			// Send out email
			SmtpTemplateMailer smtpTemplateMailer = SmtpTemplateMailer.getInstance();
			smtpTemplateMailer.sendEmail(mailTemplateCouplet, mailTemplateData);

			return Boolean.TRUE;

		} catch (IllegalArgumentException iae) {
			String errorMessage = "sendMail(MailerDataDTO mailerData) failed. " +
									"\n " +
									"MailerData = " +
									mailerData +
									"\n " +
									"IllegalArgumentException = " +
									iae;
			LOGGER.error(errorMessage);
			SmvServiceException swe = new SmvServiceException(SmvErrorCode.MAILER_SERVICE_ILLEGAL_ARGUMENT, 
					SmvErrorCode.MAILER_SERVICE_ILLEGAL_ARGUMENT_MSG);
			throw swe;
		} catch (SmvServiceException swe) {
			String errorMessage = "sendMail(MailerDataDTO mailerData) failed. " +
									"\n " +
									"MailerData = " +
									mailerData +
									"\n " +
									"SmvServiceException = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "sendMail(MailerDataDTO mailerData) failed. " +
									"\n " +
									"MailerData = " +
									mailerData +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);

			SmvServiceException swe = new SmvServiceException();
			swe.setErrorCode(SmvErrorCode.MAILER_SERVICE_EXCEPTION);
			swe.setErrorMessage(SmvErrorCode.MAILER_SERVICE_EXCEPTION_MSG + exception);

			throw swe;
		}
	}

}

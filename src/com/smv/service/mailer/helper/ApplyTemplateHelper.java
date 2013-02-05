/**
 * 
 */
package com.smv.service.mailer.helper;

import org.apache.log4j.Logger;

import com.smv.common.bean.MailerDataDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.mailer.MailTemplateCouplet;

/**
 * @author TriNguyen
 *
 */
public class ApplyTemplateHelper {

	private static final Logger LOGGER = Logger.getLogger(ApplyTemplateHelper.class);

	protected ApplyTemplateHelper() {
	}

	public static MailTemplateCouplet applyTemplate(MailerDataDTO mailerData) throws SmvServiceException, Exception {
		try {

			// Validate parameter(s)
			ParameterChecker.checkMailerDataNonNull(mailerData);
			ParameterChecker.checkMailerEventCodeNonNullEmptyBlank(mailerData);
			
			// At this point, MailerData is "safe" and passes basic parameter checking. 

			// Get templatized subject
			String subject = ApplySubjectTemplateHelper.applySubjectTemplate(mailerData);
			
			// Get templatized body
			String body = ApplyBodyTemplateHelper.applyBodyTemplate(mailerData);
	
			// Construct Mail Template Couplet
			MailTemplateCouplet mailTemplateCoupletResult = new MailTemplateCouplet(subject, body);
			
			return mailTemplateCoupletResult;

		} catch (IllegalArgumentException iae) {
			String errorMessage = "applyTemplate(MailerDataDTO mailerData) failed. " +
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
			String errorMessage = "applyTemplate(MailerDataDTO mailerData) failed. " +
									"\n " +
									"MailerData = " +
									mailerData +
									"\n " +
									"SmvServiceException = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "applyTemplate(MailerDataDTO mailerData) failed. " +
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

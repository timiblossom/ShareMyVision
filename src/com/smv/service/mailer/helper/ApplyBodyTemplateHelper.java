/**
 * 
 */
package com.smv.service.mailer.helper;

import org.apache.log4j.Logger;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.smv.common.bean.MailerDataDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.mailer.db.dao.EventCodeDAO;
import com.smv.service.mailer.db.dao.EventCodeTemplateDAO;
import com.smv.service.mailer.db.dao.BodyTemplateDAO;
import com.smv.service.mailer.db.dbobject.EventCodeDO;
import com.smv.service.mailer.db.dbobject.EventCodeTemplateDO;
import com.smv.service.mailer.db.dbobject.BodyTemplateDO;

/**
 * @author TriNguyen
 *
 */
public class ApplyBodyTemplateHelper {

	private static final Logger LOGGER = Logger.getLogger(ApplyBodyTemplateHelper.class);

	/**
	 * 
	 */
	protected ApplyBodyTemplateHelper() {
	}

	public static String applyBodyTemplate(MailerDataDTO mailerData) throws SmvServiceException, Exception {
		try {

			// Validate parameter(s)
			ParameterChecker.checkMailerDataNonNull(mailerData);
			ParameterChecker.checkMailerEventCodeNonNullEmptyBlank(mailerData);
			
			// At this point, MailerData is "safe" and passes basic parameter checking. 
			// Perform work of applying body template based on event code Mailer Data DTO
			return performApplyBodyTemplateWork(mailerData);

		} catch (IllegalArgumentException iae) {
			String errorMessage = "applyBodyTemplate(MailerDataDTO mailerData) failed. " +
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
			String errorMessage = "applyBodyTemplate(MailerDataDTO mailerData) failed. " +
									"\n " +
									"MailerData = " +
									mailerData +
									"\n " +
									"SmvServiceException = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "applyBodyTemplate(MailerDataDTO mailerData) failed. " +
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


	protected static String performApplyBodyTemplateWork(MailerDataDTO mailerData) throws SmvServiceException, Exception {
		try {
			
			// Get Subject Template DO based on MailerDataDTO
			BodyTemplateDO bodyTemplateDO = retrieveBodyTemplateDO(mailerData);
			
			// Apply Subject Template on Mailer Data and return result
			return templatizeBody(bodyTemplateDO, mailerData);
		} catch (Exception exception) {
			String errorMessage = "performApplyBodyTemplateWork(MailerDataDTO mailerData) failed. " +
									"\n " +
									"MailerData = " +
									mailerData +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);
			throw exception;
		}
	}

	public static BodyTemplateDO retrieveBodyTemplateDO(MailerDataDTO mailerData) throws SmvServiceException, Exception {
		try {
			// Get Event Code DO
			EventCodeDO eventCodeDO = EventCodeDAO.getEventCodeByName(mailerData.getMailerEventCode());
			if (eventCodeDO == null) {
				String errorMessage = "performApplyBodyTemplateWork(MailerDataDTO mailerData) failed. " +
										"\n " +
										"MailerData = " +
										mailerData;
				LOGGER.error(errorMessage);
				SmvServiceException swe = new SmvServiceException(SmvErrorCode.MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT, 
											SmvErrorCode.MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT_MSG + errorMessage);
				throw swe;
			}

			// Get EventCode-to-BodyTemplate relationship DO based on Event Code
			EventCodeTemplateDO eventCodeTemplateDO = EventCodeTemplateDAO.getEventCodeTemplateByEventCodeId(eventCodeDO.getId());
			if (eventCodeTemplateDO == null) {
				String errorMessage = "performApplyBodyTemplateWork(MailerDataDTO mailerData) failed. " +
										"\n " +
										"MailerData = " +
										mailerData;
				LOGGER.error(errorMessage);
				SmvServiceException swe = new SmvServiceException(SmvErrorCode.MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT, 
											SmvErrorCode.MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT_MSG + errorMessage);
				throw swe;
			}
			
			// Get Subject Template DO based on EventCode-to-BodyTemplate relationship DO
			BodyTemplateDO bodyTemplateDO = BodyTemplateDAO.getBodyTemplateById(eventCodeTemplateDO.getBodyTemplateId());
			if (bodyTemplateDO == null) {
				String errorMessage = "performApplyBodyTemplateWork(MailerDataDTO mailerData) failed. " +
										"\n " +
										"MailerData = " +
										mailerData;
				LOGGER.error(errorMessage);
				SmvServiceException swe = new SmvServiceException(SmvErrorCode.MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT, 
											SmvErrorCode.MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT_MSG + errorMessage);
				throw swe;
			}
			
			// Found Subject Template DO.
			// So return it.
			return bodyTemplateDO;

		} catch (Exception exception) {
			String errorMessage = "retrieveBodyTemplateDO(MailerDataDTO mailerData) failed. " +
									"\n " +
									"MailerData = " +
									mailerData +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);
			throw exception;
		}
	}

	public static String templatizeBody(BodyTemplateDO bodyTemplateDO, MailerDataDTO mailerData) 
			throws ResourceNotFoundException, ParseErrorException, Exception {
		
		return TemplateApplicator.applyTemplate(bodyTemplateDO.getFileLocation(), KeyValueMapHelper.convertToMap(mailerData.getBodyData()));
	}
	
}

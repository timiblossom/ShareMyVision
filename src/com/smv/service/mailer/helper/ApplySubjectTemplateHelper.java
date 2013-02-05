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
import com.smv.service.mailer.db.dao.SubjectTemplateDAO;
import com.smv.service.mailer.db.dbobject.EventCodeDO;
import com.smv.service.mailer.db.dbobject.EventCodeTemplateDO;
import com.smv.service.mailer.db.dbobject.SubjectTemplateDO;

/**
 * @author TriNguyen
 *
 */
public class ApplySubjectTemplateHelper {

	private static final Logger LOGGER = Logger.getLogger(ApplySubjectTemplateHelper.class);

	/**
	 * 
	 */
	protected ApplySubjectTemplateHelper() {
	}

	public static String applySubjectTemplate(MailerDataDTO mailerData) throws SmvServiceException, Exception {
		try {

			// Validate parameter(s)
			ParameterChecker.checkMailerDataNonNull(mailerData);
			ParameterChecker.checkMailerEventCodeNonNullEmptyBlank(mailerData);
			
			// At this point, MailerData is "safe" and passes basic parameter checking. 
			// Perform work of applying subject template based on event code Mailer Data DTO
			return performApplySubjectTemplateWork(mailerData);

		} catch (IllegalArgumentException iae) {
			String errorMessage = "applySubjectTemplate(MailerDataDTO mailerData) failed. " +
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
			String errorMessage = "applySubjectTemplate(MailerDataDTO mailerData) failed. " +
									"\n " +
									"MailerData = " +
									mailerData +
									"\n " +
									"SmvServiceException = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "applySubjectTemplate(MailerDataDTO mailerData) failed. " +
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

	protected static String performApplySubjectTemplateWork(MailerDataDTO mailerData) throws SmvServiceException, Exception {
		try {
			
			// Get Subject Template DO based on MailerDataDTO
			SubjectTemplateDO subjectTemplateDO = retrieveSubjectTemplateDO(mailerData);
			
			// Apply Subject Template on Mailer Data and return result
			return templatizeSubject(subjectTemplateDO, mailerData);
		} catch (Exception exception) {
			String errorMessage = "performApplySubjectTemplateWork(MailerDataDTO mailerData) failed. " +
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

	public static SubjectTemplateDO retrieveSubjectTemplateDO(MailerDataDTO mailerData) throws SmvServiceException, Exception {
		try {
			// Get Event Code DO
			EventCodeDO eventCodeDO = EventCodeDAO.getEventCodeByName(mailerData.getMailerEventCode());
			if (eventCodeDO == null) {
				String errorMessage = "performApplySubjectTemplateWork(MailerDataDTO mailerData) failed. " +
										"\n " +
										"MailerData = " +
										mailerData;
				LOGGER.error(errorMessage);
				SmvServiceException swe = new SmvServiceException(SmvErrorCode.MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT, 
											SmvErrorCode.MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT_MSG + errorMessage);
				throw swe;
			}

			// Get EventCode-to-SubjectTemplate relationship DO based on Event Code
			EventCodeTemplateDO eventCodeTemplateDO = EventCodeTemplateDAO.getEventCodeTemplateByEventCodeId(eventCodeDO.getId());
			if (eventCodeTemplateDO == null) {
				String errorMessage = "performApplySubjectTemplateWork(MailerDataDTO mailerData) failed. " +
										"\n " +
										"MailerData = " +
										mailerData;
				LOGGER.error(errorMessage);
				SmvServiceException swe = new SmvServiceException(SmvErrorCode.MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT, 
											SmvErrorCode.MAILER_SERVICE_EVENT_CODE_NOT_FOUND_ARGUMENT_MSG + errorMessage);
				throw swe;
			}
			
			// Get Subject Template DO based on EventCode-to-SubjectTemplate relationship DO
			SubjectTemplateDO subjectTemplateDO = SubjectTemplateDAO.getSubjectTemplateById(eventCodeTemplateDO.getSubjectTemplateId());
			if (subjectTemplateDO == null) {
				String errorMessage = "performApplySubjectTemplateWork(MailerDataDTO mailerData) failed. " +
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
			return subjectTemplateDO;

		} catch (Exception exception) {
			String errorMessage = "retrieveSubjectTemplateDO(MailerDataDTO mailerData) failed. " +
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

	public static String templatizeSubject(SubjectTemplateDO subjectTemplateDO, MailerDataDTO mailerData) 
			throws ResourceNotFoundException, ParseErrorException, Exception {
		
		return TemplateApplicator.applyTemplate(subjectTemplateDO.getFileLocation(), KeyValueMapHelper.convertToMap(mailerData.getSubjectData()));
	}
	
}

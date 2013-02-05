/**
 * 
 */
package com.smv.service.mailer.helper;

import org.apache.log4j.Logger;

import com.smv.common.bean.MailerDataDTO;
import com.smv.util.text.StringUtils;

/**
 * @author TriNguyen
 *
 */
public class ParameterChecker {

	private static final Logger LOGGER = Logger.getLogger(ParameterChecker.class);
	
	protected ParameterChecker() {
	}

	public static void checkMailerDataNonNull(MailerDataDTO mailerData) throws IllegalArgumentException {
		if (mailerData == null) {
			String errorMessage = "MailerData parameter is null.";
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkMailerEventCodeNonNullEmptyBlank(MailerDataDTO mailerData) throws IllegalArgumentException {
		checkMailerDataNonNull(mailerData);
		
		if (StringUtils.nullOrEmptyOrBlankString(mailerData.getMailerEventCode())) {
			String errorMessage = "Mailer Event Code is null, empty, or blank.";
			LOGGER.error(errorMessage + 
						"; \n" + 
						"MailerData = " + 
						mailerData);
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void checkRecipientNonNull(MailerDataDTO mailerData) throws IllegalArgumentException {
		checkMailerDataNonNull(mailerData);
		
		if ((mailerData.getTo() == null) || (mailerData.getTo().getEntries() == null)) {
			String errorMessage = "Mailer Recipient(s) is(are) null.";
			LOGGER.error(errorMessage + 
						"; \n" + 
						"MailerData = " + 
						mailerData);
			throw new IllegalArgumentException(errorMessage);
		}
	}

}

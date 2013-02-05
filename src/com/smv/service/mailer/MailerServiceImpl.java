package com.smv.service.mailer;

import javax.jws.WebService;

import com.smv.common.bean.MailerDataDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.util.thread.InstanceGenerator;


/**
 * @author Minh Do
 * 07/22/2010
 */

@WebService(name = "mailer", endpointInterface = "com.smv.service.mailer.IMailerService", serviceName = "MailerService", targetNamespace = "http://smv")
public class MailerServiceImpl implements IMailerService{

	@Override
	public Boolean sendMail(MailerDataDTO mailerData) throws SmvServiceException {
		try {
			return InstanceGenerator.getMailerImplInstance().sendMail(mailerData);
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.MAILER_SERVICE_EXCEPTION, SmvErrorCode.MAILER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

}

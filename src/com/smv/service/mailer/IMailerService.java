package com.smv.service.mailer;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.smv.common.bean.MailerDataDTO;
import com.smv.common.exception.SmvServiceException;


/**
 * @author Minh Do
 * 07/22/2010
 */
@WebService
public interface IMailerService {

	public Boolean sendMail(@WebParam(name = "mailerData") MailerDataDTO mailerData) throws SmvServiceException;

}

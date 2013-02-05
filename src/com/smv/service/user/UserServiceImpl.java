package com.smv.service.user;

import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.smv.common.Constant;
import com.smv.common.bean.AccountDTO;
import com.smv.common.bean.SessionDTO;
import com.smv.common.bean.UserDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.util.soap.SoapUtil;
import com.smv.util.thread.InstanceGenerator;

@WebService(endpointInterface = "com.smv.service.user.IUserService", serviceName = "UserService", targetNamespace = "http://smv") 
public class UserServiceImpl implements IUserService {
	
	protected static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Resource
	private WebServiceContext ctx;
	
	@Resource 
	public void setWsContext(WebServiceContext wsContext) {
		ctx = wsContext;
	}

	private Map<String, String> getHttpEnv() {
		return SoapUtil.getHttpHeaders(ctx);
	}

	@Override
	public AccountDTO getAccount(UserDTO user) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().getAccount(user, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	@Override
	public UserDTO getUserFromAccount(AccountDTO account) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().getUser(account, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	@Override
	public UserDTO getUser(UserDTO user) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().getUser(user, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUserService#isUserExist(java.lang.String)
	 */
	@Override
	public Boolean isUserExist(String emailAddress) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().isUserExist(emailAddress, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	@Override
	public SessionDTO login(UserDTO user) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().login(user, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	@Override
	public SessionDTO logout(UserDTO user) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().logout(user, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	@Override
	public UserDTO register(UserDTO user) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().register(user, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUserService#activateUser(java.lang.String)
	 */
	@Override
	public Boolean activateUser(String activationCode) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().activateUser(activationCode, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUserService#regenerateActivationCode(com.smv.common.bean.UserDTO)
	 */
	@Override
	public String regenerateActivationCode(UserDTO user) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().regenerateActivationCode(user, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUserService#changePassword(com.smv.common.bean.UserDTO, java.lang.String)
	 */
	@Override
	public Boolean changePassword(UserDTO user, String newPassword) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().changePassword(user, newPassword, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUserService#changeForgottenPassword(com.smv.common.bean.UserDTO, java.lang.String)
	 */
	@Override
	public Boolean changeForgottenPassword(UserDTO user) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().changeForgottenPassword(user, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUserService#forgetPassword(com.smv.common.bean.UserDTO)
	 */
	@Override
	public UserDTO forgetPassword(UserDTO user) throws SmvServiceException {
		try {
			return InstanceGenerator.getUserImplInstance().forgetPassword(user, getHttpEnv());
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	
	/**********************************************************************************************
	 * For Mobile API
	 **********************************************************************************************/
	
	@Override
	public UserDTO userRequest(UserDTO user) throws SmvServiceException {
		if (user == null) {
			return null;
		}
		
		try {
			if (Constant.REGISTER.equalsIgnoreCase(user.getAction())) {
				UserDTO retVal = register(user);
			
				//hide some info
				retVal.setContact(null);
				retVal.setUserEmail(null);
				retVal.setUserActivationCode(null);
				return retVal;
			} else if (Constant.LOGIN.equalsIgnoreCase(user.getAction())) {
				SessionDTO session = InstanceGenerator.getUserImplInstance().login(user, getHttpEnv());
				user.setSession(session);
				return user;			
			}
		} catch (SmvServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new SmvServiceException(SmvErrorCode.USER_SERVICE_EXCEPTION, 
					                      SmvErrorCode.USER_SERVICE_EXCEPTION_MSG + e);			
		}
		
		return null;
	}

}

package com.smv.service.user;

import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.smv.common.bean.AccountDTO;
import com.smv.common.bean.PermissionDTO;
import com.smv.common.bean.SessionDTO;
import com.smv.common.bean.UserDTO;
import com.smv.service.user.helper.ActivationHelper;
import com.smv.service.user.helper.ChangeForgottenPasswordHelper;
import com.smv.service.user.helper.ChangePasswordHelper;
import com.smv.service.user.helper.ForgetPasswordHelper;
import com.smv.service.user.helper.GetAccountHelper;
import com.smv.service.user.helper.GetUserHelper;
import com.smv.service.user.helper.GetUserPermissionHelper;
import com.smv.service.user.helper.IsUserExistedHelper;
import com.smv.service.user.helper.LoginHelper;
import com.smv.service.user.helper.LogoutHelper;
import com.smv.service.user.helper.RegisterHelper;
import com.smv.service.user.helper.UpsertAccountHelper;
import com.smv.service.user.helper.UpsertUserHelper;
import com.smv.service.user.helper.UpsertUserPermissionHelper;

public class UserImpl implements IUser {

	protected static Logger LOGGER = Logger.getLogger(UserImpl.class);
	private Map<String, String> httpEnv = null;

	public UserImpl() {
	    BasicConfigurator.configure();
	}
	
	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#setHttpEnv(java.util.Map)
	 */
	@Override
	public void setHttpEnv(final Map<String, String> httpEnv) {
		this.httpEnv = httpEnv;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#getAccount(com.smv.common.bean.UserDTO, java.util.Map)
	 */
	@Override
	public AccountDTO getAccount(UserDTO user, Map<String, String> httpEnv) throws Exception {		
		return GetAccountHelper.getAccount(user);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#getUser(com.smv.common.bean.AccountDTO, java.util.Map)
	 */
	@Override
	public UserDTO getUser(AccountDTO account, Map<String, String> httpEnv) throws Exception {		
		return GetUserHelper.getUser(account);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#getUser(com.smv.common.bean.UserDTO, java.util.Map)
	 */
	@Override
	public UserDTO getUser(UserDTO user, Map<String, String> httpEnv) throws Exception {		
		return GetUserHelper.getUser(user);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#isUserExist(java.lang.String, java.util.Map)
	 */
	@Override
	public Boolean isUserExist(String emailAddress, Map<String, String> httpEnv) throws Exception {
		return IsUserExistedHelper.isUserExisted(emailAddress);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#getUserPermission(com.smv.common.bean.UserDTO, java.util.Map)
	 */
	@Override
	public PermissionDTO[] getUserPermission(UserDTO user, Map<String, String> httpEnv) throws Exception {
		
		return GetUserPermissionHelper.getUserPermission(user);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#login(com.smv.common.bean.UserDTO, java.util.Map)
	 */
	@Override
	public SessionDTO login(UserDTO user, Map<String, String> httpEnv) throws Exception {
		
		return LoginHelper.login(user, httpEnv);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#logout(com.smv.common.bean.UserDTO, java.util.Map)
	 */
	@Override
	public SessionDTO logout(UserDTO user, Map<String, String> httpEnv) throws Exception {
		
		return LogoutHelper.logout(user);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#register(com.smv.common.bean.UserDTO, java.util.Map)
	 */
	@Override
	public UserDTO register(UserDTO user, Map<String, String> httpEnv) throws Exception {
		
		UserDTO retVal = RegisterHelper.register(user);
		
		//hide these information
		retVal.setAction(null);
		retVal.setUserSalt(null);
		retVal.getContact().setId(null);
		
		return retVal;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#upsertAccount(com.smv.common.bean.AccountDTO, java.util.Map)
	 */
	@Override
	public AccountDTO upsertAccount(AccountDTO account, Map<String, String> httpEnv) throws Exception {
		
		return UpsertAccountHelper.upsertAccount(account);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#upsertUser(com.smv.common.bean.UserDTO, java.util.Map)
	 */
	@Override
	public UserDTO upsertUser(UserDTO user, Map<String, String> httpEnv) throws Exception {
		
		return UpsertUserHelper.upsertUser(user);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#upsertUserPermission(com.smv.common.bean.UserDTO, com.smv.common.bean.PermissionDTO, java.util.Map)
	 */
	@Override
	public UserDTO upsertUserPermission(UserDTO user, PermissionDTO permission, Map<String, String> httpEnv) throws Exception {
		
		return UpsertUserPermissionHelper.upsertUserPermission(user, permission);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#activateUser(java.lang.String, java.util.Map)
	 */
	@Override
	public Boolean activateUser(String activationCode, Map<String, String> httpEnv) throws Exception {
		
		ActivationHelper.activateUser(activationCode);
		return Boolean.TRUE;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#regenerateActivationCode(com.smv.common.bean.UserDTO, java.util.Map)
	 */
	@Override
	public String regenerateActivationCode(UserDTO user, Map<String, String> httpEnv) throws Exception {		
		return ActivationHelper.regenerateActivationCode(user);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#changePassword(com.smv.common.bean.UserDTO, java.lang.String, java.util.Map)
	 */
	@Override
	public Boolean changePassword(UserDTO user, String newPassword, Map<String, String> httpEnv) throws Exception {
		
		ChangePasswordHelper.changePassword(user, newPassword);
		return Boolean.TRUE;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#changeForgottenPassword(com.smv.common.bean.UserDTO, java.lang.String, java.util.Map)
	 */
	@Override
	public Boolean changeForgottenPassword(UserDTO user, Map<String, String> httpEnv) throws Exception {
		
		ChangeForgottenPasswordHelper.changeForgottenPassword(user);
		return Boolean.TRUE;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.user.IUser#forgetPassword(com.smv.common.bean.UserDTO, java.util.Map)
	 */
	@Override
	public UserDTO forgetPassword(UserDTO user, Map<String, String> httpEnv) throws Exception {
		
		return ForgetPasswordHelper.forgetPassword(user);
	}

}

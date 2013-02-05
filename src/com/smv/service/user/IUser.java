package com.smv.service.user;

import java.util.Map;

import com.smv.common.bean.AccountDTO;
import com.smv.common.bean.PermissionDTO;
import com.smv.common.bean.SessionDTO;
import com.smv.common.bean.UserDTO;
import com.smv.common.exception.SmvServiceException;

public interface IUser {
	public SessionDTO login(UserDTO user, Map<String, String> httpEnv) throws Exception;
	public SessionDTO logout(UserDTO user, Map<String, String> httpEnv) throws Exception;
	public UserDTO register(UserDTO user, Map<String, String> httpEnv) throws Exception;
	public AccountDTO getAccount(UserDTO user, Map<String, String> httpEnv) throws Exception;
	public UserDTO getUser(AccountDTO account, Map<String, String> httpEnv) throws Exception;
	public UserDTO getUser(UserDTO user, Map<String, String> httpEnv) throws Exception;
	public Boolean isUserExist(String emailAddress, Map<String, String> httpEnv) throws Exception;
	public UserDTO upsertUser(UserDTO user, Map<String, String> httpEnv) throws Exception;
	public AccountDTO upsertAccount(AccountDTO account, Map<String, String> httpEnv) throws Exception;
	public PermissionDTO[] getUserPermission(UserDTO user, Map<String, String> httpEnv) throws Exception;
	public UserDTO upsertUserPermission(UserDTO user, PermissionDTO permission, Map<String, String> httpEnv) throws Exception;
	public void setHttpEnv(Map<String, String> httpEnv) throws Exception;
	public Boolean activateUser(String activationCode, Map<String, String> httpEnv) throws Exception;
	public String regenerateActivationCode(UserDTO user, Map<String, String> httpEnv) throws Exception;
	public Boolean changePassword(UserDTO user, String newPassword, Map<String, String> httpEnv) throws Exception;
	public Boolean changeForgottenPassword(UserDTO user, Map<String, String> httpEnv) throws Exception;
	public UserDTO forgetPassword(UserDTO user, Map<String, String> httpEnv) throws Exception;
}

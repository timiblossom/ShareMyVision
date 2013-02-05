package com.smv.service.user;


import javax.jws.WebParam;
import javax.jws.WebService;

import com.smv.common.bean.AccountDTO;
import com.smv.common.bean.SessionDTO;
import com.smv.common.bean.UserDTO;
import com.smv.common.exception.SmvServiceException;


@WebService
public interface IUserService {
    
    public SessionDTO login(@WebParam(name = "user") UserDTO user) throws SmvServiceException;
	public SessionDTO logout(@WebParam(name = "user") UserDTO user) throws SmvServiceException;
	public UserDTO register(@WebParam(name = "user") UserDTO user) throws SmvServiceException;
	public AccountDTO getAccount(@WebParam(name = "user") UserDTO user) throws SmvServiceException;
	public UserDTO getUserFromAccount(@WebParam(name = "account") AccountDTO account) throws SmvServiceException;
	public UserDTO getUser(@WebParam(name = "user") UserDTO user) throws SmvServiceException;
	public Boolean isUserExist(@WebParam(name = "emailAddress") String emailAddress) throws SmvServiceException;
	public Boolean activateUser(@WebParam(name = "activationCode") String activationCode) throws SmvServiceException;
	public String regenerateActivationCode(@WebParam(name = "user") UserDTO user) throws SmvServiceException;
	public Boolean changePassword(@WebParam(name = "user") UserDTO user, @WebParam(name = "newPassword") String newPassword) throws SmvServiceException;
	public Boolean changeForgottenPassword(@WebParam(name = "user") UserDTO user) throws SmvServiceException;
	public UserDTO forgetPassword(@WebParam(name = "user") UserDTO user) throws SmvServiceException;

	//For Android
    public UserDTO userRequest(@WebParam(name = "user") UserDTO user) throws SmvServiceException;
}

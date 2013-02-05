
package com.zaptoe.user.helper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.bean.PermissionDTO;
import com.zaptoe.common.bean.UserDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.UserConstant;
import com.zaptoe.user.db.dao.PermissionDAO;
import com.zaptoe.user.db.dao.RoleDAO;
import com.zaptoe.user.db.dao.RolePermissionDAO;
import com.zaptoe.user.db.dbo.PermissionDO;
import com.zaptoe.user.db.dbo.RoleDO;
import com.zaptoe.user.db.dbo.RolePermissionDO;
import com.zaptoe.util.db.AbstractDO;


public class UpsertUserPermissionHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(UpsertUserHelper.class);

	public static UserDTO upsertUserPermission(UserDTO user, PermissionDTO permission) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			ParameterChecker.checkUserPassword(user);
			ParameterChecker.checkUserAction(user);
			ParameterChecker.checkPermissionParameter(permission);
			return performUpsertUserPermissionWork(user, permission);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "upsertUserPermission(UserDTO user, PermissionDTO permission) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"Permission = " +
									permission +
									"\n " +
									"Exception = " +
									iae;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG + errorMessage);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "upsertUserPermission(UserDTO user, PermissionDTO permission) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"Permission = " +
									permission +
									"\n " +
									"Exception = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "upsertUserPermission(UserDTO user, PermissionDTO permission) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"Permission = " +
									permission +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
		}
	}

	protected static UserDTO performUpsertUserPermissionWork(UserDTO user, PermissionDTO permission) throws Exception {
		try {
			UpsertUserHelper.upsertUser(user);
			performUpsertPermissionWork(user, permission);
			return user;
		} catch (Exception exception) {
			LOGGER.error("Error in performing performUpsertUserPermissionWork(); \nUser = " + user + "; \nPermission = " + permission + "; \nException = " + exception);
			throw exception;
		}
	}

	protected static void performUpsertPermissionWork(UserDTO user, PermissionDTO permission) throws Exception {
		try {
			// Retrieve related Role
			RoleDO roleDO = RoleDAO.getRoleByNameAndDescription(user.getRoleName(), user.getRoleDescription());
			
			if (roleDO == null) {
				String errorMessage = "Role does not exist for user. \nUser = " + user + "; \nPermission = " + permission;
				LOGGER.error(errorMessage);
				throw new Exception(errorMessage);
			}

			// Check if Permission exists in the system.
			PermissionDO permissionLookAheadDO = PermissionDAO.getPermissionById(permission.getId());

			// Set operation of DO based on User Operation's action
			if (UserConstant.INSERT.equals(permission.getAction())) {
				if (permissionLookAheadDO != null) {
					String errorMessage = "Permission already exists. \nPermission = " + permission;
					LOGGER.error(errorMessage);
					throw new Exception(errorMessage);
				}
			} else if (UserConstant.UPDATE.equals(permission.getAction())) {
				if (permissionLookAheadDO == null) {
					String errorMessage = "Permission does not exist. \nPermission = " + permission;
					LOGGER.error(errorMessage);
					throw new Exception(errorMessage);
				}
			}

			// Switch based on User Operation
			if (UserConstant.INSERT.equals(permission.getAction())) {
				// Create Permission DO
				PermissionDO permissionDO = new PermissionDO();
				
				// Set attributes of Permission DO
				permissionDO.setDescription(permission.getPermissionDescription());
				permissionDO.setName(permission.getPermissionName());
				
				// Set operation to "CREATE" -- i.e. insert new row into database
				permissionDO.setOperation(AbstractDO.CREATE);

				// Link DAO-DO
				PermissionDAO permissionDao = new PermissionDAO(permissionDO);
				permissionDao.save(true, false);

				// Create RolePermission DO
				RolePermissionDO rolePermissionDO = new RolePermissionDO();
				
				// Set attributes of Permission DO
				rolePermissionDO.setPermissionId(permissionDO.getId());
				rolePermissionDO.setRoleId(roleDO.getId());
				
				// Set operation to "CREATE" -- i.e. insert new row into database
				rolePermissionDO.setOperation(AbstractDO.CREATE);

				// Link DAO-DO and save DO
				RolePermissionDAO rolePermissionDao = new RolePermissionDAO(rolePermissionDO);
				rolePermissionDao.save(true, false);

			} else if (UserConstant.UPDATE.equals(permission.getAction())) {
				// Retrieve all related Role_Permissions
				List<RolePermissionDO> rolePermissionDOList = RolePermissionDAO.getRolePermissionByRoleId(roleDO.getId());
				for (RolePermissionDO rolePermissionDO : rolePermissionDOList) {
					PermissionDO permissionDO = PermissionDAO.getPermissionById(rolePermissionDO.getPermissionId());
					if (!permission.getId().equals(permissionDO.getId())) {
						// Set attributes of Permission DO
						permissionDO.setDescription(permission.getPermissionDescription());
						permissionDO.setName(permission.getPermissionName());

						// Set operation to "UPDATE" -- i.e. update row in database
						permissionDO.setOperation(AbstractDO.UPDATE);

						// Link DAO-DO and save DO
						PermissionDAO permissionDao = new PermissionDAO(permissionDO);
						permissionDao.save(true, false);
						
						break;
					}
				}
			} else {
				String errorMessage = "Unsupported action operation = " + permission.getAction();
				LOGGER.error(errorMessage);
				throw new Exception(errorMessage);
			}
		} catch (Exception exception) {
			LOGGER.error("Error in performing performUpsertUserPermissionWork(); \nUser = " + user + "; \nPermission = " + permission + "; \nException = " + exception);
			throw exception;
		}
	}

}

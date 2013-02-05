
package com.zaptoe.user.helper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.bean.PermissionDTO;
import com.zaptoe.common.bean.UserDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.db.dao.PermissionDAO;
import com.zaptoe.user.db.dao.RoleDAO;
import com.zaptoe.user.db.dao.RolePermissionDAO;
import com.zaptoe.user.db.dao.UserDAO;
import com.zaptoe.user.db.dbo.PermissionDO;
import com.zaptoe.user.db.dbo.RoleDO;
import com.zaptoe.user.db.dbo.RolePermissionDO;
import com.zaptoe.user.db.dbo.UserDO;



public class GetUserPermissionHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(GetUserPermissionHelper.class);

	public static PermissionDTO[] getUserPermission(UserDTO user) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			ParameterChecker.checkUserPassword(user);
			return performGetUserPermissionWork(user);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "getUserPermission(UserDTO user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									iae;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG + errorMessage);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "getUserPermission(UserDTO user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "getUserPermission(UserDTO user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									exception;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
		}
	}

	protected static PermissionDTO[] performGetUserPermissionWork(UserDTO user) throws Exception {
		try {
			// Check if User exists in the system.
			UserDO userDO = UserDAO.getUserByEmail(user.getUserEmail());

			if (userDO == null) {
				String errorMessage = "User does not exist. \nUser = " + user;
				LOGGER.error(errorMessage);
				throw new Exception(errorMessage);
			}

			// Retrieve Permissions and related objects
			RoleDO roleDO = RoleDAO.getRoleById(userDO.getRoleId().longValue());
			
			if (roleDO == null) {
				String errorMessage = "User does not have any associated roles. \nUser = " + user;
				LOGGER.error(errorMessage);
				throw new Exception(errorMessage);
			}

			List<RolePermissionDO> rolePermissionsList = RolePermissionDAO.getRolePermissionByRoleId(roleDO.getId());
			if ((rolePermissionsList == null) || (rolePermissionsList.isEmpty()) || (rolePermissionsList.size() == 0)) {
				String errorMessage = "User does not have any associated roles-permissions. \nUser = " + user + "\nRole = " + roleDO;
				LOGGER.error(errorMessage);
				throw new Exception(errorMessage);
			}

			// Create DTOs
			List<PermissionDTO> permissionDTOList = new ArrayList<PermissionDTO> ();
			
			// Set attributes of Permission DTO List
			for (RolePermissionDO rolePermissionDO : rolePermissionsList) {
				PermissionDO permissionDO = PermissionDAO.getPermissionById(rolePermissionDO.getPermissionId());
				if (permissionDO == null) {
					String errorMessage = "User does not have permission associated with roles-permissions. \nUser = " + user + "\nRole-Permission = " + rolePermissionDO;
					LOGGER.error(errorMessage);
					throw new Exception(errorMessage);
				}
				PermissionDTO permission = new PermissionDTO();
				permission.setPermissionDescription(permissionDO.getDescription());
				permission.setPermissionName(permissionDO.getName());
				permission.setRoleDescription(roleDO.getDescription());
				permission.setRoleName(roleDO.getName());
				permission.setId(permissionDO.getId());
				permissionDTOList.add(permission);
			}
			
			return permissionDTOList.toArray(new PermissionDTO[0]);
		} catch (Exception exception) {
			LOGGER.error("Error in performing performGetUserPermissionWork(); User = " + user + "; Exception = " + exception);
			throw exception;
		}
	}
}

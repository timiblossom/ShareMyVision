/**
 * 
 */
package com.smv.service.user.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.smv.service.user.db.dao.UserStatusDAO;
import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class UserStatusDO extends AbstractVersionedDatedDO {

	public static final String USER_STATUS_NAME_ACTIVATION_PENDING = "ActivationPending";
	public static final String USER_STATUS_NAME_PAYMENT_PENDING = "PaymentPending";
	public static final String USER_STATUS_NAME_ACTIVATED = "Activated";
	public static final String USER_STATUS_NAME_INACTIVE = "Inactive";
	public static final String USER_STATUS_NAME_DELETED = "Deleted";
	public static final String USER_STATUS_NAME_SUSPENDED = "Suspended";
	public static final String USER_STATUS_NAME_RESET = "Reset";
	public static final String USER_STATUS_NAME_UNKNOWN = "Unknown";

	public static UserStatusDO getUserStatus(String name) throws IllegalArgumentException {
		List<UserStatusDO> allUserStatus = UserStatusDAO.getAllUserStatus();
		for (UserStatusDO userStatus : allUserStatus) {
			if (userStatus.getName().equals(name)) {
				return userStatus;
			}
		}
		
		throw new IllegalArgumentException("Non-existent User Status with name: " + name);
	}
	
	public static UserStatusDO getUserStatus(Long id) throws IllegalArgumentException {
		List<UserStatusDO> allUserStatus = UserStatusDAO.getAllUserStatus();
		for (UserStatusDO userStatus : allUserStatus) {
			if (userStatus.getId().equals(id)) {
				return userStatus;
			}
		}
		
		throw new IllegalArgumentException("Non-existent User Status with id: " + id);
	}
	

	private String name;

	/**
	 * 
	 */
	public UserStatusDO() {}

	/**
	 * @param id
	 */
	public UserStatusDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public UserStatusDO(Long id, Long versionId, Date updatedOn, Timestamp createdOn) {
		super(id, versionId, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 */
	public UserStatusDO(Long id, Long versionId, Date updatedOn, Timestamp createdOn, String name) {
		super(id, versionId, updatedOn, createdOn);
		setName(name);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

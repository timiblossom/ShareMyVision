/**
 * 
 */
package com.smv.service.user.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.smv.service.user.db.dao.UserTypeDAO;
import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class UserTypeDO extends AbstractVersionedDatedDO {

	public static final String USER_TYPE_NAME_FREE = "Free";
	public static final String USER_TYPE_NAME_CORPORATE = "Corporate";
	public static final String USER_TYPE_NAME_SMALL_BUSINESS = "SmallBusiness";
	public static final String USER_TYPE_NAME_SMALL_OFFICE_HOME_OFFICE = "SmallOfficeHomeOffice";

	public static UserTypeDO getUserType(String name) throws IllegalArgumentException {
		List<UserTypeDO> allUserTypes = UserTypeDAO.getAllUserTypes();
		for (UserTypeDO userType : allUserTypes) {
			if (userType.getName().equals(name)) {
				return userType;
			}
		}
		
		throw new IllegalArgumentException("Non-existent User Type with name: " + name);
	}
	
	public static UserTypeDO getUserType(Long id) throws IllegalArgumentException {
		List<UserTypeDO> allUserTypes = UserTypeDAO.getAllUserTypes();
		for (UserTypeDO userType : allUserTypes) {
			if (userType.getId().equals(id)) {
				return userType;
			}
		}
		
		throw new IllegalArgumentException("Non-existent User Type with id: " + id);
	}
	

	private String name;

	/**
	 * 
	 */
	public UserTypeDO() {
	}

	/**
	 * @param id
	 */
	public UserTypeDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public UserTypeDO(Long id, Long versionId, Date updatedOn, Timestamp createdOn) {
		super(id, versionId, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 */
	public UserTypeDO(Long id, Long versionId, Date updatedOn, Timestamp createdOn, String name) {
		super(id, versionId, updatedOn, createdOn);
		setName(name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}

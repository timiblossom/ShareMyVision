/**
 * 
 */
package com.smv.service.outlet.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.smv.common.bean.KeyValueEntryDTO;
import com.smv.common.bean.KeyValueMapDTO;
import com.smv.common.bean.OutletDTO;
import com.smv.common.bean.UserOutletDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.outlet.OutletConstant;
import com.smv.service.outlet.db.dao.OutletStatusTypeDAO;
import com.smv.service.outlet.db.dao.OutletTypeDAO;
import com.smv.service.outlet.db.dao.UserOutletDAO;
import com.smv.service.outlet.db.dao.UserOutletKeyValuePairDAO;
import com.smv.service.outlet.db.dbobject.OutletTypeDO;
import com.smv.service.outlet.db.dbobject.UserOutletDO;
import com.smv.service.outlet.db.dbobject.UserOutletKeyValuePairDO;
import com.smv.util.db.AbstractDO;

/**
 * @author TriNguyen
 *
 */
public class UpsertUserOutletHelper {

	protected static final Logger LOGGER = Logger.getLogger(UpsertUserOutletHelper.class);
	
	protected UpsertUserOutletHelper() {
	}

	public static UserOutletDTO upsertUserOutlet(UserOutletDTO userOutlet) throws IllegalArgumentException, SmvServiceException, Exception {
		try {
        	return performUpsertUserOutletWork(userOutlet);
		} catch (IllegalArgumentException iae) {
            String errorMessage = "upsertUserOutlet() failed. " + 
						        	"\n " +
						        	"userOutlet = " +
						        	userOutlet +
						        	"\n " +
						            "Exception = " + 
									iae;
			LOGGER.error(errorMessage);
			
			SmvServiceException swe = new SmvServiceException();
			swe.setErrorCode(SmvErrorCode.OUTLET_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(SmvErrorCode.OUTLET_SERVICE_ILLEGAL_ARGUMENT_MSG + 
					errorMessage);
			throw swe;
		} catch (SmvServiceException swe) {
            String errorMessage = "upsertUserOutlet() failed. " + 
						        	"\n " +
						        	"userOutlet = " +
						        	userOutlet +
						        	"\n " +
						            "Exception = " + 
						            swe;

			LOGGER.error(errorMessage);

            SmvServiceException wrappedSwe = new SmvServiceException();
            wrappedSwe.setErrorCode(SmvErrorCode.OUTLET_SERVICE_EXCEPTION);
            wrappedSwe.setErrorMessage(SmvErrorCode.OUTLET_SERVICE_EXCEPTION_MSG +
            		errorMessage +
            		swe);

			throw wrappedSwe;
        } catch (Exception exception) {
            String errorMessage = "upsertUserOutlet() failed. " + 
					            	"\n " +
					            	"userOutlet = " +
					            	userOutlet +
					            	"\n " +
					                "Exception = " + 
					                exception;

            LOGGER.error(errorMessage);

            SmvServiceException swe = new SmvServiceException();
            swe.setErrorCode(SmvErrorCode.OUTLET_SERVICE_EXCEPTION);
            swe.setErrorMessage(SmvErrorCode.OUTLET_SERVICE_EXCEPTION_MSG +
                exception);

            throw swe;
        }
		
	}

	protected static UserOutletDTO performUpsertUserOutletWork(UserOutletDTO userOutlet) throws Exception {
		// Check if UserOutlet exists in the system.
    	Long userId = userOutlet.getUserId();
    	Long outletId = userOutlet.getOutlet().getId();
		List<UserOutletDO> userOutletList = UserOutletDAO.getUserOutletByUserIdAndOutletId(userId, outletId);
		UserOutletDO userOutletDO = null;

		// Check if userOutlet exists in database
		if (OutletConstant.INSERT.equals(userOutlet.getAction())) {
			if ((userOutletList != null) && (!userOutletList.isEmpty()) && (userOutletList.size() > 0)) {
				String errorMessage = "UserOutlet already exists." + 
										"\n" +
										"UserOutlet = " + 
										userOutlet;
				LOGGER.error(errorMessage);
				throw new Exception(errorMessage);
			}
			userOutletDO = new UserOutletDO();
		} else if (OutletConstant.UPDATE.equals(userOutlet.getAction())) {
			if ((userOutletList == null) || (userOutletList.isEmpty()) || (userOutletList.size() < 1)) {
				String errorMessage = "UserOutlet does not exist." + 
										"\n" +
										"UserOutlet = " + 
										userOutlet;
				LOGGER.error(errorMessage);
				throw new Exception(errorMessage);
			}
			userOutletDO = userOutletList.get(0);
		}
		
		try {
			
			// Set attributes of DO from DTO
			setAttributesOfDO(userOutletDO, userOutlet);
			
			// ReConstitute KeyValueMapDTO of User Outlet DTO as UserOutletKeyValuePairDO
			List<UserOutletKeyValuePairDO> userOutletKeyValuePairDOsList = new ArrayList<UserOutletKeyValuePairDO>();
			reconstituteToDO(userOutletKeyValuePairDOsList, userOutlet, userOutletDO);
			
			// Set operation of DO based on User Operation's action
			if (OutletConstant.INSERT.equals(userOutlet.getAction())) {
				// Set operation to "CREATE" -- i.e. insert new row into database
				userOutletDO.setOperation(AbstractDO.CREATE);
				for (UserOutletKeyValuePairDO userOutletKeyValuePairDO : userOutletKeyValuePairDOsList) {
					userOutletKeyValuePairDO.setOperation(AbstractDO.CREATE);
				}
			} else if (OutletConstant.UPDATE.equals(userOutlet.getAction())) {
				// Set operation to "UPDATE" -- i.e. update row in database
				userOutletDO.setOperation(AbstractDO.UPDATE);
				for (UserOutletKeyValuePairDO userOutletKeyValuePairDO : userOutletKeyValuePairDOsList) {
					userOutletKeyValuePairDO.setOperation(AbstractDO.UPDATE);
				}
			}

			// Link DAO-DO
			UserOutletDAO userOutletDao = new UserOutletDAO(userOutletDO);
			List<UserOutletKeyValuePairDAO> userOutletKeyValuePairDAOsList = new ArrayList<UserOutletKeyValuePairDAO>();
			if ((userOutletKeyValuePairDOsList != null) && (!userOutletKeyValuePairDOsList.isEmpty()) && (userOutletKeyValuePairDOsList.size() > 0)) {
				for (UserOutletKeyValuePairDO userOutletKeyValuePairDO : userOutletKeyValuePairDOsList) {
					UserOutletKeyValuePairDAO userOutletKeyValuePairDAO = new UserOutletKeyValuePairDAO(userOutletKeyValuePairDO);
					userOutletKeyValuePairDAOsList.add(userOutletKeyValuePairDAO);
				}
			}


			// Save DAO Operation
			if (userOutletDao.save(true, false) == false) {
				String errorMessage = "Error in performing upsertUserOutlet() with saving DAO; " +
										"\n" +
										"userOutlet = " +
										userOutlet;
				LOGGER.error(errorMessage);
				throw new Exception(errorMessage);
			}
			if ((userOutletKeyValuePairDAOsList != null) && (!userOutletKeyValuePairDAOsList.isEmpty()) && (userOutletKeyValuePairDAOsList.size() > 0)) {
				for (UserOutletKeyValuePairDAO userOutletKeyValuePairDAO : userOutletKeyValuePairDAOsList) {
					if (userOutletKeyValuePairDAO.save(true, false) == false) {
						String errorMessage = "Error in performing upsertUserOutlet() with saving DAO; " +
												"\n" +
												"userOutlet = " +
												userOutlet;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				}
			}
			
			// TODO:
			// HERE I AM!!!
			
			// Propagate back results of operation back to DTO
			propagateUserOutlet(userOutlet, userOutletDO, userOutletKeyValuePairDOsList);

			return userOutlet;
		} catch (Exception exception) {
			String errorMessage = "Error in performing upsertUserOutlet() with saving DAO; " +
									"\n" +
									"userOutlet = " +
									userOutlet;
			LOGGER.error(errorMessage);
			throw exception;
		}
			
	}
	
	protected static void setAttributesOfDO(UserOutletDO userOutletDO, UserOutletDTO userOutlet) throws SmvServiceException {
		userOutletDO.setName(userOutlet.getName());
		userOutletDO.setDescription(userOutlet.getDescription());
		userOutletDO.setOutletId(userOutlet.getOutlet().getId());
		userOutletDO.setUserId(userOutlet.getUserId());
		Long statusId = OutletStatusTypeHelper.getOutletStatusTypeIdByName(userOutlet.getStatusType());
		userOutletDO.setStatusId(statusId);
	}
	
	protected static void propagateUserOutlet(UserOutletDTO userOutlet, UserOutletDO userOutletDO, List<UserOutletKeyValuePairDO> userOutletKeyValuePairDOsList) throws SmvServiceException {
		
		userOutlet.setName(userOutletDO.getName());
		userOutlet.setDescription(userOutletDO.getDescription());

		// Propagate Outlet
		OutletTypeDO userOutletTypeDO = OutletTypeDAO.getOutletTypeById(userOutletDO.getId());
		OutletDTO outlet = new OutletDTO();
		propagateOutlet(outlet, userOutletTypeDO);
		userOutlet.setOutlet(outlet);
		
		userOutlet.setUserId(userOutletDO.getUserId());
		
		// Outlet Status of User Outlet
		// NOTE: This is not the same as Status of the Outlet Type
		// This status of User Outlet is enabled/disabled or set by the user.
		String userOutletStatus = OutletStatusTypeDAO.getOutletStatusTypeById(userOutletDO.getStatusId()).getName();
		outlet.setStatusType(userOutletStatus);
	}

	protected static void reconstituteToDO(List<UserOutletKeyValuePairDO> userOutletKeyValuePairDOsList, UserOutletDTO userOutlet, UserOutletDO userOutletDO) throws SmvServiceException {
		KeyValueMapDTO keyValueMap = userOutlet.getKeyValueMap();
		if (keyValueMap != null) {
			List<KeyValueEntryDTO> keyValueMapEntries = keyValueMap.getEntries();
			if (keyValueMapEntries != null) {
				for (KeyValueEntryDTO keyValueEntry : keyValueMapEntries) {
					UserOutletKeyValuePairDO userOutletKeyValuePairDO = new UserOutletKeyValuePairDO();
					userOutletKeyValuePairDO.setKeyPair(keyValueEntry.getKey());
					userOutletKeyValuePairDO.setValuePair(keyValueEntry.getValue());
					userOutletKeyValuePairDO.setParentContainerId(userOutletDO.getId());
					userOutletKeyValuePairDOsList.add(userOutletKeyValuePairDO);
				}
			}
		}
	}

	protected static void propagateOutlet(OutletDTO outlet, OutletTypeDO userOutletTypeDO) throws SmvServiceException {
		outlet.setId(userOutletTypeDO.getId());
		outlet.setName(userOutletTypeDO.getName());
		outlet.setDescription(userOutletTypeDO.getDescription());
		String statusType = OutletStatusTypeDAO.getOutletStatusTypeById(userOutletTypeDO.getStatusId()).getName();
		outlet.setStatusType(statusType);
	}

}

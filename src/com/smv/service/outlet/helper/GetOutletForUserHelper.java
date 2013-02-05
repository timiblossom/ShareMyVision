package com.smv.service.outlet.helper;

import com.smv.common.bean.OutletDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.outlet.db.dao.OutletStatusTypeDAO;
import com.smv.service.outlet.db.dao.OutletTypeDAO;
import com.smv.service.outlet.db.dao.UserOutletDAO;
import com.smv.service.outlet.db.dbobject.OutletTypeDO;
import com.smv.service.outlet.db.dbobject.UserOutletDO;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TriNguyen
 *
 */
public class GetOutletForUserHelper {

	private static final Logger LOGGER = Logger.getLogger(GetOutletForUserHelper.class);

    protected GetOutletForUserHelper() {
    }

    public static OutletDTO[] getOutletForUser(Long userId) throws SmvServiceException, Exception {
        try {
            // Retrieve all User Outlets belonging to userId
        	List<UserOutletDO> userOutletDOsList = UserOutletDAO.getUserOutletByUserId(userId);

        	// Sanity check retrieval result
            if ((userOutletDOsList == null) || (userOutletDOsList.size() == 0)) {
                return null;
            }

            // Retrieve list of Outlet Types corresponding to each User Outlet retrieved
            List<OutletTypeDO> outletTypeDOsList = new ArrayList<OutletTypeDO>();
            for (UserOutletDO userOutletDO : userOutletDOsList) {
            	OutletTypeDO outletTypeDO = OutletTypeDAO.getOutletTypeById(userOutletDO.getOutletId());
            	// Set Status according to status of User Outlet
            	Long statusId = userOutletDO.getStatusId();
            	outletTypeDO.setStatusId(statusId);
            	outletTypeDOsList.add(outletTypeDO);
            }

            // Sanity check retrieval result
            if ((outletTypeDOsList == null) || (outletTypeDOsList.size() == 0)) {
                return null;
            }

            // Construct list of OutletTypes for return result
            List<OutletDTO> outletDTOsList = OutletHelper.createDTOListFromDOList(outletTypeDOsList);

            // Return Outlet DTOs
            return OutletHelper.createDTOArrayFromDTOList(outletDTOsList);

        } catch (Exception exception) {
            String errorMessage = "getOutletForUser() failed. " + 
						        	"\n " +
						            "userId = " +
						            userId +
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
}

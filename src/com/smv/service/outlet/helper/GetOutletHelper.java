package com.smv.service.outlet.helper;

import com.smv.common.bean.OutletDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.outlet.db.dao.OutletTypeDAO;
import com.smv.service.outlet.db.dbobject.OutletTypeDO;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author TriNguyen
 *
 */
public class GetOutletHelper {

	private static final Logger LOGGER = Logger.getLogger(GetOutletHelper.class);

    protected GetOutletHelper() {
    }

    public static OutletDTO[] getOutlet() throws SmvServiceException, Exception {
        try {
            // Retrieve all products
            List<OutletTypeDO> outletTypeDOsList = OutletTypeDAO.getAllOutletTypes();

            // Sanity check retrieval result
            if ((outletTypeDOsList == null) || (outletTypeDOsList.size() == 0)) {
                return null;
            }

            // Construct list of OutletTypes for return result
            List<OutletDTO> outletDTOsList = OutletHelper.createDTOListFromDOList(outletTypeDOsList);

            // Return Outlet DTOs
            return OutletHelper.createDTOArrayFromDTOList(outletDTOsList);

        } catch (Exception exception) {
            String errorMessage = "getOutlet() failed. " + 
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

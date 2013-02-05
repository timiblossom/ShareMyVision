package com.smv.service.outlet.helper;

import org.apache.log4j.Logger;

import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.outlet.db.dao.OutletStatusTypeDAO;
import com.smv.service.outlet.db.dbobject.OutletStatusTypeDO;

/**
 * @author TriNguyen
 *
 */
public class OutletStatusTypeHelper {

	protected static final Logger LOGGER = Logger.getLogger(OutletStatusTypeHelper.class);
	
	protected OutletStatusTypeHelper() {
	}

	public static Long getOutletStatusTypeIdByName(String statusTypeName) throws SmvServiceException {
		OutletStatusTypeDO outletStatus = OutletStatusTypeDAO.getOutletStatusTypeByName(statusTypeName);
		
		if (outletStatus != null) {
			return outletStatus.getId();
		} else {
			String errorMessage = "OutletStatusType does not exist." + 
									"\n" +
									"statusTypeName = " + 
									statusTypeName;
			LOGGER.error(errorMessage);
            SmvServiceException swe = new SmvServiceException();
            swe.setErrorCode(SmvErrorCode.OUTLET_SERVICE_OUTLET_STATUS_NOT_FOUND_EXCEPTION);
            swe.setErrorMessage(SmvErrorCode.OUTLET_SERVICE_OUTLET_STATUS_NOT_FOUND_EXCEPTION_MSG +
            					errorMessage);
            throw swe;
		}
	}

}

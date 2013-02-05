package com.smv.service.outlet.helper;

import com.smv.common.bean.OutletDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.outlet.db.dao.OutletStatusTypeDAO;
import com.smv.service.outlet.db.dao.OutletTypeDAO;
import com.smv.service.outlet.db.dbobject.OutletStatusTypeDO;
import com.smv.service.outlet.db.dbobject.OutletTypeDO;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TriNguyen
 *
 */
public class OutletHelper {

	private static final Logger LOGGER = Logger.getLogger(OutletHelper.class);

    protected OutletHelper() {
    }

	public static void checkOutletTypeExists(Long outletId) throws SmvServiceException {
		
		if ((outletId == null) || !OutletHelper.doesOutletTypeExist(outletId)) {
            String errorMessage = SmvErrorCode.OUTLET_SERVICE_OUTLET_TYPE_NOT_FOUND_EXCEPTION_MSG +
						    		"outletId = " +
						    		outletId;

            LOGGER.error(errorMessage);

            SmvServiceException swe = new SmvServiceException();
            swe.setErrorCode(SmvErrorCode.OUTLET_SERVICE_OUTLET_TYPE_NOT_FOUND_EXCEPTION);
            swe.setErrorMessage(errorMessage);

			throw swe;
		}
	}
	
    public static boolean doesOutletTypeExist(long outletId) {
    	OutletTypeDO outletTypeDO = OutletTypeDAO.getOutletTypeById(outletId);
    	return (outletTypeDO == null) ? false : true;
    }
    
    public static List<OutletDTO> createDTOListFromDOList(List<OutletTypeDO> outletTypeDOsList) {
        // Construct list of OutletTypes for return result
        List<OutletDTO> outletDTOsList = new ArrayList<OutletDTO>();

        // Iterate through each outlet type
        for (OutletTypeDO outletTypeDO : outletTypeDOsList) {
            // Sanity check retrieval result
            if (outletTypeDO != null) {
            	
                // Create OutletType DTO
                OutletDTO outletDTO = new OutletDTO();

                // Set attributes of Outlet DTOs from Outlet DOs
                copyAttributes(outletTypeDO, outletDTO);

                // Add Outlet DTO entry into outletDTO list
                outletDTOsList.add(outletDTO);
            }
        }
        
        return outletDTOsList;
    }
    
    public static OutletDTO[] createDTOArrayFromDTOList(List<OutletDTO> outletDTOsList) {
        if ((outletDTOsList != null) && (outletDTOsList.size() > 0)) {
            OutletDTO[] outletsArray = new OutletDTO[outletDTOsList.size()];
            outletDTOsList.toArray(outletsArray);

            return outletsArray;
        } else {
            return null;
        }
    }
    
    public static void copyAttributes(OutletTypeDO outletTypeDO, OutletDTO outletDTO) {
    	outletDTO.setId(outletTypeDO.getId());
        outletDTO.setName(outletTypeDO.getName());
        outletDTO.setDescription(outletTypeDO.getDescription());
    	outletDTO.setStatusType(translateStatusIdToString(outletTypeDO.getStatusId()));
    }
    
    public static String translateStatusIdToString(Long statusId) {
    	OutletStatusTypeDO outletStatusTypeDO = OutletStatusTypeDAO.getOutletStatusTypeById(statusId);
    	return (outletStatusTypeDO == null) ? null : outletStatusTypeDO.getName();
    }

}

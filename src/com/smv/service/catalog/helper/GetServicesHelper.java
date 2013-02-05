/**
 *
 */
package com.smv.service.catalog.helper;

import com.smv.common.bean.ServiceDTO;
import com.smv.common.bean.ServiceKeyValuePairDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;

import com.smv.service.catalog.db.dao.ServiceDAO;
import com.smv.service.catalog.db.dao.ServiceKeyValuePairDAO;
import com.smv.service.catalog.db.dbobject.ServiceDO;
import com.smv.service.catalog.db.dbobject.ServiceKeyValuePairDO;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * @author TriNguyen
 *
 */
public class GetServicesHelper {
    private static final Logger LOGGER = Logger.getLogger(GetServicesHelper.class);

    protected GetServicesHelper() {
    }

    public static ServiceDTO[] getServices() throws SmvServiceException, Exception {
        try {
            // Retrieve all services
            List<ServiceDO> serviceDOs = ServiceDAO.getAllServices();

            // Sanity check retrieval result
            if ((serviceDOs == null) || (serviceDOs.size() == 0)) {
                return null;
            }

            // Construct list of services for return result
            List<ServiceDTO> services = new ArrayList<ServiceDTO>();

            // Iterate through each service
            for (ServiceDO serviceDO : serviceDOs) {
                // Sanity check retrieval result
                if (serviceDO != null) {
                    // Retrieve all service key value pairs corresponding to each service
                    List<ServiceKeyValuePairDO> serviceKeyValuePairDOs = ServiceKeyValuePairDAO.getKeyValuePairByService(serviceDO.getId());

                    // Create Service DTO
                    ServiceDTO service = new ServiceDTO();

                    // Set attributes of Service DTOs from Service DOs
                    service.setId(serviceDO.getId());
                    service.setName(serviceDO.getName());

                    // Construct list of service key value pairs for association with specific service
                    List<ServiceKeyValuePairDTO> serviceKeyValuePairs = new ArrayList<ServiceKeyValuePairDTO>();

                    // Sanity check retrieval result of Key Value Pairs
                    if ((serviceKeyValuePairDOs != null) &&
                            (serviceKeyValuePairDOs.size() > 0)) {
                        // Iterate through each service key value pair of Key Value Pairs
                        for (ServiceKeyValuePairDO serviceKeyValuePairDO : serviceKeyValuePairDOs) {
                            // Sanity check retrieval result
                            if (serviceKeyValuePairDO != null) {
                                // Create Service Key Value Pair DTO
                                ServiceKeyValuePairDTO serviceKeyValuePair = new ServiceKeyValuePairDTO();

                                // Set attributes of Service Key Value Pair DTOs from Service Key Value Pair DOs
                                serviceKeyValuePair.setId(serviceKeyValuePairDO.getId());
                                serviceKeyValuePair.setKeyPair(serviceKeyValuePairDO.getKeyPair());
                                serviceKeyValuePair.setValuePair(serviceKeyValuePairDO.getValuePair());
                                serviceKeyValuePair.setContainerParentId(serviceKeyValuePairDO.getParentContainerId());

                                // Add service key value pair to list of service key value pairs
                                serviceKeyValuePairs.add(serviceKeyValuePair);
                            } // if (serviceKeyValuePairDO != null)
                        } // for (ServiceKeyValuePairDO serviceKeyValuePairDO : serviceKeyValuePairDOs)
                    } // if ((serviceKeyValuePairDOs != null) && (serviceKeyValuePairDOs.size() > 0))

                    // Associate service key value pairs corresponding to list of service key value pairs
                    if ((serviceKeyValuePairs != null) &&
                            (serviceKeyValuePairs.size() > 0)) {
                        ServiceKeyValuePairDTO[] serviceKeyValuePairDtoArray = new ServiceKeyValuePairDTO[serviceKeyValuePairs.size()];
                        serviceKeyValuePairs.toArray(serviceKeyValuePairDtoArray);
                        service.setServiceKeyValuePairs(serviceKeyValuePairDtoArray);
                    }

                    // Add Service DTO entry into service list
                    services.add(service);
                } // if (serviceDO != null)
            } // for (ServiceDO serviceDO : serviceDOs)

            // Return Service DTOs
            if ((services != null) && (services.size() > 0)) {
                ServiceDTO[] servicesArray = new ServiceDTO[services.size()];
                services.toArray(servicesArray);

                return servicesArray;
            } else {
                return null;
            }
        } catch (Exception exception) {
            String errorMessage = "getServices() failed. " + "\n " +
                "Exception = " + exception;
            LOGGER.error(errorMessage);

            SmvServiceException swe = new SmvServiceException();
            swe.setErrorCode(SmvErrorCode.CATALOG_SERVICE_EXCEPTION);
            swe.setErrorMessage(SmvErrorCode.CATALOG_SERVICE_EXCEPTION_MSG +
                exception);
            throw swe;
        }
    }
}

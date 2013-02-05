/**
 *
 */
package com.smv.service.catalog.helper;

import com.smv.common.bean.CatalogDTO;
import com.smv.common.bean.CatalogKeyValuePairDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;

import com.smv.service.catalog.db.dao.CatalogDAO;
import com.smv.service.catalog.db.dao.CatalogKeyValuePairDAO;
import com.smv.service.catalog.db.dbobject.CatalogDO;
import com.smv.service.catalog.db.dbobject.CatalogKeyValuePairDO;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * @author TriNguyen
 *
 */
public class GetCatalogHelper {
    private static final Logger LOGGER = Logger.getLogger(GetCatalogHelper.class);

    protected GetCatalogHelper() {
    }

    public static CatalogDTO[] getCatalog() throws SmvServiceException, Exception {
        try {
            // Retrieve all products
            List<CatalogDO> catalogDOs = CatalogDAO.getAllCatalogs();

            // Sanity check retrieval result
            if ((catalogDOs == null) || (catalogDOs.size() == 0)) {
                return null;
            }

            // Construct list of catalogs for return result
            List<CatalogDTO> catalogs = new ArrayList<CatalogDTO>();

            // Iterate through each catalog
            for (CatalogDO catalogDO : catalogDOs) {
                // Sanity check retrieval result
                if (catalogDO != null) {
                    // Retrieve all catalog key value pairs corresponding to each catalog
                    List<CatalogKeyValuePairDO> catalogKeyValuePairDOs = CatalogKeyValuePairDAO.getKeyValuePairByCatalog(catalogDO.getId());

                    // Create Catalog DTO
                    CatalogDTO catalog = new CatalogDTO();

                    // Set attributes of Catalog DTOs from Catalog DOs
                    catalog.setId(catalogDO.getId());
                    catalog.setName(catalogDO.getName());

                    // Construct list of catalog key value pairs for association with specific catalog
                    List<CatalogKeyValuePairDTO> catalogKeyValuePairs = new ArrayList<CatalogKeyValuePairDTO>();

                    // Sanity check retrieval result of Key Value Pairs
                    if ((catalogKeyValuePairDOs != null) &&
                            (catalogKeyValuePairDOs.size() > 0)) {
                        // Iterate through each catalog key value pair
                        for (CatalogKeyValuePairDO catalogKeyValuePairDO : catalogKeyValuePairDOs) {
                            // Sanity check retrieval result
                            if (catalogKeyValuePairDO != null) {
                                // Create Catalog DTO
                                CatalogKeyValuePairDTO catalogKeyValuePair = new CatalogKeyValuePairDTO();

                                // Set attributes of Catalog Key Value Pair DTOs from Catalog Key Value Pair DOs
                                catalogKeyValuePair.setId(catalogKeyValuePairDO.getId());
                                catalogKeyValuePair.setKeyPair(catalogKeyValuePairDO.getKeyPair());
                                catalogKeyValuePair.setValuePair(catalogKeyValuePairDO.getValuePair());
                                catalogKeyValuePair.setContainerParentId(catalogKeyValuePairDO.getParentContainerId());

                                // Add catalog key value pair to list of catalog key value pairs
                                catalogKeyValuePairs.add(catalogKeyValuePair);
                            } // if (catalogKeyValuePairDO != null)
                        } // for (CatalogKeyValuePairDO catalogKeyValuePairDO : catalogKeyValuePairDOs)
                    } // if ((catalogKeyValuePairDOs != null) && (catalogKeyValuePairDOs.size() > 0))

                    // Associate catalog key value pairs corresponding to list of catalog key value pairs
                    if ((catalogKeyValuePairs != null) &&
                            (catalogKeyValuePairs.size() > 0)) {
                        CatalogKeyValuePairDTO[] catalogKeyValuePairDtoArray = new CatalogKeyValuePairDTO[catalogKeyValuePairs.size()];
                        catalogKeyValuePairs.toArray(catalogKeyValuePairDtoArray);
                        catalog.setCatalogKeyValuePairs(catalogKeyValuePairDtoArray);
                    }

                    // Add Catalog DTO entry into catalog list
                    catalogs.add(catalog);
                } // if (catalogDO != null)
            } // for (CatalogDO catalogDO : catalogDOs)

            // Return Catalog DTOs
            if ((catalogs != null) && (catalogs.size() > 0)) {
                CatalogDTO[] catalogsArray = new CatalogDTO[catalogs.size()];
                catalogs.toArray(catalogsArray);

                return catalogsArray;
            } else {
                return null;
            }
        } catch (Exception exception) {
            String errorMessage = "getCatalog() failed. " + "\n " +
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

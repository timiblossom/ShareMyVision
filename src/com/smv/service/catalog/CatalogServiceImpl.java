package com.smv.service.catalog;



import javax.jws.WebService;

import com.smv.common.bean.CatalogDTO;
import com.smv.common.bean.ProductDTO;
import com.smv.common.bean.ServiceDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.util.thread.InstanceGenerator;


@WebService(endpointInterface = "com.smv.service.catalog.ICatalogService", serviceName = "CatalogService", targetNamespace = "http://smv") 
public class CatalogServiceImpl implements ICatalogService {

	/* (non-Javadoc)
	 * @see com.smv.service.catalog.ICatalogService#getCatalog()
	 */
	@Override
	public CatalogDTO[] getCatalog() throws SmvServiceException {
		try {
			return InstanceGenerator.getCatalogImplInstance().getCatalog();
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.CATALOG_SERVICE_EXCEPTION, SmvErrorCode.CATALOG_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.catalog.ICatalogService#getAllProducts()
	 */
	@Override
	public ProductDTO[] getAllProducts() throws SmvServiceException {
		try {
			return InstanceGenerator.getCatalogImplInstance().getProducts();
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.CATALOG_SERVICE_EXCEPTION, SmvErrorCode.CATALOG_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.catalog.ICatalogService#getProducts(java.lang.Long, java.lang.String)
	 */
	@Override
	public ProductDTO[] getProducts(Long id, String status) throws SmvServiceException {
		try {
			return InstanceGenerator.getCatalogImplInstance().getProducts(id, status);
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.CATALOG_SERVICE_EXCEPTION, SmvErrorCode.CATALOG_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.catalog.ICatalogService#getAllServices()
	 */
	@Override
	public ServiceDTO[] getAllServices() throws SmvServiceException {
		try {
			return InstanceGenerator.getCatalogImplInstance().getServices();
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.CATALOG_SERVICE_EXCEPTION, SmvErrorCode.CATALOG_SERVICE_EXCEPTION_MSG + exception);
		}
	}

}

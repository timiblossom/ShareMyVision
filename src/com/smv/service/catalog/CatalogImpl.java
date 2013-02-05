package com.smv.service.catalog;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.smv.common.bean.CatalogDTO;
import com.smv.common.bean.ProductDTO;
import com.smv.common.bean.ServiceDTO;
import com.smv.service.catalog.helper.GetCatalogHelper;
import com.smv.service.catalog.helper.GetProductsHelper;
import com.smv.service.catalog.helper.GetServicesHelper;

public class CatalogImpl implements ICatalog {

	protected static Logger LOGGER = Logger.getLogger(CatalogImpl.class);

	public CatalogImpl() {
	    BasicConfigurator.configure();
	}
	
	/* (non-Javadoc)
	 * @see com.smv.service.catalog.ICatalog#getCatalog()
	 */
	@Override
	public CatalogDTO[] getCatalog() throws Exception {
		return GetCatalogHelper.getCatalog();
	}

	/* (non-Javadoc)
	 * @see com.smv.service.catalog.ICatalog#getProducts()
	 */
	@Override
	public ProductDTO[] getProducts() throws Exception {
		return GetProductsHelper.getProducts();
	}

	/* (non-Javadoc)
	 * @see com.smv.service.catalog.ICatalog#getProducts(java.lang.Long, java.lang.String)
	 */
	@Override
	public ProductDTO[] getProducts(Long id, String status) throws Exception {
		return GetProductsHelper.getProducts(id, status);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.catalog.ICatalog#getServices()
	 */
	@Override
	public ServiceDTO[] getServices() throws Exception {
		return GetServicesHelper.getServices();
	}

}

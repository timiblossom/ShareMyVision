package com.smv.service.catalog;

import com.smv.common.bean.CatalogDTO;
import com.smv.common.bean.ProductDTO;
import com.smv.common.bean.ServiceDTO;

public interface ICatalog {
	public CatalogDTO[] getCatalog() throws Exception;
	public ProductDTO[] getProducts() throws Exception;
	public ServiceDTO[] getServices() throws Exception;

	public ProductDTO[] getProducts(Long id, String status) throws Exception;
}

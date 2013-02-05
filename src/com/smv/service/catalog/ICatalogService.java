package com.smv.service.catalog;


import javax.jws.WebService;

import com.smv.common.bean.CatalogDTO;
import com.smv.common.bean.ProductCompositionDTO;
import com.smv.common.bean.ProductDTO;
import com.smv.common.bean.ServiceCompositionDTO;
import com.smv.common.bean.ServiceDTO;
import com.smv.common.exception.SmvServiceException;


@WebService
public interface ICatalogService {

	public CatalogDTO[] getCatalog() throws SmvServiceException;
	public ProductDTO[] getAllProducts() throws SmvServiceException;
	public ServiceDTO[] getAllServices() throws SmvServiceException;

	public ProductDTO[] getProducts(Long id, String status) throws SmvServiceException;
}

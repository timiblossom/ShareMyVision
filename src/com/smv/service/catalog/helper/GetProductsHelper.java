/**
 * 
 */
package com.smv.service.catalog.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.smv.common.bean.ProductDTO;
import com.smv.common.bean.ProductKeyValuePairDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.catalog.db.dao.ProductDAO;
import com.smv.service.catalog.db.dao.ProductKeyValuePairDAO;
import com.smv.service.catalog.db.dbobject.ProductDO;
import com.smv.service.catalog.db.dbobject.ProductKeyValuePairDO;
import com.smv.service.catalog.db.dbobject.StatusDO;
import com.smv.util.text.StringUtils;

/**
 * @author TriNguyen
 *
 */
public class GetProductsHelper {

	private static final Logger LOGGER = Logger.getLogger(GetProductsHelper.class);

	protected GetProductsHelper() {
	}

	public static ProductDTO[] getProducts() throws SmvServiceException, Exception {
		
		try {
			// Retrieve all products
			List<ProductDO> productDOs = ProductDAO.getAllProducts();
			
			// Sanity check retrieval result
			if ((productDOs == null) || (productDOs.size() == 0)) {
				return  null;
			}

			// Construct list of products for return result
			List<ProductDTO> products = new ArrayList<ProductDTO>();

			// Iterate through each product
			for (ProductDO productDO : productDOs) {
				// Sanity check retrieval result
				if (productDO != null) {
					// Retrieve all product key value pairs corresponding to each product
					List<ProductKeyValuePairDO> productKeyValuePairDOs = ProductKeyValuePairDAO.getKeyValuePairByProduct(productDO.getId());

					// Create Product DTO
					ProductDTO product = new ProductDTO();

					// Set attributes of Product DTOs from Product DOs
					propagateProductAttributes(productDO, product);

					// Construct list of product key value pairs for association with specific product
					List<ProductKeyValuePairDTO> productKeyValuePairs = new ArrayList<ProductKeyValuePairDTO>();

					// Sanity check retrieval result of Key Value Pairs
					if ((productKeyValuePairDOs != null) && (productKeyValuePairDOs.size() > 0)) {

						// Iterate through each product key value pair
						for (ProductKeyValuePairDO productKeyValuePairDO : productKeyValuePairDOs) {

							// Sanity check retrieval result that specific Key Value Pair DO is not null
							if (productKeyValuePairDO != null) {

								// Create Product Key Value Pair DTO
								ProductKeyValuePairDTO productKeyValuePair = new ProductKeyValuePairDTO();

								// Set attributes of Product Key Value Pair DTOs from Product Key Value Pair DOs
								productKeyValuePair.setId(productKeyValuePairDO.getId());
								productKeyValuePair.setKeyPair(productKeyValuePairDO.getKeyPair());
								productKeyValuePair.setValuePair(productKeyValuePairDO.getValuePair());
								productKeyValuePair.setContainerParentId(productKeyValuePairDO.getParentContainerId());
								
								// Add product key value pair to list of product key value pairs
								productKeyValuePairs.add(productKeyValuePair);

							} // if (productKeyValuePairDO != null)
						} // for (ProductKeyValuePairDO productKeyValuePairDO : productKeyValuePairDOs)
					} // if ((productKeyValuePairDOs != null) && (productKeyValuePairDOs.size() > 0))

					// Associate product key value pairs corresponding to list of product key value pairs
					if ((productKeyValuePairs != null) && (productKeyValuePairs.size() > 0)) {

						ProductKeyValuePairDTO[] productKeyValuePairDtoArray = new ProductKeyValuePairDTO[productKeyValuePairs.size()];
						productKeyValuePairs.toArray(productKeyValuePairDtoArray);
						product.setProductKeyValuePairs(productKeyValuePairDtoArray);

					}

					// Add Product DTO entry into product list
					products.add(product);

				} // if (productDO != null)
			} // for (ProductDO productDO : productDOs)
			
			// Return Product DTOs
			if ((products != null) && (products.size() > 0)) {
				ProductDTO[] productsArray = new ProductDTO[products.size()];
				products.toArray(productsArray);
				return productsArray;
			} else {
				return null;
			}
		} catch (Exception exception) {
			String errorMessage = "getProducts() failed. " +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);

			SmvServiceException swe = new SmvServiceException();
			swe.setErrorCode(SmvErrorCode.CATALOG_SERVICE_EXCEPTION);
			swe.setErrorMessage(SmvErrorCode.CATALOG_SERVICE_EXCEPTION_MSG + exception);
			throw swe;
		}
	}

	public static ProductDTO[] getProducts(Long id, String status) throws SmvServiceException, Exception {
		
		if ((id == null) && (StringUtils.nullOrEmptyOrBlankString(status))) {
			return getProducts();
		}

		if ((id != null) && (StringUtils.nullOrEmptyOrBlankString(status))) {
			return getProductById(id);
		} else if ((id != null) && !(StringUtils.nullOrEmptyOrBlankString(status))) {
			return getProductsByIdAndStatus(id, status);
		}
		
		return getProductsByStatus(status);
	}

	protected static ProductDTO[] getProductById(Long id) throws SmvServiceException, Exception {
		
		try {
			ProductDO productDO = ProductDAO.getProductById(id);

			// Construct list of products for return result
			List<ProductDTO> products = new ArrayList<ProductDTO>();

			// Create Product DTO
			ProductDTO product = new ProductDTO();
			
			if (productDO != null) {
				// Set attributes of Product DTOs from Product DOs
				propagateProductAttributes(productDO, product);

				// Retrieve all product key value pairs corresponding to each product
				List<ProductKeyValuePairDO> productKeyValuePairDOs = ProductKeyValuePairDAO.getKeyValuePairByProduct(productDO.getId());

				
				// Construct list of product key value pairs for association with specific product
				List<ProductKeyValuePairDTO> productKeyValuePairs = new ArrayList<ProductKeyValuePairDTO>();

				// Sanity check retrieval result of Key Value Pairs
				if ((productKeyValuePairDOs != null) && (productKeyValuePairDOs.size() > 0)) {

					// Iterate through each product key value pair
					for (ProductKeyValuePairDO productKeyValuePairDO : productKeyValuePairDOs) {

						// Sanity check retrieval result that specific Key Value Pair DO is not null
						if (productKeyValuePairDO != null) {

							// Create Product Key Value Pair DTO
							ProductKeyValuePairDTO productKeyValuePair = new ProductKeyValuePairDTO();

							// Set attributes of Product Key Value Pair DTOs from Product Key Value Pair DOs
							productKeyValuePair.setId(productKeyValuePairDO.getId());
							productKeyValuePair.setKeyPair(productKeyValuePairDO.getKeyPair());
							productKeyValuePair.setValuePair(productKeyValuePairDO.getValuePair());
							productKeyValuePair.setContainerParentId(productKeyValuePairDO.getParentContainerId());
							
							// Add product key value pair to list of product key value pairs
							productKeyValuePairs.add(productKeyValuePair);

						} // if (productKeyValuePairDO != null)
					} // for (ProductKeyValuePairDO productKeyValuePairDO : productKeyValuePairDOs)
				} // if ((productKeyValuePairDOs != null) && (productKeyValuePairDOs.size() > 0))

				// Associate product key value pairs corresponding to list of product key value pairs
				if ((productKeyValuePairs != null) && (productKeyValuePairs.size() > 0)) {

					ProductKeyValuePairDTO[] productKeyValuePairDtoArray = new ProductKeyValuePairDTO[productKeyValuePairs.size()];
					productKeyValuePairs.toArray(productKeyValuePairDtoArray);
					product.setProductKeyValuePairs(productKeyValuePairDtoArray);

				}
			}
			
			// Add single product to products array
			products.add(product);
			
			// Return Product DTOs
			if ((products != null) && (products.size() > 0)) {
				ProductDTO[] productsArray = new ProductDTO[products.size()];
				products.toArray(productsArray);
				return productsArray;
			} else {
				return null;
			}
		} catch (Exception exception) {
			String errorMessage = "getProductById(id) failed. " +
									"\n " +
									"id = " +
									id +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);

			SmvServiceException swe = new SmvServiceException();
			swe.setErrorCode(SmvErrorCode.CATALOG_SERVICE_EXCEPTION);
			swe.setErrorMessage(SmvErrorCode.CATALOG_SERVICE_EXCEPTION_MSG + exception);
			throw swe;
		}
	}
	
	protected static ProductDTO[] getProductsByStatus(String status) throws SmvServiceException, Exception {
		try {
			Long statusId = StatusDO.getStatus(status).getId();
			
			// Retrieve all products
			List<ProductDO> productDOs = ProductDAO.getProductsByStatusId(statusId);
			
			// Sanity check retrieval result
			if ((productDOs == null) || (productDOs.size() == 0)) {
				return  null;
			}

			// Construct list of products for return result
			List<ProductDTO> products = new ArrayList<ProductDTO>();

			// Iterate through each product
			for (ProductDO productDO : productDOs) {
				// Sanity check retrieval result
				if (productDO != null) {
					// Retrieve all product key value pairs corresponding to each product
					List<ProductKeyValuePairDO> productKeyValuePairDOs = ProductKeyValuePairDAO.getKeyValuePairByProduct(productDO.getId());

					// Create Product DTO
					ProductDTO product = new ProductDTO();

					// Set attributes of Product DTOs from Product DOs
					propagateProductAttributes(productDO, product);

					// Construct list of product key value pairs for association with specific product
					List<ProductKeyValuePairDTO> productKeyValuePairs = new ArrayList<ProductKeyValuePairDTO>();

					// Sanity check retrieval result of Key Value Pairs
					if ((productKeyValuePairDOs != null) && (productKeyValuePairDOs.size() > 0)) {

						// Iterate through each product key value pair
						for (ProductKeyValuePairDO productKeyValuePairDO : productKeyValuePairDOs) {

							// Sanity check retrieval result that specific Key Value Pair DO is not null
							if (productKeyValuePairDO != null) {

								// Create Product Key Value Pair DTO
								ProductKeyValuePairDTO productKeyValuePair = new ProductKeyValuePairDTO();

								// Set attributes of Product Key Value Pair DTOs from Product Key Value Pair DOs
								productKeyValuePair.setId(productKeyValuePairDO.getId());
								productKeyValuePair.setKeyPair(productKeyValuePairDO.getKeyPair());
								productKeyValuePair.setValuePair(productKeyValuePairDO.getValuePair());
								productKeyValuePair.setContainerParentId(productKeyValuePairDO.getParentContainerId());
								
								// Add product key value pair to list of product key value pairs
								productKeyValuePairs.add(productKeyValuePair);

							} // if (productKeyValuePairDO != null)
						} // for (ProductKeyValuePairDO productKeyValuePairDO : productKeyValuePairDOs)
					} // if ((productKeyValuePairDOs != null) && (productKeyValuePairDOs.size() > 0))

					// Associate product key value pairs corresponding to list of product key value pairs
					if ((productKeyValuePairs != null) && (productKeyValuePairs.size() > 0)) {

						ProductKeyValuePairDTO[] productKeyValuePairDtoArray = new ProductKeyValuePairDTO[productKeyValuePairs.size()];
						productKeyValuePairs.toArray(productKeyValuePairDtoArray);
						product.setProductKeyValuePairs(productKeyValuePairDtoArray);

					}

					// Add Product DTO entry into product list
					products.add(product);

				} // if (productDO != null)
			} // for (ProductDO productDO : productDOs)
			
			// Return Product DTOs
			if ((products != null) && (products.size() > 0)) {
				ProductDTO[] productsArray = new ProductDTO[products.size()];
				products.toArray(productsArray);
				return productsArray;
			} else {
				return null;
			}
		} catch (Exception exception) {
			String errorMessage = "getProducts(status) failed. " +
									"\n " +
									"status = " +
									status +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);

			SmvServiceException swe = new SmvServiceException();
			swe.setErrorCode(SmvErrorCode.CATALOG_SERVICE_EXCEPTION);
			swe.setErrorMessage(SmvErrorCode.CATALOG_SERVICE_EXCEPTION_MSG + exception);
			throw swe;
		}
	}
		
	protected static ProductDTO[] getProductsByIdAndStatus(Long id, String status) throws SmvServiceException, Exception {
		try {
			Long statusId = StatusDO.getStatus(status).getId();
			
			// Retrieve all products
			List<ProductDO> productDOs = ProductDAO.getProductByIdAndStatusId(id, statusId);
			
			// Sanity check retrieval result
			if ((productDOs == null) || (productDOs.size() == 0)) {
				return  null;
			}

			// Construct list of products for return result
			List<ProductDTO> products = new ArrayList<ProductDTO>();

			// Iterate through each product
			for (ProductDO productDO : productDOs) {
				// Sanity check retrieval result
				if (productDO != null) {
					// Retrieve all product key value pairs corresponding to each product
					List<ProductKeyValuePairDO> productKeyValuePairDOs = ProductKeyValuePairDAO.getKeyValuePairByProduct(productDO.getId());

					// Create Product DTO
					ProductDTO product = new ProductDTO();

					// Set attributes of Product DTOs from Product DOs
					propagateProductAttributes(productDO, product);

					
					// Construct list of product key value pairs for association with specific product
					List<ProductKeyValuePairDTO> productKeyValuePairs = new ArrayList<ProductKeyValuePairDTO>();

					// Sanity check retrieval result of Key Value Pairs
					if ((productKeyValuePairDOs != null) && (productKeyValuePairDOs.size() > 0)) {

						// Iterate through each product key value pair
						for (ProductKeyValuePairDO productKeyValuePairDO : productKeyValuePairDOs) {

							// Sanity check retrieval result that specific Key Value Pair DO is not null
							if (productKeyValuePairDO != null) {

								// Create Product Key Value Pair DTO
								ProductKeyValuePairDTO productKeyValuePair = new ProductKeyValuePairDTO();

								// Set attributes of Product Key Value Pair DTOs from Product Key Value Pair DOs
								productKeyValuePair.setId(productKeyValuePairDO.getId());
								productKeyValuePair.setKeyPair(productKeyValuePairDO.getKeyPair());
								productKeyValuePair.setValuePair(productKeyValuePairDO.getValuePair());
								productKeyValuePair.setContainerParentId(productKeyValuePairDO.getParentContainerId());
								
								// Add product key value pair to list of product key value pairs
								productKeyValuePairs.add(productKeyValuePair);

							} // if (productKeyValuePairDO != null)
						} // for (ProductKeyValuePairDO productKeyValuePairDO : productKeyValuePairDOs)
					} // if ((productKeyValuePairDOs != null) && (productKeyValuePairDOs.size() > 0))

					// Associate product key value pairs corresponding to list of product key value pairs
					if ((productKeyValuePairs != null) && (productKeyValuePairs.size() > 0)) {

						ProductKeyValuePairDTO[] productKeyValuePairDtoArray = new ProductKeyValuePairDTO[productKeyValuePairs.size()];
						productKeyValuePairs.toArray(productKeyValuePairDtoArray);
						product.setProductKeyValuePairs(productKeyValuePairDtoArray);

					}

					// Add Product DTO entry into product list
					products.add(product);

				} // if (productDO != null)
			} // for (ProductDO productDO : productDOs)
			
			// Return Product DTOs
			if ((products != null) && (products.size() > 0)) {
				ProductDTO[] productsArray = new ProductDTO[products.size()];
				products.toArray(productsArray);
				return productsArray;
			} else {
				return null;
			}
		} catch (Exception exception) {
			String errorMessage = "getProductsByIdAndStatus(id, status) failed. " +
									"\n " +
									"id = " +
									id +
									"\n " +
									"status = " +
									status +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);

			SmvServiceException swe = new SmvServiceException();
			swe.setErrorCode(SmvErrorCode.CATALOG_SERVICE_EXCEPTION);
			swe.setErrorMessage(SmvErrorCode.CATALOG_SERVICE_EXCEPTION_MSG + exception);
			throw swe;
		}
	}

	protected static void propagateProductAttributes(ProductDO productDO, ProductDTO product) throws SmvServiceException {
		try {
			// Set attributes of Product DTOs from Product DOs
			product.setId(productDO.getId());
			product.setName(productDO.getName());
			product.setDescription(productDO.getDescription());
			if (productDO.getStatusId() != null) {
				String statusName = StatusDO.getStatus(productDO.getStatusId()).getName();
				product.setStatus(statusName);
			}
			product.setStartDate(productDO.getStartDate());
			product.setEndDate(productDO.getEndDate());

		} catch (Exception exception) {
			String errorMessage = "propagateProductAttributes(productDO, product) failed. " +
									"\n " +
									"productDO = " +
									productDO +
									"\n " +
									"product = " +
									product +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);

			SmvServiceException swe = new SmvServiceException();
			swe.setErrorCode(SmvErrorCode.CATALOG_SERVICE_EXCEPTION);
			swe.setErrorMessage(SmvErrorCode.CATALOG_SERVICE_EXCEPTION_MSG + exception);
			throw swe;
		}
	}
}

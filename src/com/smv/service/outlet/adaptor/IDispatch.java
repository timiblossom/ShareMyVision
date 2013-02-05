package com.smv.service.outlet.adaptor;

import com.smv.common.bean.OutletDTO;
import com.smv.common.bean.UserOutletContentDTO;
import com.smv.common.exception.SmvServiceException;

/**
 * @author TriNguyen
 *
 */
public interface IDispatch {

	public Boolean dispatchToMultipleOutlets(Long userId, 
			UserOutletContentDTO content, 
			OutletDTO[] outlets) 
	throws SmvServiceException, 
			Exception;

	public Boolean dispatchToSingleOutlet(Long userId, 
			UserOutletContentDTO content, 
			OutletDTO outlet) 
	throws SmvServiceException, 
			Exception;
}

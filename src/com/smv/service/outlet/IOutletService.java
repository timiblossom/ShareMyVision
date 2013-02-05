package com.smv.service.outlet;


import javax.jws.WebParam;
import javax.jws.WebService;

import com.smv.common.bean.OutletDTO;
import com.smv.common.bean.UserOutletContentDTO;
import com.smv.common.bean.UserOutletDTO;
import com.smv.common.exception.SmvServiceException;


@WebService
public interface IOutletService {

	/*
	 * Retrieves enumeration of all Outlets in system. 
	 */
	public OutletDTO[] getOutlet() throws SmvServiceException;
	
	/*
	 * Retrieves Outlets associated with user 
	 */
	public OutletDTO[] getOutletForUser(@WebParam(name = "userId") Long userId) throws SmvServiceException;
	
	/*
	 * Like upsert, if there is some data in the dto, override db with the new data. 
	 * If not existed, just save.
	 */
	public UserOutletDTO enableOutletForUser(@WebParam(name = "userOutlet") UserOutletDTO userOutlet) throws SmvServiceException; 
	
	/*
	 * Just mark disable
	 */
	public UserOutletDTO disableOutletForUser(@WebParam(name = "userOutlet") UserOutletDTO userOutlet) throws SmvServiceException;
	
	/*
	 * If outlets is null, publish to all.
	 * Otherwise, if outlets is NOT null, then publish only to that array of outlets
	 */
	public Boolean publishToOutlets(@WebParam(name = "userId") Long userId, 
			                        @WebParam(name = "content") UserOutletContentDTO content, 
			                        @WebParam(name = "outlets") OutletDTO[] outlets) 
		throws SmvServiceException;
	
}

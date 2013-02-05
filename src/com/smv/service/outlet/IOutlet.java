package com.smv.service.outlet;

import com.smv.common.bean.OutletDTO;
import com.smv.common.bean.UserOutletDTO;
import com.smv.common.bean.UserOutletContentDTO;

public interface IOutlet {
	
	/*
	 * Retrieves enumeration of all Outlets in system. 
	 */
	public OutletDTO[] getOutlet() throws Exception;
	
	/*
	 * Retrieves Outlets associated with user 
	 */
	public OutletDTO[] getOutletForUser(Long userId) throws Exception;
	
	/*
	 * Like upsert(), if there is some data in the dto, override db with the new data for user. 
	 * If not existed, just save.
	 */
	public UserOutletDTO enableOutletForUser(UserOutletDTO userOutlet) throws Exception; 
	
	/*
	 * Just mark disable for user
	 */
	public UserOutletDTO disableOutletForUser(UserOutletDTO userOutlet) throws Exception;
	
	/*
	 * If outlets is null, publish to all.
	 * Otherwise, if outlets is NOT null, then publish only to that array of outlets
	 */
	public Boolean publishToOutlets(Long userId, 
									UserOutletContentDTO content, 
									OutletDTO[] outlets) 
	throws Exception;
	
}

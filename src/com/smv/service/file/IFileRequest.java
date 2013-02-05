package com.smv.service.file;

import com.smv.common.exception.SmvServiceException;

/**
 * @author Minh Do
 * 07/22/2010
 */
public interface IFileRequest {
	public boolean validate() throws SmvServiceException ;
	public Object execute() throws Exception;
	
}

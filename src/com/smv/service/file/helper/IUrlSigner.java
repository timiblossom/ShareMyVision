package com.smv.service.file.helper;

import com.smv.common.bean.EventDTO;



/**
 * @author Minh Do
 * 03/01/2010
 */
public interface IUrlSigner {
	
	public EventDTO signUploadFileUrls(EventDTO event) throws Exception; 
	public EventDTO signDownloadFileUrl(EventDTO event) throws Exception;
	public EventDTO signDeleteFileUrl(EventDTO event) throws Exception;
	public EventDTO signGetFileDetailUrl(EventDTO event) throws Exception;	

}
